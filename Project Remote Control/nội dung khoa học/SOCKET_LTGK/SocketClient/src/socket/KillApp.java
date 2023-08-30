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

public class KillApp extends JFrame {

	private JPanel contentPane;
	private JTextField TextPid;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client c = null;
					KillApp frame = new KillApp(c);
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
	public KillApp(Client c ) {
		
		ImageIcon killIcon = new ImageIcon("kill.png");
		
		
		setTitle("Kill App");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(700, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNewLabel = new JLabel("ID: ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel.setBounds(24, 24, 44, 26);
		contentPane.add(lblNewLabel);
		
		TextPid = new JTextField();
		TextPid.setBounds(78, 24, 135, 30);
		TextPid.setBorder(BorderFactory.createLineBorder(new Color(0xb266ff),3));
		TextPid.setOpaque(true);
		TextPid.setForeground(new Color(0x0e3a53));
		TextPid.setBackground(new Color(0xaaf0d1));
		TextPid.setFont(new Font("Consolas",Font.PLAIN,25));
		TextPid.setHorizontalAlignment(JTextField.CENTER);
		contentPane.add(TextPid);
		TextPid.setColumns(10);
		
		JButton KillAppButton = new JButton("KILL",killIcon);
		KillAppButton.setBounds(255, 10, 135,59);
		KillAppButton.setOpaque(true);
		KillAppButton.setForeground(new Color(0xff007f));
		KillAppButton.setBackground(new Color(0xf4bfc7));
		KillAppButton.setBorder(BorderFactory.createLineBorder(new Color(0x8732a8),3));
		KillAppButton.setFont(new Font("Consolas",Font.BOLD,18));
		KillAppButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pid = TextPid.getText();
				c.KillApp(pid);
			}
		});
		contentPane.add(KillAppButton);
	}

}
