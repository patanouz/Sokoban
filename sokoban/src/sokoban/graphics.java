package sokoban;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class graphics extends JFrame {

	
	
	private Player Player;
	private JFrame frame;
	private Figure figure;
	private board board;

	private ArrayList<String> map = new ArrayList<String>();
	private ArrayList<box> boxes = new ArrayList<box>();

	
	public graphics(Player player, board board) {
		
		this.Player = player;
		this.board = board;
		
		boxes = board.getBoxes();
		
		
		
		frame = new JFrame();
		frame.setPreferredSize(new Dimension(1920, 1080));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		createComponents(frame.getContentPane());
		addListeners();
		frame.pack();
		frame.setVisible(true);
		
		
	}
	
	
	private void createComponents(Container container) {
        DrawingBoard drawingBoard = new DrawingBoard(Player, board, boxes);
        container.add(drawingBoard);
        
        frame.addKeyListener(new KeyboardListener(drawingBoard, Player, board));
        
    }
	
	private void addListeners() {
    }

    public JFrame getFrame() {
        return frame;
    }
	
    
}
