package sokoban;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class board {

	private mapReader reader;

	private Player player;

	private DrawingBoard draw;

	private int currentlevel;

	private ArrayList<String> map = new ArrayList<String>();
	private ArrayList<box> boxes = new ArrayList<box>();

	private HashMap<String, BufferedImage> colors = new HashMap<String, BufferedImage>();

	// Sets the board, boxes and players.
	// TODO: separate map loading into other class
	public board() {

		this.currentlevel = 1;
		reader = new mapReader(currentlevel, draw);

		player = reader.getPlayer();
		map = reader.getMap();
		boxes = reader.getBoxes();

		try {

			BufferedImage wall_red = ImageIO.read(new File("images/red.png"));
			BufferedImage wall_green = ImageIO.read(new File("images/green.png"));
			BufferedImage wall_yellow = ImageIO.read(new File("images/yellow.png"));
			BufferedImage wall_purple = ImageIO.read(new File("images/purple.png"));
			BufferedImage wall_blue = ImageIO.read(new File("images/blue.png"));
			BufferedImage blank = ImageIO.read(new File("images/blank.png"));
			BufferedImage floor = ImageIO.read(new File("images/floor.png"));
			BufferedImage goal = ImageIO.read(new File("images/goal.png"));
			BufferedImage box_floor = ImageIO.read(new File("images/box_on_floor.png"));
			BufferedImage box_goal = ImageIO.read(new File("images/box_on_goal.png"));

			colors.put("red", wall_red);
			colors.put("green", wall_green);
			colors.put("yellow", wall_yellow);
			colors.put("purple", wall_purple);
			colors.put("blue", wall_blue);
			colors.put("blank", blank);
			colors.put("floor", floor);
			colors.put("goal", goal);
			colors.put("box_floor", box_floor);
			colors.put("box_goal", box_goal);

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
				player.increaseX();
			} else {
				b.move(-100, 0);
				b.DecreaseX();
				player.DecreaseX();
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
				player.DecreaseY();
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
				player.increaseY();

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

			if (hasBox((curX + x), curY)) {
				return boxMove(x, y, curX, curY);

			}

			if (x == 1) {

				player.increaseX();
			} else {
				player.DecreaseX();
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

				player.DecreaseY();

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

				player.increaseY();

				return true;
			}
		}

		return true;
	}

	public Player getPlayer() {
		return player;
	}

	public void reset() {

		map.clear();
		boxes.clear();
		player = null;
		reader.removePlayer();
		draw.delete();

		reader = new mapReader(currentlevel, draw);
		player = reader.getPlayer();
		map = reader.getMap();
		boxes = reader.getBoxes();

	}

	public void levelBump() {
		currentlevel++;
		map.clear();
		boxes.clear();
		player = null;
		reader.removePlayer();
		draw.delete();

		reader = new mapReader(currentlevel, draw);
		player = reader.getPlayer();
		map = reader.getMap();
		boxes = reader.getBoxes();
	}

	public void paint(Graphics g) {

		int x = 20;
		int y = 50;
		for (String map : map) {
			for (int j = 0; j < map.length(); j++) {
				char c = map.charAt(j);

				if (c == 'W') {
					g.drawImage(colors.get(reader.getColor()), x, y, null);
					x += 100;
				} else if (c == 'F') {
					g.drawImage(colors.get("floor"), x, y, null);
					x += 100;
				} else if (c == 'G') {
					g.drawImage(colors.get("goal"), x, y, null);
					x += 100;
				} else if (c == 'B') {
					g.drawImage(colors.get("blank"), x, y, null);
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
