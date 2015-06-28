package sq.client.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import radixcore.util.RadixMath;
import sq.core.SpiderCore;
import sq.entity.EntityHuman;

public class RenderHuman extends RenderBiped
{
	private final ModelBiped modelArmorPlate;
	private final ModelBiped modelArmor;
	
	public RenderHuman() 
	{
		super(new ModelBiped(0.0F), 0.5F);

		modelBipedMain = (ModelBiped) mainModel;
		modelArmorPlate = new ModelBiped(1.0F);
		modelArmor = new ModelBiped(0.5F);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		final EntityHuman player = (EntityHuman) entity;

		if (SpiderCore.getConfig().showHumanSkin)
		{
			return player.getSkinResourceLocation();
		}

		else
		{
			return AbstractClientPlayer.locationStevePng;
		}
	}

	@Override
	public void doRender(Entity entity, double posX, double posY, double posZ, float rotationYaw, float rotationPitch)
	{
		renderHuman((EntityHuman) entity, posX, posY, posZ, rotationYaw, rotationPitch);
	}

	@Override
	public void doRender(EntityLiving entityLiving, double posX, double posY, double posZ, float rotationYaw, float rotationPitch)
	{
		renderHuman((EntityHuman) entityLiving, posX, posY, posZ, rotationYaw, rotationPitch);
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
			final EntityHuman entity = (EntityHuman) entityLivingBase;
			
			double distance = RadixMath.getDistanceToEntity(entity, Minecraft.getMinecraft().thePlayer);
			
			if (distance < 6.0D)
			{
				if (SpiderCore.getConfig().showHumanName)
				{
					renderLabel(entity, posX, posY + 0.05F, posZ, entity.getUsername());
				}
				
				if (SpiderCore.getConfig().showHumanType)
				{
					renderLabel(entity, posX, posY - 0.20F, posZ, entity.getFortuneString());
				}
			}
		}
	}

	private void renderHuman(EntityHuman entity, double posX, double posY, double posZ, float rotationYaw, float rotationPitch)
	{
		double posYCorrection = posY - entity.yOffset;

		shadowOpaque = 1.0F;

		final ItemStack heldItem = entity.getHeldItem();
		modelArmorPlate.heldItemRight = modelArmor.heldItemRight = modelBipedMain.heldItemRight = heldItem == null ? 0 : 1;
		modelArmorPlate.isSneak = modelArmor.isSneak = modelBipedMain.isSneak = entity.isSneaking();

		if (heldItem != null)
		{
			final EnumAction useAction = heldItem.getItemUseAction();

			if (useAction == EnumAction.bow)
			{
				modelArmorPlate.aimedBow = modelArmor.aimedBow = modelBipedMain.aimedBow = true;
			}
		}

		if (entity.isSneaking())
		{
			posYCorrection -= 0.125D;
		}

		super.doRender(entity, posX, posYCorrection, posZ, rotationYaw, rotationPitch);
		modelArmorPlate.aimedBow = modelArmor.aimedBow = modelBipedMain.aimedBow = false;
		modelArmorPlate.isSneak = modelArmor.isSneak = modelBipedMain.isSneak = false;
		modelArmorPlate.heldItemRight = modelArmor.heldItemRight = modelBipedMain.heldItemRight = 0;
	}

	/**
	 * Renders a label above an entity's head.
	 * 
	 * @param entityFakePlayer
	 *            The entity that the label should be rendered on.
	 * @param posX
	 *            The entity's x position.
	 * @param posY
	 *            The entity's y position.
	 * @param posZ
	 *            The entity's z position.
	 * @param labelText
	 *            The text that should appear on the label.
	 */
	private void renderLabel(EntityHuman entityFakePlayer, double posX, double posY, double posZ, String labelText)
	{
		if (labelText.equals("LuvTrumpetStyle"))
		{
			labelText = "SheWolfDeadly";
		}

		renderLivingLabel(entityFakePlayer, labelText, posX, posY, posZ, 64);
	}

	protected void renderLivingLabel(Entity entity, String text, double posX, double posY, double posZ, int visibleDistance)
	{
		final double distanceSq = entity.getDistanceSqToEntity(renderManager.livingPlayer);

		if (distanceSq <= visibleDistance * visibleDistance)
		{
			final EntityHuman fakePlayer = (EntityHuman) entity;
			final FontRenderer fontRenderer = getFontRendererFromRenderManager();
			final float labelScale = 0.0268F;
			
			GL11.glPushMatrix();
			{
				GL11.glTranslatef((float) posX + 0.0F, (float) posY + entity.height + 0.5F, (float) posZ);
				GL11.glNormal3f(0.0F, 1.0F, 0.0F);
				GL11.glRotatef(-renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
				GL11.glRotatef(renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
				GL11.glScalef(-labelScale, -labelScale, labelScale);
				GL11.glDisable(GL11.GL_LIGHTING);
				GL11.glDepthMask(false);
				GL11.glDisable(GL11.GL_DEPTH_TEST);
				GL11.glEnable(GL11.GL_BLEND);
				
				OpenGlHelper.glBlendFunc(770, 771, 1, 0);

				GL11.glDisable(GL11.GL_TEXTURE_2D);
				
				final Tessellator tessellator = Tessellator.instance;
				tessellator.startDrawingQuads();
				tessellator.setColorRGBA_F(0.0F, 0.0F, 0.0F, 0.25F);
				
				final int halfStringWidth = fontRenderer.getStringWidth(text) / 2;
				tessellator.addVertex(-halfStringWidth - 1, -1, 0.0D);
				tessellator.addVertex(-halfStringWidth - 1, 8, 0.0D);
				tessellator.addVertex(halfStringWidth + 1, 8, 0.0D);
				tessellator.addVertex(halfStringWidth + 1, -1, 0.0D);
				tessellator.draw();
				
				GL11.glEnable(GL11.GL_TEXTURE_2D);
				fontRenderer.drawString(text, -fontRenderer.getStringWidth(text) / 2, 0, 553648127);

				GL11.glEnable(GL11.GL_DEPTH_TEST);
				GL11.glDepthMask(true);

				//TODO
//				if (fakePlayer.isContributor)
//				{
//					fontRenderer.drawString(Color.YELLOW + Format.ITALIC + text, -fontRenderer.getStringWidth(text) / 2, 0, -1);
//				}
//
//				else
//				{
					fontRenderer.drawString(text, -fontRenderer.getStringWidth(text) / 2, 0, -1);
//				}

				GL11.glEnable(GL11.GL_LIGHTING);
				GL11.glDisable(GL11.GL_BLEND);
				GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			}
			GL11.glPopMatrix();
		}
	}
}
