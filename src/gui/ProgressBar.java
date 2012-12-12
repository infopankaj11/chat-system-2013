package gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;

public class ProgressBar implements ChangeListener{
	 private JFrame f = null;
	 JProgressBar pro;
	 JLabel lab;
	 Timer timer;
	 JButton b;
	 
	 
	 public JFrame getF() {
		return f;
	}

	public ProgressBar(){
	  f = new JFrame("Send Files ...");
	  Container contentPane = f.getContentPane();
	  lab = new JLabel("",JLabel.CENTER);
	  pro = new JProgressBar();
	  pro.setOrientation(JProgressBar.HORIZONTAL);
	  pro.setMinimum(0);
	  pro.setMaximum(100);
	  pro.setValue(0);
	  pro.setStringPainted(true);
	  pro.addChangeListener(this);
	  pro.setPreferredSize(new Dimension(200,30));
	  JPanel pa = new JPanel();
	  
	  contentPane.add(pa,BorderLayout.NORTH);
	  contentPane.add(pro,BorderLayout.CENTER);
	  contentPane.add(lab,BorderLayout.SOUTH);
	  f.pack();
	  f.setVisible(true);
	  f.addWindowListener(new WindowAdapter(){
	   public void windowClosing(WindowEvent e){
	    System.exit(0);
	   }                    
	  });
	  
	 }
	
	 @Override
	 public void stateChanged(ChangeEvent e) {
	  // TODO Auto-generated method stub
	  int v = pro.getValue();
	  if(e.getSource() == pro)
	   lab.setText("In progress:"+Integer.toString(v)+"% ");
	 }
	
	 public void getProgress(int speed){
		 timer = new Timer(speed,(ActionListener) this);
		 timer.start();
		   int v = pro.getValue();
		   if(v < 100)
		    pro.setValue(++v);
		   else{
		    timer.stop();
		    pro.setValue(0);
		   }
	 }
//	 @Override
//	 public void actionPerformed(ActionEvent e) {
//	  // TODO Auto-generated method stub
//	  if(e.getSource() == b){
//	   timer.start();
//	  }
//	  if(e.getSource() == timer){
//	   int v = pro.getValue();
//	   if(v < 100)
//	    pro.setValue(++v);
//	   else{
//	    timer.stop();
//	    pro.setValue(0);
//	   }
//	  }
//	 }
	
	}
