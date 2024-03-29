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
	private JTextArea textArea;
	private int index=0;
	private int idFile=0;
	private File fileToSend=null;

	JPanel panelPrinciple;
	JPanel panelLeft;
	JPanel panelMiddle;
 	JPanel panelRight;
	JPanel panelLeftTop;
	JPanel panelLeftMiddle;
	JPanel panelLeftBottom;
	JPanel panelRightBottom;
	JPanel panelTab;
	
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

	@SuppressWarnings("unused")
	private File file = null;  
	private String pathOpen = null; 
	private String pathSave=null;
	private ChatController c;
	private LocalUserModel localUser;
	
	/**
	 * Construire le GUI
	 */
    public GUI(){   
    	this.intComponents();
  //      this.setSize(550, 550);
        this.setVisible(true);
        this.setTitle("Welcome to chat system !!!");                
        pack(); 
        
        this.addWindowListener(new WindowAdapter(){
     	   public void windowClosing(WindowEvent we){
     		   c.logout();
     		   System.exit(0);
     	   }
     });
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
     
	
	public String getPathSave() {
		return pathSave;
	}

	/**
	 * get TextArea
	 * @return
	 */
	public JTextArea getTextArea() {                
		return textArea;
	}

	/**
	 * get Index
	 * @return
	 */
	public int getIndex() {
		return index;
	}
	
	/**
	 * Set Index
	 * @param index
	 */
	public void setIndex(int index) {
		this.index = index;
	}
	
	
	
	public File getFileToSend() {                
		return fileToSend;
	}

	/**
	 * get idFile
	 * @return
	 */
	public int getIdFile() {
		return idFile;
	}
	
	

	public String getPathOpen() {
		return pathOpen;
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
    	
    	labelUser=new JLabel("User : ");
    	userConnect=new JLabel("Connected users : ");
    	
    	textUser=new JTextArea();    	
    	msg=new JTextArea();
    	textMiddleTop=new JTextArea();
    	
    	buttonConnect=new JButton("Connect");
    	buttonDisconnect=new JButton("Disconnect");   	
    	buttonSend=new JButton("Send");
    	buttonBrowse=new JButton("Browse");
    	   	
    	userPanel=new JList();
    	
    	jScrollPane1 = new JScrollPane(userPanel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    	jScrollPane1.setViewportView(userPanel);

    	tabMiddleTop=new JTabbedPane(JTabbedPane.TOP);
    	tabMiddleTop.addTab("Talk", textMiddleTop);
    	
    	msg.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode()==KeyEvent.VK_ENTER){
					ArrayList<RemoteUser> r=new ArrayList<RemoteUser>();
					String s = msg.getText();
					displayText("You say : " + s +"\n",textArea);
					msg.setText(" ");
					Object[] o=userPanel.getSelectedValues();
					listP=new InetAddress[o.length];
					for(int i=0;i<o.length;i++){
						r.add((RemoteUser)o[i]);
		             }
					for(int j=0;j<r.size();j++){
						listP[j]=r.get(j).getAddressIP();			
					}
					c.controlSendText(s,listP);
				}				
			}

			@Override
			public void keyReleased(KeyEvent arg0) {				
			}

			@Override
			public void keyTyped(KeyEvent arg0) {				
			}
    		
    	});
    	
    	userPanel.setBorder(blueline);
    	textUser.setBorder(blueline);
    	msg.setBorder(blueline);
    	
    	jScrollPane1.setPreferredSize(new Dimension(250,400));
    	buttonSend.setPreferredSize(new Dimension(150,30));
    	buttonBrowse.setPreferredSize(new Dimension(150,30));
    	tabMiddleTop.setPreferredSize(new Dimension(350,400));
    	labelUser.setPreferredSize(new Dimension(70,30));
    	textUser.setPreferredSize(new Dimension(130,30));
    	buttonConnect.setPreferredSize(new Dimension(110,30));
    	buttonDisconnect.setPreferredSize(new Dimension(115,30));
    	userPanel.setPreferredSize(new Dimension(250,400));
    	userConnect.setPreferredSize(new Dimension(150,30));

    	buttonDisconnect.setEnabled(false);
    	textMiddleTop.setEnabled(false);
    	buttonSend.setEnabled(false);
    	buttonBrowse.setEnabled(false);
    	textUser.setEnabled(true);
    	            
    	buttonSend.addActionListener(new SendListener());
    	buttonConnect.addActionListener(new ConnexionListener());
    	buttonDisconnect.addActionListener(new DixconnexionListener());
    	buttonBrowse.addActionListener(new BrowseListener());
    	userPanel.addMouseListener(monMouseListener);
                
    	panelLeftTop.add(labelUser);
    	panelLeftTop.add(textUser);
               
    	panelLeftMiddle.add(buttonConnect);
    	panelLeftMiddle.add(buttonDisconnect);
               
    	panelLeftBottom.add(userConnect,BorderLayout.NORTH);
    	panelLeftBottom.add(jScrollPane1,BorderLayout.SOUTH);
               
    	panelLeft.add(panelLeftTop,BorderLayout.NORTH);
    	panelLeft.add(panelLeftMiddle,BorderLayout.CENTER);
    	panelLeft.add(panelLeftBottom,BorderLayout.SOUTH);
               
    	panelMiddle.add(tabMiddleTop,BorderLayout.NORTH);
    	panelMiddle.add(msg,BorderLayout.CENTER);
    	panelMiddle.add(panelRightBottom,BorderLayout.SOUTH);
               
    	panelRightBottom.add(buttonSend);
    	panelRightBottom.add(buttonBrowse);
                       
    	panelPrinciple.add(panelLeft,BorderLayout.WEST);
    	panelPrinciple.add(panelMiddle,BorderLayout.CENTER);
    	getContentPane().add(panelPrinciple);
	}
	
    
	/**GUI correspond a la mode connexion**/
	public void connectedMode(){
		userPanel.setEnabled(true);
		buttonConnect.setEnabled(false);
		buttonDisconnect.setEnabled(true);
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
        
	/** <-->
	 * ActionListener sur la boutton connexion. Quand on clique,
	 * on enoit un Hello a tout le monde
	 */
	class ConnexionListener implements ActionListener{
		public void actionPerformed(ActionEvent a){                        			
			if(textUser.getText().equals("")){
				System.out.println("You must enter a username !!");
			}
			else{
				c.getNetwork().getUdpR().start();
				c.login(textUser.getText());
			}
		}    
	}

	/**
	 * ActionListener sur la boutton send.en tappe les messages 
	 * et on clique send pour envoyer messages
	 */     
	class SendListener implements ActionListener{
		public void actionPerformed(ActionEvent a){
			ArrayList<RemoteUser> r=new ArrayList<RemoteUser>();
			String s = msg.getText();
			displayText("You say : " + s +"\n",textArea);
			msg.setText(" ");
			Object[] o=userPanel.getSelectedValues();
			listP=new InetAddress[o.length];
			for(int i=0;i<o.length;i++){
				r.add((RemoteUser)o[i]);
             }
			for(int j=0;j<r.size();j++){
				listP[j]=r.get(j).getAddressIP();			
			}
			c.controlSendText(s,listP);
		}
    }
       
	/**
	 * ActionListener sur la boutton browse
	 */ 
	class BrowseListener implements ActionListener{
		public void actionPerformed(ActionEvent a){
			int result=0;
			idFile=idFile+1; /**Identifiant de fichier, unique pour chaque fichier**/
			JFileChooser fileChooser = new JFileChooser();  
			FileSystemView fsv = FileSystemView.getFileSystemView();  
			System.out.println(fsv.getHomeDirectory());              
			fileChooser.setCurrentDirectory(fsv.getHomeDirectory());  
			fileChooser.setDialogTitle("Select the file to send...");  
			fileChooser.setApproveButtonText("OK");  
			fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);  
			result = fileChooser.showOpenDialog(panelPrinciple);  
			if (JFileChooser.APPROVE_OPTION == result){  
				fileToSend=fileChooser.getSelectedFile();
				pathOpen=fileChooser.getSelectedFile().getPath();  
				System.out.println("path of file selected: "+pathOpen);  				
			}
			RemoteUser o=(RemoteUser) userPanel.getSelectedValue();/**On recupere le user pour envoyer un fichier**/
			userToSendFile=o.getAddressIP().toString().substring(1,o.getAddressIP().toString().length());
			System.out.println("Fichier sent to : "+userToSendFile);
			c.controlPropFile(fileToSend.getName(), fileToSend.length(), idFile, userToSendFile);			
			System.out.println("File name : "+fileToSend.getName());
			System.out.println("File id : "+idFile);
		}  	
	}
	
	/**
	 * Double click sur une source de userPanel, un autre TabbedPane
	 * affiche
	 */
	 MouseListener monMouseListener = new MouseAdapter() {
	     public void mouseClicked(MouseEvent e) {
	    	 if (e.getClickCount() == 2) {	    		
	    		RemoteUser o=(RemoteUser) userPanel.getSelectedValue();
	    		if(getTabIndex(o.getUsername())==-1){
	    			addTab(o.getUsername());
	    		}
	    	}
	    }
	};
        
	/**
	 * Proposition d'enregistrement de fichier ou refuse de fichier
	 * @param file
	 * @param user
	 * @param size
	 * @param id
	 */
	public void optionFile(String file,String user,long size,int id,String ip){
		String path="";
		JFileChooser choose = new JFileChooser();
		choose.setDialogTitle("Save this file: "+file+ ". From: "+user+ ". size : "+ size+". id : "+id +" ?");
		int ret =choose.showOpenDialog(this);
		choose.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		System.out.println("file selection : "+choose.getFileSelectionMode());
		if(ret == JFileChooser.APPROVE_OPTION){
			pathSave = choose.getSelectedFile().getAbsolutePath();
			System.out.println("path : "+path);					
			c.controlAcceptFile(id,true,true,ip,file);
			System.out.println("Send file accepted !!");
			System.out.println("test id 2 : "+id);
		}
		if(ret==JFileChooser.CANCEL_OPTION){
			c.controlAcceptFile(id,false,false,ip,file);
			System.out.println("Send file refused !!");
		}
	}
     
	/**
	 * Display des messages.
	 * @param message
	 */
	public void displayMsg(String message){
		textMiddleTop.append(message); 
		textMiddleTop.setForeground(Color.BLACK);
	}
	
	/**
	 * Display des messages sur differents TabPane selon differentes
	 * personnes
	 */
	public void displayText(String message,JTextArea text){
		text.append(message);
		text.setForeground(createRandomColor());
	}

	
	public void creatTextArea(){
		this.textArea=new JTextArea();
		textArea.setBackground(Color.WHITE);
		textArea.setEnabled(false);
		textArea.setForeground(createRandomColor());
	}
	/**
	 * Ajouter un autre JTabbedPane quand on veux lancer une autre
	 * conversation
	 */
	public void addTab(String name){
		creatTextArea();
		tabMiddleTop.addTab(name,textArea);
		this.index=getTabIndex(name);
		tabMiddleTop.setBackgroundAt(index, createRandomColor());		
		textArea=(JTextArea) tabMiddleTop.getComponentAt(index);
	}
	
	/**
	 * Verifier si le tab existe deja dans le GUI
	 * @param name
	 * @return
	 */
	public int getTabIndex(String name){
		return tabMiddleTop.indexOfTab(name);
	}

	public void removeTab(int index){
		tabMiddleTop.remove(index);
	}
	/**
	 * Creation d'une couleur au hasard
	 * @return
	 */
    public Color createRandomColor() {  
        return new Color((new Double(Math.random() * 128)).intValue() + 128,  
                (new Double(Math.random() * 128)).intValue() + 128,  
                (new Double(Math.random() * 128)).intValue() + 128);  
    }  
}
