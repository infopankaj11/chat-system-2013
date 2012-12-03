package model;

import java.util.ArrayList;

public class ListUser {
	
	private ArrayList<User> listUser=new ArrayList<User>();

	public ListUser(ArrayList<User> listUser) {
		super();
		this.listUser = listUser;
	}

	public ArrayList<User> getListUser() {
		return listUser;
	}

	public void setListUser(ArrayList<User> listUser) {
		this.listUser = listUser;
	}
	
	public void addListUser(User user){
		listUser.add(user);
	}
	
}
