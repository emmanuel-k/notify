package notify;

public class NotifyService {
	private static Runner runner = new Runner();
	
	public static void start(String[] args) {
		Main.isRunning = true;
		runner.start();
	}
	
	public static void stop() {
		Main.isRunning = false;
		runner.stop();
	}
}