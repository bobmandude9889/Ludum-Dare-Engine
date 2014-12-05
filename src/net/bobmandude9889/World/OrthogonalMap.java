package net.bobmandude9889.World;

import java.awt.Graphics;

public class OrthogonalMap implements Map {
	
	public OrthogonalLayer[] layers;
	
	public int width;
	public int height;
	public int tileWidth;
	public int tileHeight;
	
	protected OrthogonalMap(int width, int height, int tileWidth, int tileHeight, OrthogonalLayer[] layers){
		this.layers = layers;
		this.width = width;
		this.height = height;
		this.tileWidth = tileWidth;
		this.tileHeight = tileHeight;
	}
	
	@Override
	public String toString(){
		String out = "";
		for(int i = 0; i < layers.length; i++){
			OrthogonalLayer layer = layers[i];
			out += "Layer " + i + ": \n";
			for(int x = 0; x < layer.getWidth(); x++){
				for(int y = 0; y < layer.getHeight(); y++){
					out += x + " , " + y + " : " + layer.getTiles()[x + (y * width)] + "\n";
				}
			}
			out += "\n";
		}
		return out;
	}
	
	@Override
	public void render(Graphics g){
		for (int i = 0; i < layers.length; i++) {
			OrthogonalLayer layer = layers[i];
			for (int x = 0; x < width; x++) {
				for (int y = 0; y < height; y++) {
					System.out.println(layer == null);
					if (layer != null) {
						Tile t = layer.getTile(x, y);
						if (t != null) {
							g.drawImage(t.getImage(), x * 50, y * 50, 50, 50, null);
						}
					}
				}
			}
		}
	}
	
}
