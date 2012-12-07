package network;

import java.net.InetAddress;
import java.net.UnknownHostException;

import model.RemoteUser;

import controller.ChatController;
import signals.*;

public class Network {
        final static  int portDest=5500;
        private  UDPSender udpS= new UDPSender(8000);
        private ChatController c;
        private UDPReceiver udpR;
       
        public Network (ChatController c){
                udpR = new UDPReceiver(c);
 //               udpR.start();              
        }      
        
		public UDPReceiver getUdpR() {
			return udpR;
		}


		public void setC(ChatController c) {
			this.c = c;
		}
		
		public void signal_Hello(){
                Signal signalHello=new Hello(c.getLocalUser().getUsername());
                System.out.println("tester user : " +c.getLocalUser().getUsername());
                try {
                	udpS.sendSignal(InetAddress.getByName("255.255.255.255"),signalHello,portDest);
                } catch (UnknownHostException e) {
                        e.printStackTrace();
                }
        }
       
        public void signal_Bye(){
                Signal signalBye=new GoodBye();
                try {
                        udpS.sendSignal(InetAddress.getByName("255.255.255.255"),signalBye,portDest);
                } catch (UnknownHostException e) {
                        e.printStackTrace();
                }
        }
       
        public void signal_Hello_Reply(String address){
                Signal signalHelloReply=new HelloReply(c.getLocalUser().getUsername());   
                System.out.println("test HelloReply: " + c.getLocalUser().getUsername());
                try {
					udpS.sendSignal(InetAddress.getByName(address), signalHelloReply, portDest);
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}            
        }

       
      public void signal_Send_Text(InetAddress[] UserIp, String msg){
          try {
                 if(UserIp==null){
                         Signal sendText= new SendText(msg,UserIp);
                         udpS.sendSignal(InetAddress.getByName("255.255.255.255"),sendText,portDest);
                 }
                 else{
                         int i;
                         Signal sendText= new SendText(msg,UserIp);
                         for(i=0;i<UserIp.length;i++){
                        	 System.out.println("length of address : " + UserIp.length);
                                 udpS.sendSignal(UserIp[i],sendText,portDest);
                         }
                 }
          } catch (UnknownHostException e) {
                        e.printStackTrace();
          }  
      }      
}

