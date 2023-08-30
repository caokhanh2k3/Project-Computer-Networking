package socket;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class StartProcess extends JFrame {

	private JPanel contentPane;
	private JTextField textNameprocess;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client c = null;
					StartProcess frame = new StartProcess(c);
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
	public StartProcess(Client c) {


         ImageIcon startIcon = new ImageIcon("start.png");
		
		//=================================================================
		setTitle("Start Process");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(700, 100, 415, 260);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		
		lblNewLabel = new JLabel("NAME PROCESS: ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(10, 19, 98, 27);
		contentPane.add(lblNewLabel);
		contentPane.add(lblNewLabel);
		
		textNameprocess = new JTextField();
		
		textNameprocess.setBounds(118, 17, 146, 36);
		textNameprocess.setBorder(BorderFactory.createLineBorder(new Color(0xb266ff),3));
		textNameprocess.setOpaque(true);
		textNameprocess.setForeground(new Color(0x0e3a53));
		textNameprocess.setBackground(new Color(0xaaf0d1));
		textNameprocess.setFont(new Font("Consolas",Font.PLAIN,25));
		textNameprocess.setHorizontalAlignment(JTextField.CENTER);
		contentPane.add(textNameprocess);
		textNameprocess.setColumns(10);
		
		JButton ButtonStartProcess = new JButton("START",startIcon);
		ButtonStartProcess.setBounds(120, 100, 135,59);
		ButtonStartProcess.setOpaque(true);
		ButtonStartProcess.setForeground(new Color(0xff007f));
		ButtonStartProcess.setBackground(new Color(0xf4bfc7));
		ButtonStartProcess.setBorder(BorderFactory.createLineBorder(new Color(0x8732a8),3));
		ButtonStartProcess.setFont(new Font("Consolas",Font.BOLD,18));
		ButtonStartProcess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				String NameProcess = textNameprocess.getText();
				c.StartProcess( NameProcess);
			}
		});
		contentPane.add(ButtonStartProcess);
	}

}
