package network;

import gui.GUI;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPClient extends Thread{

    private int port = 6500;
    private byte[] buffer = new byte[2560];
    private long senddatasize;
    private long filesize;
    private GUI mon_gui;
    private Socket socket;
    
    private int idFilesent;
    private String filepath;

    public TCPClient(InetAddress adr){
    	try {
			socket = new Socket(adr, port);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @SuppressWarnings("empty-statement")
    	public long getFilesize() {
		return filesize;
	}

	public void setFilesize(long filesize) {
		this.filesize = filesize;
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


    public void run(int idFilename) throws UnknownHostException, IOException {

        int data_size = 0;

        
        FileInputStream in = new FileInputStream(this.filepath);
        System.out.println(this.filepath);

        OutputStream os = socket.getOutputStream();
        DataOutputStream dos = new DataOutputStream(os);

        File myfile = new File(this.filepath);
        filesize = myfile.length();

        System.out.println(this.filepath);

        // Fermeture du flux d'entree
        in.close();

        // Fermeture du flux de sortie
        dos.close();

        // Fermeture du socket
        socket.close();
    }


}
