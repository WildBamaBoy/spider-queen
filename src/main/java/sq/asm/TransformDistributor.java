package sq.asm;

import static org.objectweb.asm.Opcodes.ALOAD;
import static org.objectweb.asm.Opcodes.ARETURN;
import static org.objectweb.asm.Opcodes.ILOAD;
import static org.objectweb.asm.Opcodes.INVOKESTATIC;
import static org.objectweb.asm.Opcodes.POP;

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

import net.minecraft.launchwrapper.IClassTransformer;

/**
 * This class manipulates Minecraft's bytecode in order to inject the method calls
 * present in ASMEventHooks.java.
 */
public class TransformDistributor implements IClassTransformer
{
	public static final int ASM_CHANGES_TO_MAKE = 3;
	public static int asmChangesMade;
	public static final Logger logger = LogManager.getLogger("SQ");
	
	@Override
	public byte[] transform(String name, String transformedName, byte[] basicClass)
	{
		//Work with the readable name, not the obfuscated name.
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

	private byte[] transformSpider(byte[] basicClass)
	{
		boolean found = false;
		ClassNode node = new ClassNode();
		ClassReader reader = new ClassReader(basicClass);
		reader.accept(node, 0);

		for (MethodNode method : node.methods) //Run through each method in this class.
		{
			//The method in a deobf environment is findPlayerToAttack. 
			//In a normal environment this is func_xxxxx_x, which changes across Minecraft versions.
			if (method.name.equals("findPlayerToAttack") || method.name.equals("func_70782_k"))
			{
				logger.info("Patching findPlayerToAttack()...");
				
				//We completely wipe out this method and redirect it to use our own.
				method.instructions.clear();
				
				//Build the new list of opcodes to be added to the method.
				InsnList inject = new InsnList();
				inject.add(new LabelNode(new Label()));
				inject.add(new VarInsnNode(ALOAD, 0)); //Load 'this'
				inject.add(new MethodInsnNode(INVOKESTATIC, //Invoke the method.
						"sq/asm/ASMEventHooks", 
						"onSpiderFindPlayerToAttack", 
						"(Lnet/minecraft/entity/monster/EntitySpider;)Lnet/minecraft/entity/Entity;"
						, false));
				inject.add(new InsnNode(ARETURN)); //Return whatever the method returned.
				
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
			//Just like transformSpider, onEaten() is in deobf, func_xxxxx_x is for regular players.
			if (method.name.equals("onEaten") || method.name.equals("func_77654_b"))
			{
				logger.info("Patching onEaten()...");
				
				//Since we don't want to remove and rewrite this method, we are keeping
				//its current code in place and searching for a suitable insertion point.

				//The target insertion point is the return statement. We're searching
				//each instruction for ARETURN.
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
					InsnList inject = new InsnList();

					//Build our code to inject.
					inject.add(new VarInsnNode(ALOAD, 1)); //Load the item stack instance.
					inject.add(new VarInsnNode(ALOAD, 2)); //Load the world instance.
					inject.add(new VarInsnNode(ALOAD, 3)); //Load the player instance.
					inject.add(new MethodInsnNode(INVOKESTATIC, //Invoke our method.
							"sq/asm/ASMEventHooks", 
							"onEaten", 
							"(Lnet/minecraft/item/ItemStack;Lnet/minecraft/world/World;Lnet/minecraft/entity/player/EntityPlayer;)V", 
							false));

					//Insert our code just before the return statement that we found earlier.
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
			//Deobf name is generate(), obfuscated name is func_xxxxx_x.
			if (method.name.equals("generate") || method.name.equals("func_76484_a"))
			{
				found = true;
				logger.info("Patching WorldGenPumpkin.generate()...");
				
				//Same as before, we're searching for a location where we can insert
				//our method call.
				
				//The target instruction node is just after the POP statement.
				AbstractInsnNode target = null;
				for (int i = 0; i < method.instructions.size(); i++)
				{
					AbstractInsnNode currentNode = method.instructions.get(i);

					if (currentNode.getOpcode() == POP)
					{
						target = currentNode.getNext(); //AFTER the POP statement. We don't want to call the method beforehand.
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

					//Build our code.
					inject.add(new VarInsnNode(ALOAD, 1)); //World
					inject.add(new VarInsnNode(ALOAD, 2)); //Random
					inject.add(new VarInsnNode(ILOAD, 7)); //X
					inject.add(new VarInsnNode(ILOAD, 8)); //Y
					inject.add(new VarInsnNode(ILOAD, 9)); //Z
					inject.add(new MethodInsnNode(INVOKESTATIC, 
							"sq/asm/ASMEventHooks", 
							"onPumpkinGenerate", 
							"(Lnet/minecraft/world/World;Ljava/util/Random;III)V", 
							false));

					//Inject.
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

	private void incrementChangesMade()
	{
		asmChangesMade++;
		logger.info("Performed patch " + asmChangesMade + "/" + ASM_CHANGES_TO_MAKE + ".");
	}
}
