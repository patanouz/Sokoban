package sokoban;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class graphics extends JFrame {

	
	
	private Player player;
	private JFrame frame;
	private Figure figure;
	private Board board;
	private DrawingBoard drawingBoard;
	private EditorBoard editorBoard;
	private JPanel listPane;
	private JPanel topMenu;
	private JPanel bottomMenu;
	
	//private ArrayList<String> map = new ArrayList<String>();
	//private ArrayList<box> boxes = new ArrayList<box>();
	
	int wid = 1440;
	int hig = 799;
	
	int dheight;
	int dwidth;
	
	boolean init = true; //prolly dont need this, i dont want to use it but im lazy.
	
	
	//1 = game, 2 = editor, 3 = ???, 4 = profit!
	//This is a dumb idea, i should instead use an interface for drawingboard/editingboard etc but im too lazy
	private int boardType = 0; 
	

	
	public graphics(Player player, Board board) {
		
		

		this.player = player;
		this.board = board;
		listPane = new JPanel();
		topMenu = new JPanel(new FlowLayout(FlowLayout.CENTER));
		bottomMenu = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		
		//boxes = board.getBoxes();
		
		
		frame = new JFrame();
		frame.setPreferredSize(new Dimension(wid, hig));
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		BorderLayout layout = new BorderLayout();
		frame.setLayout(layout);
		
		Load();
		
	}
	
	public void Load() {
		
		
		JButton butt = new JButton("Start Game");
		butt.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {
		    drawingBoard.setFocus();
		  }
		});
		
		JButton butt2 = new JButton("Butt2");
		butt2.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {
		    System.out.println(drawingBoard.getSize());
		  }
		});
		
		JButton butt3 = new JButton("Back to Menu");
		
		butt3.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				switch(boardType) {
				case 1:
					board.reset();
					drawingBoard.player = board.getPlayer();
					drawingBoard.revalidate();
					drawingBoard.repaint();
					listPane.removeAll();
		    		frame.remove(drawingBoard);
					frame.dispose();
					break;
				case 2:
					listPane.removeAll();
					frame.remove(editorBoard);
					frame.dispose();
					bottomMenu.removeAll();
				}
				
			}
		});
		
		topMenu.add(butt);
		topMenu.add(butt2);
		topMenu.add(butt3);
		
		JLabel moves = new JLabel("0");
		
		//bottomMenu.add(moves);
		
		
		
       // listPane.setLayout(new BoxLayout(listPane, BoxLayout.PAGE_AXIS));
       // listPane.add(Box.createRigidArea(new Dimension(0,5)));
        
        frame.getContentPane().add(listPane, BorderLayout.CENTER);
        frame.getContentPane().add(topMenu, BorderLayout.NORTH);
        frame.getContentPane().add(bottomMenu, BorderLayout.SOUTH);
       
        
        frame.pack();
        //frame.setVisible(true);

        
	}
	

    public JFrame getFrame() {
        return frame;
    }
    
    

    
    public void fixMyShit(int height, int width) {
    	int y = (dheight - height);
		int x = (dwidth - width);
		listPane.setBorder(BorderFactory.createEmptyBorder((y / 2), (x / 2), (y / 2), (x / 2)));
    }
    
    public void GameBoard() {
    	
    	boardType = 1;
    	
    	
    	if(drawingBoard == null) {
    		
    		drawingBoard = new DrawingBoard(player, board);
    		drawingBoard.setBackground(frame.getBackground());
    		drawingBoard.setGraphics(this);
    		
    	}
    	
    	listPane.removeAll();
    	listPane.setLayout(new BoxLayout(listPane, BoxLayout.PAGE_AXIS));
        listPane.add(Box.createRigidArea(new Dimension(0,5)));
        
    	listPane.add(drawingBoard);
        
    	//board.reset();
    	
		frame.pack();
		frame.setVisible(true);
		
		if(init) {

			dheight = drawingBoard.getSize().height;
			dwidth = drawingBoard.getSize().width;
			init = false;
			
		}
		
		fixMyShit(board.getHeight(), board.getWidth());
		drawingBoard.setFocus();
		
		
    }
    
    public void EditorBoard() {
    	
    	boardType = 2;
    	
    	if(editorBoard == null) {
    	
    		editorBoard = new EditorBoard(board);
    		editorBoard.setBackground(frame.getBackground());
    		//editorBoard.setGraphics(this);
    	
    	}
    	
    	frame.getContentPane().remove(bottomMenu);
    	bottomMenu = editorBoard.editorMenu();
    	frame.getContentPane().add(bottomMenu, BorderLayout.SOUTH);
    	frame.pack();
    	 
    	
    	listPane.removeAll();
    	listPane.setLayout(new BoxLayout(listPane, BoxLayout.PAGE_AXIS));
        listPane.add(Box.createRigidArea(new Dimension(0,5)));
        
    	listPane.add(editorBoard);

    	
    	
		frame.pack();
		frame.setVisible(true);
		
		if(init) {
    	dheight = editorBoard.getSize().height;
		dwidth = editorBoard.getSize().width;
		init = false;
		}

		
		
		fixMyShit(720,1140);
		
    	
    }
    
    private void BottomMenuFiller() {
    	
    	bottomMenu.removeAll();
    	
    	
    	
    	
    	/*JMenuBar menuBar = new JMenuBar();
        JMenuItem saveItem, saveAllItem;

        // Menu
        JMenu fileMenu = new JMenu("File");

        // Menu Item (Drop down menus)
        saveItem = new JMenuItem("Save");
        
        saveAllItem = new JMenuItem("Save All");

        // Adding menu items to menu
        fileMenu.add(saveItem);
        fileMenu.add(saveAllItem);

        // adding menu to menu bar
        menuBar.add(fileMenu);

        // setting menubar at top of the window.

        // if you create a object of JFrame in class then code to set JMenuBar to JFrame will be:
        // jframe.setJMenuBar(menuBar);
        // if class is extending JFrame then it will be like this:
        setJMenuBar(menuBar);
        
        bottomMenu.add(menuBar);*/
        
    	
    	
    	
    }
    
    
	
    
}
