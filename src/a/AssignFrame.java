package a;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.Main;
import main.Player;

public class AssignFrame extends JFrame {
    private static final long serialVersionUID = 3L;

    private ActionListener listener;

    public AssignFrame(final Main main) {

	setTitle("Settings");

	final JTextField assignHpLevel = new JTextField("99", 2), assignPrayerLevel = new JTextField("75", 2), assignSaraAmount = new JTextField("2", 3), assignSuperAmount = new JTextField("3", 3), assignMSField = new JTextField("30", 3);
	final JLabel assignSara = new JLabel("Saradomin Brews:"), assignSuper = new JLabel("Super Restores:"), assignPrayer = new JLabel("Prayer Level:"), assignHp = new JLabel("Hitpoints Level:"), assignMSLabel = new JLabel("Assign MS");

	JButton startButton = new JButton("Select gear");

	listener = new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		System.out.println("actionPerformed");

		int saraAmount = Integer.parseInt(assignSaraAmount.getText());
		int superAmount = Integer.parseInt(assignSuperAmount.getText());
		int hpLvl = Integer.parseInt(assignHpLevel.getText());
		int prayLvl = Integer.parseInt(assignPrayerLevel.getText());
		int ping = Integer.parseInt(assignMSField.getText());
		if (/* main.startGame(saraAmount, superAmount) */true) {
		    setVisible(false);

		}
	    }

	};

	startButton.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		new AssignGear(this);
		setVisible(false);
	    }

	});

	JPanel panel = new JPanel();
	panel.add(assignSara);
	panel.add(assignSaraAmount);
	panel.add(assignSuper);
	panel.add(assignSuperAmount);
	panel.add(assignHp);
	panel.add(assignHpLevel);
	panel.add(assignPrayer);
	panel.add(assignPrayerLevel);
	panel.add(assignMSLabel);
	panel.add(assignMSField);
	panel.add(startButton);
	add(panel);
	pack();
	setLocationRelativeTo(null);
	setDefaultCloseOperation(3);
	setResizable(false);
	setVisible(true);
    }
}