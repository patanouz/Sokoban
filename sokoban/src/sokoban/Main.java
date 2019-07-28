package sokoban;

import java.awt.Graphics;

public class Main {
	public static void main(String[] args) {
		
		
		board board = new board(11, 8);
		
		//board b = new board();
		graphics g = new graphics(board.getPlayer(), board);
		
		
	}
}

/*

Sokoban TODO:

1. level editor!

2. class for saving games as text, also loading maps from files.

3. a win condition, example "next level" button.

4. graphics updates

5. a main menu

6. a background image

7. sick dubstep music

8. easter eggs

9. ???

10. profit!


*/