package gui;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.FileSystemView;

import model.LocalUserModel;
import model.RemoteUser;
import model.TalkUserModel;

import java.awt.event.*;  
import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import controller.ChatController;

public class GUI extends JFrame{
       
        private static final long serialVersionUID = 1234L;
        private Vector<String> liste=new Vector<String>();
        private InetAddress[] listP;
        private String userToSendFile;
        private boolean connected;
        
        public boolean isConnected() {
			return connected;
		}

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
        JScrollPane jScrollPane1;
        JScrollPane jScrollPane2;
        JList userPanel;
        JButton buttonConversion;
        JTabbedPane tabMiddleTop;
        JTextArea textMiddleTop;
        JTextArea msg;
        JTextArea participates;
        JLabel labelParticipates;
        JButton buttonSend;
        JButton buttonBrowse;
  //      JButton buttonAddToFile;
        
   		private File file = null;  
		private String path = null; 
       
        private ChatController c;
        private LocalUserModel localUser;
        
        
        
        public GUI(){
                pack();
                this.setSize(1000, 600);
                this.intComponents();
                this.setVisible(true);
                this.setTitle("Welcome to chat system !!!");
        }
        
        public void setController(ChatController c) {
            this.c=c;
        }
        
        public void setLocalUser(LocalUserModel l) {
            localUser=l;
            this.userPanel.setModel(localUser.getRemoteUsers());
        }
     
        public String getPath() {
			return path;
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
                userPanel=new JList();
                jScrollPane1 = new JScrollPane();
                jScrollPane2 = new JScrollPane();
 //               buttonAddToFile = new JButton();
               
//                userPanel.setModel(new AbstractListModel() {
//					private static final long serialVersionUID = 1234L;
//					String[] strings = {  };
//                    public int getSize() { return strings.length; }
//                    public Object getElementAt(int i) { return strings[i]; }});
                jScrollPane1.setViewportView(userPanel);
                buttonConversion=new JButton("Add use(s) to conversion !");
                buttonConversion.setEnabled(false);
                tabMiddleTop=new JTabbedPane(JTabbedPane.TOP);
                textMiddleTop=new JTextArea();
 //               tabMiddleTop.add(textMiddleTop);
                tabMiddleTop.addTab("Talk", textMiddleTop);
                msg=new JTextArea();
                participates=new JTextArea();
 //               this.participates.setModel(localUser.getParticipeUsers());
//                participates.setModel(new AbstractListModel() {
//					private static final long serialVersionUID = 1234L;
//					String[] strings = {  };
//                    public int getSize() { return strings.length; }
//                    public Object getElementAt(int i) { return strings[i]; }});
                jScrollPane2.setViewportView(participates);
                
                buttonSend=new JButton("Send");
                buttonBrowse=new JButton("Browse");
                labelParticipates=new JLabel("Participates : ");

                userPanel.setBorder(blueline);
                textUser.setBorder(blueline);
        //        tabMiddleTop.setBorder(blueline);
                participates.setBorder(blueline);
                jScrollPane1.setPreferredSize(new Dimension(150,425));
                jScrollPane2.setPreferredSize(new Dimension(150,200));
                buttonSend.setPreferredSize(new Dimension(130,30));
                buttonBrowse.setPreferredSize(new Dimension(130,30));
                labelParticipates.setPreferredSize(new Dimension(100,30));
                
            //    participates.setPreferredSize(new Dimension(230,200));
                textMiddleTop.setEnabled(false);
                buttonSend.setEnabled(false);
                buttonBrowse.setEnabled(false);
                msg.setBorder(blueline);
                tabMiddleTop.setPreferredSize(new Dimension(100,500));
                labelUser.setPreferredSize(new Dimension(60,30));
                textUser.setPreferredSize(new Dimension(200,30));
                buttonConnect.setPreferredSize(new Dimension(130,30));
                buttonDisconnect.setPreferredSize(new Dimension(130,30));
                userPanel.setPreferredSize(new Dimension(260,430));
                buttonConversion.setPreferredSize(new Dimension(260,30));
               
                buttonSend.addActionListener(new SendListener());
                buttonConnect.addActionListener(new ConnexionListener());
                buttonDisconnect.addActionListener(new DixconnexionListener());
                buttonConversion.addActionListener(new AddToConversionListener());
                buttonBrowse.addActionListener(new BrowseListener());
                
                panelLeftTop.add(labelUser);
                panelLeftTop.add(textUser);
               
                panelLeftMiddle.add(buttonConnect);
                panelLeftMiddle.add(buttonDisconnect);
               
                panelLeftBottom.add(userConnect,BorderLayout.NORTH);
              //  panelLeftBottom.add(userPanel,BorderLayout.CENTER);
                panelLeftBottom.add(jScrollPane1,BorderLayout.CENTER);
                panelLeftBottom.add(buttonConversion,BorderLayout.SOUTH);
    //           panelLeftBottom.add(buttonAddToFile,BorderLayout.SOUTH);
               
                panelLeft.add(panelLeftTop,BorderLayout.NORTH);
                panelLeft.add(panelLeftMiddle,BorderLayout.CENTER);
                panelLeft.add(panelLeftBottom,BorderLayout.SOUTH);
               
                panelMiddle.add(tabMiddleTop,BorderLayout.NORTH);
                panelMiddle.add(msg,BorderLayout.CENTER);
               
                panelRightBottom.add(buttonSend);
                panelRightBottom.add(buttonBrowse);
                       
                panelRight.add(labelParticipates,BorderLayout.NORTH);
                panelRight.add(jScrollPane2,BorderLayout.CENTER);
                panelRight.add(panelRightBottom,BorderLayout.SOUTH);
               
               
                panelPrinciple.add(panelLeft,BorderLayout.WEST);
                panelPrinciple.add(panelRight,BorderLayout.EAST);
                panelPrinciple.add(panelMiddle,BorderLayout.CENTER);
                getContentPane().add(panelPrinciple);

        }
       
         class DixconnexionListener implements ActionListener{
                 public void actionPerformed(ActionEvent a){ 
                	 	 c.logout();    
                	 	textUser.setText(" ");
                	 	textMiddleTop.setText(" ");                	 	 
                 }    
        }
         
         class ConnexionListener implements ActionListener{
                 public void actionPerformed(ActionEvent a){                        
                         c.getNetwork().getUdpR().start();
                         c.login(textUser.getText());
                 }    
        }
         
         public void connectedMode(){
             userPanel.setEnabled(true);
             buttonConnect.setEnabled(false);
             buttonDisconnect.setEnabled(true);
             buttonConversion.setEnabled(true);
             buttonSend.setEnabled(true);
             buttonBrowse.setEnabled(true);
             textUser.setEnabled(false);
             c.getNetwork().getUdpR().setActive(true);         
         }
         
         public void disconnectedMode(){
        	 textUser.setText(" ");
        	 textUser.setEnabled(true);
             buttonConnect.setEnabled(true);
             buttonDisconnect.setEnabled(false);    
             buttonConversion.setEnabled(false);
             buttonSend.setEnabled(false);
             buttonBrowse.setEnabled(false);
             c.getNetwork().getUdpR().setActive(false);
         }
         
         class SendListener implements ActionListener{
        	 public void actionPerformed(ActionEvent a){
        		 Date d = new Date();
        		 String s = msg.getText();
        		 textMiddleTop.append("You say : " + s +"\n");
        		 msg.setText(" ");
        		 c.controlSendText(s, listP);
        	 }
         }
         
        class AddToConversionListener implements ActionListener{
        	public void actionPerformed(ActionEvent a){
                ArrayList<RemoteUser> r=new ArrayList<RemoteUser>();
                Object[] o=userPanel.getSelectedValues();
                listP=new InetAddress[o.length];
                for(int i=0;i<o.length;i++)
                {
                    r.add((RemoteUser)o[i]);
                }
                for(int j=0;j<r.size();j++){
                	listP[j]=r.get(j).getAddressIP();
                	 System.out.println("Test of send message : " +listP[j]);
                }
                participates.setText(r.toString());
        	}
        }
        
        class BrowseListener implements ActionListener{
        	public void actionPerformed(ActionEvent a){
        		int result=0;
        		int lengthFile=0;
        		int id =0;
        		RemoteUser user;
        		JFileChooser fileChooser = new JFileChooser();  
        		FileSystemView fsv = FileSystemView.getFileSystemView();  
        		System.out.println(fsv.getHomeDirectory());              
	        	fileChooser.setCurrentDirectory(fsv.getHomeDirectory());  
	        	fileChooser.setDialogTitle("Select the file to upload...");  
	        	fileChooser.setApproveButtonText("OK");  
	        	fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);  
	        	result = fileChooser.showOpenDialog(panelPrinciple);  
	        	if (JFileChooser.APPROVE_OPTION == result) {  
	        		file=fileChooser.getSelectedFile();
	        		path=fileChooser.getSelectedFile().getPath();  
	        		System.out.println("path: "+path);  
	        	}
	        	Object o=userPanel.getSelectedValues();
	        	//user=(RemoteUser)o;
	        	userToSendFile="192.168.1.17";
	        	c.controlPropFile(file.getName(), file.length(), id, userToSendFile);
	        	System.out.println("File name : "+file.getName());
	        	id++;
            }  
        }
  
//         public void deleteUserFromParticipates(){
//             this.listeP.remove(c.getLocalUser().getUsername()); 
//             participates.setListData(this.listeP);
//         }
         
        public void displayMsg(String message){
             textMiddleTop.append(message); 
        }
        
//        public void addNewTalk(){
//        	textMiddleTop.add(tabMiddleTop);
//        }
}
