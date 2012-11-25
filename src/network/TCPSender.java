package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPSender {

	private Socket socketClient = null;
	private InetAddress addressServer=null;
	private int portServer;
	private PrintWriter out=null;
	private BufferedReader in =null;
	
	public TCPSender(InetAddress addressServer,int portServer) {
		try {
			socketClient=new Socket(addressServer.getHostName(),portServer);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
//	out = new PrintWriter(socketClient.getOutputStream(),true);

	
	

	
}
