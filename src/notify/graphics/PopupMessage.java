package notify.graphics;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import notify.util.Img;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.JTextArea;

import java.awt.event.MouseAdapter;

public class PopupMessage extends JDialog {

	private static final long serialVersionUID = 1057656267599864677L;

	public PopupMessage(String message) {
		ImageIcon headingIcon = new ImageIcon(Img.get("notify-24x24.png"));
		ImageIcon closeIcon = new ImageIcon(Img.get("arrow-bottom.png"));
		
		Dimension scrSize = Toolkit.getDefaultToolkit().getScreenSize();
		Insets toolHeight = Toolkit.getDefaultToolkit().getScreenInsets(this.getGraphicsConfiguration());
		
		setSize(300,102);
		setUndecorated(true);
		setLocation(scrSize.width - this.getWidth() - 5, scrSize.height - toolHeight.bottom - this.getHeight() - 5);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setAlwaysOnTop(true);
		
		Thread hidePopup = new Thread() {
			@Override
			public void run() {
				try {
					Thread.sleep(10000);
					dispose();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		
		// Dialog Style
		getRootPane().setBorder(BorderFactory.createLineBorder(new Color(0xCCCCCC)));
		getContentPane().setBackground(new Color(0xFFFFFF));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0xF5FCFE));
		panel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(0xCCCCCC)));
		
		JTextArea txtrMessage = new JTextArea();
		txtrMessage.setEditable(false);
		txtrMessage.setForeground(Color.GRAY);
		txtrMessage.setWrapStyleWord(true);
		txtrMessage.setLineWrap(true);
		txtrMessage.setFont(new Font("Arial", Font.PLAIN, 12));
		txtrMessage.setText(message);
		txtrMessage.addMouseListener(new MouseAdapter() {
			Color textColor = txtrMessage.getForeground();
			Color backgroundColor = txtrMessage.getBackground();
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				setCursor(new Cursor(Cursor.HAND_CURSOR));
				txtrMessage.setForeground(new Color(0xFFFFFF));
				txtrMessage.setBackground(new Color(137, 201, 46));
				txtrMessage.setMargin(new Insets(10,10,10,10));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				txtrMessage.setForeground(textColor);
				txtrMessage.setBackground(backgroundColor);
				txtrMessage.setMargin(new Insets(0,0,0,0));
			}
			@Override
			public void mouseClicked(MouseEvent arg0) {
				new FenetrePrincipale().setVisible(true);
				dispose();
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 301, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addComponent(txtrMessage, GroupLayout.PREFERRED_SIZE, 282, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(txtrMessage, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(28, Short.MAX_VALUE))
		);
		SpringLayout sl_panel = new SpringLayout();
		panel.setLayout(sl_panel);
		
		JLabel lblLogo = new JLabel(headingIcon);
		sl_panel.putConstraint(SpringLayout.NORTH, lblLogo, 0, SpringLayout.NORTH, panel);
		panel.add(lblLogo);
		
		JLabel lblNotify = new JLabel("Notify");
		lblNotify.setFont(new Font("Arial", Font.PLAIN, 13));
		sl_panel.putConstraint(SpringLayout.NORTH, lblNotify, 7, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, lblNotify, 40, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, lblLogo, -6, SpringLayout.WEST, lblNotify);
		panel.add(lblNotify);
		
		JLabel lblCloseicon = new JLabel(closeIcon);
		lblCloseicon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
			}
		});
		sl_panel.putConstraint(SpringLayout.NORTH, lblCloseicon, -3, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.EAST, lblCloseicon, -10, SpringLayout.EAST, panel);
		panel.add(lblCloseicon);
		getContentPane().setLayout(groupLayout);
		
		hidePopup.start();
		
		setVisible(true);
	}
}