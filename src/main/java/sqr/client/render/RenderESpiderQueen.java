package sqr.client.render;


import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import sqr.client.model.ModelSpiderQueen;
import sqr.core.SQR;

public class RenderESpiderQueen extends RenderLiving
{

	public RenderESpiderQueen(ModelSpiderQueen modelbiped, float f)
	{
		super(modelbiped, f);
		this.modelBipedMain = modelbiped;
	}

	@Override
	public void doRender(EntityLivingBase entityliving, double d, double d1, double d2, float f, float f1)
	{
		SQR.setTMale(true);
		super.doRender(entityliving,d,d1,d2,f,f1);
	}

	@Override
	protected void renderEquippedItems(EntityLivingBase entityliving, float f)
	{
		final ItemStack itemstack = entityliving.getHeldItem();
		if(itemstack != null)
		{
			GL11.glPushMatrix();
			//this.modelBipedMain.bipedRightArm.postRender(0.0625F);
			GL11.glTranslatef(-0.0625F, 0.4375F, 0.0625F);
			
//			if(itemstack.itemID < 256 && RenderBlocks.renderItemIn3d(Blocks.blocksList[itemstack.itemID].getRenderType()))
//			{
//				float f1 = 0.5F;
//				GL11.glTranslatef(0.0F, 0.1875F, -0.3125F);
//				f1 *= 0.75F;
//				GL11.glRotatef(20F, 1.0F, 0.0F, 0.0F);
//				GL11.glRotatef(45F, 0.0F, 1.0F, 0.0F);
//				GL11.glScalef(f1, -f1, f1);
//			} else
//				if(Items.itemsList[itemstack.itemID].isFull3D())
//				{
//					final float f2 = 0.625F;
//					GL11.glTranslatef(0.0F, 0.1875F, 0.0F);
//					GL11.glScalef(f2, -f2, f2);
//					GL11.glRotatef(-100F, 1.0F, 0.0F, 0.0F);
//					GL11.glRotatef(45F, 0.0F, 1.0F, 0.0F);
//				} else
//				{
//					final float f3 = 0.375F;
//					GL11.glTranslatef(0.25F, 0.1875F, -0.1875F);
//					GL11.glScalef(f3, f3, f3);
//					GL11.glRotatef(60F, 0.0F, 0.0F, 1.0F);
//					GL11.glRotatef(-90F, 1.0F, 0.0F, 0.0F);
//					GL11.glRotatef(20F, 0.0F, 0.0F, 1.0F);
//				}
//			this.renderManager.itemRenderer.renderItem(entityliving, itemstack, 1);

			//TODO Render from 1.2.2
			
			GL11.glPopMatrix();
		}
	}

	protected ModelSpiderQueen modelBipedMain;

	@Override
	protected ResourceLocation getEntityTexture(Entity p_110775_1_) 
	{
		return null;
	}

}
