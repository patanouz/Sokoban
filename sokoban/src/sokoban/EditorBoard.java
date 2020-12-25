package sokoban;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class EditorBoard extends JPanel implements MouseListener {
	
	private Image tile;
	private Image blank;
	private Board board;
	
	public JPanel bottomMenu = new JPanel(new FlowLayout(FlowLayout.CENTER));
	
	
	private ArrayList<String> map = new ArrayList<String>();
	public ArrayList<String> printableMap = new ArrayList<String>();
	
	private String wallColor = "blue";
	private char paintBrush = 'F';
	
	public EditorBoard(Board board) {
		
		this.board = board;
		
		
		
		for(int i = 0; i < 16; i++) {
			map.add("BBBBBBBBBBBBBBBBBBBB");
		}
		
	
		addMouseListener(this);  
		
		try {
			tile = ImageIO.read(new File("src/images/editor_blank.png")).getScaledInstance(60, 45, Image.SCALE_AREA_AVERAGING);
			blank = ImageIO.read(new File("src/images/red.png")).getScaledInstance(60, 45, Image.SCALE_AREA_AVERAGING);
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("wat");
			e.printStackTrace();
		}
		
		
	}
	
	@Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        
        
        int y = 0;
        
       for(String line : map) {
    	   for(int x = 0; x < 19; x++) {
    		   
    		   switch(line.charAt(x)) {
    		   case 'B':
    			   graphics.drawImage(board.colors.get("editor"), x * 60, y * 45, null);
    			   break;
    		   case 'W':
    			   graphics.drawImage(board.colors.get(wallColor), x * 60, y * 45, null);
    			   break;
    		   case 'F':
    			   graphics.drawImage(board.colors.get("floor"), x * 60, y * 45, null);
    		   break;
    		   case 'G':
    			   graphics.drawImage(board.colors.get("goal"), x * 60, y * 45, null);
    			   break;
    		   }
    		   
    	   }
    	   y++;
       }
        
    }

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		StringBuilder repaint = new StringBuilder(map.get(e.getY() / 45));
		repaint.setCharAt(e.getX() / 60, paintBrush);
		map.set(e.getY() / 45, repaint.toString());
		
		revalidate();
		repaint();
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public JPanel editorMenu() {
		
		ButtonGroup radioButtons = new ButtonGroup();
    	
    	JRadioButton r1 = new JRadioButton("Wall");
    	r1.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {
		    paintBrush = 'W';
		  }
		});
    	
    	
    	JRadioButton r2 = new JRadioButton("Floor");
    	r2.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {
		    paintBrush = 'F';
		  }
		});
    	
    	
    	JRadioButton r3 = new JRadioButton("Goal");
    	r3.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {
		    paintBrush = 'G';
		  }
		});
    	
    	
    	JRadioButton r4 = new JRadioButton("Blank");
    	r4.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {
		    paintBrush = 'B';
		  }
		});
    	
    	JButton save = new JButton("Save");
    	save.addActionListener(new ActionListener()
    	{
    		
    		
    		
    		
    		public void actionPerformed(ActionEvent e)
    		{
    			
    			for(String s : map) {
    				printableMap.add(s);
    			}
    			
    			
    			CleanMap();
    			
    			char[][] wtf = new char[printableMap.size()][20];
    			
    			for(int i = 0; i < printableMap.size(); i++) {
    				wtf[i] = printableMap.get(i).toCharArray();
    			}
    			
    			
    			rotateCW(wtf);
    			
  
    			CleanMap();
    			
    			
    			
    			for(String s : printableMap) {
    				System.out.println(s);
    			}
    		
    			
    			
    			
    		
    		
    		}
    	});
    	
    	radioButtons.add(r1);
    	radioButtons.add(r2);
    	radioButtons.add(r3);
    	radioButtons.add(r4);
    	
    	bottomMenu.add(r1);
    	bottomMenu.add(r2);
    	bottomMenu.add(r3);
    	bottomMenu.add(r4);
    	bottomMenu.add(save);
    	
    	
    	return bottomMenu;
    	
	}
	
	
	//this is probably a dumb idea, i need to rotate the array 90°, however it is not exactly a 2d array, its an arraylist of strings.
	//Should have just made a [][] array instead from the beginning but if i do that i will want to change it in drawingboard class as well
	//and i am lazy so i dont care. Sue me, I dare you. 
	public void RotateMap() {
		
		char[][] wtf = new char[16][20];
		
		for(int i = 0; i < map.size(); i++) {
			wtf[i] = map.get(i).toCharArray();
		}
		
	}
	
	private void rotateCW(char[][] mat) {
		
		printableMap.clear();
		
	    final int M = mat.length;
	    final int N = mat[0].length;
	    char[][] ret = new char[N][M];
	    for (int r = 0; r < M; r++) {
	        for (int c = 0; c < N; c++) {
	            ret[c][M-1-r] = mat[r][c];
	        }
	    }
	    
	    
	    for(char[] arr : ret) {
	    	String str = String.valueOf(arr);
	    	printableMap.add(str);
	    }
	}
	
private void rotateCCW() {
		
	
		ArrayList<String> temp = printableMap;
		
		char[][] mat = new char[printableMap.size()][20];
		
		for(int i = 0; i < printableMap.size(); i++) {
			mat[i] = printableMap.get(i).toCharArray();
		}
		

		printableMap.clear();
		
	    final int M = mat.length;
	    final int N = mat[0].length;
	    char[][] ret = new char[N][M];
	    for (int r = 0; r < M; r++) {
	        for (int c = 0; c < N; c++) {
	            ret[c][M-1-r] = mat[r][c];
	        }
	    }
	    
	    
	    for(char[] arr : ret) {
	    	String str = String.valueOf(arr);
	    	printableMap.add(str);
	    }
	}
	
	
	
	public void CleanMap() {
		
		
		//printableMap.clear();
		ArrayList<String> temp = new ArrayList<String>();
		
		
		boolean notBlank = false;
		
		for(String line : printableMap) {
			notBlank = false;
			for(char c : line.toCharArray()) {
				if(c != 'B') {
					notBlank = true;
				}
				
			}
			if(notBlank) {
				temp.add(line);
			}
			
		}
		printableMap.clear();
		printableMap = temp;
	}

}


//graphics.drawImage(tile, j, i, null);