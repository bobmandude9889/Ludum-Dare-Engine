package net.bobmandude9889.World;

public class OrthogonalLayer {

	private int width;
	private int height;

	private int[] tiles;

	protected OrthogonalLayer(int[] tiles, int width, int height) {
		this.tiles = tiles;
		this.width = width;
		this.height = height;
	}

	public Tile getTile(int x, int y) {
		try {
			int id = tiles[x + (y * width)];
			if (id == 0) {
				return null;
			} else {
				return GlobalTileSet.get(id);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			return null;
		}
	}

	public int[] getTiles() {
		return tiles;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

}
