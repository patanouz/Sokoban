package sokoban;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class DrawingBoard extends JPanel {

	public Player player;
    private Board board;
    private ArrayList<box> boxes = new ArrayList<box>();
    private graphics g;
    

    public DrawingBoard(Player figure, Board board) {
       super.setBackground(Color.WHITE);
       
       super.addKeyListener(new KeyboardListener(this, board));
       
       this.board = board;
       this.boxes = board.getBoxes();
       this.player = board.getPlayer();
       
       board.getboard(this);
       board.inGoal();
       
       this.requestFocusInWindow();
       
    }
    
    public void delete() {
    	player = null;
    }
    
    public void addKeyboardListener(KeyboardListener k) {
    	
    }
    
    public void setGraphics(graphics g) {
    	this.g = g;
    }
    
    public graphics getgraphics() {
    	return g;
    }
    
    public void updatePlayer() {
    	this.player = board.getPlayer();
    }
    
    
    public void setFocus() {
    	super.setFocusable(true);
    	super.requestFocusInWindow();
    	
    }
    
    public void newFigure(Player figure) {
    	this.player = figure;
    }


	@Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        board.paint(graphics);
        player.draw(graphics);
        
        for(box b : boxes) {
        	b.draw(graphics);
        }
        
    }
}
