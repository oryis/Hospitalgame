import java.util.HashMap;

public class World {

	// Using HashMap to store rooms (key = room name, value = Place object)
	public static HashMap<String, Place> rooms = new HashMap<>();

	public static Place buildWorld() {
		// Create all Place objects with descriptions
		Place lobby = new Place("lobby");
		Place treatmentRoom = new Place("treatmentRoom");
		Place nurseStation = new Place("nurseStation");
		Place restRoom = new Place("restRoom");
		Place surgeryRoom = new Place("surgeryRoom");
		Place suite = new Place("suite");
		Place hallway = new Place("hallway");
		Place xRay = new Place("xRay");
		Place icu = new Place("icu");
		Place pediatric = new Place("pediatric");
		Place recovery = new Place("recovery");
		Place emergency = new Place("emergency");
		Place isolation = new Place("isolation");
		Place generalWard = new Place("generalWard");

		// Add rooms to the HashMap
		rooms.put("lobby", lobby);
		rooms.put("treatmentRoom", treatmentRoom);
		rooms.put("nurseStation", nurseStation);
		rooms.put("restRoom", restRoom);
		rooms.put("surgeryRoom", surgeryRoom);
		rooms.put("suite", suite);
		rooms.put("hallway", hallway);
		rooms.put("xRay", xRay);
		rooms.put("icu", icu);
		rooms.put("pediatric", pediatric);
		rooms.put("recovery", recovery);
		rooms.put("emergency", emergency);
		rooms.put("isolation", isolation);
		rooms.put("generalWard", generalWard);

		// Create items
		Item diamond = new Item("diamond", "A sparkling diamond.");
		Item brightLight = new Item("light", "A bright light.");
		Item notebook = new Item("notebook", "A small notebook.");
		Item key = new Item("key", "A blue metal key.");
		Item lockpick = new Item("lockpick", "A lockpick for one of the doors.");
		Item map = new Item("map", "A map of the hospital.");
		Item meds = new Item("meds", "Unknown medications.");
		Item bandaid = new Item("bandaid", "A simple band-aid.");
		Item syringe = new Item("syringe", "A medical syringe.");

		// Adding new items
		Item paperClip = new Item("paper clip", "A small paper clip.");
		Item bobbyPin = new Item("bobby pin", "A small bobby pin.");
		Item lightWire = new Item("light wire", "A piece of light wire.");
		Item axe = new Item("axe", "A small axe.");
		Item hammer = new Item("hammer", "A small hammer.");
		Item needle = new Item("needle", "A small needle.");
		Item notebookKey = new Item("notebook key", "A key found inside a notebook.");

		// Add items to rooms
		lobby.putItem(diamond);
		treatmentRoom.putItem(key);
		surgeryRoom.putItem(meds);
		restRoom.putItem(brightLight);
		nurseStation.putItem(lockpick);
		suite.putItem(notebook);
		hallway.putItem(map);

		// Add NPCs
		nurseGhost ghost = new nurseGhost();
		Puppy puppy = new Puppy();
		hallway.addNPC(puppy);
		lobby.addNPC(ghost);

		// Add a safe and combination to nurse station
		Safe supplyCabinet = new Safe("A locked cabinet filled with medical supplies.", true);
		Combination combination = new Combination("Combination", "A mysterious combination", "4509");

		nurseStation.putItem(combination);

		supplyCabinet.putItem(bandaid);
		supplyCabinet.putItem(syringe);

		// Add exits between places
		lobby.addExit(suite, 'e');
		restRoom.addExit(suite, 's');
		suite.addExit(restRoom, 'n');
		lobby.addExit(treatmentRoom, 'w');
		treatmentRoom.addExit(lobby, 'e');
		treatmentRoom.addExit(nurseStation, 'n');
		nurseStation.addExit(treatmentRoom, 's');
		surgeryRoom.addExit(hallway, 'e');
		hallway.addExit(surgeryRoom, 'w');
		surgeryRoom.addExit(recovery, 'e');
		recovery.addExit(surgeryRoom, 'w');
		lobby.addExit(hallway, 'n');
		hallway.addExit(lobby, 's');
		hallway.addExit(generalWard, 'e');
		icu.addExit(generalWard, 'w');

		// Return the starting place (lobby)
		return lobby;
	}
}
