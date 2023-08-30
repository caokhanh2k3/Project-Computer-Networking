package socket;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class StartApp extends JFrame {

	private JPanel contentPane;
	private JTextField textNameApp;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client c = null;
					StartApp frame = new StartApp(c);
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
	public StartApp(Client c) {
		
		
		ImageIcon startIcon = new ImageIcon("start.png");
		
		//=================================================================
		
		
		setTitle("Start App");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(700, 100, 415, 260);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNewLabel = new JLabel("NAME APP: ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(10, 19, 98, 27);
		contentPane.add(lblNewLabel);
		
		
		
		textNameApp = new JTextField();
		textNameApp.setBounds(118, 17, 146, 36);
		textNameApp.setBorder(BorderFactory.createLineBorder(new Color(0xb266ff),3));
		textNameApp.setOpaque(true);
		textNameApp.setForeground(new Color(0x0e3a53));
		textNameApp.setBackground(new Color(0xaaf0d1));
		textNameApp.setFont(new Font("Consolas",Font.PLAIN,25));
		textNameApp.setHorizontalAlignment(JTextField.CENTER);
		contentPane.add(textNameApp);
		textNameApp.setColumns(10);
		
		JButton ButtonStartApp = new JButton("START",startIcon);
		ButtonStartApp.setBounds(120, 100, 135,59);
		ButtonStartApp.setOpaque(true);
		ButtonStartApp.setForeground(new Color(0xff007f));
		ButtonStartApp.setBackground(new Color(0xf4bfc7));
		ButtonStartApp.setBorder(BorderFactory.createLineBorder(new Color(0x8732a8),3));
		ButtonStartApp.setFont(new Font("Consolas",Font.BOLD,18));
		ButtonStartApp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String NameApp = textNameApp.getText();
				c.StartApp(NameApp);
			}
		});
		contentPane.add(ButtonStartApp);
	}

}
