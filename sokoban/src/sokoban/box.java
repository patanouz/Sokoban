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
	
	private Image box1;
	private Image box2;
	
	private boolean inGoal;

	public box(int x, int y) {
		super(x, y);
		this.x = x;
		this.y = y;
		
		
		this.posX = (x * 60);
		this.posY = (y * 45);
		
		
		try {
			box1 = ImageIO.read(new File("src/images/box_on_floor.png"));
			box2 = ImageIO.read(new File("src/images/box_on_goal.png"));

			Image modified = box1.getScaledInstance(60, 45, Image.SCALE_AREA_AVERAGING);
			box1 = modified;
			Image modified2 = box2.getScaledInstance(60, 45, Image.SCALE_AREA_AVERAGING);
			box2 = modified2;

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
