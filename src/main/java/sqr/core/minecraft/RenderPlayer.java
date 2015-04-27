//package sqr.core.minecraft;
//
//import net.minecraft.client.Minecraft;
//import net.minecraft.client.entity.EntityPlayerSP;
//import net.minecraft.client.gui.FontRenderer;
//import net.minecraft.client.model.ModelBiped;
//import net.minecraft.client.renderer.RenderBlocks;
//import net.minecraft.client.renderer.Tessellator;
//import net.minecraft.client.renderer.entity.RenderLiving;
//import net.minecraft.entity.Entity;
//import net.minecraft.entity.EntityLivingBase;
//import net.minecraft.entity.player.EntityPlayer;
//import net.minecraft.init.Blocks;
//import net.minecraft.init.Items;
//import net.minecraft.item.EnumAction;
//import net.minecraft.item.ItemStack;
//import net.minecraft.util.MathHelper;
//
//import org.lwjgl.opengl.GL11;
//
//import sqr.client.model.ModelSpiderQueen;
//
//public class RenderPlayer extends RenderLiving
//{
//
//	private final ModelSpiderQueen modelBipedMain; // SNIP
//
//	private final ModelBiped modelArmorChestplate;
//	private final ModelBiped modelArmor;
//	private static final String armorFilenamePrefix[] =
//		{
//		"cloth", "chain", "iron", "diamond", "gold"
//		};
//
//	public RenderPlayer()
//	{
//
//		super(new ModelSpiderQueen(), 0.5F);	// SNIP
//		this.modelBipedMain = (ModelSpiderQueen)this.mainModel;	// SNIP
//
//		this.modelArmorChestplate = new ModelBiped(1.0F);
//		this.modelArmor = new ModelBiped(0.5F);
//	}
//
//
//	protected int setArmorModel(EntityPlayer par1EntityPlayer, int par2, float par3)
//	{
//		// SNIP
//		/*ItemStack itemstack = par1EntityPlayer.inventory.armorItemInSlot(3 - par2);
//
//        if (itemstack != null)
//        {
//            Item item = itemstack.getItem();
//
//            if (item instanceof ItemArmor)
//            {
//                ItemArmor itemarmor = (ItemArmor)item;
//                bindTexture(new ResourceLocation((new StringBuilder()).append("/armor/").append(armorFilenamePrefix[itemarmor.renderIndex]).append("_").append(par2 != 2 ? 1 : 2).append(".png").toString());
//                ModelBiped modelbiped = par2 != 2 ? modelArmorChestplate : modelArmor;
//                modelbiped.bipedHead.showModel = par2 == 0;
//                modelbiped.bipedHeadwear.showModel = par2 == 0;
//                modelbiped.bipedBody.showModel = par2 == 1 || par2 == 2;
//                modelbiped.bipedRightArm.showModel = par2 == 1;
//                modelbiped.bipedLeftArm.showModel = par2 == 1;
//                modelbiped.bipedRightLeg.showModel = par2 == 2 || par2 == 3;
//                modelbiped.bipedLeftLeg.showModel = par2 == 2 || par2 == 3;
//                setRenderPassModel(modelbiped);
//                return !itemstack.isItemEnchanted() ? 1 : 15;
//            }
//        }*/
//
//		return -1;
//	}
//
//	public void renderPlayer(EntityPlayer par1EntityPlayer, double par2, double par4, double par6, float par8, float par9)
//	{
//		final ItemStack itemstack = par1EntityPlayer.inventory.getCurrentItem();
//		// SNIP
//		/*
//		modelArmorChestplate.heldItemRight = modelArmor.heldItemRight = modelBipedMain.heldItemRight = itemstack == null ? 0 : 1;
//
//        if (itemstack != null && par1EntityPlayer.getItemInUseCount() > 0)
//        {
//            EnumAction enumaction = itemstack.getItemUseAction();
//
//            if (enumaction == EnumAction.block)
//            {
//                modelArmorChestplate.heldItemRight = modelArmor.heldItemRight = modelBipedMain.heldItemRight = 3;
//            }
//            else if (enumaction == EnumAction.bow)
//            {
//                modelArmorChestplate.aimedBow = modelArmor.aimedBow = modelBipedMain.aimedBow = true;
//            }
//        }
//
//        modelArmorChestplate.isSneak = modelArmor.isSneak = modelBipedMain.isSneak = par1EntityPlayer.isSneaking();
//		 */
//		// -SNIP
//
//		double d = par4 - par1EntityPlayer.yOffset;
//
//		if (par1EntityPlayer.isSneaking() && !(par1EntityPlayer instanceof EntityPlayerSP))
//		{
//			d -= 0.125D;
//		}
//
//		super.doRenderLiving(par1EntityPlayer, par2, d, par6, par8, par9);
//
//		// SNIP
//		/*modelArmorChestplate.aimedBow = modelArmor.aimedBow = modelBipedMain.aimedBow = false;
//        modelArmorChestplate.isSneak = modelArmor.isSneak = modelBipedMain.isSneak = false;
//        modelArmorChestplate.heldItemRight = modelArmor.heldItemRight = modelBipedMain.heldItemRight = 0;*/
//		// -SNIP
//	}
//
//
//	protected void renderName(EntityPlayer par1EntityPlayer, double par2, double par4, double par6)
//	{
//		if (Minecraft.isGuiEnabled() && par1EntityPlayer != this.renderManager.livingPlayer)
//		{
//			final float f = 1.6F;
//			final float f1 = 0.01666667F * f;
//			final float f2 = par1EntityPlayer.getDistanceToEntity(this.renderManager.livingPlayer);
//			final float f3 = par1EntityPlayer.isSneaking() ? 32F : 64F;
//
//			if (f2 < f3)
//			{
//				final String s = par1EntityPlayer.username;
//
//				if (!par1EntityPlayer.isSneaking())
//				{
//					if (par1EntityPlayer.isPlayerSleeping())
//					{
//						this.renderLivingLabel(par1EntityPlayer, s, par2, par4 - 1.5D, par6, 64);
//					}
//					else
//					{
//						this.renderLivingLabel(par1EntityPlayer, s, par2, par4, par6, 64);
//					}
//				}
//				else
//				{
//					final FontRenderer fontrenderer = this.getFontRendererFromRenderManager();
//					GL11.glPushMatrix();
//					GL11.glTranslatef((float)par2 + 0.0F, (float)par4 + 2.3F, (float)par6);
//					GL11.glNormal3f(0.0F, 1.0F, 0.0F);
//					GL11.glRotatef(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
//					GL11.glRotatef(this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
//					GL11.glScalef(-f1, -f1, f1);
//					GL11.glDisable(GL11.GL_LIGHTING);
//					GL11.glTranslatef(0.0F, 0.25F / f1, 0.0F);
//					GL11.glDepthMask(false);
//					GL11.glEnable(GL11.GL_BLEND);
//					GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
//					final Tessellator tessellator = Tessellator.instance;
//					GL11.glDisable(GL11.GL_TEXTURE_2D);
//					tessellator.startDrawingQuads();
//					final int i = fontrenderer.getStringWidth(s) / 2;
//					tessellator.setColorRGBA_F(0.0F, 0.0F, 0.0F, 0.25F);
//					tessellator.addVertex(-i - 1, -1D, 0.0D);
//					tessellator.addVertex(-i - 1, 8D, 0.0D);
//					tessellator.addVertex(x +1, 8D, 0.0D);
//					tessellator.addVertex(x +1, -1D, 0.0D);
//					tessellator.draw();
//					GL11.glEnable(GL11.GL_TEXTURE_2D);
//					GL11.glDepthMask(true);
//					fontrenderer.drawString(s, -fontrenderer.getStringWidth(s) / 2, 0, 0x20ffffff);
//					GL11.glEnable(GL11.GL_LIGHTING);
//					GL11.glDisable(GL11.GL_BLEND);
//					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
//					GL11.glPopMatrix();
//				}
//			}
//		}
//	}
//
//
//	protected void renderSpecials(EntityPlayer par1EntityPlayer, float par2)
//	{
//		super.renderEquippedItems(par1EntityPlayer, par2);
//		final ItemStack itemstack = par1EntityPlayer.inventory.armorItemInSlot(3);
//
//		if (itemstack != null && itemstack.getItem() < 256)
//		{
//			GL11.glPushMatrix();
//			this.modelBipedMain.bipedHead.postRender(0.0625F);
//
//			if (RenderBlocks.renderItemIn3d(Blocks.blocksList[itemstack.itemID].getRenderType()))
//			{
//				final float f = 0.625F;
//				GL11.glTranslatef(0.0F, -0.25F, 0.0F);
//				GL11.glRotatef(180F, 0.0F, 1.0F, 0.0F);
//				GL11.glScalef(f, -f, f);
//			}
//
//			this.renderManager.itemRenderer.renderItem(par1EntityPlayer, itemstack, 0);
//			GL11.glPopMatrix();
//		}
//
//		if (par1EntityPlayer.username.equals("deadmau5") && loadDownloadableImageTexture(par1EntityPlayer.skinUrl, null))
//		{
//			for (int i = 0; i < 2; i++)
//			{
//				final float f1 = par1EntityPlayer.prevRotationYaw + (par1EntityPlayer.rotationYaw - par1EntityPlayer.prevRotationYaw) * par2 - (par1EntityPlayer.prevRenderYawOffset + (par1EntityPlayer.renderYawOffset - par1EntityPlayer.prevRenderYawOffset) * par2);
//				final float f2 = par1EntityPlayer.prevRotationPitch + (par1EntityPlayer.rotationPitch - par1EntityPlayer.prevRotationPitch) * par2;
//				GL11.glPushMatrix();
//				GL11.glRotatef(f1, 0.0F, 1.0F, 0.0F);
//				GL11.glRotatef(f2, 1.0F, 0.0F, 0.0F);
//				GL11.glTranslatef(0.375F * (i * 2 - 1), 0.0F, 0.0F);
//				GL11.glTranslatef(0.0F, -0.375F, 0.0F);
//				GL11.glRotatef(-f2, 1.0F, 0.0F, 0.0F);
//				GL11.glRotatef(-f1, 0.0F, 1.0F, 0.0F);
//				final float f7 = 1.333333F;
//				GL11.glScalef(f7, f7, f7);
//				// modelBipedMain.renderEars(0.0625F);	// SNIP
//				GL11.glPopMatrix();
//			}
//		}
//
//		if (loadDownloadableImageTexture(par1EntityPlayer.playerCloakUrl, null))
//		{
//			GL11.glPushMatrix();
//			GL11.glTranslatef(0.0F, 0.0F, 0.125F);
//			final double d = par1EntityPlayer.field_20066_r + (par1EntityPlayer.field_20063_u - par1EntityPlayer.field_20066_r) * (double)par2 - (par1EntityPlayer.prevPosX + (par1EntityPlayer.posX - par1EntityPlayer.prevPosX) * par2);
//			final double d1 = par1EntityPlayer.field_20065_s + (par1EntityPlayer.field_20062_v - par1EntityPlayer.field_20065_s) * (double)par2 - (par1EntityPlayer.prevPosY + (par1EntityPlayer.posY - par1EntityPlayer.prevPosY) * par2);
//			final double d2 = par1EntityPlayer.field_20064_t + (par1EntityPlayer.field_20061_w - par1EntityPlayer.field_20064_t) * (double)par2 - (par1EntityPlayer.prevPosZ + (par1EntityPlayer.posZ - par1EntityPlayer.prevPosZ) * par2);
//			final float f10 = par1EntityPlayer.prevRenderYawOffset + (par1EntityPlayer.renderYawOffset - par1EntityPlayer.prevRenderYawOffset) * par2;
//			final double d3 = MathHelper.sin(f10 * (float)Math.PI / 180F);
//			final double d4 = -MathHelper.cos(f10 * (float)Math.PI / 180F);
//			float f12 = (float)d1 * 10F;
//
//			if (f12 < -6F)
//			{
//				f12 = -6F;
//			}
//
//			if (f12 > 32F)
//			{
//				f12 = 32F;
//			}
//
//			float f13 = (float)(d * d3 + d2 * d4) * 100F;
//			final float f14 = (float)(d * d4 - d2 * d3) * 100F;
//
//			if (f13 < 0.0F)
//			{
//				f13 = 0.0F;
//			}
//
//			final float f15 = par1EntityPlayer.prevCameraYaw + (par1EntityPlayer.cameraYaw - par1EntityPlayer.prevCameraYaw) * par2;
//			f12 += MathHelper.sin((par1EntityPlayer.prevDistanceWalkedModified + (par1EntityPlayer.distanceWalkedModified - par1EntityPlayer.prevDistanceWalkedModified) * par2) * 6F) * 32F * f15;
//
//			if (par1EntityPlayer.isSneaking())
//			{
//				f12 += 25F;
//			}
//
//			GL11.glRotatef(6F + f13 / 2.0F + f12, 1.0F, 0.0F, 0.0F);
//			GL11.glRotatef(f14 / 2.0F, 0.0F, 0.0F, 1.0F);
//			GL11.glRotatef(-f14 / 2.0F, 0.0F, 1.0F, 0.0F);
//			GL11.glRotatef(180F, 0.0F, 1.0F, 0.0F);
//			//modelBipedMain.renderCloak(0.0625F);	// SNIP
//			GL11.glPopMatrix();
//		}
//
//		ItemStack itemstack1 = par1EntityPlayer.inventory.getCurrentItem();
//
//		if (itemstack1 != null)
//		{
//			GL11.glPushMatrix();
//			this.modelBipedMain.bipedRightArm.postRender(0.0625F);
//			GL11.glTranslatef(-0.0625F, 0.4375F, 0.0625F);
//
//			if (par1EntityPlayer.fishEntity != null)
//			{
//				itemstack1 = new ItemStack(Items.stick);
//			}
//
//			EnumAction enumaction = null;
//
//			if (par1EntityPlayer.getItemInUseCount() > 0)
//			{
//				enumaction = itemstack1.getItemUseAction();
//			}
//
//			if (itemstack1.itemID < 256 && RenderBlocks.renderItemIn3d(Blocks.blocksList[itemstack1.itemID].getRenderType()))
//			{
//				float f3 = 0.5F;
//				GL11.glTranslatef(0.0F, 0.1875F, -0.3125F);
//				f3 *= 0.75F;
//				GL11.glRotatef(20F, 1.0F, 0.0F, 0.0F);
//				GL11.glRotatef(45F, 0.0F, 1.0F, 0.0F);
//				GL11.glScalef(f3, -f3, f3);
//			}
//			else if (itemstack1.itemID == Items.bow)
//			{
//				final float f4 = 0.625F;
//				GL11.glTranslatef(0.0F, 0.125F, 0.3125F);
//				GL11.glRotatef(-20F, 0.0F, 1.0F, 0.0F);
//				GL11.glScalef(f4, -f4, f4);
//				GL11.glRotatef(-100F, 1.0F, 0.0F, 0.0F);
//				GL11.glRotatef(45F, 0.0F, 1.0F, 0.0F);
//			}
//			else if (Items.itemsList[itemstack1.itemID].isFull3D())
//			{
//				final float f5 = 0.625F;
//
//				if (Items.itemsList[itemstack1.itemID].shouldRotateAroundWhenRendering())
//				{
//					GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
//					GL11.glTranslatef(0.0F, -0.125F, 0.0F);
//				}
//
//				if (par1EntityPlayer.getItemInUseCount() > 0 && enumaction == EnumAction.block)
//				{
//					GL11.glTranslatef(0.05F, 0.0F, -0.1F);
//					GL11.glRotatef(-50F, 0.0F, 1.0F, 0.0F);
//					GL11.glRotatef(-10F, 1.0F, 0.0F, 0.0F);
//					GL11.glRotatef(-60F, 0.0F, 0.0F, 1.0F);
//				}
//
//				GL11.glTranslatef(0.0F, 0.1875F, 0.0F);
//				GL11.glScalef(f5, -f5, f5);
//				GL11.glRotatef(-100F, 1.0F, 0.0F, 0.0F);
//				GL11.glRotatef(45F, 0.0F, 1.0F, 0.0F);
//			}
//			else
//			{
//				final float f6 = 0.375F;
//				GL11.glTranslatef(0.25F, 0.1875F, -0.1875F);
//				GL11.glScalef(f6, f6, f6);
//				GL11.glRotatef(60F, 0.0F, 0.0F, 1.0F);
//				GL11.glRotatef(-90F, 1.0F, 0.0F, 0.0F);
//				GL11.glRotatef(20F, 0.0F, 0.0F, 1.0F);
//			}
//
//			if (itemstack1.getItem().func_46058_c())
//			{
//				for (int j = 0; j <= 1; j++)
//				{
//					final int k = itemstack1.getItem().getColorFromDamage(itemstack1.getItemDamage(), j);
//					final float f8 = (k >> 16 & 0xff) / 255F;
//					final float f9 = (k >> 8 & 0xff) / 255F;
//					final float f11 = (k & 0xff) / 255F;
//					GL11.glColor4f(f8, f9, f11, 1.0F);
//					this.renderManager.itemRenderer.renderItem(par1EntityPlayer, itemstack1, j);
//				}
//			}
//			else
//			{
//				this.renderManager.itemRenderer.renderItem(par1EntityPlayer, itemstack1, 0);
//			}
//
//			GL11.glPopMatrix();
//		}
//	}
//
//	protected void renderPlayerScale(EntityPlayer par1EntityPlayer, float par2)
//	{
//		final float f = 0.9375F;
//		GL11.glScalef(f, f, f);
//	}
//
//	public void drawFirstPersonHand()
//	{
//		this.modelBipedMain.onGround = 0.0F;
//		this.modelBipedMain.setRotationAngles(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
//		this.modelBipedMain.bipedRightArm.render(0.0625F);
//	}
//
//
//	protected void renderPlayerSleep(EntityPlayer par1EntityPlayer, double par2, double par4, double par6)
//	{
//		if (par1EntityPlayer.isEntityAlive() && par1EntityPlayer.isPlayerSleeping())
//		{
//			super.renderLivingAt(par1EntityPlayer, par2 + (double)par1EntityPlayer.field_22063_x, par4 + (double)par1EntityPlayer.field_22062_y, par6 + (double)par1EntityPlayer.field_22061_z);
//		}
//		else
//		{
//			super.renderLivingAt(par1EntityPlayer, par2, par4, par6);
//		}
//	}
//
//
//	protected void rotatePlayer(EntityPlayer par1EntityPlayer, float par2, float par3, float par4)
//	{
//		if (par1EntityPlayer.isEntityAlive() && par1EntityPlayer.isPlayerSleeping())
//		{
//			GL11.glRotatef(par1EntityPlayer.getBedOrientationInDegrees(), 0.0F, 1.0F, 0.0F);
//			GL11.glRotatef(this.getDeathMaxRotation(par1EntityPlayer), 0.0F, 0.0F, 1.0F);
//			GL11.glRotatef(270F, 0.0F, 1.0F, 0.0F);
//		}
//		else
//		{
//			super.rotateCorpse(par1EntityPlayer, par2, par3, par4);
//		}
//	}
//
//
//	@Override
//	protected void passSpecialRender(EntityLivingBase par1EntityLivingBase, double par2, double par4, double par6)
//	{
//		this.renderName((EntityPlayer)par1EntityLivingBase, par2, par4, par6);
//	}
//
//
//	@Override
//	protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2)
//	{
//		this.renderPlayerScale((EntityPlayer)par1EntityLivingBase, par2);
//	}
//
//
//	@Override
//	protected int shouldRenderPass(EntityLivingBase par1EntityLivingBase, int par2, float par3)
//	{
//		return this.setArmorModel((EntityPlayer)par1EntityLivingBase, par2, par3);
//	}
//
//	@Override
//	protected void renderEquippedItems(EntityLivingBase par1EntityLivingBase, float par2)
//	{
//		this.renderSpecials((EntityPlayer)par1EntityLivingBase, par2);
//	}
//
//	@Override
//	protected void rotateCorpse(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4)
//	{
//		this.rotatePlayer((EntityPlayer)par1EntityLivingBase, par2, par3, par4);
//	}
//
//
//	@Override
//	protected void renderLivingAt(EntityLivingBase par1EntityLivingBase, double par2, double par4, double par6)
//	{
//		this.renderPlayerSleep((EntityPlayer)par1EntityLivingBase, par2, par4, par6);
//	}
//
//	@Override
//	public void doRenderLiving(EntityLivingBase par1EntityLivingBase, double par2, double par4, double par6, float par8, float par9)
//	{
//		this.renderPlayer((EntityPlayer)par1EntityLivingBase, par2, par4, par6, par8, par9);
//	}
//
//
//	@Override
//	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
//	{
//		this.renderPlayer((EntityPlayer)par1Entity, par2, par4, par6, par8, par9);
//	}
// }
