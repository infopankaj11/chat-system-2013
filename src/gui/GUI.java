package gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

public class GUI extends JPanel{
	
	private static final long serialVersionUID = 1234L;
	
	JPanel listUser=new JPanel();
	JButton buttonSend=new JButton("send");
	JButton buttonBrowse=new JButton("browse");
	JTextArea msg=new JTextArea();
	JButton boutonQuit=new JButton("I want to quit !!");
	
	GUI(){
		
		Border blackline = BorderFactory.createLineBorder(Color.black,3); 	
		Border blueline = BorderFactory.createLineBorder(Color.blue,3);
		
		this.setLayout(new BorderLayout());
		JPanel panel1=new JPanel(new BorderLayout());
		JPanel panel2=new JPanel();
		JPanel panel3=new JPanel(new FlowLayout());
		JScrollPane defile = new JScrollPane(panel2,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		panel2.setBackground(Color.lightGray);
		listUser.setBackground(Color.gray);
		addCompForBorder(blackline, defile);
		addCompForBorder(blackline, listUser);
		msg.setPreferredSize(new Dimension(570,50));
		msg.setBackground(Color.white);
		addCompForBorder(blueline, msg);
		buttonSend.setPreferredSize(new Dimension(90,50));
		buttonBrowse.setPreferredSize(new Dimension(90,50));
		
		panel3.add(msg);
		panel3.add(buttonSend);
		panel3.add(buttonBrowse);
		
		panel1.add(boutonQuit,BorderLayout.NORTH);
		panel1.add(panel3,BorderLayout.SOUTH);
		panel1.add(panel2,BorderLayout.CENTER);
		panel1.add(defile);
		
		this.add(listUser, BorderLayout.EAST);
		this.add(panel1, BorderLayout.CENTER);	
	}
		
	 void addCompForBorder(Border border, JComponent container) {  
	    container.setBorder(border);  
	    container.add(Box.createRigidArea(new Dimension(200, 800))); 
	 }  

}
