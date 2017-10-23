package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.font.TextLayout;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import static main.Main.*;

public class Jad extends JPanel {
    private static final long serialVersionUID = 2L;

    private int attack = Prayer.NONE;
    private static int i = -6; // FIXME fix this later, fuck static

    private static Jad theJad = new Jad();

    public static Random random = new Random();

    private static Timer animationTimer, countdownTimer;

    private ImageIcon layout = new ImageIcon(getClass().getClassLoader().getResource("resources/layout.png"));

    private final HashMap<String, Image> imgs = new HashMap<String, Image>();

    private boolean ready = false;

    private long ms;

    public Jad() {
	super(null);
	setBounds(0, 0, layout.getIconWidth(), layout.getIconHeight());
	setBackground(Color.blue);
	setFont(Main.getRSFont());
    }

    public final void attack(final Player player) {
	if (player.isDead())
	    Main.getMain().getWindowListeners()[0].windowClosed(new WindowEvent(Main.getMain(), WindowEvent.WINDOW_CLOSED));
	if (animationTimer.isRunning())
	    assert false : "AnimationTimer shouldn't be running at this point";
	attack = 1 + random.nextInt(2);
	i = 0;
	animationTimer.start();
	Sound.getSound().playClip(attackStrings.get(attack));
	ms = System.currentTimeMillis();
	Tick.requestAdd(new Tick(7 - (attack * 2)) {// 7 - 2 = 5 or 7 - 4 = 3
	    public void execute() {
		System.out.println(((double) (ms - System.currentTimeMillis())) / 600);
		damage(player);
	    }
	});

    }

    public int getAttack() {
	return attack;
    }

    public static final Jad getJad() {
	return theJad;
    }

    public void damage(Player player) {
	int damage = player.getActivatedPrayer() == attack ? 0 : jadRNG.nextInt(97);

	if (damage >= player.getHp()) {
	    damage = player.getHp();
	}

	System.out.println(damage);
	player.damage(damage);

    }

    @Override
    public void paintComponent(Graphics g) {
	super.paintComponent(g);
	if (countdownTimer.isRunning())
	    if (i == 0) {
		countdownTimer.stop();
		Tick.requestAdd(new Tick(1) {
		    @Override
		    public void execute() {
			attack(Player.getPlayer());
		    }
		});
	    } else
		i++;
	else if (animationTimer.isRunning())
	    if (i >= 145) {
		animationTimer.stop();
		i = 0;
		attack = Prayer.NONE;
		Tick.requestAdd(new Tick(1) {
		    @Override
		    public void execute() {
			attack(Player.getPlayer());
		    }
		});
	    } else
		i++;

	Graphics2D g2 = (Graphics2D) g;

	g2.drawImage(layout.getImage(), 0, 0, null);

	String attackString = attackStrings.get(attack);
	String string = "resources/animation/" + attackString + (attack != Prayer.NONE ? "/" : "") + "jad" + attackString + " (" + i + ").png";

	Image img = null;
	ImageIcon icon = null;

	a: if (!imgs.containsKey(string)) {
	    URL imgURL = getClass().getClassLoader().getResource(string);
	    if (imgURL == null) {
		System.out.println("can't find image " + string);
		break a;
	    }
	    icon = new ImageIcon(imgURL);

	    img = icon.getImage();
	    imgs.put(string, img);
	} else
	    img = imgs.get(string);

	Player player = Player.getPlayer();
	drawString(g2, String.valueOf(player.getHp()), 177, 276);
	drawString(g2, String.valueOf(player.getPrayPoints()), 193, 315);
	drawString(g2, ("VERSION: BETA"), 5, 330);

	assert img != null : "img shouldn't be null at this point";
	g2.drawImage(img, 7, 8, null);
	// TODO move attack cooldown from pics to ticktimer

    }

    private void drawString(Graphics2D g, String text, int x, int y) {
	TextLayout textLayout = new TextLayout(text, getFont(), g.getFontRenderContext());
	g.setPaint(new Color(0, 0, 0));
	textLayout.draw(g, x + 1, y + 1);
	g.setPaint(Color.GREEN);
	textLayout.draw(g, x, y);
    }

    public static void startCountdown() {
	countdownTimer.start();
    }

    public boolean isReady() {
	return ready;
    }

    static {

	animationTimer = new Timer(33, new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		theJad.repaint();
	    }
	});

	countdownTimer = new Timer(500, new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		theJad.repaint();
	    }

	});

    }

}