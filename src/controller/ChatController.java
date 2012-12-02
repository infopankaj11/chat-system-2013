package controller;
import java.net.InetAddress;
import java.text.DateFormat;
import java.util.Date;

import signals.Hello;
import signals.Signal;
import network.Network;
import model.ListUser;
import model.User;
import gui.GUI;

public class ChatController {
	
	private GUI gui;
	private Network ni;
	private User localUser;
	private ListUser listUser;
	
	
	public ChatController(){
		this.listUser=null;
//		localUser.setConnected(false);
	}
	
	public void setLocalUser(String localUser) {
		this.localUser = new User(localUser);
	}

	public User getLocalUser(){
		return localUser;
	}
	
	public void setNi(Network ni){
		this.ni=ni;
	}
	
	public void lancheNi(){
		Network ni=new Network(this);
		this.setNi(ni);
		Network.signal_Hello();
	//	localUser.setConnected(true);
	}

	public void setGui(GUI gui) {
		this.gui = gui;
	}


	
	public void controlConnexion(){
		Network.signal_Hello();
	}
	
	public void controlDisplayHello(Hello h){
        Date d = new Date();
        String s = h.getUsername() +" : "+ " says Hello to everyone, at " +DateFormat.getTimeInstance().format(d);
        gui.displayMsg(s);
    }

	
	
	
	
	

}
