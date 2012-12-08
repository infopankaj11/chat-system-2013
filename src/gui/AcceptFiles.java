package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class AcceptFiles extends JFrame{
	
	private JPanel mainPanel;
	private JButton buttonAcceptNow;
	private JButton buttonAcceptLater;
	private JButton buttonRefuse;
	private JTextArea textAcceptFile;
	private JLabel labelFileName;
	private JLabel labelFileSize;
	private JLabel labelFileID;
	private JLabel labelFileFrom;
	private JPanel panelButton;
	private JPanel panelText;
	
	public AcceptFiles(String file,long fileSize,int id, String username){
		System.out.println("File name 4: "+file);
		initComponents(file,fileSize,id,username);
		this.setVisible(true);
		pack();
		setSize(400,200);
		
	}
	
	public void initComponents(String file,long fileSize,int id, String username){
		mainPanel=new JPanel(new BorderLayout());
		mainPanel.setBackground(Color.WHITE);
		panelButton=new JPanel(new FlowLayout());
		buttonAcceptNow=new JButton("Accept Now !");
		buttonAcceptLater=new JButton("Accept Later !");
		buttonRefuse=new JButton("Refuse !");
		textAcceptFile=new JTextArea("Do you want to accept this file ? \n" + "Name : " +file +"\n" + "Size : "+fileSize+"\n"+"ID : "+ id +"\n"+ "From: "+username);
		textAcceptFile.setEnabled(false);

		System.out.println("File name 3: "+file);
		
		buttonAcceptNow.addActionListener(new acceptNowListener());
		buttonRefuse.addActionListener(new refuseListener());
		buttonAcceptLater.addActionListener(new acceptLatertListener());
		
		panelButton.add(buttonRefuse);
		panelButton.add(buttonAcceptLater);
		panelButton.add(buttonAcceptNow);
		
		mainPanel.add(textAcceptFile,BorderLayout.NORTH);
		mainPanel.add(panelButton,BorderLayout.SOUTH);
		
		getContentPane().add(mainPanel);

	}

	class refuseListener implements ActionListener{
		 public void actionPerformed(ActionEvent a){ 
			 setVisible(false);
		 }
	}
	
	class acceptNowListener implements ActionListener{
		 public void actionPerformed(ActionEvent a){ 
			 
		 }
	}
	
	class acceptLatertListener implements ActionListener{
		 public void actionPerformed(ActionEvent a){ 
			 
		 }
	}
}
