import java.util.*;
import java.util.concurrent.TimeUnit;
import java.awt.event.KeyEvent;

public class Game {
    boolean alive = true;
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
			rocketRow--;
		}

		else if (direction == KeyEvent.VK_LEFT) {
			rocketCol--;
		}
		
		else if (direction == KeyEvent.VK_DOWN) {
			rocketRow++;
		}

		else if (direction == KeyEvent.VK_RIGHT) {
			rocketCol++;
		}
		screen[rocketRow][rocketCol] = "🚀";
	}

	public void addAstroid() {
		int location = (int) (Math.random() * (rows-1));
		screen[location][columns-1] = "💥";
	}

	public void moveAstroids() {
		for (int i=0; i<screen.length; i++) {
			for (int j=0; j<screen[i].length; j++) {
				if (screen[i][j] == "💥") {
					screen[i][j] = " ";
					if (j > 0) {
						screen[i][j-1] = "💥";
					}
				}
			}
		}
	}

	public int setDifficuilty(int difficuilty) {
	    return this.difficuilty = difficuilty; 
	}

	public int addScore(int score) {
	    return this.score += score; 
	}


	public void printStats() {
		String diff = difficuilty==1 ? "easy" : (difficuilty==2 ? "medium" : "hard");

		System.out.println(String.join("", Collections.nCopies(columns, "◼️")));
		System.out.print("◼️ SPACE CRUISER ◼️ ");
		System.out.print("Difficuilty: " + diff + " ◼️ ");
		System.out.println("Score: " + score);
	}

	public void printScreen() {
		System.out.println(String.join("", Collections.nCopies(columns, "◼️")));

		for (int i = 0; i<rows; i++) {
			System.out.println("◼️" + String.join("", screen[i]));
		}
		System.out.println(String.join("", Collections.nCopies(columns, "◼️")));
	}

    public String toString(){
        return alive ? "Game Ongoing!" : "Game Ended!";
    }
}
