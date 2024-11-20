import java.util.HashMap;

public class Item {

	private static String name;
	private static String desc;
	public static char[] getDescription;

	public Item(String n, String d) { // constructor method
		name = n;
		desc = d;
	}

	public void setName(String n) { // set method
		name = n;
	}

	public static String getDescription() { // get method for my description
		return desc; // returning my description
	}

	public void setDescription(String d) {
		desc = d; // set method for d
	}

	public static String getName() {
		return name; // returning the name
	}

	public String toString() {
		return name; // my toString
	}

	public void use() {
		Game.print("You can't use that.");
		
	// hi	
	}

	public void open() {
		Game.print("You can't open that.");

	}

	public void open(HashMap<String, Item> inventory) {
		// 
		
	}

	public void put(String string, Item item) { //  
		
		
	}
}
