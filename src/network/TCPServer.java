package network;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


import controller.ChatController;

/**
 * Cette classe permet de creer un socket TCP serveur
 * @author yingqing
 */
public class TCPServer extends Thread {

    private ServerSocket listenSocket;
    private Socket clientSocket;
    private String filename;
    @SuppressWarnings("unused")
	private ChatController controller;
    private static int serverPort=6500;
    private PrintWriter PrintWriterOut;

    public TCPServer(String filename,ChatController c) {
        try {
            this.filename = filename;
            controller = c;
            listenSocket = new ServerSocket(serverPort);
            System.out.println("server start listening... ... ...");         
            this.start();
        } catch (IOException e) {
        	Logger.getLogger(ChatController.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("Failed to create et start the server socket !!");
        }
    }

    public void run() {
        int buf;
        byte[] buffer = new byte[2056];
        String path;
        try {
        	path=controller.getFilePath();
            clientSocket = listenSocket.accept();          
            InputStream is = clientSocket.getInputStream();
            FileOutputStream fos = new FileOutputStream(path);
            BufferedOutputStream bos = new BufferedOutputStream(fos);            
            System.out.println("Path of save file : "+path);       
            while ((buf = is.read(buffer, 0, buffer.length)) > 0){
               bos.write(buffer, 0, buf);
            }          
            bos.close();
            System.out.println("TCPServer received !!");
        } catch (EOFException e) {
        	Logger.getLogger(ChatController.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("EOF problem !!");
        } catch (IOException e) {
        	Logger.getLogger(ChatController.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("I/O problem !!");
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
            	Logger.getLogger(ChatController.class.getName()).log(Level.SEVERE, null, e);
                System.out.println("Fail to close server socket !!");
            }
        }
    }
}


