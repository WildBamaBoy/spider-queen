//TODO Creeper spider.

//package sqr.entity;
//
//
//import net.minecraft.entity.Entity;
//import net.minecraft.util.DamageSource;
//import net.minecraft.util.MathHelper;
//import net.minecraft.world.World;
//
//public class EntityBoomSpider extends EntityFriendlySpider
//{
//
//	public EntityBoomSpider(World world)
//	{
//		super(world);
////		//TODO texture ="/imgz/boomspider_1.png";
//		this.setSize(1.4F, 0.9F);
////		//TODO Attribute moveSpeed = 0.8F;
//		this.timeo = 0;
//	}
//
//	@Override
//	public int getMaxHealth() { return 30; }
//
//	@Override
//	public String getEntityTexture()
//	{
//		if(this.getLevel() == 2)
//		{
//			return "/imgz/boomspider_3.png";
//		}
//		if(this.getLevel() == 1)
//		{
//			return "/imgz/boomspider_2.png";
//		}
//		return "/imgz/boomspider_1.png";
//	}
//
//	@Override
//	public void onDeath(DamageSource damagesource)
//	{
//		super.onDeath(damagesource);
//
//		//TODO Explode on death.
////		this.hurtArea(16F);
//	}
//
//	@Override
//	public int getAttackStrength()
//	{
//		return 3 + 4 * this.getLevel();
//	}
//
//	@Override
//	protected boolean canDespawn()
//	{
//		return false;
//	}
//
//	//TODO NOt relevant
////	@Override
////	protected Entity findPlayerToAttack()
////	{
////		return SQR.findEnemyToAttack(this.worldObj, this);
////	}
//
//	@Override
//	protected void attackEntity(Entity entity, float f)
//	{
//		final float f1 = this.getBrightness(1.0F);
//		if(f1 > 0.5F && this.rand.nextInt(100) == 0)
//		{
//			this.entityToAttack = null;
//			return;
//		}
//		if(f > 2.0F && f < 7F && this.rand.nextInt(10) == 0)
//		{
//			if(this.onGround)
//			{
//				final double d = entity.posX - this.posX;
//				final double d1 = entity.posZ - this.posZ;
//				final float f2 = MathHelper.sqrt_double(d * d + d1 * d1);
//				this.motionX = d / f2 * 0.5D * 0.80000001192092896D + this.motionX * 0.20000000298023224D;
//				this.motionZ = d1 / f2 * 0.5D * 0.80000001192092896D + this.motionZ * 0.20000000298023224D;
//				this.motionY = 0.40000000596046448D;
//			}
//		} else
//		{
//			if(this.attackTime == 0)
//			{
//				double d = entity.posX - this.posX;
//				double d1 = entity.posZ - this.posZ;
//				final float f21 = MathHelper.sqrt_double(d * d + d1 * d1) * 0.5F;
//				d = d + entity.motionX * 7.2D * f21;
//				d1 = d1 + entity.motionZ * 7.2D * f21;
//				final EntityBoomball entityarrow = new EntityBoomball(this.worldObj, this);
//				final double d2 = entity.posY + 0.4D - 0.20000000298023224D - entityarrow.posY;
//				this.worldObj.playSoundAtEntity(this, "Octoball", 1.0F, 1.0F / (this.rand.nextFloat() * 0.4F + 0.8F));
//				this.worldObj.spawnEntityInWorld(entityarrow);
//				entityarrow.setBoomballHeading(d, d2, d1, 0.6F, 0F);
//				this.attackTime = 25 - this.getLevel() * 4;
//			}
//			//super.attackEntity(entity, f);
//		}
//	}
//
//	private final int timeo;
//}
