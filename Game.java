import java.util.*;
import java.awt.event.KeyEvent;

public class Game {
    // Used to keep track of the game state.
    int lives = 5;
	int score = 0;
	int distance = 0;
	int difficulty = 4;
	
    // Used for screen construction and obstacle movement.
	int rows = 20;
	int columns = 80;
	int rocketRow = rows/2;
	int rocketCol = 2;

    // Used for key press (true) and release (false) detection.
	boolean up;
	boolean down;
	boolean right;
	boolean left;

    // Creating a 2d array to contain and display the game
	String screen[][] = new String[rows][columns];
	
	// Filling in the screen with empty spaces and a rocket.
	public Game() {
		for (int i=0; i<screen.length; i++) {
			for (int j=0; j<screen[i].length; j++) {
				screen[i][j] = " ";
			}
		}
		screen[rocketRow][rocketCol] = "🚀";
	}
    
    /*
    Activates or deactivates 4 variables representing direction as booleans.
    
    @param direction - int representing the keyboard arrow keys
    @param move - boolean for whether the key is pressed (true) or released (false)
    */
	public void setDirection(int direction, boolean move) {
		if (direction == KeyEvent.VK_UP) {
			up = move;
		}

		else if (direction == KeyEvent.VK_LEFT) {
			left = move;
		}
		
		else if (direction == KeyEvent.VK_DOWN) {
			down = move;
		}

		else if (direction == KeyEvent.VK_RIGHT) {
			right = move;
		}
	}

    /*
    Erases the current location of the rocket,
    changes its row and column based on which keys are activated/true,
    uses Math functions to prevent the rocket coordinates from exceeding the array dimensions,
    and places the rocket back into its new index.
    */
	public void moveSpaceship() {
		screen[rocketRow][rocketCol] = " ";
		
		if (up) {
			rocketRow = Math.max(rocketRow-1, 0);
		}

		if (left) {
			rocketCol = Math.max(rocketCol-1, 0);
		}

		if (down) {
			rocketRow = Math.min(rocketRow+1, rows-1);
		}
		
		if (right) {
			rocketCol = Math.min(rocketCol+1, columns-1);
		}

		screen[rocketRow][rocketCol] = "🚀";
	}

    /*
    Using the range 1 to the screen's width (rows),
    places a random astroid on the last column of the screen array.
	*/
	public void addAstroid() {
		int location = (int) (Math.random() * (rows-1));
		screen[location][columns-1] = "💥";
	}
	
    /*
    Using the range 1 to the screen's width (rows),
    places a random present on the last column of the screen array.
	*/
	public void addPresent() {
		int location = (int) (Math.random() * (rows-1));
		screen[location][columns-1] = "🎁";
	}

    /*
    For each element of the screen array,
        if the obstacle is an astroid or present, it gets deleted.
        If the obstacle is not in the beginning of the array (index 0), it gets moved back one column,
        otherwise deleted.
	*/
	public void moveObjects() {
		for (int i=0; i<rows; i++) {
			for (int j=0; j<columns; j++) {
				String obstacle = screen[i][j];
				if (obstacle == "💥" || obstacle == "🎁") {
					screen[i][j] = " ";
					
					if (j > 0) {
						screen[i][j-1] = obstacle;
					}
					
					/*
					If the object is in the same index as the rocket,
					if it is an astroid: lives decrease by 1,
					if it is a present: score increases by 30.
					*/
					if ((i == rocketRow && j == rocketCol) || (i == rocketRow && j == rocketCol+1) || (i == rocketRow && j == rocketCol-1)) {
					    screen[i][j-1] = " ";

						if (obstacle == "💥") {
					    	lives--;
						}
						
						else {
							this.shoot();
					    	score += 30;
						}
					}
				}
			}
		}
	}

    //Every String in the screen array after the column index of the rocket turn into a cloud.
	public void shoot() {
		for (int i=rocketCol+1; i<columns; i++) {
			if (screen[rocketRow][i] == "💥") {
				screen[rocketRow][i] = "💨";
			}
		}
	}

    // Sets the difficulty of the game.
	public void setDifficulty(int difficulty) {
	    this.difficulty = difficulty; 
	}
    
    // Returns whether the user has more than 0 lives as a boolean.
	public boolean isOngoing() {
	    return lives > 0;
	}

    /*
    Prints:
        screen column size amount of blocks,
        difficulty as a String (easy if 9, medium if 6, hard otherwise),
        score as an int,
        lives as heart emoji Strings,
        screen column size amount of blocks.
        
        for every row in the screen:
            a block and the entire row.
        
        screen column size amount of blocks.
	*/
	public void printScreen() {
		String diff = difficulty==9 ? "easy" : (difficulty==6 ? "medium" : "hard");

		System.out.println(String.join("", Collections.nCopies(columns, "◼️")));
		System.out.print("◼️ SPACE CRUISER ◼️ ");
		System.out.print("Difficulty: " + diff + " ◼️ ");
		System.out.print("Score: " + score + " ◼️ ");
		System.out.println("Lives: " + String.join(" ", Collections.nCopies(lives, "❤️")));
	    
		System.out.println(String.join("", Collections.nCopies(columns, "◼️")));

		for (String[] row : screen) {
			System.out.println("◼️" + String.join("", row));
		}

		System.out.println(String.join("", Collections.nCopies(columns, "◼️")));
	}

    // Returns a string representing the state of the game.
    public String toString(){
        return lives > 0 ? "Game Ongoing" : "Game Ended";
    }
}
