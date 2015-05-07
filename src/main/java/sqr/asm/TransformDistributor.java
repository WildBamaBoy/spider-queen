package sqr.asm;

import static org.objectweb.asm.Opcodes.ALOAD;
import static org.objectweb.asm.Opcodes.ARETURN;
import static org.objectweb.asm.Opcodes.INVOKESTATIC;
import net.minecraft.launchwrapper.IClassTransformer;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

public class TransformDistributor implements IClassTransformer
{
	@Override
	public byte[] transform(String name, String transformedName, byte[] basicClass)
	{
		if (transformedName.equals("net.minecraft.item.ItemFood"))
		{
			return transformOnEaten(basicClass);
		}
		
		return basicClass;
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
				System.out.println("Spider Queen is now patching onEaten()...");
				
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
					System.out.println("Patching onEaten() failed! Things aren't going to work well!");
					return basicClass;
				}
				
				else
				{
					//Insert the method call before the method returns.
					InsnList inject = new InsnList();
					
					inject.add(new VarInsnNode(ALOAD, 1));
					inject.add(new VarInsnNode(ALOAD, 2));
					inject.add(new VarInsnNode(ALOAD, 3));
					inject.add(new MethodInsnNode(INVOKESTATIC, "sqr/asm/ASMEventHooks", "onEaten", "(Lnet/minecraft/item/ItemStack;Lnet/minecraft/world/World;Lnet/minecraft/entity/player/EntityPlayer;)V", false));
					
					method.instructions.insertBefore(target, inject);
					System.out.println("Spider Queen has successfully patched onEaten().");
				}
				
				break;
			}
		}
		
		ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
		node.accept(writer);
		
		return writer.toByteArray();
	}

}
