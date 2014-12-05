package net.bobmandude9889.World;

import java.util.ArrayList;
import java.util.List;

public class PropertySet {
	
	private List<Property> properties = new ArrayList<Property>();
	
	protected PropertySet(){};
	
	public void add(Property property){
		properties.add(property);
	}
	
	public String valueOf(String name){
		for(Property p : properties){
			if(p.name.equals(name)){
				return p.value;
			}
		}
		return null;
	}
	
}
