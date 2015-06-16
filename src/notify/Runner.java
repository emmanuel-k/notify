package notify;

import notify.graphics.PopupMessage;
import notify.model.User;
import notify.model.UserManager;
import notify.util.DB;
import notify.util.Internet;

public class Runner {
	private final static UserManager usermanager = new UserManager(DB.getInstance());
	private User currentUser;
	private static boolean started = false;
	
	public Runner() {}
	
	public void start() {
		started = true;
		
		while (started) {
			synchronized (this) {
				currentUser = usermanager.getCurrentUser();
				if(currentUser != null) {
					if( !currentUser.getListeNote().isEmpty() ) {
						if(Internet.isAvailable()) {
				   			new PopupMessage("Hey "+currentUser.getName()+"! Je vois que tu es connecté, clique ici, tu as des notes!");
				   		} 
					}
				}
				try {
					this.wait(30000);
				} catch (InterruptedException ie) {}
			}
		}
	}
	
	public void stop() {
		started = false;
		synchronized (this) {
			this.notify();
		}
	}
}