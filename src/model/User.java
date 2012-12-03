package model;

import java.net.InetAddress;

public class User {
	
	private String userName;
    private InetAddress userAddress;
    private boolean connected;

    public User(String name,InetAddress address) {
        this.userName = name;
        this.userAddress=address;
        
    }

    public boolean isConnected() {
		return connected;
	}

	public void setConnected(boolean connected) {
		this.connected = connected;
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
}
