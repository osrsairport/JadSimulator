package main;

import java.util.ArrayList;

public abstract class Tick {
    private static ArrayList<Tick> ticks = new ArrayList<Tick>();
    private static ArrayList<Tick> requestedTicks = new ArrayList<Tick>();

    private final int maxTicks;
    private int ticksLeft;

    protected Tick(int ticks) {
	ticksLeft = ticks;
	maxTicks = -1;
    }

    protected Tick(int ticks, boolean repeat) {
	ticksLeft = ticks;
	maxTicks = repeat ? ticks : -1;
    }

    private static boolean add(Tick tick) {
	return ticks.add(tick);
    }

    private static boolean remove(Tick tick) {
	return ticks.remove(tick);
    }

    public static void tick() {
	for (Tick tick : ticks.toArray(new Tick[ticks.size()])) {
	    tick.ticksLeft--;
	    if (tick.ticksLeft == 0) {
		tick.execute();
		if (tick.maxTicks != -1)
		    tick.ticksLeft = tick.maxTicks;
		else
		    remove(tick);
	    }
	}
    }

    public abstract void execute();

    public static boolean requestAdd(Tick tick) {
	return requestedTicks.add(tick);
    }

    protected static void addRequestedTicks() {
	for (Tick tick : requestedTicks.toArray(new Tick[requestedTicks.size()])) {
	    add(tick);
	    requestedTicks.remove(tick);
	}
    }
}