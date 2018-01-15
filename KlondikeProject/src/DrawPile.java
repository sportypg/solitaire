import java.util.ArrayList;

public class DrawPile extends Deck{

	public DrawPile(String[] suits, int[] values) {
		super(suits, values);
	}
	
	public Card getLastCard(){
		if(this.size()>=1){
			return this.getCard(this.size()-1);
		}
		return null;
	}
	
	public void createDeck() {
		String[] getSuit = new String[] { "h", "c", "s", "d" };
		int[] values = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13 };
		cards = new ArrayList<Card>();
		int b=0;
		for (int a = 0; a < values.length; a++) {
			for (String suitString : getSuit) {
				cards.add(new Card(suitString, values[a],"dpile"));
				cards.get(b).setCoord(this.deckCoordX, this.deckCoordY);
				b++;
			}
		}
		
		shuffle();
	}
}
