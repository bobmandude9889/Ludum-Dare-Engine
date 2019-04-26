package net.bobmandude9889.Render.UI;

import java.awt.image.BufferedImage;

public abstract class UIComponent {

	int x, y, width, height;
	BufferedImage image;
	BufferedImage hoverImage = null;
	
	public abstract void onClick();
	
	public UIComponent(int x, int y, int width, int height, BufferedImage image) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.image = image;
	}
	
	public void setHoverImage(BufferedImage hoverImage) {
		this.hoverImage = hoverImage;
	}
	
	public BufferedImage getImage() {
		return image;
	}
	
	public BufferedImage getHoverImage() {
		return hoverImage;
	}
	
	public boolean contains(int x, int y) {
		return (x > this.x && x < this.x + this.width && y > this.y && y < this.y + this.height);
	}
	
}
