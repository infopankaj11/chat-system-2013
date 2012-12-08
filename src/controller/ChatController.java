package controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultListModel;

import signals.GoodBye;
import signals.Hello;
import signals.HelloReply;
import signals.PropFile;
import signals.SendText;
import model.LocalUserModel;
import model.RemoteUser;
import model.TalkUserModel;
import network.Network;
import gui.AcceptFiles;
import gui.GUI;

public class ChatController {
       
        private GUI gui;
        private Network network;
        private LocalUserModel localUser; 
        private RemoteUser user;
        private AcceptFiles fileAccept;
        private String file;
        
 
       
        public ChatController(){
    
        }      
        
        
       
		public String getFile() {
			return file;
		}



		public void setFile(String file) {
			this.file = file;
		}



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
       
        

        public Network getNetwork() {
			return network;
		}

		public void login(String username){
        	 if(!localUser.isConnected()){
                 localUser.setConnected(true);
                 localUser.setUsername(username);
 //                network.getUdpR().start();
                 network.signal_Hello();
             }
        	 else{
        		 System.out.println("User already connected !!");
        	 }
        }
        
        
       
        public void logout(){
        	 if(localUser.isConnected()){
        		 network.signal_Bye();
                 localUser.setConnected(false);
                 localUser.removeAllRemoteUser();
                
             }
             else{
            	 System.out.println("User already disconnected !!");
             }        	
        }
       
        public LocalUserModel getLocalUser() {
			return localUser;
		}

		public void controlDisplayHello(Hello h,String userName){
        	Date d = new Date();
        	String s = h.getUsername() +" ( "+ userName + ") :  says Hello to everyone, at " +DateFormat.getTimeInstance().format(d) + "\n";
 //       	gui.setLocalUser(localUser);
        	System.out.println("test 100 : " +this);
        	gui.displayMsg(s);
        }
       
        public void controlDisplayBye(GoodBye b,String userName){
        	Date d = new Date();
        	String s = userName +" : "+ " says Goodbye to everyone, at " +DateFormat.getTimeInstance().format(d) + "\n";
        	gui.displayMsg(s);
//        	gui.deleteUserFromUserPanel();
 //       	gui.deleteUserFromParticipates(userName);
        	
        }
        
        
        public void controlDisplayText(SendText t,String username){
        	
        	Date d = new Date();
        	String s = username +" : "+ " says " + t.getMessage()+ ", at " +DateFormat.getTimeInstance().format(d) + "\n";
 //       	gui.addNewTalk();
        	gui.displayMsg(s);
        	
        }
        
        public void controlDisplayHelloReply(HelloReply hr,String userName){
        	Date d = new Date();
        	System.out.println("User name of hello reply : "+hr.getUsername());
        	String s = hr.getUsername() +" ( "+ userName + ") :  says helloReply to you , at " +DateFormat.getTimeInstance().format(d) + "\n";
        	gui.displayMsg(s);
        }
        
        public void controlSendHelloReply(String adress){
        	try {
				network.signal_Hello_Reply(adress);
				System.out.println("Test : "+ InetAddress.getByName(adress));
			} catch (UnknownHostException e) {
				System.out.println("failed to send hello back !");
			}
        }
        
        public void controlSendText(String message,InetAddress[] users){
        	network.signal_Send_Text(users, message);
        }
        
        public void controlPropFile(String filename, long filesize, int file_id,String address){
        	network.signal_Propo_File(filename, filesize, file_id, address);
        	this.setFile(filename);
        	System.out.println("File name 2 : "+file);

        }
              
        public void dialogAcceptFile(String file){
        	fileAccept=new AcceptFiles(file);
        	System.out.println("File name 5: "+file);
        }
        
        public void controlAcceptFile(int fId, boolean acc, boolean now,String address){
        	network.siganl_accept_file(fId, acc, now, address);
        }
        
//        public void controlGetUserToTalk(ArrayList<RemoteUser> r) {
//            //Cr�ation d'une nouvelle discussion
//            localUser.addToTalk(r);
//            
//            ArrayList<RemoteUser> participants=localUser.getTalk().g
//            String[] contributorsIps=new String[participants.size()+1];
//            //On ajoute au participant de la discussion les utilisateurs s�lectionn�s
//            for(int i=0;i<participants.size();i++)
//            {
//                contributorsIps[i]=participants.get(i).getAddressIP().getHostAddress();
//            }
//            //On ajoute s'ajoute � la discussion
//            contributorsIps[participants.size()]=NetworkInterface.getLocalHost().getHostAddress();
//
//            
//            for(RemoteUser participant:localUser.getDiscussion(talkId).getParticipants())
//            {
//                logger.info("Envoi d'un NewTalk � "+participant.getUsername());
//                networkInterface.sendSignal(participant.getAddressIP(),new NewTalk(talkId,contributorsIps));
//            }
//
//        }
//        
//        public void controllerCloseThread(){       	
//        	network.signal_Bye();
//        	System.out.println("test cao ni ma1 : " + network.getUDPR());
//        	network.getUDPR().setActive(false);
//        }
//       
}

