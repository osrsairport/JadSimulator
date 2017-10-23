package item;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import main.Player;
import main.Tick;

public abstract class Potion extends Consumable {

    private JButton butt;

    private Potion() {
	servings = 4;
    }

    public static final class Sarabrew extends Potion {
	@Override
	public void onDrink() {
	    Player player = Player.getPlayer();
	    player.heal((int) (2 + Math.floor(player.getHpLvl() * 0.15f)));
	}

    }

    public static final class Superrestore extends Potion {
	@Override
	public void onDrink() {
	    Player player = Player.getPlayer();
	    player.restorePray((int) (8 + Math.floor(player.getHpLvl() / 4)));
	}

    }

    @Override
    public Item onInteraction() {
	final Item item = super.onInteraction();
	if (!(item == Item.Vial || item == null)) {
	    Tick.requestAdd(new Tick(1) {
		public void execute() {
		    onDrink();
		    item.getButt().setIcon(new ImageIcon(getClass().getClassLoader().getResource(("resources/" +  item.toString() + ".png"))));
		}
	    });

	}
	return item;
    }

    @Override
    public String toString() {
	return getClass().getSimpleName() + " (" + servings + ")";

    }

    @Override
    protected Item getStorageItem() {
	return Item.Vial;
    }

    public abstract void onDrink();

    public void setButt(JButton potButton) {
	butt = potButton;
    }

    @Override
    public JButton getButt() {
	return butt;
    }
}
