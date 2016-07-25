package notify.graphics;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.JButton;

import notify.util.Img;

import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.text.html.HTMLEditorKit;

public class AboutDialog extends JDialog {

	private static final long serialVersionUID = -2150693545308602769L;
	
	public AboutDialog() {
		getContentPane().setBackground(Color.WHITE);
		setResizable(false);
		// Windows Parameters
		//=================================================
		this.setTitle("A propos de notify!");
		this.setSize(344, 335);
		this.setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// Set frame's icons
        ArrayList<Image> iconList = new ArrayList<Image>();
        iconList.add(new ImageIcon(Img.get("notify-24x24.png")).getImage());
        iconList.add(new ImageIcon(Img.get("notify-48x48.png")).getImage());
        iconList.add(new ImageIcon(Img.get("notify-64x64.png")).getImage());
        iconList.add(new ImageIcon(Img.get("notify-128x128.png")).getImage());
        this.setIconImages(iconList);
        
        // Icons
        //==================================================================
 		ImageIcon iconLogo;
 		iconLogo = new ImageIcon(Img.get("notify-48x48.png"));
        
        JButton btnFermer = new JButton("Fermer");
        btnFermer.setFont(new Font("Tahoma", Font.PLAIN, 11));
        btnFermer.setOpaque(false);
        
        JEditorPane txt = new JEditorPane();
        txt.setForeground(Color.DARK_GRAY);
        HTMLEditorKit kit = new HTMLEditorKit();
        txt.setEditorKit(kit);
        txt.setDocument(kit.createDefaultDocument());
        txt.setOpaque(false);
        txt.setEditable(false);
        txt.setText("<strong><em>Notify</em></strong> est un logiciel \u00E9crit en java "
        					+	"en guise de projet de fin d'\u00E9tude du cours de conception "
        					+	"des applications et atelier de programmation par l'\u00E9tudiant "
        					+	"<strong>KWENE NJUME Emmanuel</strong>  sous la direction de son enseignant "
        					+	"<strong>M. TCHAKOUNTE Franklin</strong>"
        					+	"<p><i>Email:</i>	<a href=\"mailto:kwene.emmanuel@gmail.com\"><i>kwene.emmanuel@gmail.com</i></a><br/>"
        					+	"<i>Site web:</i>	<a href=\"http://emmanuelkwene.com/\"><i>http://emmanuelkwene.com</i></a></p>");
        
        JPanel panel = new JPanel();
        panel.setBackground(new Color(0xF5FCFE));
        panel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(0xCCCCCC)));
        
        GroupLayout groupLayout = new GroupLayout(getContentPane());
        groupLayout.setHorizontalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
        			.addContainerGap(146, Short.MAX_VALUE)
        			.addComponent(btnFermer)
        			.addGap(135))
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
        				.addGroup(groupLayout.createSequentialGroup()
        					.addGap(10)
        					.addComponent(txt, GroupLayout.PREFERRED_SIZE, 318, Short.MAX_VALUE))
        				.addComponent(panel, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 338, GroupLayout.PREFERRED_SIZE))
        			.addContainerGap())
        );
        groupLayout.setVerticalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addComponent(panel, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(txt, GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
        			.addGap(18)
        			.addComponent(btnFermer)
        			.addContainerGap())
        );
        SpringLayout sl_panel = new SpringLayout();
        panel.setLayout(sl_panel);
        
        JLabel lblLogo = new JLabel("Notify", iconLogo, SwingConstants.CENTER);
        sl_panel.putConstraint(SpringLayout.WEST, lblLogo, 0, SpringLayout.WEST, panel);
        sl_panel.putConstraint(SpringLayout.EAST, lblLogo, 0, SpringLayout.EAST, panel);
        panel.add(lblLogo);
        lblLogo.setFont(new Font("Tahoma", Font.PLAIN, 22));
        
        JLabel lblVersion = new JLabel("Version 1.0");
        sl_panel.putConstraint(SpringLayout.NORTH, lblVersion, 6, SpringLayout.SOUTH, lblLogo);
        sl_panel.putConstraint(SpringLayout.WEST, lblVersion, 0, SpringLayout.WEST, lblLogo);
        sl_panel.putConstraint(SpringLayout.EAST, lblVersion, 0, SpringLayout.EAST, panel);
        panel.add(lblVersion);
        lblVersion.setHorizontalAlignment(SwingConstants.CENTER);
        lblVersion.setFont(new Font("Tahoma", Font.PLAIN, 11));
        getContentPane().setLayout(groupLayout);
        
        // ACTIONS
        //==================================================

        // Closing frame...
        btnFermer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				dispose();
			}
		});
	}
}
