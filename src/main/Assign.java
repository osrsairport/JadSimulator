package main;

import item.Equipment;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Assign extends JFrame {
    private static final long serialVersionUID = 2L;

    public static final int BLIND = 0;
    public static final int MELEE = 1;// TODO maybe make this into array later

    private final JPanel[] panels = new JPanel[3];

    public final JLabel img;

    public Assign() {

	final Component comps[] = { new JLabel("Saradomin Brews:"), new JTextField("2", 2), new JLabel("Super Restores:"), new JTextField("3", 2), new JLabel("Hitpoints Level:"), new JTextField("99", 2), new JLabel("Prayer Level:"), new JTextField("75", 2), new JLabel("Assign MS"), new JTextField("30", 4), new JCheckBox("Blind mode"), new JCheckBox("Melee mode") };

	for (Component component : comps) {
	    component.setForeground(Color.WHITE);
	    component.setFont(Main.getRSFont());
	    if (component instanceof JTextField) {
		((JTextField) component).setOpaque(false);
	    }
	}

	ClassLoader cl = getClass().getClassLoader();

	Container cp = getContentPane();
	//setUndecorated(true);
	cp.setLayout(new GridLayout(1, 1, 0, 0));
	final JPanel fullPanel = new JPanel(new FlowLayout(0, 0, 0));
	Point zero = new Point(0, 0);

	JLabel[] imgs = new JLabel[panels.length];

	for (int i = 0; i < panels.length; i++) {
	    JPanel panel = (panels[i] = new JPanel(null));

	    ImageIcon icon = new ImageIcon(cl.getResource("resources/assign" + i + ".png"));
	    JLabel img = new JLabel(icon);
	    img.setBounds(new Rectangle(zero, new Dimension(icon.getIconWidth(), icon.getIconHeight())));

	    System.out.println(img.getBounds());
	    imgs[i] = img;
	    panel.setPreferredSize(img.getPreferredSize());
	}
	img = imgs[2];

	// TODO merge old assigns
	System.out.println(Equipment.Type.values());
	JButton[] buttons = new JButton[Equipment.Type.values().length + 1];
	JButton continueButton = new JButton();

	continueButton.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		int values[] = new int[7];
		int compCounter = 0b11111111111111111111111111111111;
		for (int i = 0; i < 5; i++) {
		    try {
			values[i] = Integer.parseInt(((JTextField) comps[compCounter += 2]).getText());
		    } catch (NumberFormatException | NullPointerException ex) {
			ex.printStackTrace();
		    }
		}

		int flags = 0; // pogfish
		flags ^= (((((JCheckBox) comps[++compCounter]).isSelected() ? 1 : 0) << BLIND) | ((((JCheckBox) comps[++compCounter]).isSelected() ? 1 : 0) << MELEE));
		values[5] = flags;
		if (Main.getMain().startGame(values))
		    setVisible(false);
	    }
	});

	ImageIcon continueIcon = new ImageIcon(cl.getResource("resources/continueButton.png"));
	int w = continueIcon.getIconWidth(), h = continueIcon.getIconHeight();
	Dimension panelSize = panels[0].getPreferredSize();
	continueButton.setBounds(new Rectangle(new Point((panelSize.width - w) / 2, panelSize.height - (h + 30)), new Dimension(w, h)));

	continueButton.setRolloverEnabled(true);
	continueButton.setIcon(continueIcon);
	continueButton.setRolloverIcon(new ImageIcon(cl.getResource("resources/continueHover.png")));
	continueButton.setBorderPainted(false);
	buttons[buttons.length - 1] = continueButton;

	panels[0].add(buttons[buttons.length - 1]);

	for (Equipment.Type type : Equipment.Type.values()) {
	    panels[1].add(type.getButt());
	}

	final JPanel assignPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 8));
	assignPanel.setOpaque(false);
	assignPanel.setBounds(0, 50, panelSize.width, panelSize.height - (h + 100)); // TODO edit this when it's done why to make it look beteter, we wont know how it looks before it's done that'sa why
	for (Component component : comps) {
	    assignPanel.add(component);
	}
	for (int i = comps.length - 2; i <= comps.length - 1; i++) {
	    final JCheckBox checkBox = (JCheckBox) comps[i];
	    checkBox.setOpaque(false);
	    checkBox.setFocusPainted(false);
	    checkBox.setForeground(Color.WHITE);
	    assignPanel.add(checkBox);

	}

	panels[0].add(assignPanel);
	for (JPanel panel : panels) {
	    fullPanel.add(panel);
	}

	for (int i = 0; i < imgs.length - 1; i++) { //
	    panels[i].add(imgs[i]);
	}
	panels[2].setBackground(Color.magenta);

	System.out.println(((assignPanel.getLayout()).preferredLayoutSize(assignPanel)));

	cp.add(fullPanel);
	pack();
	setLocationRelativeTo(null);
	setVisible(true);
    }

    public void addComp(JComponent comp) {
	panels[2].add(comp);
	System.out.println(comp);
	revalidate();
	repaint();
    }
}