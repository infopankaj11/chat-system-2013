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
     * Creation du lien entre LocalUser et Gui
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
     * Ajouter le remoteUSer dans la liste, si il existe deja, 
     * on ne l'ajoute pas.
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
     *  Supprimer un remote user de la liste
     * @param remoteUser
     */
    public void removeRemoteUser(RemoteUser remoteUser) {
        remoteUsers.removeElement(remoteUser);    
    }

    /**
     * Supprimer tous les remote users dans la liste
     */
    public void removeAllRemoteUser(){
        remoteUsers.clear();
    }     
    

	/**
     * Recuperer le remoteUser a partir de son adress
     * ip
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
}