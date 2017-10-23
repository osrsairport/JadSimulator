package main;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import java.io.IOException;
import java.util.HashMap;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {

    private final HashMap<String, Clip> clips = new HashMap<String, Clip>();
    private static final Sound theSound = new Sound();

    public Sound() {

	ClassLoader cl = getClass().getClassLoader();

	Clip clip = null;
	try {
	    for (int i = 1; i <= 2; i++) {
		final String attack = Main.attackStrings.get(i);
		clip = AudioSystem.getClip();

		if (clip == null)
		    throw new LineUnavailableException();
		clip.open(AudioSystem.getAudioInputStream(cl.getResource("resources/jad" + attack + ".wav")));
		clips.put(attack, clip);
	    }
	    for (int i = 0; i <= 3; i++) {
		// we need to tell it which file we need
		clip = AudioSystem.getClip();

		if (clip == null)
		    throw new LineUnavailableException();
		clip.open(AudioSystem.getAudioInputStream(cl.getResource("resources/pray" + String.valueOf(i) + ".wav")));
		clips.put(String.valueOf(i), clip);
	    }

	} catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
	    System.err.println("error occurde trying to initalize the sound shit");
	    e.printStackTrace();
	}

    }

    public void playClip(String clipName) {
	try {
	    final Clip clip = clips.get(clipName);

	    if (clip == null) {
		throw new LineUnavailableException();
	    }
	    clip.setMicrosecondPosition(0);
	    clip.start();

	    /*
	     * try { Thread.sleep(clip.getMicrosecondLength() / 1000); } catch (InterruptedException e) { e.printStackTrace(); } //this might be it. we can add a new Thread to play the clips
	     */

	    // clip.setMicrosecondPosition(0);
	} catch (LineUnavailableException e) {

	    System.err.println("some error occured while trying to play the sound clip");
	    e.printStackTrace();
	}
    }

    public static Sound getSound() {
	return theSound;
    }

    public Clip[] getAllClips() {
	return clips.values().toArray(new Clip[clips.size()]);
    }

}