package sqr.core;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import net.minecraft.client.Minecraft;

public abstract class DJBaseMod
{
	private boolean hasChecked = false;
	private boolean hasChatted = false;
	private String shouldUpdate = "";
	
	public DJBaseMod()
	{
		// ModLoader.setInGameHook(this, true, false);
	}
	
	public String stripToNumbers(String str)
	{
		return str.replaceAll("[^0-9]", "");
	}
	
	public boolean OnTickInGame(float f, Minecraft minecraft)
	{
		if (!this.hasChecked)
		{
			this.checkUpdate();
			if (this.shouldUpdate != "" & !this.hasChatted)
			{
				if (minecraft != null)
				{
					if (minecraft.thePlayer != null)
					{
						// if(!stripToNumbers(shouldUpdate).equals(stripToNumbers(getVersion())))
						// {
						// minecraft.thePlayer.addChatMessage("Update needed || "
						// + getModName() + " || http://djoslin.info");
						// }
						
						this.hasChatted = true;
					}
				}
			}
			return true;
		}
		return false;
	}
	
	/*
	 * public void load() { }
	 * 
	 * public String getVersion() { return "1"; }
	 */
	
	public abstract String getModName();
	
	public String convertStreamToString(InputStream is)
	{
		return new Scanner(is).useDelimiter("\\A").next();
	}
	
	public void checkUpdate()
	{
		this.hasChecked = true;
		try
		{
			// Create a URL for the desired page
			final URL url = new URL("http://www.djoslin.info/updates/" + this.getModName() + ".txt");
			// http://dl.dropbox.com/u/3734515/glasslight.txt
			// URL url = new URL("http://dl.dropbox.com/u/3734515/" +
			// getModName() + ".txt");
			
			final URLConnection conn = url.openConnection();
			// setting these timeouts ensures the client does not deadlock
			// indefinitely
			// when the server has problems.
			conn.setConnectTimeout(250);
			conn.setReadTimeout(250);
			final InputStream str = conn.getInputStream();
			this.shouldUpdate = this.convertStreamToString(str);
			// BufferedReader in = new BufferedReader(new
			// InputStreamReader(url.openStream()));
			// String str = in.readLine();
			// while ((str = in.readLine()) != null) {
			// System.out.println(str);
			// shouldUpdate = str;
			// }
			// in.close();
		}
		catch (final MalformedURLException e)
		{
		}
		catch (final IOException e)
		{
		}
	}
}
