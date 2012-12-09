package model;

import java.util.ArrayList;
import javax.swing.DefaultListModel;

/**
 * Cette classe permet de creer une liste de remote user qui participe
 * la conversation
 * @author yingqing
 */
public class TalkUserModel {

    /**
     * Stocker une liste de participants à la discussion
     * @see participants
     */
    ArrayList<RemoteUser> participants;
    private DefaultListModel participantsListModel;
 
    /**
     * Constructeur de la classe Discussion
     */
    public TalkUserModel(){
    	participants=new ArrayList<RemoteUser>();
        participantsListModel=new DefaultListModel();
    }

    /**
     * Méthode d'ajout d'un participant à la liste
     * de participants
     * @param p
     */
    public void addParticipant(RemoteUser p)
    {
        participants.add(p);
        getParticipantsListModel().addElement(p);
    }

    /**
     * Méthode de suppression d'un participant de la liste
     * de participants
     * @param p
     */
    public void removeParticipant(RemoteUser p)
    {
        participants.remove(p);
        getParticipantsListModel().removeElement(p);
    }

    /**
     *
     * @return the participants
     */
    public ArrayList<RemoteUser> getParticipants() {
        return participants;
    }

    /**
     * @return the participantsListModel
     */
    public DefaultListModel getParticipantsListModel() {
        return participantsListModel;
    }
}