package sqr.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelSpiderEgg extends ModelBase
{
	private final ModelRenderer	New_Shape0;
	private final ModelRenderer	New_Shape1;
	private final ModelRenderer	New_Shape2;

	public ModelSpiderEgg()
	{
		textureWidth = 64;
		textureHeight = 32;

		New_Shape0 = new ModelRenderer(this, 0, 4);
		New_Shape0.addBox(-3F, -3F, -3F, 6, 6, 6);
		New_Shape0.setRotationPoint(0F, 21F, 0F);
		New_Shape0.setTextureSize(64, 32);
		New_Shape0.mirror = true;
		setRotation(New_Shape0, 0F, 0F, 0F);
		New_Shape1 = new ModelRenderer(this, 0, 0);
		New_Shape1.addBox(-2F, -2F, -1F, 2, 2, 2);
		New_Shape1.setRotationPoint(1F, 22F, 0F);
		New_Shape1.setTextureSize(64, 32);
		New_Shape1.mirror = true;
		setRotation(New_Shape1, 0F, 0F, 0F);
		New_Shape2 = new ModelRenderer(this, 28, 8);
		New_Shape2.addBox(0F, 0F, 0F, 4, 4, 4);
		New_Shape2.setRotationPoint(-2F, 19F, -2F);
		New_Shape2.setTextureSize(64, 32);
		New_Shape2.mirror = true;
		setRotation(New_Shape2, 0F, 0F, 0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		New_Shape0.render(f5);
		New_Shape1.render(f5);
		New_Shape2.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}
