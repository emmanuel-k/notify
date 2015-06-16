package notify.util;

import java.net.HttpURLConnection;
import java.net.URL;

public class Internet {
	
    public static boolean isAvailable() {

        try {

            try {
            	
                URL url = new URL("http://www.google.com");
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.connect();
                if (con.getResponseCode() == 200) return true;
                
            } catch (Exception exception) { return false; }
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }
    
    // On empeche l'instantiation de la classe
    private Internet() {}
}