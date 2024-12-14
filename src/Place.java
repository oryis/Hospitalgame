import java.util.HashMap;
import java.util.Map;

public class Place {

	private static String Response = null;

	private String description;
	private HashMap<String, Item> items;
	private HashMap<String, NPC> npcs = new HashMap<>();
	private Place east;
	private Place west;
	private Place north;
	private Place south;
	private Place up;
	private Place down;
	private boolean Locked;
	private String keyItem;

	public Place(String desc) {
		description = desc;
		items = new HashMap<>();
		this.npcs = new HashMap<>();
	}

	public Place getExit(char d) {
		switch (d) {
		case 'e':
			return east;
		case 'w':
			return west;
		case 'n':
			return north;
		case 's':
			return south;
		case 'u':
			return up;
		case 'd':
			return down;
		default:
			return null;
		}
	}

	public void addExit(Place c, char d) {
		switch (d) {
		case 'e':
			east = c;
			break;
		case 'w':
			west = c;
			break;
		case 'n':
			north = c;
			break;
		case 's':
			south = c;
			break;
		case 'u':
			up = c;
			break;
		case 'd':
			down = c;
			break;
		}
	}

	public String toString() {
		return description;
	}

	public Item getItem(String name) {
		return items.get(name);
	}

	public void putItem(Item i) {
		items.put(i.getName(), i);
	}

	public boolean isLocked() {
		return Locked;
	}

	public void setLocked(boolean locked) {
		Locked = locked;
	}

	public void lockRoom(String keyItem) {
		this.Locked = true;
		this.keyItem = keyItem;
	}

	public void unlockRoom() {
		this.Locked = false;
		this.keyItem = null;
	}

	public String getKeyItem() {
		return keyItem;
	}

	public String getName() {
		return description; // This returns the description, which acts as the name
	}

	public void addItem(Item supplyCabinet) {
		items.put(supplyCabinet.getName(), supplyCabinet);
	}

	public void addItem(Combination combination) {
		items.put(combination.getName(), combination);
	}

	public void addNPC(NPC puppy) {
		npcs.put(puppy.getName(), puppy);
	}

	public NPC getNPC(String name) {
		return npcs.get(name);
	}

	public String getResponse() {
		return Response;
	}

	public void removeNPC(NPC npc) {
		npcs.remove(npc.getName());
	}

	public void showNPCS() {
		if (npcs.isEmpty()) {
			System.out.println("There are no NPCs here.");
		} else {
			System.out.println("You see:");
			for (NPC npc : npcs.values()) {
				System.out.println("- " + npc.getName() + ": " + npc.getdesc());
			}
		}
	}

	public String getDescripton() {
		return description;
	}

	public HashMap<String, NPC> getNPCs() {
		return npcs;
	}

	public void addExit(Place place, String direction) {
		switch (direction) {
		case "east":
			east = place;
			break;
		case "west":
			west = place;
			break;
		case "north":
			north = place;
			break;
		case "south":
			south = place;
			break;
		case "up":
			up = place;
			break;
		case "down":
			down = place;
			break;
		}
	}

	public String getItems() {
		return items.keySet().toString();
	}

	public String displayDetails() {
		return description + "\nItems: " + getItems() + "\nNPCs: " + getNPCs().keySet().toString();
	}

	public boolean hasNPC(String npcName) {
		return npcs.containsKey(npcName);
	}
}
