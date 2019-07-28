package sokoban;

import java.awt.Graphics;

public abstract class Figure {
    
    private int x;
    private int y;
    
    
    public Figure(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public void move(int dx, int dy) {
    	
        this.x+= dx;
        this.y+= dy;
    }
    
    public void increaseX() {
		x ++;
	}
	
	public void increaseY() {
		y ++;
	}
	
	public void DecreaseX() {
		x --;
	}
	
	public void DecreaseY() {
		y --;
	}
    
    public abstract void draw(Graphics graphics);
}
