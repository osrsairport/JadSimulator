package main;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JToggleButton;

public class Prayer extends Interface {

    public static final Integer NONE = new Integer(0);
    public static final Integer MAGE = new Integer(1);
    public static final Integer RANGE = new Integer(2);

    private final HashMap<Integer, JToggleButton> prayButts = new HashMap<Integer, JToggleButton>();

    private final ButtonGroup prayButtons = new ButtonGroup();

    private final JToggleButton disableButton = new JToggleButton();

    private int selectedPrayer = Integer.valueOf(NONE);

    public Prayer(Rectangle buttonLocation, String name, final Main main) {
	super(buttonLocation, name, main);
	prayButtons.add(disableButton);

	ClassLoader cl = getClass().getClassLoader();

	final JLabel[] tooltips = new JLabel[2];
	for (int i = 0; i < tooltips.length; i++) {
	    JLabel label = new JLabel(new ImageIcon(cl.getResource("resources/" + String.valueOf(i + 1) + "hover.png")));
	    label.setVisible(false);
	    tooltips[i] = label;
	    label.setBounds(43, 196, 182 + i * 2, 43);
	    panel.add(label);
	}

	ActionListener prayListener = new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		final Player player = Player.getPlayer();
		final int prayer = Integer.parseInt(e.getActionCommand());

		JToggleButton myButt = (JToggleButton) e.getSource();
		final Sound sound = Sound.getSound();
		if (selectedPrayer == Integer.valueOf(prayer)) {
		    if (player.getPrayPoints() <= 0) {
			turnOffPrayer();
			player.setActivatedPrayer(NONE);
			myButt.setSelected(false);
		    } else {
			disableButton.setSelected(true);
			selectedPrayer = NONE;
			player.setActivatedPrayer(NONE);
			Tick.requestAdd(new Tick(1) {
			    @Override
			    public void execute() {
				sound.playClip(String.valueOf(3));
			    }
			});
		    }
		} else {
		    if (player.getPrayPoints() <= 0) {
			turnOffPrayer();
			player.setActivatedPrayer(NONE);
			myButt.setSelected(false);
		    } else {
			Tick.requestAdd(new Tick(1) {
			    @Override
			    public void execute() {
				sound.playClip(String.valueOf(prayer));
			    }
			});
			myButt.setSelected(true);
			player.setActivatedPrayer(Integer.valueOf(prayer));
			selectedPrayer = Integer.valueOf(prayer);
		    }
		}
	    }
	};

	MouseListener hoverListener = new MouseAdapter() {
	    @Override
	    public void mouseEntered(MouseEvent e) {
		super.mouseEntered(e);
		JToggleButton myButt = (JToggleButton) e.getComponent();
		JLabel label = tooltips[Integer.parseInt(myButt.getActionCommand()) - 1];
		label.setVisible(true);
		label.requestFocusInWindow();
	    }

	    @Override
	    public void mouseExited(MouseEvent e) {
		super.mouseExited(e);
		JToggleButton myButt = (JToggleButton) e.getComponent();
		JLabel label = tooltips[Integer.parseInt(myButt.getActionCommand()) - 1];
		label.setVisible(false);
		label.requestFocusInWindow();
	    }
	};

	for (int i = 1; i < 3; i++) {
	    JToggleButton pray = new JToggleButton();
	    pray.setIcon(new ImageIcon(cl.getResource("resources/prayer" + i + false + ".png")));
	    pray.setSelectedIcon(new ImageIcon(cl.getResource("resources/prayer" + i + true + ".png")));
	    pray.setOpaque(true);
	    pray.setBorderPainted(false);
	    pray.setBounds((78 - 37) + i * 37, 157, 34, 34);
	    pray.setActionCommand(Integer.toString(i));
	    pray.addActionListener(prayListener);
	    pray.setActionCommand(String.valueOf(i));
	    pray.addMouseListener(hoverListener);
	    prayButtons.add(pray);
	    panel.add(pray);
	    prayButts.put(i, pray);

	}
	// TODO add custom label to prayer interface. 'Pray +" / "+ maxpray'
    }

    public void turnOffPrayer() {
	disableButton.setSelected(true);
	selectedPrayer = NONE;
	Tick.requestAdd(new Tick(1) {
	    @Override
	    public void execute() {
		Sound.getSound().playClip(String.valueOf(0));
	    }
	});
    }

    /*
     * public void setPrayerActivated(int prayer) { JButton myButt = prayImgs.get(Integer.valueOf(prayer)); if(prayButtons.isSelected(arg0)) prayButtons.setSelected(arg0, true) System.out.println(prayer + " was activated m9"); System.out.println(""); }
     */

}
