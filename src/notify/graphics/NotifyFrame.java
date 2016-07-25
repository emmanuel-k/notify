package notify.graphics;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import notify.Main;
import notify.util.Img;

public abstract class NotifyFrame extends JFrame {

	private static final long serialVersionUID = -365905174983910391L;
	TrayIcon trayIcon;
    SystemTray tray;

	public NotifyFrame() {
		// Windows Parameters
		//=================================================
		this.setTitle("Notify");
		this.setSize(300, 430);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.getContentPane().setBackground(new Color(204, 224, 234));
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
		// Set frame's icons
        ArrayList<Image> iconList = new ArrayList<Image>();
        iconList.add(new ImageIcon(Img.get("notify-24x24.png")).getImage());
        iconList.add(new ImageIcon(Img.get("notify-48x48.png")).getImage());
        iconList.add(new ImageIcon(Img.get("notify-64x64.png")).getImage());
        iconList.add(new ImageIcon(Img.get("notify-128x128.png")).getImage());
        this.setIconImages(iconList);
      
        if( Main.isRunning == false )
        {
        	//Check the SystemTray is supported
        	if (!SystemTray.isSupported())
        	{
                System.out.println("SystemTray is not supported");
                return;
            }
        	
            final PopupMenu popup = new PopupMenu();
            final TrayIcon trayIcon = new TrayIcon(new ImageIcon(Img.get("notify-16x16.png")).getImage());
            final SystemTray tray = SystemTray.getSystemTray();
           
            // Create a pop-up menu components
            MenuItem displayMenu = new MenuItem("Ouvrir la fenêtre");
            MenuItem exitItem = new MenuItem("Quitter");
           
            //Add components to pop-up menu
            popup.add(displayMenu);
            popup.add(exitItem);
            
            // Terminer mon programme
            displayMenu.addActionListener(new ActionListener() {
    			@Override
    			public void actionPerformed(ActionEvent arg0) {
    				new Main();
    			}
    		});
            
            // Terminer mon programme
            exitItem.addActionListener(new ActionListener() {
    			@Override
    			public void actionPerformed(ActionEvent arg0) {
    				Main.Terminate();
    			}
    		});
           
            trayIcon.setPopupMenu(popup);
           
            try {
                tray.add(trayIcon);
            } catch (AWTException e) {
                System.out.println("TrayIcon could not be added.");
            }
        }        
	}

}
