package net.bobmandude9889.Resource;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound implements Runnable {

	private FloatControl volume;

	private String fileName;

	private float percentVolume;

	public Clip clip;
	
	protected Sound(String name) {
		this.fileName = name;
		try {
			clip = AudioSystem.getClip();
			AudioInputStream inputStream = AudioSystem.getAudioInputStream(ResourceLoader.getFile(fileName));
			clip.open(inputStream);
			FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			this.volume = volume;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setVolume(float percent) {
		float range = volume.getMaximum() - volume.getMinimum();
		float vol = range * (percent / 100);
		volume.setValue(volume.getMinimum() + vol);
	}

	public synchronized void play(float percent) {
		this.percentVolume = percent;
		new Thread(this).start();
	}

	@Override
	public void run() {
		try {
			clip.start();
			float range = volume.getMaximum() - volume.getMinimum();
			float vol = range * (percentVolume / 100);
			volume.setValue(volume.getMinimum() + vol);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
