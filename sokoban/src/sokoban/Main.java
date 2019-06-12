package sokoban;

import java.awt.Graphics;

public class Main {
	public static void main(String[] args) {
		
		player Player = new player(20, 50);
		board board = new board();
		
		//board b = new board();
		graphics g = new graphics(Player, board);
		
		
	}
}