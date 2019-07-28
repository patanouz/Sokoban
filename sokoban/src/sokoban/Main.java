package sokoban;

import java.awt.Graphics;

public class Main {
	public static void main(String[] args) {
		
		
		board board = new board(11, 8);
		
		//board b = new board();
		graphics g = new graphics(board.getPlayer(), board);
		
		
	}
}