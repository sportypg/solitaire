import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.event.MouseListener;


public class KlondikeBoard {
	
	
	Card select2;
	Card select1;
	DrawPile dpile;
	PlayingPile ppile1;
	PlayingPile ppile2;
	PlayingPile ppile3;
	PlayingPile ppile4;
	PlayingPile ppile5;
	PlayingPile ppile6;
	PlayingPile ppile7;
	LayPile lpile;
	FinalPile fpile1;
	FinalPile fpile2;
	FinalPile fpile3;
	FinalPile fpile4;
	ArrayList<PlayingPile> playingPiles = new ArrayList<PlayingPile>();
	ArrayList<FinalPile> finalPiles = new ArrayList<FinalPile>();

	public void move(){
		if(select1.getDeckType().equals("ppile") && select2.getDeckType().equals("ppile")){
			if((select1.getPointValue()+1 == select2.getPointValue())||(select2.getPointValue()==0)){
				if((select1.getRed()==!(select2.getRed()))||(select2.getSuit().equals("w"))){
//					System.out.println("GOOD MATCH");
//					System.out.println(select1.toString());
//					System.out.println(select2.toString());
					
					int a1 = cardPPileLocation(select1,"a");
					int b1 = cardPPileLocation(select1,"b");
					int a2 = cardPPileLocation(select2,"a");
					int b2 = cardPPileLocation(select2,"b");
					int a1size = playingPiles.get(a1).size();
					
					if(select2.getSuit().equals("w")){
						playingPiles.get(cardPPileLocation(select2,"a")).removeCard(0);
					}
					
					
//					System.out.println(a1 + " " + " " +b1 + " " + a2 + " " + b2);
					
					
					for(int a = 0; a<a1size-b1; a++){
//						System.out.println("boop" + a);
						playingPiles.get(a1).getCard(b1).setCoord(playingPiles.get(a2).getx(),
								(int)(playingPiles.get(a2).gety() + (0.25*playingPiles.get(a2).getCardHeight()*playingPiles.get(a2).size())));
//						System.out.println("meep");
						playingPiles.get(a2).addCard(playingPiles.get(a1).getCard(b1));
						playingPiles.get(a1).removeCard(b1);
//						System.out.println(playingPiles.get(a2).size());
//						System.out.println(playingPiles.get(a1).size());
//						System.out.println("test" + a);
					}
					if(playingPiles.get(a1).size() == 0){
						System.out.println(a1);
						playingPiles.get(a1).addCard(new Card("w",14,"ppile"));
						
						playingPiles.get(a1).getCard(0).setCoord(playingPiles.get(a1).getx(),playingPiles.get(a1).gety());
						System.out.println(playingPiles.get(a1).getx());
						System.out.println(playingPiles.get(a1).gety());
					}
					playingPiles.get(a1).getCard(playingPiles.get(a1).size()-1).setFaceUp(true);				
				}
				else{
					System.out.println("BAD MATCH");
				}
			}
			else{
				System.out.println("BAD MATCH");
			}
		}
		else{
		}
		
		if(select1.getDeckType().equals("lpile") && select2.getDeckType().equals("ppile")){
			if((select1.getPointValue()+1 == select2.getPointValue())||(select2.getPointValue()==0)){
				if((select1.getRed()==!(select2.getRed()))||(select2.getSuit().equals("w"))){
					int a2 = cardPPileLocation(select2,"a");
					int b2 = cardPPileLocation(select2,"b");
					
					if(select2.getSuit().equals("w")){
						playingPiles.get(cardPPileLocation(select2,"a")).removeCard(0);
					}
					
			
					cardLpileLocation(select1).setDeckType("ppile");
					cardLpileLocation(select1).setCoord(playingPiles.get(a2).getx(),
							(int)(playingPiles.get(a2).gety() + (0.25*playingPiles.get(a2).getCardHeight()*playingPiles.get(a2).size())));
					cardLpileLocation(select1).setSelected(false);
					playingPiles.get(a2).addCard(cardLpileLocation(select1));
					lpile.removeCard(lpile.size()-1);
					System.out.println("SUP");
					
					if(lpile.size()>=3){
						for(int a = 0; a < 3; a++){
							lpile.getCard(lpile.size()-1-a).setShouldBeDrawn(true);
							lpile.getCard(lpile.size()-1-a).setCoord((int)(lpile.getx() + (2-a)*0.18*lpile.getCardWidth()), lpile.gety());
						}
					}
				}

			}
				
		}
		if(select1.getDeckType().equals("ppile") && select2.getDeckType().equals("fpile")){
			if((select1.getPointValue()-1 == select2.getPointValue())){
				if((select1.getSuit().equals(select2.getSuit()))||(select2.getSuit().equals("w"))){
					if(cardPPileLocation(select1, "b") ==playingPiles.get(cardPPileLocation(select1, "a")).size()-1){
						
						
						int a1 = cardPPileLocation(select1,"a");
						int b1 = cardPPileLocation(select1,"b");
						playingPiles.get(a1).getCard(b1).setCoord(finalPiles.get(cardFPileLocation(select2,"a")).getx(),finalPiles.get(cardFPileLocation(select2,"a")).gety());
						finalPiles.get(cardFPileLocation(select2,"a")).getCard(finalPiles.get(cardFPileLocation(select2,"a")).size()-1).setShouldBeDrawn(false);
						finalPiles.get(cardFPileLocation(select2,"a")).addCard(playingPiles.get(a1).getCard(b1));
						finalPiles.get(cardFPileLocation(select2,"a")).getCard(finalPiles.get(cardFPileLocation(select2,"a")).size()-1).setDeckType("fpile");
						if(select2.getSuit().equals("w")){
							finalPiles.get(cardFPileLocation(select2,"a")).removeCard(0);
						}
						System.out.println("--" + finalPiles.get(0).size());
						playingPiles.get(a1).removeCard(b1);
						
						if(playingPiles.get(a1).size() == 0){
							playingPiles.get(a1).addCard(new Card("w",14,"ppile"));
							playingPiles.get(a1).getCard(0).setCoord(playingPiles.get(a1).getx(),playingPiles.get(a1).gety());
							
						}
						if(!(playingPiles.get(a1).getCard(playingPiles.get(a1).size()-1).getFaceUp())){
							playingPiles.get(a1).getCard(playingPiles.get(a1).size()-1).setFaceUp(true);
						}
					}
					
					else{
						System.out.println("not bottom card");
					}												
				}
			}
		}
		if(select1.getDeckType().equals("lpile") && select2.getDeckType().equals("fpile")){
			if((select1.getPointValue()-1 == select2.getPointValue())){
				if((select1.getSuit().equals(select2.getSuit()))||(select2.getSuit().equals("w"))){
					lpile.getCard(lpile.size()-1).setCoord(finalPiles.get(cardFPileLocation(select2,"a")).getx(),finalPiles.get(cardFPileLocation(select2,"a")).gety());
					finalPiles.get(cardFPileLocation(select2,"a")).getCard(finalPiles.get(cardFPileLocation(select2,"a")).size()-1).setShouldBeDrawn(false);
					finalPiles.get(cardFPileLocation(select2,"a")).addCard(lpile.getCard(lpile.size()-1));
					finalPiles.get(cardFPileLocation(select2,"a")).getCard(finalPiles.get(cardFPileLocation(select2,"a")).size()-1).setDeckType("fpile");
					if(select2.getSuit().equals("w")){
						finalPiles.get(cardFPileLocation(select2,"a")).removeCard(0);
					}
					lpile.removeCard(lpile.size()-1);
					
					if(lpile.size()>=3){
						for(int a = 0; a < 3; a++){
							lpile.getCard(lpile.size()-1-a).setShouldBeDrawn(true);
							lpile.getCard(lpile.size()-1-a).setCoord((int)(lpile.getx() + (2-a)*0.18*lpile.getCardWidth()), lpile.gety());
						}
					}
				}
			}
		}

	}
	
	public int cardPPileLocation(Card input, String letter){
		for(int a = 0; a< playingPiles.size();a++){
			for(int b = 0; b<playingPiles.get(a).size();  b++){
				if((playingPiles.get(a).getCard(b).getx()==input.getx()) && (playingPiles.get(a).getCard(b).gety()==input.gety())){
					if(letter.equals("a")){
						return a;
					}
					if(letter.equals("b")){
						return b;
					}
				}
			}
		}
		return 0;
	}
	
	public void setUpBoard() {
		String[] emptysuit = new String[0];
		int[] emptyvalues = new int[0];

		// Initalize Piles
		dpile = new DrawPile(emptysuit, emptyvalues);
		lpile = new LayPile(emptysuit, emptyvalues);
		ppile1 = new PlayingPile(emptysuit, emptyvalues);
		ppile2 = new PlayingPile(emptysuit, emptyvalues);
		ppile3 = new PlayingPile(emptysuit, emptyvalues);
		ppile4 = new PlayingPile(emptysuit, emptyvalues);
		ppile5 = new PlayingPile(emptysuit, emptyvalues);
		ppile6 = new PlayingPile(emptysuit, emptyvalues);
		ppile7 = new PlayingPile(emptysuit, emptyvalues);
		playingPiles.addAll(Arrays.asList(ppile1, ppile2, ppile3, ppile4, ppile5, ppile6, ppile7));
		fpile1 = new FinalPile(emptysuit, emptyvalues);
		fpile2 = new FinalPile(emptysuit, emptyvalues);
		fpile3 = new FinalPile(emptysuit, emptyvalues);
		fpile4 = new FinalPile(emptysuit, emptyvalues);
		finalPiles.addAll(Arrays.asList(fpile1, fpile2, fpile3, fpile4));
		
		//SetPileCoords
		dpile.setCoord(
				(int)(dpile.getCardHeight()),
				(int)(dpile.getCardWidth())
				);
		for (int a = 0; a < playingPiles.size(); a++) {
			playingPiles.get(a).setCoord(
					(int)(dpile.getCardHeight() + a * 1.25 * dpile.getCardWidth()),
					(int)(dpile.getCardWidth() + dpile.getCardHeight() + (0.7 * dpile.getCardHeight()))
					);
		}
		
		for (int a = 0; a < finalPiles.size(); a++){
			finalPiles.get(a).setCoord(
					(int)(dpile.getCardHeight() + (a+3) * 1.25 * dpile.getCardWidth()),
					(int)(dpile.getCardWidth())
					);
		}
		
		lpile.setCoord(
				(int)(lpile.getCardHeight() + 1.25 * lpile.getCardWidth()),
				(int)(lpile.getCardWidth())
				);
		
		dealBoard();
	}
	
	private void dealBoard(){
		
		//Setting Cards
		dpile.createDeck();
		finalPiles.get(0).addCard(new Card("w",0,"fpile"));
		finalPiles.get(0).getCard(0).setCoord(finalPiles.get(0).getx(), finalPiles.get(0).gety());
		finalPiles.get(1).addCard(new Card("w",0,"fpile"));
		finalPiles.get(1).getCard(0).setCoord(finalPiles.get(1).getx(), finalPiles.get(1).gety());
		finalPiles.get(2).addCard(new Card("w",0,"fpile"));
		finalPiles.get(2).getCard(0).setCoord(finalPiles.get(2).getx(), finalPiles.get(2).gety());
		finalPiles.get(3).addCard(new Card("w",0,"fpile"));
		finalPiles.get(3).getCard(0).setCoord(finalPiles.get(3).getx(), finalPiles.get(3).gety());
		
		//Dealing cards
		for(int a = 0; a < playingPiles.size(); a++){
			for(int b = -1; b < a; b++){				
				playingPiles.get(a).addCard(dpile.getLastCard());
				dpile.getLastCard().setShouldBeDrawn(true);
				dpile.getLastCard().setDeckType("ppile");
				dpile.getLastCard().setCoord(
						(int)(playingPiles.get(a).getx()), 
						(int)(playingPiles.get(a).gety() + (b+1)*0.25*playingPiles.get(a).getCardHeight())
						);
				if(b==a-1){
					dpile.getLastCard().setFaceUp(true);

				}
				dpile.removeCard(dpile.size()-1);
			}
		}
		dpile.getLastCard().setShouldBeDrawn(true);
	}
	
	public void draw(Graphics g) {
//		Card test = new Card("h", 12);
//		test.setCoord(dpile.getCardHeight() * 4, dpile.getCardWidth() * 4);
//		test.setShouldBeDrawn(false);
//		dpile.addCard(test);
		dpile.draw(g);
		lpile.draw(g);
		for (PlayingPile ppile : playingPiles) {
			ppile.draw(g);
		}
		for (FinalPile fpile : finalPiles)	{
			fpile.draw(g);
		}
	}

	public Card cardLpileLocation(Card input){
		if(lpile.getCard(lpile.size()-1).getx() == input.getx() && lpile.getCard(lpile.size()-1).gety()==input.gety()){
			return lpile.getCard(lpile.size()-1);
		}
		return null;
	}

	public void resetSelect(){
		if(select1.getDeckType().equals("ppile")){
			playingPiles.get(cardPPileLocation(select1, "a")).getCard(cardPPileLocation(select1, "b")).setSelected(false);
		}
		if(select2.getDeckType().equals("ppile")){
			playingPiles.get(cardPPileLocation(select2, "a")).getCard(cardPPileLocation(select2, "b")).setSelected(false);

		}
		if(select2.getDeckType().equals("lpile")){
			cardLpileLocation(select2).setSelected(false);
		}
		if(select2.getDeckType().equals("fpile")){
//			System.out.println(cardFPileLocation(select2,"a"));
			System.out.println(cardFPileLocation(select2,"b"));
			finalPiles.get(cardFPileLocation(select2, "a")).getCard(cardFPileLocation(select2, "b")).setSelected(false);
		}
	}
	
	public int cardFPileLocation(Card input, String letter){
		int fpilesize;
		for(int a = 0; a< finalPiles.size();a++){
			fpilesize = finalPiles.get(a).size();
			System.out.println(a + input.toString() + letter);
			System.out.println(".-." + fpilesize);
			System.out.println(input.getx() + " " + finalPiles.get(a).getCard(fpilesize-1).getx());
				if((finalPiles.get(a).getCard(fpilesize-1).getx()==input.getx()) && (finalPiles.get(a).getCard(fpilesize-1).gety()==input.gety())){
					if(letter.equals("a")){
						return a;
					}
					if(letter.equals("b")){
						return fpilesize-1;
					}
				}
		}
		return 0;
	}
		
	public int clickedAt(MouseEvent arg0, int selectedCards) {
		// TODO Auto-generated method stub
		if(selectedCards == 0){
			runDrawPile(arg0);
			if(!(runLayPile(arg0)==null)){
				select1 = runLayPile(arg0);
				cardLpileLocation(select1).setSelected(true);
				System.out.println("ye");
				return 1;
			}
			if(!(checkPlayingPile(arg0)==null)){
				select1 = checkPlayingPile(arg0);
				if(select1.getSuit().equals("w")){
					return 0;
				}
				System.out.println(select1.toString());
				if(select1.getFaceUp()){
					playingPiles.get(cardPPileLocation(select1, "a")).getCard(cardPPileLocation(select1, "b")).setSelected(!(playingPiles.get(cardPPileLocation(select1, "a")).getCard(cardPPileLocation(select1, "b")).getSelected()));
//					select1.setSelected(!(select1.getSelected()));
					return 1;
				}
			}
		}
		
		if(selectedCards == 1){
			runDrawPile(arg0);
			
			if(!(runFinalPile(arg0)==null)){
				select2 = runFinalPile(arg0);
				System.out.println("LOL");
				return 1;
			}
			if(!(runLayPile(arg0)==null)){
				select2 = runLayPile(arg0);
				resetSelect();
				return -1;
			}			
			if(!(checkPlayingPile(arg0)==null)){
				select2 = checkPlayingPile(arg0);
				System.out.println(select2.toString());
				if(select2.getFaceUp()){
					playingPiles.get(cardPPileLocation(select2, "a")).getCard(cardPPileLocation(select2, "b")).setSelected(!(playingPiles.get(cardPPileLocation(select2, "a")).getCard(cardPPileLocation(select2, "b")).getSelected()));
					if(select1.equals(select2)){
						return -1;
					}
					return 1;
				}
			}
		}
		
		return 0;
	}
	
	public Card runFinalPile(MouseEvent arg0){
		for(int a = 0; a < finalPiles.size(); a++){
			if(arg0.getX()>=finalPiles.get(a).getx()
					&& arg0.getX()<=(finalPiles.get(a).getx() + finalPiles.get(a).getCardWidth())){
				if(arg0.getY()>=finalPiles.get(a).gety()
						&& arg0.getY()<=finalPiles.get(a).gety() + finalPiles.get(a).getCardHeight()){

					return finalPiles.get(a).getCard(finalPiles.get(a).size()-1);
				}
			}
		}
		return null;
	}
	
	public Card runLayPile(MouseEvent arg0){
		if(lpile.size()==0){
			return null;
		}
		if(arg0.getX()>=lpile.getCard(lpile.size()-1).getx() && arg0.getX()<=(lpile.getCard(lpile.size()-1).getx() + dpile.getCardWidth())){
			if(arg0.getY()>=lpile.getCard(lpile.size()-1).gety() && arg0.getY()<=(lpile.getCard(lpile.size()-1).gety() + dpile.getCardHeight())){
				
				if(!(lpile.getCard(lpile.size()-1).getShouldBeDrawn())){
					return null;
				}
				return lpile.getCard(lpile.size()-1);
			}
		}
		return null;

	}
	
	public void runDrawPile(MouseEvent arg0){	
		if(arg0.getX()>=dpile.getx() && arg0.getX()<=(dpile.getx() + dpile.getCardWidth())){
			if(arg0.getY()>=dpile.gety() && arg0.getY()<=(dpile.gety() + dpile.getCardHeight())){
				if(dpile.size()==0 && lpile.size()==0){
//					Do nothing
				}
				else{
					drawPileFunction();
				}
				System.out.println(dpile.size());
			}
		}
	}
	
	public Card checkPlayingPile(MouseEvent arg0){
		for(int a = 0; a < playingPiles.size(); a++){
			if(arg0.getX()>=playingPiles.get(a).getx()
					&& arg0.getX()<=(playingPiles.get(a).getx() + playingPiles.get(a).getCardWidth())){
				if(arg0.getY()>=playingPiles.get(a).gety()
						&& arg0.getY()<=(playingPiles.get(a).gety() 
								+ playingPiles.get(a).getCardHeight() 
								+ (int)(0.25*playingPiles.get(a).getCardHeight()*(playingPiles.get(a).size()-1)))
					){
					System.out.println("WORK" + a);
					
//					WITHIN THE DECK
					for(int b = 0; b<playingPiles.get(a).size(); b++){
						int c;
						if(b==playingPiles.get(a).size()-1){
							c = 0;
						}
						else{
							c = 1;
						}						
						if(arg0.getY()>=playingPiles.get(a).getCard(b).gety()
								&& arg0.getY()<=(playingPiles.get(a).getCard(b).gety() 
										- (int)(0.75*c*playingPiles.get(a).getCardHeight())
										+ playingPiles.get(a).getCardHeight())
							){
							
//							RETURN CARD
							return playingPiles.get(a).getCard(b);
						}
					}
					
				}
			}
		}
		return null;
	}
	
	public void drawPileFunction(){
		if(dpile.size()==0){
			System.out.println(lpile.size());
			for(Card cards : lpile.getCardList()){
				cards.setShouldBeDrawn(false);
				cards.setFaceUp(false);
				cards.setDeckType("dpile");
				cards.setCoord(dpile.getx(), dpile.gety());
				dpile.addCard(cards);
			}
			
			int b = lpile.size();
			for(int a = 0; a < b; a++){	
				lpile.removeCard(0);
			}
			
//			RE-ORDER CARDS
			int dpilesize = dpile.size();
			for(int a = 0; a < dpilesize; a++){
				dpile.addCard(dpile.getCard(dpilesize-(a+1)));
			}
			for(int a = 0; a < dpilesize; a++){
				dpile.removeCard(0);
			}
			
			
			dpile.getLastCard().setShouldBeDrawn(true);
			System.out.println("++" + lpile.size());
			System.out.println("--" + dpile.size());
		}
		else{
			if(dpile.size()<4){
				if(lpile.size()>0){
					for(int a = 0; a<lpile.size(); a++){
						lpile.getCard(lpile.size()-a-1).setShouldBeDrawn(false);
					}
				}
				for(int a = 0; a<3; a++){
					if(dpile.size()==0){
						break;
					}
					dpile.getLastCard().setFaceUp(true);
					dpile.getLastCard().setDeckType("lpile");
					dpile.getLastCard().setShouldBeDrawn(true);
					dpile.getLastCard().setCoord((int)(lpile.getx() + a*0.18*lpile.getCardWidth()), lpile.gety());
					lpile.addCard(dpile.getLastCard());
					dpile.removeCard(dpile.size()-1);
				}
//				for(int a = 0; a<dpile.size();a++){
//					dpile.getLastCard().setFaceUp(true);
//					dpile.getLastCard().setShouldBeDrawn(true);
//					dpile.getLastCard().setCoord((int)(lpile.getx() + a*0.18*lpile.getCardWidth()), lpile.gety());
//					lpile.addCard(dpile.getLastCard());
//					dpile.removeCard(dpile.size()-1);
//				}
			}
			if(dpile.size()>=4){
				if(lpile.size()>0){
					for(int a = 0; a<lpile.size(); a++){
						lpile.getCard(lpile.size()-a-1).setShouldBeDrawn(false);
					}
				}
				for(int a = 0; a<3; a++){
					dpile.getLastCard().setFaceUp(true);
					dpile.getLastCard().setDeckType("lpile");
					dpile.getLastCard().setShouldBeDrawn(true);
					dpile.getLastCard().setCoord((int)(lpile.getx() + a*0.18*lpile.getCardWidth()), lpile.gety());
					lpile.addCard(dpile.getLastCard());
					dpile.removeCard(dpile.size()-1);
				}
				dpile.getLastCard().setShouldBeDrawn(true);
				dpile.getLastCard().setFaceUp(false);
			}
		}
	}
	
	public void flip(){
		if(dpile.getLastCard().getFaceUp()==true){
			dpile.getLastCard().setFaceUp(false);
		}
		else{
			dpile.getLastCard().setFaceUp(true);
		}
	}

}