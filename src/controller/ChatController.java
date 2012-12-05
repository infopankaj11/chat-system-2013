package controller;

import java.text.DateFormat;
import java.util.Date;

import javax.swing.DefaultListModel;

import signals.GoodBye;
import signals.Hello;
import signals.SendText;
import network.Network;
import model.User;
import gui.GUI;

public class ChatController {
       
        private GUI gui;
        private Network network;
        private User localUser;
        private DefaultListModel listUser;
        private boolean connected;
       
       
        public ChatController(){
      //          connected=false;
        }
        
        public void createLocalUser (String s) {
            this.localUser = new User(s);
        }

       
        public DefaultListModel getListUser() {
                return listUser;
        }

        public User getLocalUser(){
                return localUser;
        }
       
        public void setNi(Network ni){
                this.network=ni;
        }
       
        public void lancheNi(){
                Network ni=new Network(this);
                this.setNi(ni);
                ni.signal_Hello();
                //connected=true;
        }

        public void setGui(GUI gui) {
                this.gui = gui;
        }

        public void controlConnexion(){
        	network.signal_Hello();
        }
       
        public void controlDisconnexion(){
        	network.signal_Bye();
        }
       
        public void controlDisplayHello(Hello h){
        	Date d = new Date();
        	String s = h.getUsername() +" : "+ " says Hello to everyone, at " +DateFormat.getTimeInstance().format(d) + "\n";
        	gui.addUser();
        	gui.displayMsg(s);
        }
       
        public void controlDisplayBye(GoodBye b){
        	Date d = new Date();
        	String s = this.getLocalUser().getUserName() +" : "+ " says Goodbye to everyone, at " +DateFormat.getTimeInstance().format(d) + "\n";
        	gui.deleteUser();
        	gui.displayMsg(s);
        }
        
        public void controlDisplayText(SendText t){
        	Date d = new Date();
        	String s = this.getLocalUser().getUserName() +" : "+ " says " + t.getMessage()+ ", at " +DateFormat.getTimeInstance().format(d) + "\n";
        	gui.displayMsg(s);
        }
        
        public void controllerCloseThread(){       	
        	network.signal_Bye();
//        	network.getUDPReceiver().setActive(false);
        }
       
}

