package poker_5_card_stud;

public class Card {
	private Rank rankOfCard;
	private Suit suitOfCard;
	

	public Card(Rank rankOfCard, Suit suitOfCard) {
		this.rankOfCard = rankOfCard;
		this.suitOfCard= suitOfCard;
	}

	protected Rank getRank() {
		return getRankOfCard();
	}

	protected Suit getSuit() {
		return suitOfCard;
	}

	protected int getNumericRank(){
		int a = 0;
		switch(this.getRankOfCard()){
		case TWO : a=2;break;
		case THREE : a=3;break;
		case FOUR : a=4;break;
		case FIVE: a=5;break;
		case SIX : a=6;break;
		case SEVEN : a=7;break;
		case EIGHT : a=8;break;
		case NINE : a=9;break;
		case TEN : a=10;break;
		case JACK : a=11;break;
		case QUEEN : a=12;break;
		case KING : a=13;break;
		case ACE : a=14;break;
		}
		return a;
	}

	public Rank getRankOfCard() {
		return rankOfCard;
	}
}