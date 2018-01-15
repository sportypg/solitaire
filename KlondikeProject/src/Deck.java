import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * The Deck class represents a shuffled deck of cards. It provides several
 * operations including initialize, shuffle, deal, and check if empty.
 */
public class Deck {

	/**
	 * cards contains all the cards in the deck.
	 */
	protected List<Card> cards;

	/**
	 * size is the number of not-yet-dealt cards. Cards are dealt from the top
	 * (highest index) down. The next card to be dealt is at size - 1.
	 */
	protected int size;

	protected int deckCoordX;

	protected int deckCoordY;

	private JLabel deckLabel;

	/** Width of a card. */
	private static final int CARD_WIDTH = 73;
	/** Height of a card. */
	private static final int CARD_HEIGHT = 97;

	/**
	 * Creates a new <code>Deck</code> instance.<BR>
	 * It pairs each element of ranks with each element of suits, and produces
	 * one of the corresponding card.
	 * 
	 * @param ranks
	 *            is an array containing all of the card ranks.
	 * @param suits
	 *            is an array containing all of the card suits.
	 * @param values
	 *            is an array containing all of the card point values.
	 */
	public Deck(String[] suits, int[] values) {
		cards = new ArrayList<Card>();
		int b=0;
		for (int j = 0; j < suits.length; j++) {
			for (String suitString : suits) {
				cards.add(new Card(suitString, values[j],""));
			}
			if(!(cards.get(b)==null)){
				cards.get(b).setCoord(deckCoordX, deckCoordY);
				b++;
			}
		}

		size = cards.size();
	}

	// *****METHODS******
	public void createDeck() {
		String[] getSuit = new String[] { "h", "c", "s", "d" };
		int[] values = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13 };
		cards = new ArrayList<Card>();
		int b=0;
		for (int a = 0; a < values.length; a++) {
			for (String suitString : getSuit) {
				cards.add(new Card(suitString, values[a],""));
				cards.get(b).setCoord(deckCoordX, deckCoordY);
				b++;
			}
		}
		
		shuffle();
	}

	/**
	 * Determines if this deck is empty (no undealt cards).
	 * 
	 * @return true if this deck is empty, false otherwise.
	 */

	public void draw(Graphics g) {
		Card test;
		g.setColor(new Color(51, 102, 0));
		g.clearRect(deckCoordX, deckCoordY, CARD_WIDTH, CARD_HEIGHT);
		g.fillRect(deckCoordX, deckCoordY, CARD_WIDTH, CARD_HEIGHT);
		// g.drawRect(deckCoordX, deckCoordY, CARD_WIDTH, CARD_HEIGHT);
		try {

			for (int a = 0; a < cards.size(); a++) {
//				System.out.println(a);
//				System.out.println(cards.get(a).toString());
				test = (cards.get(a));
				if (test.getShouldBeDrawn()) {
					BufferedImage img = ImageIO
							.read(getClass().getResourceAsStream(imageFileName(test, test.getSelected())));
					// BufferedImage bi = new
					// BufferedImage(CARD_WIDTH, CARD_HEIGHT,
					// BufferedImage.TYPE_INT_ARGB);
					// Graphics g = bi.getGraphics();
					g.drawImage(img, test.getx(), test.gety(), null);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		// contentPane.add(input.getLabel());
		// input.getLabel().setBounds(input.getx(), input.gety(),CARD_WIDTH,
		// CARD_HEIGHT);
		// input.setSelected(false);
		// String cardImageFileName = imageFileName(input, input.getSelected());
		// URL imageURL = getClass().getResource(cardImageFileName);
		// if (imageURL != null){
		// ImageIcon icon = new ImageIcon(imageURL);
		// input.getLabel().setIcon(icon);
		// input.getLabel().setVisible(true);
		// }
		// else{
		// throw new RuntimeException("Card image not found: \"" +
		// cardImageFileName + "\"");
		// }
	}

	private String imageFileName(Card c, boolean isSelected) {
		String str = "cards/";
		if (c.getFaceUp()==false) {
			return "cards/back1.GIF";
		}

		// SettingRankName
		if (c.getPointValue() < 11 && c.getPointValue() != 1) {
			str += c.getPointValue();
		} else {
			if (c.getPointValue() == 1) {
				str += "ace";
			} else if (c.getPointValue() == 11) {
				str += "jack";
			} else if (c.getPointValue() == 12) {
				str += "queen";
			} else if (c.getPointValue() == 13) {
				str += "king";
			}
		}

		// getSuit
		if (c.getSuit().equals("h")) {
			str += "hearts";
		} else if (c.getSuit().equals("d")) {
			str += "diamonds";
		} else if (c.getSuit().equals("s")) {
			str += "spades";
		} else if (c.getSuit().equals("c")) {
			str += "clubs";
		}

		if (isSelected) {
			str += "S";
		}
		str += ".GIF";
		return str;
	}

	public void addCard(Card input) {
		cards.add(input);
	}

	public void removeCard(int input){
		cards.remove(input);
	}
	
	public Card getCard(int x) {
		Card Answer;
		if(x<0 || x>=cards.size()){
			return null;
		}
		Answer = cards.get(x);
		return Answer;
	}

	public boolean isEmpty() {
		size = cards.size();
		return size == 0;
	}

	public void setCoord(int x, int y) {
		deckCoordX = x;
		deckCoordY = y;
	}

	public int getx() {
		return deckCoordX;
	}

	public int gety() {
		return deckCoordY;
	}

	public int getCardWidth() {
		return CARD_WIDTH;
	}

	public int getCardHeight() {
		return CARD_HEIGHT;
	}

	public List<Card> getCardList(){
		return cards;
	}
	
	/**
	 * Accesses the number of undealt cards in this deck.
	 * 
	 * @return the number of undealt cards in this deck.
	 */
	public int size() {
		size = cards.size();
		return size;
	}

	/**
	 * Randomly permute the given collection of cards and reset the size to
	 * represent the entire deck.
	 */
	public void shuffle() {
		for (int k = cards.size() - 1; k > 0; k--) {
			int howMany = k + 1;
			int start = 0;
			int randPos = (int) (Math.random() * howMany) + start;
			Card temp = cards.get(k);
			cards.set(k, cards.get(randPos));
			cards.set(randPos, temp);
		}
		size = cards.size();
	}

	/**
	 * Deals a card from this deck.
	 * 
	 * @return the card just dealt, or null if all the cards have been
	 *         previously dealt.
	 */
	public Card deal() {
		if (isEmpty()) {
			return null;
		}
		size--;
		Card c = cards.get(size);
		return c;
	}

	/**
	 * Generates and returns a string representation of this deck.
	 * 
	 * @return a string representation of this deck.
	 */
	@Override
	public String toString() {
		String rtn = "size = " + size + "\nUndealt cards: \n";

		for (int k = size - 1; k >= 0; k--) {
			rtn = rtn + cards.get(k);
			if (k != 0) {
				rtn = rtn + ", ";
			}
			if ((size - k) % 2 == 0) {
				// Insert carriage returns so entire deck is visible on console.
				rtn = rtn + "\n";
			}
		}

		rtn = rtn + "\nDealt cards: \n";
		for (int k = cards.size() - 1; k >= size; k--) {
			rtn = rtn + cards.get(k);
			if (k != size) {
				rtn = rtn + ", ";
			}
			if ((k - cards.size()) % 2 == 0) {
				// Insert carriage returns so entire deck is visible on console.
				rtn = rtn + "\n";
			}
		}

		rtn = rtn + "\n";
		return rtn;
	}
}
