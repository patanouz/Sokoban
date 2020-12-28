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

	//TODO: rewrite all this to only be one x/y, not multiple.
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

	//TODO: rewrite all this to only be one x/y, not multiple.
	public Player(int x, int y) {
		super(x, y);
		this.x = (x * 60);
		this.y = (y * 45);
		this.posX = x;
		this.posY = y;

		//This only needs to be duplicate until I merge these two constructors.
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
	
	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}
	
	//move one step
	public void move(int dx, int dy) {

		this.x += dx;
		this.y += dy;
	}

	public Image getPlayerImage() {
		return player;
	}
	

	@Override
	public void draw(Graphics graphics) {
		// TODO Auto-generated method stub
		graphics.drawImage(player, x, y, null);
	}

}
