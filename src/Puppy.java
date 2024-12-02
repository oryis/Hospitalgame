import java.util.ArrayList;

public class Puppy extends NPC {

	private int talkCount; // talk
	private boolean ranAway;

	public Puppy() {
		super("puppy", "A hideous puppy wags his tail.");
		this.talkCount = 0;
		this.ranAway = false;
	}

	@Override
	public void talk() {
		// if (ranAway) {
		// say("The puppy ran away and doesn't come back.");
		// return;
		// }
		talkCount++;
		switch (talkCount) {
		case 1: // dialog 1
			say("Hi! I'm an adorable puppy!");
			String[] options1 = { "Yes you are! Who's a good boy?", "Ew, no. You're actually kinda hideous." };
			getResponse(options1);
			break;

		case 2: // dialog 2
			say("Hey! Wanna play fetch? Say yes! Say yes!");
			String[] options2 = { "Yes! I love fetch!", "No. I don't like playing fetch or like puppies." };
			getResponse(options2);
			break;

		default: // dialog 3
			say("Yip");
			Game.print("The puppy wags his tail.");
			break;
		}

		// talkCount++;
	}

	@Override
	public void response(int option) {
		switch (talkCount) {
		case 1: // should respond to dialog 1
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

		case 2: // should respond to dialog 2
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
