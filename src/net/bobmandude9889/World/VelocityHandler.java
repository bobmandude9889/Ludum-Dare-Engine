package net.bobmandude9889.World;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class VelocityHandler {

	private static List<Entity> entityList = new CopyOnWriteArrayList<Entity>();
	private static int delay;

	private static Thread thread = new Thread(new VelocityHandler().new HandlerRunnable(entityList, delay));

	protected VelocityHandler() {
	}

	public static void init(int delayMil) {
		thread.start();
		delay = delayMil;
	}

	public static void add(Entity entity) {
		entityList.add(entity);
	}

	private class HandlerRunnable implements Runnable {

		List<Entity> entityList;
		int delay;
		long lastUpdate;

		protected HandlerRunnable(List<Entity> entityList, int delay) {
			this.entityList = entityList;
			this.delay = delay;
		}

		@Override
		public void run() {
			lastUpdate = System.currentTimeMillis();
			while (true) {
				if (System.currentTimeMillis() - lastUpdate >= delay) {
					for (Entity e : entityList) {
						Velocity v = e.getVelocity();
						e.setLocation(e.getLocation().plus(v.x, v.y));
					}
					lastUpdate = System.currentTimeMillis();
				}
			}
		}

	}
}