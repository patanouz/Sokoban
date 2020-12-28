package sokoban;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardListener implements KeyListener {

	private DrawingBoard drawingBoard;
	//private Player player;       //Previously i had a copy of player here which worked fine but then resetting the board became fucky. 
	private Board board;			//Changed player to public in drawingboard and that solved everything but now its public
	private int cheatHax = 0;		//will take 2.3 years to fix that and CBA
//	private Figure figure;
//	private Figure box;

	public KeyboardListener(DrawingBoard drawingBoard, Board board) {
		//this.player = player;
		this.drawingBoard = drawingBoard;
		this.board = board;
		

	}

	@Override
	public void keyPressed(KeyEvent e) {

		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			cheatHax = 0;
			if (board.allowed(-1, 0, drawingBoard.player.getX(), drawingBoard.player.getY())) {
				drawingBoard.player.move(-60, 0);

			}
			break;
		case KeyEvent.VK_RIGHT:
			cheatHax = 0;
			if (board.allowed(1, 0, drawingBoard.player.getX(), drawingBoard.player.getY())) {
				drawingBoard.player.move(60, 0);
				
			}
			break;
		case KeyEvent.VK_UP:
			cheatHax = 0;
			if (board.allowed(0, -1, drawingBoard.player.getX(), drawingBoard.player.getY())) {
				drawingBoard.player.move(0, -45);
				
			}
			break;
		case KeyEvent.VK_DOWN:
			cheatHax = 0;
			if (board.allowed(0, 1, drawingBoard.player.getX(), drawingBoard.player.getY())) {
				drawingBoard.player.move(0, 45);
			}
			break;
		case KeyEvent.VK_R:
			board.reset();
			drawingBoard.player = board.getPlayer();
			drawingBoard.revalidate();
			drawingBoard.repaint();
			break;
		case KeyEvent.VK_L:
			if(cheatHax == 0) {
				cheatHax = 1;
			} else if(cheatHax == 2) {
				
				cheatHax = 0;
				
				board.levelBump();
				drawingBoard.player = board.getPlayer();
				drawingBoard.revalidate();
				drawingBoard.repaint();
			}
			break;
		case KeyEvent.VK_O:
			if(cheatHax == 1) {
				cheatHax = 2;
			}
			break;
		default:
			break;
		}
		
		board.inGoal();
		drawingBoard.repaint();
		if (board.winner()) {
			board.levelBump();
			drawingBoard.player = board.getPlayer();
			drawingBoard.revalidate();
			drawingBoard.repaint();
			
		}
		
		
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent ke) {
	}
}