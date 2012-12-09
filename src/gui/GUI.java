package gui;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.*;  
import java.io.File;
import java.net.InetAddress;
import java.util.ArrayList;
import model.LocalUserModel;
import model.RemoteUser;


import controller.ChatController;

/**
 * Cette classe permet de definir l'interface graphique de chat system
 * @author yingqing
 */
public class GUI extends JFrame{
	private static final long serialVersionUID = 1234L;
	private InetAddress[] listP;
	private String userToSendFile;
	private boolean connected;
//	private ProgressBar bar;

	JPanel panelPrinciple;
	JPanel panelLeft;
	JPanel panelMiddle;
 	JPanel panelRight;
	JPanel panelLeftTop;
	JPanel panelLeftMiddle;
	JPanel panelLeftBottom;
	JPanel panelRightBottom;
//	JPanel panelTab;
	
	JLabel labelUser;
	JLabel userConnect;
	JLabel labelParticipates;
	
	JTextArea textUser;
	JTextArea textMiddleTop;
	JTextArea msg;
	JTextArea participates;
	
	JButton buttonConnect;
	JButton buttonDisconnect;
	JButton buttonSend;
	JButton buttonBrowse;
	JButton buttonConversion;
	
	JScrollPane jScrollPane1;
	JScrollPane jScrollPane2;
	
	JList userPanel;

	JTabbedPane tabMiddleTop;

	private File file = null;  
	private String path = null; 
	private ChatController c;
	private LocalUserModel localUser;
	
	/**
	 * Construire le GUI
	 */
    public GUI(){   
    	this.intComponents();
        this.setSize(550, 550);
        this.setVisible(true);
        this.setTitle("Welcome to chat system !!!");
        pack(); 
    }
        
    /**
     * get Connected
     * @return connected
     */
    public boolean isConnected() {
    	return connected;
	}
       
    /**
     * set ChatControlleur
     * @param c
     */
    public void setController(ChatController c) {
    	this.c=c;
    }
    
    /**
     * set LocalUser, definir le model de JList
     * @param l
     */
	public void setLocalUser(LocalUserModel l) {
		localUser=l;
		this.userPanel.setModel(localUser.getRemoteUsers());
	}
     
	/**
	 * get Path de fichier
	 * @return
	 */
	public String getPath() {
		return path;
	}

	/**
	 * Dessigner le GUI
	 */
	public void intComponents(){
		/**Border en bleu*/
		Border blueline = BorderFactory.createLineBorder(Color.blue,1);
               
		panelPrinciple=new JPanel(new BorderLayout(10,10));
		panelLeft=new JPanel(new BorderLayout());
		panelLeftTop=new JPanel(new FlowLayout());
		panelLeftMiddle=new JPanel(new FlowLayout());
		panelLeftBottom=new JPanel(new BorderLayout());
    	panelMiddle=new JPanel(new BorderLayout(5,5));
    	panelRightBottom=new JPanel(new FlowLayout());
 //   	panelTab=new JPanel();
//    	panelRight=new JPanel(new BorderLayout());
    	
    	labelUser=new JLabel("User : ");
    	userConnect=new JLabel("Connected users : ");
//    	labelParticipates=new JLabel("Participates : ");
    	
    	textUser=new JTextArea();    	
    	msg=new JTextArea();
//    	participates=new JTextArea();
    	textMiddleTop=new JTextArea();
    	
    	buttonConnect=new JButton("Connect");
    	buttonDisconnect=new JButton("Disconnect");   	
//    	buttonConversion=new JButton("Add user(s) to conversion !");
    	buttonSend=new JButton("Send");
    	buttonBrowse=new JButton("Browse");
    	   	
    	userPanel=new JList();
    	
    	jScrollPane1 = new JScrollPane();
 //   	jScrollPane2 = new JScrollPane();
    	jScrollPane1.setViewportView(userPanel);
 //   	jScrollPane2.setViewportView(participates);
    	
    	tabMiddleTop=new JTabbedPane(JTabbedPane.TOP);
    	tabMiddleTop.addTab("Talk", textMiddleTop);
    	
    	userPanel.setBorder(blueline);
    	textUser.setBorder(blueline);
 //   	participates.setBorder(blueline);
    	msg.setBorder(blueline);
    	
    	jScrollPane1.setPreferredSize(new Dimension(200,400));
//    	jScrollPane2.setPreferredSize(new Dimension(150,200));
    	buttonSend.setPreferredSize(new Dimension(150,30));
    	buttonBrowse.setPreferredSize(new Dimension(150,30));
//    	labelParticipates.setPreferredSize(new Dimension(100,30));
    	tabMiddleTop.setPreferredSize(new Dimension(350,400));
    	labelUser.setPreferredSize(new Dimension(70,30));
    	textUser.setPreferredSize(new Dimension(130,30));
    	buttonConnect.setPreferredSize(new Dimension(100,30));
    	buttonDisconnect.setPreferredSize(new Dimension(100,30));
    	userPanel.setPreferredSize(new Dimension(260,430));
//    	buttonConversion.setPreferredSize(new Dimension(260,30));
                
//    	buttonConversion.setEnabled(false);
    	buttonDisconnect.setEnabled(false);
    	textMiddleTop.setEnabled(false);
    	buttonSend.setEnabled(false);
    	buttonBrowse.setEnabled(false);
    	textUser.setEnabled(true);
    	            
    	buttonSend.addActionListener(new SendListener());
    	buttonConnect.addActionListener(new ConnexionListener());
    	buttonDisconnect.addActionListener(new DixconnexionListener());
//    	buttonConversion.addActionListener(new AddToConversionListener());
    	buttonBrowse.addActionListener(new BrowseListener());
                
    	panelLeftTop.add(labelUser);
    	panelLeftTop.add(textUser);
               
    	panelLeftMiddle.add(buttonConnect);
    	panelLeftMiddle.add(buttonDisconnect);
               
    	panelLeftBottom.add(userConnect,BorderLayout.NORTH);
    	panelLeftBottom.add(jScrollPane1,BorderLayout.CENTER);
//    	panelLeftBottom.add(buttonConversion,BorderLayout.SOUTH);
               
    	panelLeft.add(panelLeftTop,BorderLayout.NORTH);
    	panelLeft.add(panelLeftMiddle,BorderLayout.CENTER);
    	panelLeft.add(panelLeftBottom,BorderLayout.SOUTH);
               
    	panelMiddle.add(tabMiddleTop,BorderLayout.NORTH);
    	panelMiddle.add(msg,BorderLayout.CENTER);
    	panelMiddle.add(panelRightBottom,BorderLayout.SOUTH);
               
    	panelRightBottom.add(buttonSend);
    	panelRightBottom.add(buttonBrowse);
                       
//    	panelRight.add(labelParticipates,BorderLayout.NORTH);
//    	panelRight.add(jScrollPane2,BorderLayout.CENTER);
//    	panelRight.add(panelRightBottom,BorderLayout.SOUTH);
               
               
    	panelPrinciple.add(panelLeft,BorderLayout.WEST);
//    	panelPrinciple.add(panelRight,BorderLayout.EAST);
    	panelPrinciple.add(panelMiddle,BorderLayout.CENTER);
    	getContentPane().add(panelPrinciple);

	}
	
    
	/**GUI correspond a la mode connexion**/
	public void connectedMode(){
		userPanel.setEnabled(true);
		buttonConnect.setEnabled(false);
		buttonDisconnect.setEnabled(true);
//		buttonConversion.setEnabled(true);
		buttonSend.setEnabled(true);
		buttonBrowse.setEnabled(true);
		textUser.setEnabled(false);
		c.getNetwork().getUdpR().setActive(true);         
	}
      
	/**GUI correspond a la mode deconnexion**/
	public void disconnectedMode(){
		textUser.setText(" ");
		textUser.setEnabled(true);
		buttonConnect.setEnabled(true);
		buttonDisconnect.setEnabled(false);    
//		buttonConversion.setEnabled(false);
		buttonSend.setEnabled(false);
		buttonBrowse.setEnabled(false);
		c.getNetwork().getUdpR().setActive(false);
	}
       
	/**
	 * ActionListener sur la boutton deconnexion. Quand on clique,
	 * on enoit un GoodBye a tout le monde, et vider tout le panels
	 */
	class DixconnexionListener implements ActionListener{
		public void actionPerformed(ActionEvent a){ 
			c.logout();    
			textUser.setText(" ");
			textMiddleTop.setText(" ");  
			c.getNetwork().getUdpR().setActive(false);
		}    
	}
        
	/**
	 * ActionListener sur la boutton connexion. Quand on clique,
	 * on enoit un Hello a tout le monde
	 */
	class ConnexionListener implements ActionListener{
		public void actionPerformed(ActionEvent a){                        
			c.getNetwork().getUdpR().start();
			c.login(textUser.getText());
		}    
	}

	/**
	 * ActionListener sur la boutton send.en tappe les messages 
	 * et on clique send pour envoyer messages
	 */     
	class SendListener implements ActionListener{
		public void actionPerformed(ActionEvent a){
			ArrayList<RemoteUser> r=new ArrayList<RemoteUser>();
			tabMiddleTop.addTab("Talk2", textMiddleTop);
			String s = msg.getText();
			textMiddleTop.append("You say : " + s +"\n");
			msg.setText(" ");
			Object[] o=userPanel.getSelectedValues();
			listP=new InetAddress[o.length];
			for(int i=0;i<o.length;i++){
				r.add((RemoteUser)o[i]);
             }
			for(int j=0;j<r.size();j++){
				listP[j]=r.get(j).getAddressIP();
			}
//			participates.setText(r.toString());
			c.controlSendText(s, listP);
		}
    }
     
	/**
	 * ActionListener sur la boutton addToConversion
	 */ 
//	class AddToConversionListener implements ActionListener{
//		public void actionPerformed(ActionEvent a){
//			ArrayList<RemoteUser> r=new ArrayList<RemoteUser>();
//			Object[] o=userPanel.getSelectedValues();
//			listP=new InetAddress[o.length];
//			for(int i=0;i<o.length;i++){
//				r.add((RemoteUser)o[i]);
//             }
//			for(int j=0;j<r.size();j++){
//				listP[j]=r.get(j).getAddressIP();
//			}
//			participates.setText(r.toString());
//		}
//	}
        
	/**
	 * ActionListener sur la boutton browse
	 */ 
	class BrowseListener implements ActionListener{
		public void actionPerformed(ActionEvent a){
			int result=0;
			int id =0;
			JFileChooser fileChooser = new JFileChooser();  
			FileSystemView fsv = FileSystemView.getFileSystemView();  
			System.out.println(fsv.getHomeDirectory());              
			fileChooser.setCurrentDirectory(fsv.getHomeDirectory());  
			fileChooser.setDialogTitle("Select the file to upload...");  
			fileChooser.setApproveButtonText("OK");  
			fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);  
			result = fileChooser.showOpenDialog(panelPrinciple);  
			if (JFileChooser.APPROVE_OPTION == result){  
				file=fileChooser.getSelectedFile();
				path=fileChooser.getSelectedFile().getPath();  
				System.out.println("path: "+path);  
			}
			userToSendFile="192.168.1.17";
			c.controlPropFile(file.getName(), file.length(), id, userToSendFile);
			System.out.println("File name : "+file.getName());
			c.getTcpC().setFilepath(path);
			id++;
		}  	
	}
        
	/**
	 * Proposition d'enregistrement de fichier ou refuse de fichier
	 * @param file
	 * @param user
	 * @param size
	 * @param id
	 */
	public void optionFile(String file,String user,long size,int id){
		String path="";
		JFileChooser choose = new JFileChooser();
		choose.setDialogTitle("Save this file: "+file+ " From: "+user+ " size : "+ size+" id : "+id +" ?");
		int ret =choose.showOpenDialog(this);
		choose.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		System.out.println("file selection : "+choose.getFileSelectionMode());
		if(ret == JFileChooser.APPROVE_OPTION){
			path = choose.getSelectedFile().getAbsolutePath();
			System.out.println("path : "+path);
			c.controlAcceptFile(id,true,true,user);
			System.out.println("Send file accepted !!");
		}
		if(ret==JFileChooser.CANCEL_OPTION){
			c.controlAcceptFile(id,false,false,user);
			System.out.println("Send file refused !!");
		}
	}
     
	/**
	 * Display des messages.
	 * @param message
	 */
	public void displayMsg(String message){
		textMiddleTop.append(message); 
	}
}
