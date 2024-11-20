import java.util.Scanner;
import java.util.HashMap;

public class Game {

	private Room currentRoom;

	public Game() {
		currentRoom = World.buildWorld();
	}

	private static HashMap<String, Item> inventory = new HashMap<>();

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
		Room currentRoom = World.buildWorld();
		Scanner input = new Scanner(System.in);

		String command;
		do {
			System.out.println(currentRoom);
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
				Room nextRoom = currentRoom.getExit(command.charAt(0));
				if (nextRoom == null) {
					System.out.println("You can't go that way sorry!");
				} else {
					// Check if the room is locked
					if (nextRoom.isLocked()) {
						// Check if the player has the key
						Item key = inventory.get("key");
						if (key != null) {
							// Unlock the locked room
							nextRoom.setLocked(false);
							System.out.println("You used the key to unlock the room.");
							currentRoom = nextRoom;
						} else {
							System.out.println("The room is locked. You need a key.");
						}
					} else {
						currentRoom = nextRoom; // Move to the next room
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
					Item i = currentRoom.getItem(itemName);
					if (i == null) {
						System.out.println("There's nothing to take.");
					} else {
						inventory.put(i.getName(), i);
						System.out.println("You pick up the " + i.getName());
					}
				}
				break;
			case "look":
				Item t = currentRoom.getItem(words[1]);
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

			// Update current room after handling direction commands // mm

		} while (!command.equals("x")); //

		input.close();

	}

}
