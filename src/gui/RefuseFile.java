package gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RefuseFile extends JFrame{
	private JPanel mainPanel;
	private JLabel msg;
	
	public RefuseFile(){
		initComponents();
		pack();
		setVisible(true);
		setSize(300,150);
	}
	
	public void initComponents(){
		mainPanel=new JPanel(new BorderLayout());
		msg=new JLabel("Send text refused !! You can send it later !! ;(");
		
		mainPanel.add(msg,BorderLayout.CENTER);
		getContentPane().add(mainPanel);
	}

}
