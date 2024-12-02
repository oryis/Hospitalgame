public class World {

	public static Place buildWorld() {
		Place lobby = new Place("lobby", "You are in the Lobby.");
		Place treatmentPlace = new Place("treatmentPlace", "You are in the Treatment Room.");
		Place nursestation = new Place("nursestation", "You are in the Nurse Station. ");
		Place restPlace = new Place("restPlace", "You are in the Rest Place.");
		Place surgeryPlace = new Place("surgeryPlace", "You are in the Surgery Room.");
		Place suite = new Place("suite", "You are in the Patient Suite");
		Place hallway = new Place("Hallway", "You are in the Hallway");
		Place xRay = new Place("xRay", "You are in the X-Ray Place");
		Place icu = new Place("icu", "You are in the ICU");
		Place pediatric = new Place("pediatric", "You are in the Pediatric Room");
		Place recovery = new Place("recovery", "You are in the Recovery Room");
		Place emergency = new Place("emergency", "You are in the X-Ray Room");
		Place isolation = new Place("isolation", "You are in the Isolation Room");
		Place generalWard = new Place("GeneralWard", "You are The General staff Room");
		// set items and description

		Item diamond = new Item("diamond", "A sparkling diamond");
		Item brightlight = new Item("Light", "Bright light");
		Item notebook = new Item("notebook", "A small notebook");
		Item key = new Item("key", "blue metal key"); // adding items to Places
		Item lockpick = new Item("lockpick", "A lockpick for one of the doors");
		Item map = new Item("map", "A Map of the Hosiptal");
		Item meds = new Item("meds", "unknown meds");
		

		// add items to Place
		lobby.putItem(diamond);
		treatmentPlace.putItem(key);
		surgeryPlace.putItem(meds);
		restPlace.putItem(brightlight);
		nursestation.putItem(lockpick);
		suite.putItem(notebook);
		hallway.putItem(map);
		
		Puppy Puppy = new Puppy();
		hallway.addNPC(Puppy);

		
		// my safe and combination
		Safe supplyCabinet = new Safe("Supply Cabinet", "A locked cabinet filled with medical supplies.", "");
		Combination combination = new Combination("Combination", "A mysterious combination", "4509"); // did this wrong
																										// x.x

		// safe and combination Place
		nursestation.addItem(supplyCabinet);
		nursestation.addItem(combination);

		lobby.addExit(suite, 'e'); // lobby connects to patient suite

		restPlace.addExit(suite, 's'); // restPlace connects to suite
		suite.addExit(restPlace, 'n'); // suite connects to restPlace

		lobby.addExit(treatmentPlace, 'w'); // lobby connects to treatment
		treatmentPlace.addExit(lobby, 'e');// treatment connects to lobby

		treatmentPlace.addExit(nursestation, 'n'); // TREATMENT connects to nurse
		treatmentPlace.setLocked(true);

		nursestation.addExit(treatmentPlace, 's'); // nurse to treatment

		surgeryPlace.addExit(hallway, 'e'); // surgery connects to hallway
		hallway.addExit(surgeryPlace, 'w'); // hallway connects to surgery

		surgeryPlace.addExit(recovery, 'e'); // surgery connects to recovery?
		recovery.addExit(surgeryPlace, 'w'); // recovery connect to surgery?

		treatmentPlace.addExit(nursestation, 'n'); // TREATMENT connects to nurse
		treatmentPlace.setLocked(true);

		lobby.addExit(hallway, 'u'); // lobby connects to hallway if u go up
		hallway.addExit(lobby, 'd'); // hallway connects to lobby going down

		hallway.addExit(generalWard, "e");
		icu.addExit(generalWard, "w");
		return lobby; // lobby
	}

}
