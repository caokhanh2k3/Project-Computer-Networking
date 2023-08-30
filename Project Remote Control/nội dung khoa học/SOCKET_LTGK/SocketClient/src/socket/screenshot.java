package socket;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Consumer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class screenshot extends JFrame {

	private JPanel contentPane;
	Graphics g;
	String path = "screen.jpg";
	BufferedImage image = ImageIO.read(new File(path));
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client c = null;
					screenshot frame  = new screenshot(c);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	
	
	public screenshot(Client c) throws IOException {

		ImageIcon shootIcon = new ImageIcon("shoot.png");
		ImageIcon saveIcon = new ImageIcon("save.png");
		
		
		
		
		
		setTitle("Screenshot");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(50, 50, 1050, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
	       JPanel pnl = new JPanel() {
	    	   @Override
	           protected void paintComponent(Graphics g) {
	               super.paintComponent(g);
	               g.drawImage(image, 0, 0, 1000, 600,null); 
	           }
	       };
	       
	       pnl.setBounds(10, 85, 1000, 750);
	       contentPane.add(pnl);
		
		
		
		
		JButton Buttonsave = new JButton("SAVE",saveIcon);
		Buttonsave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			    File file = null;
			    JFileChooser fileChooser = new JFileChooser();
			    Component modalToComponent = null;
			    if (fileChooser.showSaveDialog(modalToComponent) == JFileChooser.APPROVE_OPTION) {
			        file = new File(fileChooser.getSelectedFile() + ".jpg");
			        
			        fileChooser.setDialogTitle("Save Screenshot");
			       
			    }
			    try {
			        ImageIO.write(image, "jpg", file);
			    } catch (IOException ex) {
			       
			    }
			}
		});
		Buttonsave.setBounds(487, 10, 150, 75);
		Buttonsave.setOpaque(true);
		Buttonsave.setForeground(new Color(0xff007f));
		Buttonsave.setBackground(new Color(0xf4bfc7));
		Buttonsave.setBorder(BorderFactory.createLineBorder(new Color(0x8732a8),3));
		Buttonsave.setFont(new Font("Consolas",Font.BOLD,18));
		
		contentPane.add(Buttonsave);
		
		
        
		JButton Buttonscreenshot = new JButton("TAKE",shootIcon);
		Buttonscreenshot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					c.Screenshot();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
				screenshot scrs1 = null;
				try {
					scrs1 = new screenshot(c);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				scrs1.setVisible(true);
				
			}
		});
		Buttonscreenshot.setBounds(267, 10 ,150, 75 );
		Buttonscreenshot.setOpaque(true);
		Buttonscreenshot.setForeground(new Color(0xff007f));
		Buttonscreenshot.setBackground(new Color(0xf4bfc7));
		Buttonscreenshot.setBorder(BorderFactory.createLineBorder(new Color(0x8732a8),3));
		Buttonscreenshot.setFont(new Font("Consolas",Font.BOLD,18));
		contentPane.add(Buttonscreenshot);
		
		
		

		
		
	}
	
	
	
	
	
	
	
}
