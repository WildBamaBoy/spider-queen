package sq.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;

public class ModelJack extends ModelBase
{
	private final ModelRenderer head;
	private final ModelRenderer body1;
	private final ModelRenderer neck;
	private final ModelRenderer headTop;
	private final ModelRenderer body3;
	private final ModelRenderer body2;
	private final ModelRenderer arm;
	private final ModelRenderer hand;
	private final ModelRenderer lantern;

	public ModelJack()
	{
		textureWidth = 64;
		textureHeight = 32;

		head = new ModelRenderer(this, 0, 0);
		head.addBox(-4F, -8F, -4F, 8, 8, 8);
		head.setRotationPoint(0F, 0F, -1F);
		head.setTextureSize(64, 32);
		head.mirror = true;
		setRotation(head, 0F, 0F, 0F);
		body1 = new ModelRenderer(this, 0, 16);
		body1.addBox(-3F, 0F, -8F, 6, 8, 8);
		body1.setRotationPoint(0F, 1F, 0F);
		body1.setTextureSize(64, 32);
		body1.mirror = true;
		setRotation(body1, 0.7853982F, 0F, 0F);
		neck = new ModelRenderer(this, 0, 4);
		neck.addBox(-1F, 0F, -1F, 2, 2, 2);
		neck.setRotationPoint(0F, 0F, -1F);
		neck.setTextureSize(64, 32);
		neck.mirror = true;
		setRotation(neck, 0F, 0F, 0F);
		headTop = new ModelRenderer(this, 0, 0);
		headTop.addBox(-1F, -10F, -1F, 2, 2, 2);
		headTop.setRotationPoint(0F, 0F, -1F);
		headTop.setTextureSize(64, 32);
		headTop.mirror = true;
		setRotation(headTop, 0F, 0F, 0F);
		body3 = new ModelRenderer(this, 2, 18);
		body3.addBox(-1F, 1F, 7F, 6, 6, 6);
		body3.setRotationPoint(0F, 1F, 0F);
		body3.setTextureSize(64, 32);
		body3.mirror = true;
		setRotation(body3, 0.7853982F, 0F, 0F);
		body2 = new ModelRenderer(this, 2, 18);
		body2.addBox(-5F, 1F, -7F, 6, 6, 6);
		body2.setRotationPoint(0F, 1F, 0F);
		body2.setTextureSize(64, 32);
		body2.mirror = true;
		setRotation(body2, 0.7853982F, 0F, 0F);
		arm = new ModelRenderer(this, 28, 22);
		arm.addBox(-1F, -1F, -2F, 1, 8, 2);
		arm.setRotationPoint(4F, 5F, -1F);
		arm.setTextureSize(64, 32);
		arm.mirror = true;
		setRotation(arm, -1.745329F, 0F, 0F);
		hand = new ModelRenderer(this, 32, 10);
		hand.addBox(-2F, 6F, -3F, 3, 3, 3);
		hand.setRotationPoint(4F, 5F, -1F);
		hand.setTextureSize(64, 32);
		hand.mirror = true;
		setRotation(hand, -1.570796F, 0F, 0F);
		lantern = new ModelRenderer(this, 34, 24);
		lantern.addBox(-2F, 6F, 0F, 3, 3, 5);
		lantern.setRotationPoint(4F, 5F, -1F);
		lantern.setTextureSize(64, 32);
		lantern.mirror = true;
		setRotation(lantern, -1.570796F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		
		GL11.glTranslated(0.0D, 1.0D, 0.0D);
		setRotationAngles(f, f1, f2, f3, f4, f5);
		head.render(f5);
		body1.render(f5);
		neck.render(f5);
		headTop.render(f5);
		body3.render(f5);
		body2.render(f5);
		arm.render(f5);
		hand.render(f5);
		lantern.render(f5);
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
		
        body1.rotateAngleX = MathHelper.cos(100F * 0.6662F + 3.141593F) * 2.5F * f1 * 0.5F;
        body2.rotateAngleX = MathHelper.cos(100F * 0.6662F + 3.141593F) * 2.5F * f1 * 0.5F;
        body3.rotateAngleX = MathHelper.cos(100F * 0.6662F + 3.141593F) * 2.5F * f1 * 0.5F;
	}

}
