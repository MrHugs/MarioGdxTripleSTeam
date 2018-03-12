package utiles;

public enum CollisionBits {

	bala((short) 2), mario((short) 4);

	private short categoryBits;

	private CollisionBits(short categoryBits) {
		this.categoryBits = categoryBits;
	}

	public short getCategoryBits() {
		return categoryBits;
	}

}
