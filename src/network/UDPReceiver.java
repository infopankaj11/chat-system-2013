package network;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import controller.ChatController;

import signals.*;

public class UDPReceiver extends Thread{
     private final int portLocal=5500;
     byte[] buf = new byte[2560];
     Signal sigal;
     private ChatController c;

     public UDPReceiver(ChatController c){
    	 this.c=c;
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
                 
            while(true){         
            	DatagramPacket packet=new DatagramPacket(buf,buf.length);
            	try {
                // Reception of the UDP packet on the local port
            		socketUDPReceive.receive(packet);
               
                // IP of the remote user
            		InetAddress adr = packet.getAddress();
               
                // Conversion      //      socketUDPReceive.close(); of the packet from Bytes to Array data
            		ByteArrayInputStream bis = new ByteArrayInputStream(packet.getData());
               
                // Conversion into an object
            		ObjectInputStream received = new ObjectInputStream(bis);
               
                    	try {
                    		sigal = (Signal) received.readObject();
                    		if (sigal instanceof Hello){
                    			c.controlDisplayHello((Hello)sigal);
                    		}
                    		if(sigal instanceof SendText){
                    			System.out.println(adr.getHostName()+" : "+((SendText) sigal).getMessage());
                    		}
                    	} catch (ClassNotFoundException e) {
                    		e.printStackTrace();
                    	}              
                	} catch (IOException e) {
                		e.printStackTrace();
                		System.out.println("Receive with socket UDP failed!!");
                	}
                 }
    }
} 