// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package spiderqueen.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import spiderqueen.core.SpiderQueen;
import spiderqueen.core.forge.PlayerExtension;
import spiderqueen.entity.EntityWebslinger;
import spiderqueen.enums.EnumPacketType;

import com.radixshock.radixcore.network.Packet;

// Referenced classes of package net.minecraft.src:
//            Item, World, EntityPlayer, ItemStack, 
//            EntityFish

public class ItemWebslinger extends Item
{
	public ItemWebslinger()
	{
		setMaxDamage(64);
		setMaxStackSize(1);
		setCreativeTab(SpiderQueen.getInstance().tabSpiderQueen);
	}

	@Override
	public boolean isFull3D()
	{
		return true;
	}

	@Override
	public boolean shouldRotateAroundWhenRendering()
	{
		return true;
	}

	@Override
	public boolean hitEntity(ItemStack itemstack, EntityLivingBase entityliving, EntityLivingBase entityliving1)
	{
		if(entityliving1 instanceof EntityPlayer)
		{
			EntityPlayer ell = (EntityPlayer) entityliving1;
			PlayerExtension playerExtension = (PlayerExtension) ell.getExtendedProperties(PlayerExtension.ID);

			if (playerExtension.webEntity != null)
			{
				if (!(entityliving instanceof EntityPlayer))
				{
					playerExtension.webEntity.player = null;
				}
			}
			return true;
		}
		return false;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer)
	{
		PlayerExtension playerExtension = (PlayerExtension) entityplayer.getExtendedProperties(PlayerExtension.ID);

		if (playerExtension.webEntity != null)
		{
			if (world.isRemote)
			{
				SpiderQueen.packetPipeline.sendPacketToServer(new Packet(EnumPacketType.DestroySlinger,
						playerExtension.webEntity.getEntityId(), entityplayer.posX, entityplayer.posY, entityplayer.posZ));

				playerExtension.webEntity.player = null;
				playerExtension.webEntity = null;
			}
		} 

		else
		{
			world.playSoundAtEntity(entityplayer, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

			if (!world.isRemote)
			{
				world.spawnEntityInWorld(new EntityWebslinger(world, entityplayer));
			}
		}

		return itemstack;
	}

	@Override
	public void registerIcons(IIconRegister iconRegister)
	{
		itemIcon = iconRegister.registerIcon("spiderqueen:Webslinger");
	}
}
