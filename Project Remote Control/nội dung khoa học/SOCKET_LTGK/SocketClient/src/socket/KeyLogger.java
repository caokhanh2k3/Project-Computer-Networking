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
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class KeyLogger extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client c = null;
					KeyLogger frame = new KeyLogger(c);
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
	public KeyLogger(Client c) {
		
		ImageIcon printIcon = new ImageIcon("print.png");
		ImageIcon delIcon = new ImageIcon("delete.png");
		ImageIcon hookIcon = new ImageIcon("hook.png");
		ImageIcon unhookIcon = new ImageIcon("unhook.png");
		
		
		//=====================================================================================
		setTitle("Keystrokes");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		
		//=============================================================================
		JTextPane textPaneKeylog = new JTextPane();
		textPaneKeylog.setBounds(20, 16, 235, 288);
		//==============================================================================
		
		
		
		JButton HookButton = new JButton("HOOK",hookIcon);
		HookButton.setBounds(265,16,155,72);
		HookButton.setOpaque(true);
		HookButton.setForeground(new Color(0xff007f));
		HookButton.setBackground(new Color(0xf4bfc7));
		HookButton.setBorder(BorderFactory.createLineBorder(new Color(0x8732a8),3));
		HookButton.setFont(new Font("Consolas",Font.BOLD,18));
		HookButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.Hook();
			}
		});
		contentPane.setLayout(null);
		contentPane.add(HookButton);
		
		JButton UnhookButton = new JButton("UNHOOK",unhookIcon);
		
		UnhookButton.setBounds(265,88,155,72);
		UnhookButton.setOpaque(true);
		UnhookButton.setForeground(new Color(0xff007f));
		UnhookButton.setBackground(new Color(0xf4bfc7));
		UnhookButton.setBorder(BorderFactory.createLineBorder(new Color(0x8732a8),3));
		UnhookButton.setFont(new Font("Consolas",Font.BOLD,18));
		UnhookButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					c.UnHook();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		contentPane.add(UnhookButton);
		
		JButton viewButton = new JButton("PRINT",printIcon);
		
		viewButton.setBounds(265,160,155,72);
		viewButton.setOpaque(true);
		viewButton.setForeground(new Color(0xff007f));
		viewButton.setBackground(new Color(0xf4bfc7));
		viewButton.setBorder(BorderFactory.createLineBorder(new Color(0x8732a8),3));
		viewButton.setFont(new Font("Consolas",Font.BOLD,18));
		viewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pre = textPaneKeylog.getText();
				
				String keylognew = c.ViewKeyStroker();
				
				textPaneKeylog.setText( pre + " " + keylognew );
				
			}
		});
		contentPane.add(viewButton);
		
		JButton ClearButton = new JButton("DELETE",delIcon);
		
		ClearButton.setBounds(265,232,155,72);
		ClearButton.setOpaque(true);
		ClearButton.setForeground(new Color(0xff007f));
		ClearButton.setBackground(new Color(0xf4bfc7));
		ClearButton.setBorder(BorderFactory.createLineBorder(new Color(0x8732a8),3));
		ClearButton.setFont(new Font("Consolas",Font.BOLD,18));
		ClearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textPaneKeylog.setText(null);
			}
		});
		contentPane.add(ClearButton);
		contentPane.add(textPaneKeylog);
	}
}
