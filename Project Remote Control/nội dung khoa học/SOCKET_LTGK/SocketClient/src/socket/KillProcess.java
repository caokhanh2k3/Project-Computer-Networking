package socket;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.function.Consumer;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class KillProcess extends JFrame {

	private JPanel contentPane;
	private JTextField textPID;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client c = null;
					KillProcess frame = new KillProcess(c);
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


	
	
	public KillProcess(Client c) {
		ImageIcon killIcon = new ImageIcon("kill.png");
		
		
		
		setTitle("Kill Process");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(700, 100, 415, 260);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		
		lblNewLabel = new JLabel("ID: ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel.setBounds(24, 24, 44, 26);
		contentPane.add(lblNewLabel);
		
		textPID = new JTextField();
		textPID.setBounds(78, 24, 135, 30);
		textPID.setBorder(BorderFactory.createLineBorder(new Color(0xb266ff),3));
		textPID.setOpaque(true);
		textPID.setForeground(new Color(0x0e3a53));
		textPID.setBackground(new Color(0xaaf0d1));
		textPID.setFont(new Font("Consolas",Font.PLAIN,25));
		textPID.setHorizontalAlignment(JTextField.CENTER);
		
		contentPane.add(textPID);
		textPID.setColumns(10);
		
		JButton ButtonKill = new JButton("KILL",killIcon);
		ButtonKill.setBounds(255, 10, 135,59);
		ButtonKill.setOpaque(true);
		ButtonKill.setForeground(new Color(0xff007f));
		ButtonKill.setBackground(new Color(0xf4bfc7));
		ButtonKill.setBorder(BorderFactory.createLineBorder(new Color(0x8732a8),3));
		ButtonKill.setFont(new Font("Consolas",Font.BOLD,18));
		ButtonKill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pid = textPID.getText();
				c.KillProcess(pid);
			}
		});
		contentPane.add(ButtonKill);
	}

}
