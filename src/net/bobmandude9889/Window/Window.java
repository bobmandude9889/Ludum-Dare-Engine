package net.bobmandude9889.Window;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import net.bobmandude9889.Render.Camera;

@SuppressWarnings("serial")
public class Window extends JFrame {
	
	public Display display;
	
	private Image backgroundImage;
	
	public Window(int width, int height, String title, Camera camera, Color background){
		super();
		this.setSize(width, height);
		this.setTitle(title);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.display = new Display(this,camera);
		this.add(display);
		BufferedImage bufferedBackground = new BufferedImage(1,1,BufferedImage.TYPE_INT_RGB);
		Graphics g = bufferedBackground.getGraphics();
		g.setColor(background);
		g.fillRect(0, 0, 1, 1);
		this.backgroundImage = bufferedBackground;
	}
	
	public Window(int width, int height, String title, Camera camera, Image background){
		super();
		this.setSize(width, height);
		this.setTitle(title);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.display = new Display(this,camera);
		this.add(display);
		this.backgroundImage = background;
	}
	
	public boolean contains(Point point){
		return point.x > 0 && point.x < getWidth() && point.y > 0 && point.y < getHeight();
	}
	
	public Image getBackgroundImage(){
		return backgroundImage;
	}
	
}
