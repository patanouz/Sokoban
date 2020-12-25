package sokoban;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Editor {
	
	private JFrame frame;
	
	public Editor() {

	}
	
	
	public void Create() {
		
		frame = new JFrame();
		frame.setPreferredSize(new Dimension(800, 600));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		BorderLayout layout = new BorderLayout();
		frame.setLayout(layout);
		
		
		
		
		JButton butt = new JButton("Start Game");
		butt.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {
		    
		  }
		});
		
		JButton butt2 = new JButton("Butt2");
		butt2.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {
		    
		  }
		});
		
		JButton butt3 = new JButton("Back to Menu");
		
		butt3.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				
			}
		});
		

		JPanel flow = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		flow.add(butt);
		flow.add(butt2);
		flow.add(butt3);
		
		
		//listPane = new JPanel();
        //listPane.setLayout(new BoxLayout(listPane, BoxLayout.PAGE_AXIS));
        //listPane.add(Box.createRigidArea(new Dimension(0,5)));
        
        
        
        
        
        //listPane.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
        //listPane.add(drawingBoard);
		
        //frame.getContentPane().add(listPane, BorderLayout.CENTER);
        frame.getContentPane().add(flow, BorderLayout.SOUTH);
		
		
		frame.pack();
		frame.setVisible(true);
	}

}



/*
to resize image to 60/45


entry.setValue(modified);
*/