package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

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
	private JLabel labelAcceptFile;
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
		panelButton=new JPanel(new FlowLayout());
		buttonAcceptNow=new JButton("Accept Now !");
		buttonAcceptLater=new JButton("Accept Later !");
		buttonRefuse=new JButton("Refuse !");
		panelText=new JPanel(new BoxLayout(panelText,BoxLayout.Y_AXIS));
		labelAcceptFile=new JLabel("Do you want to accept this file ?");
		labelFileName=new JLabel("Name : " + file);
		labelFileSize=new JLabel("Size : " + fileSize + " bytes");
		labelFileID=new JLabel("ID : "+ id);
		labelFileFrom=new JLabel("From : " + username);
		
		System.out.println("File name 3: "+file);
		
		
		panelText.add(labelAcceptFile);
		panelText.add(labelFileName);
		panelText.add(labelFileSize);
		panelText.add(labelFileID);
		panelText.add(labelFileFrom);
				
		panelButton.add(buttonRefuse);
		panelButton.add(buttonAcceptLater);
		panelButton.add(buttonAcceptNow);
		
		mainPanel.add(panelText,BorderLayout.NORTH);
		mainPanel.add(panelButton,BorderLayout.SOUTH);
		
		getContentPane().add(mainPanel);

	}

}
