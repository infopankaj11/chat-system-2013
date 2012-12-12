package model;

import gui.GUI;

import java.net.InetAddress;
import javax.swing.DefaultListModel;

/**
 * Cette classe permet de creer une liste de remote user qui est connecte
 * @author yingqing
 */
public class LocalUserModel {
	
	/**
	 * @see gui
	 */
    private GUI gui;
    
    /**
     * Indique si l'utilisateur est connecte
     * @see connected
     */
    private boolean connected;
    
    /**
     * Nom d'utilisateur
     */
    private String username;
    
    /**
     * Liste d'utilisateurs distants
     */
    private DefaultListModel remoteUsers;

    /**
     * Construire un model user
     */
    public LocalUserModel(){
        connected=false;
        username=new String();
        remoteUsers = new DefaultListModel();
    }

    /**
     * Creation du lien LocalUser <--> Gui
     * @param gui
     */
    public void setView(GUI gui){
        this.gui=gui;
    }
       
    /**
     * @return the connected
     */
    public boolean isConnected() {
        return connected;
    }

    /**
     * @param connected the connected to set
     */
    public void setConnected(boolean connected) {
        if(connected){
            gui.connectedMode();
        }
        else{
            gui.disconnectedMode();
        }
        this.connected = connected;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the remoteUsers
     */
    public DefaultListModel getRemoteUsers() {
        return remoteUsers;
    }

    /**
     * Ajout un utilisateur distant a la liste d'utilisateurs
     * @param remoteIP
     * @param userName
     */
    public void addRemoteUser(InetAddress remoteIP, String userName) {
    	boolean existed=false;
        RemoteUser remoteUser=new RemoteUser(remoteIP,userName);
        for(int i=0 ; i<remoteUsers.size();i++){
        	RemoteUser r=(RemoteUser)remoteUsers.elementAt(i);
        	if(remoteIP.equals(r.getAddressIP())){
        		existed=true;
        		System.out.println("User already exists!!");
        	}
        }
        if(existed==false){
        	getRemoteUsers().addElement(remoteUser);
        }
    }

	/**
     * Retourne l'utilisateur distant correspondant a
     * l'adresse IP passee en parametre
     * @param address
     * @return result
     */
    public RemoteUser getRemoteUser(InetAddress address){
        RemoteUser result=null;
        for(int i=0;i<remoteUsers.size();i++){
            RemoteUser r=(RemoteUser)remoteUsers.elementAt(i);
            if(r.getAddressIP().equals(address)){
                result=r;
            }
        }
        return result;
    }

    /**
     *  Methode permet de supprimer un utilisateur de la liste
     *  d'utilisateurs distants
     * @param remoteUser
     */
    public void removeRemoteUser(RemoteUser remoteUser) {
    	boolean existed=true;
        for(int i=0 ; i<remoteUsers.size();i++){
        	RemoteUser r=(RemoteUser)remoteUsers.elementAt(i);
        	if(!remoteUser.getAddressIP().equals(r.getAddressIP())){
        		existed=false;
        		System.out.println("User didn't exist !!");
        	}
        }
        if(existed==true){
        	remoteUsers.removeElement(remoteUser);
        }       
    }

    /**
     * M�thode permettant de supprimer tous les utilisateurs
     * de la liste d'utilisateurs distants
     */
    public void removeAllRemoteUser(){
        remoteUsers.clear();
    }     
}