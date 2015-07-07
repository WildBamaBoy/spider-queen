package sq.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Defines the model for the mandragora's attack vines.
 */
public class ModelVines extends ModelBase
{
	public ModelVines()
	{
		int yy = 7;
		vine = new ModelRenderer(this,0, 0);
		vine.addBox(-1, -1, -20, 2, 2, 20, 0F);
		vine.setRotationPoint(0, 24 + yy, 18);

		vine.rotateAngleX = -1.041001F;
		vine.rotateAngleY = 0F;
		vine.rotateAngleZ = 0F;
		vine.mirror = false;

		vine2 = new ModelRenderer(this,0, 0);
		vine2.addBox(-2, -2, -2, 4, 4, 4, 0F);
		vine2.setRotationPoint(0, 24 + yy, 0);

		vine2.rotateAngleX = 0F;
		vine2.rotateAngleY = 0F;
		vine2.rotateAngleZ = 0F;
		vine2.mirror = false;

		vine3 = new ModelRenderer(this,0, 0);
		vine3.addBox(-1, -1, -14, 2, 2, 14, 0F);
		vine3.setRotationPoint(0, 7 + yy, 8);

		vine3.rotateAngleX = 0F;
		vine3.rotateAngleY = 0F;
		vine3.rotateAngleZ = 0F;
		vine3.mirror = false;

		vine4 = new ModelRenderer(this,0, 0);
		vine4.addBox(0, 0, 0, 2, 2, 20, 0F);
		vine4.setRotationPoint(-1, 23 + yy, -17);

		vine4.rotateAngleX = 1.003826F;
		vine4.rotateAngleY = 0F;
		vine4.rotateAngleZ = 0F;
		vine4.mirror = false;
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		setRotationAngles(f, f1, f2, f3, f4, f5);
		vine.render(f5);
		vine3.render(f5);
		vine4.render(f5);
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
	{
	}

	public ModelRenderer vine;
	public ModelRenderer vine2;
	public ModelRenderer vine3;
	public ModelRenderer vine4;
}
