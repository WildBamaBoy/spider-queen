package sq.core.forge;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import sq.client.render.CocoonRenderFactory;
import sq.client.render.WebshotRenderFactory;
import sq.entities.EntityCocoon;
import sq.entities.EntityWebshot;
import sq.items.ISubtypeModelProvider;

public class ClientProxy extends ServerProxy
{
	private HashMap<String, Item> initRegistrationMap = new HashMap<String, Item>();
	
	@Override
	public void registerEntityModels()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityCocoon.class, new CocoonRenderFactory());
		RenderingRegistry.registerEntityRenderingHandler(EntityWebshot.class, new WebshotRenderFactory());
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
		
		// Store the model registration for later, subtype models must be registered in preInit
		// but regular registration takes place in init.
		else 
		{
			initRegistrationMap.put(name, item);
		}
	}
	
	@Override
	public void handleInitEvent()
	{
		ItemModelMesher mesher = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();
		
		for (Map.Entry<String, Item> entry : initRegistrationMap.entrySet())
		{
			mesher.register(entry.getValue(), 0, new ModelResourceLocation("spiderqueen:" + entry.getKey(), "inventory"));
		}
	}
}
