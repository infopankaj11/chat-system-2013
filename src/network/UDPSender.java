package network;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import signals.*;

public class UDPSender {
	
	private DatagramSocket socketUDPSend=null;
	private int portLocal;

	public UDPSender(int port){
		this.portLocal=port;
		try {
			socketUDPSend=new DatagramSocket(port);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
	
	public byte[] serialiserSignal(Signal signal){
		byte[] serial = null;
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream    oos = new ObjectOutputStream(bos);
			//serialiser l'objet Message dans un taableau de byte
			oos.writeObject(signal);     //objet message to bos
			serial = bos.toByteArray();  //bos to Byte[]	
			oos.close();
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
			System.out.println("Message non serialisé");	
		}
		return serial;
	}
	
	public void sendSignal(InetAddress adressDistant,Signal signal,int portDest){
		byte[] send_signal=serialiserSignal(signal);

		DatagramPacket packet=new DatagramPacket(send_signal,send_signal.length,adressDistant,portDest);
		try {
			socketUDPSend.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Send with socket UDP failed!!");
		}
	}
	
}
