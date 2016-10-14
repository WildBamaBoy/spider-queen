package sq.core.forge;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import sq.client.render.CocoonRenderFactory;
import sq.entities.EntityCocoon;
import sq.items.ISubtypeModelProvider;

public class ClientProxy extends ServerProxy
{
	@Override
	public void registerEntityModels()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityCocoon.class, new CocoonRenderFactory());
	}
	
	@Override
	public void registerItemRenderer(Item item, String name) 
	{
		if (item instanceof ISubtypeModelProvider)
		{
			ISubtypeModelProvider provider = (ISubtypeModelProvider) item;
			ModelLoader.setCustomMeshDefinition(item, provider.getMeshDefinition());
			ResourceLocation location = provider.getResourceLocation();
			
			if (location == null)
			{
				location = new ResourceLocation("spiderqueen", "item/" + name);
			}
			
			for (String variant : provider.getVariantNames())
			{
				ModelLoader.registerItemVariants(item, new ModelResourceLocation(location, variant));
			}
		}
	}
}
