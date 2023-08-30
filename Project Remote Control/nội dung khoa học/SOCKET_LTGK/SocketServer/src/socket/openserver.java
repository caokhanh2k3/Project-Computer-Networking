package socket;

import java.awt.AWTException;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.github.kwhat.jnativehook.NativeHookException;

//import com.github.kwhat.jnativehook.NativeHookException;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class openserver extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					openserver frame = new openserver();
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
	public openserver() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 367, 232);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton buttonopenserver = new JButton("OPEN SERVER");
		buttonopenserver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  Server sv = new Server();
			        
			       try {
			    	 
					sv.server();
					
				} catch (AWTException | InterruptedException | NativeHookException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		buttonopenserver.setBounds(113, 48, 130, 88);
		contentPane.add(buttonopenserver);
	}

}
