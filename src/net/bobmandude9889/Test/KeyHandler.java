package net.bobmandude9889.Test;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import net.bobmandude9889.Render.Camera;
import net.bobmandude9889.Window.Window;

public class KeyHandler implements KeyListener {

	Camera camera;
	Window window;
	
	public KeyHandler(Camera camera, Window window) {
		this.camera = camera;
		this.window = window;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case 37:
			camera.location.x -= 0.1;
			break;
		case 38:
			camera.location.y -= 0.1;
			break;
		case 39:
			camera.location.x += 0.1;
			break;
		case 40:
			camera.location.y += 0.1;
			break;
		case 45:
			camera.zoom -= 1;
			break;
		case 61:
			camera.zoom += 1;
			break;
		}
		window.display.repaint();
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}

}
