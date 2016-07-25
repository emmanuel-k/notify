package notify.graphics;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;

import notify.model.Note;
import notify.model.NoteManager;
import notify.model.User;
import notify.model.UserManager;
import notify.util.DB;
import notify.util.Img;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JScrollPane;
import javax.swing.SpringLayout;

public class EditRemoveNoteFrame extends JDialog {

	private static final long serialVersionUID = -2969433881886489473L;
	private NoteManager notemanager = new NoteManager(DB.getInstance());
	private final static UserManager usermanager = new UserManager(DB.getInstance());
	private User currentUser;

	
	public EditRemoveNoteFrame(final Note note, final JFrame window) {
    	// Initialisation
    	currentUser = usermanager.getCurrentUser();
		
		// Windows Parameters
		//=================================================
		String fTitle = "Details de la note!";
		this.setTitle(fTitle);
		this.setSize(360, 183);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// Set frame's icons
        ArrayList<Image> iconList = new ArrayList<Image>();
        iconList.add(new ImageIcon(Img.get("notify-24x24.png")).getImage());
        iconList.add(new ImageIcon(Img.get("notify-48x48.png")).getImage());
        iconList.add(new ImageIcon(Img.get("notify-64x64.png")).getImage());
        iconList.add(new ImageIcon(Img.get("notify-128x128.png")).getImage());
        this.setIconImages(iconList);
        this.setBackground(Color.RED);
        
        // ICONES
        //======================
		ImageIcon iconUpdate = new ImageIcon(Img.get("reload.png"));
		ImageIcon iconDelete = new ImageIcon(Img.get("delete-item.png"));
        
        JPanel headPanel = new JPanel();
        headPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(0xDBDBDB)));
        headPanel.setBackground(new Color(0xF5FCFE));
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setOpaque(false);
        scrollPane.setBorder(null);
        GroupLayout groupLayout = new GroupLayout(getContentPane());
        groupLayout.setHorizontalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addComponent(headPanel, GroupLayout.PREFERRED_SIZE, 356, GroupLayout.PREFERRED_SIZE)
        				.addGroup(groupLayout.createSequentialGroup()
        					.addContainerGap()
        					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 333, GroupLayout.PREFERRED_SIZE)))
        			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        groupLayout.setVerticalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addComponent(headPanel, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        
        JTextArea textArea = new JTextArea();
        textArea.setText(note.getText());
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setOpaque(false);
        textArea.setBorder(null);
        textArea.setFont(new Font("Arial", Font.PLAIN, 13));
        textArea.setForeground(new Color(0x555555));
        scrollPane.setViewportView(textArea);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.getViewport().setBackground(Color.BLACK);
        SpringLayout sl_headPanel = new SpringLayout();
        headPanel.setLayout(sl_headPanel);
        
        JLabel lblHeadline = new JLabel("Actions sur cette note");
        sl_headPanel.putConstraint(SpringLayout.WEST, lblHeadline, 10, SpringLayout.WEST, headPanel);
        lblHeadline.setFont(new Font("Tahoma", Font.PLAIN, 14));
        headPanel.add(lblHeadline);
        
        JLabel lblDelete = new JLabel(iconDelete);
        lblDelete.setToolTipText("Supprimer la note!");
        sl_headPanel.putConstraint(SpringLayout.SOUTH, lblHeadline, 0, SpringLayout.SOUTH, lblDelete);
        sl_headPanel.putConstraint(SpringLayout.NORTH, lblDelete, 10, SpringLayout.NORTH, headPanel);
        sl_headPanel.putConstraint(SpringLayout.EAST, lblDelete, -10, SpringLayout.EAST, headPanel);
        headPanel.add(lblDelete);
        
        JLabel lblUpdate = new JLabel(iconUpdate);
        lblUpdate.setToolTipText("Mettre \u00E0 jour la note!");
        sl_headPanel.putConstraint(SpringLayout.NORTH, lblUpdate, 10, SpringLayout.NORTH, headPanel);
        sl_headPanel.putConstraint(SpringLayout.EAST, lblUpdate, -6, SpringLayout.WEST, lblDelete);
        headPanel.add(lblUpdate);
        getContentPane().setLayout(groupLayout);
        
        getContentPane().setBackground(Color.WHITE);
        
        // ACTIONS
        //=======================================
        lblUpdate.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				String txt = textArea.getText();
				String dialogTitle = "Modification de la note";
				String erreur = "";
				
				if(txt.equals("")) {
					erreur = "Note vide!\nVeuillez écrire quelque chose ou alors supprimez simplement la note!";
				} else {
					note.setText(txt);
					if(notemanager.update(note)) {
						JOptionPane.showMessageDialog(null, "La note a bien été mise à jour!", dialogTitle, JOptionPane.INFORMATION_MESSAGE);
						dispose();
						window.dispose();
						new FenetrePrincipale().setVisible(true);
					} else {
						erreur = "La note n'a pas pu être mise à jour \n Veuillez réessayer plus tard SVP!";
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
        
        // REMOVING
        lblDelete.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment supprimer cette note?") == JOptionPane.OK_OPTION) {
					if(notemanager.delete(note)) {
						currentUser.removeNote(note);
						JOptionPane.showMessageDialog(null, "La note a bien été supprimée!", "Suppresion", JOptionPane.INFORMATION_MESSAGE);
						dispose();
						window.dispose();
						new FenetrePrincipale().setVisible(true);
					}
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
	}
}
