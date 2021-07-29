import java.util.*;
import java.util.concurrent.TimeUnit;
import java.awt.event.KeyEvent;

public class Game {
    int lives = 5;
	int score = 0;
	int distance = 0;
	int difficuilty = 4;
	
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

	public void moveSpaceship(int direction) {
		screen[rocketRow][rocketCol] = " ";
		if (direction == KeyEvent.VK_UP) {
			rocketRow = Math.max(rocketRow-1, 0);
		}

		else if (direction == KeyEvent.VK_LEFT) {
			rocketCol = Math.max(rocketCol-1, 0);
		}
		
		else if (direction == KeyEvent.VK_DOWN) {
			rocketRow = Math.min(rocketRow+1, rows-1);
		}

		else if (direction == KeyEvent.VK_RIGHT) {
			rocketCol = Math.min(rocketCol+1, columns-1);
		}
		screen[rocketRow][rocketCol] = "🚀";
	}

	public void addAstroid() {
		int location = (int) (Math.random() * (rows-1));
		screen[location][columns-1] = "💥";
	}

	public void moveAstroids() {
		for (int i=0; i<rows; i++) {
			for (int j=0; j<columns; j++) {
				if (screen[i][j] == "💥") {
					screen[i][j] = " ";
					if (j > 0) {
						screen[i][j-1] = "💥";
					}
					if ((i == rocketRow && j == rocketCol) || (i == rocketRow && j == rocketCol+1)) {
					    lives--;
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

	public void printStats() {
		String diff = difficuilty==7 ? "easy" : (difficuilty==4 ? "medium" : "hard");

		System.out.println(String.join("", Collections.nCopies(columns, "◼️")));
		System.out.print("◼️ SPACE CRUISER ◼️ ");
		System.out.print("Difficuilty: " + diff + " ◼️ ");
		System.out.print("Score: " + score + " ◼️ ");
		System.out.println("Lives: " + String.join(" ", Collections.nCopies(lives, "❤️")));
	}

	public void printScreen() {
	    this.moveSpaceship(0);
		System.out.println(String.join("", Collections.nCopies(columns, "◼️")));

		for (int i = 0; i<rows; i++) {
			System.out.println("◼️" + String.join("", screen[i]));
		}
		System.out.println(String.join("", Collections.nCopies(columns, "◼️")));
	}

    public String toString(){
        return lives > 0 ? "Game Ongoing" : "Game Ended";
    }
}
