package gui;

import java.awt.*;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.border.Border;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;


import java.awt.*;  
import java.awt.event.*;  
import javax.swing.*;  

import controller.ChatController;

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
	
	private ChatController c;
	public GUI(ChatController c){
		this.c=c;
		pack();
		this.setSize(1000, 600);
		this.intComponents();
		this.setVisible(true);
		this.setTitle("Welcome to chat system !!!");
	}

	
	public void intComponents(){
		Border blueline = BorderFactory.createLineBorder(Color.blue,1);
		
		panelPrinciple=new JPanel(new BorderLayout(10,10));
		panelLeft=new JPanel(new BorderLayout());
		panelLeftTop=new JPanel(new FlowLayout());
		panelLeftMiddle=new JPanel(new FlowLayout());
		panelLeftBottom=new JPanel(new BorderLayout());
		panelMiddle=new JPanel(new BorderLayout(5,5));
		panelRightBottom=new JPanel(new FlowLayout());
		
		panelRight=new JPanel(new BorderLayout());
		labelUser=new JLabel("User : ");
		textUser=new JTextArea();
		textUser.setEnabled(true);
		buttonConnect=new JButton("Connect");
		buttonDisconnect=new JButton("Disconnect");
		buttonDisconnect.setEnabled(false);
		userConnect=new JLabel("Connected users : ");
		userPanel=new JTextArea();
		buttonConversion=new JButton("Add use(s) to conversion !");
		textMiddleTop=new JTextArea();
		msg=new JTextArea();
		participates=new JTextArea();
		buttonSend=new JButton("Send");
		buttonBrowse=new JButton("Browse");
		labelParticipates=new JLabel("Participates : ");

		userPanel.setBorder(blueline);
		textUser.setBorder(blueline);
		textMiddleTop.setBorder(blueline);
		participates.setBorder(blueline);
		buttonSend.setPreferredSize(new Dimension(130,30));
		buttonBrowse.setPreferredSize(new Dimension(130,30));
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
		
		buttonConnect.addActionListener(new ConnexionListener());
		buttonDisconnect.addActionListener(new DixconnexionListener());
		
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
		
		panelRightBottom.add(buttonSend);
		panelRightBottom.add(buttonBrowse);
			
		panelRight.add(labelParticipates,BorderLayout.NORTH);
		panelRight.add(participates,BorderLayout.CENTER);
		panelRight.add(panelRightBottom,BorderLayout.SOUTH);
		
		
		panelPrinciple.add(panelLeft,BorderLayout.WEST);
		panelPrinciple.add(panelRight,BorderLayout.EAST);
		panelPrinciple.add(panelMiddle,BorderLayout.CENTER);
		getContentPane().add(panelPrinciple);

	}
	
	 class DixconnexionListener implements ActionListener{
		 public void actionPerformed(ActionEvent a){
			 textUser.setText(" ");
			 buttonConnect.setEnabled(true);
			 buttonDisconnect.setEnabled(false);	
			 c.controlDisconnexion();
		 }    
	}
	 
	 class ConnexionListener implements ActionListener{
		 public void actionPerformed(ActionEvent a){
			 c.setLocalUser(textUser.getText());
			 buttonConnect.setEnabled(false);
			 buttonDisconnect.setEnabled(true);
			 c.lancheNi();
		 }    
	}
		    
	public void displayMsg(String message){
		textMiddleTop.append(message);	
//		userPanel.setText(c.getLocalUser().getUserName());
	}
}
