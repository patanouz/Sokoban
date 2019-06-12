package sokoban;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;


public class DrawingBoard extends JPanel {

    private Figure figure;
    private board board;

    public DrawingBoard(Figure figure, board board) {
       super.setBackground(Color.WHITE);
       this.figure = figure;
       this.board = board;
       
       
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        board.paint(graphics);
        figure.draw(graphics);
        
    }
}
