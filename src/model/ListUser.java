package model;

import java.util.ArrayList;
import java.util.Vector;

public class ListUser
{
   private Vector<User> listUser;
   
   public ListUser(Vector<User> listUser) {
	this.listUser = listUser;
   }

   public Vector<User> getListUser() {
	   return listUser;
   }

   public void setListUser(Vector<User> listUser) {
	   this.listUser = listUser;
   }
   
   public void addListUser(User user){
       listUser.add(user);
}

   
   
   
}