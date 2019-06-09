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

	
	private BufferedImage red;
	private BufferedImage blank;
	private BufferedImage floor;
	private BufferedImage goal;
	
	private ArrayList<String> map = new ArrayList<String>();

	public graphics() {
		
		map.add("WWWWWWWWW");
		map.add("WFFFGFFFW");
		map.add("WFFFGFFFW");
		map.add("WFWWGWWFW");
		map.add("WFFFGFFFW");
		map.add("WWWWWWWWW");
		
		
		
		
		try {
		red = ImageIO.read(new File("images/blue.png"));
		blank = ImageIO.read(new File("images/blank.png"));
		floor = ImageIO.read(new File("images/floor.png"));
		goal = ImageIO.read(new File("images/goal.png"));
		} catch (IOException ex) {
			// handle exception...
			System.out.println("fail" + ex);
		}
  
		setSize(1280, 720);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public void paint(Graphics g) {
		super.paint(g);
		
		int x = 20;
		int y = 50;
		for(String test : map) {
			for(int j = 0; j < test.length(); j++) {
				char c = test.charAt(j);
				
				
				if(c == 'W') {
					
					g.drawImage(red, x, y, this);
					x+= 100;
				} else if (c == 'F') {
					g.drawImage(floor, x, y, this);
					x+= 100;
				} else if (c == 'G') {
					g.drawImage(goal, x, y, this);
					x+= 100;
				} else {
					g.drawImage(blank, x, y, this);
					x+= 100;
				}
				
				
				
	/*		switch(c) {
			case 'W' :
				
			case 'F' :
				g.drawImage(floor, x, y, this);
				x+= 100;
			case 'G' :
				g.drawImage(goal, x, y, this);
				x+= 100;
			case 'B' :
				g.drawImage(blank, x, y, this);
				x+= 100;
				
				}
		*/	
			
				
			}
			y += 75;
			x = 20;
		}
		
		
	}

}
