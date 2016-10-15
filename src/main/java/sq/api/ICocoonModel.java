package sq.api;

import net.minecraft.client.model.ModelRenderer;

/**
 * Indicates that the model implementing this interface is properly set up to have its head rendered on
 * a Spider Queen cocoon.
 * 
 * The class implementing this interface MUST be a model.
 * 
 * If your model extends from ModelQuadruped or ModelBiped, you MIGHT NOT need to implement this interface.
 */
public interface ICocoonModel 
{
	/*
	 * Returns an array containing the models that are considered the head of a particular creature.
	 * Only the returned models will be rendered on the cocoon.
	 */
	ModelRenderer[] getHeadModelComponents();
	
	/*
	 * Use this to apply corrections to translation, scale, etc. before the model will be rendered on-screen.
	 */
	void preRender();
}
