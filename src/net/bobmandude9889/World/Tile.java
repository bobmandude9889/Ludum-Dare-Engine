package net.bobmandude9889.World;

import java.awt.Image;

public class Tile {
	
	private Image img;
	private PropertySet properties;
	private int id;
	
	protected Tile(Image img, PropertySet properties, int id){
		this.img = img;
		if(properties == null){
			this.properties = new PropertySet();
		} else {
			this.properties = properties;
		}
		this.id = id;
	}
	
	public Image getImage(){
		return img;
	}
	
	public PropertySet getProperties(){
		return properties;
	}
	
	public int getId(){
		return id;
	}
	
}
