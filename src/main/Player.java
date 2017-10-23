package main;

import item.Consumable;
import item.Equipment;

public class Player {
    private final int prayLvl;
    private final int hpLvl;
    private int hp;
    private int prayPoints;

    private Equipment cape;
    private Equipment helm;
    private Equipment projectile;
    private Equipment platebody;
    private Equipment platelegs;
    private Equipment weapon;
    private Equipment shield;
    private Equipment boots;
    private Equipment amulet;
    private Equipment ring;

    private Integer activatedPrayer = Prayer.NONE;
    private boolean dead = false;

    private static Player thePlayer;

    private Player(int hp, int pray) {
	hpLvl = hp;
	prayLvl = pray;
	this.hp = getHpLvl();
	this.prayPoints = getPrayLvl();
    }

    public static void setLvls(int hp, int pray) {
	thePlayer = new Player(hp, pray);
    }

    public int getHp() {
	return hp;
    }

    public int getPrayPoints() {
	return prayPoints;
    }

    public Equipment getAmulet() {
	return amulet;
    }

    public Equipment getBoots() {
	return boots;
    }

    public Equipment getHelm() {
	return helm;
    }

    public Equipment getCape() {
	return cape;
    }

    public Equipment getPlatebody() {
	return platebody;
    }

    public Equipment getPlatelegs() {
	return platelegs;
    }

    public Equipment getProjectile() {
	return projectile;
    }

    public Equipment getRing() {
	return ring;
    }

    public Equipment getShield() {
	return shield;
    }

    public Equipment getWeapon() {
	return weapon;
    }

    public void setAmulet(Equipment amulet) {
	this.amulet = amulet;
    }

    public void setBoots(Equipment boots) {
	this.boots = boots;
    }

    public void setCape(Equipment cape) {
	this.cape = cape;
    }

    public void setHelm(Equipment helm) {
	this.helm = helm;
    }

    public void setPlatebody(Equipment platebody) {
	this.platebody = platebody;
    }

    public void setPlatelegs(Equipment platelegs) {
	this.platelegs = platelegs;
    }

    public void setProjectile(Equipment projectile) {
	this.projectile = projectile;
    }

    public void setRing(Equipment ring) {
	this.ring = ring;
    }

    public void setShield(Equipment shield) {
	this.shield = shield;
    }

    public void setWeapon(Equipment weapon) {
	this.weapon = weapon;
    }

    public Integer getActivatedPrayer() {
	return activatedPrayer;
    }

    public void setActivatedPrayer(Integer activatedPrayer) {
	this.activatedPrayer = activatedPrayer;
    }

    public void damage(int i) {
	hp -= i;
	if (hp <= 0) {
	    System.out.println("you died m9");
	    dead = true;
	    hp = 0;
	}
    }

    public void eat(Consumable item) {
	/*
	 * switch (item.getType()) { case Sarabrew: break; case Superrestore: prayPoints += 8 + (maxPray / 4); prayPoints = prayPoints > maxPray ? maxPray : prayPoints; break; case Vial: assert false : "can't eat a vial m9, and item shouldn't be a vial at this point"; // Kappa4000 break; default: assert false : "item of type which is not defined, was " + item.getType(); break; } } }
	 */
    }

    public void drainPrayer(int i) {
	if (i != -1)
	    prayPoints -= i;
	else {
	    prayPoints = 0;
	    activatedPrayer = Prayer.NONE;
	}
    }

    public boolean isDead() {
	return dead;
    }

    public static Player getPlayer() {
	return thePlayer;
    }

    public int getHpLvl() {
	return hpLvl;
    }

    public int getPrayLvl() {
	return prayLvl;
    }

    public void heal(int amt) {
	hp = hp > hpLvl ? hpLvl : hp + amt;
    }

    public void restorePray(int amt) {
	int i = prayPoints + amt;
	prayPoints = i > prayLvl ? prayLvl : i;
    }
}