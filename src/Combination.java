public class Combination extends Item {

	private String combinationCode;

	public Combination(String name, String description, String combinationCode) { // constructor with my three parem
		super(name, description); // calls item class,
		this.combinationCode = combinationCode; //store in combination code
	}

	public String getCombinationCode() {
		return combinationCode;
	}

	@Override
	public void use() {
		System.out.println("Use this combination to open a specific safe.");
	}// kk
}
