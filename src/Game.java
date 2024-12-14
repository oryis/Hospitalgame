import java.util.Scanner;
import java.util.HashMap;

public class Game {

	private static final String NPCPuppy = null;
	private static Place currentPlace;
	public static Scanner input = new Scanner(System.in);

	public static String print(String message) {
		System.out.println(message);
		return message;
	}

	String currentPlaceDescription = Game.print(currentPlace.displayDetails()); // Use displayDetails

	public static void processCommand(String command) {
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
				Game.print("You can't go that way sorry!");
			} else {
				// Check if the Place is locked
				if (nextPlace.isLocked()) {
					// Check if the player has the key
					Item key = inventory.get("key");
					if (key != null) {
						// Unlock the locked Place
						nextPlace.setLocked(false);
						Game.print("You used the key to unlock the Place.");
						currentPlace = nextPlace;
					} else {
						Game.print("The Place is locked. You need a key.");
					}
				} else {
					currentPlace = nextPlace; // Move to the next Place
				}
			}
			break;
		case "x":
			Game.print(" Bye! Thanks for walking through my Game:). ");
			break;
		case "take":
			if (words.length > 1) {
				Item item = currentPlace.getItem(words[1]);
				if (item != null) {
					inventory.put(item.getName(), item);
					Game.print("You found and picked up a: " + item.getName());
				} else {
					Game.print("No such item in the room.");
				}
			}
			break;
		case "look":
			Item t = currentPlace.getItem(words[1]);
			if (t == null) {
				Game.print("There's nothing there to look at.");
			} else {
				Game.print(t.getDescription()); // Print the description of the specific item
			}
			break;
		case "i":
			Game.print("You are Carrying:");
			for (Item it : inventory.values()) {
				Game.print(it);
			}
			Game.print("");
			break;
		case "use":
			Item itemToUse = inventory.get(words[1]);
			if (itemToUse != null) {
				itemToUse.use(); // calls the use method
			} else {
				Game.print("You don't have that item, try again.");
			}
		case "talk":
			if (words.length < 2) {
				Game.print("Talk to whom?");
				return;
			}

			String npcName = words[1];
			NPC npc = currentPlace.getNPC(npcName);

			if (npc != null) {
				npc.talk();

				// Handle the nurseGhost riddle separately
				if (npc.getType().equals("nurseGhost")) {
					nurseGhost nurse = (nurseGhost) npc;

					if (!nurse.hasSolvedRiddle()) {
						Game.print("What has a key but no lock, a space but no room, and an entrance but no door?");
						String playerResponse = input.nextLine();

						if (playerResponse.equalsIgnoreCase("A keyboard")) {
							Game.print("Correct! Here, take this.");
							Game.getInventory().put("lockpick",
									new Item("lockpick", "A lockpick for one of the doors"));
							nurse.setSolvedRiddle(true);
						} else {
							Game.print("Incorrect. Try again.");
						}
					}
				}
			} else {
				Game.print("There's no such NPC in this place.");
			}
			break;

		case "open":
			Item itemTOpen = inventory.get(words[1]);
			if (itemTOpen != null) {
				itemTOpen.open();
			} else {
				Game.print("There's nothing to open here.");
			}

		default:
			Game.print("I don't know what you mean."); // me either girl
			break;
		}

	}

	public static void main1(String[] args) {
		Puppy puppy = new Puppy();

		for (int i = 0; i < 4; i++) { // interaction loop
			puppy.talk();

		}
		String command = input.nextLine();
		String[] words = command.split(" ");
	}

	public Game() {
		currentPlace = World.buildWorld();
	}

	public static HashMap<String, Item> inventory = new HashMap<>();
	public static HashMap<String, String> room_Description = new HashMap<>();

	public static HashMap<String, Item> getInventory() {
		return inventory;
	}

	public static void main(String[] args) {
		runGame();
	}

	public static void print(Object obj) {
		Game.print(obj.toString());
	}

	public static Item getInventory(String itemName) {
		return inventory.get(itemName);

	}

	public static void runGame() {
		Place currentPlace = World.buildWorld();

		String command;
		do {
			Game.print(currentPlace);
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
					Game.print("You can't go that way sorry!");
				} else {
					// Check if the Place is locked
					if (nextPlace.isLocked()) {
						// Check if the player has the key
						Item key = inventory.get("key");
						if (key != null) {
							// Unlock the locked Place
							nextPlace.setLocked(false);
							Game.print("You used the key to unlock the Place.");
							currentPlace = nextPlace;
						} else {
							Game.print("The Place is locked. You need a key.");
						}
					} else {
						currentPlace = nextPlace; // Move to the next Place
					}
				}
				break;
			case "x":
				Game.print(" Bye! Thanks for walking through my Game:). ");
				break;
			case "take":
				if (words.length > 1) {
					Item item = currentPlace.getItem(words[1]);
					if (item != null) {
						inventory.put(item.getName(), item);
						Game.print("You picked up: " + item.getName());
					} else {
						Game.print("No such item in the room.");
					}
				}
				break;
			case "look":
				Item t = currentPlace.getItem(words[1]);
				if (t == null) {
					Game.print("There's nothing there to look at.");
				} else {
					Game.print(t.getDescription()); // Print the description of the specific item
				}
				break;
			case "i":
				Game.print("You are Carrying:");
				for (Item it : inventory.values()) {
					Game.print(it);
				}
				Game.print("");
				break;
			case "use":
				Item itemToUse = inventory.get(words[1]);
				if (itemToUse != null) {
					itemToUse.use(); // calls the use method
				} else {
					Game.print("You don't have that item, try again.");
				}
			case "talk":
				if (words.length < 2) {
					Game.print("Talk to whom?");
					return;
				}

				String npcName = words[1];
				NPC npc = currentPlace.getNPC(npcName);

				if (npc != null) {
					npc.talk();

					// Handle the nurseGhost riddle separately
					if (npc.getType().equals("nurseGhost")) {
						nurseGhost nurse = (nurseGhost) npc;

						if (!nurse.hasSolvedRiddle()) {
							Game.print("What has a key but no lock, a space but no room, and an entrance but no door?");
							String playerResponse = input.nextLine();

							if (playerResponse.equalsIgnoreCase("A keyboard")) {
								Game.print("Correct! Here, take this.");
								Game.getInventory().put("lockpick",
										new Item("lockpick", "A lockpick for one of the doors"));
								nurse.setSolvedRiddle(true);
							} else {
								Game.print("Incorrect. Try again.");
							}
						}
					}
				} else {
					Game.print("There's no such NPC in this place.");
				}
				break;

			case "open":
				Item itemTOpen = inventory.get(words[1]);
				if (itemTOpen != null) {
					itemTOpen.open();
				} else {
					Game.print("There's nothing to open here.");
				}

			default:
				Game.print("I don't know what you mean."); // me either girl
				break;
			}

			// Update current Place after handling direction commands // mm

		} while (!command.equals("x")); //

		input.close();

	}

	private static void say(String string) {

	}

}
