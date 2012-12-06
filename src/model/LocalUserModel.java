package model;

import gui.GUI;

import java.net.InetAddress;
import java.util.ArrayList;
import javax.swing.DefaultListModel;

public class LocalUserModel {

    private GUI view;
    // Indique si l'utilisateur est connecté
    private boolean connected;
    // Nom d'utilisateur
    private String username;
    // Liste d'utilisateurs distants
    private DefaultListModel remoteUsers;
    private TalkUserModel talk;

    public LocalUserModel()
    {
        connected=false;
        username=new String();
        remoteUsers = new DefaultListModel();
    }

    /**
     * Creation du lien LocalUser <--> View
     * @param v
     */
    public void setView(GUI v)
    {
        view=v;
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
        if(connected)
        {
            view.connectedMode();
        }
        else
        {
            view.disconnectedMode();
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
     * Ajout d'un utilisateur distant à la liste d'utilisateurs
     * @param remoteIP
     * @param userName
     */
    public void addRemoteUser(InetAddress remoteIP, String userName) {
        RemoteUser remoteUser=new RemoteUser(remoteIP,userName);
        getRemoteUsers().addElement(remoteUser);
    }

    /**
     * @return the remoteUsers
     */
    public DefaultListModel getRemoteUsers() {
        return remoteUsers;
    }
    

	/**
     * Retourne l'utilisateur distant correspondant à
     * l'adresse IP passée en paramètre
     * @param address
     * @return
     */
    public RemoteUser getRemoteUser(InetAddress address)
    {
        RemoteUser result=null;

        for(int i=0;i<remoteUsers.size();i++)
        {
            RemoteUser r=(RemoteUser)remoteUsers.elementAt(i);
            if(r.getAddressIP().equals(address))
            {
                result=r;
            }
        }

        return result;
    }

    /**
     *  Méthode permettant de supprimer un utilisateur de la liste
     *  d'utilisateurs distants
     * @param remoteUser
     */
    public void removeRemoteUser(RemoteUser remoteUser) {
        remoteUsers.removeElement(remoteUser);
        System.out.println("Tester Remote User \n");
    }

    /**
     * Méthode permettant de supprimer tous les utilisateurs
     * de la liste d'utilisateurs distants
     */
    public void removeAllRemoteUser(){
        remoteUsers.clear();
    }
//    public void addToTalk(ArrayList<RemoteUser> remoteUsers) {
//        TalkUserModel t=new TalkUserModel();
//        for(RemoteUser r:remoteUsers)
//        {
//            t.addParticipant(r);
//        }
//        talk.add(t);
//    }

    public TalkUserModel getDiscussion()
    {
    	
        	return talk;
    }

    
    
}