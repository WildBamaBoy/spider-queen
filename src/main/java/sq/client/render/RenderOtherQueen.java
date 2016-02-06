package sq.client.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.util.ResourceLocation;
import sq.client.model.ModelSpiderQueen;
import sq.entity.creature.EntitySpiderQueen;

/**
 * Sets the texture on the other spider queens' model pre-render.
 */
public class RenderOtherQueen<T extends EntitySpiderQueen> extends RenderLiving<T>
{
	private final ModelSpiderQueen	modelBipedMain;

	public RenderOtherQueen()
	{
		super(Minecraft.getMinecraft().getRenderManager(), new ModelSpiderQueen(), 0.0F);
		modelBipedMain = new ModelSpiderQueen();
	}

//	@Override
//	protected void func_96449_a(EntityLivingBase entityLivingBase, double posX, double posY, double posZ, String string, float unknownFloat, double unknownDouble)
//	{
//		return;
//	}
//
//	@Override
//	protected void preRenderCallback(EntityLivingBase entityLivingBase, float partialTickTime)
//	{
//		return;
//	}
//
//	@Override
//	protected void func_82408_c(EntityLivingBase entityLivingBase, int renderPass, float partialTickTime)
//	{
//		return;
//	}
//
//	@Override
//	protected int shouldRenderPass(T entityLivingBase, int renderPass, float partialTickTime)
//	{
//		return -1;
//	}
//
//	@Override
//	protected void renderEquippedItems(T entityLivingBase, float partialTickTime)
//	{
//		return;
//	}

	@Override
	protected void rotateCorpse(T entityLivingBase, float unknownFloat1, float unknownFloat2, float unknownFloat3)
	{
		super.rotateCorpse(entityLivingBase, unknownFloat1, unknownFloat2, unknownFloat3);
	}

	@Override
	protected void renderLivingAt(T entityLivingBase, double posX, double posY, double posZ)
	{
		super.renderLivingAt(entityLivingBase, posX, posY, posZ);
	}

	@Override
	public void doRender(T entityLivingBase, double posX, double posY, double posZ, float rotationYaw, float rotationPitch)
	{
		super.doRender(entityLivingBase, posX, posY, posZ, rotationYaw, rotationPitch);
	}

	@Override
	protected ResourceLocation getEntityTexture(T entity)
	{
		EntitySpiderQueen spiderQueen = (EntitySpiderQueen)entity;
		return RenderSpiderQueen.spiderQueenTextures[spiderQueen.getTextureId()];
	}
}