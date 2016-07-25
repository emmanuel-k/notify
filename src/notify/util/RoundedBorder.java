package notify.util;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;

import javax.swing.border.Border;

public class RoundedBorder implements Border {
    
    private int radius;
    private Color color;
    
    public RoundedBorder(int radius) {
        this.radius = radius;
        this.color = Color.BLACK;
    }
    
    public RoundedBorder(int radius, Color arg) {
        this.radius = radius;
        this.color = arg;
    }
    
    //@Override
    public Insets getBorderInsets(Component c) {
        return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
    }

    //@Override
    public boolean isBorderOpaque() {
        return true;
    }

    //@Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
    	g.setColor(this.color);
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.drawRoundRect(x,y,width-1,height-1,radius,radius);
    }
}