package net.bobmandude9889.Render.UI;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class UI implements MouseListener, MouseMotionListener {
	
	BufferedImage background;
	int x, y, width, height;
	List<UIComponent> components;
	int mouseX = 0, mouseY = 0;
	
	public UI(int x, int y, int width, int height, BufferedImage background) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.background = background;
		components = new ArrayList<>();
	}
		
	public void draw(Graphics g) {
		for (UIComponent component : components) {
			BufferedImage image = component.contains(mouseX, mouseY) ? component.hoverImage : component.image;
			g.drawImage(image, this.x + component.x, this.y + component.y, component.width, component.height, null);
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		this.mouseX = e.getX();
		this.mouseY = e.getY();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		for (UIComponent component : components) {
			if (component.contains(e.getX(), e.getY())) {
				component.onClick();
			}
		}
	}
		
}
