package sq.asm;

import static org.objectweb.asm.Opcodes.ALOAD;
import static org.objectweb.asm.Opcodes.ARETURN;
import static org.objectweb.asm.Opcodes.CHECKCAST;
import static org.objectweb.asm.Opcodes.INSTANCEOF;
import static org.objectweb.asm.Opcodes.INVOKESTATIC;
import static org.objectweb.asm.Opcodes.IRETURN;
import net.minecraft.launchwrapper.IClassTransformer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FrameNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.JumpInsnNode;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.TypeInsnNode;
import org.objectweb.asm.tree.VarInsnNode;

import sq.core.SpiderCore;

public class TransformDistributor implements IClassTransformer
{
	public static final Logger logger = LogManager.getLogger("SQ");

	@Override
	public byte[] transform(String name, String transformedName, byte[] basicClass)
	{
		if (transformedName.equals("net.minecraft.item.ItemFood"))
		{
			return transformOnEaten(basicClass);
		}

		else if (transformedName.equals("net.minecraft.entity.monster.EntitySpider"))
		{
			return transformSpider(basicClass);
		}
		
		else if (transformedName.equals("net.minecraft.entity.monster.EntityMob"))
		{
			return transformMob(basicClass);
		}
		
		return basicClass;
	}

	private byte[] transformMob(byte[] basicClass)
	{
		boolean found = false;
		ClassNode node = new ClassNode();
		ClassReader reader = new ClassReader(basicClass);
		reader.accept(node, 0);

		for (MethodNode method : node.methods)
		{
			if (method.name.equals("attackEntityFrom"))
			{
				found = true;
				logger.info("Patching EntityMob.attackEntityFrom()...");
				
				InsnList inject = new InsnList();
				LabelNode label10 = null;
				AbstractInsnNode injectPoint = null;
				
				//Search for the insertion point.
				// ALOAD 3: entity
				// ALOAD 0: this
				// IF_ACMPEQ L8  <-- after this jump
				boolean f1 = true;
				boolean f2 = true;
				
				for (int i = 0; i < method.instructions.size(); i++)
				{
					AbstractInsnNode n = method.instructions.get(i);
					
					if (n instanceof VarInsnNode)
					{
						VarInsnNode vn = (VarInsnNode)n;
						
						if (vn.getOpcode() == ALOAD && vn.getNext().getNext() instanceof JumpInsnNode)
						{
							f1 = false;
							
							JumpInsnNode jn = (JumpInsnNode)vn.getNext().getNext();
							
							if (jn.getOpcode() == Opcodes.IF_ACMPEQ)
							{
								injectPoint = jn.getNext(); //AFTER the jump instruction.
								f2 = false;
							}
						}
					}
				}
				
				if (f1)
				{
					SpiderCore.asmErrors.add("ALOAD followed by jump not found.");
				}
				
				if (f2)
				{
					SpiderCore.asmErrors.add("No jump with IF_ACMPEQ at expected location.");
				}
				
				//Search for L10, to jump to after we check if the provided entity is a player.
				//L10
				// LINENUMBER 38 L10
			    // FRAME APPEND [Entity]
			    // ALOAD 0: this
			    // ALOAD 3: entity
			    // PUTFIELD EntityMob.entityToAttack : Entity
				f1 = true;
				
				for (int i = 0; i < method.instructions.size(); i++)
				{
					AbstractInsnNode n = method.instructions.get(i);
					
					if (n instanceof LabelNode)
					{
						LabelNode ln = (LabelNode)n;
						
						if (ln.getNext().getNext() instanceof FrameNode)
						{
							FrameNode fn = (FrameNode)ln.getNext().getNext();
							
							if (fn.type == Opcodes.F_APPEND)
							{
								f1 = false;
								label10 = ln;
								break;
							}
						}
					}
				}
				
				if (f1)
				{
					SpiderCore.asmErrors.add("Frame with F_APPEND not found.");
					SpiderCore.asmCompleted = false;
				}
				
				//if (entity instanceof EntityPlayer)
				inject.add(new VarInsnNode(ALOAD, 3));
				inject.add(new TypeInsnNode(INSTANCEOF, "net/minecraft/entity/player/EntityPlayer"));
				inject.add(new JumpInsnNode(Opcodes.IFEQ, label10));
				
				//return onMobAttackedByPlayer
				inject.add(new VarInsnNode(ALOAD, 0));
				inject.add(new VarInsnNode(ALOAD, 3));
				inject.add(new TypeInsnNode(CHECKCAST, "net/minecraft/entity/player/EntityPlayer"));
				inject.add(new MethodInsnNode(INVOKESTATIC, "sq/asm/ASMEventHooks", "onMobAttackedByPlayer", "(Lnet/minecraft/entity/monster/EntityMob;Lnet/minecraft/entity/player/EntityPlayer;)Z", false));
				inject.add(new InsnNode(IRETURN));
				
				method.instructions.insertBefore(injectPoint, inject);
				logger.info("Successfully patched attackEntityFrom().");
				break;
			}
		}

		if (!found)
		{
			SpiderCore.asmCompleted = false;
			SpiderCore.asmErrors.add("Failed to patch EntityMob.attackEntityFrom(), method not found.");
		}
		
		ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
		node.accept(writer);

		return writer.toByteArray();
	}
	
	private byte[] transformSpider(byte[] basicClass)
	{
		boolean found = false;
		ClassNode node = new ClassNode();
		ClassReader reader = new ClassReader(basicClass);
		reader.accept(node, 0);

		for (MethodNode method : node.methods)
		{
			if (method.name.equals("findPlayerToAttack"))
			{
				found = true;
				logger.info("Rewriting EntitySpider.findPlayerToAttack()...");
				method.instructions.clear();
				
				InsnList inject = new InsnList();

				inject.add(new LabelNode(new Label()));
				inject.add(new VarInsnNode(ALOAD, 0));
				inject.add(new MethodInsnNode(INVOKESTATIC, "sq/asm/ASMEventHooks", "onSpiderFindPlayerToAttack", "(Lnet/minecraft/entity/monster/EntitySpider;)Lnet/minecraft/entity/Entity;", false));
				inject.add(new InsnNode(ARETURN));
				
				method.instructions.insert(inject);
				logger.info("Successfully rewrote findPlayerToAttack().");
				break;
			}
		}

		if (!found)
		{
			SpiderCore.asmCompleted = false;
			SpiderCore.asmErrors.add("Failed to rewrite EntitySpider.findPlayerToAttack()");
		}
		
		ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
		node.accept(writer);

		return writer.toByteArray();
	}

	private byte[] transformOnEaten(byte[] basicClass)
	{
		ClassNode node = new ClassNode();
		ClassReader reader = new ClassReader(basicClass);
		reader.accept(node, 0);

		for (MethodNode method : node.methods)
		{
			if (method.name.equals("onEaten"))
			{
				logger.info("Patching onEaten()...");

				//Target instruction node is the return statement.
				AbstractInsnNode target = null;
				for (int i = 0; i < method.instructions.size(); i++)
				{
					AbstractInsnNode currentNode = method.instructions.get(i);

					if (currentNode.getOpcode() == ARETURN)
					{
						target = currentNode;
						break;
					}
				}

				//Make sure we found the target.
				if (target == null)
				{
					logger.fatal("Patching onEaten() failed! Things aren't going to work well!");
					SpiderCore.asmCompleted = false;
					SpiderCore.asmErrors.add("Failed to patch ItemFood.onEaten().");
					return basicClass;
				}

				else
				{
					//Insert the method call before the method returns.
					InsnList inject = new InsnList();

					inject.add(new VarInsnNode(ALOAD, 1));
					inject.add(new VarInsnNode(ALOAD, 2));
					inject.add(new VarInsnNode(ALOAD, 3));
					inject.add(new MethodInsnNode(INVOKESTATIC, "sq/asm/ASMEventHooks", "onEaten", "(Lnet/minecraft/item/ItemStack;Lnet/minecraft/world/World;Lnet/minecraft/entity/player/EntityPlayer;)V", false));

					method.instructions.insertBefore(target, inject);
					logger.info("Successfully patched onEaten().");
				}

				break;
			}
		}

		ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
		node.accept(writer);

		return writer.toByteArray();
	}
}
