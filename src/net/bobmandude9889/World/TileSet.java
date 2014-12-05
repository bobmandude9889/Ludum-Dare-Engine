package net.bobmandude9889.World;

import java.awt.image.BufferedImage;
import java.util.HashMap;

public class TileSet {
	
	private int tileWidth;
	private int tileHeight;
	private BufferedImage imageMatrix;
	private Tile[] tiles;
	private int firstgid;
	
	protected TileSet(int tileWidth, int tileHeight, BufferedImage imageMatrix, HashMap<Integer,PropertySet> propertyList, int firstgid){
		this.tileWidth = tileWidth;
		this.tileHeight = tileHeight;
		this.imageMatrix = imageMatrix;
		this.firstgid = firstgid;
		tiles = new Tile[(int) (Math.floor(imageMatrix.getHeight(null) / tileHeight) * Math.floor(imageMatrix.getWidth(null) / tileWidth))];
		int width = (int) Math.floor(imageMatrix.getWidth(null) / tileWidth);
		for(int i = 0; i < tiles.length; i++){
			PropertySet properties = null;
			if(propertyList.containsKey(i)){
				properties = propertyList.get(i);
			}
			tiles[i] = new Tile(imageMatrix.getSubimage((i % width) * tileWidth, (int) Math.floor(i / width) * tileHeight, tileWidth, tileHeight), properties, i);
		}
	}
	
	public Tile[] getTiles(){
		return tiles;
	}
	
	public Tile get(int index){
		return tiles[index];
	}
	
	public Tile getGlobal(int index){
		return tiles[index + firstgid];
	}
	
	public int getTileWidth(){
		return tileWidth;
	}
	
	public int getTileHeight(){
		return tileHeight;
	}
	
	public BufferedImage getImageMatrix(){
		return imageMatrix;
	}
	
}
