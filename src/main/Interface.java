package main;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Interface {

    public static final String INVENTORY = "Inventory", PRAYER = "Prayer", LOGOUT = "Logout";

    private static HashMap<Interface, String> alltheInterfaces = new HashMap<Interface, String>();

    protected final JPanel panel;
    private final JButton button;
    private final JLabel img;

    private static int width = -1, height = -1;

    public Interface(Rectangle buttonLocation, final String name, final Main main) {
	button = new JButton();

	panel = new JPanel(null);
	panel.setBackground(Color.magenta);
	System.out.println(getClass().getClassLoader());
	ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("resources/" + name + ".png"));
	panel.setBounds(new Rectangle(new Point(0, 0), new Dimension(icon.getIconWidth(), icon.getIconHeight())));
	img = new JLabel(icon);
	if (icon.getIconHeight() != Jad.getJad().getHeight())
	    System.err.println("The height of the panels are not equal to jad height");
	if ((width & height) == -1) {
	    width = icon.getIconWidth();
	    height = icon.getIconHeight();
	} else if (icon.getIconWidth() != width && icon.getIconHeight() != height)
	    System.err.println("The sizes of the panels are not equal");

	img.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());

	panel.setBackground(Color.BLUE);

	Main.invis(button);
	button.setBounds(buttonLocation);
	button.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		try {
		    JPanel cardPanel = main.getCardPanel();
		    CardLayout layout = (CardLayout) cardPanel.getLayout();
		    layout.show(cardPanel, name);
		} catch (IllegalArgumentException e1) {
		    e1.printStackTrace();
		}
	    }

	});
	panel.setVisible(false);
	// System.out.printf("%s\n%s\n", panel, buttonInv);

    }

    public JButton getButton() {
	return button;
    }

    public JPanel getPanel() {
	return panel;
    }

    @Override
    public String toString() {
	return String.format("%s[%s]", this.getClass().getName(), getPanel());
    }

    public static HashMap<Interface, String> getAllInterfaces() {
	return alltheInterfaces;
    }

    public JLabel getImg() {
	return img;
    }
}