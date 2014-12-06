package net.bobmandude9889.Render;

import java.awt.Color;
import java.awt.Dimension;
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

	public Location getTopLeft(Window window, Dimension dimension) {
		return location.plus(-((double) dimension.width / 2) / zoom, -((double) dimension.height / 2) / zoom);
	}

	public void render(Graphics g, Window window, Dimension dimension) {
		Image background = window.getBackgroundImage();
		g.clearRect(0,0,dimension.width,dimension.height);
		g.drawImage(background, 0, 0, dimension.width, dimension.height, null);
		Location loc = getTopLeft(window,dimension);
		if (world.map instanceof OrthogonalMap) {
			OrthogonalMap map = (OrthogonalMap) world.map;
			// Render tiles
			for (int i = 0; i < map.layers.length; i++) {
				OrthogonalLayer layer = map.layers[i];
				int renderToX = (int) (Math.ceil(loc.x + 2) + Math.ceil((dimension.width / zoom)));
				int startingX = (int) getTopLeft(window,dimension).x;
				if(startingX < 0) startingX = 0;
				for (int x = startingX; x < (renderToX > map.width ? map.width : renderToX); x++) {
					int renderToY = (int) (Math.ceil(loc.y + 2) + Math.ceil((dimension.height / zoom)));
					int startingY = (int) getTopLeft(window,dimension).y;
					if(startingY < 0) startingY = 0;
					for (int y = startingY; y < (renderToY > map.height ? map.height : renderToY); y++) {
						if (layer != null) {
							Tile t = layer.getTile(x, y);
							if (t != null) {
								Location topLeft = getTopLeft(window,dimension);
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
			Location topLeft = getTopLeft(window,dimension);
			Point render = new Point((int) (zoom * (eLoc.x - topLeft.x)), (int) (zoom * (eLoc.y - topLeft.y)));
			Image img = e.getImage();
			double ratio = (double) img.getWidth(null) / (double) img.getHeight(null);
			Dimension imgSize = new Dimension(zoom, (int) (zoom / ratio));
			if (render.x + imgSize.width > 0 && render.y + imgSize.getHeight() > 0 && render.x < dimension.width && render.y < dimension.height) {
				g.drawImage(img, render.x, render.y, imgSize.width, imgSize.height, null);
			}
		}
	}

}
