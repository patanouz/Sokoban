package sokoban;

import java.util.ArrayList;

public class board {
	
	private ArrayList<String> map = new ArrayList<String>();
	
	
	public board() {
		
	}
	
	
	
	public void createBoard(int x, int y) {
		
		for(int i = 0; i < x; i++) {
			
			for(int j = 0; j < y; j++) {
				map.add("x");
			}
			
		}
	}
}
