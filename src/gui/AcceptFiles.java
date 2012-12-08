package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

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
	private JLabel labelAcceptFile;
	private JPanel panelButton;
	
	public AcceptFiles(String file,long fileSize,int id){
		System.out.println("File name 4: "+file);
		initComponents(file,fileSize,id);
		this.setVisible(true);
		pack();
		setSize(400,200);
		
	}
	
	public void initComponents(String file,long fileSize,int id){
		mainPanel=new JPanel(new BorderLayout());
		panelButton=new JPanel(new FlowLayout());
		buttonAcceptNow=new JButton("Accept Now !");
		buttonAcceptLater=new JButton("Accept Later !");
		buttonRefuse=new JButton("Refuse !");
		labelAcceptFile=new JLabel("Do you want to accept this file : " + file + ", size : "+ fileSize + " bytes,  id : " + id);
		System.out.println("File name 3: "+file);
		
		panelButton.add(buttonRefuse);
		panelButton.add(buttonAcceptLater);
		panelButton.add(buttonAcceptNow);
		
		mainPanel.add(labelAcceptFile,BorderLayout.NORTH);
		mainPanel.add(panelButton,BorderLayout.SOUTH);
		
		getContentPane().add(mainPanel);

	}

}
