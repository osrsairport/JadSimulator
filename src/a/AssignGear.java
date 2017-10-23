package a;

import item.Equipment;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.Transient;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("unused")
// XXX FIXME
public class AssignGear extends JFrame {

    private static final long serialVersionUID = 4L;

    public class MyButton extends JButton {
	private static final long serialVersionUID = 6L;

	public MyButton(int x, int y, int width, int height) {
	    super();
	    setBounds(x, y, width, height);
	}
    }

    public AssignGear(final ActionListener actionListener) {

	setUndecorated(true);

	ClassLoader cl = getClass().getClassLoader();
	final ImageIcon icon1 = new ImageIcon(cl.getResource("resources/gearAssign.png"));
	final ImageIcon icon2 = new ImageIcon(cl.getResource("resources/gearSelect.png"));
	final ImageIcon icon3 = new ImageIcon(cl.getResource("resources/gearInv.png"));

	final Image img1 = icon1.getImage();
	final Image img2 = icon2.getImage();
	final Image img3 = icon3.getImage();

	final JPanel gearPanel = new JPanel(new BorderLayout());
	final JPanel invPanel = new JPanel(new BorderLayout());
	final JPanel assignPanel = new JPanel(null/* might want this flowlayout, doesn't matter for now */);
	final JPanel fullPanel = new JPanel(new BorderLayout());

	final JLabel assignImg = new JLabel(icon1)

	{
	    private static final long serialVersionUID = 5L;

	    @Override
	    public void paintComponent(Graphics g) {
		g.drawImage(img3, 0, 0, null);
	    }

	    @Override
	    public Rectangle getBounds() {
		return new Rectangle(0, 0, icon2.getIconWidth(), icon2.getIconHeight());
	    }

	    @Override
	    public Dimension getPreferredSize() {
		return getBounds().getSize();
	    }
	};

	final JLabel gearImg = new JLabel(icon2)

	{
	    private static final long serialVersionUID = 6L;

	    @Override
	    public void paintComponent(Graphics g) {
		g.drawImage(img1, 0, 0, null);
	    }

	    @Override
	    public Rectangle getBounds() {
		return new Rectangle(0, 0, icon1.getIconWidth(), icon1.getIconHeight());
	    }

	    @Override
	    public Dimension getPreferredSize() {
		return getBounds().getSize();
	    }
	};

	final JLabel invImg = new JLabel(icon3)

	{ // thats new
	    private static final long serialVersionUID = 7L;

	    @Override
	    public void paintComponent(Graphics g) {
		g.drawImage(img2, 0, 0, null);
	    }

	    @Override
	    public Rectangle getBounds() {
		return new Rectangle(0, 0, icon2.getIconWidth(), icon2.getIconHeight());
	    }

	    @Override
	    @Transient
	    public Dimension getPreferredSize() {
		return getBounds().getSize();
	    }
	};

	final JButton[] buttons = new JButton[Equipment.Type.values().length + 1];

	for (int i = 0; i < buttons.length - 1; i++) {
	    Equipment.Type[] type = Equipment.Type.values();
	    buttons[i] = type[i].getButt();
	    // buttons[i].addActionListener(type[/i]);

	}
	buttons[11] = new JButton();
	buttons[11].setBounds(10, 272, 158, 32);

	buttons[11].setRolloverEnabled(true);
	buttons[11].setIcon(new ImageIcon(cl.getResource("resources/continueButton.png")));
	buttons[11].setRolloverIcon(new ImageIcon(cl.getResource("resources/continueHover.png")));
	buttons[11].setBorderPainted(false);

	buttons[11].addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		setVisible(false);
	    }

	});
	// final HashMap<JButton, Equipment> inventory = new HashMap<JButton, Equipment>();

	/*
	 * //we'll have it here just in case WE ACTUALLY NEED THIS oh but we might need it later for (int i=0;i<=10;i++) {buttons[i].addActionListener(new ActionListener() {
	 * @Override public void actionPerformed(ActionEvent e) { for(int counter=0;counter<=10;counter++) { //Equipment gear = new Equipment(); JButton itemButton = new JButton(); itemButton.setMargin(new Insets(1, 1, 1, 1)); itemButton.setBorder(null); //itemButton.setContentAreaFilled(false); int x = counter % 4, y = counter / 4; itemButton.setBounds(226 + x * 42, 45 + y * 36, 32, 32); //inventory.put(itemButton, gear); fullPanel.add(itemButton); itemButton.addActionListener(new ActionListener() {
	 * @Override public void actionPerformed(ActionEvent e) { System.out.println("Works?"); Equipment item = inventory.get((JButton) e.getSource()); //item.setSelected(); } }); //gear.setButt(itemButton); fullPanel.add(itemButton); } }}); /* for (int counter = 0; counter <=10; counter++) { System.out.println("execute"); Equipment gear = new Equipment(Equipment.Helm); JButton itemButton = new JButton(); itemButton.setMargin(new Insets(1, 1, 1, 1)); itemButton.setBorder(null); itemButton.setContentAreaFilled(false); int x = counter % 4, y = counter / 4; itemButton.setBounds(52 + x * 42, 45 + y * 36, 32, 32); inventory.put(itemButton, gear); fullPanel.add(itemButton); itemButton.addActionListener(new ActionListener() {
	 * @Override public void actionPerformed(ActionEvent e) { Equipment gear = inventory.get((JButton) e.getSource()); } }); gear.setButt(itemButton); fullPanel.add(itemButton); }}});
	 */

	assignPanel.add(assignImg);
	assignPanel.setBounds(assignImg.getBounds());

	invPanel.add(invImg);
	invPanel.setBounds(invImg.getBounds());
	for (JButton butt : buttons) {
	    gearPanel.add(butt);
	    System.out.printf("%d\t%d\t%s\n", butt.getBounds().x, butt.getBounds().y, butt.getParent());
	}
	gearPanel.add(gearImg);

	gearPanel.setBounds(gearImg.getBounds());

	fullPanel.add(assignPanel, BorderLayout.WEST);
	fullPanel.add(gearImg, BorderLayout.CENTER);
	fullPanel.add(invImg, BorderLayout.EAST);

	Container cp = getContentPane();
	cp.setLayout(new BorderLayout());
	System.out.println(fullPanel.getPreferredSize());
	cp.setPreferredSize(fullPanel.getPreferredSize());
	cp.add(fullPanel);
	cp.setBounds(fullPanel.getBounds());
	cp.setSize(fullPanel.getPreferredSize());

	pack();
	setLocationRelativeTo(null);
	setDefaultCloseOperation(3);
	setResizable(false);
	setVisible(true);

    }
}
