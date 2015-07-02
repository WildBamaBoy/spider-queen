package sq.client.model;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;

import org.lwjgl.opengl.GL11;

import sq.entity.EntitySpiderEx;
import sq.enums.EnumSpiderType;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelSpiderEx extends ModelBase
{
	public ModelRenderer	villagerSpiderChestRight;
	public ModelRenderer	villagerSpiderChestLeft;

	public ModelRenderer	defaultSpiderHead;
	public ModelRenderer	defaultSpiderNeck;
	public ModelRenderer	defaultSpiderBody;
	public ModelRenderer	defaultSpiderLeg1;
	public ModelRenderer	defaultSpiderLeg2;
	public ModelRenderer	defaultSpiderLeg3;
	public ModelRenderer	defaultSpiderLeg4;
	public ModelRenderer	defaultSpiderLeg5;
	public ModelRenderer	defaultSpiderLeg6;
	public ModelRenderer	defaultSpiderLeg7;
	public ModelRenderer	defaultSpiderLeg8;

	public ModelRenderer	boomSpiderCharge;
	public ModelRenderer	boomSpiderHead;
	public ModelRenderer	boomSpiderBody;
	public ModelRenderer	boomSpiderRearEnd;
	public ModelRenderer	boomSpiderLeg1;
	public ModelRenderer	boomSpiderLeg2;
	public ModelRenderer	boomSpiderLeg3;
	public ModelRenderer	boomSpiderLeg4;
	public ModelRenderer	boomSpiderLeg5;
	public ModelRenderer	boomSpiderLeg6;
	public ModelRenderer	boomSpiderLeg7;
	public ModelRenderer	boomSpiderLeg8;

	public ModelRenderer	novaSpiderHead;
	public ModelRenderer	novaSpiderBody;
	public ModelRenderer	novaSpiderRearEnd;
	public ModelRenderer	novaSpiderLeg1;
	public ModelRenderer	novaSpiderLeg2;
	public ModelRenderer	novaSpiderLeg3;
	public ModelRenderer	novaSpiderLeg4;
	public ModelRenderer	novaSpiderLeg5;
	public ModelRenderer	novaSpiderLeg6;
	public ModelRenderer	novaSpiderLeg7;
	public ModelRenderer	novaSpiderLeg8;

	public ModelRenderer	slingerSpiderHead;
	public ModelRenderer	slingerSpiderBody;
	public ModelRenderer	slingerSpiderRearEnd;
	public ModelRenderer	slingerSpiderLeg1;
	public ModelRenderer	slingerSpiderLeg2;
	public ModelRenderer	slingerSpiderLeg3;
	public ModelRenderer	slingerSpiderLeg4;
	public ModelRenderer	slingerSpiderLeg5;
	public ModelRenderer	slingerSpiderLeg6;
	public ModelRenderer	slingerSpiderLeg7;
	public ModelRenderer	slingerSpiderLeg8;

	public ModelRenderer	tankSpiderHead;
	public ModelRenderer	tankSpiderBody;
	public ModelRenderer	tankSpiderRearEnd;
	public ModelRenderer	tankSpiderLeg1;
	public ModelRenderer	tankSpiderLeg2;
	public ModelRenderer	tankSpiderLeg3;
	public ModelRenderer	tankSpiderLeg4;
	public ModelRenderer	tankSpiderLeg5;
	public ModelRenderer	tankSpiderLeg6;
	public ModelRenderer	tankSpiderLeg7;
	public ModelRenderer	tankSpiderLeg8;
	public ModelRenderer	tankSpiderTopBulk;
	public ModelRenderer	tankSpiderBackBulk;
	public ModelRenderer	tankSpiderRightBulk;
	public ModelRenderer	tankSpiderLeftBulk;

	public ModelRenderer	enderSpiderBody;
	public ModelRenderer	enderSpiderLeg8;
	public ModelRenderer	enderSpiderLeg6;
	public ModelRenderer	enderSpiderLeg4;
	public ModelRenderer	enderSpiderLeg2;
	public ModelRenderer	enderSpiderLeg7;
	public ModelRenderer	enderSpiderLeg5;
	public ModelRenderer	enderSpiderLeg3;
	public ModelRenderer	enderSpiderLeg1;

	public ModelSpiderEx()
	{
		initDefaultSpider();
		initBoomSpider();
		initNovaSpider();
		initSlingerSpider();
		initTankSpider();
		initEnderSpider();
		initVillagerSpiderChest();
	}

	@Override
	public void render(Entity entity, float limbSwing, float prevLimbSwing, float rotateFloat, float rotationYaw, float rotationPitch, float partialTickTime)
	{
		final EntitySpiderEx spider = (EntitySpiderEx) entity;
		setRotationAngles(limbSwing, prevLimbSwing, rotateFloat, rotationYaw, rotationPitch, partialTickTime, entity);

		switch (spider.getSpiderType())
		{
		case TANK:
			renderTankSpider(partialTickTime);
			break;

		case WIMPY:
		case NORMAL:
			renderNormalSpider(partialTickTime);
			break;

		case PACK:
			renderNormalSpider(partialTickTime);
			renderVillagerSpiderChest(partialTickTime);
			break;

		case BOOM:
			renderBoomSpider(partialTickTime);
			break;

		case SLINGER:
			renderSlingerSpider(partialTickTime);
			break;

		case NOVA:
			renderNovaSpider(partialTickTime);
			break;

		case ENDER:
			renderEnderSpider(partialTickTime);
			break;

		case RIDER:
			renderRiderSpiders(entity, partialTickTime);
			break;

		default:
			break;
		}
	}

	@Override
	public void setRotationAngles(float limbSwing, float prevLimbSwing, float rotateFloat, float rotationYaw, float rotationPitch, float partialTickTime, Entity entity)
	{
		final EntitySpiderEx spider = (EntitySpiderEx) entity;

		if (spider.getSpiderType() == EnumSpiderType.BOOM)
		{
			boomSpiderHead.rotateAngleY = rotationYaw / 57.29578F;
			boomSpiderHead.rotateAngleX = rotationPitch / 57.29578F;
			final float f6 = 0.7853982F;
			boomSpiderLeg1.rotateAngleZ = -f6;
			boomSpiderLeg2.rotateAngleZ = f6;
			boomSpiderLeg3.rotateAngleZ = -f6 * 0.74F;
			boomSpiderLeg4.rotateAngleZ = f6 * 0.74F;
			boomSpiderLeg5.rotateAngleZ = -f6 * 0.74F;
			boomSpiderLeg6.rotateAngleZ = f6 * 0.74F;
			boomSpiderLeg7.rotateAngleZ = -f6;
			boomSpiderLeg8.rotateAngleZ = f6;
			final float f7 = -0F;
			final float f8 = 0.3926991F;
			boomSpiderLeg1.rotateAngleY = f8 * 2.0F + f7;
			boomSpiderLeg2.rotateAngleY = -f8 * 2.0F - f7;
			boomSpiderLeg3.rotateAngleY = f8 * 1.0F + f7;
			boomSpiderLeg4.rotateAngleY = -f8 * 1.0F - f7;
			boomSpiderLeg5.rotateAngleY = -f8 * 1.0F + f7;
			boomSpiderLeg6.rotateAngleY = f8 * 1.0F - f7;
			boomSpiderLeg7.rotateAngleY = -f8 * 2.0F + f7;
			boomSpiderLeg8.rotateAngleY = f8 * 2.0F - f7;
			final float f9 = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + 0.0F) * 0.4F) * prevLimbSwing;
			final float f10 = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + 3.141593F) * 0.4F) * prevLimbSwing;
			final float f11 = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + 1.570796F) * 0.4F) * prevLimbSwing;
			final float f12 = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + 4.712389F) * 0.4F) * prevLimbSwing;
			final float f13 = Math.abs(MathHelper.sin(limbSwing * 0.6662F + 0.0F) * 0.4F) * prevLimbSwing;
			final float f14 = Math.abs(MathHelper.sin(limbSwing * 0.6662F + 3.141593F) * 0.4F) * prevLimbSwing;
			final float f15 = Math.abs(MathHelper.sin(limbSwing * 0.6662F + 1.570796F) * 0.4F) * prevLimbSwing;
			final float f16 = Math.abs(MathHelper.sin(limbSwing * 0.6662F + 4.712389F) * 0.4F) * prevLimbSwing;
			boomSpiderLeg1.rotateAngleY += f9;
			boomSpiderLeg2.rotateAngleY += -f9;
			boomSpiderLeg3.rotateAngleY += f10;
			boomSpiderLeg4.rotateAngleY += -f10;
			boomSpiderLeg5.rotateAngleY += f11;
			boomSpiderLeg6.rotateAngleY += -f11;
			boomSpiderLeg7.rotateAngleY += f12;
			boomSpiderLeg8.rotateAngleY += -f12;
			boomSpiderLeg1.rotateAngleZ += f13;
			boomSpiderLeg2.rotateAngleZ += -f13;
			boomSpiderLeg3.rotateAngleZ += f14;
			boomSpiderLeg4.rotateAngleZ += -f14;
			boomSpiderLeg5.rotateAngleZ += f15;
			boomSpiderLeg6.rotateAngleZ += -f15;
			boomSpiderLeg7.rotateAngleZ += f16;
			boomSpiderLeg8.rotateAngleZ += -f16;
		}

		else if (spider.getSpiderType() == EnumSpiderType.NOVA)
		{
			novaSpiderHead.rotateAngleY = rotationYaw / 57.29578F;
			novaSpiderHead.rotateAngleX = rotationPitch / 57.29578F;
			final float f6 = 0.7853982F;
			novaSpiderLeg1.rotateAngleZ = -f6;
			novaSpiderLeg2.rotateAngleZ = f6;
			novaSpiderLeg3.rotateAngleZ = -f6 * 0.74F;
			novaSpiderLeg4.rotateAngleZ = f6 * 0.74F;
			novaSpiderLeg5.rotateAngleZ = -f6 * 0.74F;
			novaSpiderLeg6.rotateAngleZ = f6 * 0.74F;
			novaSpiderLeg7.rotateAngleZ = -f6;
			novaSpiderLeg8.rotateAngleZ = f6;
			final float f7 = -0F;
			final float f8 = 0.3926991F;
			novaSpiderLeg1.rotateAngleY = f8 * 2.0F + f7;
			novaSpiderLeg2.rotateAngleY = -f8 * 2.0F - f7;
			novaSpiderLeg3.rotateAngleY = f8 * 1.0F + f7;
			novaSpiderLeg4.rotateAngleY = -f8 * 1.0F - f7;
			novaSpiderLeg5.rotateAngleY = -f8 * 1.0F + f7;
			novaSpiderLeg6.rotateAngleY = f8 * 1.0F - f7;
			novaSpiderLeg7.rotateAngleY = -f8 * 2.0F + f7;
			novaSpiderLeg8.rotateAngleY = f8 * 2.0F - f7;
			final float f9 = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + 0.0F) * 0.4F) * prevLimbSwing;
			final float f10 = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + 3.141593F) * 0.4F) * prevLimbSwing;
			final float f11 = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + 1.570796F) * 0.4F) * prevLimbSwing;
			final float f12 = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + 4.712389F) * 0.4F) * prevLimbSwing;
			final float f13 = Math.abs(MathHelper.sin(limbSwing * 0.6662F + 0.0F) * 0.4F) * prevLimbSwing;
			final float f14 = Math.abs(MathHelper.sin(limbSwing * 0.6662F + 3.141593F) * 0.4F) * prevLimbSwing;
			final float f15 = Math.abs(MathHelper.sin(limbSwing * 0.6662F + 1.570796F) * 0.4F) * prevLimbSwing;
			final float f16 = Math.abs(MathHelper.sin(limbSwing * 0.6662F + 4.712389F) * 0.4F) * prevLimbSwing;
			novaSpiderLeg1.rotateAngleY += f9;
			novaSpiderLeg2.rotateAngleY += -f9;
			novaSpiderLeg3.rotateAngleY += f10;
			novaSpiderLeg4.rotateAngleY += -f10;
			novaSpiderLeg5.rotateAngleY += f11;
			novaSpiderLeg6.rotateAngleY += -f11;
			novaSpiderLeg7.rotateAngleY += f12;
			novaSpiderLeg8.rotateAngleY += -f12;
			novaSpiderLeg1.rotateAngleZ += f13;
			novaSpiderLeg2.rotateAngleZ += -f13;
			novaSpiderLeg3.rotateAngleZ += f14;
			novaSpiderLeg4.rotateAngleZ += -f14;
			novaSpiderLeg5.rotateAngleZ += f15;
			novaSpiderLeg6.rotateAngleZ += -f15;
			novaSpiderLeg7.rotateAngleZ += f16;
			novaSpiderLeg8.rotateAngleZ += -f16;
		}

		else if (spider.getSpiderType() == EnumSpiderType.SLINGER)
		{
			slingerSpiderHead.rotateAngleY = rotationYaw / 57.29578F;
			slingerSpiderHead.rotateAngleX = rotationPitch / 57.29578F;
			final float f6 = 0.7853982F;
			slingerSpiderLeg1.rotateAngleZ = -f6;
			slingerSpiderLeg2.rotateAngleZ = f6;
			slingerSpiderLeg3.rotateAngleZ = -f6 * 0.74F;
			slingerSpiderLeg4.rotateAngleZ = f6 * 0.74F;
			slingerSpiderLeg5.rotateAngleZ = -f6 * 0.74F;
			slingerSpiderLeg6.rotateAngleZ = f6 * 0.74F;
			slingerSpiderLeg7.rotateAngleZ = -f6;
			slingerSpiderLeg8.rotateAngleZ = f6;
			final float f7 = -0F;
			final float f8 = 0.3926991F;
			slingerSpiderLeg1.rotateAngleY = f8 * 2.0F + f7;
			slingerSpiderLeg2.rotateAngleY = -f8 * 2.0F - f7;
			slingerSpiderLeg3.rotateAngleY = f8 * 1.0F + f7;
			slingerSpiderLeg4.rotateAngleY = -f8 * 1.0F - f7;
			slingerSpiderLeg5.rotateAngleY = -f8 * 1.0F + f7;
			slingerSpiderLeg6.rotateAngleY = f8 * 1.0F - f7;
			slingerSpiderLeg7.rotateAngleY = -f8 * 2.0F + f7;
			slingerSpiderLeg8.rotateAngleY = f8 * 2.0F - f7;
			final float f9 = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + 0.0F) * 0.4F) * prevLimbSwing;
			final float f10 = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + 3.141593F) * 0.4F) * prevLimbSwing;
			final float f11 = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + 1.570796F) * 0.4F) * prevLimbSwing;
			final float f12 = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + 4.712389F) * 0.4F) * prevLimbSwing;
			final float f13 = Math.abs(MathHelper.sin(limbSwing * 0.6662F + 0.0F) * 0.4F) * prevLimbSwing;
			final float f14 = Math.abs(MathHelper.sin(limbSwing * 0.6662F + 3.141593F) * 0.4F) * prevLimbSwing;
			final float f15 = Math.abs(MathHelper.sin(limbSwing * 0.6662F + 1.570796F) * 0.4F) * prevLimbSwing;
			final float f16 = Math.abs(MathHelper.sin(limbSwing * 0.6662F + 4.712389F) * 0.4F) * prevLimbSwing;
			slingerSpiderLeg1.rotateAngleY += f9;
			slingerSpiderLeg2.rotateAngleY += -f9;
			slingerSpiderLeg3.rotateAngleY += f10;
			slingerSpiderLeg4.rotateAngleY += -f10;
			slingerSpiderLeg5.rotateAngleY += f11;
			slingerSpiderLeg6.rotateAngleY += -f11;
			slingerSpiderLeg7.rotateAngleY += f12;
			slingerSpiderLeg8.rotateAngleY += -f12;
			slingerSpiderLeg1.rotateAngleZ += f13;
			slingerSpiderLeg2.rotateAngleZ += -f13;
			slingerSpiderLeg3.rotateAngleZ += f14;
			slingerSpiderLeg4.rotateAngleZ += -f14;
			slingerSpiderLeg5.rotateAngleZ += f15;
			slingerSpiderLeg6.rotateAngleZ += -f15;
			slingerSpiderLeg7.rotateAngleZ += f16;
			slingerSpiderLeg8.rotateAngleZ += -f16;
		}

		else if (spider.getSpiderType() == EnumSpiderType.TANK)
		{
			tankSpiderHead.rotateAngleY = rotationYaw / 57.29578F;
			tankSpiderHead.rotateAngleX = rotationPitch / 57.29578F;
			final float f6 = 0.7853982F;
			tankSpiderLeg1.rotateAngleZ = -f6;
			tankSpiderLeg2.rotateAngleZ = f6;
			tankSpiderLeg3.rotateAngleZ = -f6 * 0.74F;
			tankSpiderLeg4.rotateAngleZ = f6 * 0.74F;
			tankSpiderLeg5.rotateAngleZ = -f6 * 0.74F;
			tankSpiderLeg6.rotateAngleZ = f6 * 0.74F;
			tankSpiderLeg7.rotateAngleZ = -f6;
			tankSpiderLeg8.rotateAngleZ = f6;
			final float f7 = -0F;
			final float f8 = 0.3926991F;
			tankSpiderLeg1.rotateAngleY = f8 * 2.0F + f7;
			tankSpiderLeg2.rotateAngleY = -f8 * 2.0F - f7;
			tankSpiderLeg3.rotateAngleY = f8 * 1.0F + f7;
			tankSpiderLeg4.rotateAngleY = -f8 * 1.0F - f7;
			tankSpiderLeg5.rotateAngleY = -f8 * 1.0F + f7;
			tankSpiderLeg6.rotateAngleY = f8 * 1.0F - f7;
			tankSpiderLeg7.rotateAngleY = -f8 * 2.0F + f7;
			tankSpiderLeg8.rotateAngleY = f8 * 2.0F - f7;
			final float f9 = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + 0.0F) * 0.4F) * prevLimbSwing;
			final float f10 = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + 3.141593F) * 0.4F) * prevLimbSwing;
			final float f11 = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + 1.570796F) * 0.4F) * prevLimbSwing;
			final float f12 = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + 4.712389F) * 0.4F) * prevLimbSwing;
			final float f13 = Math.abs(MathHelper.sin(limbSwing * 0.6662F + 0.0F) * 0.4F) * prevLimbSwing;
			final float f14 = Math.abs(MathHelper.sin(limbSwing * 0.6662F + 3.141593F) * 0.4F) * prevLimbSwing;
			final float f15 = Math.abs(MathHelper.sin(limbSwing * 0.6662F + 1.570796F) * 0.4F) * prevLimbSwing;
			final float f16 = Math.abs(MathHelper.sin(limbSwing * 0.6662F + 4.712389F) * 0.4F) * prevLimbSwing;
			tankSpiderLeg1.rotateAngleY += f9;
			tankSpiderLeg2.rotateAngleY += -f9;
			tankSpiderLeg3.rotateAngleY += f10;
			tankSpiderLeg4.rotateAngleY += -f10;
			tankSpiderLeg5.rotateAngleY += f11;
			tankSpiderLeg6.rotateAngleY += -f11;
			tankSpiderLeg7.rotateAngleY += f12;
			tankSpiderLeg8.rotateAngleY += -f12;
			tankSpiderLeg1.rotateAngleZ += f13;
			tankSpiderLeg2.rotateAngleZ += -f13;
			tankSpiderLeg3.rotateAngleZ += f14;
			tankSpiderLeg4.rotateAngleZ += -f14;
			tankSpiderLeg5.rotateAngleZ += f15;
			tankSpiderLeg6.rotateAngleZ += -f15;
			tankSpiderLeg7.rotateAngleZ += f16;
			tankSpiderLeg8.rotateAngleZ += -f16;
		}

		else if (spider.getSpiderType() == EnumSpiderType.ENDER)
		{
			final float quarterCircle = (float) Math.PI / 4F;
			enderSpiderLeg1.rotateAngleZ = -quarterCircle;
			enderSpiderLeg2.rotateAngleZ = quarterCircle;
			enderSpiderLeg3.rotateAngleZ = -quarterCircle * 0.74F;
			enderSpiderLeg4.rotateAngleZ = quarterCircle * 0.74F;
			enderSpiderLeg5.rotateAngleZ = -quarterCircle * 0.74F;
			enderSpiderLeg6.rotateAngleZ = quarterCircle * 0.74F;
			enderSpiderLeg7.rotateAngleZ = -quarterCircle;
			enderSpiderLeg8.rotateAngleZ = quarterCircle;

			final float eighthCircle = (float) Math.PI / 8F;
			enderSpiderLeg1.rotateAngleY = eighthCircle * 2.0F;
			enderSpiderLeg2.rotateAngleY = -eighthCircle * 2.0F;
			enderSpiderLeg3.rotateAngleY = eighthCircle * 1.0F;
			enderSpiderLeg4.rotateAngleY = -eighthCircle * 1.0F;
			enderSpiderLeg5.rotateAngleY = -eighthCircle * 1.0F;
			enderSpiderLeg6.rotateAngleY = eighthCircle * 1.0F;
			enderSpiderLeg7.rotateAngleY = -eighthCircle * 2.0F;
			enderSpiderLeg8.rotateAngleY = eighthCircle * 2.0F;

			final float frontRotateY = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F) * 0.4F) * prevLimbSwing;
			final float frontMidRotateY = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + (float) Math.PI) * 0.4F) * prevLimbSwing;
			final float backMidRotateY = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + (float) Math.PI / 2F) * 0.4F) * prevLimbSwing;
			final float backRotateY = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + (float) Math.PI * 3F / 2F) * 0.4F) * prevLimbSwing;
			final float frontRotateZ = Math.abs(MathHelper.sin(limbSwing * 0.6662F) * 0.4F) * prevLimbSwing;
			final float frontMidRotateZ = Math.abs(MathHelper.sin(limbSwing * 0.6662F + (float) Math.PI) * 0.4F) * prevLimbSwing;
			final float backMidRotateZ = Math.abs(MathHelper.sin(limbSwing * 0.6662F + (float) Math.PI / 2F) * 0.4F) * prevLimbSwing;
			final float backRotateZ = Math.abs(MathHelper.sin(limbSwing * 0.6662F + (float) Math.PI * 3F / 2F) * 0.4F) * prevLimbSwing;

			enderSpiderLeg1.rotateAngleY += frontRotateY;
			enderSpiderLeg2.rotateAngleY += -frontRotateY;
			enderSpiderLeg3.rotateAngleY += frontMidRotateY;
			enderSpiderLeg4.rotateAngleY += -frontMidRotateY;
			enderSpiderLeg5.rotateAngleY += backMidRotateY;
			enderSpiderLeg6.rotateAngleY += -backMidRotateY;
			enderSpiderLeg7.rotateAngleY += backRotateY;
			enderSpiderLeg8.rotateAngleY += -backRotateY;
			enderSpiderLeg1.rotateAngleZ += frontRotateZ;
			enderSpiderLeg2.rotateAngleZ += -frontRotateZ;
			enderSpiderLeg3.rotateAngleZ += frontMidRotateZ;
			enderSpiderLeg4.rotateAngleZ += -frontMidRotateZ;
			enderSpiderLeg5.rotateAngleZ += backMidRotateZ;
			enderSpiderLeg6.rotateAngleZ += -backMidRotateZ;
			enderSpiderLeg7.rotateAngleZ += backRotateZ;
			enderSpiderLeg8.rotateAngleZ += -backRotateZ;
		}

		else
		{
			defaultSpiderHead.rotateAngleY = rotationYaw / (180F / (float) Math.PI);
			defaultSpiderHead.rotateAngleX = rotationPitch / (180F / (float) Math.PI);

			final float quarterCircle = (float) Math.PI / 4F;
			defaultSpiderLeg1.rotateAngleZ = -quarterCircle;
			defaultSpiderLeg2.rotateAngleZ = quarterCircle;
			defaultSpiderLeg3.rotateAngleZ = -quarterCircle * 0.74F;
			defaultSpiderLeg4.rotateAngleZ = quarterCircle * 0.74F;
			defaultSpiderLeg5.rotateAngleZ = -quarterCircle * 0.74F;
			defaultSpiderLeg6.rotateAngleZ = quarterCircle * 0.74F;
			defaultSpiderLeg7.rotateAngleZ = -quarterCircle;
			defaultSpiderLeg8.rotateAngleZ = quarterCircle;

			final float eighthCircle = (float) Math.PI / 8F;
			defaultSpiderLeg1.rotateAngleY = eighthCircle * 2.0F;
			defaultSpiderLeg2.rotateAngleY = -eighthCircle * 2.0F;
			defaultSpiderLeg3.rotateAngleY = eighthCircle * 1.0F;
			defaultSpiderLeg4.rotateAngleY = -eighthCircle * 1.0F;
			defaultSpiderLeg5.rotateAngleY = -eighthCircle * 1.0F;
			defaultSpiderLeg6.rotateAngleY = eighthCircle * 1.0F;
			defaultSpiderLeg7.rotateAngleY = -eighthCircle * 2.0F;
			defaultSpiderLeg8.rotateAngleY = eighthCircle * 2.0F;

			final float frontRotateY = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F) * 0.4F) * prevLimbSwing;
			final float frontMidRotateY = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + (float) Math.PI) * 0.4F) * prevLimbSwing;
			final float backMidRotateY = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + (float) Math.PI / 2F) * 0.4F) * prevLimbSwing;
			final float backRotateY = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + (float) Math.PI * 3F / 2F) * 0.4F) * prevLimbSwing;
			final float frontRotateZ = Math.abs(MathHelper.sin(limbSwing * 0.6662F) * 0.4F) * prevLimbSwing;
			final float frontMidRotateZ = Math.abs(MathHelper.sin(limbSwing * 0.6662F + (float) Math.PI) * 0.4F) * prevLimbSwing;
			final float backMidRotateZ = Math.abs(MathHelper.sin(limbSwing * 0.6662F + (float) Math.PI / 2F) * 0.4F) * prevLimbSwing;
			final float backRotateZ = Math.abs(MathHelper.sin(limbSwing * 0.6662F + (float) Math.PI * 3F / 2F) * 0.4F) * prevLimbSwing;

			defaultSpiderLeg1.rotateAngleY += frontRotateY;
			defaultSpiderLeg2.rotateAngleY += -frontRotateY;
			defaultSpiderLeg3.rotateAngleY += frontMidRotateY;
			defaultSpiderLeg4.rotateAngleY += -frontMidRotateY;
			defaultSpiderLeg5.rotateAngleY += backMidRotateY;
			defaultSpiderLeg6.rotateAngleY += -backMidRotateY;
			defaultSpiderLeg7.rotateAngleY += backRotateY;
			defaultSpiderLeg8.rotateAngleY += -backRotateY;
			defaultSpiderLeg1.rotateAngleZ += frontRotateZ;
			defaultSpiderLeg2.rotateAngleZ += -frontRotateZ;
			defaultSpiderLeg3.rotateAngleZ += frontMidRotateZ;
			defaultSpiderLeg4.rotateAngleZ += -frontMidRotateZ;
			defaultSpiderLeg5.rotateAngleZ += backMidRotateZ;
			defaultSpiderLeg6.rotateAngleZ += -backMidRotateZ;
			defaultSpiderLeg7.rotateAngleZ += backRotateZ;
			defaultSpiderLeg8.rotateAngleZ += -backRotateZ;
		}
	}

	private void initTankSpider()
	{
		tankSpiderHead = new ModelRenderer(this, 32, 4);
		tankSpiderHead.addBox(-4F, -4F, -8F, 8, 8, 8, 0.0F);
		tankSpiderHead.setRotationPoint(0F, 15F, -3F);
		tankSpiderHead.rotateAngleX = 0.18081F;
		tankSpiderHead.rotateAngleZ = -0.27122F;

		tankSpiderBody = new ModelRenderer(this, 0, 0);
		tankSpiderBody.addBox(-3F, -3F, -3F, 6, 6, 6, 0.0F);
		tankSpiderBody.setRotationPoint(0F, 15F, 0F);

		tankSpiderRearEnd = new ModelRenderer(this, 0, 12);
		tankSpiderRearEnd.addBox(-5F, -4F, -6F, 12, 9, 12, 0.0F);
		tankSpiderRearEnd.setRotationPoint(-1F, 14F, 9F);

		tankSpiderLeg1 = new ModelRenderer(this, 18, 0);
		tankSpiderLeg1.addBox(-15F, -1F, -1F, 16, 2, 2, 0.0F);
		tankSpiderLeg1.setRotationPoint(-4F, 15F, 2F);
		tankSpiderLeg1.rotateAngleX = 0.57596F;
		tankSpiderLeg1.rotateAngleY = 0.19199F;

		tankSpiderLeg2 = new ModelRenderer(this, 18, 0);
		tankSpiderLeg2.addBox(-1F, -1F, -1F, 16, 2, 2, 0.0F);
		tankSpiderLeg2.setRotationPoint(4F, 15F, 2F);
		tankSpiderLeg2.rotateAngleX = -0.57596F;
		tankSpiderLeg2.rotateAngleY = -0.19199F;

		tankSpiderLeg3 = new ModelRenderer(this, 18, 0);
		tankSpiderLeg3.addBox(-15F, -1F, -1F, 16, 2, 2, 0.0F);
		tankSpiderLeg3.setRotationPoint(-4F, 15F, 1F);
		tankSpiderLeg3.rotateAngleX = 0.27925F;
		tankSpiderLeg3.rotateAngleY = 0.19199F;

		tankSpiderLeg4 = new ModelRenderer(this, 18, 0);
		tankSpiderLeg4.addBox(-1F, -1F, -1F, 16, 2, 2, 0.0F);
		tankSpiderLeg4.setRotationPoint(4F, 15F, 1F);
		tankSpiderLeg4.rotateAngleX = -0.27925F;
		tankSpiderLeg4.rotateAngleY = -0.19199F;

		tankSpiderLeg5 = new ModelRenderer(this, 18, 0);
		tankSpiderLeg5.addBox(-15F, -1F, -1F, 16, 2, 2, 0.0F);
		tankSpiderLeg5.setRotationPoint(-4F, 15F, 0F);
		tankSpiderLeg5.rotateAngleX = -0.27925F;
		tankSpiderLeg5.rotateAngleY = 0.19199F;

		tankSpiderLeg6 = new ModelRenderer(this, 18, 0);
		tankSpiderLeg6.addBox(-1F, -1F, -1F, 16, 2, 2, 0.0F);
		tankSpiderLeg6.setRotationPoint(4F, 15F, 0F);
		tankSpiderLeg6.rotateAngleX = 0.27925F;
		tankSpiderLeg6.rotateAngleY = -0.19199F;

		tankSpiderLeg7 = new ModelRenderer(this, 18, 0);
		tankSpiderLeg7.addBox(-15F, -1F, -1F, 16, 2, 2, 0.0F);
		tankSpiderLeg7.setRotationPoint(-4F, 15F, -1F);
		tankSpiderLeg7.rotateAngleX = -0.57596F;
		tankSpiderLeg7.rotateAngleY = 0.19199F;

		tankSpiderLeg8 = new ModelRenderer(this, 18, 0);
		tankSpiderLeg8.addBox(-1F, -1F, -1F, 16, 2, 2, 0.0F);
		tankSpiderLeg8.setRotationPoint(4F, 15F, -1F);
		tankSpiderLeg8.rotateAngleX = 0.57596F;
		tankSpiderLeg8.rotateAngleY = -0.19199F;

		tankSpiderTopBulk = new ModelRenderer(this, 0, 12);
		tankSpiderTopBulk.addBox(0F, 0F, 0F, 8, 2, 9, 0.0F);
		tankSpiderTopBulk.setRotationPoint(-4F, 8F, 4F);

		tankSpiderBackBulk = new ModelRenderer(this, 0, 12);
		tankSpiderBackBulk.addBox(-4F, -2F, 0F, 7, 6, 2, 0.0F);
		tankSpiderBackBulk.setRotationPoint(0F, 14F, 15F);

		tankSpiderLeftBulk = new ModelRenderer(this, 0, 12);
		tankSpiderLeftBulk.addBox(0F, -2F, -3F, 2, 4, 9, 0.0F);
		tankSpiderLeftBulk.setRotationPoint(6F, 15F, 7F);

		tankSpiderRightBulk = new ModelRenderer(this, 0, 12);
		tankSpiderRightBulk.addBox(-2F, -2F, -3F, 2, 4, 10, 0.0F);
		tankSpiderRightBulk.setRotationPoint(-6F, 15F, 7F);
	}

	private void initBoomSpider()
	{
		boomSpiderCharge = new ModelRenderer(this, 32, 0);
		boomSpiderCharge.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
		boomSpiderCharge.setRotationPoint(0.0F, 4.0F, 0.0F);

		boomSpiderHead = new ModelRenderer(this, 32, 4);
		boomSpiderHead.addBox(-4F, -4F, -8F, 8, 8, 8, 0.0F);
		boomSpiderHead.setRotationPoint(0F, 15F, -3F);

		boomSpiderBody = new ModelRenderer(this, 0, 0);
		boomSpiderBody.addBox(-3F, -3F, -3F, 6, 6, 6, 0.0F);
		boomSpiderBody.setRotationPoint(0F, 15F, 0F);

		boomSpiderRearEnd = new ModelRenderer(this, 0, 12);
		boomSpiderRearEnd.addBox(-5F, -4F, -6F, 10, 8, 12, 0.0F);
		boomSpiderRearEnd.setRotationPoint(0F, 11F, 7F);
		boomSpiderRearEnd.rotateAngleX = 0.63284F;

		boomSpiderLeg1 = new ModelRenderer(this, 18, 0);
		boomSpiderLeg1.addBox(-15F, -1F, -1F, 16, 2, 2, 0.0F);
		boomSpiderLeg1.setRotationPoint(-4F, 15F, 2F);
		boomSpiderLeg1.rotateAngleX = 0.57596F;
		boomSpiderLeg1.rotateAngleY = 0.19199F;

		boomSpiderLeg2 = new ModelRenderer(this, 18, 0);
		boomSpiderLeg2.addBox(-1F, -1F, -1F, 16, 2, 2, 0.0F);
		boomSpiderLeg2.setRotationPoint(4F, 15F, 2F);
		boomSpiderLeg2.rotateAngleX = -0.57596F;
		boomSpiderLeg2.rotateAngleY = -0.19199F;

		boomSpiderLeg3 = new ModelRenderer(this, 18, 0);
		boomSpiderLeg3.addBox(-15F, -1F, -1F, 16, 2, 2, 0.0F);
		boomSpiderLeg3.setRotationPoint(-4F, 15F, 1F);
		boomSpiderLeg3.rotateAngleX = 0.27925F;
		boomSpiderLeg3.rotateAngleY = 0.19199F;

		boomSpiderLeg4 = new ModelRenderer(this, 18, 0);
		boomSpiderLeg4.addBox(-1F, -1F, -1F, 16, 2, 2, 0.0F);
		boomSpiderLeg4.setRotationPoint(4F, 15F, 1F);
		boomSpiderLeg4.rotateAngleX = -0.27925F;
		boomSpiderLeg4.rotateAngleY = -0.19199F;

		boomSpiderLeg5 = new ModelRenderer(this, 18, 0);
		boomSpiderLeg5.addBox(-15F, -1F, -1F, 16, 2, 2, 0.0F);
		boomSpiderLeg5.setRotationPoint(-4F, 15F, 0F);
		boomSpiderLeg5.rotateAngleX = -0.27925F;
		boomSpiderLeg5.rotateAngleY = 0.19199F;

		boomSpiderLeg6 = new ModelRenderer(this, 18, 0);
		boomSpiderLeg6.addBox(-1F, -1F, -1F, 16, 2, 2, 0.0F);
		boomSpiderLeg6.setRotationPoint(4F, 15F, 0F);
		boomSpiderLeg6.rotateAngleX = 0.27925F;
		boomSpiderLeg6.rotateAngleY = -0.19199F;

		boomSpiderLeg7 = new ModelRenderer(this, 18, 0);
		boomSpiderLeg7.addBox(-15F, -1F, -1F, 16, 2, 2, 0.0F);
		boomSpiderLeg7.setRotationPoint(-4F, 15F, -1F);
		boomSpiderLeg7.rotateAngleX = -0.57596F;
		boomSpiderLeg7.rotateAngleY = 0.19199F;

		boomSpiderLeg8 = new ModelRenderer(this, 18, 0);
		boomSpiderLeg8.addBox(-1F, -1F, -1F, 16, 2, 2, 0.0F);
		boomSpiderLeg8.setRotationPoint(4F, 15F, -1F);
		boomSpiderLeg8.rotateAngleX = 0.57596F;
		boomSpiderLeg8.rotateAngleY = -0.19199F;
	}

	private void initDefaultSpider()
	{
		defaultSpiderHead = new ModelRenderer(this, 32, 4);
		defaultSpiderHead.addBox(-4.0F, -4.0F, -8.0F, 8, 8, 8, 0.0F);
		defaultSpiderHead.setRotationPoint(0.0F, 15, -3.0F);

		defaultSpiderNeck = new ModelRenderer(this, 0, 0);
		defaultSpiderNeck.addBox(-3.0F, -3.0F, -3.0F, 6, 6, 6, 0.0F);
		defaultSpiderNeck.setRotationPoint(0.0F, 15, 0.0F);

		defaultSpiderBody = new ModelRenderer(this, 0, 12);
		defaultSpiderBody.addBox(-5.0F, -4.0F, -6.0F, 10, 8, 12, 0.0F);
		defaultSpiderBody.setRotationPoint(0.0F, 15, 9.0F);

		defaultSpiderLeg1 = new ModelRenderer(this, 18, 0);
		defaultSpiderLeg1.addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
		defaultSpiderLeg1.setRotationPoint(-4.0F, 15, 2.0F);

		defaultSpiderLeg2 = new ModelRenderer(this, 18, 0);
		defaultSpiderLeg2.addBox(-1.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
		defaultSpiderLeg2.setRotationPoint(4.0F, 15, 2.0F);

		defaultSpiderLeg3 = new ModelRenderer(this, 18, 0);
		defaultSpiderLeg3.addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
		defaultSpiderLeg3.setRotationPoint(-4.0F, 15, 1.0F);

		defaultSpiderLeg4 = new ModelRenderer(this, 18, 0);
		defaultSpiderLeg4.addBox(-1.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
		defaultSpiderLeg4.setRotationPoint(4.0F, 15, 1.0F);

		defaultSpiderLeg5 = new ModelRenderer(this, 18, 0);
		defaultSpiderLeg5.addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
		defaultSpiderLeg5.setRotationPoint(-4.0F, 15, 0.0F);

		defaultSpiderLeg6 = new ModelRenderer(this, 18, 0);
		defaultSpiderLeg6.addBox(-1.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
		defaultSpiderLeg6.setRotationPoint(4.0F, 15, 0.0F);

		defaultSpiderLeg7 = new ModelRenderer(this, 18, 0);
		defaultSpiderLeg7.addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
		defaultSpiderLeg7.setRotationPoint(-4.0F, 15, -1.0F);

		defaultSpiderLeg8 = new ModelRenderer(this, 18, 0);
		defaultSpiderLeg8.addBox(-1.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
		defaultSpiderLeg8.setRotationPoint(4.0F, 15, -1.0F);
	}

	private void initSlingerSpider()
	{
		slingerSpiderHead = new ModelRenderer(this, 32, 4);
		slingerSpiderHead.addBox(-4F, -6F, -8F, 8, 8, 8, 0.0F);
		slingerSpiderHead.setRotationPoint(0F, 14F, -3F);

		slingerSpiderBody = new ModelRenderer(this, 0, 0);
		slingerSpiderBody.addBox(-3F, -3F, -3F, 6, 4, 6, 0.0F);
		slingerSpiderBody.setRotationPoint(0F, 16F, 0F);

		slingerSpiderRearEnd = new ModelRenderer(this, 0, 12);
		slingerSpiderRearEnd.addBox(-5F, -4F, -6F, 8, 6, 10, 0.0F);
		slingerSpiderRearEnd.setRotationPoint(1F, 13F, 8F);
		slingerSpiderRearEnd.rotateAngleX = 0.32023F;

		slingerSpiderLeg1 = new ModelRenderer(this, 18, 0);
		slingerSpiderLeg1.addBox(-15F, -1F, -1F, 16, 1, 1, 0.0F);
		slingerSpiderLeg1.setRotationPoint(-4F, 15F, 2F);
		slingerSpiderLeg1.rotateAngleX = 0.57596F;
		slingerSpiderLeg1.rotateAngleY = 0.19199F;

		slingerSpiderLeg2 = new ModelRenderer(this, 18, 0);
		slingerSpiderLeg2.addBox(-1F, -1F, -1F, 16, 1, 1, 0.0F);
		slingerSpiderLeg2.setRotationPoint(4F, 15F, 2F);
		slingerSpiderLeg2.rotateAngleX = -0.57596F;
		slingerSpiderLeg2.rotateAngleY = -0.19199F;

		slingerSpiderLeg3 = new ModelRenderer(this, 18, 0);
		slingerSpiderLeg3.addBox(-15F, -1F, -1F, 16, 1, 1, 0.0F);
		slingerSpiderLeg3.setRotationPoint(-4F, 15F, 1F);
		slingerSpiderLeg3.rotateAngleX = 0.27925F;
		slingerSpiderLeg3.rotateAngleY = 0.19199F;

		slingerSpiderLeg4 = new ModelRenderer(this, 18, 0);
		slingerSpiderLeg4.addBox(-1F, -1F, -1F, 16, 1, 1, 0.0F);
		slingerSpiderLeg4.setRotationPoint(4F, 15F, 1F);
		slingerSpiderLeg4.rotateAngleX = -0.27925F;
		slingerSpiderLeg4.rotateAngleY = -0.19199F;

		slingerSpiderLeg5 = new ModelRenderer(this, 18, 0);
		slingerSpiderLeg5.addBox(-15F, -1F, -1F, 16, 1, 1, 0.0F);
		slingerSpiderLeg5.setRotationPoint(-4F, 15F, 0F);
		slingerSpiderLeg5.rotateAngleX = -0.27925F;
		slingerSpiderLeg5.rotateAngleY = 0.19199F;

		slingerSpiderLeg6 = new ModelRenderer(this, 18, 0);
		slingerSpiderLeg6.addBox(-1F, -1F, -1F, 16, 1, 1, 0.0F);
		slingerSpiderLeg6.setRotationPoint(4F, 15F, 0F);
		slingerSpiderLeg6.rotateAngleX = 0.27925F;
		slingerSpiderLeg6.rotateAngleY = -0.19199F;

		slingerSpiderLeg7 = new ModelRenderer(this, 18, 0);
		slingerSpiderLeg7.addBox(-15F, -1F, -1F, 16, 1, 1, 0.0F);
		slingerSpiderLeg7.setRotationPoint(-4F, 15F, -1F);
		slingerSpiderLeg7.rotateAngleX = -0.57596F;
		slingerSpiderLeg7.rotateAngleY = 0.19199F;

		slingerSpiderLeg8 = new ModelRenderer(this, 18, 0);
		slingerSpiderLeg8.addBox(-1F, -1F, -1F, 16, 1, 1, 0.0F);
		slingerSpiderLeg8.setRotationPoint(4F, 15F, -1F);
		slingerSpiderLeg8.rotateAngleX = 0.57596F;
		slingerSpiderLeg8.rotateAngleY = -0.19199F;
	}

	private void initNovaSpider()
	{
		novaSpiderHead = new ModelRenderer(this, 0, 8);
		novaSpiderHead.addBox(-3F, -2F, -4F, 4, 3, 4, 0.0F);
		novaSpiderHead.setRotationPoint(0F, 19F, -3F);

		novaSpiderBody = new ModelRenderer(this, 0, 0);
		novaSpiderBody.addBox(-3F, -3F, -3F, 4, 4, 4, 0.0F);
		novaSpiderBody.setRotationPoint(0F, 19F, 0F);

		novaSpiderRearEnd = new ModelRenderer(this, 0, 15);
		novaSpiderRearEnd.addBox(-3F, -4F, 0F, 6, 6, 6, 0.0F);
		novaSpiderRearEnd.setRotationPoint(-1F, 18F, 1F);

		novaSpiderLeg1 = new ModelRenderer(this, 18, 0);
		novaSpiderLeg1.addBox(-9F, -1F, -1F, 10, 1, 1, 0.0F);
		novaSpiderLeg1.setRotationPoint(-3F, 19F, 1F);
		novaSpiderLeg1.rotateAngleX = 0.57596F;
		novaSpiderLeg1.rotateAngleY = 0.19199F;

		novaSpiderLeg2 = new ModelRenderer(this, 18, 0);
		novaSpiderLeg2.addBox(-1F, -1F, -1F, 10, 1, 1, 0.0F);
		novaSpiderLeg2.setRotationPoint(1F, 19F, 1F);
		novaSpiderLeg2.rotateAngleX = -0.57596F;
		novaSpiderLeg2.rotateAngleY = -0.19199F;

		novaSpiderLeg3 = new ModelRenderer(this, 18, 0);
		novaSpiderLeg3.addBox(-9F, -1F, -1F, 10, 1, 1, 0.0F);
		novaSpiderLeg3.setRotationPoint(-3F, 19F, 0F);
		novaSpiderLeg3.rotateAngleX = 0.27925F;
		novaSpiderLeg3.rotateAngleY = 0.19199F;

		novaSpiderLeg4 = new ModelRenderer(this, 18, 0);
		novaSpiderLeg4.addBox(-1F, -1F, -1F, 10, 1, 1, 0.0F);
		novaSpiderLeg4.setRotationPoint(1F, 19F, 0F);
		novaSpiderLeg4.rotateAngleX = -0.27925F;
		novaSpiderLeg4.rotateAngleY = -0.19199F;

		novaSpiderLeg5 = new ModelRenderer(this, 18, 0);
		novaSpiderLeg5.addBox(-9F, -1F, -1F, 10, 1, 1, 0.0F);
		novaSpiderLeg5.setRotationPoint(-3F, 19F, -1F);
		novaSpiderLeg5.rotateAngleX = -0.27925F;
		novaSpiderLeg5.rotateAngleY = 0.19199F;

		novaSpiderLeg6 = new ModelRenderer(this, 18, 0);
		novaSpiderLeg6.addBox(-1F, -1F, -1F, 10, 1, 1, 0.0F);
		novaSpiderLeg6.setRotationPoint(1F, 19F, -1F);
		novaSpiderLeg6.rotateAngleX = 0.27925F;
		novaSpiderLeg6.rotateAngleY = -0.19199F;

		novaSpiderLeg7 = new ModelRenderer(this, 18, 0);
		novaSpiderLeg7.addBox(-10F, -1F, -1F, 10, 1, 1, 0.0F);
		novaSpiderLeg7.setRotationPoint(-2F, 19F, -2F);
		novaSpiderLeg7.rotateAngleX = -0.57596F;
		novaSpiderLeg7.rotateAngleY = 0.19199F;

		novaSpiderLeg8 = new ModelRenderer(this, 18, 0);
		novaSpiderLeg8.addBox(-1F, -1F, -1F, 10, 1, 1, 0.0F);
		novaSpiderLeg8.setRotationPoint(1F, 19F, -2F);
		novaSpiderLeg8.rotateAngleX = 0.57596F;
		novaSpiderLeg8.rotateAngleY = -0.19199F;
	}

	private void initEnderSpider()
	{
		enderSpiderBody = new ModelRenderer(this, 0, 0);
		enderSpiderBody.addBox(-3F, -3F, -3F, 6, 6, 6);
		enderSpiderBody.setRotationPoint(0F, 5F, 0F);

		enderSpiderLeg8 = new ModelRenderer(this, 18, 0);
		enderSpiderLeg8.addBox(-1F, -1F, -1F, 17, 1, 1);
		enderSpiderLeg8.setRotationPoint(2.5F, 9F, -1F);
		enderSpiderLeg8.setTextureSize(64, 32);
		enderSpiderLeg8.mirror = true;
		setRotation(enderSpiderLeg8, 0.2974289F, 0.5759587F, 1.230457F);
		enderSpiderLeg6 = new ModelRenderer(this, 18, 0);
		enderSpiderLeg6.addBox(-1F, -1F, -1F, 17, 1, 1);
		enderSpiderLeg6.setRotationPoint(2.5F, 9F, 0F);
		enderSpiderLeg6.setTextureSize(64, 32);
		enderSpiderLeg6.mirror = true;
		setRotation(enderSpiderLeg6, 0.5948578F, 0.2792527F, 1.22173F);
		enderSpiderLeg4 = new ModelRenderer(this, 18, 0);
		enderSpiderLeg4.addBox(-1F, -1F, -1F, 17, 1, 1);
		enderSpiderLeg4.setRotationPoint(2F, 9F, 1F);
		enderSpiderLeg4.setTextureSize(64, 32);
		enderSpiderLeg4.mirror = true;
		setRotation(enderSpiderLeg4, 0F, -0.2792527F, 1.22173F);
		enderSpiderLeg2 = new ModelRenderer(this, 18, 0);
		enderSpiderLeg2.addBox(-1F, -1F, -1F, 17, 1, 1);
		enderSpiderLeg2.setRotationPoint(2F, 9F, 2F);
		enderSpiderLeg2.setTextureSize(64, 32);
		enderSpiderLeg2.mirror = true;
		setRotation(enderSpiderLeg2, 0.2974289F, -0.5759587F, 1.230457F);
		enderSpiderLeg7 = new ModelRenderer(this, 18, 0);
		enderSpiderLeg7.addBox(-15F, -1F, -1F, 17, 1, 1);
		enderSpiderLeg7.setRotationPoint(-2.5F, 10F, -1F);
		enderSpiderLeg7.setTextureSize(64, 32);
		enderSpiderLeg7.mirror = true;
		setRotation(enderSpiderLeg7, 0F, -0.5759587F, -1.230457F);
		enderSpiderLeg5 = new ModelRenderer(this, 18, 0);
		enderSpiderLeg5.addBox(-15F, -1F, -1F, 17, 1, 1);
		enderSpiderLeg5.setRotationPoint(-2.5F, 10F, 0F);
		enderSpiderLeg5.setTextureSize(64, 32);
		enderSpiderLeg5.mirror = true;
		setRotation(enderSpiderLeg5, 0F, -0.2792527F, -1.22173F);
		enderSpiderLeg3 = new ModelRenderer(this, 18, 0);
		enderSpiderLeg3.addBox(-15F, -1F, -1F, 17, 1, 1);
		enderSpiderLeg3.setRotationPoint(-2.5F, 10F, 1F);
		enderSpiderLeg3.setTextureSize(64, 32);
		enderSpiderLeg3.mirror = true;
		setRotation(enderSpiderLeg3, 0F, 0.2792527F, -1.22173F);
		enderSpiderLeg1 = new ModelRenderer(this, 18, 0);
		enderSpiderLeg1.addBox(-15F, -1F, -1F, 18, 1, 1);
		enderSpiderLeg1.setRotationPoint(-2.5F, 10F, 2F);
		enderSpiderLeg1.setTextureSize(64, 32);
		enderSpiderLeg1.mirror = true;
		setRotation(enderSpiderLeg1, 0.3665191F, 0.5759587F, -1.230457F);
	}

	private void initVillagerSpiderChest()
	{
		villagerSpiderChestRight = new ModelRenderer(this, 46, 16);
		villagerSpiderChestRight.addBox(0F, -4F, -4F, 1, 8, 8);
		villagerSpiderChestRight.setRotationPoint(-5.9F, 20F, 11F);
		villagerSpiderChestRight.setTextureSize(64, 32);
		villagerSpiderChestRight.mirror = true;
		setRotation(villagerSpiderChestRight, 0F, 0F, 0F);
		villagerSpiderChestLeft = new ModelRenderer(this, 46, 16);
		villagerSpiderChestLeft.addBox(0F, -4F, -4F, 1, 8, 8);
		villagerSpiderChestLeft.setRotationPoint(5.9F, 20F, 11F);
		villagerSpiderChestLeft.setTextureSize(64, 32);
		villagerSpiderChestLeft.mirror = true;
		setRotation(villagerSpiderChestLeft, 0F, 3.141593F, 0F);
	}

	private void renderTankSpider(float partialTickTime)
	{
		tankSpiderHead.render(partialTickTime);
		tankSpiderRearEnd.render(partialTickTime);
		tankSpiderBody.render(partialTickTime);
		tankSpiderLeftBulk.render(partialTickTime);
		tankSpiderTopBulk.render(partialTickTime);
		tankSpiderBackBulk.render(partialTickTime);
		tankSpiderRightBulk.render(partialTickTime);
		tankSpiderLeg1.render(partialTickTime);
		tankSpiderLeg2.render(partialTickTime);
		tankSpiderLeg3.render(partialTickTime);
		tankSpiderLeg4.render(partialTickTime);
		tankSpiderLeg5.render(partialTickTime);
		tankSpiderLeg6.render(partialTickTime);
		tankSpiderLeg7.render(partialTickTime);
		tankSpiderLeg8.render(partialTickTime);
	}

	private void renderBoomSpider(float partialTickTime)
	{
		boomSpiderHead.render(partialTickTime);
		boomSpiderBody.render(partialTickTime);
		boomSpiderRearEnd.render(partialTickTime);
		boomSpiderLeg1.render(partialTickTime);
		boomSpiderLeg2.render(partialTickTime);
		boomSpiderLeg3.render(partialTickTime);
		boomSpiderLeg4.render(partialTickTime);
		boomSpiderLeg5.render(partialTickTime);
		boomSpiderLeg6.render(partialTickTime);
		boomSpiderLeg7.render(partialTickTime);
		boomSpiderLeg8.render(partialTickTime);
	}

	private void renderNormalSpider(float partialTickTime)
	{
		defaultSpiderHead.render(partialTickTime);
		defaultSpiderNeck.render(partialTickTime);
		defaultSpiderBody.render(partialTickTime);
		defaultSpiderLeg1.render(partialTickTime);
		defaultSpiderLeg2.render(partialTickTime);
		defaultSpiderLeg3.render(partialTickTime);
		defaultSpiderLeg4.render(partialTickTime);
		defaultSpiderLeg5.render(partialTickTime);
		defaultSpiderLeg6.render(partialTickTime);
		defaultSpiderLeg7.render(partialTickTime);
		defaultSpiderLeg8.render(partialTickTime);
	}

	private void renderSlingerSpider(float partialTickTime)
	{
		slingerSpiderHead.render(partialTickTime);
		slingerSpiderBody.render(partialTickTime);
		slingerSpiderRearEnd.render(partialTickTime);
		slingerSpiderLeg1.render(partialTickTime);
		slingerSpiderLeg2.render(partialTickTime);
		slingerSpiderLeg3.render(partialTickTime);
		slingerSpiderLeg4.render(partialTickTime);
		slingerSpiderLeg5.render(partialTickTime);
		slingerSpiderLeg6.render(partialTickTime);
		slingerSpiderLeg7.render(partialTickTime);
		slingerSpiderLeg8.render(partialTickTime);
	}

	private void renderNovaSpider(float partialTickTime)
	{
		novaSpiderHead.render(partialTickTime);
		novaSpiderBody.render(partialTickTime);
		novaSpiderRearEnd.render(partialTickTime);
		novaSpiderLeg1.render(partialTickTime);
		novaSpiderLeg2.render(partialTickTime);
		novaSpiderLeg3.render(partialTickTime);
		novaSpiderLeg4.render(partialTickTime);
		novaSpiderLeg5.render(partialTickTime);
		novaSpiderLeg6.render(partialTickTime);
		novaSpiderLeg7.render(partialTickTime);
		novaSpiderLeg8.render(partialTickTime);
	}

	private void renderEnderSpider(float partialTickTime)
	{
		GL11.glPushMatrix();
		{
			GL11.glTranslated(0, 0.35D, 0);
			enderSpiderBody.render(partialTickTime);
		}
		GL11.glPopMatrix();

		GL11.glPushMatrix();
		{
			GL11.glTranslated(0, -0.05D, 0);
			GL11.glScaled(1, 1.5D, 1);
			enderSpiderLeg1.render(partialTickTime);
			enderSpiderLeg2.render(partialTickTime);
			enderSpiderLeg3.render(partialTickTime);
			enderSpiderLeg4.render(partialTickTime);
			enderSpiderLeg5.render(partialTickTime);
			enderSpiderLeg6.render(partialTickTime);
			enderSpiderLeg7.render(partialTickTime);
			enderSpiderLeg8.render(partialTickTime);
		}
		GL11.glPopMatrix();
	}

	private void renderRiderSpiders(Entity entity, float partialTickTime)
	{
		final EntitySpiderEx spider = (EntitySpiderEx) entity;
		final int level = spider.getLevel();

		if (spider.isOnLadder())
		{
			final Vec3 lookVector = spider.getLookVec();

			if (lookVector.xCoord <= -0.90 || lookVector.zCoord <= -0.90)
			{
				GL11.glRotatef(270, 1, 0, 0);
				GL11.glTranslated(0, -1.2, 0);
			}

			else if (lookVector.xCoord >= 0.90 || lookVector.zCoord >= 0.90)
			{
				GL11.glRotatef(-90, 1, 0, 0);
				GL11.glTranslated(0, -1.2, 0);
			}
		}

		if (level == 1)
		{
			for (int pass = 0; pass < 9; pass++)
			{
				GL11.glPushMatrix();
				{
					GL11.glScaled(0.3D, 0.3D, 0.3D);
					GL11.glTranslated(0, 3.6D, 0D);

					switch (pass)
					{
					case 0:
						GL11.glTranslated(0D, 0.0D, -2.0D);
						break;
					case 1:
						GL11.glTranslated(0D, 0, 2.0D);
						break;
					case 2:
						GL11.glTranslated(1.8D, 0, 0D);
						break;
					case 3:
						GL11.glTranslated(-1.8D, 0, 0D);
						break;
					case 4:
						GL11.glTranslated(1.8D, 0, -2.0D);
						break;
					case 5:
						GL11.glTranslated(-1.8D, 0, -2.0D);
						break;
					case 6:
						GL11.glTranslated(1.8D, 0, 2.0D);
						break;
					case 7:
						GL11.glTranslated(-1.8D, 0, 2.0D);
						break;
					default:
						break;
					}
					renderNormalSpider(partialTickTime);
				}
				GL11.glPopMatrix();
			}
		}

		else if (level == 2)
		{
			for (int pass = 0; pass < 13; pass++)
			{
				GL11.glPushMatrix();
				{
					GL11.glScaled(0.2D, 0.2D, 0.2D);
					GL11.glTranslated(0, 6.1D, 0D);

					switch (pass)
					{
					case 0:
						GL11.glTranslated(0D, 0.0D, -2.0D);
						break;
					case 1:
						GL11.glTranslated(0D, 0, 2.0D);
						break;
					case 2:
						GL11.glTranslated(1.8D, 0, 0D);
						break;
					case 3:
						GL11.glTranslated(-1.8D, 0, 0D);
						break;
					case 4:
						GL11.glTranslated(1.8D, 0, -2.0D);
						break;
					case 5:
						GL11.glTranslated(-1.8D, 0, -2.0D);
						break;
					case 6:
						GL11.glTranslated(1.8D, 0, 2.0D);
						break;
					case 7:
						GL11.glTranslated(3.5D, 0, 0.0D);
						break;
					case 8:
						GL11.glTranslated(-3.5D, 0, 0.0D);
						break;
					case 9:
						GL11.glTranslated(0D, 0, 0D);
						break;
					case 10:
						GL11.glTranslated(0D, 0, 4D);
						break;
					case 11:
						GL11.glTranslated(-1.8D, 0, 2.0D);
						break;
					case 12:
						GL11.glTranslated(0D, 0, -4D);
						break;
					default:
						break;
					}
					renderNormalSpider(partialTickTime);
				}
				GL11.glPopMatrix();
			}
		}

		else if (level == 3)
		{
			for (int pass = 0; pass < 25; pass++)
			{
				GL11.glPushMatrix();
				{
					GL11.glScaled(0.15D, 0.15D, 0.15D);
					GL11.glTranslated(0, 8.6D, 0D);

					switch (pass)
					{
					case 0:
						GL11.glTranslated(0D, 0.0D, -2.0D);
						break;
					case 1:
						GL11.glTranslated(0D, 0, 2.0D);
						break;
					case 2:
						GL11.glTranslated(1.8D, 0, 0D);
						break;
					case 3:
						GL11.glTranslated(-1.8D, 0, 0D);
						break;
					case 4:
						GL11.glTranslated(1.8D, 0, -2.0D);
						break;
					case 5:
						GL11.glTranslated(-1.8D, 0, -2.0D);
						break;
					case 6:
						GL11.glTranslated(1.8D, 0, 2.0D);
						break;
					case 7:
						GL11.glTranslated(3.5D, 0, 0.0D);
						break;
					case 8:
						GL11.glTranslated(-3.5D, 0, 0.0D);
						break;
					case 9:
						GL11.glTranslated(0D, 0, 0D);
						break;
					case 10:
						GL11.glTranslated(0D, 0, 4D);
						break;
					case 11:
						GL11.glTranslated(-1.8D, 0, 2.0D);
						break;
					case 12:
						GL11.glTranslated(0D, 0, -4D);
						break;
					case 13:
						GL11.glTranslated(1.8D, 0, -4D);
						break;
					case 14:
						GL11.glTranslated(-1.8D, 0, -4D);
						break;
					case 15:
						GL11.glTranslated(3.5D, 0, -4D);
						break;
					case 16:
						GL11.glTranslated(-3.5D, 0, -4D);
						break;
					case 17:
						GL11.glTranslated(-3.5D, 0, -2D);
						break;
					case 18:
						GL11.glTranslated(-3.5D, 0, 4D);
						break;
					case 19:
						GL11.glTranslated(-3.5D, 0, 2D);
						break;
					case 20:
						GL11.glTranslated(1.8D, 0, 4D);
						break;
					case 21:
						GL11.glTranslated(3.5D, 0, 4D);
						break;
					case 22:
						GL11.glTranslated(3.5D, 0, 2D);
						break;
					case 23:
						GL11.glTranslated(3.5D, 0, -2D);
						break;
					case 24:
						GL11.glTranslated(-1.8D, 0, 4D);
						break;
					default:
						break;
					}
					renderNormalSpider(partialTickTime);
				}
				GL11.glPopMatrix();
			}
		}
	}

	private void renderVillagerSpiderChest(float partialTickTime)
	{
		GL11.glPushMatrix();
		{
			GL11.glTranslated(0.0D, -0.30D, 0.0D);
			villagerSpiderChestRight.render(partialTickTime);
			villagerSpiderChestLeft.render(partialTickTime);
		}
		GL11.glPopMatrix();
	}

	private void setRotation(ModelRenderer model, float angleX, float angleY, float angleZ)
	{
		model.rotateAngleX = angleX;
		model.rotateAngleY = angleY;
		model.rotateAngleZ = angleZ;
	}
}