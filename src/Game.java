import java.util.Scanner;
import java.util.HashMap;

public class Game {

	private static final String NPCPuppy = null;
	private Place currentPlace;
	public static Scanner input = new Scanner(System.in);

	public static void print(String message) {
		System.out.println(message);
	}

	public static void main1(String[] args) {
		Puppy puppy = new Puppy();

		for (int i = 0; i < 4; i++) { // interaction loop
			puppy.talk();

		}
	}

	public Game() {
		currentPlace = World.buildWorld();
	}

	public static HashMap<String, Item> inventory = new HashMap<>();
	public static HashMap<String, String> roomDescriptions = new HashMap<>();

	public static HashMap<String, Item> getInventory() {
		return inventory;
	}

	public static void main(String[] args) {
		runGame();
	}

	public static void print(Object obj) {
		System.out.println(obj.toString());
	}

	public static Item getInventory(String itemName) {
		return inventory.get(itemName);
	}

	public static void runGame() {
		Place currentPlace = World.buildWorld();

		String command;
		do {
			System.out.println(currentPlace);
			System.out.print("Where do you want to go? ");
			command = input.nextLine();
			String[] words = command.split(" ");

			switch (words[0]) {
			case "e":
			case "w":
			case "n": // when i run it says my north is null
			case "s":
			case "u":
			case "d":
				Place nextPlace = currentPlace.getExit(command.charAt(0));
				if (nextPlace == null) {
					System.out.println("You can't go that way sorry!");
				} else {
					// Check if the Place is locked
					if (nextPlace.isLocked()) {
						// Check if the player has the key
						Item key = inventory.get("key");
						if (key != null) {
							// Unlock the locked Place
							nextPlace.setLocked(false);
							System.out.println("You used the key to unlock the Place.");
							currentPlace = nextPlace;
						} else {
							System.out.println("The Place is locked. You need a key.");
						}
					} else {
						currentPlace = nextPlace; // Move to the next Place
					}
				}
				break;
			case "x":
				System.out.println(" Bye! Thanks for walking through my Game:). ");
				break;
			case "take":
				if (words.length < 2) {
					System.out.println("You need to specify what you want to take!");
				} else {
					String itemName = words[1];
					Item i = currentPlace.getItem(itemName);
					if (i == null) {
						System.out.println("There's nothing to take.");
					} else {
						inventory.put(i.getName(), i);
						System.out.println("You pick up the " + i.getName());
					}
				}
				break;
			case "look":
				Item t = currentPlace.getItem(words[1]);
				if (t == null) {
					System.out.println("There's nothing there to look at.");
				} else {
					System.out.println(t.getDescription()); // Print the description of the specific item
				}
				break;
			case "i":
				System.out.println("You are Carrying:");
				for (Item it : inventory.values()) {
					System.out.println(it);
				}
				System.out.println();
				break;
			case "use":
				Item itemToUse = inventory.get(words[1]);
				if (itemToUse != null) {
					itemToUse.use(); // calls the use method
				} else {
					System.out.println("You don't have that item, try again.");
				}
			case "talk":
				NPC NpcPuppy = currentPlace.getNPC(words[1]);
				if (NpcPuppy != null) {
					NpcPuppy.talk(); // to take????
				} else {
					System.out.println("There's no puppy to talk too, stop talking to yourself.");
				}
				break;
			case "open":
				Item itemTOpen = inventory.get(words[1]);
				if (itemTOpen != null) {
					itemTOpen.open();
				} else {
					System.out.println("There's nothing to open here.");
				}

			default:
				System.out.println("I don't know what you mean."); // me either girl
				break;
			}

			// Update current Place after handling direction commands // mm

		} while (!command.equals("x")); //

		input.close();

	}

}
