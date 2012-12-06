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
import model.LocalUserModel;
import model.RemoteUser;
import network.Network;
import gui.GUI;

public class ChatController {
       
        private GUI gui;
        private Network network;
        private LocalUserModel localUser; 
        private RemoteUser user;
       
        public ChatController(){}      
       
        public RemoteUser getUser() {
			return user;
		}

		public void setUser(RemoteUser user) {
			this.user = user;
		}

		public void setLocalUser(LocalUserModel localUser) {
			this.localUser = localUser;
		}

		public void setNi(Network ni){
                this.network=ni;
        }
        
        public void setGui(GUI g) {
            this.gui = g;
        }
       

        public void login(){
        	network.signal_Hello();
        }
        
        
       
//        public void controlDisconnexion(){
//        	network.signal_Bye();
//        }
       
        public LocalUserModel getLocalUser() {
			return localUser;
		}

		public void controlDisplayHello(Hello h){
        	Date d = new Date();
        	String s = h.getUsername() +" : "+ " says Hello to everyone, at " +DateFormat.getTimeInstance().format(d) + "\n";
        	gui.setLocalUser(localUser);
        //	gui.addUser();
        	System.out.println("test 100 : " +user.getUsername());
        	System.out.println("test 100 : " +user.getAddressIP());
        	System.out.println("test 100 : " +this);
        	gui.displayMsg(s);
        }
       
//        public void controlDisplayBye(GoodBye b){
//        	Date d = new Date();
//        	String s = this.getLocalUser().getUsername() +" : "+ " says Goodbye to everyone, at " +DateFormat.getTimeInstance().format(d) + "\n";
//        	gui.displayMsg(s);
//        	gui.deleteUserFromUserPanel();
//        	gui.deleteUserFromParticipates();
//        	
//        }
        
//        public void controlDisplayText(SendText t){
//        	Date d = new Date();
//        	String s = this.getLocalUser().getUserName() +" : "+ " says " + t.getMessage()+ ", at " +DateFormat.getTimeInstance().format(d) + "\n";
//        	gui.displayMsg(s);
//        }
        
//        public void controlDisplayHelloReply(HelloReply hr){
//        	Date d = new Date();
//        	String s = hr.getUsername() +" : "+ " says helloReply to you , at " +DateFormat.getTimeInstance().format(d) + "\n";
//        	gui.displayMsg(s);
//        	gui.addUser();
//        }
        
//        public void controlSendHelloReply(String adress){
//        	try {
//				network.signal_Hello_Reply(adress);
//				System.out.println("Test : "+ InetAddress.getByName(adress));
//			} catch (UnknownHostException e) {
//				System.out.println("failed to send hello back !");
//			}
//        }
//        
//        public void controllerCloseThread(){       	
//        	network.signal_Bye();
//        	System.out.println("test cao ni ma1 : " + network.getUDPR());
//        	network.getUDPR().setActive(false);
//        }
//       
}

