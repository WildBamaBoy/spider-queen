package sqr.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

import org.lwjgl.opengl.GL11;

public class ModelSpiderQueen extends ModelBase
{
	private final ModelRenderer head;
	private final ModelRenderer body;
	private final ModelRenderer rear;
	private final ModelRenderer torso;
	private final ModelRenderer armLeft;
	private final ModelRenderer armRight;
	private final ModelRenderer breasts;
	private final ModelRenderer spinner1;
	private final ModelRenderer spinner2;
	private final ModelRenderer leg1;
	private final ModelRenderer leg2;
	private final ModelRenderer leg3;
	private final ModelRenderer leg4;
	private final ModelRenderer leg5;
	private final ModelRenderer leg6;
	private final ModelRenderer leg7;
	private final ModelRenderer leg8;

	public ModelSpiderQueen()
	{
		textureWidth = 64;
		textureHeight = 32;

		head = new ModelRenderer(this, 32, 4);
		head.addBox(-4F, -8F, -4F, 8, 8, 8);
		head.setRotationPoint(0F, 3F, -3F);
		head.setTextureSize(64, 32);
		head.mirror = true;
		setRotation(head, 0F, 0F, 0F);
		body = new ModelRenderer(this, 1, 0);
		body.addBox(-3F, -3F, -3F, 6, 6, 5);
		body.setRotationPoint(0F, 15F, 0F);
		body.setTextureSize(64, 32);
		body.mirror = true;
		setRotation(body, 0F, 0F, 0F);
		rear = new ModelRenderer(this, 1, 11);
		rear.addBox(-5F, -4F, -6F, 10, 10, 11);
		rear.setRotationPoint(0F, 11F, 7F);
		rear.setTextureSize(64, 32);
		rear.mirror = true;
		setRotation(rear, 0F, 0F, 0F);
		torso = new ModelRenderer(this, 44, 18);
		torso.addBox(-3F, -10F, -2F, 6, 10, 4);
		torso.setRotationPoint(0F, 13F, -3F);
		torso.setTextureSize(64, 32);
		torso.mirror = true;
		setRotation(torso, 0F, 0F, 0F);
		armLeft = new ModelRenderer(this, 56, 0);
		armLeft.addBox(-1F, -1F, -1F, 2, 10, 2);
		armLeft.setRotationPoint(4F, 4F, -3F);
		armLeft.setTextureSize(64, 32);
		armLeft.mirror = true;
		setRotation(armLeft, 0F, 0F, 0F);
		armRight = new ModelRenderer(this, 56, 0);
		armRight.addBox(-1F, -1F, -1F, 2, 10, 2);
		armRight.setRotationPoint(-4F, 4F, -3F);
		armRight.setTextureSize(64, 32);
		armRight.mirror = true;
		setRotation(armRight, 0F, 0F, 0F);
		breasts = new ModelRenderer(this, 46, 20);
		breasts.addBox(-3F, -7F, -7F, 6, 3, 2);
		breasts.setRotationPoint(0F, 13F, -3F);
		breasts.setTextureSize(64, 32);
		breasts.mirror = true;
		setRotation(breasts, -0.5846853F, 0F, 0F);
		spinner1 = new ModelRenderer(this, 0, 0);
		spinner1.addBox(-1F, -3F, 0F, 2, 3, 1);
		spinner1.setRotationPoint(2F, 15F, 11F);
		spinner1.setTextureSize(64, 32);
		spinner1.mirror = true;
		setRotation(spinner1, -0.3228859F, 0F, 0F);
		spinner2 = new ModelRenderer(this, 0, 0);
		spinner2.addBox(-1F, -3F, 0F, 2, 3, 1);
		spinner2.setRotationPoint(-2F, 15F, 11F);
		spinner2.setTextureSize(64, 32);
		spinner2.mirror = true;
		setRotation(spinner2, -0.3228859F, 0F, 0F);
		leg1 = new ModelRenderer(this, 18, 0);
		leg1.addBox(-15F, -1F, -1F, 16, 2, 2);
		leg1.setRotationPoint(-4F, 15F, 2F);
		leg1.setTextureSize(64, 32);
		leg1.mirror = true;
		setRotation(leg1, 0.5759587F, 0.1919862F, 0F);
		leg2 = new ModelRenderer(this, 18, 0);
		leg2.addBox(-1F, -1F, -1F, 16, 2, 2);
		leg2.setRotationPoint(4F, 15F, 2F);
		leg2.setTextureSize(64, 32);
		leg2.mirror = true;
		setRotation(leg2, -0.5759587F, -0.1919862F, 0F);
		leg3 = new ModelRenderer(this, 18, 0);
		leg3.addBox(-15F, -1F, -1F, 16, 2, 2);
		leg3.setRotationPoint(-4F, 15F, 1F);
		leg3.setTextureSize(64, 32);
		leg3.mirror = true;
		setRotation(leg3, 0.2792527F, 0.1919862F, 0F);
		leg4 = new ModelRenderer(this, 18, 0);
		leg4.addBox(-1F, -1F, -1F, 16, 2, 2);
		leg4.setRotationPoint(4F, 15F, 1F);
		leg4.setTextureSize(64, 32);
		leg4.mirror = true;
		setRotation(leg4, -0.2792527F, -0.1919862F, 0F);
		leg5 = new ModelRenderer(this, 18, 0);
		leg5.addBox(-15F, -1F, -1F, 16, 2, 2);
		leg5.setRotationPoint(-4F, 15F, 0F);
		leg5.setTextureSize(64, 32);
		leg5.mirror = true;
		setRotation(leg5, -0.2792527F, 0.1919862F, 0F);
		leg6 = new ModelRenderer(this, 18, 0);
		leg6.addBox(-1F, -1F, -1F, 16, 2, 2);
		leg6.setRotationPoint(4F, 15F, 0F);
		leg6.setTextureSize(64, 32);
		leg6.mirror = true;
		setRotation(leg6, 0.2792527F, -0.1919862F, 0F);
		leg7 = new ModelRenderer(this, 18, 0);
		leg7.addBox(-15F, -1F, -1F, 16, 2, 2);
		leg7.setRotationPoint(-4F, 15F, -1F);
		leg7.setTextureSize(64, 32);
		leg7.mirror = true;
		setRotation(leg7, -0.5759587F, 0.1919862F, 0F);
		leg8 = new ModelRenderer(this, 18, 0);
		leg8.addBox(-1F, -1F, -1F, 16, 2, 2);
		leg8.setRotationPoint(4F, 15F, -1F);
		leg8.setTextureSize(64, 32);
		leg8.mirror = true;
		setRotation(leg8, -0.5759587F, -0.1919862F, 0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		setRotationAngles(f, f1, f2, f3, f4, f5);

		if (entity instanceof EntityPlayer)
		{
			//TODO Render code for rider spiders.
//			final EntityPlayer player = (EntityPlayer) entity;
//
//			if (player.ridingEntity instanceof EntityHatchedSpider)
//			{
//				final EntityHatchedSpider spider = (EntityHatchedSpider) player.ridingEntity;
//
//				if (spider.isOnLadder())
//				{
//					final Vec3 lookVector = spider.getLookVec();
//
//					if (lookVector.xCoord <= -0.90 || lookVector.zCoord <= -0.90)
//					{
//						GL11.glRotatef(270, 1, 0, 0);
//						GL11.glTranslated(0, 1.8, 0);
//					}
//
//					else if (lookVector.xCoord >= 0.90 || lookVector.zCoord >= 0.90)
//					{
//						GL11.glRotatef(-90, 1, 0, 0);
//						GL11.glTranslated(0, 1.8, 0);
//					}
//				}
//			}
		}

		head.render(f5);
		body.render(f5);
		rear.render(f5);
		leg1.render(f5);
		leg2.render(f5);
		leg3.render(f5);
		leg4.render(f5);
		leg5.render(f5);
		leg6.render(f5);
		leg7.render(f5);
		leg8.render(f5);
		torso.render(f5);
		armLeft.render(f5);
		armRight.render(f5);
		spinner1.render(f5);
		spinner2.render(f5);
		breasts.render(f5);
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
	{
		head.rotateAngleY = f3 / (180F / (float) Math.PI);
		head.rotateAngleX = f4 / (180F / (float) Math.PI);

		final float f6 = 0.7853982F;
		leg1.rotateAngleZ = -f6;
		leg2.rotateAngleZ = f6;
		leg3.rotateAngleZ = -f6 * 0.74F;
		leg4.rotateAngleZ = f6 * 0.74F;
		leg5.rotateAngleZ = -f6 * 0.74F;
		leg6.rotateAngleZ = f6 * 0.74F;
		leg7.rotateAngleZ = -f6;
		leg8.rotateAngleZ = f6;
		final float f7 = -0F;
		final float f8 = 0.3926991F;
		leg1.rotateAngleY = f8 * 2.0F + f7;
		leg2.rotateAngleY = -f8 * 2.0F - f7;
		leg3.rotateAngleY = f8 * 1.0F + f7;
		leg4.rotateAngleY = -f8 * 1.0F - f7;
		leg5.rotateAngleY = -f8 * 1.0F + f7;
		leg6.rotateAngleY = f8 * 1.0F - f7;
		leg7.rotateAngleY = -f8 * 2.0F + f7;
		leg8.rotateAngleY = f8 * 2.0F - f7;
		final float f9 = -(MathHelper.cos(f * 0.6662F * 2.0F + 0.0F) * 0.4F) * f1;
		final float f10 = -(MathHelper.cos(f * 0.6662F * 2.0F + 3.141593F) * 0.4F) * f1;
		final float f11 = -(MathHelper.cos(f * 0.6662F * 2.0F + 1.570796F) * 0.4F) * f1;
		final float f12 = -(MathHelper.cos(f * 0.6662F * 2.0F + 4.712389F) * 0.4F) * f1;
		final float f13 = Math.abs(MathHelper.sin(f * 0.6662F + 0.0F) * 0.4F) * f1;
		final float f14 = Math.abs(MathHelper.sin(f * 0.6662F + 3.141593F) * 0.4F) * f1;
		final float f15 = Math.abs(MathHelper.sin(f * 0.6662F + 1.570796F) * 0.4F) * f1;
		final float f16 = Math.abs(MathHelper.sin(f * 0.6662F + 4.712389F) * 0.4F) * f1;

		armRight.rotateAngleX = MathHelper.cos(f * 0.6662F + 3.141593F) * 2.0F * f1 * 0.5F;
		armLeft.rotateAngleX = MathHelper.cos(f * 0.6662F) * 2.0F * f1 * 0.5F;
		armRight.rotateAngleZ = 0.0F;
		armLeft.rotateAngleZ = 0.0F;
		if (isRiding)
		{
			armRight.rotateAngleX += -0.6283185F;
			armLeft.rotateAngleX += -0.6283185F;
		}

		armRight.rotateAngleY = 0.0F;
		armLeft.rotateAngleY = 0.0F;
		
		if (onGround > -9990F)
		{
			float ff6 = onGround;
			body.rotateAngleY = MathHelper.sin(MathHelper.sqrt_float(ff6) * 3.141593F * 2.0F) * 0.2F;
			armRight.rotationPointZ = MathHelper.sin(body.rotateAngleY) * 4F - 3F;
			armRight.rotationPointX = -MathHelper.cos(body.rotateAngleY) * 4F;
			armLeft.rotationPointZ = -MathHelper.sin(body.rotateAngleY) * 4F - 3F;
			armLeft.rotationPointX = MathHelper.cos(body.rotateAngleY) * 4F;
			armRight.rotateAngleY += body.rotateAngleY;
			armLeft.rotateAngleY += body.rotateAngleY;
			armLeft.rotateAngleX += body.rotateAngleY;
			ff6 = 1.0F - onGround;
			ff6 *= ff6;
			ff6 *= ff6;
			ff6 = 1.0F - ff6;
			final float ff7 = MathHelper.sin(ff6 * 3.141593F);
			final float ff8 = MathHelper.sin(onGround * 3.141593F) * -(head.rotateAngleX - 0.7F) * 0.75F;
			armRight.rotateAngleX -= ff7 * 1.2D + ff8;
			armRight.rotateAngleY += body.rotateAngleY * 2.0F;
			armRight.rotateAngleZ = MathHelper.sin(onGround * 3.141593F) * -0.4F;
		}

		body.rotateAngleX = 0.0F;
		head.rotationPointY = 3F;

		armRight.rotateAngleZ += MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
		armLeft.rotateAngleZ -= MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
		armRight.rotateAngleX += MathHelper.sin(f2 * 0.067F) * 0.05F;
		armLeft.rotateAngleX -= MathHelper.sin(f2 * 0.067F) * 0.05F;

		leg1.rotateAngleY += f9;
		leg2.rotateAngleY += -f9;
		leg3.rotateAngleY += f10;
		leg4.rotateAngleY += -f10;
		leg5.rotateAngleY += f11;
		leg6.rotateAngleY += -f11;
		leg7.rotateAngleY += f12;
		leg8.rotateAngleY += -f12;
		leg1.rotateAngleZ += f13;
		leg2.rotateAngleZ += -f13;
		leg3.rotateAngleZ += f14;
		leg4.rotateAngleZ += -f14;
		leg5.rotateAngleZ += f15;
		leg6.rotateAngleZ += -f15;
		leg7.rotateAngleZ += f16;
		leg8.rotateAngleZ += -f16;
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
	
	public ModelRenderer getHead()
	{
		return head;
	}
	
	public ModelRenderer getRightArm()
	{
		return armRight;
	}
}
