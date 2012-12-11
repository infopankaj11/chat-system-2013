package network;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.RemoteUser;
import controller.ChatController;
import signals.*;

/**
 * Cette classe permet de creer un socket UDP pour recevoir des sigaux
 * @author yingqing
 */
public class UDPReceiver extends Thread{
	private Signal sigal; 
    private ChatController c=new ChatController();
	/**
	 * Definir le port d'ecoute de socket
	 * @see portLocal
	 */
     private final int portLocal=5500;
     
     /**
      * Buffert utilise pour stockets les byte
      * @see buf
      */
     private byte[] buf = new byte[1024];
     
     /**
      * Boolean permet d'active ou deactiver le thread
      * @see active
      */
     private boolean active;
     
     /**
      * Constructeur permet de construire un socket UDP pour 
      * recevoir, qu'on on cree ce socket, on veux que le thread
      * est en mode d'ecoute!
      * @param c
      */
     public UDPReceiver(ChatController c){
         active=true;
         this.c=c;  
     }
     
     /**setteur de active
      * @param active
      */
     public void setActive(boolean active) {
         this.active = active;
     }
     
     /**
      * Cette methode permet de deserialiser des bytes en objet pour qu'on puisse
      * lire
      * @param sigToRead
      * @return sigal
      */
     public static Signal deserializeSignal(byte[] sigToRead){
        Signal sigal=null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(sigToRead);
            ObjectInputStream ois = new ObjectInputStream(bis);
            sigal = (Signal) ois.readObject();
        } catch (IOException e) {
        	Logger.getLogger(UDPReceiver.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("Object no deserialized");
        } catch (ClassNotFoundException e) {
        	Logger.getLogger(UDPReceiver.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("Object no deserialized");
        }
        return sigal;
    }
   
     /**
      * Methode permet d'activer le thread et on ecoute sur le reseau 
      * pour recevoir le signal. thread est active si boolean active vaut 
      * true, sinon, le thread est arrete, et on ne ecoute pas sur le reseau 
      */
    public void run(){
        DatagramSocket socketUDPReceive=null;        
        try {
              socketUDPReceive=new DatagramSocket(portLocal);
            } catch (SocketException e1) {
            	Logger.getLogger(UDPReceiver.class.getName()).log(Level.SEVERE, null, e1);
                System.out.println("creation of socket UDP receiver failed !!");
            }
                 
         while(active){        
        	 DatagramPacket packet=new DatagramPacket(buf,buf.length);
             try {
             socketUDPReceive.receive(packet);
             InetAddress adr = packet.getAddress();
             /**Conversion of the packet from Bytes to Array data*/
             ByteArrayInputStream bis = new ByteArrayInputStream(packet.getData());
             /**Conversion into an object*/
             ObjectInputStream received = new ObjectInputStream(bis);    
             try {
            	 String userDistant=adr.getHostAddress();
            	 System.out.println("userDistant : " +userDistant);
            	 String userLocal=InetAddress.getLocalHost().getHostAddress();
            	 System.out.println("userLocal : " +userLocal);
            	 RemoteUser remoteUser=c.getLocalUser().getRemoteUser(InetAddress.getByName(adr.getHostAddress()));
                 sigal = (Signal) received.readObject();
                 
                 /**
                  * Pour le signal Hello, on distingue si ce Hello provient de l'utilisateur distant
                  * ou celui local. si c'est local, on ne fait rien, sinon, on envoit un HelloReply 
                  * a la personne si nous envoit Hello! Et on ajoute cette personne dans la liste
                  * connecté! C'est pour ajouter des personnes qui se connectent avant moi ! 
                  */
                 if (sigal instanceof Hello){                  	                 	
                     if(userDistant.equals(userLocal)){}
                     else{
                    	 System.out.println(userDistant+ " is online!!");
                    	 c.controlDisplayHello((Hello)sigal,adr.getHostName());
                    	 c.getLocalUser().addRemoteUser(InetAddress.getByName(adr.getHostAddress()), ((Hello) sigal).getUsername());
                    	 c.controlSendHelloReply(adr.getHostAddress());
                     }                                              
                  }  
                 /**
                  * Pour le signal GoodBye, on distingue si ce GoodBye provient de l'utilisateur distant
                  * ou celui local. si c'est local, on ne fait rien, sinon, on la retire sur la liste !
                  */
                  if(sigal instanceof GoodBye){
                	  if(userDistant.equals(userLocal)){}
                	  else{
                		  /**Permet de recuperer le login associe a cette address ip */       		  
                		  c.controlDisplayBye((GoodBye)sigal,remoteUser.getUsername());
                          c.getLocalUser().removeRemoteUser(remoteUser);
                      }
                   }
                  /**
                   * Si on recoit un text provenant d'autre utilisateur, on l'affiche ! 
                   */
                  if(sigal instanceof SendText){
                	  c.controlDisplayText((SendText)sigal,remoteUser.getUsername());
                  }
                  /**
                   * Si on recoit un HelloReply provenant d'autre utilisateur, on l'ajoute dans
                   * la liste, c'est pour ajouter les personnes qui se connectent apres moi ! 
                   */
                  if (sigal instanceof HelloReply){
                	  c.getLocalUser().addRemoteUser(InetAddress.getByName(adr.getHostAddress()), ((HelloReply) sigal).getUsername());
                	  c.controlDisplayHelloReply((HelloReply)sigal,adr.getHostName());
                  }
                  /**
                   * Si on recoit un PropreFile, il y aura un dialog qui va afficher      
                   */
                  if(sigal instanceof PropFile){
                	  c.dialogAcceptFile(((PropFile) sigal).getFileName(),((PropFile) sigal).getFileSize(),((PropFile) sigal).getFileID(),remoteUser.getUsername());
                  }
                  /**
                   * Pas encore fini !!!!!!!!!!!!!!!!!
                   */
                  if(sigal instanceof AcceptFile){
                	  c.controlDisplayAcceptFile(((AcceptFile) sigal).getFileID(), ((AcceptFile) sigal).accepted(), ((AcceptFile) sigal).now(), remoteUser.getAddressIP());
                  }
             } catch (ClassNotFoundException e) {
            	 Logger.getLogger(UDPReceiver.class.getName()).log(Level.SEVERE, null, e);
             }   
             } catch (IOException e) {
            	 Logger.getLogger(UDPReceiver.class.getName()).log(Level.SEVERE, null, e);
            	 System.out.println("Receive with socket UDP failed!!");
             }
         }
         socketUDPReceive.close(); /**On close le socket si on quitte le chat system*/
    }   
}