package notify.graphics;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class NotifyScrollBarUI extends BasicScrollBarUI {
	
	public NotifyScrollBarUI() {
		super();
	}
	
	@Override
	protected JButton createDecreaseButton(int orientation) {
        JButton button = super.createDecreaseButton(orientation);
        button.setBackground(new Color(0xBBD6E0));
        button.setOpaque(false);
        return button;
    }

    @Override
    protected JButton createIncreaseButton(int orientation) {
        JButton button = super.createIncreaseButton(orientation);
        button.setBackground(new Color(0xBBD6E0));
        button.setOpaque(false);
        return button;
    }
    
    @Override
    protected void configureScrollBarColors() {
		super.thumbColor = new Color(0xEFD050);
		super.thumbDarkShadowColor = null;
		super.scrollBarWidth = 10;
		super.trackColor = new Color(255, 255, 255);
		super.decrButton = null;
    }
}
