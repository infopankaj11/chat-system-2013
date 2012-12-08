package network;

import gui.AcceptFiles;

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
         active=true;
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
                  	   String a=adr.getHostAddress();
                       String b=InetAddress.getLocalHost().getHostAddress();
                        sigal = (Signal) received.readObject();
                        if (sigal instanceof Hello){  

//                            String a=adr.getHostName();
//                            String b=InetAddress.getLocalHost().getHostName();
                             System.out.println("test ================ : "+ a);
                             System.out.println("test ================ : "+ b);
                            if(a.equals(b)){
                                System.out.println("*****************************");
                            }
                            else{
                            	System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!");
                                c.controlDisplayHello((Hello)sigal,adr.getHostName());
                              //  c.getLocalUser().addRemoteUser(InetAddress.getByName(adr.getHostAddress()), adr.getHostName());
                                c.getLocalUser().addRemoteUser(InetAddress.getByName(adr.getHostAddress()), ((Hello) sigal).getUsername());
                                c.controlSendHelloReply(adr.getHostAddress());
                                System.out.println("test goodbye  !!" +c.getLocalUser().getRemoteUsers());
                                System.out.println("test Hello  !!" + adr.getHostName());
                                System.out.println("test Hello  !!" + InetAddress.getByName(adr.getHostAddress()));
                                  System.out.println("test hello UDP Receiver !!");
                                  System.out.println("controlleur dans UDP "+ c);
                            }
//                              c.controlSendHelloReply(adr.getHostAddress());;                                                
                        }
                        
                        if(sigal instanceof GoodBye){

                            if(a.equals(b)){
            
                            }
                            else{
                            RemoteUser remoteUser=c.getLocalUser().getRemoteUser(InetAddress.getByName(adr.getHostAddress()));
                            c.controlDisplayBye((GoodBye)sigal,remoteUser.getUsername());
                            c.getLocalUser().removeRemoteUser(remoteUser);
                            System.out.println("test goodbye  !!" + remoteUser.getUsername());
                            System.out.println("test goodbye  !!" + adr.getHostName());
                            System.out.println("test goodbye  !!" + InetAddress.getByName(adr.getHostAddress()));
                            }
                        }
                        
                        if(sigal instanceof SendText){
                        	RemoteUser remoteUser=c.getLocalUser().getRemoteUser(InetAddress.getByName(adr.getHostAddress()));
                            c.controlDisplayText((SendText)sigal,remoteUser.getUsername());
//                            c.getLocalUser().getDiscussion().addParticipant(new RemoteUser(InetAddress.getByName(adr.getHostAddress()), adr.getHostName()));
                        }
                        
                        if (sigal instanceof HelloReply){
                            c.getLocalUser().addRemoteUser(InetAddress.getByName(adr.getHostAddress()), ((HelloReply) sigal).getUsername());
                            c.controlDisplayHelloReply((HelloReply)sigal,adr.getHostName());
                                      
                        }
                        
                        if(sigal instanceof PropFile){
                        	RemoteUser remoteUser=c.getLocalUser().getRemoteUser(InetAddress.getByName(adr.getHostAddress()));
                        	c.dialogAcceptFile(((PropFile) sigal).getFileName(),((PropFile) sigal).getFileSize(),((PropFile) sigal).getFileID(),remoteUser.getUsername());
                        }
                        
                        if(sigal instanceof AcceptFile){
                        	c.controlAcceptFile(((AcceptFile) sigal).getFileID(), ((AcceptFile) sigal).accepted(), ((AcceptFile) sigal).now(), adr.getHostAddress());
                        }
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