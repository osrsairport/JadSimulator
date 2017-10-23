package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.beans.Transient;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

public class MyComponent extends JComponent {

    private Dimension size;
    private final ImageIcon icon;

    private static final long serialVersionUID = 2L;

    public MyComponent() {
	this(null);
    }

    public MyComponent(ImageIcon icon) {
	super();
	this.icon = icon;
    }

    @Override
    public void setBounds(int arg0, int arg1, int arg2, int arg3) {
	super.setBounds(arg0, arg1, arg2, arg3);
	size = new Dimension(arg2, arg3);
	System.out.println("a " + size);
    }

    @Override
    public void paintComponent(Graphics g) {
	super.paintComponents(g);
	if (icon != null)
	    g.drawImage(icon.getImage(), 0, 0, null);
    }

    @Override
    public void setPreferredSize(Dimension arg0) {
	super.setPreferredSize(arg0);
	size = arg0;
    }

    @Override
    @Transient
    public Dimension getPreferredSize() {
	return size;
    }
}