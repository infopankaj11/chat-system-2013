package network;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import signals.PropFile;

public class TCPServer implements Runnable{
    
    
    private ServerSocket serverSocket ;
    private Socket       socket ;
    private BufferedReader  entree ; //lecture flux de la socket 
    private PrintWriter sortie ;     //Ecriture dans un fichier
    private boolean     isStart ;
    private int         port;
    private PropFile fileToReceive ;
    private long     sizeFileToReceive;
    private static File   defaultDirectory;
     
     
    public TCPServer(short port ,PropFile file, long taille)
    {
          
          this.setPort(port);
  //        this.setFile(file);
          this.sizeFileToReceive =taille;
          this.setDefaultDirectory();
          System.out.println("Lancement du serveur ...\n");
          
          try {
               //Etape 1 : Creation de la socket
                openServeur();
          } catch (IOException ex) {
            Logger.getLogger(TCPServer.class.getName()).log(Level.SEVERE, null, ex);
          }
          
    }
//   public final void setFile(PropFile file){
//        this.fileToReceive=new PropFile(file);
//    }
    public final void setPort(short port){
         if(port>1024) this.port=port;
    }
    
    private boolean setDefaultDirectory()
    {
        defaultDirectory =new File("../ChatDownload");
        return defaultDirectory.mkdir();
    }
    public static void  setDefaultDirectory(String defaultPath)
    {
        defaultDirectory =new File(defaultPath);
        defaultDirectory.mkdir();
    }
    
    
    public void lancerServeur(){

          
          System.out.println("Serveur En attente de connexion\n");
          //Etape 1 : attente d'une connexion
          waitConnect();
          System.out.println("Connexion au serveur reussie port  : "+socket);

          //etape 2 : Recuperation des flux de la connexion
          openFlux();
          try {
            //etape 3 : Traiter la connexion .
            receptionData();
          } catch (IOException ex) {
            Logger.getLogger(TCPServer.class.getName()).log(Level.SEVERE, null, ex);
          }
         //Etape 4 : Fermer les flux 
         closeFlux();
        
    }

    
    /**
     * Creation du ServeurSocket
     * @throws IOException
     * 
     */
    private void openServeur() throws IOException
    {
	try{
	    //Creation de la socket serveur
            serverSocket = new ServerSocket(port);
	}catch (java.io.IOException ex){
	    Logger.getLogger(TCPServer.class.getName()).log(Level.SEVERE, null, ex);
            serverSocket.close();
	}
	
    }
    
    /**
     * Attente de connexion
     */
    private void  waitConnect()
    {
	try{
	    //recuperation de la socket
            socket = serverSocket.accept();
	}
         catch(java.io.IOException ex){
            try {
                Logger.getLogger(TCPServer.class.getName()).log(Level.SEVERE, null, ex);
                socket.close();
            } catch (IOException ex1) {
                Logger.getLogger(TCPServer.class.getName()).log(Level.SEVERE, null, ex1);
            }
	}
        isStart=true;
    }
    
    
     /**
     * Ouverture des flux d'entree et sortie
     */
    private void openFlux()
    {
	try{
	     //Recuperation des flux
            entree       = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            sortie = new PrintWriter(new FileWriter( new File(defaultDirectory+"/"+fileToReceive.getFileName())),true);


	}catch (java.io.IOException ex){
            try {
                Logger.getLogger(TCPServer.class.getName()).log(Level.SEVERE, null, ex);
                entree.close();
                sortie.close();
            } catch (IOException ex1) {
                Logger.getLogger(TCPServer.class.getName()).log(Level.SEVERE, null, ex1);
            }
	}
    }

     /**
     * Reception des donnees
     * @throws IOException
     */
    private void receptionData() throws IOException{
       
        int size = (int)sizeFileToReceive;
       
        int octectLu=0;
        int octectTraite = 0;
        char[] recu;
        System.out.println("Debut de la transmission");
        boolean transfert =true ;
    
        // Tant que l'on ne reçoit pas tout le fichier             
        while(transfert) 
        {
              recu = new char[size]; // Création d'un nouveau tableau
              octectLu= entree.read(recu,0, size);
              if(octectLu>0) // Si on a lu des donnees
              {
                    sortie.write(recu);
                    sortie.flush();
               }
              octectTraite = octectTraite + octectLu;
               if(octectTraite == size){
               transfert = false;
               System.out.println("Fin de la transmission");

           }
       }        
    }
         
    /**
     * Fermer les flux
     */
    private void closeFlux()
    {
	try{
	    if( entree!=null )
		entree.close();      // Fermeture du flux d'entree
	    if( sortie!=null )
		sortie.close();     // Fermeture du flux de sortie
	    if( socket!=null)
		socket.close();     // Fermeture du socket
	    if( serverSocket!=null )
		serverSocket.close();         // Fermeture du serveur
	}catch (java.io.IOException ex){
	    Logger.getLogger(TCPServer.class.getName()).log(Level.SEVERE, null, ex);
	}
         System.out.println("Fermeture des flux serveur");
    }


    @Override
    public void run() {  
        lancerServeur();
   }
}