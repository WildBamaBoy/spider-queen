package sq.util;

import java.util.HashMap;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelEnderman;
import net.minecraft.client.model.ModelVillager;
import net.minecraft.client.model.ModelWitch;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import sq.core.SpiderCore;

public final class CocoonRenderCache 
{
	private static CocoonRenderCache instance;
	private HashMap<Class, ModelRenderPair> entityModelRenderMap;
	
	private CocoonRenderCache(){}
	
	public static CocoonRenderCache getInstance()
	{
		if (instance == null)
		{
			instance = new CocoonRenderCache();
			instance.entityModelRenderMap = new HashMap<Class, ModelRenderPair>();
		}
		
		return instance;
	}
	
	/*
	 * Returns the model / render class pair for the given entity class. If the MRP isn't cached,
	 * it is added to the map and returned.
	 */
	public ModelRenderPair getModelRenderPair(Class<? extends Entity> clazz) 
	{
		if (!entityModelRenderMap.containsKey(clazz))
		{
			RenderLiving render = (RenderLiving) Minecraft.getMinecraft().getRenderManager().entityRenderMap.get(clazz);
			Class modelClass = render.getMainModel().getClass();
			ModelBase model = null;
			
			try
			{
				try
				{
					model = (ModelBase) modelClass.newInstance();
				}
				
				catch (InstantiationException e)
				{
					if (modelClass == ModelVillager.class)
					{
						model = new ModelVillager(0.0F);
					}
					
					else if (modelClass == ModelEnderman.class)
					{
						model = new ModelEnderman(0.0F);
					}
					
					else if (modelClass == ModelWitch.class)
					{
						model = new ModelWitch(0.0F);
					}
					
					else
					{
						SpiderCore.getLogger().error("Couldn't create new model for class " + modelClass + ". This is a bug, report it!");
						model = new ModelBiped(0.0F);
					}
				}
			}
			
			catch (Exception e)
			{
				e.printStackTrace();
			}
			
			entityModelRenderMap.put(clazz, new ModelRenderPair(model, render));
		}
		
		return entityModelRenderMap.get(clazz);
	}
}
