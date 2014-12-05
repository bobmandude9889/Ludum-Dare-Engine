package net.bobmandude9889.World;

import java.awt.Image;

public interface Entity {
	
	public Location getLocation();
	public void setLocation(Location location);
	public Velocity getVelocity();
	public void setVelocity(Velocity velocity);
	public Image getImage();
	
}
