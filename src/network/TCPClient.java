package network;

import gui.TransferSucceed;

import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

import controller.ChatController;

/**
 * Cette classe permet de creer un socket client pour envoyer des fichiers
 * @author yingqing
 *
 */
public class TCPClient extends Thread {

	/**
	 * On definit le port d'ecoute de tcp 6500
	 */
    private static int serverPort=6500;
    private Socket socket = null;
    @SuppressWarnings("unused")
	private File fileToSend;
    private ChatController controller;
    @SuppressWarnings("unused")
	private String filePath;
    @SuppressWarnings("unused")
	private TransferSucceed t;


    /**
     * Constructeur pour construire un socket TCP client, on lui passe en parametre
     * adress ip de serveur, le fichier a envoye et le controlleur
     * @param serverAddress
     * @param fileToSend
     * @param c
     */
    public TCPClient(InetAddress serverAddress, File fileToSend, ChatController c) {
    	try {
            this.fileToSend = fileToSend;
            this.controller = c;
            socket = new Socket(serverAddress, serverPort);
            this.start();
        } catch (IOException ex) {
        	Logger.getLogger(TCPClient.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Failed to create et start un client socket !!");
        }
    }

    public void run() {  
        int buf;
        byte[] buffer = new byte[2056];
        try {
        	String path=controller.getFilePathOpen();
            FileInputStream fis = new FileInputStream(path);
            BufferedInputStream bis = new BufferedInputStream(fis);          
            OutputStream os = socket.getOutputStream();
            
      
            while ((buf = bis.read(buffer, 0, buffer.length)) > 0){
                os.write(buffer, 0, buf);
                os.flush();
            }          
            t=new TransferSucceed();
            System.out.println("Send has benn sent successfully!!");
        }
        catch (UnknownHostException e){
        	Logger.getLogger(TCPClient.class.getName()).log(Level.SEVERE, null, e);
        	System.out.println("Unkonwn user for socket client !!");
        }
        catch (EOFException e){
        	Logger.getLogger(TCPClient.class.getName()).log(Level.SEVERE, null, e);
        	System.out.println("EOF problem !!");
        }
        catch (IOException e){
        	Logger.getLogger(TCPClient.class.getName()).log(Level.SEVERE, null, e);
        	System.out.println("I/O problem !!");
        }
        finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                	Logger.getLogger(TCPClient.class.getName()).log(Level.SEVERE, null, e);
                	System.out.println("Close socket client failed !!");
                }
            }
        }
    }
}



