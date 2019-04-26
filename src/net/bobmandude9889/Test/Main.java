package net.bobmandude9889.Test;

import net.bobmandude9889.Render.Camera;
import net.bobmandude9889.Resource.ResourceLoader;
import net.bobmandude9889.Window.Window;
import net.bobmandude9889.World.Location;
import net.bobmandude9889.World.OrthogonalMapLoader;
import net.bobmandude9889.World.Velocity;
import net.bobmandude9889.World.VelocityHandler;
import net.bobmandude9889.World.World;

public class Main {

	public static void main(String[] args){
		World world = new World(OrthogonalMapLoader.load("map.tmx"));
		Camera camera = new Camera(new Location(0,0),50, world);
		Window window = new Window(800,600,"Game", camera, ResourceLoader.loadImage("player_sheet.png"));
		window.addKeyListener(new KeyHandler(camera,window));
		//Sound sound = ResourceLoader.loadSound("sound.wav");
		//sound.play(50f);
		Player player = new Player(new Location(2,2), new Velocity(0,0),camera);
		VelocityHandler.init(10);
		world.addEntity(player);
	}
	
}
