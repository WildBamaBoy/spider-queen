package sq.client.render;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import sq.client.model.ModelSpiderQueen;
import sq.entity.EntitySpiderQueen;

public class RenderOtherQueen extends RenderLiving
{
	private final ModelSpiderQueen	modelBipedMain;

	public RenderOtherQueen()
	{
		super(new ModelSpiderQueen(), 0.0F);
		modelBipedMain = new ModelSpiderQueen();
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
	protected void renderEquippedItems(EntityLivingBase entityLivingBase, float partialTickTime)
	{
		return;
	}

	@Override
	protected void rotateCorpse(EntityLivingBase entityLivingBase, float unknownFloat1, float unknownFloat2, float unknownFloat3)
	{
		super.rotateCorpse(entityLivingBase, unknownFloat1, unknownFloat2, unknownFloat3);
	}

	@Override
	protected void renderLivingAt(EntityLivingBase entityLivingBase, double posX, double posY, double posZ)
	{
		super.renderLivingAt(entityLivingBase, posX, posY, posZ);
	}

	@Override
	public void doRender(EntityLivingBase entityLivingBase, double posX, double posY, double posZ, float rotationYaw, float rotationPitch)
	{
		super.doRender(entityLivingBase, posX, posY, posZ, rotationYaw, rotationPitch);
	}

	@Override
	public void doRender(Entity entity, double posX, double posY, double posZ, float rotationYaw, float rotationPitch)
	{
		this.doRender((EntityLivingBase) entity, posX, posY, posZ, rotationYaw, rotationPitch);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		EntitySpiderQueen spiderQueen = (EntitySpiderQueen)entity;
		return RenderSpiderQueen.spiderQueenTextures[spiderQueen.getTextureIndex()];
	}
}