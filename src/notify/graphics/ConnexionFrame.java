package notify.graphics;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Set;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import notify.model.User;
import notify.model.UserManager;
import notify.util.DB;
import notify.util.Img;

public class ConnexionFrame extends NotifyFrame {
	private static final long serialVersionUID = -8250045061732121562L;
	private JComboBox<User> userCbx = new JComboBox<User>();
	private JPasswordField mdp;
	private JButton btnConnexion;
	private JLabel adduser;
	private UserManager usermanager = new UserManager(DB.getInstance());
	private User nouser = new User(0, "Aucun utilisateur...", "");

	public ConnexionFrame() {
		// Checking if user is already logged
		//========================================
		this.setTitle("Notify - Connexion à votre compte");
		this.setSize(338, 476);
		this.setLocationRelativeTo(null);
		
		// Icons
		//==================================================================
		ImageIcon iconLogo, iconUser, iconPass, iconAddUser;
		iconLogo = new ImageIcon(Img.get("notify-48x48.png"));
		iconUser = new ImageIcon(Img.get("user.png"));
		iconPass = new ImageIcon(Img.get("pass.png"));
		iconAddUser = new ImageIcon(Img.get("adduser.png"));
		
		JLabel logo = new JLabel(iconLogo);
		
		JLabel lbluser = new JLabel("Utilisateur", iconUser, SwingConstants.CENTER);
		lbluser.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		// Adding registered users to ComboBox
		Set<User> usersList = usermanager.findAll();
		
		if(usersList.isEmpty()) {
			userCbx.addItem(nouser);
		} else {
			for(User user: usersList) {
				userCbx.addItem(user);
			}
		}
		
		userCbx.setFont(new Font("Tahoma", Font.PLAIN, 13));
		userCbx.setOpaque(false);
		userCbx.setFocusable(false);
		
		JLabel lblmdp = new JLabel("Mot de passe", iconPass, SwingConstants.CENTER);
		lblmdp.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		mdp = new JPasswordField();
		
		btnConnexion = new JButton("Connexion");
		btnConnexion.setOpaque(false);
		btnConnexion.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		adduser = new JLabel("Ajouter un nouvel utilisateur", iconAddUser, SwingConstants.CENTER);
		adduser.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		JTextPane txtDescrip = new JTextPane();
		txtDescrip.setForeground(new Color(0x777777));
		txtDescrip.setEditable(false);
		txtDescrip.setText("Notify vous permet d'enregistrer de petites notes qui vous seront notifi\u00E9es plus tard lorsque vous serez connect\u00E9 \u00E0 Internet!");
		txtDescrip.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		txtDescrip.setOpaque(false);
		StyledDocument doc = txtDescrip.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		
		// Positioning components
		//==========================================================
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addComponent(adduser, GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblmdp, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
						.addComponent(mdp, GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
						.addComponent(btnConnexion, GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
						.addComponent(userCbx, Alignment.TRAILING, 0, 312, Short.MAX_VALUE)
						.addComponent(lbluser, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(logo, GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(txtDescrip, GroupLayout.PREFERRED_SIZE, 312, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(logo)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtDescrip, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(lbluser)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(userCbx, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblmdp)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(mdp, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addGap(30)
					.addComponent(btnConnexion, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addGap(41)
					.addComponent(adduser, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
		);
		getContentPane().setLayout(groupLayout);
		
		// Adding actions
		//===================================================
		// Connection trying operation...
		btnConnexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if(userCbx.getItemCount() > 0 && !userCbx.getSelectedItem().toString().equals(nouser.getName())) {
					@SuppressWarnings("deprecation")
					String password = mdp.getText();
					String username = userCbx.getSelectedItem().toString();
					User loggedUser = usermanager.connectUser(username, password);
					
					try {
						loggedUser.getName();
						dispose();
						new FenetrePrincipale();
					} catch(Exception e) {
						JOptionPane.showMessageDialog(null, "Mot de passe incorrect", "Connexion", JOptionPane.WARNING_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Veuillez choisir un nom d'utilisateur", "Connexion", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
		// Opening registering frame...		
		adduser.addMouseListener(new MouseListener() {
			Color oldBgColor = adduser.getBackground();
			Color oldFgColor = adduser.getForeground();
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
				new RegisterFrame();
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				setCursor(new Cursor(Cursor.HAND_CURSOR));
				adduser.setBackground(new Color(137, 201, 46));
				adduser.setForeground(new Color(255, 255, 255));
				adduser.setOpaque(true);
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				adduser.setBackground(oldBgColor);
				adduser.setForeground(oldFgColor);
				adduser.setOpaque(false);
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
			}});
				
		
		// Displaying frame
		//=================
		setVisible(true);
	}
}
