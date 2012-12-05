package model;

import java.net.InetAddress;

public class User {

    private String userName;
    private InetAddress userAddress;

    public User(String name) {
        userName = name;
    }

    public InetAddress getUserAddress() {
        return userAddress;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserAddress(InetAddress address) {
        userAddress = address;
    }

    public void setUserName(String name) {
        userName = name;
    }

    public String toString() {
        return userName;
    }

}


