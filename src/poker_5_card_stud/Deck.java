package poker_5_card_stud;
import java.util.*;

public class Deck{
	ArrayList<Card> deck = new ArrayList<Card>();

	public Deck() {
		Rank[] ranks = Rank.values();
		Suit[] suits = Suit.values();
		for (Rank r : ranks){
			for (Suit u : suits){
				this.deck.add(new Card(r,u));
			}
		}
		//this.Shuffle();
		Collections.shuffle(this.deck);
	}
	public Deck(int numberofdecks) {
		for(int i=0;i<numberofdecks;i++){
			Deck newdeck = new Deck();
			for (int j=0;j<newdeck.numberofcards();j++){
				this.deck.add(newdeck.deck.get(j));
			}
		}
		Collections.shuffle(this.deck);
	}
	
	private void Shuffle(){
		Random rnd = new Random();
		ArrayList<Card> output = new ArrayList<Card>();
		for (int i=this.deck.size();i>0;i--){
			int index = rnd.nextInt(i)-1;
			output.add(this.deck.get(index));
			this.deck.remove(index);
		}
		this.deck = output;
	}
	
	protected Card drawcard(){
		Card out = this.deck.get(this.deck.size()-1);
		this.deck.remove(this.deck.size()-1);
		return out; 
	}
	
	protected Card[] dealcards(int numberOfCards){
		Card[] out = new Card[numberOfCards];
		for (int i=0; i<numberOfCards;i++){
			out[i]= this.drawcard();
		}
		return out;
	}
	
	protected int numberofcards(){
		return this.deck.size();
	}
	

}
