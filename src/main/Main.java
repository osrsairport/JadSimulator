package main;

import item.Equipment;
import item.Item;
import item.Potion;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Random;

import javax.sound.sampled.Clip;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class Main extends JFrame {

    private Point initialClick;

    private static final long serialVersionUID = 1L;

    private int prayTick = 0;

    public static Random jadRNG = new Random();

    private final HashMap<JButton, Item> inventory /* (items) */= new HashMap<JButton, Item>();
    private final Interface inv;

    private TickListener tickTimerListener = new TickListener();
    public final Timer tickTimer = new Timer(600, tickTimerListener);

    private final JPanel cardPanel = new JPanel(new CardLayout());

    public static final HashMap<Integer, String> attackStrings = new HashMap<Integer, String>();
    static {
	attackStrings.put(Prayer.MAGE, "Mage");
	attackStrings.put(Prayer.RANGE, "Range");
	attackStrings.put(Prayer.NONE, "");

	Font font = null;
	InputStream stream = null;
	stream = Main.class.getClassLoader().getResourceAsStream("resources/font.ttf");

	if (stream != null)
	    try {
		font = Font.createFont(Font.TRUETYPE_FONT, stream);
		// System.out.println(font);
	    } catch (IOException | FontFormatException e) {
		System.err.println("Can't access font file, or font file is wrong format");
	    }
	else
	    System.out.println("font stream was null");
	try {
	    if (stream != null)
		stream.close();
	} catch (IOException e) {
	    e.printStackTrace();
	}

	if (font == null)
	    font = new Font("Arial", Font.PLAIN, 15);
	else
	    font = font.deriveFont(Font.PLAIN, 15);
	Main.font = font;
    }

    private static Main main;
    private static Assign assign;

    private int ping = 0;

    private static Font font;

    public static final void main(String[] args) {
	SwingUtilities.invokeLater(new Runnable() {
	    @Override
	    public void run() {
		main = new Main();
		assign = new Assign();
		main.setSize(main.getContentPane().getPreferredSize()); // TODO don't do this
		Equipment.doThis();

	    }
	});

    }

    public static void contentsOf(Container container, boolean recursion, boolean size, Color... c) {
	for (Component comp : container.getComponents()) {
	    if (recursion && comp instanceof Container) {
		contentsOf((Container) comp, true, size, c);
	    }
	    if (size) {
		System.out.println(comp + " is " + comp.getPreferredSize());
	    } else if (c.length == 0)
		System.out.println(comp + " located on \n" + container + "\n\n");
	    else
		comp.setBackground(c[0]);
	}
    }

    public Main() {

	ClassLoader cl = getClass().getClassLoader();

	setLayout(new GridLayout(1, 1, 0, 0));
	setDefaultCloseOperation(3);
	setUndecorated(true);

	HashMap<Interface, String> interfaces = Interface.getAllInterfaces();
	inv = new Interface(new Rectangle(116, 0, 32, 35), Interface.INVENTORY, this);
	final Prayer pray = new Prayer(new Rectangle(182, 0, 32, 35), Interface.PRAYER, this);
	final Interface logout = new Interface(new Rectangle(116, 298, 32, 35), Interface.LOGOUT, this);
	interfaces.put(inv, Interface.INVENTORY);
	interfaces.put(pray, Interface.PRAYER);
	interfaces.put(logout, Interface.LOGOUT);

	JButton gameExit = new JButton();
	gameExit.setRolloverEnabled(true);
	gameExit.setOpaque(true);
	gameExit.setBorderPainted(false);
	gameExit.setIcon(new ImageIcon(cl.getResource("resources/logoutButton.png")));
	gameExit.setRolloverIcon(new ImageIcon(cl.getResource("resources/logoutHover.png")));
	gameExit.setBounds(61, 192, 139, 31);
	gameExit.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		System.exit(0);
	    }
	});
	logout.getPanel().add(gameExit);
	JButton creditPop = new JButton();
	creditPop.setRolloverEnabled(true);
	creditPop.setOpaque(true);
	creditPop.setBorderPainted(false);
	creditPop.setIcon(new ImageIcon(cl.getResource("resources/creditButton.png")));
	creditPop.setRolloverIcon(new ImageIcon(cl.getResource("resources/creditHover.png")));
	creditPop.setBounds(101, 235, 62, 31);
	creditPop.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		new Popup();// fuck this it has tons of compiler errors
	    }
	});
	logout.getPanel().add(creditPop);

	Container cp = getContentPane();
	Jad jad = Jad.getJad();
	cp.setLayout(new GridLayout(1, 2, 0, 0));
	cp.add(jad);

	Container m9 = new Container();
	Icon icon = inv.getImg().getIcon();
	m9.setBounds(jad.getWidth(), 0, icon.getIconWidth(), icon.getIconHeight());
	// System.out.println("m9 is ".concat(String.valueOf(m9.getPreferredSize())));

	cardPanel.setBounds(new Rectangle(new Point(0, 0), m9.getPreferredSize()));

	addWindowListener(new WindowAdapter() {
	    @Override
	    public void windowClosed(WindowEvent e) {
		super.windowClosed(e);
		for (Clip clip : Sound.getSound().getAllClips())
		    clip.close();
		dispose();
		System.exit(0);
	    }
	});

	m9.add(cardPanel);
	cp.add(m9);
	cardPanel.setLayout(new CardLayout());

	for (Interface if1 : interfaces.keySet()) {
	    cardPanel.add(if1.getPanel(), interfaces.get(if1));
	    m9.add(if1.getButton());
	}
	getRootPane().setPreferredSize(cp.getPreferredSize());
	pack();

	cp = getContentPane();
	cp.setLayout(new BorderLayout());
	Dimension d = cp.getSize();
	cp.setPreferredSize(new Dimension(d.width - 3, d.height)); // TODO don't do this
	getRootPane().setPreferredSize(cp.getSize());
	setLocationRelativeTo(null);
	// System.out.println(cp);

	setResizable(false);

	// TODO more to do here
    }

    public boolean startGame(int[] values) {
	if ((values[0] + values[1]) > 28 || values[2] > 99 || values[3] > 99 || values[4] > 1800)
	    return false;

	Player.setLvls(values[2], values[3]);
	setPing(values[4]);

	createPots(values[0], values[1]);
	for (Interface inf : Interface.getAllInterfaces().keySet())
	    inf.getPanel().add(inf.getImg());

	revalidate();

	final Player player = Player.getPlayer();

	Tick.requestAdd(new Tick(100, true) {
	    @Override
	    public void execute() {
		if (player.getHp() > 99) {
		    player.damage(1);
		    System.out.println("Stat d.Timer");
		}
	    }
	});
	Tick.requestAdd(new Tick(1, true) {

	    @Override
	    public void execute() {
		if (player.getActivatedPrayer() != Prayer.NONE)
		    prayTick++;
		if (prayTick == 3) {// TODO add support for more than 1 prayer, move this to tick
		    prayTick = 0;
		    player.drainPrayer(1);

		    if (player.getPrayPoints() <= 0) {
			Sound.getSound().playClip(String.valueOf(0));
			player.drainPrayer(-1);
		    }
		}
	    }

	});
	tickTimer.start();
	Jad.startCountdown();

	addMouseListener(new MouseAdapter() {
	    public void mousePressed(MouseEvent e) {
		initialClick = e.getPoint();
		getComponentAt(initialClick);
		System.out.println(initialClick);
		System.out.println(getComponentAt(initialClick));
		// this tells me its jrootpane but i only want it to work if u click the stone rocky layout shit
		// and where i clicked
	    }
	});

	addMouseMotionListener(new MouseMotionAdapter() {
	    @Override
	    public void mouseDragged(MouseEvent e) {
		// before u noob ask this gets the location of the window
		int thisX = getLocation().x;
		int thisY = getLocation().y;
		// checks how much the mouse moved u dumb ass cunt incase u dont get it
		int xMoved = (thisX + e.getX()) - (thisX + initialClick.x);
		int yMoved = (thisY + e.getY()) - (thisY + initialClick.y);
		// finally moves the god damn simulator
		int X = thisX + xMoved;
		int Y = thisY + yMoved;

		setLocation(X, Y);
	    }
	});

	setAlwaysOnTop(true);
	setVisible(true);

	CardLayout layout = (CardLayout) getCardPanel().getLayout();

	layout.show(cardPanel, Interface.INVENTORY);
	// TODO more to do here

	return true;
    }

    public void createPots(int saras, int supers) { // TODO add support for other items than pots

	for (int counter = 0; counter < (saras + supers); counter++) {

	    Potion potion = (counter < saras ? new Potion.Sarabrew() : new Potion.Superrestore());

	    JButton potButton = new JButton(new ImageIcon(getClass().getClassLoader().getResource("resources/" + potion + ".png")));
	    potButton.setMargin(new Insets(1, 1, 1, 1));
	    potButton.setBorder(null);
	    potButton.setContentAreaFilled(false);
	    int x = counter % 4, y = counter / 4;
	    potButton.setBounds(52 + x * 42, 45 + y * 36, potButton.getIcon().getIconWidth(), potButton.getIcon().getIconHeight());

	    inventory.put(potButton, potion);
	    potButton.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
		    final Item item = inventory.get((JButton) e.getSource());
		    if (item instanceof Potion)
			Tick.requestAdd(new Tick(1) {
			    @Override
			    public void execute() {
				((Potion) item).onInteraction();
			    }
			});

		}
	    });
	    potion.setButt(potButton);
	    inv.getPanel().add(potButton);
	}
    }

    public JPanel getCardPanel() {
	return cardPanel;
    }

    public static void invis(JButton button) {
	button.setOpaque(false);
	button.setBorderPainted(false);
	button.setContentAreaFilled(false);
    }

    public static Main getMain() {
	return main;
    }

    public static Assign getAssign() {
	return assign;
    }

    public static Font getRSFont() {
	return font;
    }

    public int getPing() {
	return ping;
    }

    public void setPing(int ping) {
	this.ping = ping;
    }

    public class TickListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {

	    final Player player = Player.getPlayer();

	    Tick.addRequestedTicks();
	    Tick.tick();

	    if (player.isDead())
		return;

	    /*
	     * aaa: if (drinkTick == 0) { Item item = Item.getDrunkItem(); if (item == null) break aaa; player.eat(item); item.drink(); item.getButt().setIcon(new ImageIcon(getClass().getClassLoader().getResource("resources/" + item.getType().name() + (item.getType() != Item.Type.Vial ? (" (" + item.getDoses() + ")") : "") + ".png"))); drinkTick = 3; } else { drinkTick--; }
	     */

	}
    }

}