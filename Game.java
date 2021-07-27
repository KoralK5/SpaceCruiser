 import java.util.*;
import java.util.concurrent.TimeUnit;

public class Game {
    private boolean alive = true;
	private int score = 0;
	private int difficuilty;
	private int rows = 10;
	private int columns = 50;

	public int setDifficuilty(int difficuilty) {
	    return this.difficuilty = difficuilty; 
	}

	public void wait(int seconds) {
		try {
			TimeUnit.SECONDS.sleep(seconds);
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
		System.out.print("SPACE CRUISER | ");
		System.out.print("Difficuilty: " + diff + " | ");
		System.out.println("Score: " + this.score);
	}

	public void printScreen() {
		System.out.println("-".repeat(this.columns));

		for (int i = 0; i<this.rows; i++) {
			System.out.println("|" + " ".repeat(this.columns-2) + "|");
		}

		System.out.println("-".repeat(this.columns));
	}

    public String toString(){
        return alive ? "Game Ongoing!" : "Game Ended!";
    }
}
