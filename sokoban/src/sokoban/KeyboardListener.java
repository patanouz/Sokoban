package sokoban;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardListener implements KeyListener {

	private Component component;
	private Player figure;
	private board board;
//	private Figure figure;
//	private Figure box;

	public KeyboardListener(Component component, Player figure, board board) {
		this.figure = figure;
		this.component = component;
		this.board = board;
		

	}

	@Override
	public void keyPressed(KeyEvent e) {

		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			if (board.allowed(-1, 0, figure.getX(), figure.getY())) {
				figure.move(-80, 0);

			}
			break;
		case KeyEvent.VK_RIGHT:
			if (board.allowed(1, 0, figure.getX(), figure.getY())) {
				figure.move(80, 0);
				
			}
			break;
		case KeyEvent.VK_UP:
			if (board.allowed(0, -1, figure.getX(), figure.getY())) {
				figure.move(0, -60);
				
			}
			break;
		case KeyEvent.VK_DOWN:
			if (board.allowed(0, 1, figure.getX(), figure.getY())) {
				figure.move(0, 60);
			}
			break;
		case KeyEvent.VK_R:
			board.reset();
			figure = board.getPlayer();
			component.revalidate();
			component.repaint();
			break;
		default:
			break;
		}
		
		board.inGoal();
		component.repaint();
		if (board.winner()) {
			board.levelBump();
			figure = board.getPlayer();
			component.revalidate();
			component.repaint();
			
		}
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent ke) {
	}
}