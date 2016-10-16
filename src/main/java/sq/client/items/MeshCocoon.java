package sq.client.items;

import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import sq.enums.EnumCocoonType;

/*
 * Defines the location of the cocoon's model
 */
public class MeshCocoon implements ItemMeshDefinition 
{
	private final String name;
	
	public MeshCocoon(String name)
	{
		this.name = name;
	}
	
	@Override
	public ModelResourceLocation getModelLocation(ItemStack stack) 
	{
		return new ModelResourceLocation(new ResourceLocation("spiderqueen", "item/" + name), "type=" + EnumCocoonType.getCocoonType(stack.getMetadata()).getName());
	}

}
