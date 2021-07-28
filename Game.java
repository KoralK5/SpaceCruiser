import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.Arrays;

public class Game {
    boolean alive = true;
	int score = 0;
	int distance = 0;
	int difficuilty = 4;
	
	int rows = 20;
	int columns = 80;

	int rocketRow = rows/2;
	int rocketCol = 1;

	String screen[][] = new String[rows][columns];
	
	public Game() {
		for (int i=0; i<this.screen.length; i++) {
			for (int j=0; j<this.screen[i].length; j++) {
				this.screen[i][j] = " ";
			}
		}
		this.screen[rocketRow][rocketCol] = "🚀";
	}

	public void moveSpaceship(char direction) {
		this.screen[rocketRow][rocketCol] = " ";
		if (direction == 'w') {
			rocketRow++;
		}

		if (direction == 'a') {
			rocketCol--;
		}
		
		if (direction == 's') {
			rocketRow--;
		}

		if (direction == 'd') {
			rocketCol++;
		}
		this.screen[rocketRow][rocketCol] = "🚀";
	}

	public void addAstroid() {
		int location = (int) (Math.random() * (rows-1));
		this.screen[location][columns-1] = "💥";
	}

	public void moveAstroids() {
		for (int i=0; i<this.screen.length; i++) {
			for (int j=0; j<this.screen[i].length; j++) {
				if (this.screen[i][j] == "💥") {
					this.screen[i][j] = " ";
					if (j > 0) {
						this.screen[i][j-1] = "💥";
					}
				}
			}
		}
	}

	public int setDifficuilty(int difficuilty) {
	    return this.difficuilty = difficuilty; 
	}

	public void wait(int ms) {
		try {
			TimeUnit.MILLISECONDS.sleep(ms);
		}
		catch(InterruptedException e) {}
	}

	public void countdown() {
		Main.clear();
		System.out.print("\t\t\t\t\t\t|\n".repeat(2));
		System.out.println("\t\t\t\t\t\t3");
		wait(1);

		Main.clear();
		System.out.print("\t\t\t\t\t\t|\n".repeat(4));
		System.out.print("\t\t\t\t\t\t2");
		wait(1);

		Main.clear();
		System.out.print("\t\t\t\t\t\t|\n".repeat(6));
		System.out.print("\t\t\t\t\t\t1");
		wait(1);
	}

	public void printStats() {
		String diff = this.difficuilty==1 ? "easy" : (this.difficuilty==2 ? "medium" : "hard");

		System.out.println("-".repeat(this.columns));
		System.out.print("| SPACE CRUISER | ");
		System.out.print("Difficuilty: " + diff + " | ");
		System.out.println("Score: " + this.score);
	}

	public void printScreen() {
		System.out.println("-".repeat(this.columns));

		for (int i = 0; i<this.rows; i++) {
			System.out.println("|" + String.join("", this.screen[i]));
		}
		System.out.println("-".repeat(this.columns));
	}

    public String toString(){
        return alive ? "Game Ongoing!" : "Game Ended!";
    }
}
