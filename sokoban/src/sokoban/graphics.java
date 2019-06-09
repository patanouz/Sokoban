package sokoban;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import chessboard.Main;

public class graphics extends JPanel {

	static JTextField textfield1;
	private BufferedImage image;

	public graphics() {
		try {
			image = ImageIO.read(new File("/images/red.png"));
		} catch (IOException ex) {
			// handle exception...
		}

		JFrame frame = new JFrame();
		frame.setSize(1920, 1080);
		frame.getContentPane().add(new Main());
		frame.setLocationRelativeTo(null);
		frame.setBackground(Color.LIGHT_GRAY);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public void paint(Graphics g) {

		for (int i = 20; i < 975; i += 75) {
			for (int j = 50; j < 1800; j += 100) {
				g.drawImage(image, j, i, this);

			}

		}
	}

}
