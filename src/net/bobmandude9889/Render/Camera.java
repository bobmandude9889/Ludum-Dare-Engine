package net.bobmandude9889.Render;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.util.List;

import net.bobmandude9889.Window.Window;
import net.bobmandude9889.World.Entity;
import net.bobmandude9889.World.Location;
import net.bobmandude9889.World.OrthogonalLayer;
import net.bobmandude9889.World.OrthogonalMap;
import net.bobmandude9889.World.Tile;
import net.bobmandude9889.World.World;

public class Camera {

	public Location location;
	public int zoom;
	public World world;

	public Camera(Location loc, int zoom, World world) {
		this.location = loc;
		this.zoom = zoom;
		this.world = world;
	}

	public void move(Location loc) {
		this.location = loc;
	}

	public Location getLocation() {
		return location;
	}

	public Location getTopLeft(Window window) {
		return location.plus(-((double) window.getWidth() / 2) / zoom, -((double) window.getHeight() / 2) / zoom);
	}

	public void render(Graphics g, Window window) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, window.getWidth(), window.getHeight());
		Location loc = getTopLeft(window);
		if (world.map instanceof OrthogonalMap) {
			OrthogonalMap map = (OrthogonalMap) world.map;
			// Render tiles
			for (int i = 0; i < map.layers.length; i++) {
				OrthogonalLayer layer = map.layers[i];
				int renderToX = (int) (Math.ceil(loc.x + 2) + Math.ceil((window.getWidth() / zoom)));
				int startingX = (int) getTopLeft(window).x;
				if(startingX < 0) startingX = 0;
				for (int x = startingX; x < (renderToX > map.width ? map.width : renderToX); x++) {
					int renderToY = (int) (Math.ceil(loc.y + 2) + Math.ceil((window.getHeight() / zoom)));
					int startingY = (int) getTopLeft(window).y;
					if(startingY < 0) startingY = 0;
					for (int y = startingY; y < (renderToY > map.height ? map.height : renderToY); y++) {
						if (layer != null) {
							Tile t = layer.getTile(x, y);
							if (t != null) {
								Location topLeft = getTopLeft(window);
								Point render = new Point((int) (zoom * (x - topLeft.x)), (int) (zoom * (y - topLeft.y)));
								Image img = t.getImage();
								g.drawImage(img, render.x, render.y, zoom, zoom, null);
								g.setColor(Color.WHITE);
							}
						}
					}
				}
			}
		}
		// Render Entities
		List<Entity> entityList = world.entityList;
		for (Entity e : entityList) {
			Location eLoc = e.getLocation();
			Location topLeft = getTopLeft(window);
			Point render = new Point((int) (zoom * (eLoc.x - topLeft.x)), (int) (zoom * (eLoc.y - topLeft.y)));
			if (render.x + e.getImage().getWidth(null) > 0 && render.y + e.getImage().getHeight(null) > 0 && render.x < window.getWidth() && render.y < window.getHeight()) {
				Image img = e.getImage();
				double ratio = (double) img.getWidth(null) / (double) img.getHeight(null);
				g.drawImage(img, render.x, render.y, zoom, (int) (zoom / ratio), null);
			}
		}
	}

}
