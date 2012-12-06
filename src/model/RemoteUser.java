/*
 * Classe RemoteUser
 * Modèle associé à un utilisateur distant
 */

package model;

import java.net.InetAddress;


public class RemoteUser {

        private InetAddress addressIP;
        private String username;
        
        public RemoteUser(String username) {
          this.username=username;
      }

        public RemoteUser(InetAddress addressIP,String username) {
            this.addressIP=addressIP;
            this.username=username;
        }

        /**
         * Redéfinition de la méthode d'affichage d'un utilisateur
         * distant (username/IP)
         * @return
         */
    @Override
        public String toString()
        {
            return getUsername()+"/"+getAddressIP().getHostAddress();
        }

    /**
     * @return the addressIP
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
     * @param u
     */
    public void setUsername(String u) {
        username=u;
    }

	public void setAddressIP(InetAddress addressIP) {
		this.addressIP = addressIP;
	}
    
    
}