package net.bobmandude9889.World;

import java.util.ArrayList;
import java.util.List;

public class GlobalTileSet {

	private static List<TileSet> tileSets = new ArrayList<TileSet>();
	private static List<Integer> firstGids = new ArrayList<Integer>();
	
	public static void addTileSet(TileSet tileSet){
		tileSets.add(tileSet);
	}
	
	public static void addFirstGid(Integer i){
		firstGids.add(i);
	}
	
	public static Tile get(int i){
		for (int j = 0; j < firstGids.size(); j++) {
			int firstGid = firstGids.get(j);
			if(i < firstGid) return tileSets.get(j - 1).get(i - firstGids.get(j - 1));
		}
		return tileSets.get(tileSets.size() - 1).get(i - firstGids.get(firstGids.size() - 1));
	}
	
}
