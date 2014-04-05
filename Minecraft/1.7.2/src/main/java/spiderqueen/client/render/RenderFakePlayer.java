package spiderqueen.client.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import spiderqueen.entity.EntityFakePlayer;

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
			renderLabel(entityFakePlayer, posX, posY, posZ, entityFakePlayer.username);
		}
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity par1Entity) 
	{
		final EntityFakePlayer player = (EntityFakePlayer)par1Entity;
		return player.skinResourceLocation;
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

			GL11.glPopMatrix();
		}

		else
		{
			//RenderLivingLabel
			func_147906_a(entityFakePlayer, labelText, posX, posY, posZ, 64);
		}
	}
}
