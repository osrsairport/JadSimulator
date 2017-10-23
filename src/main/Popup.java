package main;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Popup {

    public Popup() {
	createDialog("Credits");
    }

    private void createDialog(String title) {
	JDialog dialog = new JDialog();
	dialog.setTitle(title);
	dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

	JPanel panel = new JPanel();

	JLabel credits = new JLabel("<html><div style=\"text-align: center;\">Credits to:<br />Airport<br />DivineCake<");
	panel.add(credits);
	dialog.add(panel);
	dialog.pack();
	dialog.setLocationRelativeTo(null);
	dialog.setResizable(false);
	dialog.setVisible(true);
    }
}