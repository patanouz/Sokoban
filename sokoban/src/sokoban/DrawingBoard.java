package sokoban;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;


public class DrawingBoard extends JPanel {

    private Figure figure;
    private board board;
    private ArrayList<box> boxes = new ArrayList<box>();

    public DrawingBoard(Figure figure, board board, ArrayList<box> boxes) {
       super.setBackground(Color.WHITE);
       this.figure = figure;
       this.board = board;
       this.boxes = boxes;
       
       board.getboard(this);
       board.inGoal();
       
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
