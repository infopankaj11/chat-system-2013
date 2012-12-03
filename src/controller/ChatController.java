package controller;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.util.Date;

import signals.GoodBye;
import signals.Hello;
import signals.Signal;
import network.Network;
import model.ListUser;
import model.User;
import gui.GUI;

public class ChatController {
	
	private GUI gui;
	private Network network;
	private User localUser;
	private ListUser listUser;
	private boolean connected;
	
	
	public ChatController(){
		connected=false;
	}
	
	public ListUser getListUser() {
		return listUser;
	}

	public void setLocalUser(String localUser) {
		try {
			this.localUser = new User(localUser,InetAddress.getByName("127.0.0.1"));
		} catch (UnknownHostException e) {
			System.out.println("Fail to create a local user");
		}
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
		Network.signal_Hello();
		connected=true;
	}

	public void setGui(GUI gui) {
		this.gui = gui;
	}

	public void controlConnexion(){
		Network.signal_Hello();
	}
	
	public void controlDisconnexion(){
		Network.signal_Bye();
	}
	
	public void controlDisplayHello(Hello h){
        Date d = new Date();
        String s = h.getUsername() +" : "+ " says Hello to everyone, at " +DateFormat.getTimeInstance().format(d) + "\n";
        gui.displayMsg(s);
    }
	
	public void controlDisplayBye(GoodBye b){
        Date d = new Date();
        String s = localUser.getUserName() +" : "+ " says Goodbye to everyone, at " +DateFormat.getTimeInstance().format(d) + "\n";
        gui.displayMsg(s);
    }

	public void controllerAddUser(String userName, InetAddress adresse){
		listUser.addListUser(new User(userName,adresse));
    }
	
	
	
	

}
