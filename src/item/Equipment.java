package item;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import main.Main;

public class Equipment {
    public static final HashMap<Equipment, String> helms = new HashMap<Equipment, String>();
    public static final HashMap<Equipment, String> capes = new HashMap<Equipment, String>();
    public static final HashMap<Equipment, String> amulets = new HashMap<Equipment, String>();
    public static final HashMap<Equipment, String> projectiles = new HashMap<Equipment, String>();
    public static final HashMap<Equipment, String> weapons = new HashMap<Equipment, String>();
    public static final HashMap<Equipment, String> platebodies = new HashMap<Equipment, String>();
    public static final HashMap<Equipment, String> shields = new HashMap<Equipment, String>();
    public static final HashMap<Equipment, String> platelegs = new HashMap<Equipment, String>();
    public static final HashMap<Equipment, String> gloves = new HashMap<Equipment, String>();
    public static final HashMap<Equipment, String> boots = new HashMap<Equipment, String>();
    public static final HashMap<Equipment, String> rings = new HashMap<Equipment, String>();

    public static final HashMap<Equipment.Type, HashMap<? extends Equipment, ? extends String>> types = new HashMap<Equipment.Type, HashMap<? extends Equipment, ? extends String>>();

    public static final HashMap<Equipment.Type, JButton[]> buttons = new HashMap<Equipment.Type, JButton[]>();

    // TODO better not do it this way
    static {

	helms.put(new Equipment(Type.Helm), "InitiateHelm");
	helms.put(new Equipment(Type.Helm), "ProselyteHelm");
	helms.put(new Equipment(Type.Helm), "ArchersHelm");
	helms.put(new Equipment(Type.Helm), "BerserkerHelm");
	helms.put(new Equipment(Type.Helm), "NeitiznotHelm");
	helms.put(new Equipment(Type.Helm), "VeracsHelm");
	helms.put(new Equipment(Type.Helm), "ArmadylHelm");
	helms.put(new Equipment(Type.Helm), "SerpentineHelm");
	helms.put(new Equipment(Type.Helm), "MysticHelm");
	helms.put(new Equipment(Type.Helm), "VoidMelee");
	helms.put(new Equipment(Type.Helm), "VoidRange");
	helms.put(new Equipment(Type.Helm), "VoidMage");
	helms.put(new Equipment(Type.Helm), "AhrimHood");

	capes.put(new Equipment(Type.Cape), "Cape");
	capes.put(new Equipment(Type.Cape), "AvasAttractor");
	capes.put(new Equipment(Type.Cape), "AvasAccumulator");
	capes.put(new Equipment(Type.Cape), "GodCloak");
	capes.put(new Equipment(Type.Cape), "MageCloak");
	capes.put(new Equipment(Type.Cape), "ArdougneCloak1");
	capes.put(new Equipment(Type.Cape), "ArdougneCloak2");
	capes.put(new Equipment(Type.Cape), "ArdougneCloak3");
	capes.put(new Equipment(Type.Cape), "ArdougneCloak4");
	capes.put(new Equipment(Type.Cape), "FireCape");

	amulets.put(new Equipment(Type.Amulet), "AmuletOfAccuracy");
	amulets.put(new Equipment(Type.Amulet), "AmuletOfPower");
	amulets.put(new Equipment(Type.Amulet), "AmuletOfGlory");
	amulets.put(new Equipment(Type.Amulet), "AmuletOfTheDamned");
	amulets.put(new Equipment(Type.Amulet), "AmuletOfFury");

	projectiles.put(new Equipment(Type.Projectile), "RuneArrow");
	projectiles.put(new Equipment(Type.Projectile), "BroadBolts");
	projectiles.put(new Equipment(Type.Projectile), "MithrilBolts");
	projectiles.put(new Equipment(Type.Projectile), "AdamantBolts");
	projectiles.put(new Equipment(Type.Projectile), "RuniteBolts");
	projectiles.put(new Equipment(Type.Projectile), "DiamondBolts");

	weapons.put(new Equipment(Type.Weapon), "MagicShortbow");
	weapons.put(new Equipment(Type.Weapon), "AdamantCbow");
	weapons.put(new Equipment(Type.Weapon), "RuneCbow");
	weapons.put(new Equipment(Type.Weapon), "CrystalBow");
	weapons.put(new Equipment(Type.Weapon), "Blowpipe");
	weapons.put(new Equipment(Type.Weapon), "DragonScimatar");
	weapons.put(new Equipment(Type.Weapon), "Whip");
	weapons.put(new Equipment(Type.Weapon), "Trident");
	weapons.put(new Equipment(Type.Weapon), "SwampTrident");

	platebodies.put(new Equipment(Type.Platebody), "InitiatePlatebody");
	platebodies.put(new Equipment(Type.Platebody), "ProselytePlatebody");
	platebodies.put(new Equipment(Type.Platebody), "RunePlatebody");
	platebodies.put(new Equipment(Type.Platebody), "BlueDBody");
	platebodies.put(new Equipment(Type.Platebody), "RedDBody");
	platebodies.put(new Equipment(Type.Platebody), "BlackDBody");
	platebodies.put(new Equipment(Type.Platebody), "GodDBody");
	platebodies.put(new Equipment(Type.Platebody), "MysticTop");
	platebodies.put(new Equipment(Type.Platebody), "VoidTop");
	platebodies.put(new Equipment(Type.Platebody), "AhrimTop");
	platebodies.put(new Equipment(Type.Platebody), "KarilTop");
	platebodies.put(new Equipment(Type.Platebody), "ArmadylTop");

	shields.put(new Equipment(Type.Shield), "BookBandos");
	shields.put(new Equipment(Type.Shield), "BookAncient");
	shields.put(new Equipment(Type.Shield), "BookArmadyl");
	shields.put(new Equipment(Type.Shield), "BookZamorak");
	shields.put(new Equipment(Type.Shield), "BookSaradomin");
	shields.put(new Equipment(Type.Shield), "BookGuthix");
	shields.put(new Equipment(Type.Shield), "RuneKiteshield");
	shields.put(new Equipment(Type.Shield), "GraniteShield");
	shields.put(new Equipment(Type.Shield), "SpiritShield");
	shields.put(new Equipment(Type.Shield), "CrystalShield");
	shields.put(new Equipment(Type.Shield), "DragonFireShield");

	platelegs.put(new Equipment(Type.Platelegs), "InitiatePlatelegs");
	platelegs.put(new Equipment(Type.Platelegs), "ProselytePlatelegs");
	platelegs.put(new Equipment(Type.Platelegs), "RunePlatelegs");
	platelegs.put(new Equipment(Type.Platelegs), "BlueDChaps");
	platelegs.put(new Equipment(Type.Platelegs), "RedDChaps");
	platelegs.put(new Equipment(Type.Platelegs), "BlackDChaps");
	platelegs.put(new Equipment(Type.Platelegs), "GodDChaps");
	platelegs.put(new Equipment(Type.Platelegs), "MysticBottom");
	platelegs.put(new Equipment(Type.Platelegs), "VoidBottom");
	platelegs.put(new Equipment(Type.Platelegs), "AhrimBottom");
	platelegs.put(new Equipment(Type.Platelegs), "VeracSkirt");
	platelegs.put(new Equipment(Type.Platelegs), "KarilSkirt");
	platelegs.put(new Equipment(Type.Platelegs), "ArmadylSkirt");

	gloves.put(new Equipment(Type.Gloves), "MysticGloves");
	gloves.put(new Equipment(Type.Gloves), "CombatBracelet");
	gloves.put(new Equipment(Type.Gloves), "RegenBracelet");
	gloves.put(new Equipment(Type.Gloves), "VoidGloves");
	gloves.put(new Equipment(Type.Gloves), "MithrilGloves");
	gloves.put(new Equipment(Type.Gloves), "AdamantGloves");
	gloves.put(new Equipment(Type.Gloves), "RuneGloves");
	gloves.put(new Equipment(Type.Gloves), "DragonGloves");
	gloves.put(new Equipment(Type.Gloves), "BarrowsGloves");

	boots.put(new Equipment(Type.Boots), "MysticBoots");
	boots.put(new Equipment(Type.Boots), "WizardBoots");
	boots.put(new Equipment(Type.Boots), "RangerBoots");
	boots.put(new Equipment(Type.Boots), "SnakeSkinBoots");
	boots.put(new Equipment(Type.Boots), "DragonBoots");
	boots.put(new Equipment(Type.Boots), "BandosBoots");

	rings.put(new Equipment(Type.Ring), "RingOfRecoil");
	rings.put(new Equipment(Type.Ring), "BeaconRing");
	rings.put(new Equipment(Type.Ring), "WarriorRing");
	rings.put(new Equipment(Type.Ring), "SeersRing");
	rings.put(new Equipment(Type.Ring), "ArchersRing");
	rings.put(new Equipment(Type.Ring), "BerserkerRing");
	rings.put(new Equipment(Type.Ring), "RingOfTheGods");
	rings.put(new Equipment(Type.Ring), "TyrannicalRing");
	rings.put(new Equipment(Type.Ring), "TreasonousRing");

	types.put(Type.Cape, capes);
	types.put(Type.Amulet, amulets);
	types.put(Type.Boots, boots);
	types.put(Type.Gloves, gloves);
	types.put(Type.Helm, helms);
	types.put(Type.Platebody, platebodies);
	types.put(Type.Platelegs, platelegs);
	types.put(Type.Projectile, projectiles);
	types.put(Type.Ring, rings);
	types.put(Type.Shield, shields);
	types.put(Type.Weapon, weapons);
    }

    public static void doThis() {
	a: for (Type type : Type.values()) {
	    HashMap<? extends Equipment, ? extends String> map = types.get(type);

	    Equipment[] equipments = map.keySet().toArray(new Equipment[map.size()]);
	    JButton[] buttons = new JButton[map.size()];
	    final ClassLoader cl = Equipment.class.getClassLoader();
	    for (int i = 0; i < equipments.length; i++) {
		Equipment equipment = equipments[i];
		String name = map.get(equipment);
		int x1 = i % 4;
		int y1 = i / 4;
		URL iconPath = cl.getResource("resources/gear/" + type.name() + "/" + name + ".png");
		if (iconPath == null)
		    break a;
		JButton button = new JButton(new ImageIcon(iconPath));
		// TODO don't add one button for each item
		button.setContentAreaFilled(false);
		button.setOpaque(false);
		button.setFocusPainted(false);
		button.setBounds(43 + x1 * 42, 45 + y1 * 36, 32, 32);
		button.setBorderPainted(false);
		button.setVisible(false);
		button.addActionListener(new ActionListener() {

		    @Override
		    public void actionPerformed(ActionEvent e) {
			System.out.println("the button was clicked");

		    }
		});
		buttons[i] = button;
		Main.getAssign().addComp(button);
	    }
	    Equipment.buttons.put(type, buttons);
	}
	System.out.println(Main.getAssign().img);
	Main.getAssign().addComp(Main.getAssign().img);
    }

    // Helm//
    /*
     * InitiateHelm(Type.Helm), ProselyteHelm(Type.Helm), ArchersHelm(Type.Helm), BerserkerHelm(Type.Helm), NeitiznotHelm(Type.Helm), VeracsHelm(Type.Helm), ArmadylHelm(Type.Helm), SerpentineHelm(Type.Helm), MysticHelm(Type.Helm), VoidMelee(Type.Helm), VoidRange(Type.Helm), VoidMage(Type.Helm), AhrimHood(Type.Helm), NoHelm(Type.Helm, true), // Cape// Cape(Type.Cape), AvasAttractor(Type.Cape), AvasAccumulator(Type.Cape), GodCloak(Type.Cape), MageCloak(Type.Cape), ArdougneCloak1(Type.Cape), ArdougneCloak2(Type.Cape), ArdougneCloak3(Type.Cape), ArdougneCloak4(Type.Cape), FireCape(Type.Cape), NoCape(Type.Cape, true), // Amulet// AmuletOfAccuracy(Type.Amulet), AmuletOfPower(Type.Amulet), AmuletOfGlory(Type.Amulet), AmuletOfTheDamned(Type.Amulet), AmuletOfFury(Type.Amulet), NoAmulet(Type.Amulet,
     * true), // Projectile// RuneArrow(Type.Projectile), BroadBolts(Type.Projectile), MithrilBolts(Type.Projectile), AdamantBolts(Type.Projectile), RuniteBolts(Type.Projectile), DiamondBolts(Type.Projectile), NoBolts(Type.Projectile, true), // Weapon MagicShortbow(Type.Weapon), AdamantCbow(Type.Weapon), RuneCbow(Type.Weapon), CrystalBow(Type.Weapon), Blowpipe(Type.Weapon), DragonScimatar(Type.Weapon), Whip(Type.Weapon), Trident(Type.Weapon), SwampTrident(Type.Weapon), NoWeapon(Type.Weapon, true), // Platebody InitiatePlatebody(Type.Platebody), ProselytePlatebody(Type.Platebody), RunePlatebody(Type.Platebody), BlueDBody(Type.Platebody), RedDBody(Type.Platebody), BlackDBody(Type.Platebody), GodDBody(Type.Platebody), MysticTop(Type.Platebody), VoidTop(Type.Platebody),
     * AhrimTop(Type.Platebody), KarilTop(Type.Platebody), ArmadylTop(Type.Platebody), NoBody(Type.Platebody, true), // Shield BookBandos(Type.Shield), BookAncient(Type.Shield), BookArmadyl(Type.Shield), BookZamorak(Type.Shield), BookSaradomin(Type.Shield), BookGuthix(Type.Shield), RuneKiteshield(Type.Shield), GraniteShield(Type.Shield), SpiritShield(Type.Shield), CrystalShield(Type.Shield), DragonFireShield(Type.Shield), NoShield(Type.Shield, true), // Platelegs InitiatePlatelegs(Type.Platelegs), ProselytePlatelegs(Type.Platelegs), RunePlatelegs(Type.Platelegs), BlueDChaps(Type.Platelegs), RedDChaps(Type.Platelegs), BlackDChaps(Type.Platelegs), GodDChaps(Type.Platelegs), MysticBottom(Type.Platelegs), VoidBottom(Type.Platelegs), AhrimBottom(Type.Platelegs), VeracSkirt(Type.Platelegs),
     * KarilSkirt(Type.Platelegs), ArmadylSkirt(Type.Platelegs), NoPlatelegs(Type.Platelegs, true), // Gloves MysticGloves(Type.Gloves), CombatBracelet(Type.Gloves), RegenBracelet(Type.Gloves), VoidGloves(Type.Gloves), MithrilGloves(Type.Gloves), AdamantGloves(Type.Gloves), RuneGloves(Type.Gloves), DragonGloves(Type.Gloves), BarrowsGloves(Type.Gloves), NoGloves(Type.Gloves, true), // Boots MysticBoots(Type.Boots), WizardBoots(Type.Boots), RangerBoots(Type.Boots), SnakeSkinBoots(Type.Boots), DragonBoots(Type.Boots), BandosBoots(Type.Boots), NoBoots(Type.Boots, true), // Ring RingOfRecoil(Type.Ring), BeaconRing(Type.Ring), WarriorRing(Type.Ring), SeersRing(Type.Ring), ArchersRing(Type.Ring), BerserkerRing(Type.Ring), RingOfTheGods(Type.Ring), TyrannicalRing(Type.Ring),
     * TreasonousRing(Type.Ring), NoRing(Type.Ring, true) ;
     */
    private boolean end;

    public enum Type {
	Helm(71, 68), Cape(30, 107), Amulet(71, 107), Projectile(112, 107), Weapon(15, 146), Platebody(71, 146), Shield(127, 146), Platelegs(71, 186), Gloves(15, 226), Boots(71, 226), Ring(127, 226);

	private JButton butt;

	private Type(int x, int y) {
	    butt = new JButton();
	    butt.setBounds(x, y, 35, 35);
	    butt.setContentAreaFilled(false);
	    butt.setOpaque(false);
	    butt.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

		    for (Type type : buttons.keySet()) {
			for (JButton butt : buttons.get(type))
			    butt.setVisible(false);
		    }

		    for (JButton butt : buttons.get(Type.this)) {
			butt.setVisible(true);
		    }
		}
	    });
	}

	public JButton getButt() {
	    return butt;
	}
    }

    private Type type;

    private Equipment(Type type) {
	this.type = type;
    }

    private Equipment(Type type, boolean bool) {
	this(type);
	end = bool;
    }

    public Type getType() {
	return type;
    }

    public boolean isEnd() {
	return end;
    }

}