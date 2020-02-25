package sokoban;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class DrawingBoard extends JPanel {

	private Figure figure;
    private board board;
    private ArrayList<box> boxes = new ArrayList<box>();

    public DrawingBoard(Figure figure, board board, ArrayList<box> boxes) {
       super.setBackground(Color.WHITE);
       this.board = board;
       this.boxes = boxes;
       this.figure = figure;
       
       board.getboard(this);
       board.inGoal();
       
    }
    
    public void delete() {
    	figure = null;
    }
    
    public void newFigure(Figure figure) {
    	this.figure = figure;
    }


	@Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        board.paint(graphics);
        figure.draw(graphics);
        
        for(box b : boxes) {
        	b.draw(graphics);
        }
        
    }
}
