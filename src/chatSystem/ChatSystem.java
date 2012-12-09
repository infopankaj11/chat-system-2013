package chatSystem;

import controller.ChatController;
import model.LocalUserModel;
import network.Network;
import gui.GUI;

/**Cette classe permet de creer le programme principal en utilisant le pattern MVC
 * @author YingQing**/
public class ChatSystem {
    public static void main(String[] args){  
    	/**Creation du modele, vu et controlleur 
    	**/
        ChatController c = new ChatController();
        Network ni=new Network(c);
        GUI gui = new GUI();
        LocalUserModel localUser=new LocalUserModel();
        
        /**Associer gui a modele**/
        localUser.setView(gui);
        gui.setLocalUser(localUser);
        
        /**Associer gui a controlleur**/
        gui.setController(c);
        c.setGui(gui);
        
        /**Associer controlleur a modele**/
        c.setLocalUser(localUser);
     
        /**Associer controlleur a network**/
        c.setNi(ni); 
        ni.setC(c);
    }
}
