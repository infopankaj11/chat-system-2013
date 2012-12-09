package network;

import gui.GUI;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Ce
 * @author yingqing
 *
 */
public class TCPClient extends Thread{

    private int port = 6500;
    private byte[] buffer = new byte[2560];
    private long senddatasize;
    private long filesize;
    private GUI mon_gui;
    private Socket socket;
    
    private String filesent;
    private String filepath;

    public TCPClient(InetAddress adr){
    	try {
			socket = new Socket(adr, port);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }


	public String getFilesent() {
		return filesent;
	}



	public void setFilesent(String filesent) {
		this.filesent = filesent;
	}



	public GUI getMon_gui() {
		return mon_gui;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}


    public void sendFile(String filename, InetAddress adr) {

        int data_size = 0;

        
        FileInputStream in = null;
		try {
			in = new FileInputStream(this.filepath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("client paths : "+this.filepath);

        OutputStream os = null;
		try {
			os = socket.getOutputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        DataOutputStream dos = new DataOutputStream(os);

        File myfile = new File(this.filepath);
        filesize = myfile.length();

        System.out.println(this.filepath);

        // Fermeture du flux d'entree
        try {
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        // Fermeture du flux de sortie
        try {
			dos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        // Fermeture du socket
        try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }


}
