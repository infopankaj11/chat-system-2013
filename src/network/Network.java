package network;

import java.net.InetAddress;
import java.net.UnknownHostException;

import signals.GoodBye;
import signals.Hello;
import signals.HelloReply;
import signals.SendText;
import signals.Signal;

public class Network {
	
//	static UDPSender udpS= new UDPSender(5500);
	static UDPReceiver udpR = new UDPReceiver(5500);
	public static void main(String[] args){
//		signal_Hello();
//		signal_Bye();
//		signal_Hello_Reply();
		while(true){
			udpR.receiveSignal();
		}
		
	}
	
//	public static void signal_Hello(){
//		Signal signalHello=new Hello("Yingqing");
//		try {
//			udpS.sendSignal(InetAddress.getByName("255.255.255.255"), signalHello,5500);
//		} catch (UnknownHostException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	public static void signal_Bye(){
//		Signal signalBye=new GoodBye("Yingqing");
//		try {
//			udpS.sendSignal(InetAddress.getByName("255.255.255.255"), signalBye,5500);
//		} catch (UnknownHostException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	public static void signal_Hello_Reply(){
//		Signal HelloReply=new HelloReply("Yingqing");
//		try {
//			udpS.sendSignal(InetAddress.getByName("255.255.255.255"), HelloReply,5500);
//		} catch (UnknownHostException e) {
//			e.printStackTrace();
//		}
//	}

//	public static void signal_Send_Text(){
//		Signal SendText=new SendText("Héhé!!",1);
//		try {
//			udpS.sendSignal(InetAddress.getByName("255.255.255.255"), SendText,5500);
//		} catch (UnknownHostException e) {
//			e.printStackTrace();
//		}
//	}

	
}
