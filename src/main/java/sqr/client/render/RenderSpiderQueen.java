/*******************************************************************************
 * RenderSpiderQueen.java
 * Copyright (c) 2014 Radix-Shock Entertainment.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/

package sqr.client.render;

import static net.minecraftforge.client.IItemRenderer.ItemRenderType.EQUIPPED;
import static net.minecraftforge.client.IItemRenderer.ItemRendererHelper.BLOCK_3D;

import java.util.UUID;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.tileentity.TileEntitySkullRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.common.MinecraftForge;

import org.lwjgl.opengl.GL11;

import sqr.client.model.ModelSpiderQueen;
import sqr.core.ModPropertiesList;
import sqr.core.SpiderQueen;

import com.mojang.authlib.GameProfile;

public class RenderSpiderQueen extends RenderPlayer
{
	private static final ResourceLocation	steveTextures	= new ResourceLocation("textures/entity/steve.png");
	private final ModelSpiderQueen			modelBipedMain;
	private final ModelBiped				modelFirstPerson;
	
	public RenderSpiderQueen()
	{
		super();
		modelBipedMain = new ModelSpiderQueen();
		modelFirstPerson = new ModelBiped();
		mainModel = modelBipedMain;
	}

	@Override
	protected int shouldRenderPass(AbstractClientPlayer clientPlayer, int renderPass, float partialTickTime)
	{
		return -1;
	}

	@Override
	protected void func_82408_c(AbstractClientPlayer clientPlayer, int renderPass, float partialTickTime)
	{
		return;
	}

	@Override
	public void doRender(AbstractClientPlayer clientPlayer, double posX, double posY, double posZ, float rotationYaw, float rotationPitch)
	{
		GL11.glColor3f(1.0F, 1.0F, 1.0F);

		if (clientPlayer.isSneaking() && !(clientPlayer instanceof EntityPlayerSP))
		{
		}

		GL11.glPushMatrix();
		{
			final EntityLivingBase entity = clientPlayer;

			GL11.glDisable(GL11.GL_CULL_FACE);
			mainModel.onGround = renderSwingProgress(entity, rotationPitch);

			if (renderPassModel != null)
			{
				renderPassModel.onGround = mainModel.onGround;
			}

			mainModel.isRiding = entity.isRiding();

			if (renderPassModel != null)
			{
				renderPassModel.isRiding = mainModel.isRiding;
			}

			if (clientPlayer != Minecraft.getMinecraft().thePlayer)
			{
				GL11.glTranslatef(0, 1.7F, 0);
			}

			try
			{
				final float unknownConstant = -0.0925F;

				final float realRotationPitch = entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * rotationPitch;
				float realRenderYaw = interpolateRotation(entity.prevRenderYawOffset, entity.renderYawOffset, rotationPitch);
				final float realRenderYawHead = interpolateRotation(entity.prevRotationYawHead, entity.rotationYawHead, rotationPitch);
				float wrappedRotation = handleRotationFloat(entity, rotationPitch);

				if (entity.isRiding() && entity.ridingEntity instanceof EntityLivingBase)
				{
					final EntityLivingBase entityRiding = (EntityLivingBase) entity.ridingEntity;
					realRenderYaw = interpolateRotation(entityRiding.prevRenderYawOffset, entityRiding.renderYawOffset, rotationPitch);
					wrappedRotation = MathHelper.wrapAngleTo180_float(realRenderYawHead - realRenderYaw);

					if (wrappedRotation < -85.0F)
					{
						wrappedRotation = -85.0F;
					}

					if (wrappedRotation >= 85.0F)
					{
						wrappedRotation = 85.0F;
					}

					realRenderYaw = realRenderYawHead - wrappedRotation;

					if (wrappedRotation * wrappedRotation > 2500.0F)
					{
						realRenderYaw += wrappedRotation * 0.2F;
					}
				}

				renderLivingAt(entity, posX, posY, posZ);
				rotateCorpse(entity, wrappedRotation, realRenderYaw, rotationPitch);
				
				GL11.glTranslatef(0.0F, -0.10F, 0.0F); // Move the model down
				// slightly so that it
				// touches the ground.
				GL11.glScalef(0.7F, 0.7F, -0.7F); // Scale and flip the new
				// model.

				float limbSwing = entity.prevLimbSwingAmount + (entity.limbSwingAmount - entity.prevLimbSwingAmount) * rotationPitch;
				final float limbAngle = entity.limbSwing - entity.limbSwingAmount * (1.0F - rotationPitch);

				if (limbSwing > 1.0F)
				{
					limbSwing = 1.0F;
				}

				mainModel.setLivingAnimations(entity, limbAngle, limbSwing, rotationPitch);
				renderModel(entity, limbAngle, limbSwing, wrappedRotation, realRenderYawHead - realRenderYaw, realRotationPitch, unknownConstant);
				try
				{
					renderEquippedItems(entity, limbSwing);
				}
				
				catch (Throwable e)
				{
					e.printStackTrace();
				}
				
				final float brightness = entity.getBrightness(rotationPitch);
				final int colorMultiplier = getColorMultiplier(entity, brightness, rotationPitch);

				if ((colorMultiplier >> 24 & 255) > 0 || entity.hurtTime > 0 || entity.deathTime > 0)
				{
					GL11.glDisable(GL11.GL_TEXTURE_2D);
					GL11.glDisable(GL11.GL_ALPHA_TEST);
					GL11.glEnable(GL11.GL_BLEND);
					GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
					GL11.glDepthFunc(GL11.GL_EQUAL);

					if (entity.hurtTime > 0 || entity.deathTime > 0)
					{
						GL11.glColor4f(brightness, 0.0F, 0.0F, 0.4F);
						mainModel.render(entity, limbAngle, limbSwing, wrappedRotation, realRenderYawHead - realRenderYaw, realRotationPitch, unknownConstant);
					}

					if ((colorMultiplier >> 24 & 255) > 0)
					{
						final float colorR = (colorMultiplier >> 16 & 255) / 255.0F;
						final float colorG = (colorMultiplier >> 8 & 255) / 255.0F;
						final float colorB = (colorMultiplier & 255) / 255.0F;
						final float colorA = (colorMultiplier >> 24 & 255) / 255.0F;
						GL11.glColor4f(colorR, colorG, colorB, colorA);
						mainModel.render(entity, limbAngle, limbSwing, wrappedRotation, realRenderYawHead - realRenderYaw, realRotationPitch, unknownConstant);
					}

					GL11.glDepthFunc(GL11.GL_LEQUAL);
					GL11.glDisable(GL11.GL_BLEND);
					GL11.glEnable(GL11.GL_ALPHA_TEST);
					GL11.glEnable(GL11.GL_TEXTURE_2D);
				}
			}

			catch (final Exception exception)
			{
				System.out.println("Failure to render entity. " + exception.getMessage());
			}
		}
		GL11.glPopMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(AbstractClientPlayer clientPlayer)
	{
		return clientPlayer.getLocationSkin();
	}

	@Override
	protected void preRenderCallback(AbstractClientPlayer clientPlayer, float partialTickTime)
	{
		return;
	}

	@Override
	protected void func_96449_a(AbstractClientPlayer clientPlayer, double posX, double posY, double posZ, String string, float unknownFloat, double unknownDouble)
	{
		return;
	}

	@Override
	public void renderFirstPersonArm(EntityPlayer entityPlayer)
	{
		GL11.glColor3f(1.0F, 1.0F, 1.0F);
		modelBipedMain.onGround = 0.0F;
		modelBipedMain.setRotationAngles(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, entityPlayer);

		GL11.glPushMatrix();
		{
			GL11.glScalef(0.7F, 0.9F, 0.7F);
			GL11.glTranslated(-0.3D, 0.1D, 0.0D);
			
			try
			{
				final ModPropertiesList modPropertiesList = SpiderQueen.getInstance().getModProperties();
				bindTexture(new ResourceLocation("spiderqueen:textures/entity/Arms" + modPropertiesList.spiderSkin.substring(6) + ".png"));
			}
			
			catch (NullPointerException e)
			{
				//Happens sometimes just when the player is logging in.
			}
			
			modelFirstPerson.bipedRightArm.render(0.0625F);
		}
		GL11.glPopMatrix();
	}

	@Override
	protected void renderLivingAt(AbstractClientPlayer clientPlayer, double posX, double posY, double posZ)
	{
		if (clientPlayer.isEntityAlive() && clientPlayer.isPlayerSleeping())
		{
			super.renderLivingAt(clientPlayer, posX + clientPlayer.field_71079_bU, posY + clientPlayer.field_71082_cx, posZ + clientPlayer.field_71089_bV);
		}
		else
		{
			super.renderLivingAt(clientPlayer, posX, posY, posZ);
		}
	}

	@Override
	protected void rotateCorpse(AbstractClientPlayer clientPlayer, float unknownFloat1, float unknownFloat2, float unknownFloat3)
	{
		if (clientPlayer.isEntityAlive() && clientPlayer.isPlayerSleeping())
		{
			GL11.glRotatef(clientPlayer.getBedOrientationInDegrees(), 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(getDeathMaxRotation(clientPlayer), 0.0F, 0.0F, 1.0F);
			GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
		}
		else
		{
			super.rotateCorpse(clientPlayer, unknownFloat1, unknownFloat2, unknownFloat3);
		}
	}

	@Override
	protected void func_96449_a(EntityLivingBase entityLivingBase, double posX, double posY, double posZ, String string, float unknownFloat, double unknownDouble)
	{
		return;
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityLivingBase, float partialTickTime)
	{
		return;
	}

	@Override
	protected void func_82408_c(EntityLivingBase entityLivingBase, int renderPass, float partialTickTime)
	{
		return;
	}

	@Override
	protected int shouldRenderPass(EntityLivingBase entityLivingBase, int renderPass, float partialTickTime)
	{
		return -1;
	}

	@Override
	public void renderEquippedItems(AbstractClientPlayer clientPlayer, float partialTickTime)
	{
		RenderPlayerEvent.Specials.Pre event = new RenderPlayerEvent.Specials.Pre(clientPlayer, this, partialTickTime);

		if (MinecraftForge.EVENT_BUS.post(event))
        {
            return;
        }

        GL11.glColor3f(1.0F, 1.0F, 1.0F);
        GL11.glScaled(-1, -1, -1);
        GL11.glTranslated(-0.05D, 0.3D, -0.15D);
        super.renderArrowsStuckInEntity(clientPlayer, partialTickTime);
        ItemStack itemstack = clientPlayer.inventory.armorItemInSlot(3);

        if (itemstack != null && event.renderHelmet)
        {
            GL11.glPushMatrix();
            this.modelBipedMain.head.postRender(0.0625F);
            float f1;

            if (itemstack.getItem() instanceof ItemBlock)
            {
                IItemRenderer customRenderer = MinecraftForgeClient.getItemRenderer(itemstack, EQUIPPED);
                boolean is3D = (customRenderer != null && customRenderer.shouldUseRenderHelper(EQUIPPED, itemstack, BLOCK_3D));

                if (is3D || RenderBlocks.renderItemIn3d(Block.getBlockFromItem(itemstack.getItem()).getRenderType()))
                {
                    f1 = 0.625F;
                    GL11.glTranslatef(0.0F, -0.25F, 0.0F);
                    GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScalef(f1, -f1, -f1);
                }

                this.renderManager.itemRenderer.renderItem(clientPlayer, itemstack, 0);
            }
            else if (itemstack.getItem() == Items.skull)
            {
                f1 = 1.0625F;
                GL11.glScalef(f1, -f1, -f1);
                GameProfile gameprofile = null;

                if (itemstack.hasTagCompound())
                {
                    NBTTagCompound nbttagcompound = itemstack.getTagCompound();

                    if (nbttagcompound.hasKey("SkullOwner", 10))
                    {
                        gameprofile = NBTUtil.func_152459_a(nbttagcompound.getCompoundTag("SkullOwner"));
                    }
                    else if (nbttagcompound.hasKey("SkullOwner", 8) && !StringUtils.isNullOrEmpty(nbttagcompound.getString("SkullOwner")))
                    {
                        gameprofile = new GameProfile((UUID)null, nbttagcompound.getString("SkullOwner"));
                    }
                }

                TileEntitySkullRenderer.field_147536_b.func_152674_a(-0.5F, 0.0F, -0.5F, 1, 180.0F, itemstack.getItemDamage(), gameprofile);
            }

            GL11.glPopMatrix();
        }

        float f3;

        if (clientPlayer.getCommandSenderName().equals("deadmau5") && clientPlayer.func_152123_o())
        {
            this.bindTexture(clientPlayer.getLocationSkin());

            for (int j = 0; j < 2; ++j)
            {
                float f10 = clientPlayer.prevRotationYaw + (clientPlayer.rotationYaw - clientPlayer.prevRotationYaw) * partialTickTime - (clientPlayer.prevRenderYawOffset + (clientPlayer.renderYawOffset - clientPlayer.prevRenderYawOffset) * partialTickTime);
                float f2 = clientPlayer.prevRotationPitch + (clientPlayer.rotationPitch - clientPlayer.prevRotationPitch) * partialTickTime;
                GL11.glPushMatrix();
                GL11.glRotatef(f10, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(f2, 1.0F, 0.0F, 0.0F);
                GL11.glTranslatef(0.375F * (float)(j * 2 - 1), 0.0F, 0.0F);
                GL11.glTranslatef(0.0F, -0.375F, 0.0F);
                GL11.glRotatef(-f2, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(-f10, 0.0F, 1.0F, 0.0F);
                f3 = 1.3333334F;
                GL11.glScalef(f3, f3, f3);
                GL11.glPopMatrix();
            }
        }

        boolean flag = clientPlayer.func_152122_n();
        flag = event.renderCape && flag;
        float f5;

        if (flag && !clientPlayer.isInvisible() && !clientPlayer.getHideCape())
        {
            this.bindTexture(clientPlayer.getLocationCape());
            GL11.glPushMatrix();
            GL11.glTranslatef(0.0F, 0.0F, 0.125F);
            double d3 = clientPlayer.field_71091_bM + (clientPlayer.field_71094_bP - clientPlayer.field_71091_bM) * (double)partialTickTime - (clientPlayer.prevPosX + (clientPlayer.posX - clientPlayer.prevPosX) * (double)partialTickTime);
            double d4 = clientPlayer.field_71096_bN + (clientPlayer.field_71095_bQ - clientPlayer.field_71096_bN) * (double)partialTickTime - (clientPlayer.prevPosY + (clientPlayer.posY - clientPlayer.prevPosY) * (double)partialTickTime);
            double d0 = clientPlayer.field_71097_bO + (clientPlayer.field_71085_bR - clientPlayer.field_71097_bO) * (double)partialTickTime - (clientPlayer.prevPosZ + (clientPlayer.posZ - clientPlayer.prevPosZ) * (double)partialTickTime);
            f5 = clientPlayer.prevRenderYawOffset + (clientPlayer.renderYawOffset - clientPlayer.prevRenderYawOffset) * partialTickTime;
            double d1 = (double)MathHelper.sin(f5 * (float)Math.PI / 180.0F);
            double d2 = (double)(-MathHelper.cos(f5 * (float)Math.PI / 180.0F));
            float f6 = (float)d4 * 10.0F;

            if (f6 < -6.0F)
            {
                f6 = -6.0F;
            }

            if (f6 > 32.0F)
            {
                f6 = 32.0F;
            }

            float f7 = (float)(d3 * d1 + d0 * d2) * 100.0F;
            float f8 = (float)(d3 * d2 - d0 * d1) * 100.0F;

            if (f7 < 0.0F)
            {
                f7 = 0.0F;
            }

            float f9 = clientPlayer.prevCameraYaw + (clientPlayer.cameraYaw - clientPlayer.prevCameraYaw) * partialTickTime;
            f6 += MathHelper.sin((clientPlayer.prevDistanceWalkedModified + (clientPlayer.distanceWalkedModified - clientPlayer.prevDistanceWalkedModified) * partialTickTime) * 6.0F) * 32.0F * f9;

            if (clientPlayer.isSneaking())
            {
                f6 += 25.0F;
            }

            GL11.glRotatef(6.0F + f7 / 2.0F + f6, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(f8 / 2.0F, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(-f8 / 2.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
            GL11.glPopMatrix();
        }

        ItemStack itemstack1 = clientPlayer.inventory.getCurrentItem();

        if (itemstack1 != null && event.renderItem)
        {
            GL11.glPushMatrix();
            this.modelBipedMain.armRight.postRender(0.0625F);
            GL11.glTranslatef(-0.0625F, 0.4375F, 0.0625F);

            if (clientPlayer.fishEntity != null)
            {
                itemstack1 = new ItemStack(Items.stick);
            }

            EnumAction enumaction = null;

            if (clientPlayer.getItemInUseCount() > 0)
            {
                enumaction = itemstack1.getItemUseAction();
            }

            IItemRenderer customRenderer = MinecraftForgeClient.getItemRenderer(itemstack1, EQUIPPED);
            boolean is3D = (customRenderer != null && customRenderer.shouldUseRenderHelper(EQUIPPED, itemstack1, BLOCK_3D));

            if (is3D || itemstack1.getItem() instanceof ItemBlock && RenderBlocks.renderItemIn3d(Block.getBlockFromItem(itemstack1.getItem()).getRenderType()))
            {
                f3 = 0.5F;
                GL11.glTranslatef(0.0F, 0.1875F, -0.3125F);
                f3 *= 0.75F;
                GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScalef(-f3, -f3, f3);
            }
            else if (itemstack1.getItem() == Items.bow)
            {
                f3 = 0.625F;
                GL11.glTranslatef(0.0F, 0.125F, 0.3125F);
                GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScalef(f3, -f3, f3);
                GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
            }
            else if (itemstack1.getItem().isFull3D())
            {
                f3 = 0.625F;

                if (itemstack1.getItem().shouldRotateAroundWhenRendering())
                {
                    GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
                    GL11.glTranslatef(0.0F, -0.125F, 0.0F);
                }

                if (clientPlayer.getItemInUseCount() > 0 && enumaction == EnumAction.block)
                {
                    GL11.glTranslatef(0.05F, 0.0F, -0.1F);
                    GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glRotatef(-60.0F, 0.0F, 0.0F, 1.0F);
                }

                GL11.glTranslatef(0.0F, 0.1875F, 0.0F);
                GL11.glScalef(f3, -f3, f3);
                GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
            }
            else
            {
                f3 = 0.375F;
                GL11.glTranslatef(0.25F, 0.1875F, -0.1875F);
                GL11.glScalef(f3, f3, f3);
                GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
                GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
            }

            float f4;
            float f12;
            int k;

            if (itemstack1.getItem().requiresMultipleRenderPasses())
            {
                for (k = 0; k <= itemstack1.getItem().getRenderPasses(itemstack1.getItemDamage()); ++k)
                {
                    int i = itemstack1.getItem().getColorFromItemStack(itemstack1, k);
                    f12 = (float)(i >> 16 & 255) / 255.0F;
                    f4 = (float)(i >> 8 & 255) / 255.0F;
                    f5 = (float)(i & 255) / 255.0F;
                    GL11.glColor4f(f12, f4, f5, 1.0F);
                    this.renderManager.itemRenderer.renderItem(clientPlayer, itemstack1, k);
                }
            }
            else
            {
                k = itemstack1.getItem().getColorFromItemStack(itemstack1, 0);
                float f11 = (float)(k >> 16 & 255) / 255.0F;
                f12 = (float)(k >> 8 & 255) / 255.0F;
                f4 = (float)(k & 255) / 255.0F;
                GL11.glColor4f(f11, f12, f4, 1.0F);
                this.renderManager.itemRenderer.renderItem(clientPlayer, itemstack1, 0);
            }

            GL11.glPopMatrix();
        }
        MinecraftForge.EVENT_BUS.post(new RenderPlayerEvent.Specials.Post(clientPlayer, this, partialTickTime));
	}
	
	@Override
	protected void renderEquippedItems(EntityLivingBase entityLivingBase, float partialTickTime)
	{
		this.renderEquippedItems((AbstractClientPlayer)entityLivingBase, partialTickTime);
	}

	@Override
	protected void rotateCorpse(EntityLivingBase entityLivingBase, float unknownFloat1, float unknownFloat2, float unknownFloat3)
	{
		rotateCorpse((AbstractClientPlayer) entityLivingBase, unknownFloat1, unknownFloat2, unknownFloat3);
	}

	@Override
	protected void renderLivingAt(EntityLivingBase entityLivingBase, double posX, double posY, double posZ)
	{
		renderLivingAt((AbstractClientPlayer) entityLivingBase, posX, posY, posZ);
	}

	@Override
	public void doRender(EntityLivingBase entityLivingBase, double posX, double posY, double posZ, float rotationYaw, float rotationPitch)
	{
		super.doRender((AbstractClientPlayer) entityLivingBase, posX, posY, posZ, rotationYaw, rotationPitch);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return getEntityTexture((AbstractClientPlayer) entity);
	}

	@Override
	public void doRender(Entity entity, double posX, double posY, double posZ, float rotationYaw, float rotationPitch)
	{
		this.doRender((AbstractClientPlayer) entity, posX, posY, posZ, rotationYaw, rotationPitch);
	}

	private float interpolateRotation(float unknownFloat1, float unknownFloat2, float unknownFloat3)
	{
		float multiplier;

		for (multiplier = unknownFloat2 - unknownFloat1; multiplier < -180.0F; multiplier += 360.0F)
		{
			;
		}

		while (multiplier >= 180.0F)
		{
			multiplier -= 360.0F;
		}

		return unknownFloat1 + unknownFloat3 * multiplier;
	}

	@Override
	protected void bindEntityTexture(Entity entity)
	{
		final EntityPlayer player = (EntityPlayer)entity;
		final ModPropertiesList modPropertiesList = SpiderQueen.getInstance().getModProperties();
		
		bindTexture(new ResourceLocation("spiderqueen:textures/entity/" + modPropertiesList.spiderSkin + ".png"));
	}
}
