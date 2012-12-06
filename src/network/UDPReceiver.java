package network;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import model.RemoteUser;

import controller.ChatController;

import signals.*;

public class UDPReceiver extends Thread{
     private final int portLocal=5500;
     byte[] buf = new byte[1024];
     Signal sigal;
     private ChatController c=new ChatController();
     private boolean active;
     
     public UDPReceiver(ChatController c){
         this.active = true;
         this.c=c;
         
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
                                c.controlDisplayHello((Hello)sigal); 
  //                              c.setUser(new RemoteUser(InetAddress.getByName(adr.getHostAddress()),adr.getHostName()));
                                c.getLocalUser().addRemoteUser(InetAddress.getByName(adr.getHostAddress()), adr.getHostName());
                                System.out.println("===="+c.getUser().getAddressIP());
                                System.out.println("===="+c.getUser().getUsername());
                              	System.out.println("test hello UDP Receiver !!");
                              	System.out.println("controlleur dans UDP "+ c);
//                              c.controlSendHelloReply(adr.getHostAddress());;                                                
                        }
//                        if(sigal instanceof GoodBye){
//                        	c.controlDisplayBye((GoodBye)sigal);
//                        }
//                        if(sigal instanceof SendText){
//                        	c.controlDisplayText((SendText)sigal);
//                        }
//                        if (sigal instanceof HelloReply){
//                        	c.controlDisplayHelloReply((HelloReply)sigal);
//                        }
//                        
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

