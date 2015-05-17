package sq.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelVines extends ModelBase
{

	public ModelVines()
	{
		int yy = 7;
		Vinehead = new ModelRenderer(this,0, 0);
		Vinehead.addBox(-1, -1, -20, 2, 2, 20, 0F);
		Vinehead.setRotationPoint(0, 24 + yy, 18);

		Vinehead.rotateAngleX = -1.041001F;
		Vinehead.rotateAngleY = 0F;
		Vinehead.rotateAngleZ = 0F;
		Vinehead.mirror = false;

		Vinehead2 = new ModelRenderer(this,0, 0);
		Vinehead2.addBox(-2, -2, -2, 4, 4, 4, 0F);
		Vinehead2.setRotationPoint(0, 24 + yy, 0);

		Vinehead2.rotateAngleX = 0F;
		Vinehead2.rotateAngleY = 0F;
		Vinehead2.rotateAngleZ = 0F;
		Vinehead2.mirror = false;

		Vinehead3 = new ModelRenderer(this,0, 0);
		Vinehead3.addBox(-1, -1, -14, 2, 2, 14, 0F);
		Vinehead3.setRotationPoint(0, 7 + yy, 8);

		Vinehead3.rotateAngleX = 0F;
		Vinehead3.rotateAngleY = 0F;
		Vinehead3.rotateAngleZ = 0F;
		Vinehead3.mirror = false;

		Vinehead4 = new ModelRenderer(this,0, 0);
		Vinehead4.addBox(0, 0, 0, 2, 2, 20, 0F);
		Vinehead4.setRotationPoint(-1, 23 + yy, -17);

		Vinehead4.rotateAngleX = 1.003826F;
		Vinehead4.rotateAngleY = 0F;
		Vinehead4.rotateAngleZ = 0F;
		Vinehead4.mirror = false;
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		setRotationAngles(f, f1, f2, f3, f4, f5);
		Vinehead.render(f5);
		Vinehead3.render(f5);
		Vinehead4.render(f5);
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
	{
	}

	public ModelRenderer Vinehead;
	public ModelRenderer Vinehead2;
	public ModelRenderer Vinehead3;
	public ModelRenderer Vinehead4;
}
