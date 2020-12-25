package sokoban;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class MainMenu {

	private JFrame frame;

	private JButton newGameButton;
	private JButton editorButton;
	private JButton settingsButton;
	private JButton exit;

	private final graphics game;
	
	
	public MainMenu() {

		Board board = new Board();
		game = new graphics(board.getPlayer(), board);
		initialize();
	}


	
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		newGameButton = new JButton("Play game");

		JButton highscoreButton = new JButton("High Score");

		settingsButton = new JButton("Settings");
		
		editorButton = new JButton("Level editor");
		
		exit = new JButton("Exit");
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(156)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(exit, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(newGameButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(highscoreButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(settingsButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(editorButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap(164, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(70)
					.addComponent(newGameButton)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(highscoreButton)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(editorButton)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(settingsButton)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(exit)
					.addContainerGap(74, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
		
		setActionListeners();
		
		frame.pack();
		frame.setVisible(true);
		
		
	}
	
	public void setActionListeners() {
		newGameButton.addActionListener(e -> game.GameBoard());
		
		editorButton.addActionListener(e -> game.EditorBoard());

		settingsButton.addActionListener(e -> JOptionPane.showMessageDialog(frame, "//TODO: finish making this window"));
		
		exit.addActionListener(e -> System.exit(0));
		
	}
}
