package notify.graphics;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Set;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import notify.Main;
import notify.model.User;
import notify.model.UserManager;
import notify.util.DB;
import notify.util.Img;

public class RegisterFrame extends NotifyFrame {

	private static final long serialVersionUID = 9021638823213674887L;
	private JTextField usernamefield;
	private JPasswordField mdp;
	private JButton btnReg;
	private JLabel lbltitre;
	private UserManager usermanager = new UserManager(DB.getInstance());

	public RegisterFrame() {
        // Icons
        //==================================================================
 		ImageIcon iconLogo, iconUser, iconPass, iconAddUser;
		iconLogo = new ImageIcon(Img.get("notify-128x128.png"));
		iconUser = new ImageIcon(Img.get("user.png"));
		iconPass = new ImageIcon(Img.get("pass.png"));
		iconAddUser = new ImageIcon(Img.get("adduser.png"));
 		
 		JLabel logo = new JLabel(iconLogo);
 		
 		JLabel lbluser = new JLabel("Nom d'utilisateur", iconUser, SwingConstants.RIGHT);
 		lbluser.setFont(new Font("Tahoma", Font.PLAIN, 11));
 		
 		usernamefield = new JTextField();
 		
 		JLabel lblmdp = new JLabel("Mot de passe", iconPass, SwingConstants.RIGHT);
 		lblmdp.setFont(new Font("Tahoma", Font.PLAIN, 11));
 		
 		mdp = new JPasswordField();
 		
 		btnReg = new JButton("S'incrire");
 		btnReg.setFont(new Font("Tahoma", Font.PLAIN, 11));
 		btnReg.setOpaque(false);
 		
 		lbltitre = new JLabel("Nouvel Utilisateur", iconAddUser, SwingConstants.RIGHT);
 		lbltitre.setFont(new Font("Tahoma", Font.PLAIN, 22));
 		
 		// Positioning components
 		//==========================================================
 		GroupLayout groupLayout = new GroupLayout(getContentPane());
 		// Horizontal
 		groupLayout.setHorizontalGroup(
 			groupLayout.createParallelGroup(Alignment.LEADING)
 				.addGroup(groupLayout.createSequentialGroup()
 					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
 						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
 							.addContainerGap()
 							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
 								.addComponent(usernamefield, 0, 274, Short.MAX_VALUE)
 								.addComponent(mdp, GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
 								.addGroup(groupLayout.createSequentialGroup()
 									.addGap(0)
 									.addComponent(logo))))
 						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
 							.addContainerGap()
 							.addComponent(btnReg, GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE))
 						.addGroup(groupLayout.createSequentialGroup()
 							.addGap(39)
 							.addComponent(lbltitre))
 						.addGroup(groupLayout.createSequentialGroup()
 							.addContainerGap()
 							.addComponent(lbluser))
 						.addGroup(groupLayout.createSequentialGroup()
 							.addContainerGap()
 							.addComponent(lblmdp)))
 					.addContainerGap())
 		);
 		// Vertical
 		groupLayout.setVerticalGroup(
 			groupLayout.createParallelGroup(Alignment.LEADING)
 				.addGroup(groupLayout.createSequentialGroup()
 					.addContainerGap()
 					.addComponent(lbltitre)
 					.addGap(0)
 					.addComponent(logo)
 					.addGap(4)
 					.addComponent(lbluser)
 					.addPreferredGap(ComponentPlacement.UNRELATED)
 					.addComponent(usernamefield, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
 					.addGap(18)
 					.addComponent(lblmdp)
 					.addGap(11)
 					.addComponent(mdp, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
 					.addGap(26)
 					.addComponent(btnReg, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
 					.addContainerGap(140, Short.MAX_VALUE))
 		);
 		getContentPane().setLayout(groupLayout);
 		
 		this.setVisible(true);
		
		// Adding actions
		//===================================================
 		
 		// Connection trying operation...
 		btnReg.addActionListener(new ActionListener() {
 			public void actionPerformed(ActionEvent ae) {
 				
				@SuppressWarnings("deprecation")
				String password = mdp.getText();
				String username = usernamefield.getText();
				
				User user = new User();
				user.setName(username);
				user.setPassword(password);
				
				Set<User> users = usermanager.findAll();
				String erreur = "";
				
				if( (username.equals("")) || (password.equals("")) ) {
					erreur = "Veuillez remplir tous les champs!";
				} else {
					
					for(User u: users) {
						if(u.getName().equals(user.getName())) {
							erreur = "Ce nom d'utilisateur est déjà pris, veuillez en choisir un autre!";
							break;
						}
					}
					// Display errors
					if(!erreur.equals("")) {
						JOptionPane.showMessageDialog(null, erreur, "Inscription", JOptionPane.WARNING_MESSAGE);
						erreur = "";
					} else {
						if(usermanager.create(user)) {
							JOptionPane.showMessageDialog(null, "Votre inscription a bien été effectuée.\n Sur la fenêtre de connexion veuillez choisir votre nom d'utilisateur et vous connecter!", "Inscription", JOptionPane.INFORMATION_MESSAGE);
							dispose();
						} else {
							erreur = "Inscription echoué, veuillez réessayer plus tard!";
						}
					}
				}
				
				// Display errors
				if(!erreur.equals("")) {
					JOptionPane.showMessageDialog(null, erreur, "Inscription", JOptionPane.WARNING_MESSAGE);
					erreur = "";
				}
			}
		});		
        
 		// When closing windows
        this.addWindowListener(new WindowListener() {
			@Override
			public void windowActivated(WindowEvent arg0) {}

			@Override
			public void windowClosed(WindowEvent arg0) { new Main(); }

			@Override
			public void windowClosing(WindowEvent arg0) {}
			@Override
			public void windowDeactivated(WindowEvent arg0) {}
			@Override
			public void windowDeiconified(WindowEvent arg0) {}
			@Override
			public void windowIconified(WindowEvent arg0) {}
			@Override
			public void windowOpened(WindowEvent arg0) {}
		});

	}

}
