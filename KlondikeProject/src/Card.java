import java.awt.Point;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

public class Card {

	/**
	 * String value that holds the suit of the card
	 */
	private String suit;

	/**
	 * int value that holds the point value.
	 */
	private int pointValue;

	private boolean red;

	private boolean faceUp;

	private boolean selected;

	private int cardCoordX;

	private int cardCoordY;

	private boolean shouldBeDrawn;

	private JLabel cardLabel;
	
	private String deckType;

	/**
	 * Creates a new <code>Card</code> instance. this.faceUp= false; if(suit==
	 * "h" || suit== "d"){ red = true;
	 * 
	 * @param cardRank
	 *            a <code>String</code> value containing the rank of the card
	 * @param cardSuit
	 *            a <code>String</code> value containing the suit of the card
	 * @param cardPointValue
	 *            an <code>int</code> value containing the point value of the
	 *            card
	 */
	public Card(String cardSuit, int cardPointValue, String cardDeckType) {
		// initializes a new Card with the given rank, suit, and point value
		deckType = cardDeckType;
		suit = cardSuit;
		pointValue = cardPointValue;
		this.faceUp = false;
		if (suit == "h" || suit == "d") {
			red = true;
		} else {
			red = false;
		}
		this.shouldBeDrawn = false;
		this.selected = false;
	}

	/**
	 * Accesses this <code>Card's</code> suit.
	 * 
	 * @return this <code>Card's</code> suit.
	 */

	public boolean getRed(){
		return red;
	}
	
	
	public void setDeckType(String input){
		deckType = input;
	}
	
	public String getDeckType(){
		return deckType;
	}
	
	public void setFaceUp(boolean input){
		faceUp = input;
	}
	
	public void setShouldBeDrawn(boolean input) {
		shouldBeDrawn = input;
	}

	public boolean getShouldBeDrawn() {
		return shouldBeDrawn;
	}

	public void setSelected(boolean input) {
		selected = input;
	}

	public JLabel getLabel() {
		return cardLabel;
	}

	public boolean getFaceUp() {
		return faceUp;
	}

	public boolean getSelected() {
		return selected;
	}

	public String getSuit() {
		return suit;
	}

	public void setCoord(int x, int y) {
		cardCoordX = x;
		cardCoordY = y;
	}

	public int getx() {
		return cardCoordX;
	}

	public int gety() {
		return cardCoordY;
	}

	/**
	 * Accesses this <code>Card's</code> point value.
	 * 
	 * @return this <code>Card's</code> point value.
	 */
	public int getPointValue() {
		return pointValue;
	}

	/**
	 * Compare this card with the argument.
	 * 
	 * @param otherCard
	 *            the other card to compare to this
	 * @return true if the rank, suit, and point value of this card are equal to
	 *         those of the argument; false otherwise.
	 */
	public boolean matches(Card otherCard) {
		return otherCard.getSuit().equals(this.getSuit()) && otherCard.getPointValue() == this.getPointValue();
	}

	/**
	 * Converts the rank, suit, and point value into a string in the format
	 * "[Rank] of [Suit] (point value = [PointValue])". This provides a useful
	 * way of printing the contents of a <code>Deck</code> in an easily readable
	 * format or performing other similar functions.
	 *
	 * @return a <code>String</code> containing the rank, suit, and point value
	 *         of the card.
	 */
	@Override
	public String toString() {
		return pointValue + " of " + suit + " in " + deckType;
	}

}
