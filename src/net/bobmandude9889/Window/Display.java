package net.bobmandude9889.Window;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import net.bobmandude9889.Render.Camera;

public class Display extends JPanel implements Runnable, KeyListener {

	private Window window;
	private Camera camera;

	Thread thread = new Thread(this);

	public Display(Window window, Camera camera) {
		this.camera = camera;
		this.window = window;
		window.addKeyListener(this);
		thread.start();
	}

	@Override
	public void paintComponent(Graphics g) {
		camera.render(g, window, this.getSize());
	}

	@Override
	public void run() {
		while (true) {
			repaint();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (!Key.isPressed(e.getKeyCode())){
			Key.add(e.getKeyCode());
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (Key.isPressed(e.getKeyCode()))
			Key.remove(e.getKeyCode());
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

}
