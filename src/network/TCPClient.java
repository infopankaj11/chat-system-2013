package network;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPClient extends Thread{
	
	private int portServer;
	private Socket socketClient=null;
	private File fileToSend;
	
	public TCPClient(InetAddress addressServer,int portServer,File fileToSend){
		this.portServer=portServer;
		this.fileToSend=fileToSend;
		try {
			socketClient=new Socket(addressServer,portServer);
		} catch (IOException e) {
			System.out.println("Can not create socketClient!!");
		}
		this.start();
	}
	
	public void run(){
        byte[] buffer = new byte[8100];
		FileInputStream fis = null;
			try {
				fis = new FileInputStream(fileToSend);
			} catch (FileNotFoundException e) {
				System.out.println("Cannot find the file");
			}
	        BufferedInputStream bis = new BufferedInputStream(fis);
	        OutputStream os = null;
				try {
					os = socketClient.getOutputStream();
				} catch (IOException e) {
					System.out.println("I/O problem");
				}		
				try {
					while (bis.read(buffer, 0, buffer.length) > 0){
						os.write(buffer, 0, bis.read(buffer, 0, buffer.length));
					    os.flush();
					}
				} catch (IOException e) {
					System.out.println("I/O problem");
				}
		try {
			socketClient.close();
		} catch (IOException e) {
			System.out.println("Can not close socketClient!");
		}
	}
}
