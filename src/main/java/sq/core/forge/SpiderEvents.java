package sq.core.forge;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ServerTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import radixcore.constant.Time;
import radixcore.util.RadixMath;
import sq.core.SpiderCore;

public final class SpiderEvents 
{
	private int counter;
	private int timeUntilSpawnWeb;
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onRenderPlayerPre(RenderPlayerEvent.Pre pre) 
	{
		if (SpiderCore.getConfig().useSpiderQueenModel && pre.getEntityPlayer() == Minecraft.getMinecraft().thePlayer)
		{
			try
			{
			pre.setCanceled(true);

			GL11.glPushMatrix();
			{
				GL11.glScaled(0.95D, 0.95D, 0.95D);
				GL11.glTranslated(0.0D, -0.10D, 0.0D);
				ClientProxy.renderSpiderQueen.doRender(pre.getEntityPlayer(), 0F, 0F, 0F, 0F, pre.getPartialRenderTick());
			}
			GL11.glPopMatrix();
			}
			
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	@SubscribeEvent
	public void serverTickEventHandler(ServerTickEvent event)
	{
		/* Update the random spawning of webs. */
		MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
		
		timeUntilSpawnWeb--;
		
		if (timeUntilSpawnWeb <= 0)
		{
			for (final WorldServer worldServer : server.worldServers)
			{
				for (final Object obj : worldServer.playerEntities)
				{
					final EntityPlayer player = (EntityPlayer) obj;
					player.inventory.addItemStackToInventory(new ItemStack(Items.STRING, RadixMath.getNumberInRange(1, 3)));
				}
			}

			timeUntilSpawnWeb = Time.MINUTE * 3;
		}
	}
}
