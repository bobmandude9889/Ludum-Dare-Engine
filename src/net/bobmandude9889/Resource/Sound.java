package net.bobmandude9889.Resource;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound {

	private FloatControl volume;

	private String fileName;

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
		float vol = (float) (range * (Math.sqrt(percent) / 10));
		volume.setValue(volume.getMinimum() + vol);
	}

	public float getVolume() {
		return (volume.getValue() / volume.getMaximum()) * 100f;
	}

	public boolean isPlaying() {
		return clip.getFrameLength() != clip.getFramePosition() && clip.isRunning();
	}

	public void pause(){
		clip.stop();
	}
	
	public void resume(){
		clip.start();
	}
	
	public synchronized void play(float percent) {
		setVolume(percent);
		clip.setFramePosition(0);
		clip.start();
	}

}
