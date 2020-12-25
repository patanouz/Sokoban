package sokoban;

public class Main {
	public static void main(String[] args) {

		if(System.getProperty("os.name").equals("Linux")) {
			System.setProperty("sun.java2d.opengl", "true");
		}

		
		
		MainMenu menu = new MainMenu();
		

	}
}

/*

Sokoban TODO:

1. level editor!

2. class for saving games as text, also loading maps from files.

3. a win condition, example "next level" button.

4. graphics updates <- DONE!

5. a main menu

6. a background image

7. sick dubstep music

8. easter eggs <-- added a cheat code, is that enough?

9. ???

10. profit!


*/