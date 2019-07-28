package sokoban;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class board {

	private BufferedImage wall_blue;
	private BufferedImage wall_dre;
	private BufferedImage wall_yellow;
	private BufferedImage wall_green;
	private BufferedImage wall_purple;
	private BufferedImage wall_red;
	private BufferedImage blank;
	private BufferedImage floor;
	private BufferedImage goal;
	private BufferedImage box_floor;
	private BufferedImage box_goal;

	private player Player;

	private DrawingBoard draw;

	private int playerPosX;
	private int playerPosY;

	private ArrayList<String> map = new ArrayList<String>();
	private ArrayList<box> boxes = new ArrayList<box>();

	// Sets the board, boxes and players.
	// TODO: separate map loading into other class
	public board(int x, int y) {

		this.playerPosX = x;
		this.playerPosY = y;

		int playerX = ((playerPosX * 100) + 20);
		int playerY = (50 + (playerPosY * 75));

		Player = new player(playerX, playerY, playerPosX, playerPosY);

/*		map.add("WWWWWWWWW");
		map.add("WFFFGFFFW");
		map.add("WFFFGFFFW");
		map.add("WFFFGFFFW");
		map.add("WFFFGFFFW");
		map.add("WWWWWWWWW");
		
		*/
		
		map.add("BBBBWWWWWBBBBBBBBBB");
		map.add("BBBBWFFFWBBBBBBBBBB");
		map.add("BBWWWFFFWBBBBBBBBBB");
		map.add("BBWFFFFFWWBBBBBBBBB");
		map.add("BBWFWFFFFWBBBBBBBBB");
		map.add("WWWFWFWWFWBBBWWWWWW");
		map.add("WFFFFFWWFWWWWWFFGGW");
		map.add("WFFFFFFFFFFFFFFFGGW");
		map.add("WWWWWFWWWFWFWWFFGGW");
		map.add("BBBBWFFFFFWWWWWWWWW");
		map.add("BBBBWWWWWWWBBBBBBBB");
		
		
		boxes.add(new box(5, 2));
		boxes.add(new box(5, 4));
		boxes.add(new box(5, 7));
		boxes.add(new box(7, 3));
		boxes.add(new box(7, 4));
		boxes.add(new box(2, 7));

		try {

			wall_red = ImageIO.read(new File("images/red.png"));
			wall_green = ImageIO.read(new File("images/green.png"));
			wall_yellow = ImageIO.read(new File("images/yellow.png"));
			wall_purple = ImageIO.read(new File("images/purple.png"));
			wall_blue = ImageIO.read(new File("images/blue.png"));
			blank = ImageIO.read(new File("images/blank.png"));
			floor = ImageIO.read(new File("images/floor.png"));
			goal = ImageIO.read(new File("images/goal.png"));
			box_floor = ImageIO.read(new File("images/box_on_floor.png"));
			box_goal = ImageIO.read(new File("images/box_on_goal.png"));
		} catch (IOException ex) {
			// handle exception...
			System.out.println("fail : " + ex);
		}

	}

	public void getboard(DrawingBoard b) {
		this.draw = b;
	}

	public boolean winner() {

		for (box b : boxes) {
			if (b.inGoal() == false) {
				return false;
			}
		}

		return true;
	}

	public boolean boxMove(int x, int y, int curX, int curY) {

		int bx = curX += x;
		int by = curY += y;

		
		box b = boxAt(curX, curY);

		if (y == 0) {

			String row = map.get(curY);
			if (row.charAt(bx + x) == 'W' || hasBox((bx + x), (by + y))) {

				return false;
			}
			if (x == 1) {
				b.move(100, 0);
				b.increaseX();
				Player.increaseX();
			} else {
				b.move(-100, 0);
				b.DecreaseX();
				Player.DecreaseX();
			}

			return true;

		}

		if (y == -1) {

			String row = map.get(curY - 1);

			if (row.charAt(curX) == 'W') {
				
				return false;
			} else if (hasBox((bx + x), (by + y))) {
				
				return false;
			} else {

				
				b.move(0, -75);
				b.DecreaseY();
				Player.DecreaseY();
				// draw.repaint();

				return true;

			}
		}

		if (y == 1) {

			String row = map.get(curY + 1);

			if (row.charAt(curX) == 'W' || hasBox((curX + x), (curY + y))) {

				return false;
			} else {

				b.move(0, 75);
				b.increaseY();
				Player.increaseY();

				return true;
			}
		}

		return true;

	}

	public boolean hasBox(int x, int y) {

		for (box b : boxes) {
			if ((b.getX() == x) && (b.getY() == y)) {
				return true;
			}
		}

		return false;
	}

	public box boxAt(int x, int y) {
		for (box b : boxes) {
			if ((b.getX() == x) && (b.getY() == y)) {
				return b;
			}
		}
		return null;
	}

	public ArrayList getBoxes() {
		return boxes;
	}

	public boolean inGoal() {
		for (box b : boxes) {
			String row = map.get(b.getY());
			if (row.charAt(b.getX()) == 'G') {
				b.goalUpdate(true);
			} else {
				b.goalUpdate(false);

			}

		}
		return true;

	}

	// Checks if the move is allowed or not by checking if there is a wall in the
	// next position.
	public boolean allowed(int x, int y, int curX, int curY) {

		if (y == 0) {
			String test = map.get(curY);
			if (test.charAt(curX + x) == 'W') {

				return false;
			}

			if (hasBox((curX + x), (curY + y))) {
				return boxMove(x, y, curX, curY);

			}

			if (x == 1) {

				Player.increaseX();
			} else {
				Player.DecreaseX();
			}
			return true;

		}

		if (y == -1) {

			String test = map.get(curY - 1);

			if (test.charAt(curX) == 'W') {

				return false;
			} else if (hasBox((curX + x), (curY + y))) {

				return boxMove(x, y, curX, curY);

			} else {

				Player.DecreaseY();

				return true;

			}
		}

		if (y == 1) {

			String test = map.get(curY + 1);

			if (test.charAt(curX) == 'W') {

				return false;
			} else if (hasBox((curX + x), (curY + y))) {

				return boxMove(x, y, curX, curY);

			} else {

				Player.increaseY();

				return true;
			}
		}

		return true;
	}

	public player getPlayer() {
		return Player;
	}

	public void paint(Graphics g) {

		int x = 20;
		int y = 50;
		for (String map : map) {
			for (int j = 0; j < map.length(); j++) {
				char c = map.charAt(j);

				if (c == 'W') {
					g.drawImage(wall_blue, x, y, null);
					x += 100;
				} else if (c == 'F') {
					g.drawImage(floor, x, y, null);
					x += 100;
				} else if (c == 'G') {
					g.drawImage(goal, x, y, null);
					x += 100;
				} else if (c == 'B') {
					g.drawImage(blank, x, y, null);
					x += 100;
				} else if (c == 'J') {
					g.drawImage(wall_red, x, y, null);
					x += 100;
				}

			}
			y += 75;
			x = 20;
		}

		x = 20;
		y = 50;

	}
}
