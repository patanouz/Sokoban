package sokoban;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class graphics extends JFrame {

	
	
	private player Player;
	private JFrame frame;
	private Figure figure;
	private board board;

	private ArrayList<String> map = new ArrayList<String>();
	private ArrayList<String> units = new ArrayList<String>();

	public graphics(player player, board board) {
		
		this.Player = player;
		this.board = board;
		
		
		
		
		units.add("BBBBBBBBB");
		units.add("BBBBBBBBB");
		units.add("BBABGBABB");
		units.add("BBBBBBBBB");
		units.add("BBBBBPABB");
		units.add("BBBBBBBBB");
		
		

		
		
		frame = new JFrame();
		frame.setPreferredSize(new Dimension(400, 400));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		createComponents(frame.getContentPane());
		addListeners();
		frame.pack();
		frame.setVisible(true);
		
		
	}
	
	
	private void createComponents(Container container) {
        DrawingBoard drawingBoard = new DrawingBoard(Player, board);
        container.add(drawingBoard);
        
        frame.addKeyListener(new KeyboardListener(drawingBoard, Player));
        
    }
	
	private void addListeners() {
    }

    public JFrame getFrame() {
        return frame;
    }
	
	

	

}
