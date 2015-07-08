package sq.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

/**
 * Defines the Ant's in-game model.
 */
public class ModelAnt extends ModelBase
{
	private final ModelRenderer head;
	private final ModelRenderer body;
	private final ModelRenderer rearEnd1;
	private final ModelRenderer leg1;
	private final ModelRenderer leg2;
	private final ModelRenderer leg3;
	private final ModelRenderer leg4;
	private final ModelRenderer leg5;
	private final ModelRenderer leg6;
	private final ModelRenderer rearEnd2;
	private final ModelRenderer rearEnd3;
	private final ModelRenderer jaws;
	private final ModelRenderer antenna1;
	private final ModelRenderer antenna2;

	public ModelAnt()
	{
		textureWidth = 64;
		textureHeight = 32;

		head = new ModelRenderer(this, 32, 4);
		head.addBox(-4F, -4F, -8F, 8, 8, 8);
		head.setRotationPoint(0F, 17F, -3F);
		head.setTextureSize(64, 32);
		head.mirror = true;
		setRotation(head, 0F, 0F, 0F);
		body = new ModelRenderer(this, 0, 0);
		body.addBox(-2F, -3F, -3F, 6, 6, 6);
		body.setRotationPoint(-1F, 18F, 0F);
		body.setTextureSize(64, 32);
		body.mirror = true;
		setRotation(body, 0F, 0F, 0F);
		rearEnd1 = new ModelRenderer(this, 0, 12);
		rearEnd1.addBox(-5F, -4F, -6F, 10, 8, 12);
		rearEnd1.setRotationPoint(0F, 17F, 9F);
		rearEnd1.setTextureSize(64, 32);
		rearEnd1.mirror = true;
		setRotation(rearEnd1, 0F, 0F, 0F);
		leg1 = new ModelRenderer(this, 18, 0);
		leg1.addBox(-9F, -1F, -1F, 10, 1, 1);
		leg1.setRotationPoint(-1F, 19F, 2F);
		leg1.setTextureSize(64, 32);
		leg1.mirror = true;
		setRotation(leg1, 0.5759587F, 0.1919862F, 0F);
		leg2 = new ModelRenderer(this, 18, 0);
		leg2.addBox(-1F, -1F, -1F, 10, 1, 1);
		leg2.setRotationPoint(1F, 19F, 1F);
		leg2.setTextureSize(64, 32);
		leg2.mirror = true;
		setRotation(leg2, -0.5759587F, -0.1919862F, 0F);
		leg3 = new ModelRenderer(this, 18, 0);
		leg3.addBox(-9F, -1F, -1F, 10, 1, 1);
		leg3.setRotationPoint(-1F, 19F, 0F);
		leg3.setTextureSize(64, 32);
		leg3.mirror = true;
		setRotation(leg3, 0.2792527F, 0.1570796F, 0F);
		leg4 = new ModelRenderer(this, 18, 0);
		leg4.addBox(-1F, -1F, -1F, 10, 1, 1);
		leg4.setRotationPoint(1F, 19F, -1F);
		leg4.setTextureSize(64, 32);
		leg4.mirror = true;
		setRotation(leg4, -0.2792527F, -0.1570796F, 0F);
		leg5 = new ModelRenderer(this, 18, 0);
		leg5.addBox(-9F, -1F, -1F, 10, 1, 1);
		leg5.setRotationPoint(-1F, 19F, -2F);
		leg5.setTextureSize(64, 32);
		leg5.mirror = true;
		setRotation(leg5, -0.2792527F, 0.0523599F, 0F);
		leg6 = new ModelRenderer(this, 18, 0);
		leg6.addBox(-1F, -1F, -1F, 10, 1, 1);
		leg6.setRotationPoint(1F, 19F, -2F);
		leg6.setTextureSize(64, 32);
		leg6.mirror = true;
		setRotation(leg6, 0.2792527F, -0.0523599F, 0F);
		rearEnd2 = new ModelRenderer(this, 0, 23);
		rearEnd2.addBox(-3F, -3F, 6F, 6, 6, 2);
		rearEnd2.setRotationPoint(0F, 17F, 9F);
		rearEnd2.setTextureSize(64, 32);
		rearEnd2.mirror = true;
		setRotation(rearEnd2, 0F, 0F, 0F);
		rearEnd3 = new ModelRenderer(this, 2, 12);
		rearEnd3.addBox(-4F, -6F, -5F, 8, 2, 10);
		rearEnd3.setRotationPoint(0F, 17F, 9F);
		rearEnd3.setTextureSize(64, 32);
		rearEnd3.mirror = true;
		setRotation(rearEnd3, 0F, 0F, 0F);
		jaws = new ModelRenderer(this, 40, 27);
		jaws.addBox(-4F, 2F, -12F, 8, 1, 4);
		jaws.setRotationPoint(0F, 17F, -3F);
		jaws.setTextureSize(64, 32);
		jaws.mirror = true;
		setRotation(jaws, 0F, 0F, 0F);
		antenna1 = new ModelRenderer(this, 32, 0);
		antenna1.addBox(-3F, -9F, -8F, 0, 5, 4);
		antenna1.setRotationPoint(0F, 17F, -3F);
		antenna1.setTextureSize(64, 32);
		antenna1.mirror = true;
		setRotation(antenna1, 0F, 0F, 0F);
		antenna2 = new ModelRenderer(this, 32, 0);
		antenna2.addBox(3F, -9F, -8F, 0, 5, 4);
		antenna2.setRotationPoint(0F, 17F, -3F);
		antenna2.setTextureSize(64, 32);
		antenna2.mirror = true;
		setRotation(antenna2, 0F, 0F, 0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5);
		
		head.render(f5);
		body.render(f5);
		rearEnd1.render(f5);
		leg1.render(f5);
		leg2.render(f5);
		leg3.render(f5);
		leg4.render(f5);
		leg5.render(f5);
		leg6.render(f5);
		rearEnd2.render(f5);
		rearEnd3.render(f5);
		jaws.render(f5);
		antenna1.render(f5);
		antenna2.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
	{
		head.rotateAngleY = f3 / 57.29578F;
		head.rotateAngleX = f4 / 57.29578F;

		antenna1.rotateAngleY = head.rotateAngleY; 
		antenna1.rotateAngleX = head.rotateAngleX; 
		antenna1.rotateAngleZ = head.rotateAngleZ;
		antenna2.rotateAngleY = head.rotateAngleY; 
		antenna2.rotateAngleX = head.rotateAngleX; 
		antenna2.rotateAngleZ = head.rotateAngleZ;
		jaws.rotateAngleY = head.rotateAngleY; 
		jaws.rotateAngleX = head.rotateAngleX; 
		jaws.rotateAngleZ = head.rotateAngleZ;

		float f6 = 0.7853982F;
		leg1.rotateAngleZ = -f6;
		leg2.rotateAngleZ = f6;
		leg3.rotateAngleZ = -f6 * 0.74F;
		leg4.rotateAngleZ = f6 * 0.74F;
		leg5.rotateAngleZ = -f6 * 0.74F;
		leg6.rotateAngleZ = f6 * 0.74F;
		float f7 = -0F;
		float f8 = 0.3926991F;
		leg1.rotateAngleY = f8 * 2.0F + f7;
		leg2.rotateAngleY = -f8 * 2.0F - f7;
		leg3.rotateAngleY = f8 * 1.0F + f7;
		leg4.rotateAngleY = -f8 * 1.0F - f7;
		leg5.rotateAngleY = -f8 * 1.0F + f7;
		leg6.rotateAngleY = f8 * 1.0F - f7;
		float f9 = -(MathHelper.cos(f * 0.6662F * 2.0F + 0.0F) * 0.4F) * f1;
		float f10 = -(MathHelper.cos(f * 0.6662F * 2.0F + 3.141593F) * 0.4F) * f1;
		float f11 = -(MathHelper.cos(f * 0.6662F * 2.0F + 1.570796F) * 0.4F) * f1;
		float f13 = Math.abs(MathHelper.sin(f * 0.6662F + 0.0F) * 0.4F) * f1;
		float f14 = Math.abs(MathHelper.sin(f * 0.6662F + 3.141593F) * 0.4F) * f1;
		float f15 = Math.abs(MathHelper.sin(f * 0.6662F + 1.570796F) * 0.4F) * f1;
		leg1.rotateAngleY += f9;
		leg2.rotateAngleY += -f9;
		leg3.rotateAngleY += f10;
		leg4.rotateAngleY += -f10;
		leg5.rotateAngleY += f11;
		leg6.rotateAngleY += -f11;
		leg1.rotateAngleZ += f13;
		leg2.rotateAngleZ += -f13;
		leg3.rotateAngleZ += f14;
		leg4.rotateAngleZ += -f14;
		leg5.rotateAngleZ += f15;
		leg6.rotateAngleZ += -f15;
	}
}
