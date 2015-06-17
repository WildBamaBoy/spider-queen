package sq.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import radixcore.constant.Time;
import sq.enums.EnumWebType;
import cpw.mods.fml.common.registry.GameRegistry;

public class BlockWebFull extends Block
{
	private EnumWebType webType;

	public BlockWebFull(EnumWebType type) 
	{
		super(Material.circuits);

		String name = "web-" + type.getName() + "-block";
		setWebType(type);
		setBlockName(name);
		setBlockTextureName("sq:" + name);
		setHardness(1.0F);

		if (this instanceof BlockWebGround)
		{
			name += "-ground";
		}

		else if (this instanceof BlockWebSide)
		{
			name += "-side";
		}

		GameRegistry.registerBlock(this, name);
	}

	private void setWebType(EnumWebType type)
	{
		this.webType = type;
	}

	public EnumWebType getWebType()
	{
		return webType;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public int getRenderType()
	{
		return 1;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int posX, int posY, int posZ)
	{
		return null;
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess blockAccess, int posX, int posY, int posZ)
	{
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) 
	{
		if (entity instanceof EntitySpider || entity instanceof EntityPlayer) //TODO
		{
			return;
		}

		else
		{
			entity.setInWeb();

			entity.motionX = entity.motionX * -0.1D;
			entity.motionZ = entity.motionZ * -0.1D;
			entity.motionY = entity.motionY * 0.1D;

			if (webType == EnumWebType.POISON && entity instanceof EntityLivingBase)
			{
				final EntityLivingBase entityLiving = (EntityLivingBase)entity;

				if (entityLiving.getActivePotionEffect(Potion.poison) == null)
				{
					entityLiving.addPotionEffect(new PotionEffect(Potion.poison.id, Time.SECOND * 5));
				}
			}
		}
	}

	@Override
	public boolean isLadder(IBlockAccess world, int posX, int posY, int posZ, EntityLivingBase entity)
	{
		if (entity instanceof EntityPlayer || entity instanceof EntitySpider)
		{
			return true;
		}

		else
		{
			return false;
		}
	}
}
