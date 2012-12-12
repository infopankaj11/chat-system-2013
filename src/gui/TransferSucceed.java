package gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TransferSucceed extends JFrame{
	private static final long serialVersionUID = 1234L;
	private JPanel mainPanel;
	private JLabel msg;
	
	public TransferSucceed(){
		initComponents();
		pack();
		setVisible(true);
		setSize(300,150);
	}
	
	public void initComponents(){
		mainPanel=new JPanel(new BorderLayout());
		msg=new JLabel("Send file terminat!!");
		
		mainPanel.add(msg,BorderLayout.CENTER);
		getContentPane().add(mainPanel);
	}

}

