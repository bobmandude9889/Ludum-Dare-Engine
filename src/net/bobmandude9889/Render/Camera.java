package net.bobmandude9889.Render;

import java.awt.Color;
import java.awt.Graphics;

import net.bobmandude9889.Location.Location;
import net.bobmandude9889.Window.Window;
import net.bobmandude9889.World.OrthogonalLayer;
import net.bobmandude9889.World.OrthogonalMap;
import net.bobmandude9889.World.Tile;
import net.bobmandude9889.World.World;

public class Camera {

	public Location loc;
	public int zoom;
	public World world;

	public Camera(Location loc, int zoom, World world) {
		this.loc = loc;
		this.zoom = zoom;
		this.world = world;
	}

	public void move(Location loc) {
		this.loc = loc;
	}

	public Location getLocation() {
		return loc;
	}

	public void render(Graphics g, Window window) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, window.getWidth(), window.getHeight());
		if (world.map instanceof OrthogonalMap) {
			OrthogonalMap map = (OrthogonalMap) world.map;
			for (int i = 0; i < map.layers.length; i++) {
				OrthogonalLayer layer = map.layers[i];
				int renderToX = (int) (Math.ceil(loc.x + 2) + Math.ceil((window.getWidth() / zoom)));
				int startingX = (int) Math.floor(loc.x);
				for (int x = startingX; x < (renderToX > map.width ? map.width : renderToX); x++) {
					int renderToY = (int) (Math.ceil(loc.y + 2) + Math.ceil((window.getHeight() / zoom)));
					int startingY = (int) Math.floor(loc.y);
					for (int y = startingY; y < (renderToY > map.height ? map.height : renderToY); y++) {
						if (layer != null) {
							Tile t = layer.getTile(x, y);
							if (t != null) {
								g.drawImage(t.getImage(), ((x - startingX) * zoom) - (int) ((loc.x - Math.floor(loc.x)) * zoom), ((y - startingY) * zoom) - (int) ((loc.y - Math.floor(loc.y)) * zoom), zoom, zoom, null);
							}
						}
					}
				}
			}
		}
	}

}
