package chatSystem;

import gui.GUI;
import controller.ChatController;

public class ChatSystem {
    public static void main(String[] args){       
        ChatController c = new ChatController();
        GUI gui = new GUI(c);
        c.setGui(gui);
    }
}
