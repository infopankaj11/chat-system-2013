package gui;

import java.awt.*;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.border.Border;


import java.awt.*;  
import java.awt.event.*;  
import javax.swing.*;  

public class GUI extends JFrame{
	
	private static final long serialVersionUID = 1234L;
	
	JPanel panelPrinciple;
	JPanel panelLeft;
	JPanel panelMiddle;
	JPanel panelRight;
	JPanel panelLeftTop;
	JPanel panelLeftMiddle;
	JPanel panelLeftBottom;
	JPanel panelRightBottom;
	
	JLabel labelUser;
	JTextArea textUser;
	JButton buttonConnect;
	JButton buttonDisconnect;
	JLabel userConnect;
	JTextArea userPanel;
	JButton buttonConversion;
	JTextArea textMiddleTop;
	JTextArea msg;
	JTextArea participates;
	JLabel labelParticipates;
	JButton buttonSend;
	JButton buttonBrowse;
	
	public GUI(String nom){
		super(nom);
		this.setSize(1000, 600);
		this.intComponents();
	}

	
	public void intComponents(){
		Border blackline = BorderFactory.createLineBorder(Color.black,2); 	
		Border blueline = BorderFactory.createLineBorder(Color.blue,2);
		
		panelPrinciple=new JPanel(new BorderLayout());
		panelLeft=new JPanel(new BorderLayout());
		panelLeftTop=new JPanel(new FlowLayout());
		panelLeftMiddle=new JPanel(new FlowLayout());
		panelLeftBottom=new JPanel(new BorderLayout());
		panelMiddle=new JPanel(new BorderLayout());
		panelRightBottom=new JPanel(new FlowLayout());
		
		panelRight=new JPanel(new BorderLayout());
		labelUser=new JLabel("User : ");
		textUser=new JTextArea();
		buttonConnect=new JButton("Connect");
		buttonDisconnect=new JButton("Disconnect");
		textUser.setEnabled(false);
		userConnect=new JLabel("Connected users : ");
		userPanel=new JTextArea();
		buttonConversion=new JButton("Add use(s) to conversion !");
		textMiddleTop=new JTextArea();
		msg=new JTextArea();
		participates=new JTextArea();
		buttonSend=new JButton("Send");
		buttonBrowse=new JButton("Browse");
		labelParticipates=new JLabel("Participates : ");

		panelLeft.setBorder(blackline);
		
		textUser.setBorder(blueline);
		userPanel.setBorder(blackline);
		textMiddleTop.setBorder(blackline);
		participates.setBorder(blackline);
		labelParticipates.setPreferredSize(new Dimension(100,30));
		participates.setPreferredSize(new Dimension(230,200));
		textMiddleTop.setEnabled(false);
		msg.setBorder(blueline);
		textMiddleTop.setPreferredSize(new Dimension(100,500));
		labelUser.setPreferredSize(new Dimension(60,30));
		textUser.setPreferredSize(new Dimension(200,30));
		buttonConnect.setPreferredSize(new Dimension(130,30));
		buttonDisconnect.setPreferredSize(new Dimension(130,30));
		userPanel.setPreferredSize(new Dimension(260,430));
		
		panelLeftTop.add(labelUser);
		panelLeftTop.add(textUser);
		
		panelLeftMiddle.add(buttonConnect);
		panelLeftMiddle.add(buttonDisconnect);
		
		panelLeftBottom.add(userConnect,BorderLayout.NORTH);
		panelLeftBottom.add(userPanel,BorderLayout.CENTER);
		panelLeftBottom.add(buttonConversion,BorderLayout.SOUTH);
		
		panelLeft.add(panelLeftTop,BorderLayout.NORTH);
		panelLeft.add(panelLeftMiddle,BorderLayout.CENTER);
		panelLeft.add(panelLeftBottom,BorderLayout.SOUTH);
		
		panelMiddle.add(textMiddleTop,BorderLayout.NORTH);
		panelMiddle.add(msg,BorderLayout.CENTER);
		
		panelRightBottom.add(buttonBrowse);
		panelRightBottom.add(buttonSend);
		
		panelRight.add(labelParticipates,BorderLayout.NORTH);
		panelRight.add(participates,BorderLayout.CENTER);
		panelRight.add(panelRightBottom,BorderLayout.SOUTH);
		
		
		panelPrinciple.add(panelLeft,BorderLayout.WEST);
		panelPrinciple.add(panelRight,BorderLayout.EAST);
		panelPrinciple.add(panelMiddle,BorderLayout.CENTER);
		getContentPane().add(panelPrinciple);
//		
//		Container c = getContentPane();
//		GroupLayout layout=new GroupLayout(c);
//		c.setLayout(layout);
//		
//		layout.setAutoCreateGaps(true);
//	    layout.setAutoCreateContainerGaps(true);
//	    
//	    GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
//	    hGroup.addGroup(layout.createParallelGroup().
//	             addComponent(labelUser).addComponent(buttonConnect));
//	    hGroup.addGroup(layout.createParallelGroup().
//	             addComponent(textUser).addComponent(buttonDisconnect));
//	    layout.setHorizontalGroup(hGroup);
//	    
//	    GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
//	    vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
//	            addComponent(labelUser).addComponent(textUser));
//	    vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
//	            addComponent(buttonConnect).addComponent(buttonDisconnect));
	    
//	    leftGroup.addGroup(layout.createSequentialGroup().
//	            addComponent(userConnect).addComponent(userPanel).addComponent(buttonConversion));
	    
//	    layout.setVerticalGroup(vGroup);
//	    
//	    panelPrinciple.add(panelLeft,BorderLayout.WEST);
//	    panelPrinciple.add(panelRight,BorderLayout.EAST);
//	    ((JFrame) c).getContentPane().add(panelPrinciple);
	}
	    
//	    GroupLayout.ParallelGroup leftTop = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
//	    leftTop.addComponent(labelUser);
//	    leftTop.addComponent(textUser);
//	    
//	    GroupLayout.ParallelGroup leftMiddle = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
//	    leftMiddle.addComponent(buttonConnect).addComponent(buttonDisconnect);
	    
//	    GroupLayout.ParallelGroup leftBottom = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
//	    leftBottom.addComponent(userConnect);
//	    leftBottom.addComponent(userPanel);
//	    leftBottom.addComponent(buttonConversion);
//	    
//	    layout.setVerticalGroup(layout.createSequentialGroup()
//	            .addGroup(leftTop).addGroup(leftMiddle).addGroup(leftBottom));
	    
//	    layout.setVerticalGroup(layout.createSequentialGroup() left = layout.createSequentialGroup();
//	    left.addGroup(leftTop).addGroup(leftMiddle).addGroup(leftBottom);
	    

//	    
//	}
		
		
		
		
		
//		this.setLayout(new BorderLayout());
//		JPanel panel1=new JPanel(new BorderLayout());
//		JPanel panel2=new JPanel();
//		JPanel panel3=new JPanel(new FlowLayout());
//		JPanel panel4=new JPanel();
//		GroupLayout layout=new GroupLayout(panel4);
//		layout.setAutoCreateGaps(true);
//	    layout.setAutoCreateContainerGaps(true);
//	    GroupLayout.ParallelGroup left = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
//	    left.addComponent(caseCheckBox);
//	    left.addComponent(wholeCheckBox);
//	    JScrollPane defile = new JScrollPane(panel2,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
//		
//		panel2.setBackground(Color.lightGray);
//		listUser.setBackground(Color.gray);
//		addCompForBorder(blackline, defile);
//		addCompForBorder(blackline, listUser);
//		msg.setPreferredSize(new Dimension(570,50));
//		msg.setBackground(Color.white);
//		addCompForBorder(blueline, msg);
//		buttonSend.setPreferredSize(new Dimension(90,50));
//		buttonBrowse.setPreferredSize(new Dimension(90,50));
//		
//		panel3.add(msg);
//		panel3.add(buttonSend);
//		panel3.add(buttonBrowse);
//		
//		panel1.add(boutonQuit,BorderLayout.NORTH);
//		panel1.add(panel3,BorderLayout.SOUTH);
//		panel1.add(panel2,BorderLayout.CENTER);
//		panel1.add(defile);
//		
//		this.add(listUser, BorderLayout.EAST);
//		this.add(panel1, BorderLayout.CENTER);	



}
