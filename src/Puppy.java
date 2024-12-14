import java.util.ArrayList;

public class Puppy extends NPC {

	private int talkCount; // talk count
	private boolean ranAway;

	public Puppy() {
		super("puppy", "A hideous puppy wags his tail.");
		this.talkCount = 0;
		this.ranAway = false;
	}

	@Override
	public Object getType() {
		return "puppy";
	}

	public void talk() {
		if (ranAway) {
			say("The puppy ran away and doesn't come back.");
			return; // Exit the method if the puppy has run away
		}

		talkCount++;
		switch (talkCount) {
		case 1:
			say("Hi! I'm an adorable puppy!");
			getResponse(new String[] { "Yes you are! Who's a good boy?", "Ew, no. You're actually kinda hideous." });
			break;
		case 2:
			say("Hey! Wanna play fetch? Say yes! Say yes!");
			getResponse(new String[] { "Yes! I love fetch!", "No. I don't like playing fetch or like puppies." });
			break;
		default:
			say("Yip");
			Game.print("The puppy wags his tail.");
			break;
		}
	}

	public void say(String message) {
		Game.print(message); // Use the Game class's print method
	}

	public void response(int option) {
		switch (talkCount) {
		case 1: // respond to dialog 1
			switch (option) {
			case 1:
				say("I am! I'm a good boy!");
				break;
			case 2:
				say("I am adorable! Why are you so mean?");
				Game.print("The puppy bites you. You deserve it.");
				break;
			}
			break;
		case 2: // respond to dialog 2
			switch (option) {
			case 1:
				say("Yay! Fetch!");
				Game.print("The puppy runs off and returns with a ball.");
				Game.getInventory().put("ball", new Item("ball", "A slobbery ball fetched by the puppy."));
				break;
			case 2:
				say("You're a bad person! I don't like you!");
				Game.print("The puppy runs away and doesn't come back.");
				ranAway = true;
				break;
			}
			break;
		}
	}
}
