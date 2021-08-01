import java.util.*;
import java.awt.event.KeyEvent;

public class Game {
    int lives = 5;
	int score = 0;
	int distance = 0;
	int difficuilty = 4;

	boolean up;
	boolean down;
	boolean right;
	boolean left;
	
	int rows = 20;
	int columns = 80;
	int rocketRow = rows/2;
	int rocketCol = 2;

	String screen[][] = new String[rows][columns];
	
	public Game() {
		for (int i=0; i<screen.length; i++) {
			for (int j=0; j<screen[i].length; j++) {
				screen[i][j] = " ";
			}
		}
		screen[rocketRow][rocketCol] = "🚀";
	}

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

	public void addAstroid() {
		int location = (int) (Math.random() * (rows-1));
		screen[location][columns-1] = "💥";
	}
	
	public void addPresent() {
		int location = (int) (Math.random() * (rows-1));
		screen[location][columns-1] = "🎁";
	}

	public void moveObjects() {
		for (int i=0; i<rows; i++) {
			for (int j=0; j<columns; j++) {
				String obstacle = screen[i][j];
				if (obstacle == "💥" || obstacle == "🎁") {
					screen[i][j] = " ";
					
					if (j > 0) {
						screen[i][j-1] = obstacle;
					}
					
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

	public void shoot() {
		for (int i=rocketCol+1; i<columns; i++) {
			if (screen[rocketRow][i] == "💥") {
				screen[rocketRow][i] = "💨";
			}
		}
	}

	public void setDifficuilty(int difficuilty) {
	    this.difficuilty = difficuilty; 
	}

	public void addScore(int score) {
	    this.score += score;
	}
	
	public boolean isOngoing() {
	    return lives > 0;
	}

	public void printScreen() {
		String diff = difficuilty==9 ? "easy" : (difficuilty==6 ? "medium" : "hard");

		System.out.println(String.join("", Collections.nCopies(columns, "◼️")));
		System.out.print("◼️ SPACE CRUISER ◼️ ");
		System.out.print("Difficuilty: " + diff + " ◼️ ");
		System.out.print("Score: " + score + " ◼️ ");
		System.out.println("Lives: " + String.join(" ", Collections.nCopies(lives, "❤️")));
	    
		System.out.println(String.join("", Collections.nCopies(columns, "◼️")));

		for (String[] row : screen) {
			System.out.println("◼️" + String.join("", row));
		}

		System.out.println(String.join("", Collections.nCopies(columns, "◼️")));
	}

    public String toString(){
        return lives > 0 ? "Game Ongoing" : "Game Ended";
    }
}
