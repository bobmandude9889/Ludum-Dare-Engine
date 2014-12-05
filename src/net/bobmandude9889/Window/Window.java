package net.bobmandude9889.Window;

import javax.swing.JFrame;

import net.bobmandude9889.Render.Camera;

public class Window extends JFrame {
	
	public Display display;
	
	public Window(int width, int height, String title, Camera camera){
		super();
		this.setSize(width, height);
		this.setTitle(title);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.display = new Display(this,camera);
		this.add(display);
	}
	
}
