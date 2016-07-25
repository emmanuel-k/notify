package notify.graphics;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JTextArea;

import notify.model.Note;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class NoteView extends JTextArea {

	private static final long serialVersionUID = -5807439494722044321L;
	
	public NoteView(final Note note, final JFrame window) {
		Color lighter = new Color(0xC7EDFC);
		Color darken = new Color(0xE5F7FD);
		
		this.setText(note.getText());
		this.setEditable(false);
		this.setFont(new Font("Arial", Font.PLAIN, 12));
		this.setMargin(new Insets(20,20,20,20));
		this.setBackground(lighter);
		this.setLineWrap(true);
		this.setWrapStyleWord(true);
		
		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				new EditRemoveNoteFrame(note, window).setVisible(true);
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				setBackground(darken);
				setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				setBackground(lighter);
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
			}});
	}

}
