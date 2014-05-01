package spiderqueen.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import spiderqueen.entity.EntityHatchedSpider;
import spiderqueen.enums.EnumCocoonType;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelHatchedSpider extends ModelBase
{
	public ModelRenderer defaultSpiderHead;
	public ModelRenderer defaultSpiderNeck;
	public ModelRenderer defaultSpiderBody;
	public ModelRenderer defaultSpiderLeg1;
	public ModelRenderer defaultSpiderLeg2;
	public ModelRenderer defaultSpiderLeg3;
	public ModelRenderer defaultSpiderLeg4;
	public ModelRenderer defaultSpiderLeg5;
	public ModelRenderer defaultSpiderLeg6;
	public ModelRenderer defaultSpiderLeg7;
	public ModelRenderer defaultSpiderLeg8;

	public ModelRenderer boomSpiderHead;
	public ModelRenderer boomSpiderBody;
	public ModelRenderer boomSpiderRearEnd;
	public ModelRenderer boomSpiderLeg1;
	public ModelRenderer boomSpiderLeg2;
	public ModelRenderer boomSpiderLeg3;
	public ModelRenderer boomSpiderLeg4;
	public ModelRenderer boomSpiderLeg5;
	public ModelRenderer boomSpiderLeg6;
	public ModelRenderer boomSpiderLeg7;
	public ModelRenderer boomSpiderLeg8;

	public ModelHatchedSpider()
	{
		initDefaultSpider();
		initBoomSpider();
	}

	public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
	{
		final EntityHatchedSpider spider = (EntityHatchedSpider)par1Entity;
		this.setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);

		if (EnumCocoonType.isAnimalBased(spider.cocoonType) || spider.cocoonType == EnumCocoonType.EMPTY)
		{
			this.defaultSpiderHead.render(par7);
			this.defaultSpiderNeck.render(par7);
			this.defaultSpiderBody.render(par7);
			this.defaultSpiderLeg1.render(par7);
			this.defaultSpiderLeg2.render(par7);
			this.defaultSpiderLeg3.render(par7);
			this.defaultSpiderLeg4.render(par7);
			this.defaultSpiderLeg5.render(par7);
			this.defaultSpiderLeg6.render(par7);
			this.defaultSpiderLeg7.render(par7);
			this.defaultSpiderLeg8.render(par7);
		}

		else if (spider.cocoonType == EnumCocoonType.CREEPER)
		{
			this.boomSpiderHead.render(par7);
			this.boomSpiderBody.render(par7);
			this.boomSpiderRearEnd.render(par7);
			this.boomSpiderLeg1.render(par7);
			this.boomSpiderLeg2.render(par7);
			this.boomSpiderLeg3.render(par7);
			this.boomSpiderLeg4.render(par7);
			this.boomSpiderLeg5.render(par7);
			this.boomSpiderLeg6.render(par7);
			this.boomSpiderLeg7.render(par7);
			this.boomSpiderLeg8.render(par7);
		}
	}

	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity)
	{
		//Default Spider
		final EntityHatchedSpider spider = (EntityHatchedSpider)par7Entity;
		
		if (EnumCocoonType.isAnimalBased(spider.cocoonType) || spider.cocoonType == EnumCocoonType.EMPTY)
		{
			this.defaultSpiderHead.rotateAngleY = par4 / (180F / (float)Math.PI);
			this.defaultSpiderHead.rotateAngleX = par5 / (180F / (float)Math.PI);
			float f6 = ((float)Math.PI / 4F);
			this.defaultSpiderLeg1.rotateAngleZ = -f6;
			this.defaultSpiderLeg2.rotateAngleZ = f6;
			this.defaultSpiderLeg3.rotateAngleZ = -f6 * 0.74F;
			this.defaultSpiderLeg4.rotateAngleZ = f6 * 0.74F;
			this.defaultSpiderLeg5.rotateAngleZ = -f6 * 0.74F;
			this.defaultSpiderLeg6.rotateAngleZ = f6 * 0.74F;
			this.defaultSpiderLeg7.rotateAngleZ = -f6;
			this.defaultSpiderLeg8.rotateAngleZ = f6;
			float f7 = -0.0F;
			float f8 = 0.3926991F;
			this.defaultSpiderLeg1.rotateAngleY = f8 * 2.0F + f7;
			this.defaultSpiderLeg2.rotateAngleY = -f8 * 2.0F - f7;
			this.defaultSpiderLeg3.rotateAngleY = f8 * 1.0F + f7;
			this.defaultSpiderLeg4.rotateAngleY = -f8 * 1.0F - f7;
			this.defaultSpiderLeg5.rotateAngleY = -f8 * 1.0F + f7;
			this.defaultSpiderLeg6.rotateAngleY = f8 * 1.0F - f7;
			this.defaultSpiderLeg7.rotateAngleY = -f8 * 2.0F + f7;
			this.defaultSpiderLeg8.rotateAngleY = f8 * 2.0F - f7;
			float f9 = -(MathHelper.cos(par1 * 0.6662F * 2.0F + 0.0F) * 0.4F) * par2;
			float f10 = -(MathHelper.cos(par1 * 0.6662F * 2.0F + (float)Math.PI) * 0.4F) * par2;
			float f11 = -(MathHelper.cos(par1 * 0.6662F * 2.0F + ((float)Math.PI / 2F)) * 0.4F) * par2;
			float f12 = -(MathHelper.cos(par1 * 0.6662F * 2.0F + ((float)Math.PI * 3F / 2F)) * 0.4F) * par2;
			float f13 = Math.abs(MathHelper.sin(par1 * 0.6662F + 0.0F) * 0.4F) * par2;
			float f14 = Math.abs(MathHelper.sin(par1 * 0.6662F + (float)Math.PI) * 0.4F) * par2;
			float f15 = Math.abs(MathHelper.sin(par1 * 0.6662F + ((float)Math.PI / 2F)) * 0.4F) * par2;
			float f16 = Math.abs(MathHelper.sin(par1 * 0.6662F + ((float)Math.PI * 3F / 2F)) * 0.4F) * par2;
			this.defaultSpiderLeg1.rotateAngleY += f9;
			this.defaultSpiderLeg2.rotateAngleY += -f9;
			this.defaultSpiderLeg3.rotateAngleY += f10;
			this.defaultSpiderLeg4.rotateAngleY += -f10;
			this.defaultSpiderLeg5.rotateAngleY += f11;
			this.defaultSpiderLeg6.rotateAngleY += -f11;
			this.defaultSpiderLeg7.rotateAngleY += f12;
			this.defaultSpiderLeg8.rotateAngleY += -f12;
			this.defaultSpiderLeg1.rotateAngleZ += f13;
			this.defaultSpiderLeg2.rotateAngleZ += -f13;
			this.defaultSpiderLeg3.rotateAngleZ += f14;
			this.defaultSpiderLeg4.rotateAngleZ += -f14;
			this.defaultSpiderLeg5.rotateAngleZ += f15;
			this.defaultSpiderLeg6.rotateAngleZ += -f15;
			this.defaultSpiderLeg7.rotateAngleZ += f16;
			this.defaultSpiderLeg8.rotateAngleZ += -f16;
		}

		else if (spider.cocoonType == EnumCocoonType.CREEPER)
		{
			boomSpiderHead.rotateAngleY = par4 / 57.29578F;
			boomSpiderHead.rotateAngleX = par5 / 57.29578F;
			float f6 = 0.7853982F;
			boomSpiderLeg1.rotateAngleZ = -f6;
			boomSpiderLeg2.rotateAngleZ = f6;
			boomSpiderLeg3.rotateAngleZ = -f6 * 0.74F;
			boomSpiderLeg4.rotateAngleZ = f6 * 0.74F;
			boomSpiderLeg5.rotateAngleZ = -f6 * 0.74F;
			boomSpiderLeg6.rotateAngleZ = f6 * 0.74F;
			boomSpiderLeg7.rotateAngleZ = -f6;
			boomSpiderLeg8.rotateAngleZ = f6;
			float f7 = -0F;
			float f8 = 0.3926991F;
			boomSpiderLeg1.rotateAngleY = f8 * 2.0F + f7;
			boomSpiderLeg2.rotateAngleY = -f8 * 2.0F - f7;
			boomSpiderLeg3.rotateAngleY = f8 * 1.0F + f7;
			boomSpiderLeg4.rotateAngleY = -f8 * 1.0F - f7;
			boomSpiderLeg5.rotateAngleY = -f8 * 1.0F + f7;
			boomSpiderLeg6.rotateAngleY = f8 * 1.0F - f7;
			boomSpiderLeg7.rotateAngleY = -f8 * 2.0F + f7;
			boomSpiderLeg8.rotateAngleY = f8 * 2.0F - f7;
			float f9 = -(MathHelper.cos(par1 * 0.6662F * 2.0F + 0.0F) * 0.4F) * par2;
			float f10 = -(MathHelper.cos(par1 * 0.6662F * 2.0F + 3.141593F) * 0.4F) * par2;
			float f11 = -(MathHelper.cos(par1 * 0.6662F * 2.0F + 1.570796F) * 0.4F) * par2;
			float f12 = -(MathHelper.cos(par1 * 0.6662F * 2.0F + 4.712389F) * 0.4F) * par2;
			float f13 = Math.abs(MathHelper.sin(par1 * 0.6662F + 0.0F) * 0.4F) * par2;
			float f14 = Math.abs(MathHelper.sin(par1 * 0.6662F + 3.141593F) * 0.4F) * par2;
			float f15 = Math.abs(MathHelper.sin(par1 * 0.6662F + 1.570796F) * 0.4F) * par2;
			float f16 = Math.abs(MathHelper.sin(par1 * 0.6662F + 4.712389F) * 0.4F) * par2;
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
	}
	
	private void initDefaultSpider()
	{
		this.defaultSpiderHead = new ModelRenderer(this, 32, 4);
		this.defaultSpiderHead.addBox(-4.0F, -4.0F, -8.0F, 8, 8, 8, 0.0F);
		this.defaultSpiderHead.setRotationPoint(0.0F, 15, -3.0F);

		this.defaultSpiderNeck = new ModelRenderer(this, 0, 0);
		this.defaultSpiderNeck.addBox(-3.0F, -3.0F, -3.0F, 6, 6, 6, 0.0F);
		this.defaultSpiderNeck.setRotationPoint(0.0F, 15, 0.0F);

		this.defaultSpiderBody = new ModelRenderer(this, 0, 12);
		this.defaultSpiderBody.addBox(-5.0F, -4.0F, -6.0F, 10, 8, 12, 0.0F);
		this.defaultSpiderBody.setRotationPoint(0.0F, 15, 9.0F);

		this.defaultSpiderLeg1 = new ModelRenderer(this, 18, 0);
		this.defaultSpiderLeg1.addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
		this.defaultSpiderLeg1.setRotationPoint(-4.0F, 15, 2.0F);

		this.defaultSpiderLeg2 = new ModelRenderer(this, 18, 0);
		this.defaultSpiderLeg2.addBox(-1.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
		this.defaultSpiderLeg2.setRotationPoint(4.0F, 15, 2.0F);

		this.defaultSpiderLeg3 = new ModelRenderer(this, 18, 0);
		this.defaultSpiderLeg3.addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
		this.defaultSpiderLeg3.setRotationPoint(-4.0F, 15, 1.0F);

		this.defaultSpiderLeg4 = new ModelRenderer(this, 18, 0);
		this.defaultSpiderLeg4.addBox(-1.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
		this.defaultSpiderLeg4.setRotationPoint(4.0F, 15, 1.0F);

		this.defaultSpiderLeg5 = new ModelRenderer(this, 18, 0);
		this.defaultSpiderLeg5.addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
		this.defaultSpiderLeg5.setRotationPoint(-4.0F, 15, 0.0F);

		this.defaultSpiderLeg6 = new ModelRenderer(this, 18, 0);
		this.defaultSpiderLeg6.addBox(-1.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
		this.defaultSpiderLeg6.setRotationPoint(4.0F, 15, 0.0F);

		this.defaultSpiderLeg7 = new ModelRenderer(this, 18, 0);
		this.defaultSpiderLeg7.addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
		this.defaultSpiderLeg7.setRotationPoint(-4.0F, 15, -1.0F);

		this.defaultSpiderLeg8 = new ModelRenderer(this, 18, 0);
		this.defaultSpiderLeg8.addBox(-1.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
		this.defaultSpiderLeg8.setRotationPoint(4.0F, 15, -1.0F);
	}
	
	private void initBoomSpider()
	{
		this.boomSpiderHead = new ModelRenderer(this,32, 4);
		this.boomSpiderHead.addBox(-4F, -4F, -8F, 8, 8, 8, 0.0F);
		this.boomSpiderHead.setRotationPoint(0F, 15F, -3F);
		
		this.boomSpiderBody = new ModelRenderer(this,0, 0);
		this.boomSpiderBody.addBox(-3F, -3F, -3F, 6, 6, 6, 0.0F);
		this.boomSpiderBody.setRotationPoint(0F, 15F, 0F);
		
		this.boomSpiderRearEnd = new ModelRenderer(this,0, 12);
		this.boomSpiderRearEnd.addBox(-5F, -4F, -6F, 10, 8, 12, 0.0F);
		this.boomSpiderRearEnd.setRotationPoint(0F, 11F, 7F);
		this.boomSpiderRearEnd.rotateAngleX = 0.63284F;
		
		this.boomSpiderLeg1 = new ModelRenderer(this,18, 0);
		this.boomSpiderLeg1.addBox(-15F, -1F, -1F, 16, 2, 2, 0.0F);
		this.boomSpiderLeg1.setRotationPoint(-4F, 15F, 2F);
		this.boomSpiderLeg1.rotateAngleX = 0.57596F;
		this.boomSpiderLeg1.rotateAngleY = 0.19199F;
		
		this.boomSpiderLeg2 = new ModelRenderer(this,18, 0);
		this.boomSpiderLeg2.addBox(-1F, -1F, -1F, 16, 2, 2, 0.0F);
		this.boomSpiderLeg2.setRotationPoint(4F, 15F, 2F);
		this.boomSpiderLeg2.rotateAngleX = -0.57596F;
		this.boomSpiderLeg2.rotateAngleY = -0.19199F;
		
		this.boomSpiderLeg3 = new ModelRenderer(this,18, 0);
		this.boomSpiderLeg3.addBox(-15F, -1F, -1F, 16, 2, 2, 0.0F);
		this.boomSpiderLeg3.setRotationPoint(-4F, 15F, 1F);
		this.boomSpiderLeg3.rotateAngleX = 0.27925F;
		this.boomSpiderLeg3.rotateAngleY = 0.19199F;
		
		this.boomSpiderLeg4 = new ModelRenderer(this,18, 0);
		this.boomSpiderLeg4.addBox(-1F, -1F, -1F, 16, 2, 2, 0.0F);
		this.boomSpiderLeg4.setRotationPoint(4F, 15F, 1F);
		this.boomSpiderLeg4.rotateAngleX = -0.27925F;
		this.boomSpiderLeg4.rotateAngleY = -0.19199F;
		
		this.boomSpiderLeg5 = new ModelRenderer(this,18, 0);
		this.boomSpiderLeg5.addBox(-15F, -1F, -1F, 16, 2, 2, 0.0F);
		this.boomSpiderLeg5.setRotationPoint(-4F, 15F, 0F);
		this.boomSpiderLeg5.rotateAngleX = -0.27925F;
		this.boomSpiderLeg5.rotateAngleY = 0.19199F;
		
		this.boomSpiderLeg6 = new ModelRenderer(this,18, 0);
		this.boomSpiderLeg6.addBox(-1F, -1F, -1F, 16, 2, 2, 0.0F);
		this.boomSpiderLeg6.setRotationPoint(4F, 15F, 0F);
		this.boomSpiderLeg6.rotateAngleX = 0.27925F;
		this.boomSpiderLeg6.rotateAngleY = -0.19199F;
		
		this.boomSpiderLeg7 = new ModelRenderer(this,18, 0);
		this.boomSpiderLeg7.addBox(-15F, -1F, -1F, 16, 2, 2, 0.0F);
		this.boomSpiderLeg7.setRotationPoint(-4F, 15F, -1F);
		this.boomSpiderLeg7.rotateAngleX = -0.57596F;
		this.boomSpiderLeg7.rotateAngleY = 0.19199F;
		
		this.boomSpiderLeg8 = new ModelRenderer(this,18, 0);
		this.boomSpiderLeg8.addBox(-1F, -1F, -1F, 16, 2, 2, 0.0F);
		this.boomSpiderLeg8.setRotationPoint(4F, 15F, -1F);
		this.boomSpiderLeg8.rotateAngleX = 0.57596F;
		this.boomSpiderLeg8.rotateAngleY = -0.19199F;
	}
}