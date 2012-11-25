package network;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import signals.*;

public class UDPReceiver extends Thread{
    
     DatagramSocket socketUDPReceive=null;
     final int localPort=5500;
     byte[] buf = new byte[2560];
     Signal sig;
     
     public UDPReceiver(int port){
    	 try {
			socketUDPReceive=new DatagramSocket(port);
		} catch (SocketException e) {
			e.printStackTrace();
		}
     }

    public static Signal deserializeSignal(byte[] sigToRead){
        Signal sig=null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(sigToRead);
            ObjectInputStream ois = new ObjectInputStream(bis);
            sig = (Signal) ois.readObject();
        } catch (IOException e) {
                System.out.println("Object non deserialized");
        } catch (ClassNotFoundException e) {
                System.out.println("Object non deserialized");
        }
        return sig;
    }
    
    public void receiveSignal(){
        //try {
            // Creation of the UDP socket
//            socketUDPReceive=new DatagramSocket(localPort);
            
            DatagramPacket packet=new DatagramPacket(buf,buf.length);
            try {
                // Reception of the UDP packet on the local port
                socketUDPReceive.receive(packet);
                
                // IP of the remote user
                InetAddress adr = packet.getAddress();
                
                // Conversion of the packet from Bytes to Array data
                ByteArrayInputStream bis = new ByteArrayInputStream(packet.getData());
                
                // Conversion into an object
                ObjectInputStream received = new ObjectInputStream(bis);
                
                    try {
                        sig = (Signal) received.readObject();
                        System.out.println("addresse :" + adr.toString());
                        System.out.println("message :" + sig.toString());
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("Receive with socket UDP failed!!");
                }
      //  }
            socketUDPReceive.close();
    }
   
} 