package net.bobmandude9889.World;

public class Location {
	
	public double x;
	public double y;
	
	public Location(double d, double e){
		this.x = d;
		this.y = e;
	}
	
	public Location add(double x, double y){
		this.x += x;
		this.y += y;
		return this;
	}
	
	public Location plus(double x, double y){
		return new Location(this.x + x, this.y + y);
	}
	
}
