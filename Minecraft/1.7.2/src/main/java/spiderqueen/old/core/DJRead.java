
package spiderqueen.old.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import net.minecraft.client.Minecraft;

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
		String[] linesToWrite = filetxt.split("\n");
		
		
      for (int i = 0; i < linesToWrite.length; i++) {

        pw.println(linesToWrite[i]);

      }
		// All done, close the FileWriter.
		pw.flush();
	}
	catch (IOException e) {
       //System.err.println("Write Error: " + e);
	   return;
     }
	 
    

  } //main()

  public static String localLocation()
  {
		//ClassLoader loader = DJRead.class.getClassLoader();
		//System.out.print("locallocation - " +Minecraft.getMinecraftDir().getPath() + "\n");
		return Minecraft.getMinecraftDir().getPath();//"..";
  }
  
  public static int readInt(String location, String line, int ddefault)
  {
	String tmp = readFile(location,line);
	//System.out.print(location + " :: tmp '" + tmp + "'\n");
	if(tmp == null || tmp == "" || tmp == " ") { writeInt(location,line,ddefault); return ddefault; }
	
	return Integer.parseInt(tmp);
  }
  
  public static boolean readBoolean(String location, String line, boolean ddefault)
  {
	String tmp = readFile(location,line);
	//System.out.print(location + " :: tmp '" + tmp + "'\n");
	int idefault = 0;
	if(ddefault) { idefault = 1; }
	if(tmp == null || tmp == "" || tmp == " ") { writeInt(location,line,idefault); return ddefault; }
	
	return (Integer.parseInt(tmp) == 1);
  }
  
  public static void createDir(String location)
  {
	String nloc = "";
	String[] nl = location.split("/");
	for(int i=0; i<nl.length - 1; i++)
	{
		nloc =  nloc + nl[i]; //nloc + nl[i] + "/";
		boolean success = (new File(localLocation() + nloc).mkdir());
		nloc = nloc + "/";
	}
  }
  
  public static void writeInt(String location, String line, int newval)
  {
  		String savel = "";
		
		createDir(location);

	try {
		BufferedReader saveFile=
			new BufferedReader(new FileReader(localLocation() + location));
		
		for(int i = 0; i <= 999;i++)
		{
			Boolean change = false;
			String tmp = saveFile.readLine();
			if(tmp == "" || tmp == "\n" || tmp == null || tmp == "null") { i = 1000; }
			if(i < 1000)
			{
				String tmp2[] = tmp.split(" ; ");
				
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
	catch (IOException e) {
       //System.err.println("Error:" + newval + " " + e);
		writeFile(location, line + " ; " + newval + "\n");
	   
     }
	 
  }
  
  public static ArrayList<String> grabFile(String location)
  {
	ArrayList<String> temp = new ArrayList<String>();
	
	try {
		BufferedReader saveFile=
			new BufferedReader(new FileReader(localLocation() + location));

		// Throw away the blank line at the top.
		for(int i = 0; i <= 999;i++)
		{
		
			String tmp = saveFile.readLine();
			
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
	catch (IOException e) {
       //System.err.println("Error: " + e);
		return temp;
     }
  }
  
  public static String readFile(String location, String line)
  {
    try {
		BufferedReader saveFile=
			new BufferedReader(new FileReader(localLocation() + location));

		// Throw away the blank line at the top.
		for(int i = 0; i <= 999;i++)
		{
		
			String tmp = saveFile.readLine();
			
			if(tmp == "" || tmp == "\n" || tmp == null || tmp == "null") { i = 1000; }
			if(i > 999)
			{
				saveFile.close();
				return "";
			}
				
			String tmp2[] = tmp.split(" ; ");
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
	catch (IOException e) {
       //System.err.println("Error: " + e);
	   return "";
     }
	 
  } //main()
} // TextRead