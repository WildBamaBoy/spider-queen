package sq.client.render;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import radixcore.util.RadixMath;
import sq.core.SpiderCore;
import sq.entity.creature.EntityHuman;

/**
 * Sets the texture on the human model pre-render and renders armor/weapons.
 */
public class RenderHuman<T extends EntityHuman> extends RenderBiped<T>
{
    private static final ResourceLocation DEFAULT_RES_LOC = new ResourceLocation("textures/entity/steve.png");
	private final ModelBiped modelArmorPlate;
	private final ModelBiped modelArmor;
	
	public RenderHuman() 
	{
		super(Minecraft.getMinecraft().getRenderManager(), new ModelBiped(0.0F), 0.5F);

		modelBipedMain = (ModelBiped) mainModel;
		modelArmorPlate = new ModelBiped(1.0F);
		modelArmor = new ModelBiped(0.5F);
	}

	@Override
	protected ResourceLocation getEntityTexture(T entity)
	{
		final EntityHuman player = (EntityHuman) entity;

		//If the player enabled contributor skins, show it.
		if (SpiderCore.getConfig().showHumanSkin)
		{
			return player.getSkinResourceLocation();
		}

		else //Otherwise, show Steve.
		{
			return DEFAULT_RES_LOC;
		}
	}

	@Override
	public void doRender(T entity, double posX, double posY, double posZ, float rotationYaw, float rotationPitch)
	{
		passSpecialRender(entity, posX, posY, posZ);
		renderHuman((EntityHuman) entity, posX, posY, posZ, rotationYaw, rotationPitch);
	}

	@Override
	protected void preRenderCallback(T entityLivingBase, float partialTickTime)
	{
		//Scale down to the size of the player.
		GL11.glScalef(0.9375F, 0.9375F, 0.9375F);
	}

	protected void passSpecialRender(T entityLivingBase, double posX, double posY, double posZ)
	{
		if (Minecraft.isGuiEnabled())
		{
			final EntityHuman entity = (EntityHuman) entityLivingBase;
			
			double distance = RadixMath.getDistanceToEntity(entity, Minecraft.getMinecraft().thePlayer);
			
			//Show the human's name and type if enabled and the closest player is within 6 blocks.
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
		double posYCorrection = posY - entity.getYOffset();

		shadowOpaque = 1.0F;

		final ItemStack heldItem = entity.getHeldItem();
		modelArmorPlate.heldItemRight = modelArmor.heldItemRight = modelBipedMain.heldItemRight = heldItem == null ? 0 : 1;
		modelArmorPlate.isSneak = modelArmor.isSneak = modelBipedMain.isSneak = entity.isSneaking();

		if (heldItem != null)
		{
			final EnumAction useAction = heldItem.getItemUseAction();

			if (useAction == EnumAction.BOW)
			{
				modelArmorPlate.aimedBow = modelArmor.aimedBow = modelBipedMain.aimedBow = true;
			}
		}

		if (entity.isSneaking())
		{
			posYCorrection -= 0.125D;
		}

		super.doRender((T)entity, posX, posYCorrection, posZ, rotationYaw, rotationPitch);
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

		renderLivingLabel((T)entityFakePlayer, labelText, posX, posY, posZ, 64);
	}
}
