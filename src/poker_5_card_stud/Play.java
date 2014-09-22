package poker_5_card_stud;
import java.util.*;
public class Play {

	public static void main(String[] args){
		Deck playingdeck = new Deck(3);
		Hand hand1 = new Hand(playingdeck.dealcards(5));
		Hand hand2 = new Hand(playingdeck.dealcards(5));
		Hand[] hands = new Hand[2];
		hands[0]= hand1 ;
		hands[1]= hand2;
		for (int j=0;j<2;j++){
			System.out.print("\n"+j+"\t");
			for(int i=0;i< hands[j].getHand().length;i++){
				System.out.print(hands[j].getHand()[i].getRankOfCard() + " \t");
			}
		}
		System.out.println("\n");
		Hand winner=Hand.judgehand(hands);
		for(int i=0;i< winner.getHand().length;i++){
			System.out.print(winner.getHand()[i].getRankOfCard() + " \t");
		}
		int[] rank = hand1.getRank();

		System.out.println(Arrays.toString(rank));

	}

}
