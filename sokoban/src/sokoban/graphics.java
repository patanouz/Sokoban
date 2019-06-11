package sokoban;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class graphics extends JFrame {

	private BufferedImage wall_blue;
	private BufferedImage wall_red;
	private BufferedImage wall_yellow;
	private BufferedImage wall_green;
	private BufferedImage wall_purple;
	private BufferedImage blank;
	private BufferedImage floor;
	private BufferedImage goal;
	private BufferedImage player;
	private BufferedImage box_floor;
	private BufferedImage box_goal;

	private ArrayList<String> map = new ArrayList<String>();
	private ArrayList<String> units = new ArrayList<String>();

	public graphics() {

		map.add("WWWWWWWWW");
		map.add("WFFFGFFFW");
		map.add("WFFFGFFFW");
		map.add("WFWWGWWFW");
		map.add("WFFFGFFFW");
		map.add("WWWWWWWWW");
		
		units.add("BBBBBBBBB");
		units.add("BBBBBBBBB");
		units.add("BBABGBABB");
		units.add("BBBBBBBBB");
		units.add("BBBBBPABB");
		units.add("BBBBBBBBB");

		try {

			wall_red = ImageIO.read(new File("images/red.png"));
			wall_green = ImageIO.read(new File("images/green.png"));
			wall_yellow = ImageIO.read(new File("images/yellow.png"));
			wall_purple = ImageIO.read(new File("images/purple.png"));
			wall_blue = ImageIO.read(new File("images/blue.png"));
			blank = ImageIO.read(new File("images/blank.png"));
			floor = ImageIO.read(new File("images/floor.png"));
			goal = ImageIO.read(new File("images/goal.png"));
			player = ImageIO.read(new File("images/player.png"));
			box_floor = ImageIO.read(new File("images/box_on_floor.png"));
			box_goal = ImageIO.read(new File("images/goal_with_box.png"));
		} catch (IOException ex) {
			// handle exception...
			System.out.println("fail : " + ex);
		}

		setSize(1280, 720);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public void paint(Graphics g) {
		super.paint(g);

		int x = 20;
		int y = 50;
		for (String map : map) {
			for (int j = 0; j < map.length(); j++) {
				char c = map.charAt(j);

				if (c == 'W') {
					g.drawImage(wall_blue, x, y, this);
					x += 100;
				} else if (c == 'F') {
					g.drawImage(floor, x, y, this);
					x += 100;
				} else if (c == 'G') {
					g.drawImage(goal, x, y, this);
					x += 100;
				} else if (c == 'B') {
					g.drawImage(blank, x, y, this);
					x += 100;
				} else if (c == 'J') {
					g.drawImage(wall_red, x, y, this);
					x += 100;
				}

			}
			y += 75;
			x = 20;
		}
		
		x = 20;
		y = 50;
		
		for(String unit : units) {
			for (int j = 0; j < unit.length(); j++) {
				char c = unit.charAt(j);
				
				
				System.out.println(c);
				if (c == 'B') {
					
					x+= 100;
					
				} else if (c == 'G') {
					g.drawImage(box_goal, x, y, this);
					x+= 100;
				} else if (c == 'P') {
					g.drawImage(player, x, y, this);
					x+= 100;
				} else if (c == 'A'){
					g.drawImage(box_floor, x, y, this);
					x+= 100;
				}
				 
				
				
			}
			y+= 75;
			x = 20;
			 
		}
		

	}

}
