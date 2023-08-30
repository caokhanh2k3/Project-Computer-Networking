package socket;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.function.Consumer;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;


@SuppressWarnings("serial")
public class process extends JFrame {


	private JPanel contentPane;
	
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client c = null;
					process frame = new process(c);
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
	
	
	
	
	public process(Client c) {

		ImageIcon killIcon = new ImageIcon("kill.png");
		ImageIcon viewIcon = new ImageIcon("view.png");
		ImageIcon delappIcon = new ImageIcon("deleteprocess.png");
		ImageIcon startIcon = new ImageIcon("start.png");
		
		   
		//===========================================================================
		
	    Object [][] tableData = {};
		
		DefaultTableModel model;
		
		

		String [] tableCols = {"Process Name","Process ID"};
		model = new DefaultTableModel(tableData,tableCols)
		{
			@Override
  		public boolean isCellEditable(int row, int column)
  		{
     			return false;
  		}
		};
		
		
		
		
		setTitle("PROCESS");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JButton buttonviewlistprocess = new JButton("VIEW",viewIcon);
		
		buttonviewlistprocess.setBounds(301, 6,135,59);
		buttonviewlistprocess.setOpaque(true);
		buttonviewlistprocess.setForeground(new Color(0xff007f));
		buttonviewlistprocess.setBackground(new Color(0xf4bfc7));
		buttonviewlistprocess.setBorder(BorderFactory.createLineBorder(new Color(0x8732a8),3));
		buttonviewlistprocess.setFont(new Font("Consolas",Font.BOLD,18));
		buttonviewlistprocess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					try {
						c.ListProcess();
						
						File file = new File( "process.txt");
						 @SuppressWarnings("resource")
						BufferedReader brf
				         = new BufferedReader(new FileReader(file));
						 
						String k ;
						int dem=0;
						 while ((k = brf.readLine()) != null)
						 {
							if(dem > 2)
							{
								 k = k.trim();
								
								String [] words=k.split("\\s+");
								if(6 == words.length)
								{
									Object[] newobj = {words[0],words[1]};
									 model.addRow(newobj); 
								}
								else
								{
									int temp = words.length;
									String NameProcess = "";
									for(int i=0;i<temp - 5;i++)
									{
										NameProcess = NameProcess+ words[i] + " ";
									}
									Object[] newobj = {NameProcess,words[temp - 5]};
									 model.addRow(newobj); 
								}
								

								
							}
							  dem++;
							 
						 } 
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
			}
		});
		contentPane.setLayout(null);
		contentPane.add(buttonviewlistprocess);
		
		
		JButton ButtonKillProcess = new JButton("KILL",killIcon);
		ButtonKillProcess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KillProcess kprc = new KillProcess(c);
				
				kprc.setVisible(true);
			}
		});
		ButtonKillProcess.setBounds(341, 88, 85, 35);
		ButtonKillProcess.setBounds(301, 124,135,59);
		ButtonKillProcess.setOpaque(true);
		ButtonKillProcess.setForeground(new Color(0xff007f));
		ButtonKillProcess.setBackground(new Color(0xf4bfc7));
		ButtonKillProcess.setBorder(BorderFactory.createLineBorder(new Color(0x8732a8),3));
		ButtonKillProcess.setFont(new Font("Consolas",Font.BOLD,18));
		contentPane.add(ButtonKillProcess);
		
		JButton btnNewButton = new JButton("CLEAR",delappIcon);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.setRowCount(0);
			}
		});
		btnNewButton.setBounds(301, 183, 135, 52);
		btnNewButton.setOpaque(true);
		btnNewButton.setForeground(new Color(0xff007f));
		btnNewButton.setBackground(new Color(0xf4bfc7));
		btnNewButton.setBorder(BorderFactory.createLineBorder(new Color(0x8732a8),3));
		btnNewButton.setFont(new Font("Consolas",Font.BOLD,18));
		contentPane.add(btnNewButton);
		
		JButton btnStarProcess = new JButton("START",startIcon);
		btnStarProcess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                StartProcess Sprc = new StartProcess(c);
				
				Sprc.setVisible(true);
			}
		});
		btnStarProcess.setBounds(301, 65, 135,59);
		btnStarProcess.setOpaque(true);
		btnStarProcess.setForeground(new Color(0xff007f));
		btnStarProcess.setBackground(new Color(0xf4bfc7));
		btnStarProcess.setBorder(BorderFactory.createLineBorder(new Color(0x8732a8),3));
		btnStarProcess.setFont(new Font("Consolas",Font.BOLD,18));
		contentPane.add(btnStarProcess);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 61, 363, 181);
		table = new JTable();
		table = new JTable(model);
		table.setFont(new Font("Consolas",Font.PLAIN,15));
		table.setRowHeight(22);
		table.setEnabled(true);
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(27,10,261,243);
		scrollPane.setViewportView(table);

		contentPane.add(scrollPane);
		
		
		
	
		
		
	}
}
