package notify.util;

import java.net.URL;

import notify.Main;

public class Img {
	/**
	 * Retourne le chemin vers une image
	 * @param img
	 * @return
	 */
	public static URL get(String img) { return Main.class.getResource("/icons/" + img); }
	
	private Img() {};
}
