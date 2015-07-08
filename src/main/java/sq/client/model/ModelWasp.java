package sq.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

/**
 * Defines the wasp's model in-game.
 */
public class ModelWasp extends ModelBase
{
	private final ModelRenderer head;
	private final ModelRenderer body;
	private final ModelRenderer leg1;
	private final ModelRenderer leg2;
	private final ModelRenderer leg3;
	private final ModelRenderer leg4;
	private final ModelRenderer leg5;
	private final ModelRenderer leg6;
	private final ModelRenderer jaws;
	private final ModelRenderer antenna1;
	private final ModelRenderer antenna2;
	private final ModelRenderer rearEnd3;
	private final ModelRenderer stinger;
	private final ModelRenderer rearEnd2;
	private final ModelRenderer rearEnd1;

	public ModelWasp()
	{
		textureWidth = 64;
		textureHeight = 32;

		head = new ModelRenderer(this, 32, 4);
		head.addBox(-4F, -4F, -8F, 8, 8, 8);
		head.setRotationPoint(0F, 9F, -3F);
		head.setTextureSize(64, 32);
		head.mirror = true;
		setRotation(head, 0F, 0F, 0F);
		body = new ModelRenderer(this, 0, 0);
		body.addBox(-2F, -3F, -3F, 4, 4, 9);
		body.setRotationPoint(0F, 10F, 0F);
		body.setTextureSize(64, 32);
		body.mirror = true;
		setRotation(body, 0F, 0F, 0F);
		leg1 = new ModelRenderer(this, 22, 13);
		leg1.addBox(-1F, -1F, 0F, 1, 10, 1);
		leg1.setRotationPoint(-2F, 11F, 2F);
		leg1.setTextureSize(64, 32);
		leg1.mirror = true;
		setRotation(leg1, 0F, 0F, 0F);
		leg2 = new ModelRenderer(this, 22, 13);
		leg2.addBox(-1F, -1F, 0F, 1, 10, 1);
		leg2.setRotationPoint(3F, 11F, 2F);
		leg2.setTextureSize(64, 32);
		leg2.mirror = true;
		setRotation(leg2, 0F, 0F, 0F);
		leg3 = new ModelRenderer(this, 22, 13);
		leg3.addBox(-1F, -1F, 0F, 1, 10, 1);
		leg3.setRotationPoint(-2F, 11F, 0F);
		leg3.setTextureSize(64, 32);
		leg3.mirror = true;
		setRotation(leg3, 0F, 0F, 0F);
		leg4 = new ModelRenderer(this, 22, 13);
		leg4.addBox(-1F, -1F, 0F, 1, 10, 1);
		leg4.setRotationPoint(3F, 11F, 0F);
		leg4.setTextureSize(64, 32);
		leg4.mirror = true;
		setRotation(leg4, 0F, 0F, 0F);
		leg5 = new ModelRenderer(this, 22, 13);
		leg5.addBox(-1F, -1F, 0F, 1, 10, 1);
		leg5.setRotationPoint(-2F, 11F, -2F);
		leg5.setTextureSize(64, 32);
		leg5.mirror = true;
		setRotation(leg5, 0F, 0F, 0F);
		leg6 = new ModelRenderer(this, 22, 13);
		leg6.addBox(-1F, -1F, 0F, 1, 10, 1);
		leg6.setRotationPoint(3F, 11F, -2F);
		leg6.setTextureSize(64, 32);
		leg6.mirror = true;
		setRotation(leg6, 0F, 0F, 0F);
		jaws = new ModelRenderer(this, 13, 4);
		jaws.addBox(-4F, 2F, -12F, 8, 1, 4);
		jaws.setRotationPoint(0F, 9F, -3F);
		jaws.setTextureSize(64, 32);
		jaws.mirror = true;
		setRotation(jaws, 0F, 0F, 0F);
		antenna1 = new ModelRenderer(this, 0, -3);
		antenna1.addBox(-3F, -9F, -8F, 1, 5, 4);
		antenna1.setRotationPoint(0F, 9F, -3F);
		antenna1.setTextureSize(64, 32);
		antenna1.mirror = true;
		setRotation(antenna1, 0F, 0F, 0F);
		antenna2 = new ModelRenderer(this, 0, -3);
		antenna2.addBox(3F, -9F, -8F, 1, 5, 4);
		antenna2.setRotationPoint(0F, 9F, -3F);
		antenna2.setTextureSize(64, 32);
		antenna2.mirror = true;
		setRotation(antenna2, 0F, 0F, 0F);
		rearEnd3 = new ModelRenderer(this, 3, 24);
		rearEnd3.addBox(-3F, -5F, 13F, 6, 6, 2);
		rearEnd3.setRotationPoint(0F, 9F, 6F);
		rearEnd3.setTextureSize(64, 32);
		rearEnd3.mirror = true;
		setRotation(rearEnd3, -0.9662093F, 0F, 0F);
		stinger = new ModelRenderer(this, 39, -7);
		stinger.addBox(0F, -9F, -18F, 1, 3, 7);
		stinger.setRotationPoint(0F, 9F, 6F);
		stinger.setTextureSize(64, 32);
		stinger.mirror = true;
		setRotation(stinger, 1.348549F, 3.141593F, 0F);
		rearEnd2 = new ModelRenderer(this, 0, 12);
		rearEnd2.addBox(-5F, -4F, 2F, 10, 8, 12);
		rearEnd2.setRotationPoint(0F, 9F, 6F);
		rearEnd2.setTextureSize(64, 32);
		rearEnd2.mirror = true;
		setRotation(rearEnd2, -0.7853982F, 0F, 0F);
		rearEnd1 = new ModelRenderer(this, 3, 24);
		rearEnd1.addBox(-3F, -3F, 0F, 6, 6, 2);
		rearEnd1.setRotationPoint(0F, 9F, 6F);
		rearEnd1.setTextureSize(64, 32);
		rearEnd1.mirror = true;
		setRotation(rearEnd1, -0.7853982F, 0F, 0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5);
		head.render(f5);
		body.render(f5);
		leg1.render(f5);
		leg2.render(f5);
		leg3.render(f5);
		leg4.render(f5);
		leg5.render(f5);
		leg6.render(f5);
		jaws.render(f5);
		antenna1.render(f5);
		antenna2.render(f5);
		rearEnd3.render(f5);
		stinger.render(f5);
		rearEnd2.render(f5);
		rearEnd1.render(f5);
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

		antenna1.rotateAngleY = head.rotateAngleY; antenna1.rotateAngleX = head.rotateAngleX; antenna1.rotateAngleZ = head.rotateAngleZ;
		antenna2.rotateAngleY = head.rotateAngleY; antenna2.rotateAngleX = head.rotateAngleX; antenna2.rotateAngleZ = head.rotateAngleZ;
		jaws.rotateAngleY = head.rotateAngleY; jaws.rotateAngleX = head.rotateAngleX; jaws.rotateAngleZ = head.rotateAngleZ;

		leg1.rotateAngleX = MathHelper.cos(100F * 0.6662F + 3.141593F) * 2.5F * f1;
		leg1.rotateAngleY = 0.0F;

		leg2.rotateAngleX = leg1.rotateAngleX; leg2.rotateAngleY = leg1.rotateAngleY; leg2.rotateAngleZ = leg1.rotateAngleZ; 
		leg3.rotateAngleX = leg1.rotateAngleX; leg3.rotateAngleY = leg1.rotateAngleY; leg3.rotateAngleZ = leg1.rotateAngleZ; 
		leg4.rotateAngleX = leg1.rotateAngleX; leg4.rotateAngleY = leg1.rotateAngleY; leg4.rotateAngleZ = leg1.rotateAngleZ; 
		leg5.rotateAngleX = leg1.rotateAngleX; leg5.rotateAngleY = leg1.rotateAngleY; leg5.rotateAngleZ = leg1.rotateAngleZ; 
		leg6.rotateAngleX = leg1.rotateAngleX; leg6.rotateAngleY = leg1.rotateAngleY; leg6.rotateAngleZ = leg1.rotateAngleZ; 

	}

}
