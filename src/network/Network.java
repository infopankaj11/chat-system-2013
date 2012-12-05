package network;

import java.net.InetAddress;
import java.net.UnknownHostException;

import controller.ChatController;
import signals.*;

public class Network {
        final static  int portDest=5500;
        private  UDPSender udpS= new UDPSender(8000);
        private ChatController c;
        private UDPReceiver udpR;
       
        public Network (ChatController c){
                this.c=c;
                UDPReceiver udpR = new UDPReceiver(c);
                udpR.start();
        }      
        
        public  UDPReceiver getUDPReceiver() {
            return udpR;
        }
     
        public void signal_Hello(){
                Signal signalHello=new Hello(c.getLocalUser().getUserName());
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
       
        public void signal_Hello_Reply(){
                Signal signalHelloReply=new HelloReply(c.getLocalUser().getUserName());
                if (udpR.sigal instanceof Hello){
                        String username=((Hello) udpR.sigal).getUsername();
                        try {
                                udpS.sendSignal(InetAddress.getByName(username), signalHelloReply, portDest);
                               
                        } catch (UnknownHostException e) {
                                e.printStackTrace();
                        }
                }                
        }

       
      public void signal_Send_Text(InetAddress[] UserIp, String msg){
          try {
                 if(UserIp.length==0){
                         Signal sendText= new SendText(msg,UserIp);
                         udpS.sendSignal(InetAddress.getByName("255.255.255.255"),sendText,portDest);
                 }
                 else{
                         int i;
                         Signal sendText= new SendText(msg,UserIp);
                         for(i=0;i<UserIp.length;i++){
                                 udpS.sendSignal(UserIp[i],sendText,portDest);
                         }
                 }
          } catch (UnknownHostException e) {
                        e.printStackTrace();
          }  
      }      
}

