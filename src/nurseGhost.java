import java.util.ArrayList;

public class nurseGhost extends NPC {

	private int talkCount; // talk counter
	private boolean hasSolvedRiddle; // to track riddle completion
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
		case 1: // Initial Greeting
			say("Greetings, Person. A lost soul, perhaps? I can help you escape, but you must prove your wits.");
			talkCount++;
			break;
		case 2: // Riddle Presentation
			if (!hasSolvedRiddle) {
				say("What has a key but no lock, a space but no room, and an entrance but no door?");
				String[] options = { "A keyboard", "A window", "A mailbox" }; // s
				getResponse(options);
			} else {
				say("You've already proven your dedication. Is there anything else I can help you with?");
			}
			break;
		case 3: // Handling Player Input
			say("Correct! You have a keen mind. Here, take this.");
			Game.getInventory().put("lockpick", new Item("lockpick", "A lockpick for one of the doors"));
			hasSolvedRiddle = true;
			talkCount++; // Move to next dialogue
			break;
		default: // default
			// other dialogue
			say("I can't seem to recall what we were talking about. please  refresh my memory.");
			break;
		}
	}

	public void setSolvedRiddle(boolean hasSolvedRiddle) {
	    this.hasSolvedRiddle = hasSolvedRiddle;
	}

	public boolean hasSolvedRiddle() {
	    return hasSolvedRiddle;
	}

	public boolean hasGivenLockpick() {
		// TODO Auto-generated method stub
		return false;
	}
}
