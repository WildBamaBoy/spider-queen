//TODO Wolf spider

//package sqr.entity;
//
//
//import java.util.Random;
//
//import net.minecraft.entity.Entity;
//import net.minecraft.util.DamageSource;
//import net.minecraft.util.MathHelper;
//import net.minecraft.world.World;
//import sqr.core.SQR;
// 
//public class EntityFurrySpider extends EntityFriendlySpider
//{
//
//	public EntityFurrySpider(World world)
//	{
//		super(world);
//		//TODO texture ="/imgz/furryspider_1.png";
//		this.setSize(1.4F, 0.9F);
//		//TODO Attribute moveSpeed = 0.8F;
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
//			return "/imgz/furryspider_3.png";
//		}
//		if(this.getLevel() == 1)
//		{
//			return "/imgz/furryspider_2.png";
//		}
//		return "/imgz/furryspider_1.png";
//	}
//
//	@Override
//	protected boolean canDespawn()
//	{
//		return false;
//	}
//
//	@Override
//	protected Entity findPlayerToAttack()
//	{
//		if(this.recharge) { return null; }
//		final Entity nn = SQR.getClosestEntityToEntity(this.worldObj, this, 32D, 7);
//		if(nn instanceof EntityFriendlySpider)
//		{
//			final EntityFriendlySpider tnt = (EntityFriendlySpider) nn;
//			if(tnt.health + 5 < tnt.getMaxHealth()) { return nn; }
//		}
//
//		return null;
//	}
//
//
//	public boolean attackEntityFrom(DamageSource damagesource, float i)
//	{
//		final Entity entity = damagesource.getEntity();
//		if(super.attackEntityFrom(damagesource, i))
//		{
//			if(this.riddenByEntity == entity || this.ridingEntity == entity)
//			{
//				return true;
//			}
//			if(entity != this)
//			{
//				this.entityToAttack = null;
//			}
//			return true;
//		} else
//		{
//			return false;
//		}
//	}
//
//	@Override
//	public void onLivingUpdate()
//	{
//		super.onLivingUpdate();
//		if(this.recharge) { this.entityToAttack = null; this.rechargeAmt--; }
//		if(this.rechargeAmt < 0) { this.recharge = false; this.rechargeAmt = 0; }
//		if(this.rechargeAmt > 8 + this.getLevel() * 8 & this.recharge == false) { this.recharge = true; this.rechargeAmt = 500; }
//	}
//
//
//	@Override
//	protected void attackEntity(Entity entity, float f)
//	{
//		if(entity instanceof EntityFriendlySpider)
//		{}
//		else
//		{
//			if(f > 2.0F && f < 6F && this.rand.nextInt(40) == 0)
//			{
//				if(this.onGround)
//				{
//					final double d = entity.posX - this.posX;
//					final double d1 = entity.posZ - this.posZ;
//					final float f2 = MathHelper.sqrt_double(d * d + d1 * d1);
//					this.motionX = d / f2 * 0.5D * 0.80000001192092896D + this.motionX * 0.20000000298023224D;
//					this.motionZ = d1 / f2 * 0.5D * 0.80000001192092896D + this.motionZ * 0.20000000298023224D;
//					this.motionY = 0.40000000596046448D;
//				}
//			} else
//			{
//				this.attack2Entity(entity, f);
//			}
//			return;
//		}
//
//		if(this.recharge) { this.entityToAttack = null; return; }
//		final float f1 = this.getBrightness(1.0F);
//		if(f1 > 0.5F && this.rand.nextInt(100) == 0)
//		{
//			this.entityToAttack = null;
//			return;
//		}
//
//		final Random rr = new Random();
//
//		if(rr.nextInt(20) == 0 & SQR.getDistance3d(this.entityToAttack.posX,this.entityToAttack.posY,this.entityToAttack.posZ,this.posX,this.posY,this.posZ) < 5D)
//		{
//			if(this.entityToAttack instanceof EntityFriendlySpider)
//			{
//				final EntityFriendlySpider tnt = (EntityFriendlySpider) this.entityToAttack;
//				tnt.health = tnt.health + 1;
//				if(rr.nextInt(3) == 0 ) { tnt.showHeartsOrSmokeFX(true); }
//				this.rechargeAmt++;
//				if(tnt.health > tnt.getMaxHealth()) { tnt.health = tnt.getMaxHealth(); }
//				//				System.out.print(f + ", " + tnt.health + "\n");
//				this.entityToAttack = null;
//			}
//			else
//			{
//				this.entityToAttack = null;
//			}
//		}
//	}
//
//	private boolean recharge;
//	private int rechargeAmt;
// }
