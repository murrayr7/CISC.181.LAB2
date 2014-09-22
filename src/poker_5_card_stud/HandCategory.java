package poker_5_card_stud;

public enum HandCategory {
	noPair(1),onePair(2),twoPair(3),threeOfAKind(4),straight(5),flush(6),fullHouse(7),fourOfAKind(8),
	straightFlush(9), royalFlush(10);
	public final int rankNumber;
	

	private HandCategory(int rankNumber) {
		this.rankNumber = rankNumber;
	}
}
