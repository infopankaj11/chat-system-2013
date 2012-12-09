package controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import signals.GoodBye;
import signals.Hello;
import signals.HelloReply;
import signals.SendText;

import model.LocalUserModel;
import model.RemoteUser;

import network.Network;
import network.TCPClient;
import network.TCPServer;

import gui.GUI;
import gui.RefuseFile;

/**
 * Classe controlleur ! 
 * @author yingqing
 */
public class ChatController {
       
	private GUI gui;
	private Network network;
	private LocalUserModel localUser; 
	private RemoteUser user;
	@SuppressWarnings("unused")
	private RefuseFile fileRefuse;
	private String file;
	private boolean refuse;
	private boolean acceptNow;
	private TCPClient tcpC;
	private TCPServer tcpS;
              
	public ChatController(){}
         
	public TCPClient getTcpC() {
		return tcpC;
	}

	public void setTcpC(TCPClient tcpC) {
		this.tcpC = tcpC;
	}

	public TCPServer getTcpS() {
		return tcpS;
	}

	public void setTcpS(TCPServer tcpS) {
		this.tcpS = tcpS;
	}

	public void setRefuse(boolean refuse) {
		this.refuse = refuse;
	}
	
	public void setAcceptNow(boolean acceptNow) {
		this.acceptNow = acceptNow;
	}
	
	public boolean isRefuse() {
		return refuse;
	}

	public boolean isAcceptNow() {
		return acceptNow;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public RemoteUser getUser() {
		return user;
	}
	
	public void setUser(RemoteUser user) {
		this.user = user;
	}

	public void setLocalUser(LocalUserModel localUser) {
		this.localUser = localUser;
	}

	public void setNi(Network ni){
		this.network=ni;
	}
        
	public void setGui(GUI g) {
		this.gui = g;
	}
    
	public Network getNetwork() {
		return network;
	}
	
    public LocalUserModel getLocalUser() {
		return localUser;
	}

	/**
	 * Connecter un user et envoyer signal hello
	 * @param username
	 */
	public void login(String username){
		if(!localUser.isConnected()){
			localUser.setConnected(true);
			localUser.setUsername(username);
                 network.signal_Hello();
             }
		else{
			System.out.println("User already connected !!");
		}
	}
        
	/**
	 * Deconnecter d'un user et envoyer signal hello
	 */
	public void logout(){
		if(localUser.isConnected()){
			network.signal_Bye();
			localUser.setConnected(false);
			localUser.removeAllRemoteUser();
		}
		else{
			System.out.println("User already disconnected !!");
		}        	
	}
      
	/**
	 * Afficher le message Hello 
	 * @param h
	 * @param userName
	 */
	public void controlDisplayHello(Hello h,String userName){
		Date d = new Date();
		String s = h.getUsername() +" ( "+ userName + ") :  says Hello to everyone, at " +DateFormat.getTimeInstance().format(d) + "\n";
		gui.displayMsg(s);
    }
     
	/**
	 * Afficher le message goodBye
	 * @param b
	 * @param userName
	 */
	public void controlDisplayBye(GoodBye b,String userName){
		Date d = new Date();
		String s = userName +" : "+ " says Goodbye to everyone, at " +DateFormat.getTimeInstance().format(d) + "\n";
		gui.displayMsg(s);      
	}
    
	/**
	 * Afficher les texts
	 * @param t
	 * @param username
	 */
	public void controlDisplayText(SendText t,String username){
		Date d = new Date();
		String s = username +" : "+ " says " + t.getMessage()+ ", at " +DateFormat.getTimeInstance().format(d) + "\n";
		gui.addTab(username);
		gui.displayText(s);
	}
        
	/**
	 * Afficher HelloReply
	 * @param hr
	 * @param userName
	 */
	public void controlDisplayHelloReply(HelloReply hr,String userName){
		Date d = new Date();
		String s = hr.getUsername() +" ( "+ userName + ") :  says helloReply to you , at " +DateFormat.getTimeInstance().format(d) + "\n";
		gui.displayMsg(s);
	}
	
	/**
	 * Envoyer un helloReply quand on recoit Hello
	 * @param adress
	 */
	public void controlSendHelloReply(String adress){
		try {
			network.signal_Hello_Reply(adress);
			System.out.println("Test : "+ InetAddress.getByName(adress));
		} catch (UnknownHostException e) {
			Logger.getLogger(ChatController.class.getName()).log(Level.SEVERE, null, e);
			System.out.println("failed to send hello back !");
		}
	}
     
	/**
	 * Enoyer des texts quand on clique send 
	 * @param message
	 * @param users
	 */
	public void controlSendText(String message,InetAddress[] users){
		network.signal_Send_Text(users, message);
	}
       
	/**
	 * Envoyer signal PropFile
	 * @param filename
	 * @param filesize
	 * @param file_id
	 * @param address
	 */
	public void controlPropFile(String filename, long filesize, int file_id,String address){
		network.signal_Propo_File(filename, filesize, file_id, address);
	}
       
	/**
	 * Afficher l'interface d'accepter le fichier
	 * @param file
	 * @param fileSize
	 * @param id
	 * @param username
	 */
	public void dialogAcceptFile(String file,long fileSize,int id,String username){
		gui.optionFile(file, username, fileSize,id);
	}
        
	/**
	 * Marche pas!!!
	 * @param id
	 * @param accepted
	 * @param now
	 * @param user
	 */
	public void controlAcceptFile(int id, boolean accepted, boolean now, String user){	
		network.siganl_accept_file(id, accepted, now, user);
		tcpS=new TCPServer(this,"test.txt");
		tcpS.start();
	}
        
	/**
	 * Marche pas !!!!!!!!!!!!
	 * @param id
	 * @param accepted
	 * @param now
	 * @param user
	 */
	public void controlDisplayAcceptFile(int id, boolean accepted, boolean now, String user){
		if(accepted==false && now==false){
			fileRefuse=new RefuseFile();
		}
		else{
			if(accepted==true && now==true){
				try {
          		  tcpC=new TCPClient(InetAddress.getByName("192.168.1.17"));
            	  tcpC.sendFile("test.txt", InetAddress.getByName("192.168.1.17"));
				} catch (UnknownHostException e) {
					Logger.getLogger(ChatController.class.getName()).log(Level.SEVERE, null, e);
					System.out.println("UnKnown user for AcceptFile !");
				}
          	}
        }

	} 
}

