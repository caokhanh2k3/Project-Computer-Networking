package socket;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.awt.event.ActionEvent;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;

@SuppressWarnings("serial")
public class mainmenu extends JFrame {

	private JPanel contentPane;
	private JTextField textipserver;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client c = new Client();
					mainmenu frame = new mainmenu(c);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	String ipserver;
	
	public mainmenu(Client c) throws IOException {
		ImageIcon exitIcon = new ImageIcon("exit.png");
		ImageIcon connectIcon = new ImageIcon("connect.png");
		ImageIcon shutIcon = new ImageIcon("shut.png");
		ImageIcon snapIcon = new ImageIcon("snap.png");
		ImageIcon kstrokeIcon = new ImageIcon("keystroke.png");
		ImageIcon apprunIcon = new ImageIcon("apprun.png");
		ImageIcon processrunIcon = new ImageIcon("processrun.png");
		//ImageIcon display = new ImageIcon("bg.png");
		//=================================================================================
		setTitle("main menu");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 700);
		contentPane = new JPanel();
		
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		
		
		
		
		ImageIcon display = new ImageIcon("bg.png");
		JLabel background;
		background = new JLabel();
		background.setBounds(0, 0, 790,700);
		background.setIcon(display);
		
		JLabel lblNewLabel = new JLabel("IP : ");
		lblNewLabel.setForeground(Color.white);
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblNewLabel.setBounds(74, 16, 64, 26);
		background.add(lblNewLabel);
		
		
		
		
		
		
		
		
		JButton ButtonScreenshot = new JButton("Screenshot",snapIcon);
		background.add(ButtonScreenshot);
		ButtonScreenshot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				
				try {
					c.Screenshot();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				screenshot scrs = null;
				try {
					scrs = new screenshot(c);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				scrs.setVisible(true);
			
			}

			
		});
		ButtonScreenshot.setBounds(500, 100, 248,108);
		ButtonScreenshot.setOpaque(true);
		
		ButtonScreenshot.setForeground(Color.green);
		ButtonScreenshot.setBackground(new Color(0x330066));
		ButtonScreenshot.setBorder(BorderFactory.createLineBorder(new Color(0xff007f),2));
		ButtonScreenshot.setFont(new Font("Consolas",Font.PLAIN,23));
		
		
		
		
		
		
		
		
		
		
		
		JButton ButtonKeyLog = new JButton("Keystroke",kstrokeIcon);
		background.add(ButtonKeyLog);
		ButtonKeyLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                KeyLogger keylogger = new KeyLogger(c);  
				
                keylogger.setVisible(true);
			}
		});
		ButtonKeyLog.setBounds(500, 208,248,108);
		ButtonKeyLog.setOpaque(true);
		ButtonKeyLog.setForeground(new Color(0x33ffff));
		ButtonKeyLog.setBackground(new Color(0xc66008));
		ButtonKeyLog.setBorder(BorderFactory.createLineBorder(new Color(0xff007f),2));
		ButtonKeyLog.setFont(new Font("Consolas",Font.PLAIN,23));
		
		
		
		JButton ButtonExitSocket = new JButton("Exit",exitIcon);
		background.add(ButtonExitSocket);
		ButtonExitSocket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.ExitSocket();
				
			    System.exit(0);
			}
		});
		ButtonExitSocket.setBounds(550, 562, 156,72);
		ButtonExitSocket.setOpaque(true);
		
		ButtonExitSocket.setForeground(new Color(0xf23b3b));
		ButtonExitSocket.setBackground(Color.lightGray);
		ButtonExitSocket.setFont(new Font("Arial",Font.BOLD,30));
		ButtonExitSocket.setBorder(BorderFactory.createLineBorder(new Color(0xcc6600),2));
		ButtonExitSocket.setFocusable(false);
		
		JButton ButtonApp = new JButton("App Running",apprunIcon);
		background.add(ButtonApp);
		ButtonApp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Application app = new Application(c);  
				
				app.setVisible(true);
			}
		});
		ButtonApp.setBounds(500, 316,248,108);
		ButtonApp.setOpaque(true);
		
		ButtonApp.setForeground(Color.orange);
		ButtonApp.setBackground(new Color(0x00994c));
		ButtonApp.setBorder(BorderFactory.createLineBorder(new Color(0xff007f),2));
		ButtonApp.setFont(new Font("Consolas",Font.PLAIN,20));
		
		
		
		
		JButton process = new JButton("Process Running",processrunIcon);
		background.add(process);
		process.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				process prc = new process(c);  
//				
				prc.setVisible(true);
				
				
			}
		});
		process.setBounds(500, 424, 248,108);
		process.setOpaque(true);
		
		process.setForeground(new Color(0xaaf0d1));
		process.setBackground(new Color(0x0e3a53));
		process.setBorder(BorderFactory.createLineBorder(new Color(0xff007f),2));
		process.setFont(new Font("Consolas",Font.PLAIN,20));
		
		
		
		
		
		JButton ButtonShutdown = new JButton("Shut down",shutIcon);
		background.add(ButtonShutdown);
		ButtonShutdown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.Shutdown();
			}
		});
		ButtonShutdown.setBounds(180, 509, 272,100);
		ButtonShutdown.setOpaque(true);
		//ButtonShutdown.setBounds(157,575,272,100);
		ButtonShutdown.setBorder(BorderFactory.createLineBorder(new Color(0xff007f),2));
		ButtonShutdown.setForeground(Color.yellow);
		ButtonShutdown.setBackground(Color.black);
		ButtonShutdown.setFont(new Font("Arial",Font.PLAIN,23));
		
		

		contentPane.add(background);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		textipserver = new JTextField();

		background.add(textipserver);
	
		textipserver.setBounds(134,10,342,40);
		textipserver.setBorder(BorderFactory.createLineBorder(new Color(0xb266ff),3));
		textipserver.setOpaque(true);
		textipserver.setForeground(new Color(0x330066));
		textipserver.setBackground(new Color(0xc0c0c0));
		textipserver.setFont(new Font("Consolas",Font.PLAIN,35));
		textipserver.setHorizontalAlignment(JTextField.CENTER);
		textipserver.setColumns(10);

	    
			
		
		JButton Buttonconnect = new JButton("Connect",connectIcon);
		background.add(Buttonconnect);
		Buttonconnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ipserver = textipserver.getText();
				
			    try {
			    	
					c.connect(ipserver);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		Buttonconnect.setBounds(525, 10, 173, 71 );
		Buttonconnect.setOpaque(true);
		Buttonconnect.setForeground(Color.cyan);
		Buttonconnect.setBackground(new Color(0x004c99));
		Buttonconnect.setBorder(BorderFactory.createLineBorder(new Color(0xb266ff),2));
		Buttonconnect.setFont(new Font("Arial",Font.BOLD, 15));
		
		
		
		
		
		
	}
}
