package net.bobmandude9889.Render.UI;

import java.awt.image.BufferedImage;

public class Button extends UIComponent {
	
	BufferedImage image;
	BufferedImage hoverImage = null;
	Runnable onClick;
	
	public Button(int x, int y, int width, int height, BufferedImage image, Runnable onClick) {
		super(x,y,width,height, image);
	}
	
	public void setHoverImage(BufferedImage hoverImage) {
		this.hoverImage = hoverImage;
	}

	@Override
	public void onClick() {
		onClick.run();
	}
	
}
