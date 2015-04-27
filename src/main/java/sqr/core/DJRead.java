
package sqr.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import radixcore.core.RadixCore;

public class DJRead
{

	public DJRead()
	{
	}

	public static void writeFile(String location, String filetxt)
	{
		// Create some data to write.

		// Set up the FileWriter with our file name.
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new FileWriter(localLocation() + location));
			//FileWriter saveFile = new FileWriter(localLocation() + location);
			// Write the data to the file.
			final String[] linesToWrite = filetxt.split("\n");


			for (int i = 0; i < linesToWrite.length; i++) {

				pw.println(linesToWrite[i]);

			}
			// All done, close the FileWriter.
			pw.flush();
		}
		catch (final IOException e) {
			//System.err.println("Write Error: " + e);
			return;
		}



	} //main()

	public static String localLocation()
	{
		//ClassLoader loader = DJRead.class.getClassLoader();
		//System.out.print("locallocation - " +Minecraft.getMinecraftDir().getPath() + "\n");
		return RadixCore.getRunningDirectory();
	}

	public static int readInt(String location, String line, int ddefault)
	{
		final String tmp = readFile(location,line);
		//System.out.print(location + " :: tmp '" + tmp + "'\n");
		if(tmp == null || tmp == "" || tmp == " ") { writeInt(location,line,ddefault); return ddefault; }

		return Integer.parseInt(tmp);
	}

	public static boolean readBoolean(String location, String line, boolean ddefault)
	{
		final String tmp = readFile(location,line);
		//System.out.print(location + " :: tmp '" + tmp + "'\n");
		int idefault = 0;
		if(ddefault) { idefault = 1; }
		if(tmp == null || tmp == "" || tmp == " ") { writeInt(location,line,idefault); return ddefault; }

		return Integer.parseInt(tmp) == 1;
	}

	public static void createDir(String location)
	{
		String nloc = "";
		final String[] nl = location.split("/");
		for(int i=0; i<nl.length - 1; i++)
		{
			nloc =  nloc + nl[i]; //nloc + nl[i] + "/";
			final boolean success = new File(localLocation() + nloc).mkdir();
			nloc = nloc + "/";
		}
	}

	public static void writeInt(String location, String line, int newval)
	{
		String savel = "";

		createDir(location);

		try {
			final BufferedReader saveFile=
					new BufferedReader(new FileReader(localLocation() + location));

			for(int i = 0; i <= 999;i++)
			{
				Boolean change = false;
				final String tmp = saveFile.readLine();
				if(tmp == "" || tmp == "\n" || tmp == null || tmp == "null") { i = 1000; }
				if(i < 1000)
				{
					final String tmp2[] = tmp.split(" ; ");

					if(tmp2.length > 0)
					{
						if(tmp2[0].compareTo(line) == 0)
						{
							savel = savel + line + " ; " + newval + "\n";
							change = true;
						}
					}
				}
				if(change == false)
				{
					if(i < 1000)
					{
						savel = savel + tmp + "\n";
					}
					else
					{
						savel = savel + line + " ; " + newval + "\n";
					}
				}
			}

			//System.out.print("write: " + savel + "\n");
			writeFile(location, savel);
		}
		catch (final IOException e) {
			//System.err.println("Error:" + newval + " " + e);
			writeFile(location, line + " ; " + newval + "\n");

		}

	}

	public static ArrayList<String> grabFile(String location)
	{
		final ArrayList<String> temp = new ArrayList<String>();

		try {
			final BufferedReader saveFile=
					new BufferedReader(new FileReader(localLocation() + location));

			// Throw away the blank line at the top.
			for(int i = 0; i <= 999;i++)
			{

				final String tmp = saveFile.readLine();

				if(tmp == "" || tmp == "\n" || tmp == null || tmp == "null") { i = 1000; }
				if(i > 999)
				{
					saveFile.close();
				}
				else
				{
					temp.add(tmp);
				}
			}

			saveFile.close();
			return temp;
		}
		catch (final IOException e) {
			//System.err.println("Error: " + e);
			return temp;
		}
	}

	public static String readFile(String location, String line)
	{
		try {
			final BufferedReader saveFile=
					new BufferedReader(new FileReader(localLocation() + location));

			// Throw away the blank line at the top.
			for(int i = 0; i <= 999;i++)
			{

				final String tmp = saveFile.readLine();

				if(tmp == "" || tmp == "\n" || tmp == null || tmp == "null") { i = 1000; }
				if(i > 999)
				{
					saveFile.close();
					return "";
				}

				final String tmp2[] = tmp.split(" ; ");
				//System.out.print("readd - " + tmp2[0] + " - " + line + "\n");

				if(tmp2[0].compareTo(line) == 0)
				{
					//System.out.print("readdfound\n");
					saveFile.close();
					return tmp2[1];
				}

			}

			saveFile.close();
			return "";
		}
		catch (final IOException e) {
			//System.err.println("Error: " + e);
			return "";
		}

	} //main()
} // TextRead