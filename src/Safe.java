public class Safe extends Combination {

	private boolean isOpen;
	private String combination;

	public Safe(String name, String description, String combination) {
		super(name, description, combination);
		this.combination = combination;
		isOpen = false;
	}

	@Override
	public void open() {
		if (!isOpen) { // see if the safe isn't already opened
			Item combinationItem = getInventory("combination"); // gets combination item from inventory
			if (getCombinationCode() != null) { // checks if code is null
				Combination combinationCode = (Combination) combinationItem;
				if (combinationCode.getCombinationCode().equals(this.combination)) { // compares combination with safe
																						// combination code
					isOpen = true; // tells u that its open
					System.out.println("YAYY CONGRATS! you find the sparkling diamond inside! and picked it up."); // you find it
					getInventory(combination).put("diamond", new Item("diamond", "A sparkling diamond"));
				} else {
					Game.print("The combination is incorrect.");
				}
			} else {
				Game.print("You don't have the combination.");
			}
		} else {
			Game.print("The safe is already open.");
		}
	}

	private Item getInventory(String string) {
		
		return null;
	}

	private boolean isOpen() {

		return isOpen;
	}
}
//