package network;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

import signals.*;

/**Cette classe permet de creer un socket UDP pour envoyer des sigaux
 * @author YingQing**/
public class UDPSender{
	
	/**creation d'un datagramme socket
	 * @see portLocal**/
	private DatagramSocket socketUDPSend=null;
		
	@SuppressWarnings("unused")
	/**Port local cote sender
	 * @see portLocal**/
	private int portLocal;
	
    /**Constructeur permet de contruire un socket UDP 
    * @param port**/
    public UDPSender(int port){
    	this.portLocal=port;
        try {
           socketUDPSend=new DatagramSocket(port);
         } catch (SocketException e) {
        	 Logger.getLogger(UDPSender.class.getName()).log(Level.SEVERE, null, e);
         }
     }
       
    /**
     * Cette methode permet de serialiser des objets en bytes pour qu'on puisse
     * transmettre
     * @param signal
     * @return serial
     */
     public byte[] serialiserSignal(Signal signal){
         byte[] serial = null;
         try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream    oos = new ObjectOutputStream(bos);
            /**serialiser l'objet Message dans un tableau de byte */            
            oos.writeObject(signal);   /**objet message to bos*/
            serial = bos.toByteArray(); /**bos to Byte[]  */  
            oos.close();
            }
            catch (IOException ioe) {
            	Logger.getLogger(UDPSender.class.getName()).log(Level.SEVERE, null, ioe);
                System.out.println("Can not serialise messages ! ");    
            }
          return serial;
      }
       
     /**
      * Cette methode permet d'envoyer des siganux via le soclet UDP
      * @param adressDistant
      * @param signal
      * @param portDest
      */
     public void sendSignal(InetAddress adressDistant,Signal signal,int portDest){
          byte[] send_signal=serialiserSignal(signal);
          DatagramPacket packet=new DatagramPacket(send_signal,send_signal.length,adressDistant,portDest);
          try {
               socketUDPSend.send(packet);
           } catch (IOException e) {
        	   Logger.getLogger(UDPSender.class.getName()).log(Level.SEVERE, null, e);
               System.out.println("Send with socket UDP failed!!");
          }
     }   
}