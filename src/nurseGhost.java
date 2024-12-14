import java.util.Scanner;

public class nurseGhost extends NPC {
	private int talkCount; // talk counter
	private boolean hasSolvedRiddle; // track riddle completion
	public static final String TYPE = "nurseGhost";

	public nurseGhost() {
		super("nurseGhost", "A scary pale Nurse Ghost flying around the lobby, looking to have a chat.");
		this.talkCount = 0;
		this.hasSolvedRiddle = false;
	}

	@Override
	public Object getType() {
		return TYPE;
	}

	@Override
	public void talk() {
		switch (talkCount) {
		case 0: // Greeting
			say("Greetings, Person. A lost soul, perhaps? I can help you escape, but you must prove your wits.");
			talkCount++;
			break;
		case 1: // Riddle
			if (!hasSolvedRiddle) {
				say("What has a key but no lock, a space but no room, and an entrance but no door?");
				// List the options to the player
				String[] options = { "A keyboard", "A window", "A mailbox" };
				say("Choose the correct answer:");
				for (int i = 0; i < options.length; i++) {
					say((i + 1) + ". " + options[i]);
				}
				// Wait for the player input
				askForAnswer(options);
			} else {
				say("You've already proven your dedication. Is there anything else I can help you with?");
				talkCount = 0; // Reset or continue to other options
			}
			break;
		case 2: // After correct answer
			say("Correct! You have a keen mind. Here, take this.");
			// Give the lockpick item
			Game.getInventory().put("lockpick", new Item("lockpick", "A lockpick for one of the doors"));
			hasSolvedRiddle = true;
			talkCount++; // Proceed to next dialogue
			break;
		default:
			say("I can't seem to recall what we were talking about. Please refresh my memory.");
			break;
		}
	}

	// Ask for the player's answer and process it
	private void askForAnswer(String[] options) {
		Scanner scanner = new Scanner(System.in);
		String playerAnswer = scanner.nextLine().toLowerCase();

		// Check if the answer matches one of the options
		if ("a keyboard".equals(playerAnswer)) {
			talkCount = 2; // Correct answer
			talk(); // Move to the next dialogue
		} else {
			say("Wrong answer! Try again.");
			talkCount = 1; // Stay on the riddle phase
			talk(); // Ask the riddle again
		}
	}

	public void setSolvedRiddle(boolean hasSolvedRiddle) {
		this.hasSolvedRiddle = hasSolvedRiddle;
	}

	public boolean hasSolvedRiddle() {
		return hasSolvedRiddle;
	}

	public boolean hasGivenLockpick() {
		return hasSolvedRiddle; // Return true if the lockpick has been given
	}
}