package sokoban;

import java.io.*;
import java.util.ArrayList;

public class mapReader {

	BufferedReader br;
	private static ArrayList<String> map = new ArrayList<String>();
	private static ArrayList<box> boxes = new ArrayList<box>();
	private static Player player;
	private static String color;
	
	private DrawingBoard draw;

	
	
	//TODO: i board's levelbump så skapar jag ett nytt mapreader-object för varje bana
	//borde skriva om det istället. 
	
	public mapReader(int level, DrawingBoard draw) {
		
		this.draw = draw;
		
		File file = new File("src/sokoban/map");

		try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String line;

		try {
			while ((line = br.readLine()) != null) {
				if (line.contains("level " + level + " ")) {
					line = br.readLine();
					color = line;
					line = br.readLine();

					while (!line.isEmpty()) {

						map.add(line);
						line = br.readLine();
					}
					line = br.readLine();
					line = br.readLine();
					line = br.readLine();

					String[] XandY = line.split(" ");

					int px = Integer.parseInt(XandY[0]);
					int py = Integer.parseInt(XandY[1]);

					int playerX = (px * 60);
					int playerY = (py * 45);

					player = new Player(playerX, playerY, px, py);
					try {
					draw.newFigure(player);
					player.reset(px, py);
					} catch(Exception e) {
						
					}

					line = br.readLine();
					line = br.readLine();
					line = br.readLine();
					line = br.readLine();

					while (!line.isEmpty() && line != null) {

						String[] boxxy = line.split(" ");

						int bx = Integer.parseInt(boxxy[0]);
						int by = Integer.parseInt(boxxy[1]);

						boxes.add(new box(bx, by));

						line = br.readLine();
					}

				}

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void removePlayer() {
		player = null;
	}

	public static Player getPlayer() {
		return player;
	}

	public static ArrayList<String> getMap() {
		return map;
	}

	public static ArrayList<box> getBoxes() {
		return boxes;
	}
	
	public String getColor() {
		return color;
	}
	
	public int getMapHeight() {
		return map.size() * 45;
	}
	
	public int getMapWidth() {
		return map.get(0).length() * 60;
	}

}
