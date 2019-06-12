package sokoban;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class player extends Figure {

	private int x;
	private int y;
	private BufferedImage player;

	public player(int x, int y) {
		super(x, y);
		this.x = x;
		this.y = y;
		
		try {
			player = ImageIO.read(new File("images/box_on_floor.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void move(int dx, int dy) {

		this.x += dx;
		this.y += dy;
	}

	@Override
	public void draw(Graphics graphics) {
		// TODO Auto-generated method stub
		graphics.drawImage(player, x, y, null);
	}

}