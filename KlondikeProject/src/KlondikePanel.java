import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class KlondikePanel extends JPanel {

	// /** The card displays. */
	// private JLabel[] displayCards;
	// /** The coordinates of the card displays. */
	// private Point[] cardCoords;

	Dimension dim = new Dimension(1000, 700);
	Color backGround = new Color(20, 150, 110);
	KlondikeBoard board = new KlondikeBoard();
	int selectedCards;

	public KlondikePanel() {
		this.setPreferredSize(dim);
		this.setBackground(backGround);
		setUpMouseListeners();
		board.setUpBoard();
	}

	private void setUpMouseListeners() {
		setUpML();
		setUPMML();
	}

	private void setUPMML() {
		// MouseMotionListener allows for listening for dragging

	}

	private void setUpML() {
		// MouseListeners allow for listening for clicks
		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				System.out.println("you just clicked " + arg0);
				System.out.println("SELECTED" + selectedCards);
				selectedCards += board.clickedAt(arg0, selectedCards);
				if(selectedCards==2){
					board.move();					
					
					
					
					selectedCards = 0;
					board.resetSelect();
				}
//				board.flip();
				repaint();
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

		});
		this.requestFocusInWindow();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		board.draw(g);
	}

}
