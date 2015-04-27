

package sqr.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;


// Referenced classes of package net.minecraft.src:
//            ModelBase, ModelRenderer

public class ModelGatherer extends ModelBase
{

	public ModelGatherer()
	{
		final float yo = 0F;

		this.head = new ModelRenderer(this,0, 0);
		this.head.addBox(-3, -6, -4, 6, 6, 6, 0F);
		this.head.setRotationPoint(0, 10+yo, 0);

		this.head.rotateAngleX = 0F;
		this.head.rotateAngleY = 0F;
		this.head.rotateAngleZ = 0F;

		this.body = new ModelRenderer(this,16, 12);
		this.body.addBox(-3, 0, -2, 6, 7, 3, 0F);
		this.body.setRotationPoint(0, 10+yo, 0);

		this.body.rotateAngleX = 0.5235988F;
		this.body.rotateAngleY = 0F;
		this.body.rotateAngleZ = 0F;

		this.rightarm = new ModelRenderer(this,8, 12);
		this.rightarm.addBox(-1, -1, -1, 2, 8, 2, 0F);
		this.rightarm.setRotationPoint(-4, 11+yo, 0);

		this.rightarm.rotateAngleX = 0F;
		this.rightarm.rotateAngleY = 0F;
		this.rightarm.rotateAngleZ = 0.2617994F;

		this.leftarm = new ModelRenderer(this,8, 12);
		this.leftarm.addBox(-1, -1, -1, 2, 8, 2, 0F);
		this.leftarm.setRotationPoint(4, 11+yo, 0);

		this.leftarm.rotateAngleX = 0F;
		this.leftarm.rotateAngleY = 0F;
		this.leftarm.rotateAngleZ = -0.2617994F;

		this.rightleg = new ModelRenderer(this,0, 12);
		this.rightleg.addBox(-1, -1, -1, 2, 8, 2, 0F);
		this.rightleg.setRotationPoint(-2, 17+yo, 3);

		this.rightleg.rotateAngleX = 0.5518505F;
		this.rightleg.rotateAngleY = 0.1356083F;
		this.rightleg.rotateAngleZ = -0.1293302F;

		this.leftleg = new ModelRenderer(this,0, 12);
		this.leftleg.addBox(-1, -1, -1, 2, 8, 2, 0F);
		this.leftleg.setRotationPoint(2, 17+yo, 3);

		this.leftleg.rotateAngleX = 0.461445F;
		this.leftleg.rotateAngleY = 0F;
		this.leftleg.rotateAngleZ = 0.1745329F;

		this.thorax = new ModelRenderer(this,34, 13);
		this.thorax.addBox(-2, -2, 0, 4, 4, 5, 0F);
		this.thorax.setRotationPoint(0, 15+yo, 4);

		this.thorax.rotateAngleX = 0F;
		this.thorax.rotateAngleY = 0F;
		this.thorax.rotateAngleZ = 0F;

		this.antenna1 = new ModelRenderer(this,0, -3);
		this.antenna1.addBox(-2, -10, -3, 0, 4, 3, 0F);
		this.antenna1.setRotationPoint(0, 10+yo, 0);

		this.antenna1.rotateAngleX = 0F;
		this.antenna1.rotateAngleY = 0F;
		this.antenna1.rotateAngleZ = 0F;

		this.antenna2 = new ModelRenderer(this,0, -3);
		this.antenna2.addBox(2, -10, -3, 0, 4, 3, 0F);
		this.antenna2.setRotationPoint(0, 10+yo, 0);

		this.antenna2.rotateAngleX = 0F;
		this.antenna2.rotateAngleY = 0F;
		this.antenna2.rotateAngleZ = 0F;

		this.handle1 = new ModelRenderer(this,18, -4);
		this.handle1.addBox(-1, 5, -2, 0, 4, 4, 0F);
		this.handle1.setRotationPoint(-4, 11+yo, 0);

		this.handle1.rotateAngleX = 0F;
		this.handle1.rotateAngleY = 0F;
		this.handle1.rotateAngleZ = 0F;

		this.handle2 = new ModelRenderer(this,18, -4);
		this.handle2.addBox(1, 5, -2, 0, 4, 4, 0F);
		this.handle2.setRotationPoint(4, 11+yo, 0);

		this.handle2.rotateAngleX = 0F;
		this.handle2.rotateAngleY = 0F;
		this.handle2.rotateAngleZ = 0F;

		this.bucket1 = new ModelRenderer(this,0, 22);
		this.bucket1.addBox(-1, 9, -2, 4, 4, 4, 0F);
		this.bucket1.setRotationPoint(4, 11+yo, 0);

		this.bucket1.rotateAngleX = 0F;
		this.bucket1.rotateAngleY = 0F;
		this.bucket1.rotateAngleZ = 0F;

		this.bucket2 = new ModelRenderer(this,0, 22);
		this.bucket2.addBox(-3, 9, -2, 4, 4, 4, 0F);
		this.bucket2.setRotationPoint(-4, 11+yo, 0);

		this.bucket2.rotateAngleX = 0F;
		this.bucket2.rotateAngleY = 0F;
		this.bucket2.rotateAngleZ = 0F;

		this.lash1 = new ModelRenderer(this,6, 0);
		this.lash1.addBox(3, -4, -4, 1, 1, 0, 0F);
		this.lash1.setRotationPoint(0, 10+yo, 0);

		this.lash1.rotateAngleX = 0F;
		this.lash1.rotateAngleY = 0F;
		this.lash1.rotateAngleZ = 0F;

		this.lash2 = new ModelRenderer(this,6, 0);
		this.lash2.addBox(-4, -4, -4, 1, 1, 0, 0F);
		this.lash2.setRotationPoint(0, 10+yo, 0);

		this.lash2.rotateAngleX = 0F;
		this.lash2.rotateAngleY = 0F;
		this.lash2.rotateAngleZ = 0F;

		this.Stinger2 = new ModelRenderer(this,16, 22);
		this.Stinger2.addBox(-1, -1, 5, 2, 2, 1, 0F);
		this.Stinger2.setRotationPoint(0, 15+yo, 4);

		this.Stinger2.rotateAngleX = 0F;
		this.Stinger2.rotateAngleY = 0F;
		this.Stinger2.rotateAngleZ = 0F;

		this.Stinger1 = new ModelRenderer(this,16, 22);
		this.Stinger1.addBox(-1, 0, 6, 2, 1, 1, 0F);
		this.Stinger1.setRotationPoint(0, 15+yo, 4);

		this.Stinger1.rotateAngleX = 0F;
		this.Stinger1.rotateAngleY = 0F;
		this.Stinger1.rotateAngleZ = 0F;
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		this.setRotationAngles(f, f1, f2, f3, f4, f5);
		this.head.render(f5);
		this.body.render(f5);
		this.rightarm.render(f5);
		this.leftarm.render(f5);
		this.rightleg.render(f5);
		this.leftleg.render(f5);
		this.thorax.render(f5);
		this.antenna1.render(f5);
		this.antenna2.render(f5);
		this.handle1.render(f5);
		this.handle2.render(f5);
		this.bucket1.render(f5);
		this.bucket2.render(f5);
		this.lash1.render(f5);
		this.lash2.render(f5);
		this.Stinger1.render(f5);
		this.Stinger2.render(f5);
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
	{

		this.head.rotateAngleY = f3 / 57.29578F;
		this.head.rotateAngleX = f4 / 57.29578F;

		this.antenna1.rotateAngleY = this.head.rotateAngleY; this.antenna1.rotateAngleX = this.head.rotateAngleX;
		this.antenna2.rotateAngleY = this.head.rotateAngleY; this.antenna2.rotateAngleX = this.head.rotateAngleX;

		this.lash1.rotateAngleY = this.head.rotateAngleY; this.lash1.rotateAngleX = this.head.rotateAngleX;
		this.lash2.rotateAngleY = this.head.rotateAngleY; this.lash2.rotateAngleX = this.head.rotateAngleX;

		this.rightarm.rotateAngleX = MathHelper.cos(100F * 0.6662F + 3.141593F) * 2.5F * f1 * 0.5F;
		this.leftarm.rotateAngleX = MathHelper.cos(100F * 0.6662F + 3.141593F) * 2.5F * f1 * 0.5F;
		this.rightarm.rotateAngleZ = 0.0F;
		this.leftarm.rotateAngleZ = 0.0F;
		this.rightleg.rotateAngleX = MathHelper.cos(100F * 0.6662F + 3.141593F) * 2.5F * f1;
		this.leftleg.rotateAngleX = MathHelper.cos(100F * 0.6662F + 3.141593F) * 2.5F * f1;
		this.rightleg.rotateAngleY = 0.0F;
		this.leftleg.rotateAngleY = 0.0F;


		this.rightarm.rotateAngleY = 0.0F;
		this.leftarm.rotateAngleY = 0.0F;


		this.rightarm.rotateAngleZ += MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
		this.leftarm.rotateAngleZ -= MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
		this.rightarm.rotateAngleX += MathHelper.sin(f2 * 0.067F) * 0.05F;
		this.leftarm.rotateAngleX -= MathHelper.sin(f2 * 0.067F) * 0.05F;

		this.bucket2.rotateAngleX = this.rightarm.rotateAngleX;this.bucket2.rotateAngleY = this.rightarm.rotateAngleY;this.bucket2.rotateAngleZ = this.rightarm.rotateAngleZ;
		this.handle1.rotateAngleX = this.rightarm.rotateAngleX;this.handle1.rotateAngleY = this.rightarm.rotateAngleY;this.handle1.rotateAngleZ = this.rightarm.rotateAngleZ;

		this.bucket1.rotateAngleX = this.leftarm.rotateAngleX;this.bucket1.rotateAngleY = this.leftarm.rotateAngleY;this.bucket1.rotateAngleZ = this.leftarm.rotateAngleZ;
		this.handle2.rotateAngleX = this.leftarm.rotateAngleX;this.handle2.rotateAngleY = this.leftarm.rotateAngleY;this.handle2.rotateAngleZ = this.leftarm.rotateAngleZ;
	}

	public ModelRenderer head;
	public ModelRenderer body;
	public ModelRenderer rightarm;
	public ModelRenderer leftarm;
	public ModelRenderer rightleg;
	public ModelRenderer leftleg;
	public ModelRenderer thorax;
	public ModelRenderer antenna1;
	public ModelRenderer antenna2;
	public ModelRenderer handle1;
	public ModelRenderer handle2;
	public ModelRenderer bucket1;
	public ModelRenderer bucket2;
	public ModelRenderer lash1;
	public ModelRenderer lash2;
	public ModelRenderer Stinger1;
	public ModelRenderer Stinger2;
}
