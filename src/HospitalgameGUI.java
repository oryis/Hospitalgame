import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

//though it's not FULLY  finished,i tried. i'm sorry for asking for more time knowing it won't complete it but also thank you for giving me extra time

//some parts work in a way i guess. this was fun to work on so i might finish 
//it for personally during break so i can play it

public class HospitalgameGUI extends JFrame implements ActionListener {

	// GUI Components
	JTextArea gameTextArea;
	JTextField playerInputField;
	JButton executeButton;
	JMenuItem exitMenuItem, instructionsMenuItem;

	// Game State
	Map<String, Room> rooms;
	String currentRoom;
	ArrayList<String> inventory;
	boolean ghostItemTaken;

	public HospitalgameGUI() {
		initializeRooms();
		currentRoom = "lobby";
		inventory = new ArrayList<>();
		ghostItemTaken = false;
		buildWindow();
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == executeButton) {
			String input = playerInputField.getText().trim().toLowerCase();
			if (input.isEmpty()) {
				gameTextArea.append("Please enter a command.\n");
				return;
			}
			processCommand(input);
			playerInputField.setText("");
		}
	}

	private void processCommand(String command) {
		command = getFullDirection(command); // Convert directions to full words if necessary

		Room room = rooms.get(currentRoom);
		if (room == null) {
			gameTextArea.append("Error: Room not found.\n");
			return;
		}

		String[] words = command.split(" ");
		switch (words[0]) {
		case "look":
		case "look around":
			gameTextArea.append(room.getDescription() + "\n");
			gameTextArea.append("Exits: " + String.join(", ", room.getExits().keySet()) + "\n");
			break;

		case "talk":
			if (currentRoom.equals("lobby") && !ghostItemTaken) {
				gameTextArea.append("The Nurse Ghost whispers, 'Solve my riddle to proceed.'\n");
				gameTextArea.append(
						"'What has keys but can't open locks, a space but no room, and an entrance but no door?'\n");
				gameTextArea.append("Options: A) Keyboard  B) Window  C) Mailbox\n");
			} else if (currentRoom.equals("lobby")) {
				gameTextArea.append("The Nurse Ghost says, 'I already gave you the items.'\n");
			} else {
				gameTextArea.append("There's no Nurse Ghost here.\n");
			}
			break;

		case "a": // Correct answer
			if (currentRoom.equals("lobby") && !ghostItemTaken) {
				gameTextArea.append("The Nurse Ghost nods, 'Correct! Here, take the lockpick and the diamond key.'\n");
				inventory.add("lockpick");
				inventory.add("diamond key");
				ghostItemTaken = true;
			} else {
				gameTextArea.append("There's no one here to talk to.\n");
			}
			break;

		case "b":
		case "c": // Wrong answers
			gameTextArea.append("The Nurse Ghost remains silent... That is not the answer.\n");
			break;

		case "inventory":
			displayInventory();
			break;

		case "help":
			gameTextArea.append("Try these commands:\n");
			gameTextArea.append("- 'look around' to inspect your surroundings.\n");
			gameTextArea.append("- 'talk to nurse ghost' if in the lobby.\n");
			gameTextArea.append("- 'inventory' to check your items.\n");
			gameTextArea.append("- Movement: 'north', 'south', 'east', 'west', 'up', 'down'.\n");
			gameTextArea.append("- 'use [item]': Use an item to unlock a room.\n");
			gameTextArea.append("- 'restart' to start the game over.\n");
			break;

		case "restart":
			restartGame();
			break;

		case "use":
			if (words.length > 1) {
				String itemName = command.substring(4);
				if (inventory.contains(itemName)) {
					for (Room nextRoom : rooms.values()) {
						if (nextRoom.isLocked() && itemName.equals(nextRoom.getKeyItem())) {
							nextRoom.unlockRoom();
							gameTextArea
									.append("You use the " + itemName + " to unlock the " + nextRoom.getName() + ".\n");
							return;
						}
					}
					gameTextArea.append("You can't use the " + itemName + " here.\n");
				} else {
					gameTextArea.append("You don't have " + itemName + " in your inventory.\n");
				}
			} else {
				gameTextArea.append("Use what?\n");
			}
			break;

		case "north":
		case "south":
		case "east":
		case "west":
		case "up":
		case "down":
			if (room.getExits().containsKey(command)) {
				String nextRoomName = room.getExits().get(command);
				Room nextRoom = rooms.get(nextRoomName);
				if (nextRoom.isLocked()) {
					String keyItem = nextRoom.getKeyItem();
					if (inventory.contains(keyItem)) {
						nextRoom.unlockRoom();
						currentRoom = nextRoomName;
						gameTextArea.append("You use the " + keyItem + " to unlock the " + nextRoomName + ".\n");
						gameTextArea.append("You move " + command + " and enter the " + nextRoomName + ".\n");
						gameTextArea.append(nextRoom.getDescription() + "\n");
					} else {
						gameTextArea.append(
								"The " + nextRoomName + " is locked. You need a " + keyItem + " to unlock it.\n");
					}
				} else {
					currentRoom = nextRoomName;
					gameTextArea.append("You move " + command + " and enter the " + nextRoomName + ".\n");
					gameTextArea.append(nextRoom.getDescription() + "\n");
				}
			} else {
				gameTextArea.append("You can't go that way.\n");
			}
			break;

		case "take":
			if (words.length > 1) {
				String itemName = words[1];
				if (room.hasItem(itemName)) {
					inventory.add(itemName);
					room.removeItem(itemName);
					gameTextArea.append("You picked up: " + itemName + "\n");
				} else {
					gameTextArea.append("There is no " + itemName + " here.\n");
				}
			} else {
				gameTextArea.append("Take what?\n");
			}
			break;

		default:
			gameTextArea.append(
					"Unknown command. Try 'look around', 'talk to nurse ghost', 'inventory', or a direction.\n");
		}
	}

	private void restartGame() {
		initializeRooms();
		currentRoom = "lobby";
		inventory.clear();
		ghostItemTaken = false;
		gameTextArea.setText("Welcome to the escape game!\n");
		gameTextArea.append("You wake up in a creepy hospital lobby...\n");
		gameTextArea.append("What would you like to do?\n");
	}

	private void displayInventory() {
		if (inventory.isEmpty()) {
			gameTextArea.append("Your inventory is empty.\n");
		} else {
			gameTextArea.append("You are carrying:\n");
			for (String item : inventory) {
				gameTextArea.append("- " + item + "\n");
			}
		}
	}

	private String getFullDirection(String input) {
		switch (input.toLowerCase()) {
		case "n":
			return "north";
		case "s":
			return "south";
		case "e":
			return "east";
		case "w":
			return "west";
		case "u":
			return "up";
		case "d":
			return "down";
		default:
			return input;
		}
	}

	private void initializeRooms() {
		rooms = new HashMap<>();

		// Define rooms with descriptions and exits
		Room lobby = new Room("lobby",
				"You stand in the abandoned hospital lobby. Dim lights flicker, casting shadows over dusty wheelchairs.",
				Map.of("north", "hallway", "up", "emergency"));
		Room hallway = new Room("hallway",
				"You are in a long hallway. It's eerily quiet. There's a door to the south, east, west, and up.",
				Map.of("south", "lobby", "east", "surgery room", "west", "general ward", "up", "recovery"));
		Room generalWard = new Room("general ward",
				"You are in the general ward. Rows of empty beds line the walls, their sheets still and untouched.",
				Map.of("east", "hallway", "down", "treatment room"));
		Room surgeryRoom = new Room("surgery room",
				"You are in a cold, sterile operating room. Tools are scattered everywhere.",
				Map.of("west", "hallway", "up", "ICU"));
		Room emergency = new Room("emergency", "You are in the emergency room. The atmosphere is tense.",
				Map.of("down", "lobby"));
		Room ICU = new Room("ICU",
				"You are in the ICU. The machines are eerily silent, and the air is thick with the memory of urgency.",
				Map.of("down", "surgery room"));
		Room treatmentRoom = new Room("treatment room",
				"You are in the treatment room. Cabinets line the walls, and the room is filled with various medical equipment.",
				Map.of("up", "general ward"));
		Room xRayRoom = new Room("x-ray room",
				"You are in the x-ray room. A large machine dominates the center of the room, surrounded by cabinets filled with film and reports.",
				Map.of("south", "general ward"));
		Room nursingStation = new Room("nursing station",
				"You are in the nursing station. Cabinets line the walls, filled with medical supplies.",
				Map.of("east", "hallway"));
		Room pediatric = new Room("pediatric", "You are in the pediatric room. Toys and small beds line the walls.",
				Map.of("west", "hallway"));
		Room isolation = new Room("isolation",
				"You are in the isolation room. The walls are bare, and the atmosphere is tense with confinement.",
				Map.of("north", "lobby"));
		Room recovery = new Room("recovery",
				"You are in the recovery room. It's dim and quiet, with a faint smell of antiseptic in the air.",
				Map.of("down", "hallway"));

		// Lock rooms with key items
		xRayRoom.lockRoom("paper clip");
		generalWard.lockRoom("key");
		ICU.lockRoom("bobby pin");
		nursingStation.lockRoom("light wire");
		hallway.lockRoom("key");
		recovery.lockRoom("axe");
		pediatric.lockRoom("hammer");
		isolation.lockRoom("needle");
		emergency.lockRoom("notebook key");

		// Add items to rooms
		lobby.addItem(new Item("bobby pin", "A small bobby pin."));
		treatmentRoom.addItem(new Item("key", "A small key."));
		recovery.addItem(new Item("safe", "A small safe. It has a key inside."));
		recovery.addItem(new Item("paper clip", "A small paper clip."));
		isolation.addItem(new Item("axe", "A small axe."));
		generalWard.addItem(new Item("hammer", "A small hammer."));
		pediatric.addItem(new Item("needle", "A small needle."));
		nursingStation.addItem(new Item("notebook", "A small notebook."));
		nursingStation.addItem(new Item("notebook key", "A key found inside a notebook."));

		// Add rooms to map
		rooms.put("lobby", lobby);
		rooms.put("hallway", hallway);
		rooms.put("general ward", generalWard);
		rooms.put("surgery room", surgeryRoom);
		rooms.put("recovery", recovery);
		rooms.put("emergency", emergency);
		rooms.put("ICU", ICU);
		rooms.put("treatment room", treatmentRoom);
		rooms.put("x-ray room", xRayRoom);
		rooms.put("nursing station", nursingStation);
		rooms.put("pediatric", pediatric);
		rooms.put("isolation", isolation);
	}

	private void buildWindow() {
		setTitle("Escape Game");
		setLayout(new BorderLayout());

		gameTextArea = new JTextArea();
		gameTextArea.setEditable(false);
		gameTextArea.setFont(new Font("Arial", Font.PLAIN, 14));
		gameTextArea.setWrapStyleWord(true);
		gameTextArea.setLineWrap(true);
		JScrollPane scrollPane = new JScrollPane(gameTextArea);
		add(scrollPane, BorderLayout.CENTER);

		JPanel panel = new JPanel(new GridLayout(2, 2));
		JLabel label = new JLabel("What would you do?");
		playerInputField = new JTextField();
		playerInputField.setForeground(Color.RED); // Set player input text color to red
		executeButton = new JButton("Execute");

		panel.add(label);
		panel.add(playerInputField);
		panel.add(executeButton);

		executeButton.addActionListener(this);
		add(panel, BorderLayout.SOUTH);

		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Game Menu");
		exitMenuItem = new JMenuItem("Exit");
		exitMenuItem.addActionListener(e -> System.exit(0));
		menu.add(exitMenuItem);

		instructionsMenuItem = new JMenuItem("How to Play");
		instructionsMenuItem.addActionListener(e -> showInstructions());
		menu.add(instructionsMenuItem);

		menuBar.add(menu);
		setJMenuBar(menuBar);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 400);
		setLocationRelativeTo(null);
		setVisible(true);

		gameTextArea.append("Welcome to the escape game!\n");
		gameTextArea.append("You wake up in a creepy hospital lobby...\n");
		gameTextArea.append("What would you like to do?\n");
	}

	// Method to show instructions
	private void showInstructions() {
		String instructions = "Explore rooms, find items, and solve puzzles to escape the hospital.\n\n"
				+ "Here are the items you can find in the game:\n" + "- Diamond\n" + "- Key\n" + "- Meds\n"
				+ "- Bright Light\n" + "- Lockpick\n" + "- Notebook\n" + "- Map\n" + "- Supply Cabinet\n"
				+ "- Bandaid\n" + "- Syringe\n\n" + "Commands:\n" + "- 'look around': Inspect your surroundings.\n"
				+ "- 'talk to nurse ghost': Interact with the Nurse Ghost (if present).\n"
				+ "- 'inventory': Check your items.\n"
				+ "- Movement: Use 'north', 'south', 'east', or 'west' to explore.\n"
				+ "- 'restart': Start the game over.";
		JOptionPane.showMessageDialog(this, instructions, "How to Play", JOptionPane.INFORMATION_MESSAGE);
	}

	public static void main(String[] args) {
		new HospitalgameGUI();
	}
}

class Room {
	private final String name;
	private final String description;
	private final Map<String, String> exits;

	public Room(String name, String description, Map<String, String> exits) {
		this.name = name;
		this.description = description;
		this.exits = exits;
	}

	public void removeItem(String itemName) {
		// TODO Auto-generated method stub
		
	}

	public boolean hasItem(String itemName) {
		// TODO Auto-generated method stub
		return false;
	}

	public void unlockRoom() {
		// TODO Auto-generated method stub
		
	}

	public String getKeyItem() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	public void lockRoom(String string) {
		// TODO Auto-generated method stub

	}

	public void addItem(Item item) {
		// TODO Auto-generated method stub

	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Map<String, String> getExits() {
		return exits;
	}
}

