package network;

import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

import signals.Signal;

import controller.ChatController;

// Un objet de cette classe sera crée pour recevoir un unique fichier
// Le nom de ce fichier est donné en argument du constructeur
public class TCPServer extends Thread {

    private ChatController mon_controller;
    private boolean connecte;
    private String filename;

    public TCPServer(ChatController aThis, String filename) {
        this.mon_controller = aThis;
        this.filename = filename;
    }

    public void setconnecte(boolean b) {
        this.connecte = b;
    }

    @Override
    // Méthode de réception d'un ficheir
    public void run() {

        // Sockets utilisés lors de la réception TCP
        ServerSocket socket = null;
        Socket socketClient = null;
        InputStream in = null;
        FileOutputStream out = null;
        int data_size = 0;

        byte[] buf = new byte[2560];
        Signal message;

        // Partie Initialisation des flux et mise en attente
        try {
            // Création du socket d'écoute (serveur) sur le port 2234
            socket = new ServerSocket(6500);
            // Affectation du socket client (contenant entre autre l'adresse de l'hote distant)
            socketClient = socket.accept();
            // Création du flux d'entrée (Flux TCP)
            in = socketClient.getInputStream();
            // Création du flux de sortie (Vers un fichier)
 //           out = new java.io.FileOutputStream(this.filename);
        } catch (IOException ex) {
            System.out.println("Probleme dans le processus d'attente de fichiers de ReceiveTCP !");
        }
       
        // Partie réception du fichier
        try {
            while ((data_size = in.read(buf)) != -1) {
                System.out.println("reception");
             out.write(buf, 0, data_size);          
            }
        } catch (IOException ex) {
            System.out.println("Probleme dans le processus de reception du fichier !");
        }
           
        // Partie fermeture des flux/sockets
        try {
            socket.close();
            socketClient.close();
            in.close();
            out.close();
        } catch (IOException ex) {
            System.out.println("Probleme dans la fermeture des flux/sockets !");
        }

    }
}

