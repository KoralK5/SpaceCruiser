import java.util.*;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.io.File;
import java.io.FileNotFoundException;

class Main {
	static Scanner input = new Scanner(System.in);
	static Game game = new Game();

	public static void main(String[] args) {
		clear();
		art();
		space(4);
		System.out.println("\t\t\t\t\t\tWELCOME TO SPACE CRUISER!");
		space(4);
		System.out.print("\t\t\t\t\t\t  Press enter to play:  ");
		input.nextLine();
		
		clear();
		art();
		space(4);
		story();

		clear();
		art();
		space(4);
		rules();
		
		clear();
		art();
		space(4);
		game.setDifficuilty(chooseDifficuilty());
		game.countdown();
		
		while (true) {
			game.distance++;

			clear();
			game.printStats();
			game.printScreen();
			
			if (game.distance % game.difficuilty == 0 && Math.random() > 0.8) {
				game.addAstroid();
			}
		}
	}

	public static void clear() {
		System.out.println("\033[H\033[2J");
	}

	public static void space(int lines) {
		System.out.println("\n".repeat(lines));
	}

	public static void art() {
		try {
			File myObj = new File("Spaceship.txt");
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				System.out.println(data);
			}
		myReader.close();
		}

		catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	public static void story() {
		System.out.println("Ever since the Earth was destroyed, humanity has been left stranded in a space cruiser with little to no resources left.\n");
		System.out.println("With the objective of searching for a sustainable planet, you set foot in your spaceship and set sail into the abyss of space...\n");
		System.out.println("Good luck!\n");
		System.out.print("Continue? ");
		input.nextLine();
	}

	public static void rules() {
		System.out.println("\t\t\t\t\t\t            THE RULES\n");
		System.out.println("\t\t\t\t\t\t1: Press {enter} to switch directions");
		System.out.println("\t\t\t\t\t\t2: Avoid all obstancles");
		System.out.println("\t\t\t\t\t\t3: Survive as long as possible");
		System.out.print("\nContinue? ");
		input.nextLine();
	}

	public static int chooseDifficuilty() {
		System.out.println("\t\t\t\t\t\t            CHOOSE DIFFICUILTY\n");
		System.out.println("\t\t\t\t\t\t				1: Easy\t\t\tless obstacles");
		System.out.println("\t\t\t\t\t\t				2: Medium\t\t\t  ||");
		System.out.println("\t\t\t\t\t\t				3: Hard\t\t\tmore obstacles");
		System.out.print("\nDifficuilty (1|2|3): ");
		return 12 - input.nextInt() * 3;
	}
}
