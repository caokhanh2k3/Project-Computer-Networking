package socket;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

public class Application extends JFrame {

	private JPanel contentPane;
	private JTable table;
//	private DefaultListModel<String> model = new DefaultListModel<>();
//	private JList<String> listapp = new JList<>( model );

	 
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client c= null;
					Application frame = new Application(c);
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
	@SuppressWarnings({ "unused", "serial" })
	public Application(Client c) {
		ImageIcon killIcon = new ImageIcon("kill.png");
		ImageIcon viewIcon = new ImageIcon("view.png");
		ImageIcon delappIcon = new ImageIcon("deleteprocess.png");
		ImageIcon startIcon = new ImageIcon("start.png");
		

		//=============================================================
		
		Object [][] tableData = {};
		
		DefaultTableModel model1;
		
		
		String [] tableCols = {"Application Name","Application ID"};
		model1 = new DefaultTableModel(tableData,tableCols)
		{
			@Override
  		public boolean isCellEditable(int row, int column)
  		{
     			return false;
  		}
		};
		
//		
		
		
		

		//=============================================================
		
		
		setTitle("Applications");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JButton ButtonViewApp = new JButton("VIEW",viewIcon);
		ButtonViewApp.setBounds(301, 6,135,59);
		ButtonViewApp.setOpaque(true);
		ButtonViewApp.setForeground(new Color(0xff007f));
		ButtonViewApp.setBackground(new Color(0xf4bfc7));
		ButtonViewApp.setBorder(BorderFactory.createLineBorder(new Color(0x8732a8),3));
		ButtonViewApp.setFont(new Font("Consolas",Font.BOLD,18));
		
		ButtonViewApp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					c.ListApp();
					
					
					File file = new File( "applications.txt");
					 BufferedReader brf
			         = new BufferedReader(new FileReader(file));
					 
					 String k ;
					 while ((k = brf.readLine()) != null)
					 {
						 
						 k = k.trim();
						 
						 String [] words=k.split("\\s+");
						
						 Object[] newobj = {words[0],words[1]};
						 model1.addRow(newobj);   
						 
					 } 
					
			        
					
					 
					
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		contentPane.setLayout(null);
		contentPane.add(ButtonViewApp);
		
		JButton ButtonKillApp = new JButton("KILL",killIcon);
		ButtonKillApp.setBounds(301, 124,135,59);
		ButtonKillApp.setOpaque(true);
		ButtonKillApp.setForeground(new Color(0xff007f));
		ButtonKillApp.setBackground(new Color(0xf4bfc7));
		ButtonKillApp.setBorder(BorderFactory.createLineBorder(new Color(0x8732a8),3));
		ButtonKillApp.setFont(new Font("Consolas",Font.BOLD,18));
		contentPane.add(ButtonKillApp);
		ButtonKillApp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                KillApp kapp = new KillApp(c);
				
                kapp.setVisible(true);
			}
		});
	
		
		JButton ButtonStartApp = new JButton("START",startIcon);
		ButtonStartApp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                StartApp Sapp = new StartApp(c);
				
                Sapp.setVisible(true);
			}
		});
		ButtonStartApp.setBounds(301, 65, 135,59);
		ButtonStartApp.setOpaque(true);
		ButtonStartApp.setForeground(new Color(0xff007f));
		ButtonStartApp.setBackground(new Color(0xf4bfc7));
		ButtonStartApp.setBorder(BorderFactory.createLineBorder(new Color(0x8732a8),3));
		ButtonStartApp.setFont(new Font("Consolas",Font.BOLD,18));
		contentPane.add(ButtonStartApp);
		
		JButton ButtonClear = new JButton("CLEAR",delappIcon);
		ButtonClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model1.setRowCount(0);
			}
		});
		ButtonClear.setBounds(301, 183, 135, 52);
		ButtonClear.setOpaque(true);
		ButtonClear.setForeground(new Color(0xff007f));
		ButtonClear.setBackground(new Color(0xf4bfc7));
		ButtonClear.setBorder(BorderFactory.createLineBorder(new Color(0x8732a8),3));
		ButtonClear.setFont(new Font("Consolas",Font.BOLD,18));
		contentPane.add(ButtonClear);
		//========================================================================================================
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 61, 363, 181);
		table = new JTable();
		table = new JTable(model1);
		table.setFont(new Font("Consolas",Font.PLAIN,15));
		table.setRowHeight(22);
		table.setEnabled(true);
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(27,10,261,243);
		scrollPane.setViewportView(table);

		contentPane.add(scrollPane);
		
				
	
		
		
	}
}
