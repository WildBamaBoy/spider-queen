//package sqr.core.minecraft;
//
//import net.minecraft.client.Minecraft;
//import net.minecraft.client.gui.FontRenderer;
//import net.minecraft.client.model.ModelBase;
//import net.minecraft.client.renderer.OpenGlHelper;
//import net.minecraft.client.renderer.Tessellator;
//import net.minecraft.client.renderer.entity.Render;
//import net.minecraft.entity.Entity;
//import net.minecraft.entity.EntityLivingBase;
//import net.minecraft.entity.player.EntityPlayer;
//import net.minecraft.util.MathHelper;
//
//import org.lwjgl.opengl.GL11;
//import org.lwjgl.opengl.GL12;
//
//import sqr.core.SQR;
//
//public class RenderLiving extends Render
//{
//	protected ModelBase mainModel;
//
//
//	protected ModelBase renderPassModel;
//
//	public RenderLiving(ModelBase par1ModelBase, float par2)
//	{
//		this.mainModel = par1ModelBase;
//		this.shadowSize = par2;
//	}
//
//
//	public void setRenderPassModel(ModelBase par1ModelBase)
//	{
//		this.renderPassModel = par1ModelBase;
//	}
//
//	private float func_48418_a(float par1, float par2, float par3)
//	{
//		float f;
//
//		for (f = par2 - par1; f < -180F; f += 360F) { }
//
//		for (; f >= 180F; f -= 360F) { }
//
//		return par1 + par3 * f;
//	}
//
//	public void doRenderLiving(EntityLivingBase par1EntityLivingBase, double par2, double par4, double par6, float par8, float par9)
//	{
//		GL11.glPushMatrix();
//		GL11.glDisable(GL11.GL_CULL_FACE);
//		this.mainModel.onGround = this.renderSwingProgress(par1EntityLivingBase, par9);
//
//		if (this.renderPassModel != null)
//		{
//			this.renderPassModel.onGround = this.mainModel.onGround;
//		}
//
//		this.mainModel.isRiding = par1EntityLivingBase.isRiding();
//
//		if (this.renderPassModel != null)
//		{
//			this.renderPassModel.isRiding = this.mainModel.isRiding;
//		}
//
//		this.mainModel.isChild = par1EntityLivingBase.isChild();
//
//		if (this.renderPassModel != null)
//		{
//			this.renderPassModel.isChild = this.mainModel.isChild;
//		}
//
//		try
//		{
//			final float f = this.func_48418_a(par1EntityLivingBase.prevRenderYawOffset, par1EntityLivingBase.renderYawOffset, par9);
//			final float f1 = this.func_48418_a(par1EntityLivingBase.prevRotationYawHead, par1EntityLivingBase.rotationYawHead, par9);
//			final float f2 = par1EntityLivingBase.prevRotationPitch + (par1EntityLivingBase.rotationPitch - par1EntityLivingBase.prevRotationPitch) * par9;
//			this.renderLivingAt(par1EntityLivingBase, par2, par4, par6);
//			final float f3 = this.handleRotationFloat(par1EntityLivingBase, par9);
//			this.rotateCorpse(par1EntityLivingBase, f3, f, par9);
//			final float f4 = 0.0625F;
//			GL11.glEnable(GL12.GL_RESCALE_NORMAL);
//			GL11.glScalef(-1F, -1F, 1.0F);
//			this.preRenderCallback(par1EntityLivingBase, par9);
//			GL11.glTranslatef(0.0F, -24F * f4 - 0.0078125F, 0.0F);
//			float f5 = par1EntityLivingBase.field_705_Q + (par1EntityLivingBase.field_704_R - par1EntityLivingBase.field_705_Q) * par9;
//			float f6 = par1EntityLivingBase.field_703_S - par1EntityLivingBase.field_704_R * (1.0F - par9);
//
//			if (par1EntityLivingBase.isChild())
//			{
//				f6 *= 3F;
//			}
//
//			if (f5 > 1.0F)
//			{
//				f5 = 1.0F;
//			}
//
//			GL11.glEnable(GL11.GL_ALPHA_TEST);
//			this.mainModel.setLivingAnimations(par1EntityLivingBase, f6, f5, par9);
//			this.renderModel(par1EntityLivingBase, f6, f5, f3, f1 - f, f2, f4);
//
//			for (int i = 0; i < 4; i++)
//			{
//				final int j = this.shouldRenderPass(par1EntityLivingBase, i, par9);
//
//				if (j <= 0)
//				{
//					continue;
//				}
//
//				this.renderPassModel.setLivingAnimations(par1EntityLivingBase, f6, f5, par9);
//				this.renderPassModel.render(par1EntityLivingBase, f6, f5, f3, f1 - f, f2, f4);
//
//				if (j == 15)
//				{
//					final float f8 = par1EntityLivingBase.ticksExisted + par9;
//					bindTexture(new ResourceLocation("%blur%/misc/glint.png");
//					GL11.glEnable(GL11.GL_BLEND);
//					final float f10 = 0.5F;
//					GL11.glColor4f(f10, f10, f10, 1.0F);
//					GL11.glDepthFunc(GL11.GL_EQUAL);
//					GL11.glDepthMask(false);
//
//					for (int i1 = 0; i1 < 2; i1++)
//					{
//						GL11.glDisable(GL11.GL_LIGHTING);
//						final float f13 = 0.76F;
//						GL11.glColor4f(0.5F * f13, 0.25F * f13, 0.8F * f13, 1.0F);
//						GL11.glBlendFunc(GL11.GL_SRC_COLOR, GL11.GL_ONE);
//						GL11.glMatrixMode(GL11.GL_TEXTURE);
//						GL11.glLoadIdentity();
//						final float f15 = f8 * (0.001F + i1 * 0.003F) * 20F;
//						final float f16 = 0.3333333F;
//						GL11.glScalef(f16, f16, f16);
//						GL11.glRotatef(30F - i1 * 60F, 0.0F, 0.0F, 1.0F);
//						GL11.glTranslatef(0.0F, f15, 0.0F);
//						GL11.glMatrixMode(GL11.GL_MODELVIEW);
//						this.renderPassModel.render(par1EntityLivingBase, f6, f5, f3, f1 - f, f2, f4);
//					}
//
//					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
//					GL11.glMatrixMode(GL11.GL_TEXTURE);
//					GL11.glDepthMask(true);
//					GL11.glLoadIdentity();
//					GL11.glMatrixMode(GL11.GL_MODELVIEW);
//					GL11.glEnable(GL11.GL_LIGHTING);
//					GL11.glDisable(GL11.GL_BLEND);
//					GL11.glDepthFunc(GL11.GL_LEQUAL);
//				}
//
//				GL11.glDisable(GL11.GL_BLEND);
//				GL11.glEnable(GL11.GL_ALPHA_TEST);
//			}
//
//			this.renderEquippedItems(par1EntityLivingBase, par9);
//			final float f7 = par1EntityLivingBase.getBrightness(par9);
//			final int k = this.getColorMultiplier(par1EntityLivingBase, f7, par9);
//			OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
//			GL11.glDisable(GL11.GL_TEXTURE_2D);
//			OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
//
//			if ((k >> 24 & 0xff) > 0 || par1EntityLivingBase.hurtTime > 0 || par1EntityLivingBase.deathTime > 0)
//			{
//				GL11.glDisable(GL11.GL_TEXTURE_2D);
//				GL11.glDisable(GL11.GL_ALPHA_TEST);
//				GL11.glEnable(GL11.GL_BLEND);
//				GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
//				GL11.glDepthFunc(GL11.GL_EQUAL);
//
//				if (par1EntityLivingBase.hurtTime > 0 || par1EntityLivingBase.deathTime > 0)
//				{
//					GL11.glColor4f(f7, 0.0F, 0.0F, 0.4F);
//					this.mainModel.render(par1EntityLivingBase, f6, f5, f3, f1 - f, f2, f4);
//
//					for (int l = 0; l < 4; l++)
//					{
//						if (this.inheritRenderPass(par1EntityLivingBase, l, par9) >= 0)
//						{
//							GL11.glColor4f(f7, 0.0F, 0.0F, 0.4F);
//							this.renderPassModel.render(par1EntityLivingBase, f6, f5, f3, f1 - f, f2, f4);
//						}
//					}
//				}
//
//				if ((k >> 24 & 0xff) > 0)
//				{
//					final float f9 = (k >> 16 & 0xff) / 255F;
//					final float f11 = (k >> 8 & 0xff) / 255F;
//					final float f12 = (k & 0xff) / 255F;
//					final float f14 = (k >> 24 & 0xff) / 255F;
//					GL11.glColor4f(f9, f11, f12, f14);
//					this.mainModel.render(par1EntityLivingBase, f6, f5, f3, f1 - f, f2, f4);
//
//					for (int j1 = 0; j1 < 4; j1++)
//					{
//						if (this.inheritRenderPass(par1EntityLivingBase, j1, par9) >= 0)
//						{
//							GL11.glColor4f(f9, f11, f12, f14);
//							this.renderPassModel.render(par1EntityLivingBase, f6, f5, f3, f1 - f, f2, f4);
//						}
//					}
//				}
//
//				GL11.glDepthFunc(GL11.GL_LEQUAL);
//				GL11.glDisable(GL11.GL_BLEND);
//				GL11.glEnable(GL11.GL_ALPHA_TEST);
//				GL11.glEnable(GL11.GL_TEXTURE_2D);
//			}
//
//			GL11.glDisable(GL12.GL_RESCALE_NORMAL);
//		}
//		catch (final Exception exception)
//		{
//			exception.printStackTrace();
//		}
//
//		OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
//		GL11.glEnable(GL11.GL_TEXTURE_2D);
//		OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
//		GL11.glEnable(GL11.GL_CULL_FACE);
//		GL11.glPopMatrix();
//		this.passSpecialRender(par1EntityLivingBase, par2, par4, par6);
//	}
//
//
//	protected void renderModel(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4, float par5, float par6, float par7)
//	{
//		// SNIP
//		if(par1EntityLivingBase instanceof EntityPlayer)
//		{
//			if(SQR.isMale == 0)
//			{
//				bindTexture(new ResourceLocation("/imgz/spiderqueenskin.png");
//			}
//			else
//			{
//				bindTexture(new ResourceLocation("/imgz/spiderkingskin.png");
//			}
//		}
//		else
//		{
//			loadDownloadableImageTexture(par1EntityLivingBase.skinUrl, par1EntityLivingBase.getTexture());
//		}
//		// -SNIP
//
//		this.mainModel.render(par1EntityLivingBase, par2, par3, par4, par5, par6, par7);
//	}
//
//
//	protected void renderLivingAt(EntityLivingBase par1EntityLivingBase, double par2, double par4, double par6)
//	{
//		GL11.glTranslatef((float)par2, (float)par4, (float)par6);
//	}
//
//	protected void rotateCorpse(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4)
//	{
//		GL11.glRotatef(180F - par3, 0.0F, 1.0F, 0.0F);
//
//		if (par1EntityLivingBase.deathTime > 0)
//		{
//			float f = (par1EntityLivingBase.deathTime + par4 - 1.0F) / 20F * 1.6F;
//			f = MathHelper.sqrt_float(f);
//
//			if (f > 1.0F)
//			{
//				f = 1.0F;
//			}
//
//			GL11.glRotatef(f * this.getDeathMaxRotation(par1EntityLivingBase), 0.0F, 0.0F, 1.0F);
//		}
//	}
//
//	protected float renderSwingProgress(EntityLivingBase par1EntityLivingBase, float par2)
//	{
//		return par1EntityLivingBase.getSwingProgress(par2);
//	}
//
//
//	protected float handleRotationFloat(EntityLivingBase par1EntityLivingBase, float par2)
//	{
//		return par1EntityLivingBase.ticksExisted + par2;
//	}
//
//	protected void renderEquippedItems(EntityLivingBase entityliving, float f)
//	{
//	}
//
//	protected int inheritRenderPass(EntityLivingBase par1EntityLivingBase, int par2, float par3)
//	{
//		return this.shouldRenderPass(par1EntityLivingBase, par2, par3);
//	}
//
//
//	protected int shouldRenderPass(EntityLivingBase par1EntityLivingBase, int par2, float par3)
//	{
//		return -1;
//	}
//
//	protected float getDeathMaxRotation(EntityLivingBase par1EntityLivingBase)
//	{
//		return 90F;
//	}
//
//
//	protected int getColorMultiplier(EntityLivingBase par1EntityLivingBase, float par2, float par3)
//	{
//		return 0;
//	}
//
//
//	protected void preRenderCallback(EntityLivingBase entityliving, float f)
//	{
//	}
//
//
//	protected void passSpecialRender(EntityLivingBase par1EntityLivingBase, double par2, double par4, double par6)
//	{
//		if (!Minecraft.isDebugInfoEnabled());
//	}
//
//
//	protected void renderLivingLabel(EntityLivingBase par1EntityLivingBase, String par2Str, double par3, double par5, double par7, int par9)
//	{
//		final float f = par1EntityLivingBase.getDistanceToEntity(this.renderManager.livingPlayer);
//
//		if (f > par9)
//		{
//			return;
//		}
//
//		final FontRenderer fontrenderer = this.getFontRendererFromRenderManager();
//		final float f1 = 1.6F;
//		final float f2 = 0.01666667F * f1;
//		GL11.glPushMatrix();
//		GL11.glTranslatef((float)par3 + 0.0F, (float)par5 + 2.3F, (float)par7);
//		GL11.glNormal3f(0.0F, 1.0F, 0.0F);
//		GL11.glRotatef(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
//		GL11.glRotatef(this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
//		GL11.glScalef(-f2, -f2, f2);
//		GL11.glDisable(GL11.GL_LIGHTING);
//		GL11.glDepthMask(false);
//		GL11.glDisable(GL11.GL_DEPTH_TEST);
//		GL11.glEnable(GL11.GL_BLEND);
//		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
//		final Tessellator tessellator = Tessellator.instance;
//		byte byte0 = 0;
//
//		if (par2Str.equals("deadmau5"))
//		{
//			byte0 = -10;
//		}
//
//		GL11.glDisable(GL11.GL_TEXTURE_2D);
//		tessellator.startDrawingQuads();
//		final int i = fontrenderer.getStringWidth(par2Str) / 2;
//		tessellator.setColorRGBA_F(0.0F, 0.0F, 0.0F, 0.25F);
//		tessellator.addVertex(-i - 1, -1 + byte0, 0.0D);
//		tessellator.addVertex(-i - 1, 8 + byte0, 0.0D);
//		tessellator.addVertex(x +1, 8 + byte0, 0.0D);
//		tessellator.addVertex(x +1, -1 + byte0, 0.0D);
//		tessellator.draw();
//		GL11.glEnable(GL11.GL_TEXTURE_2D);
//		fontrenderer.drawString(par2Str, -fontrenderer.getStringWidth(par2Str) / 2, byte0, 0x20ffffff);
//		GL11.glEnable(GL11.GL_DEPTH_TEST);
//		GL11.glDepthMask(true);
//		fontrenderer.drawString(par2Str, -fontrenderer.getStringWidth(par2Str) / 2, byte0, -1);
//		GL11.glEnable(GL11.GL_LIGHTING);
//		GL11.glDisable(GL11.GL_BLEND);
//		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
//		GL11.glPopMatrix();
//	}
//
//
//	@Override
//	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
//	{
//		this.doRenderLiving((EntityLivingBase)par1Entity, par2, par4, par6, par8, par9);
//	}
//}
