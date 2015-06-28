package sq.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import sq.entity.EntityBee;
import sq.enums.EnumBeeType;

public class ModelBee extends ModelBase
{
	private final ModelRenderer head;
	private final ModelRenderer body;
	private final ModelRenderer rightArm;
	private final ModelRenderer leftArm;
	private final ModelRenderer rightLeg;
	private final ModelRenderer leftLeg;
	private final ModelRenderer thorax;
	private final ModelRenderer antenna1;
	private final ModelRenderer antenna2;
	private final ModelRenderer handle1;
	private final ModelRenderer handle2;
	private final ModelRenderer bucket1;
	private final ModelRenderer bucket2;
	private final ModelRenderer lash1;
	private final ModelRenderer lash2;
	private final ModelRenderer stinger2;
	private final ModelRenderer stinger1;
	private final ModelRenderer crown;
	private final ModelRenderer scepter1;
	private final ModelRenderer scepter2;

	private final ModelRenderer headAttack;
	private final ModelRenderer bodyAttack;
	private final ModelRenderer rightArmAttack;
	private final ModelRenderer leftArmAttack;
	private final ModelRenderer rightLegAttack;
	private final ModelRenderer leftLegAttack;
	private final ModelRenderer thoraxAttack;
	private final ModelRenderer antennaAttack1;
	private final ModelRenderer antennaAttack2;
	private final ModelRenderer lashAttack1;
	private final ModelRenderer lashAttack2;
	private final ModelRenderer stingerAttack1;
	private final ModelRenderer stingerAttack2;

	public ModelBee()
	{
		textureWidth = 64;
		textureHeight = 32;

		head = new ModelRenderer(this, 0, 0);
		head.addBox(-3F, -6F, -4F, 6, 6, 6);
		head.setRotationPoint(0F, 10F, 0F);
		head.setTextureSize(64, 32);
		head.mirror = true;
		setRotation(head, 0F, 0F, 0F);

		body = new ModelRenderer(this, 16, 12);
		body.addBox(-3F, 0F, -2F, 6, 7, 3);
		body.setRotationPoint(0F, 10F, 0F);
		body.setTextureSize(64, 32);
		body.mirror = true;
		setRotation(body, 0.5235988F, 0F, 0F);

		rightArm = new ModelRenderer(this, 8, 12);
		rightArm.addBox(-1F, -1F, -1F, 2, 8, 2);
		rightArm.setRotationPoint(-4F, 11F, 0F);
		rightArm.setTextureSize(64, 32);
		rightArm.mirror = true;
		setRotation(rightArm, 0F, 0F, 0.2617994F);

		leftArm = new ModelRenderer(this, 8, 12);
		leftArm.addBox(-1F, -1F, -1F, 2, 8, 2);
		leftArm.setRotationPoint(4F, 11F, 0F);
		leftArm.setTextureSize(64, 32);
		leftArm.mirror = true;
		setRotation(leftArm, 0F, 0F, -0.2617994F);

		rightLeg = new ModelRenderer(this, 0, 12);
		rightLeg.addBox(-1F, -1F, -1F, 2, 8, 2);
		rightLeg.setRotationPoint(-2F, 17F, 3F);
		rightLeg.setTextureSize(64, 32);
		rightLeg.mirror = true;
		setRotation(rightLeg, 0.5518505F, 0.1356083F, -0.1293302F);

		leftLeg = new ModelRenderer(this, 0, 12);
		leftLeg.addBox(-1F, -1F, -1F, 2, 8, 2);
		leftLeg.setRotationPoint(2F, 17F, 3F);
		leftLeg.setTextureSize(64, 32);
		leftLeg.mirror = true;
		setRotation(leftLeg, 0.461445F, 0F, 0.1745329F);

		thorax = new ModelRenderer(this, 34, 13);
		thorax.addBox(-2F, -2F, 0F, 4, 4, 5);
		thorax.setRotationPoint(0F, 15F, 4F);
		thorax.setTextureSize(64, 32);
		thorax.mirror = true;
		setRotation(thorax, 0F, 0F, 0F);

		antenna1 = new ModelRenderer(this, 0, -3);
		antenna1.addBox(-2F, -10F, -3F, 1, 4, 3);
		antenna1.setRotationPoint(0F, 10F, 0F);
		antenna1.setTextureSize(64, 32);
		antenna1.mirror = true;
		setRotation(antenna1, 0F, 0F, 0F);

		antenna2 = new ModelRenderer(this, 0, -3);
		antenna2.addBox(2F, -10F, -3F, 1, 4, 3);
		antenna2.setRotationPoint(0F, 10F, 0F);
		antenna2.setTextureSize(64, 32);
		antenna2.mirror = true;
		setRotation(antenna2, 0F, 0F, 0F);

		handle1 = new ModelRenderer(this, 18, -4);
		handle1.addBox(-1F, 5F, -2F, 1, 4, 4);
		handle1.setRotationPoint(-4F, 11F, 0F);
		handle1.setTextureSize(64, 32);
		handle1.mirror = true;
		setRotation(handle1, 0F, 0F, 0F);

		handle2 = new ModelRenderer(this, 18, -4);
		handle2.addBox(1F, 5F, -2F, 1, 4, 4);
		handle2.setRotationPoint(4F, 11F, 0F);
		handle2.setTextureSize(64, 32);
		handle2.mirror = true;
		setRotation(handle2, 0F, 0F, 0F);

		bucket1 = new ModelRenderer(this, 0, 22);
		bucket1.addBox(-1F, 9F, -2F, 4, 4, 4);
		bucket1.setRotationPoint(4F, 11F, 0F);
		bucket1.setTextureSize(64, 32);
		bucket1.mirror = true;
		setRotation(bucket1, 0F, 0F, 0F);

		bucket2 = new ModelRenderer(this, 0, 22);
		bucket2.addBox(-3F, 9F, -2F, 4, 4, 4);
		bucket2.setRotationPoint(-4F, 11F, 0F);
		bucket2.setTextureSize(64, 32);
		bucket2.mirror = true;
		setRotation(bucket2, 0F, 0F, 0F);

		lash1 = new ModelRenderer(this, 6, 0);
		lash1.addBox(3F, -4F, -4F, 1, 1, 1);
		lash1.setRotationPoint(0F, 10F, 0F);
		lash1.setTextureSize(64, 32);
		lash1.mirror = true;
		setRotation(lash1, 0F, 0F, 0F);

		lash2 = new ModelRenderer(this, 6, 0);
		lash2.addBox(-4F, -4F, -4F, 1, 1, 1);
		lash2.setRotationPoint(0F, 10F, 0F);
		lash2.setTextureSize(64, 32);
		lash2.mirror = true;
		setRotation(lash2, 0F, 0F, 0F);

		stinger2 = new ModelRenderer(this, 16, 22);
		stinger2.addBox(-1F, -1F, 5F, 2, 2, 1);
		stinger2.setRotationPoint(0F, 15F, 4F);
		stinger2.setTextureSize(64, 32);
		stinger2.mirror = true;
		setRotation(stinger2, 0F, 0F, 0F);

		stinger1 = new ModelRenderer(this, 16, 22);
		stinger1.addBox(-1F, 0F, 6F, 2, 1, 1);
		stinger1.setRotationPoint(0F, 15F, 4F);
		stinger1.setTextureSize(64, 32);
		stinger1.mirror = true;
		setRotation(stinger1, 0F, 0F, 0F);

		crown = new ModelRenderer(this, 18, -6);
		crown.addBox(-3F, -12F, -4F, 6, 6, 6);
		crown.setRotationPoint(0F, 10F, 0F);
		crown.setTextureSize(64, 32);
		crown.mirror = true;
		setRotation(crown, 0F, 0F, 0F);

		scepter1 = new ModelRenderer(this, 42, 0);
		scepter1.addBox(0F, 0F, 6F, 1, 11, 1);
		scepter1.setRotationPoint(-4F, 11F, 0F);
		scepter1.setTextureSize(64, 32);
		scepter1.mirror = true;
		setRotation(scepter1, -1.570796F, 0F, 0.2617994F);

		scepter2 = new ModelRenderer(this, 46, 4);
		scepter2.addBox(-1F, 8F, 5F, 3, 2, 3);
		scepter2.setRotationPoint(-4F, 11F, 0F);
		scepter2.setTextureSize(64, 32);
		scepter2.mirror = true;
		setRotation(scepter2, -1.570796F, 0F, 0.2617994F);

		headAttack = new ModelRenderer(this, 0, 0);
		headAttack.addBox(-3F, -6F, -4F, 6, 6, 6);
		headAttack.setRotationPoint(0F, 10F, 0F);
		headAttack.setTextureSize(64, 32);
		headAttack.mirror = true;
		setRotation(headAttack, 0F, 0F, 0F);

		bodyAttack = new ModelRenderer(this, 16, 12);
		bodyAttack.addBox(-3F, 0F, -2F, 6, 7, 3);
		bodyAttack.setRotationPoint(0F, 10F, 0F);
		bodyAttack.setTextureSize(64, 32);
		bodyAttack.mirror = true;
		setRotation(bodyAttack, -0.4708622F, 0F, 0F);

		rightArmAttack = new ModelRenderer(this, 8, 12);
		rightArmAttack.addBox(-1F, -1F, -1F, 2, 8, 2);
		rightArmAttack.setRotationPoint(-4F, 10F, 0F);
		rightArmAttack.setTextureSize(64, 32);
		rightArmAttack.mirror = true;
		setRotation(rightArmAttack, 0F, 0F, 0.2617994F);

		leftArmAttack = new ModelRenderer(this, 8, 12);
		leftArmAttack.addBox(-1F, -1F, -1F, 2, 8, 2);
		leftArmAttack.setRotationPoint(4F, 10F, 0F);
		leftArmAttack.setTextureSize(64, 32);
		leftArmAttack.mirror = true;
		setRotation(leftArmAttack, 0F, 0F, -0.2617994F);

		rightLegAttack = new ModelRenderer(this, 0, 12);
		rightLegAttack.addBox(-1F, -1F, -1F, 2, 8, 2);
		rightLegAttack.setRotationPoint(-3F, 16F, -4F);
		rightLegAttack.setTextureSize(64, 32);
		rightLegAttack.mirror = true;
		setRotation(rightLegAttack, -2.443461F, 0.6981317F, -0.122173F);

		leftLegAttack = new ModelRenderer(this, 0, 12);
		leftLegAttack.addBox(-1F, -1F, -1F, 2, 8, 2);
		leftLegAttack.setRotationPoint(3F, 16F, -4F);
		leftLegAttack.setTextureSize(64, 32);
		leftLegAttack.mirror = true;
		setRotation(leftLegAttack, -2.443461F, -0.6981317F, -0.122173F);

		thoraxAttack = new ModelRenderer(this, 34, 13);
		thoraxAttack.addBox(-2F, -1F, 0F, 4, 4, 5);
		thoraxAttack.setRotationPoint(0F, 17F, -2F);
		thoraxAttack.setTextureSize(64, 32);
		thoraxAttack.mirror = true;
		setRotation(thoraxAttack, -2.530727F, 0F, 0F);

		antennaAttack1 = new ModelRenderer(this, 0, -3);
		antennaAttack1.addBox(-2F, -10F, -3F, 1, 4, 3);
		antennaAttack1.setRotationPoint(0F, 10F, 0F);
		antennaAttack1.setTextureSize(64, 32);
		antennaAttack1.mirror = true;
		setRotation(antennaAttack1, 0F, 0F, 0F);

		antennaAttack2 = new ModelRenderer(this, 0, -3);
		antennaAttack2.addBox(2F, -10F, -3F, 1, 4, 3);
		antennaAttack2.setRotationPoint(0F, 10F, 0F);
		antennaAttack2.setTextureSize(64, 32);
		antennaAttack2.mirror = true;
		setRotation(antennaAttack2, 0F, 0F, 0F);

		lashAttack1 = new ModelRenderer(this, 26, 0);
		lashAttack1.addBox(3F, -5F, -4F, 1, 3, 1);
		lashAttack1.setRotationPoint(0F, 10F, 0F);
		lashAttack1.setTextureSize(64, 32);
		lashAttack1.mirror = true;
		setRotation(lashAttack1, 0F, 0F, 0F);

		lashAttack2 = new ModelRenderer(this, 26, 0);
		lashAttack2.addBox(-4F, -5F, -4F, 1, 3, 1);
		lashAttack2.setRotationPoint(0F, 10F, 0F);
		lashAttack2.setTextureSize(64, 32);
		lashAttack2.mirror = true;
		setRotation(lashAttack2, 0F, 0F, 0F);

		stingerAttack1 = new ModelRenderer(this, 10, 22);
		stingerAttack1.addBox(-1F, 0F, 5F, 2, 2, 1);
		stingerAttack1.setRotationPoint(0F, 17F, -2F);
		stingerAttack1.setTextureSize(64, 32);
		stingerAttack1.mirror = true;
		setRotation(stingerAttack1, -2.530727F, 0F, 0F);

		stingerAttack2 = new ModelRenderer(this, 10, 22);
		stingerAttack2.addBox(-1F, 1F, 6F, 2, 1, 1);
		stingerAttack2.setRotationPoint(0F, 17F, -2F);
		stingerAttack2.setTextureSize(64, 32);
		stingerAttack2.mirror = true;
		setRotation(stingerAttack2, -2.530727F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5);

		final EntityBee bee = (EntityBee)entity;
		final EnumBeeType type = bee.getBeeType();

		if (type == EnumBeeType.WARRIOR && bee.getAttacking())
		{
			headAttack.render(f5);
			bodyAttack.render(f5);
			rightArmAttack.render(f5);
			leftArmAttack.render(f5);
			rightLegAttack.render(f5);
			leftLegAttack.render(f5);
			thoraxAttack.render(f5);
			antennaAttack1.render(f5);
			antennaAttack2.render(f5);
			lashAttack1.render(f5);
			lashAttack2.render(f5);
			stingerAttack2.render(f5);
			stingerAttack1.render(f5);
		}

		else
		{
			head.render(f5);
			body.render(f5);
			rightArm.render(f5);
			leftArm.render(f5);
			rightLeg.render(f5);
			leftLeg.render(f5);
			thorax.render(f5);
			antenna1.render(f5);
			antenna2.render(f5);
			lash1.render(f5);
			lash2.render(f5);
			stinger2.render(f5);
			stinger1.render(f5);
		}

		if (bee.getBeeType() == EnumBeeType.GATHERER)
		{
			handle1.render(f5);
			handle2.render(f5);
			bucket1.render(f5);
			bucket2.render(f5);
		}

		if (bee.getBeeType() == EnumBeeType.QUEEN)
		{
			crown.render(f5);
			scepter1.render(f5);
			scepter2.render(f5);
		}
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

		antenna1.rotateAngleY = head.rotateAngleY; antenna1.rotateAngleX = head.rotateAngleX;
		antenna2.rotateAngleY = head.rotateAngleY; antenna2.rotateAngleX = head.rotateAngleX;

		lash1.rotateAngleY = head.rotateAngleY; lash1.rotateAngleX = head.rotateAngleX;
		lash2.rotateAngleY = head.rotateAngleY; lash2.rotateAngleX = head.rotateAngleX;

		rightArm.rotateAngleX = MathHelper.cos(100F * 0.6662F + 3.141593F) * 2.5F * f1 * 0.5F;
		leftArm.rotateAngleX = MathHelper.cos(100F * 0.6662F + 3.141593F) * 2.5F * f1 * 0.5F;
		rightArm.rotateAngleZ = 0.0F;
		leftArm.rotateAngleZ = 0.0F;
		rightLeg.rotateAngleX = MathHelper.cos(100F * 0.6662F + 3.141593F) * 2.5F * f1;
		leftLeg.rotateAngleX = MathHelper.cos(100F * 0.6662F + 3.141593F) * 2.5F * f1;
		rightLeg.rotateAngleY = 0.0F;
		leftLeg.rotateAngleY = 0.0F;

		rightArm.rotateAngleY = 0.0F;
		leftArm.rotateAngleY = 0.0F;

		rightArm.rotateAngleZ += MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
		leftArm.rotateAngleZ -= MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
		rightArm.rotateAngleX += MathHelper.sin(f2 * 0.067F) * 0.05F;
		leftArm.rotateAngleX -= MathHelper.sin(f2 * 0.067F) * 0.05F;

		bucket2.rotateAngleX = rightArm.rotateAngleX;bucket2.rotateAngleY = rightArm.rotateAngleY;bucket2.rotateAngleZ = rightArm.rotateAngleZ;
		handle1.rotateAngleX = rightArm.rotateAngleX;handle1.rotateAngleY = rightArm.rotateAngleY;handle1.rotateAngleZ = rightArm.rotateAngleZ;

		bucket1.rotateAngleX = leftArm.rotateAngleX;bucket1.rotateAngleY = leftArm.rotateAngleY;bucket1.rotateAngleZ = leftArm.rotateAngleZ;
		handle2.rotateAngleX = leftArm.rotateAngleX;handle2.rotateAngleY = leftArm.rotateAngleY;handle2.rotateAngleZ = leftArm.rotateAngleZ;

		crown.rotateAngleY = head.rotateAngleY; crown.rotateAngleX = head.rotateAngleX;
		scepter1.rotateAngleX = rightArm.rotateAngleX-(3.14F/3F);scepter1.rotateAngleY = rightArm.rotateAngleY;scepter1.rotateAngleZ = rightArm.rotateAngleZ;
		scepter2.rotateAngleX = rightArm.rotateAngleX-(3.14F/3F);scepter2.rotateAngleY = rightArm.rotateAngleY;scepter2.rotateAngleZ = rightArm.rotateAngleZ;
	}
}
