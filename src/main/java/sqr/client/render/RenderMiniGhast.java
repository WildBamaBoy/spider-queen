//Replace with rewritten version.

//package sqr.client.render;
//
//import net.minecraft.client.model.ModelGhast;
//import net.minecraft.client.renderer.entity.RenderLiving;
//import net.minecraft.entity.Entity;
//import net.minecraft.entity.EntityLivingBase;
//import net.minecraft.util.ResourceLocation;
//
//import org.lwjgl.opengl.GL11;
//
//import sqr.entity.EntityMiniGhast;
//
//public class RenderMinighast extends RenderLiving
//{
//	public RenderMinighast()
//	{
//		super(new ModelGhast(), 0.5F);
//	}
//
//	protected void func_4014_a(EntityMiniGhast entityghast, float f)
//	{
//		final EntityMiniGhast entityghast1 = entityghast;
//		float f1 = (entityghast1.prevAttackCounter + (entityghast1.attackCounter - entityghast1.prevAttackCounter) * f) / 20F;
//		if (f1 < 0.0F)
//		{
//			f1 = 0.0F;
//		}
//		f1 = 1.0F / (f1 * f1 * f1 * f1 * f1 * 2.0F + 1.0F);
//		final float f2 = (8F + f1) / 2.0F;
//		final float f3 = (8F + 1.0F / f1) / 2.0F;
//		GL11.glScalef(f3/8F, f2/8F, f3/8F);
//		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
//	}
//
//	@Override
//	protected void preRenderCallback(EntityLivingBase entityliving, float f)
//	{
//		this.func_4014_a((EntityMiniGhast)entityliving, f);
//	}
//
//	@Override
//	protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
//		// TODO Auto-generated method stub
//		return null;
//	}
// }
