package chatSystem;

import controller.ChatController;
import model.LocalUserModel;
import network.Network;
import gui.AcceptFiles;
import gui.GUI;

public class ChatSystem {
    public static void main(String[] args){  
        ChatController c = new ChatController();
        Network ni=new Network(c);
        GUI gui = new GUI();
        LocalUserModel localUser=new LocalUserModel();
        
        localUser.setView(gui);
        gui.setLocalUser(localUser);
        
        gui.setController(c);
        c.setGui(gui);
        System.out.println("Controlleur 5 " +c);
        
        c.setLocalUser(localUser);
     
        c.setNi(ni);
        
        ni.setC(c);
        System.out.println("GUI : " +gui);
        System.out.println("Controlleur 1 " +c);
    }
}
