package notify;

import notify.graphics.ConnexionFrame;
import notify.graphics.FenetrePrincipale;
import notify.model.User;
import notify.model.UserManager;
import notify.util.DB;

public class Main {
	private final static UserManager usermanager = new UserManager(DB.getInstance());
	private User currentUser;
	public static boolean isRunning = false;
    
	public Main() {
    	// Initialisation
    	currentUser = usermanager.getCurrentUser();
    	
    	if(currentUser == null) {
        	new ConnexionFrame();
		} else {
			new FenetrePrincipale();
		}
	}
	
    public static void main(String[] args) {
    	
    	new Main();
    	NotifyService.start(args);
    	
    }
    
    public static void Terminate() {
    	NotifyService.stop();
    	System.exit(0);
    }
    
} 