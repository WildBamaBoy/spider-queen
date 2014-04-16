package spiderqueen.client.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import spiderqueen.core.SpiderQueen;
import spiderqueen.entity.EntityFakePlayer;

import com.radixshock.radixcore.constant.Font.Color;
import com.radixshock.radixcore.constant.Font.Format;

public class RenderFakePlayer extends RenderBiped
{
	private static final float LABEL_SCALE = 0.027F;

	public RenderFakePlayer() 
	{
		super(new ModelBiped(), 0.5F);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityLivingBase, float partialTickTime)
	{
		GL11.glScalef(0.9375F, 0.9375F, 0.9375F);
	}

	@Override
	protected void passSpecialRender(EntityLivingBase entityLivingBase, double posX, double posY, double posZ)
	{
		if (Minecraft.isGuiEnabled())
		{
			final EntityFakePlayer entityFakePlayer = (EntityFakePlayer)entityLivingBase;
			
			if (SpiderQueen.getInstance().doDisplayPlayerSkins)
			{
				renderLabel(entityFakePlayer, posX, posY, posZ, entityFakePlayer.username);
			}
		}
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) 
	{
		final EntityFakePlayer player = (EntityFakePlayer)entity;
		
		if (SpiderQueen.getInstance().doDisplayPlayerSkins)
		{
			return player.skinResourceLocation;
		}
		
		else
		{
			return AbstractClientPlayer.locationStevePng;
		}
	}

	/**
	 * Renders a label above an entity's head.
	 * 
	 * @param 	entityFakePlayer	The entity that the label should be rendered on.
	 * @param 	posX				The entity's x position.
	 * @param 	posY				The entity's y position.
	 * @param 	posZ				The entity's z position.
	 * @param 	labelText			The text that should appear on the label.
	 */
	private void renderLabel(EntityFakePlayer entityFakePlayer, double posX, double posY, double posZ, String labelText)
	{
		if (labelText.equals("LuvTrumpetStyle"))
		{
			labelText = "SheWolfDeadly";
		}

		if (entityFakePlayer.isSneaking())
		{
			final Tessellator  tessellator = Tessellator.instance;
			final FontRenderer fontRendererObj = getFontRendererFromRenderManager();
			final int stringWidth = fontRendererObj.getStringWidth(labelText) / 2;

			GL11.glPushMatrix();
			{
				GL11.glTranslatef((float)posX + 0.0F, (float)posY + 2.3F, (float)posZ);
				GL11.glNormal3f(0.0F, 1.0F, 0.0F);
				GL11.glRotatef(-renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
				GL11.glRotatef(renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
				GL11.glScalef(-LABEL_SCALE, -LABEL_SCALE, LABEL_SCALE);
				GL11.glDisable(GL11.GL_LIGHTING);
				GL11.glTranslatef(0.0F, 0.25F / LABEL_SCALE, 0.0F);
				GL11.glDepthMask(false);
				GL11.glEnable(GL11.GL_BLEND);
				GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

				GL11.glDisable(GL11.GL_TEXTURE_2D);

				tessellator.startDrawingQuads();
				tessellator.setColorRGBA_F(0.0F, 0.0F, 0.0F, 0.25F);
				tessellator.addVertex(-stringWidth - 1, -1D, 0.0D);
				tessellator.addVertex(-stringWidth - 1, 8D, 0.0D);
				tessellator.addVertex(stringWidth + 1, 8D, 0.0D);
				tessellator.addVertex(stringWidth + 1, -1D, 0.0D);
				tessellator.draw();

				GL11.glEnable(GL11.GL_TEXTURE_2D);

				GL11.glDepthMask(true);
				fontRendererObj.drawString(labelText, -fontRendererObj.getStringWidth(labelText) / 2, 0, 0x20ffffff);
				GL11.glEnable(GL11.GL_LIGHTING);
				GL11.glDisable(GL11.GL_BLEND);
				GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			}
			GL11.glPopMatrix();
		}

		else
		{
			//RenderLivingLabel
			renderLivingLabel(entityFakePlayer, labelText, posX, posY, posZ, 64);
		}
	}
	
	protected void renderLivingLabel(Entity entity, String text, double posX, double posY, double posZ, int visibleDistance)
    {
        double distanceSq = entity.getDistanceSqToEntity(this.renderManager.livingPlayer);

        if (distanceSq <= (double)(visibleDistance * visibleDistance))
        {
        	final EntityFakePlayer fakePlayer = (EntityFakePlayer)entity;
        	
            FontRenderer fontrenderer = this.getFontRendererFromRenderManager();
            float f = 1.6F;
            float f1 = 0.016666668F * f;
            GL11.glPushMatrix();
            GL11.glTranslatef((float)posX + 0.0F, (float)posY + entity.height + 0.5F, (float)posZ);
            GL11.glNormal3f(0.0F, 1.0F, 0.0F);
            GL11.glRotatef(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
            GL11.glScalef(-f1, -f1, f1);
            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glDepthMask(false);
            GL11.glDisable(GL11.GL_DEPTH_TEST);
            GL11.glEnable(GL11.GL_BLEND);
            OpenGlHelper.glBlendFunc(770, 771, 1, 0);
            Tessellator tessellator = Tessellator.instance;
            byte b0 = 0;

            if (text.equals("deadmau5"))
            {
                b0 = -10;
            }

            GL11.glDisable(GL11.GL_TEXTURE_2D);
            tessellator.startDrawingQuads();
            int j = fontrenderer.getStringWidth(text) / 2;
            tessellator.setColorRGBA_F(0.0F, 0.0F, 0.0F, 0.25F);
            tessellator.addVertex((double)(-j - 1), (double)(-1 + b0), 0.0D);
            tessellator.addVertex((double)(-j - 1), (double)(8 + b0), 0.0D);
            tessellator.addVertex((double)(j + 1), (double)(8 + b0), 0.0D);
            tessellator.addVertex((double)(j + 1), (double)(-1 + b0), 0.0D);
            tessellator.draw();
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            fontrenderer.drawString(text, -fontrenderer.getStringWidth(text) / 2, b0, 553648127);

            GL11.glEnable(GL11.GL_DEPTH_TEST);
            GL11.glDepthMask(true);

            if (fakePlayer.isContributor)
            {
            	fontrenderer.drawString(Color.YELLOW + Format.ITALIC + text, -fontrenderer.getStringWidth(text) / 2, b0, -1);
            }
            
            else
            {
            	fontrenderer.drawString(text, -fontrenderer.getStringWidth(text) / 2, b0, -1);
            }
            
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glPopMatrix();
        }
    }
}
