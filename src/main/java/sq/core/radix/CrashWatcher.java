package sq.core.radix;

import java.io.DataOutputStream;
import java.io.File;
import java.net.Socket;
import java.util.Scanner;

import cpw.mods.fml.common.FMLCommonHandler;
import radixcore.core.ModCrashWatcher;
import sq.core.SpiderCore;

public final class CrashWatcher extends ModCrashWatcher
{
	@Override
	protected void onCrash(File crashFile) 
	{
		try
		{
			String report = new Scanner(crashFile).useDelimiter("\\Z").next();
			boolean isServer = FMLCommonHandler.instance().getEffectiveSide().isServer();

			if (report.contains("at sq."))
			{
				if (SpiderCore.getConfig().allowCrashReporting)
				{
					final Socket connectSocket = new Socket("asp.radix-shock.com", 3577);
					final DataOutputStream dataOut = new DataOutputStream(connectSocket.getOutputStream());

					dataOut.writeByte(2);
					dataOut.writeUTF("@Validate@");
					dataOut.writeUTF("SQ");
					dataOut.writeUTF(SpiderCore.VERSION);
					dataOut.writeBoolean(isServer);
					dataOut.writeUTF(report);
					connectSocket.close();

					SpiderCore.getLog().fatal("Sent crash report to mod authors for review. Sorry about that!");
				}

				else
				{
					Thread.sleep(1000); //Give the crash report time to be displayed to the console so this message appears after the fact.
					SpiderCore.getLog().fatal("Detected a crash involving Spider Queen, but crash reporting has been disabled! :(");
					SpiderCore.getLog().fatal("Please consider enabling crash reporting. It will help us find and stop crashes such as this!");
				}
			}
		}

		catch (Exception e)
		{
			SpiderCore.getLog().fatal("Spider Queen detected a crash and attempted to report it, but failed to do so! " + e.getMessage());
		}
	}
}
