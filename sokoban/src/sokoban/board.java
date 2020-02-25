package sokoban;

import javax.imageio.ImageIO;
import javax.swing.*;
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

	private HashMap<String, Image> colors = new HashMap<String, Image>();

	// Sets the board, boxes and players.
	// TODO: separate map loading into other class
	public board() {

		this.currentlevel = 1;
		reader = new mapReader(currentlevel, draw);

		player = reader.getPlayer();
		map = reader.getMap();
		boxes = reader.getBoxes();

		try {

			Image wall_red = ImageIO.read(new File("sokoban/images/red.png"));
			Image wall_green = ImageIO.read(new File("sokoban/images/green.png"));
			Image wall_yellow = ImageIO.read(new File("sokoban/images/yellow.png"));
			Image wall_purple = ImageIO.read(new File("sokoban/images/purple.png"));
			Image wall_blue = ImageIO.read(new File("sokoban/images/blue.png"));
			Image blank = ImageIO.read(new File("sokoban/images/blank.png"));
			Image floor = ImageIO.read(new File("sokoban/images/floor.png"));
			Image goal = ImageIO.read(new File("sokoban/images/goal.png"));
			Image box_floor = ImageIO.read(new File("sokoban/images/box_on_floor.png"));
			Image box_goal = ImageIO.read(new File("sokoban/images/box_on_goal.png"));

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

		for(HashMap.Entry<String, Image> entry : colors.entrySet()) {

			Image resize = entry.getValue();

			Image modified = resize.getScaledInstance(80, 60, Image.SCALE_AREA_AVERAGING);
			entry.setValue(modified);


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
				b.move(80, 0);
				b.increaseX();
				player.increaseX();
			} else {
				b.move(-80, 0);
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

				b.move(0, -60);
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

				b.move(0, 60);
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

	public static void infoBox(String infoMessage, String titleBar)
	{
		JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
	}

	public void levelBump() {
		infoBox("You won!", "Loading next level");
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

		int x = 0;
		int y = 0;
		for (String map : map) {
			for (int j = 0; j < map.length(); j++) {
				char c = map.charAt(j);

				if (c == 'W') {
					g.drawImage(colors.get(reader.getColor()), x, y, null);
					x += 80;
				} else if (c == 'F') {
					g.drawImage(colors.get("floor"), x, y, null);
					x += 80;
				} else if (c == 'G') {
					g.drawImage(colors.get("goal"), x, y, null);
					x += 80;
				} else if (c == 'B') {
					g.drawImage(colors.get("blank"), x, y, null);
					x += 80;
				}

			}
			y += 60;
			x = 0;
		}

		x = 0;
		y = 0;

	}
}
