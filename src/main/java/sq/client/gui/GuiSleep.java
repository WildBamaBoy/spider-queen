package sq.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import sq.core.SpiderCore;
import sq.packet.PacketSleepS;

/**
 * Renders a purple overlay on the player's screen, much like when they sleep in the original game.
 */
public class GuiSleep extends GuiScreen
{
	private EntityPlayer player;
	private int sleepProgress = 0;
	private boolean hasSentSleepComplete = false;

	public GuiSleep(EntityPlayer player)
	{
		this.player = player;
	}

	@Override
	protected void keyTyped(char keyChar, int keyCode)
	{
		//Allow the player to stop by pressing Esc.
		if (keyCode == Keyboard.KEY_ESCAPE)
		{
			Minecraft.getMinecraft().displayGuiScreen(null);
		}
	}

	@Override
	public void updateScreen()
	{
		super.updateScreen();

		//Count up to 100, signaling that the sleep animation has completed.
		if (sleepProgress != 100)
		{
			sleepProgress += 2;
		}

		else
		{
			//Tell the server that this player has finished the sleeping animation. The server will close this GUI screen
			//and advance the time of day in-game if all players are asleep.
			if (!hasSentSleepComplete)
			{
				SpiderCore.getPacketHandler().sendPacketToServer(new PacketSleepS(player.getCommandSenderName(), true));
				hasSentSleepComplete = true;
			}
		}
	}

	@Override
	public void onGuiClosed() 
	{
		super.onGuiClosed();
		
		//When we close out of this GUI, tell the server we are no longer sleeping.
		SpiderCore.getPacketHandler().sendPacketToServer(new PacketSleepS(player.getCommandSenderName(), false));
	}

	@Override
	public boolean doesGuiPauseGame()
	{
		return false;
	}

	@Override
	public void drawScreen(int posX, int posY, float partialTickTime)
	{
		super.drawScreen(posX, posY, partialTickTime);

		if (sleepProgress > 0)
		{
			//This draws the purple gradient on the screen.
			ScaledResolution scaledResolution = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);

			GL11.glDisable(GL11.GL_DEPTH_TEST);
			GL11.glDisable(GL11.GL_ALPHA_TEST);
			{
				//Increase intensity as sleep progress grows larger.
				float colorIntensity = (float)sleepProgress / 100.0F;

				if (colorIntensity > 1.0F)
				{
					colorIntensity = 1.0F - (float)(sleepProgress - 100) / 10.0F;
				}

				int color = (int)(220.0F * colorIntensity) << 24 | 1052704;

				drawRect(0, 0,  scaledResolution.getScaledWidth(), scaledResolution.getScaledHeight(), color);
			}
			GL11.glEnable(GL11.GL_ALPHA_TEST);
			GL11.glEnable(GL11.GL_DEPTH_TEST);
		}
	}
}