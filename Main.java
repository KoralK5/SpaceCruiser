import java.util.*;
import java.io.*;
import java.util.concurrent.TimeUnit;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

class Main {
	static Scanner input = new Scanner(System.in);
	static Game game = new Game();

	public static void main(String[] args) {
		clear();
		art();
		space(4);
		System.out.println("\t\tWELCOME TO SPACE CRUISER!");
		space(4);
		System.out.print("\t\t  Press enter to play:  ");
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
		countdown();

		JFrame frame = new JFrame();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 400);
		frame.setFocusable(true);

		JPanel panel = new JPanel();
		
		frame.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent event) {
				int typed = event.getKeyCode();
				game.moveSpaceship(typed);
			}

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}
		});

		frame.add(panel);

		while (true) {
			wait(100);
			game.distance++;

			clear();
			game.printStats();
			game.printScreen();

			game.moveAstroids();
			if (game.distance % game.difficuilty == 0 && Math.random() > 0.8) {
				game.score++;
				game.addAstroid();
			}
		}
	}

	public static void clear() {
		System.out.println("\033[H\033[2J");
	}

	public static void space(int lines) {
		System.out.println(String.join("", Collections.nCopies(lines, "\n")));
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
		System.out.println("\t\t            THE RULES\n");
		System.out.println("\t\t1: Press {enter} to switch directions");
		System.out.println("\t\t2: Avoid all obstancles");
		System.out.println("\t\t3: Survive as long as possible");
		System.out.print("\nContinue? ");
		input.nextLine();
	}

	public static int chooseDifficuilty() {
		System.out.println("\t\t            CHOOSE DIFFICUILTY\n");
		System.out.println("\t\t1: Easy\t\t\tless obstacles");
		System.out.println("\t\t2: Medium\t\t\t|");
		System.out.println("\t\t3: Hard\t\t\tmore obstacles");
		System.out.print("\nDifficuilty (1|2|3): ");
		return 12 - input.nextInt() * 3;
	}
	
	public static void wait(int ms) {
		try {
			TimeUnit.MILLISECONDS.sleep(ms);
		}
		catch(InterruptedException e) {}
	}

	public static void countdown() {
		Main.clear();
		System.out.print(String.join("", Collections.nCopies(2, "\t\t\t\t|\n")));
		System.out.println("\t\t\t\t3");
		wait(1000);

		Main.clear();
		System.out.print(String.join("", Collections.nCopies(4, "\t\t\t\t|\n")));
		System.out.print("\t\t\t\t2");
		wait(1000);

		Main.clear();
		System.out.print(String.join("", Collections.nCopies(6, "\t\t\t\t|\n")));
		System.out.print("\t\t\t\t1");
		wait(1000);
	}
}
