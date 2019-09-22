package sokoban;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class box extends Figure {

	private int x;
	private int y;
	
	private int posX;
	private int posY;
	
	private BufferedImage box1;
	private BufferedImage box2;
	
	private boolean inGoal;

	public box(int x, int y) {
		super(x, y);
		this.x = x;
		this.y = y;
		
		
		this.posX = (20 + x * 100);
		this.posY = (50 + y * 75);
		
		
		try {
			box1 = ImageIO.read(new File("images/box_on_floor.png"));
			box2 = ImageIO.read(new File("images/box_on_goal.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void goalUpdate(boolean goal) {
		this.inGoal = goal;
	}
	
	public boolean inGoal() {
		return inGoal;
	}
	
	//move one step
	public void move(int dx, int dy) {

		this.posX += dx;
		this.posY += dy;
	}
	

	@Override
	public void draw(Graphics graphics) {
		// TODO Auto-generated method stub
		if(inGoal) {
			graphics.drawImage(box2, posX, posY, null);
		} else {
			graphics.drawImage(box1, posX, posY, null);
		}
	}

}
