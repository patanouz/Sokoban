package sokoban;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardListener implements KeyListener {

	private Component component;
	private Figure figure;
	private board board;
//	private Figure figure;
//	private Figure box;

	public KeyboardListener(Component component, Figure figure, board board) {
		this.figure = figure;
		this.component = component;
		this.board = board;
		

	}

	@Override
	public void keyPressed(KeyEvent e) {

		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			if (board.allowed(-1, 0, figure.getX(), figure.getY())) {
				figure.move(-100, 0);
				
				
				
			}
			break;
		case KeyEvent.VK_RIGHT:
			if (board.allowed(1, 0, figure.getX(), figure.getY())) {
				figure.move(100, 0);
				
				
			}
			break;
		case KeyEvent.VK_UP:
			if (board.allowed(0, -1, figure.getX(), figure.getY())) {
				figure.move(0, -75);
				
				
				
			}
			break;
		case KeyEvent.VK_DOWN:
			if (board.allowed(0, 1, figure.getX(), figure.getY())) {
				figure.move(0, 75);
				
				
			}
			break;
		default:
			break;
		}
		
		board.inGoal();
		component.repaint();
		if (board.winner()) {
			System.out.println("You won!");
		}
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent ke) {
	}
}