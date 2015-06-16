package notify.graphics;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import notify.Main;
import notify.model.Note;
import notify.model.NoteManager;
import notify.model.User;
import notify.model.UserManager;
import notify.util.DB;
import notify.util.Img;


public class FenetrePrincipale extends NotifyFrame {
    
	private static final long serialVersionUID = -6424554976666914286L;
	private final static UserManager usermanager = new UserManager(DB.getInstance());
	private final static NoteManager notemanager = new NoteManager(DB.getInstance());
	private JTextArea noteTxtArea;
	private User currentUser;
	public JPanel panel;

	
    public FenetrePrincipale() {
    	// Initialisation
    	currentUser = usermanager.getCurrentUser();
    	
		// Checking if user is already logged
		//========================================s
		if(currentUser == null) {
			dispose();
		} else {
			final Color bgColor = new Color(0xffffff);
			final Color headBgColor = new Color(0xF5FCFE);
	        final Color footBgColor = new Color(0xE5F7FD);
	        final Color colorHeadBorderBottom = new Color(0xDBDBDB);
	        final Color colorFootBorderTop = new Color(0x9FE1F8);
	        final Color colorTextAreaPlaceholder = new Color(0x7FC1C8);
			
	        this.setTitle("Notify");
	        this.setSize(320, 520);
	        this.setLocationRelativeTo(null);
			this.getContentPane().setBackground(bgColor);

			ImageIcon iconLogo = new ImageIcon(Img.get("notify-24x24.png"));
			ImageIcon iconLogout = new ImageIcon(Img.get("shut-down.png"));
			ImageIcon iconExit = new ImageIcon(Img.get("cross.png"));
			ImageIcon iconAdd = new ImageIcon(Img.get("ok.png"));
			ImageIcon iconWrite = new ImageIcon(Img.get("arrow-round.png"));
	        
	        JMenuBar menuBar = new JMenuBar();
	        setJMenuBar(menuBar);
	        
	        JMenu mnNotify = new JMenu("Notify");
	        menuBar.add(mnNotify);
	        
	        JMenuItem mntmEffacerToutesLes = new JMenuItem("Effacer toutes les notes");
	        mnNotify.add(mntmEffacerToutesLes);
	        
	        JMenuItem mntmQuitter = new JMenuItem("Quitter");
	        mnNotify.add(mntmQuitter);
	        
	        JMenu mnAide = new JMenu("Aide");
	        menuBar.add(mnAide);
	        
	        JMenuItem mntmAPropos = new JMenuItem("A propos");
	        mnAide.add(mntmAPropos);
	        SpringLayout springLayout = new SpringLayout();
	        getContentPane().setLayout(springLayout);
	        
	        JPanel headPanel = new JPanel();
	        headPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, colorHeadBorderBottom));
	        headPanel.setBackground(headBgColor);
	        springLayout.putConstraint(SpringLayout.NORTH, headPanel, 0, SpringLayout.NORTH, getContentPane());
	        springLayout.putConstraint(SpringLayout.SOUTH, headPanel, 46, SpringLayout.NORTH, getContentPane());
	        getContentPane().add(headPanel);
	        
	        JPanel footPanel = new JPanel();
	        footPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, colorFootBorderTop));
	        footPanel.setBackground(bgColor);
	        springLayout.putConstraint(SpringLayout.WEST, headPanel, 0, SpringLayout.WEST, footPanel);
	        springLayout.putConstraint(SpringLayout.EAST, headPanel, 0, SpringLayout.EAST, footPanel);
	        SpringLayout sl_headPanel = new SpringLayout();
	        headPanel.setLayout(sl_headPanel);
	        
	        JLabel lblNom = new JLabel(currentUser.getName());
	        lblNom.setFont(new Font("Arial", Font.PLAIN, 14));
	        headPanel.add(lblNom);
	        
	        panel = new JPanel();
	        JScrollPane scrollPane = new JScrollPane(panel);
	        springLayout.putConstraint(SpringLayout.EAST, scrollPane, 0, SpringLayout.EAST, headPanel);
	        scrollPane.getVerticalScrollBar().setUI(new NotifyScrollBarUI());
	        scrollPane.setOpaque(false);
	        scrollPane.setBorder(null);
	        panel.setOpaque(false);
	        panel.setBorder(new EmptyBorder(0,0,0,4));
	        scrollPane.getViewport().setBackground(bgColor);
	        springLayout.putConstraint(SpringLayout.WEST, scrollPane, 10, SpringLayout.WEST, headPanel);
	        
	        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
	        springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 6, SpringLayout.SOUTH, headPanel);
	        
	        JLabel lblLogout = new JLabel(iconLogout);
	        sl_headPanel.putConstraint(SpringLayout.SOUTH, lblNom, -8, SpringLayout.SOUTH, lblLogout);
	        lblLogout.setToolTipText("D\u00E9connexion");
	        sl_headPanel.putConstraint(SpringLayout.NORTH, lblLogout, 8, SpringLayout.NORTH, headPanel);
	        headPanel.add(lblLogout);
	        
	        JLabel lblQuit = new JLabel(iconExit);
	        sl_headPanel.putConstraint(SpringLayout.NORTH, lblQuit, 8, SpringLayout.NORTH, headPanel);
	        sl_headPanel.putConstraint(SpringLayout.EAST, lblLogout, -6, SpringLayout.WEST, lblQuit);
	        sl_headPanel.putConstraint(SpringLayout.EAST, lblQuit, -10, SpringLayout.EAST, headPanel);
	        lblQuit.setToolTipText("Quitter");
	        headPanel.add(lblQuit);
	        
	        JLabel lblLogo = new JLabel(iconLogo);
	        sl_headPanel.putConstraint(SpringLayout.NORTH, lblLogo, 8, SpringLayout.NORTH, headPanel);
	        sl_headPanel.putConstraint(SpringLayout.WEST, lblNom, 6, SpringLayout.EAST, lblLogo);
	        sl_headPanel.putConstraint(SpringLayout.WEST, lblLogo, 10, SpringLayout.WEST, headPanel);
	        headPanel.add(lblLogo);
	        
	        JLabel lblVosNotes = new JLabel(", vos notes!");
	        lblVosNotes.setForeground(Color.LIGHT_GRAY);
	        lblVosNotes.setFont(new Font("Arial", Font.PLAIN, 11));
	        sl_headPanel.putConstraint(SpringLayout.NORTH, lblVosNotes, 3, SpringLayout.NORTH, lblNom);
	        sl_headPanel.putConstraint(SpringLayout.WEST, lblVosNotes, 2, SpringLayout.EAST, lblNom);
	        headPanel.add(lblVosNotes);
	        springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, -6, SpringLayout.NORTH, footPanel);
	        
	        displayNotes();
	        
	        getContentPane().add(scrollPane);
	        
	        
	        springLayout.putConstraint(SpringLayout.NORTH, footPanel, -54, SpringLayout.SOUTH, getContentPane());
	        springLayout.putConstraint(SpringLayout.WEST, footPanel, 0, SpringLayout.WEST, getContentPane());
	        springLayout.putConstraint(SpringLayout.SOUTH, footPanel, 0, SpringLayout.SOUTH, getContentPane());
	        springLayout.putConstraint(SpringLayout.EAST, footPanel, 314, SpringLayout.WEST, getContentPane());
	        getContentPane().add(footPanel);
	        SpringLayout sl_footPanel = new SpringLayout();
	        footPanel.setLayout(sl_footPanel);
	        
	        JLabel lblWrite = new JLabel(iconWrite);
	        lblWrite.setVerticalAlignment(SwingConstants.BOTTOM);
	        sl_footPanel.putConstraint(SpringLayout.NORTH, lblWrite, 10, SpringLayout.NORTH, footPanel);
	        sl_footPanel.putConstraint(SpringLayout.WEST, lblWrite, 10, SpringLayout.WEST, footPanel);
	        footPanel.add(lblWrite);
	        
	        final String textAreaPlaceholder = "Rediger une nouvelle note ici";
	        noteTxtArea = new JTextArea(textAreaPlaceholder);
	        noteTxtArea.setLineWrap(true);
	        noteTxtArea.setWrapStyleWord(true);
	        noteTxtArea.setBackground(bgColor);
	        noteTxtArea.setBorder(null);
	        noteTxtArea.setForeground(colorTextAreaPlaceholder);
	        noteTxtArea.setFont(new Font("Arial", Font.PLAIN, 13));
	  
	        JScrollPane textAreaScrollBar = new JScrollPane(noteTxtArea);
	        
	        sl_footPanel.putConstraint(SpringLayout.NORTH, textAreaScrollBar, 18, SpringLayout.NORTH, footPanel);
	        sl_footPanel.putConstraint(SpringLayout.WEST, textAreaScrollBar, 9, SpringLayout.EAST, lblWrite);
	        sl_footPanel.putConstraint(SpringLayout.SOUTH, textAreaScrollBar, 52, SpringLayout.NORTH, footPanel);
	        sl_footPanel.putConstraint(SpringLayout.EAST, textAreaScrollBar, -42, SpringLayout.EAST, footPanel);
	        textAreaScrollBar.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	        textAreaScrollBar.setBorder(null);
	        footPanel.add(textAreaScrollBar);
	        noteTxtArea.setColumns(10);
	        
	        JLabel lblAdd = new JLabel(iconAdd);
	        sl_footPanel.putConstraint(SpringLayout.WEST, lblAdd, 6, SpringLayout.EAST, textAreaScrollBar);
	        sl_footPanel.putConstraint(SpringLayout.SOUTH, lblAdd, -2, SpringLayout.SOUTH, lblWrite);
	        lblAdd.setHorizontalAlignment(SwingConstants.RIGHT);
	        footPanel.add(lblAdd);
	        lblAdd.setVisible(false);
	        
	        // Adding actions
	        //===================================================
	        
	        noteTxtArea.addFocusListener(new FocusListener() {

				@Override
				public void focusGained(FocusEvent arg0) {
					noteTxtArea.setBackground(footBgColor);
					footPanel.setBackground(footBgColor);
					lblAdd.setVisible(true);
					if(noteTxtArea.getText().equals(textAreaPlaceholder)) noteTxtArea.setText(""); noteTxtArea.setForeground(new Color(0x222222));
				}

				@Override
				public void focusLost(FocusEvent arg0) {
					noteTxtArea.setBackground(bgColor);
					footPanel.setBackground(bgColor);
					if(noteTxtArea.getText().equals("")) noteTxtArea.setText(textAreaPlaceholder); noteTxtArea.setForeground(colorTextAreaPlaceholder);
					if(noteTxtArea.getText().equals(textAreaPlaceholder)) lblAdd.setVisible(false);
				}});
	        
	        // SUBMITTING NOTE
	        //======================
	        lblAdd.addMouseListener(new MouseListener() {

				@Override
				public void mouseClicked(MouseEvent arg0) {
					String txt = noteTxtArea.getText();
					String dialogTitle = "Ajout d'une note";
					String erreur = "";
					
					if(txt.equals("")) {
						erreur = "Note vide!\nVeuillez écrire quelque chose!";
					} else {
						Note note = new Note();
						
						note.setId(0);
						note.setText(txt);
						note.setCreationTime();
						note.setAuthor(currentUser.getId());
						
						if(currentUser.hasNote(note)) {
							erreur = "Attention, cette note existe déjà!";
						} else {
							if(notemanager.create(note)) {
								currentUser.addNote(note);
								JOptionPane.showMessageDialog(null, "La note a bien été ajoutée!", dialogTitle, JOptionPane.INFORMATION_MESSAGE);
								dispose(); repaint();
								new Main();
							} else {
								erreur = "La note n'a pas pu être ajoutée \n Veuillez réessayer plus tard SVP!";
							}
						}
					}
					
					if(!erreur.equals("")) {
						JOptionPane.showMessageDialog(null, erreur, dialogTitle, JOptionPane.WARNING_MESSAGE);
					}
				}

				@Override
				public void mouseEntered(MouseEvent arg0) {
					setCursor(new Cursor(Cursor.HAND_CURSOR));
				}

				@Override
				public void mouseExited(MouseEvent arg0) {
					setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}

				@Override
				public void mousePressed(MouseEvent arg0) {}

				@Override
				public void mouseReleased(MouseEvent arg0) {}
			});
	        
	        mntmEffacerToutesLes.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					if(currentUser.getListeNote().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Vous n'avez aucune note présente!", "Suppresion", JOptionPane.WARNING_MESSAGE);
					} else {
						if(JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment supprimer toutes vos notes?") == JOptionPane.OK_OPTION) {
							usermanager.removeAllNotes(currentUser);
							dispose();
							new Main();
						}
					}
				}
			});
	        
	        // Closing frame...
	        mntmQuitter.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					dispose();
				}
			});
	        
	        // About dialog
	        mntmAPropos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					new AboutDialog().setVisible(true);
				}
			});
	        
	        lblLogout.addMouseListener(new MouseListener() {

				@Override
				public void mouseClicked(MouseEvent arg0) {
					if(usermanager.killSession()) {
						dispose();
						new Main();
					}
				}

				@Override
				public void mouseEntered(MouseEvent arg0) {
					setCursor(new Cursor(Cursor.HAND_CURSOR));
				}

				@Override
				public void mouseExited(MouseEvent arg0) {
					setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}

				@Override
				public void mousePressed(MouseEvent arg0) {}

				@Override
				public void mouseReleased(MouseEvent arg0) {}
			});
	        
	        lblQuit.addMouseListener(new MouseListener() {

				@Override
				public void mouseClicked(MouseEvent arg0) {
					dispose();
				}

				@Override
				public void mouseEntered(MouseEvent arg0) {
					setCursor(new Cursor(Cursor.HAND_CURSOR));
				}

				@Override
				public void mouseExited(MouseEvent arg0) {
					setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}

				@Override
				public void mousePressed(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseReleased(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}
			});
	        
	        setVisible(true);
		}
	
		
        
    }
    
    public void displayNotes() {
    	for(Note note : currentUser.getListeNote())	{
        	if(note != null) {
        		panel.add(Box.createHorizontalGlue());
            	panel.add(new NoteView(note, this));
            	panel.add(Box.createRigidArea(new Dimension(0, 30)));
        	}
        }
    }
}
