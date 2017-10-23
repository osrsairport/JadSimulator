package item;

public abstract class Consumable implements Item {

    protected Consumable eatenItem;
    protected int servings;
    private boolean consumable;

    public int getServings() {
	return servings;
    }

    public Consumable getEatenItem() {
	return eatenItem;
    }

    public void setEatenItem(Consumable item) {
	eatenItem = item;
    }

    public void setConsumable() {
	consumable = true;
    }

    public Item onInteraction() {
	System.out.println("11");
	/*if (!consumable)
	    return null;*/
	System.out.println("111");
	if (servings == 0)
	    return getStorageItem();
	System.out.println("1111");
	servings--;
	System.out.println("Servs: "+servings);
	eatenItem = null;
	return this;
    }
    
    protected abstract Item getStorageItem();

}