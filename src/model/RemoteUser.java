package model;

import java.net.InetAddress;

/**
 * Classe RemoteUser
 *  Modèle associé à un utilisateur distant
 * @author yingqing
 */
public class RemoteUser {
	/**
	 * Adresse ip de remote user
	 * @see addressIP
	 */
	private InetAddress addressIP;
	
	/**
	 * Login de remote user
	 * @see username
	 */
	private String username;

	/**
	 * Consteur permet de construire un remote user a partir
	 * de son adresse ip et username
	 * @param addressIP
	 * @param username
	 */
    public RemoteUser(InetAddress addressIP,String username) {
    	this.addressIP=addressIP;
    	this.username=username;
    }

    /**
     * Redéfinition de la méthode d'affichage d'un utilisateur
     * distant (username/IP)
     * @return
     */
    public String toString(){
    	return getUsername()+"/"+getAddressIP().getHostAddress();
    }

    /**
     * @return addressIP
     */
    public InetAddress getAddressIP() {
        return addressIP;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * set the username
     * @param username
     */
    public void setUsername(String username) {
        this.username=username;
    }

    /**
     * set the setAddressIP
     * @param addressIP
     */
	public void setAddressIP(InetAddress addressIP) {
		this.addressIP = addressIP;
	}
    
    
}