package chatSystem;

import gui.GUI;
import controller.ChatController;

public class ChatSystem {
    public static void main(String[] args){
        
        ChatController c = new ChatController();
        GUI gui = new GUI("Welcome to the chat system!!!",c);
        c.setGui(gui);
    }
}
