package sokoban;

import java.awt.BorderLayout;
import java.awt.Canvas;
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

public class graphics extends JPanel {

	
	static JTextField textfield1;
	private BufferedImage image;

	public graphics() {
	       try {                
	          image = ImageIO.read(new File("../../images/test.png"));
	       } catch (IOException ex) {
	            // handle exception...
	       }
	    }
	
	
	public void paint() {
		
		
		JFrame frame = new JFrame();
		
		
		
		
		
		
		
		
		frame.add(createPanel());
		frame.setPreferredSize(new Dimension(300,300));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		
		
		
		
	}
	
	
	private JPanel createPanel() {
        JPanel panel = new JPanel(new GridLayout(5,5));
        ImageIcon icon = new ImageIcon("images/test.png");
        JLabel one = new JLabel(icon);
        JLabel two = new JLabel(icon);
       
        panel.add(one);
        validate();
        repaint();
        panel.add(one);
        
        return panel;
    }

	    @Override
	    protected void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        g.drawImage(image, 0, 0, this); // see javadoc for more info on the parameters            
	    }

	
}
