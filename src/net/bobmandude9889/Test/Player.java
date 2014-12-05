package net.bobmandude9889.Test;

import java.awt.Image;
import java.awt.image.BufferedImage;

import net.bobmandude9889.Render.Camera;
import net.bobmandude9889.Resource.ResourceLoader;
import net.bobmandude9889.World.Entity;
import net.bobmandude9889.World.Location;
import net.bobmandude9889.World.Velocity;

public class Player implements Entity{

	Location location;
	Velocity velocity;
	Camera camera;
	
	BufferedImage img;
	
	public Player(Location location, Velocity velocity, Camera camera){
		this.location = location;
		this.velocity = velocity;
		this.camera = camera;
		BufferedImage img = ResourceLoader.loadImage("player_sheet.png");
		this.img = img.getSubimage(0, 0, 50, 80);
	}
	
	@Override
	public Location getLocation() {
		return location;
	}

	@Override
	public void setLocation(Location location) {
		this.location = location;
		camera.location = this.location;
	}

	@Override
	public Velocity getVelocity() {
		return velocity;
	}

	@Override
	public void setVelocity(Velocity velocity) {
		this.velocity = velocity;
	}

	@Override
	public Image getImage() {
		return img;
	}

}
