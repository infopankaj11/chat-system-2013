package controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.DefaultListModel;

import signals.GoodBye;
import signals.Hello;
import signals.HelloReply;
import signals.SendText;
import network.Network;
import model.LocalUSer;
import model.User;
import gui.GUI;

public class ChatController {
       
        private GUI gui;
        private Network network;
        private LocalUSer localUser;
        private DefaultListModel listUser;
        private boolean connected;
       
       
        public ChatController(){
      //          connected=false;
        }
        
        public void createLocalUser (String s) {
            try {
				this.localUser = new LocalUSer(s);
			} catch (UnknownHostException e) {
				System.out.println("failed to create local user !");
			}
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
                System.out.println("test 1 : " + ni);
                this.setNi(ni);
                System.out.println("test 2 : " + ni);
//                System.out.println("test 3 : " + ni.getUDPR());
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
        	gui.displayMsg(s);
        	gui.deleteUserFromUserPanel();
        	gui.deleteUserFromParticipates();
        	
        }
        
        public void controlDisplayText(SendText t){
        	Date d = new Date();
        	String s = this.getLocalUser().getUserName() +" : "+ " says " + t.getMessage()+ ", at " +DateFormat.getTimeInstance().format(d) + "\n";
        	gui.displayMsg(s);
        }
        
        public void controlDisplayHelloReply(HelloReply hr){
        	Date d = new Date();
        	String s = this.getLocalUser().getUserName() +" : "+ " says helloReply to you , at " +DateFormat.getTimeInstance().format(d) + "\n";
        	gui.displayMsg(s);
        	gui.addUser();
        }
        
        public void controlSendHelloReply(String username){
        	try {
				network.signal_Hello_Reply(InetAddress.getByName(username));
			} catch (UnknownHostException e) {
				System.out.println("failed to send hell back !");
			}
        }
        
        public void controllerCloseThread(){       	
        	network.signal_Bye();
        	System.out.println("test cao ni ma1 : " + network.getUDPR());
 //       	network.getUDPR().setActive(false);
        }
       
}

