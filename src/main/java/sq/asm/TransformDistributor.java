package sq.asm;

import static org.objectweb.asm.Opcodes.ALOAD;
import static org.objectweb.asm.Opcodes.ARETURN;
import static org.objectweb.asm.Opcodes.ILOAD;
import static org.objectweb.asm.Opcodes.INVOKESTATIC;
import static org.objectweb.asm.Opcodes.POP;
import net.minecraft.launchwrapper.IClassTransformer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

import sq.core.SpiderCore;

public class TransformDistributor implements IClassTransformer
{
	public static final int ASM_CHANGES_TO_MAKE = 3;
	public static int asmChangesMade;
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
		
		else if (transformedName.equals("net.minecraft.world.gen.feature.WorldGenPumpkin"))
		{
			return transformPumpkin(basicClass);
		}
		
		return basicClass;
	}

	private void incrementChangesMade()
	{
		asmChangesMade++;
		logger.info("Performed patch " + asmChangesMade + "/" + ASM_CHANGES_TO_MAKE + ".");
	}
	
	private byte[] transformSpider(byte[] basicClass)
	{
		boolean found = false;
		ClassNode node = new ClassNode();
		ClassReader reader = new ClassReader(basicClass);
		reader.accept(node, 0);

		for (MethodNode method : node.methods)
		{
			if (method.name.equals("findPlayerToAttack") || method.name.equals("func_70782_k"))
			{
				logger.info("Patching findPlayerToAttack()...");
				
				method.instructions.clear();
				
				InsnList inject = new InsnList();

				inject.add(new LabelNode(new Label()));
				inject.add(new VarInsnNode(ALOAD, 0));
				inject.add(new MethodInsnNode(INVOKESTATIC, "sq/asm/ASMEventHooks", "onSpiderFindPlayerToAttack", "(Lnet/minecraft/entity/monster/EntitySpider;)Lnet/minecraft/entity/Entity;", false));
				inject.add(new InsnNode(ARETURN));
				
				method.instructions.insert(inject);
				incrementChangesMade();
				break;
			}
		}
		
		ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
		node.accept(writer);

		return writer.toByteArray();
	}

	private byte[] transformOnEaten(byte[] basicClass)
	{
		boolean found = false;
		ClassNode node = new ClassNode();
		ClassReader reader = new ClassReader(basicClass);
		reader.accept(node, 0);

		for (MethodNode method : node.methods)
		{
			if (method.name.equals("onEaten") || method.name.equals("func_77654_b"))
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
					incrementChangesMade();
				}

				break;
			}
		}
		
		ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
		node.accept(writer);

		return writer.toByteArray();
	}

	private byte[] transformPumpkin(byte[] basicClass) 
	{
		boolean found = false;
		ClassNode node = new ClassNode();
		ClassReader reader = new ClassReader(basicClass);
		reader.accept(node, 0);

		for (MethodNode method : node.methods)
		{
			if (method.name.equals("generate") || method.name.equals("func_76484_a"))
			{
				found = true;
				logger.info("Patching WorldGenPumpkin.generate()...");
				
				//Target instruction node is just after the POP statement.
				AbstractInsnNode target = null;
				for (int i = 0; i < method.instructions.size(); i++)
				{
					AbstractInsnNode currentNode = method.instructions.get(i);

					if (currentNode.getOpcode() == POP)
					{
						target = currentNode.getNext(); //After the pop statement.
						break;
					}
				}

				//Make sure we found the target.
				if (target == null)
				{
					return basicClass;
				}

				else
				{
					InsnList inject = new InsnList();

					inject.add(new VarInsnNode(ALOAD, 1));
					inject.add(new VarInsnNode(ALOAD, 2));
					inject.add(new VarInsnNode(ILOAD, 7));
					inject.add(new VarInsnNode(ILOAD, 8));
					inject.add(new VarInsnNode(ILOAD, 9));
					
					inject.add(new MethodInsnNode(INVOKESTATIC, "sq/asm/ASMEventHooks", "onPumpkinGenerate", "(Lnet/minecraft/world/World;Ljava/util/Random;III)V", false));

					method.instructions.insertBefore(target, inject);
					incrementChangesMade();
				}

				break;
			}
		}
		
		ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
		node.accept(writer);

		return writer.toByteArray();
	}
}
