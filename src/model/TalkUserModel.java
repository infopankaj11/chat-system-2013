package model;

import java.util.ArrayList;
import javax.swing.DefaultListModel;

public class TalkUserModel {

    // Stockage d'une liste de participants à la discussion
    ArrayList<RemoteUser> participants;
    private DefaultListModel participantsListModel;


    
    /**
     * Constructeur de la classe Discussion
     * @param t
     */
    public TalkUserModel()
    {
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