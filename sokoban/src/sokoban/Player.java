package sokoban;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player extends Figure {

	private int x;
	private int y;
	
	private int posX;
	private int posY;
	
	private Image player;

	public Player(int x, int y, int posX, int posY) {
		super(posX, posY);
		this.x = x;
		this.y = y;
		
		this.posX = posX;
		this.posY = posY;
		
		
		try {
			player = ImageIO.read(new File("src/images/player.png"));
			Image modified = player.getScaledInstance(60, 45, Image.SCALE_AREA_AVERAGING);
			player = modified;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void reset(int x, int y) {
		super.reset(x, y);
	}
	
	public void where() {
    	System.out.println("2x : " + super.getX());
    	System.out.println("2y : " + super.getY());
    }
	
	//move one step
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
