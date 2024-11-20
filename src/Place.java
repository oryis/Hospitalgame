import java.util.HashMap;

public class Place {
	// OBJECT variables - usually private

	private String description; // Place description to show players
	// object reference - set to null by default
	private HashMap<String, Item> items; // updating to hash maps to store items
	private Place east; // reference to Place to east
	private Place west; // reference to Place west
	private Place north; // reference to Place north
	private Place south; // reference to Place south   dfvg
	private Place up; // reference up
	private Place down; // exist
	private boolean Locked;

	// constructor method - initialize Place description
	public Place(String desc, String n) { // constructor method
		description = desc;
		items = new HashMap<>(); // all
	}

	// object method

	// object methods

	public Place getExit(char d) { // get method, return description
		if (d == 'e')
			return east;
		else if (d == 'w')
			return west;
		else if (d == 'n')
			return north;
		else if (d == 's')
			return south;
		else if (d == 'u')
			return up;
		else if (d == 'd')
			return down;
		else
			return null; //
	}
	// updates one of the Places variables
	// Character parameter identifies which variable
	// to update (example: 'e' means update the east
	// 
	// need to update this

	public void addExit(Place c, char d) {
		if (d == 'e')
			east = c;
		else if (d == 'w')
			west = c;
		else if (d == 'n')
			north = c;
		else if (d == 's')
			south = c;
		else if (d == 'u')
			up = c;
		else if (d == 'd')
			down = c;
	}

	// Formats the objects data as a string.
	// returns the string.
	public String toString() {
		return description;
	}

	public Item getItem(String name) {
		return items.get(name);
	} // 

	public void putItem(Item i) {
		items.put(Item.getName(), i);

	}

	public boolean isLocked() {
		return Locked;
	}

	public void setLocked(boolean locked) {
		Locked = locked;
	}

	public void unlock() {
	}

	public String getName() {
		return getName();

	}

	public void setName(String name) {
	}

	public void addItem(Safe supplyCabinet) {
	}

	public void addItem(Combination combination) { //

	}

}
