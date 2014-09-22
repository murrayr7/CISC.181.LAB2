package poker_5_card_stud;

public enum Suit {
	HEART(1), DIAMOND(2), SPADE(3), CLUB(4);

	public final int value;

	private Suit(int value) {
		this.value = value;
	}
};
