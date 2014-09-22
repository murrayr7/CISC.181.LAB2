package poker_5_card_stud;
import java.util.*;
public class Hand implements Comparable<Hand>  {
	private Card[] hand;
	/*rank will be an int array describing the rank of the hand
	 * all rank indices will be low to high (0 meaning "meaningless" and 1 being the lowest possible score)
	 * the first index indicates overall rank of the hand (e.g. royal flush, straight, ect) (e.g. 1=No pair/High Card)
	 * the second index indicates the rank of the "high hand" (4's in a [4,4,4,5,5] hand or 7's in [7,7,5,5,K]
	 * the third index indicates the rank of the "low hand" (5's in a [4,4,4,5,5] hand or 5's in [7,7,5,5,K]
	 * the fourth index indicates the rank of the highest card not used in either the "high hand" or "low hand"
	 */
	private int[] rank={0,0,0,0};
	private ArrayList<Integer> cardsUsed = new ArrayList<Integer>();

	public Hand(Card[] hand) {
		this.hand = hand;
	}


	public static void judgehand(Hand input){
		input.sortHandRank();
		if(input.isRoyalFlush()){
			input.rank[0]=HandCategory.royalFlush.rankNumber;
		}else if(input.isStraightFlush()){
			input.rank[0]=HandCategory.straightFlush.rankNumber;
			if((input.hand[0].getNumericRank()==2)&(input.hand[input.hand.length-1].getNumericRank()==Rank.ACE.value)){
				input.rank[1]=1;
			}
			input.rank[1]=input.hand[0].getNumericRank();
		}else if(input.isNumberOfKind(4)){
			input.rank[0]=HandCategory.fourOfAKind.rankNumber;
			input.rank[1]=input.hand[input.cardsUsed.get(0)].getNumericRank();
		}else if(input.isFullHouse()){
			input.rank[0]=HandCategory.fullHouse.rankNumber;
			if(input.hand[0].getNumericRank()!=input.hand[2].getNumericRank()){
				input.rank[1]=input.hand[2].getNumericRank();
				input.rank[2]=input.hand[0].getNumericRank();
			}else{
				input.rank[1]=input.hand[0].getNumericRank();
				input.rank[2]=input.hand[2].getNumericRank();
			}
		}else if(input.isFullHouse()){
			input.rank[0]=HandCategory.fullHouse.rankNumber;
			if(input.hand[0].getNumericRank()!=input.hand[2].getNumericRank()){
				input.rank[1]=input.hand[2].getNumericRank();
				input.rank[2]=input.hand[0].getNumericRank();
			}else{
				input.rank[1]=input.hand[0].getNumericRank();
				input.rank[2]=input.hand[2].getNumericRank();
			}
		}else if(input.isFlush()){
			input.rank[0]=HandCategory.flush.rankNumber;
			input.rank[1]=input.hand[0].getNumericRank();
		}else if(input.isStraight()){
			input.rank[0]=HandCategory.straight.rankNumber;
			if((input.hand[0].getNumericRank()==2)&(input.hand[input.hand.length-1].getNumericRank()==Rank.ACE.value)){
				input.rank[1]=1;
			}
			input.rank[1]=input.hand[0].getNumericRank();
		}else if(input.isNumberOfKind(3)){
			input.rank[0]=HandCategory.threeOfAKind.rankNumber;
			input.rank[1]=input.hand[input.cardsUsed.get(0)].getNumericRank();
		}else if(input.isTwoPair()){
			input.rank[0]=HandCategory.twoPair.rankNumber;
			for(int i:input.cardsUsed){
				if(input.rank[1]<input.getHand()[i].getNumericRank()){
					input.rank[2]=input.rank[1];
					input.rank[1]=input.getHand()[i].getNumericRank();
				}else if( input.getHand()[i].getNumericRank()!=input.rank[1]  &(input.rank[2]<input.getHand()[i].getNumericRank()) ){
					input.rank[2]=input.getHand()[i].getNumericRank();
				}
			}
		}else if(input.isNumberOfKind(2)){
			input.rank[0]=HandCategory.onePair.rankNumber;
			input.rank[1]=input.hand[input.cardsUsed.get(0)].getNumericRank();
		}else{
			input.rank[0]=HandCategory.noPair.rankNumber;
			input.rank[3]=input.hand[input.hand.length-1].getNumericRank();
		}

		for(int i=0;i<input.hand.length;i++){
			if(input.rank[3]<input.hand[i].getNumericRank()){
				boolean condition1 = false;
				for(int j=0;j<input.cardsUsed.size();j++){
					condition1=condition1 | (input.cardsUsed.get(j)!=i);
				}
				if(condition1)
					input.rank[3]=input.hand[i].getNumericRank();
			}
		}
	}


	public static Hand judgehand(Hand[] input){
		for(int i =0; i<input.length;i++){
			judgehand(input[i]);
		}
		Arrays.sort(input);
		return input[0];

	}

	public void sortHandRank(){
		Arrays.sort(this.hand,new Comparator<Card>()
				{
					@Override
					public int compare(Card o1, Card o2) {
						if(o1.getNumericRank()>o2.getNumericRank())
							return 1;
						else if(o1.getNumericRank()<o2.getNumericRank())
							return-1;
						else
							return 0;
					}
			
				});
	}
	
	public boolean isFlush(){
		for(int i=0;i<this.hand.length-1;i++){
			if(!this.hand[i].getSuit().equals(this.hand[i+1].getSuit()))
				return false;
		}
		return true;
	}

	public boolean isStraight(){
		this.sortHandRank();
		if( ((this.hand[0]).getNumericRank() == 2) && ((this.hand[this.hand.length-1]).getNumericRank()==14)  ){
			for(int i=0;i<this.hand.length-2;i++){
				if ( this.hand[i].getNumericRank() != (this.hand[i+1].getNumericRank()-1)){
					return false;
				}
			}
		} else{
			for(int i=0;i<this.hand.length-1;i++){
				if ( this.hand[i].getNumericRank() != (this.hand[i+1].getNumericRank()-1))
					return false;
			}
		}
		return true;
	}

	public boolean isRoyalFlush(){
		this.sortHandRank();
		return ((this.isFlush()&this.isStraight())&(this.hand[0].getNumericRank()==10));
	}

	public boolean isNumberOfKind(int numberOfKind){
		this.sortHandRank();
		int iterations=this.hand.length-numberOfKind+1;
		boolean condition = false;
		int i=1;
		while((i<=iterations)){
			boolean cond = (this.hand[i-1].getNumericRank()==this.hand[i+numberOfKind-2].getNumericRank());
			if((i)!=iterations){
				cond = cond & (this.hand[i-1].getNumericRank()!=this.hand[i+numberOfKind-1].getNumericRank());
			}
			if(i>1){
				cond = cond & (this.hand[i-1].getNumericRank()!=this.hand[i-2].getNumericRank());
			}
			if(cond){
				for(int j=i; j<numberOfKind;j++){
					cardsUsed.add(j);
				}
				return cond;
			}
			i++;
		}
		return condition;
	}

	public boolean isTwoPair(){
		this.sortHandRank();
		boolean condition1=false;
		for(int i=0;i<this.hand.length-1;i++){
			if(( !condition1 && (this.hand[i].getNumericRank()==this.hand[i+1].getNumericRank())  )){
				condition1=true;
				this.cardsUsed.add(i);this.cardsUsed.add(i+1);
			}else if( (condition1) & (this.hand[i].getNumericRank()==this.hand[i+1].getNumericRank())){
				this.cardsUsed.add(i);this.cardsUsed.add(i+1);
				return true;
			}
		}
		return false;
	}

	public boolean isFullHouse(){
		return (this.isNumberOfKind(2)&this.isNumberOfKind(3));
	}

	public boolean isStraightFlush(){
		return this.isFlush() & this.isStraight();
	}

	@Override
	public int compareTo(Hand otherHand) {
		for(int i=0;i<4;i++){
			if(this.rank[i]>otherHand.rank[i]){
				return -1;
			}else if(this.rank[i]<otherHand.rank[i]){
				return 1;
			}
		}
		return 0;
	}


	protected Card[] getHand() {
		return hand;
	}


	protected void setHand(Card[] hand) {
		this.hand = hand;
	}


	protected int[] getRank() {
		return rank;
	}

	
}
