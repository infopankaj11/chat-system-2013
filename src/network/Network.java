package network;

import java.net.InetAddress;
import java.net.UnknownHostException;
import signals.*;

public class Network {
        final static int portDest=5500;
        static UDPSender udpS= new UDPSender(6000);
        static UDPReceiver udpR = new UDPReceiver();
        public static void main(String[] args){
                udpR.start();
                signal_Hello();
    //            signal_Bye();
     //           signal_Hello_Reply();
                signal_Send_Text();
                
        }
        
       
        public static void signal_Hello(){
                Signal signalHello=new Hello("Yingqing");
                try {
                        udpS.sendSignal(InetAddress.getByName("255.255.255.255"),signalHello,portDest);
                } catch (UnknownHostException e) {
                        e.printStackTrace();
                }
        }
       
        public static void signal_Bye(){
                Signal signalBye=new GoodBye();
                try {
                        udpS.sendSignal(InetAddress.getByName("255.255.255.255"),signalBye,portDest);
                } catch (UnknownHostException e) {
                        e.printStackTrace();
                }
        }
       
        public static void signal_Hello_Reply(){
                Signal signalHelloReply=new HelloReply("Yingqing");
                if (udpR.sigal instanceof Hello){
                        String username=((Hello) udpR.sigal).getUsername();
                        try {
                                udpS.sendSignal(InetAddress.getByName(username), signalHelloReply, portDest);
                                
                        } catch (UnknownHostException e) {
                                e.printStackTrace();
                        }
                }                 
        }

        
      public static void signal_Send_Text(){
    	  try {
			Signal sendText= new SendText("hi",null);
			udpS.sendSignal(InetAddress.getByName("255.255.255.255"),sendText,portDest);
    	  } catch (UnknownHostException e) {
			e.printStackTrace();
    	  }  
      }  
      
      
}