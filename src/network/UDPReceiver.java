package network;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import model.User;

import controller.ChatController;

import signals.*;

public class UDPReceiver extends Thread{
     private final int portLocal=5500;
     byte[] buf = new byte[1024];
     Signal sigal;
     private ChatController c;
     private boolean active;
     
     public UDPReceiver(ChatController c){
         this.c=c;
         this.active = true;
     }
     
     public boolean isActive() {
         return active;
     }

     public void setActive(boolean active) {
         this.active = active;
     }
     
     public static Signal deserializeSignal(byte[] sigToRead){
        Signal sigal=null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(sigToRead);
            ObjectInputStream ois = new ObjectInputStream(bis);
            sigal = (Signal) ois.readObject();
        } catch (IOException e) {
                System.out.println("Object non deserialized");
        } catch (ClassNotFoundException e) {
                System.out.println("Object non deserialized");
        }
        return sigal;
    }
   
    public void run(){
        DatagramSocket socketUDPReceive=null;        
        try {
              socketUDPReceive=new DatagramSocket(portLocal);
            } catch (SocketException e1) {
                 e1.printStackTrace();
            }
                 
         while(active){        
                 DatagramPacket packet=new DatagramPacket(buf,buf.length);
                 try {
                socketUDPReceive.receive(packet);
                InetAddress adr = packet.getAddress();
                // Conversion of the packet from Bytes to Array data
                ByteArrayInputStream bis = new ByteArrayInputStream(packet.getData());
                // Conversion into an object
                ObjectInputStream received = new ObjectInputStream(bis);    
                    try {
                        sigal = (Signal) received.readObject();
                        if (sigal instanceof Hello){
//                        	String s=InetAddress.getLocalHost().toString().substring(InetAddress.getLocalHost().toString().indexOf("/"));
//                        	String userAddress=s.substring(1,s.length());
//                        	if(! userAddress.equals(adr.getHostAddress())){
//                        		System.out.println("test 3 : "+userAddress.equals(InetAddress.getLocalHost()));
//                        		System.out.println("test 5 "+userAddress);
//                        		System.out.println("test 7 "+adr.getHostAddress());                      		   
                        	   	c.getLocalUser().setUserName(adr.getHostName());
                                c.controlDisplayHello((Hello)sigal); 
                                c.controlSendHelloReply(c.getLocalUser().getUserName());
//                        	}
//                        	System.out.println("test 2 : "+userAddress.equals(InetAddress.getLocalHost()));
//                    		System.out.println("test 4 "+userAddress);
//                    		System.out.println("test 6 "+adr.getHostAddress());
//                        	c.getLocalUser().setUserName(((Hello) sigal).getUsername());
//                          c.controlDisplayHello((Hello)sigal);                                                
                        }
                        if(sigal instanceof GoodBye){
                        		c.getLocalUser().setUserName(adr.getHostName());
                                c.controlDisplayBye((GoodBye)sigal);
                        }
                        if(sigal instanceof SendText){
                        	c.getLocalUser().setUserName(adr.getHostName());
                        	c.controlDisplayText((SendText)sigal);
                        }
                        if (sigal instanceof HelloReply){
                        	c.getLocalUser().setUserName(adr.getHostName());
                        	c.controlDisplayHelloReply((HelloReply)sigal);
                        }
                        
                        } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                        }   
                        
                        } catch (IOException e) {
                                e.printStackTrace();
                                System.out.println("Receive with socket UDP failed!!");
                        }
                 }
         System.out.println("test 2 !!");
         socketUDPReceive.close();
         
        }
    
}

