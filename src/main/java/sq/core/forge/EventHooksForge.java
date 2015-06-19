package sq.core.forge;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import radixcore.util.RadixLogic;
import sq.entity.EntitySpiderEx;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public final class EventHooksForge
{
	@SubscribeEvent
	public void onPlayerSleepInBed(PlayerSleepInBedEvent event)
	{
		event.result = EntityPlayer.EnumStatus.NOT_POSSIBLE_HERE;
		event.entityPlayer.addChatMessage(new ChatComponentText("Spiders can't sleep in normal beds."));
	}
	
	@SubscribeEvent
	public void onAttackEntity(AttackEntityEvent event)
	{
		final EntityPlayer player = event.entityPlayer;
		
		if (event.target instanceof EntityLivingBase)
		{
			List<Entity> entities = RadixLogic.getAllEntitiesWithinDistanceOfCoordinates(player.worldObj, player.posX, player.posY, player.posZ, 20);
			
			for (Entity entity : entities)
			{
				if (entity instanceof EntitySpiderEx)
				{
					EntitySpiderEx spider = (EntitySpiderEx)entity;
					
					if (spider.getOwner().equals(player.getPersistentID()))
					{
						spider.setTarget(event.target);
					}
				}
			}
		}
	}
}
