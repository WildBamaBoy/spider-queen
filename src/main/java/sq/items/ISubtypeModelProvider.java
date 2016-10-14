package sq.items;

import java.util.List;

import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface ISubtypeModelProvider 
{
	@SideOnly(Side.CLIENT)
	ItemMeshDefinition getMeshDefinition();
	
	List<String> getVariantNames();
	
	/* Return null */
	ResourceLocation getResourceLocation();
}
