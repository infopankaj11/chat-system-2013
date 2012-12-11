package network;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

import controller.ChatController;
import signals.*;

/**
 * Cette classe permet de definir les signaux et gerer les sockets
 * @author yingqing2guang
 *
 */
public class Network {
	/**
	 * Definir du port d'ecoute pour le socket UDP
	 */
	final static  int portDest=5500;
	
	/**
	 * Definir du port d'ecoute pour le socket TCP
	 */
	final static int portDestTCP=6500;
	
	/**Creation d'un UDPSender**/
	private  UDPSender udpS= new UDPSender(8000);
	private ChatController c;
	private UDPReceiver udpR;
//	private TCPClient tcpClient;
//	private TCPServer tcpServer;

	/**
	 * Construire un network, une fois on a le ni, on lance le UDP reciever
	 */
	public Network (ChatController c){
		udpR = new UDPReceiver(c);              
	}      
      
	/**
	 * get UdpR
	 * @return udpR
	 */
	public UDPReceiver getUdpR() {
		return udpR;
	}

	/**
	 * set ChatControlleur
	 * @param c
	 */
	public void setC(ChatController c) {
		this.c = c;
	}
	
	/**
	 * Definition de signal Hello.On envoit hello en broadcast avec le socket UDP
	 * sur le port d'ecoute predefini.  
	 */
	public void signal_Hello(){
		Signal signalHello=new Hello(c.getLocalUser().getUsername());
		try {
			udpS.sendSignal(InetAddress.getByName("255.255.255.255"),signalHello,portDest);
		} catch (UnknownHostException e) {
			Logger.getLogger(Network.class.getName()).log(Level.SEVERE, null, e);
			System.out.println("Unknown user for Hello!!");
		}
	}
      
	/**
	 * Definition de signal GoodBye.On envoit hello en broadcast avec le socket UDP
	 * sur le port d'ecoute predefini.  
	 */
	public void signal_Bye(){
		Signal signalBye=new GoodBye();
		try {
			udpS.sendSignal(InetAddress.getByName("255.255.255.255"),signalBye,portDest);
		} catch (UnknownHostException e) {
			Logger.getLogger(Network.class.getName()).log(Level.SEVERE, null, e);
			System.out.println("Unknown user for GoodBye!!");
		}
	}
       
	/**
	 * Definition de signal HelloReply.On envoit helloReply a la personne qui
	 * nous envoit Hello avec le socket UDP sur le port d'ecoute predefini.  
	 * @param address
	 */
	public void signal_Hello_Reply(String address){
		Signal signalHelloReply=new HelloReply(c.getLocalUser().getUsername());   
		try {
			udpS.sendSignal(InetAddress.getByName(address), signalHelloReply, portDest);
		} catch (UnknownHostException e) {
			Logger.getLogger(Network.class.getName()).log(Level.SEVERE, null, e);
			System.out.println("Unknown user for HelloReply!!");
		}            
	}

	/**
	 * Definition de Text.On envoit text a des personnes avec le socket UDP
	 * sur le port d'ecoute predefini. On passe en argument le message et un tableau
	 * d'adresse ip de remote user. Si ce tableau vaut null, on envoit a tout le monde
	 * @param UserIp
	 * @param msg
	 */   
	public void signal_Send_Text(InetAddress[] UserIp, String msg){
		try {
			if(UserIp==null){
				Signal sendText= new SendText(msg,UserIp);
				udpS.sendSignal(InetAddress.getByName("255.255.255.255"),sendText,portDest);
			}
			else{
				Signal sendText= new SendText(msg,UserIp);
				for(int i=0;i<UserIp.length;i++){
					System.out.println("length of address : " + UserIp.length);
					udpS.sendSignal(UserIp[i],sendText,portDest);
				}
			}
		} catch (UnknownHostException e) {
			Logger.getLogger(Network.class.getName()).log(Level.SEVERE, null, e);
			System.out.println("Unknown user foe SendText!!");
		}  
	} 
      
	/**
	 * Demander le login de utilisateur si on le connait pas avant!
	 */
	public void signal_ask_login(String name){
		Signal AskLogin=new AskLogin();
		try {
			udpS.sendSignal(InetAddress.getByName(name),AskLogin,portDest);
		} catch (UnknownHostException e) {
			Logger.getLogger(Network.class.getName()).log(Level.SEVERE, null, e);
			System.out.println("Unknown user for AskLogin!!");
		}
	}
	/**
	 * Definir le siganl propFile
	 * @param filename
	 * @param filesize
	 * @param file_id
	 * @param address
	 */
	public void signal_Propo_File(String filename, long filesize, int file_id, String address){
		Signal PropFile=new PropFile(filename,filesize,file_id);
		try {
			udpS.sendSignal(InetAddress.getByName(address), PropFile, portDest);
		} catch (UnknownHostException e) {
			Logger.getLogger(Network.class.getName()).log(Level.SEVERE, null, e);
			System.out.println("Unknown user foe PropFile!!");
		}
	}
      
	/**
	 * Definir le siganl acceptFile
	 * @param fId
	 * @param acc
	 * @param now
	 * @param address
	 */
	public void siganl_accept_file(int fId, boolean acc, boolean now,String address){
		Signal AcceptFile=new AcceptFile(fId,acc,now);
		try {
			udpS.sendSignal(InetAddress.getByName(address), AcceptFile, portDest);
		} catch (UnknownHostException e) {
			Logger.getLogger(Network.class.getName()).log(Level.SEVERE, null, e);
			System.out.println("Unknown user for AcceptFiles!!");
		}
    }	  
}

