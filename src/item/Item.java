package item;

import javax.swing.JButton;

public interface Item {

    public static final Item Vial = new Item() {

	@Override
	public Item onInteraction() {
	    return this;
	}

	@Override
	public JButton getButt() {
	    return null;
	}
    };

    public Item onInteraction();

    public JButton getButt();
}