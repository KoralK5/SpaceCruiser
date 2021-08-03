import java.util.*;
import java.io.*;
import javax.swing.*;
import java.lang.Math;
import java.util.concurrent.TimeUnit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class Main {
    static Scanner input = new Scanner(System.in);
    static Game game = new Game();

    public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setVisible(true);
		frame.setFocusable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent event) {
				game.setDirection(event.getKeyCode(), true);
			}

			@Override
			public void keyReleased(KeyEvent event) {
				game.setDirection(event.getKeyCode(), false);
			}

			@Override
			public void keyTyped(KeyEvent e) {
			}
		});

		JPanel panel = new JPanel();
		frame.add(panel);

        boolean exit = false;
        int record = 0;

		while (!exit) {
            clear();
            art();
            space(4);
            System.out.println("\t\tWELCOME TO SPACE CRUISER!");
            space(4);
            System.out.print("\t\t  Press enter to play:  ");
            input.nextLine();

            clear();
            art();
            space(2);
            story();

            clear();
            art();
            rules();

            clear();
            art();
            space(2);
            game.setDifficuilty(chooseDifficuilty());
            countdown();

            while (game.isOngoing()) {
                wait(game.difficuilty*15 - game.distance/100);
                game.distance++;

                clear();
                game.moveObjects();
				game.moveSpaceship();
                game.printScreen();

				if (game.score % 30 == 0) {
					game.shoot();
				}
    			
                if (game.distance % game.difficuilty == 0 && Math.random() > 0.4) {
                    game.score++;
                    game.addAstroid();
    				
                    if (Math.random() > 0.9) {
                        game.addPresent();
                    }
                }
            }

			input.nextLine();
            clear();
            art();
            space(2);
            if (game.score > record) {
                System.out.println("\t\t\tNEW RECORD!!!");
                record = game.score;
            }

            System.out.println("\n\t\t\tScore: " + game.score);
            System.out.println("\t\t\tDistance: " + game.distance);

            System.out.print("\n\nGo again? ([Y]es or [N]o): ");
            String choice = input.nextLine().toLowerCase();

            exit = (choice == "n" || choice == "no");
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
			File ship = new File("Spaceship.txt");
			Scanner fileReader = new Scanner(ship);
			while (fileReader.hasNextLine()) {
				String data = fileReader.nextLine();
				System.out.println(data);
			}
			fileReader.close();
		}

		catch (FileNotFoundException e) {
			System.out.println("Spaceship.txt is not readable or does not exist.");
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
		System.out.println("\t\t            THE RULES");
		System.out.println("\t\t1: Click the graphics screen once");
		System.out.println("\t\t2: Use arrow keys to avoid all obstacles");
		System.out.println("\t\t3: Survive as long as possible");
		System.out.println("\nYou shoot every time you get 30 points");
		System.out.println("\tYour Rocket         : 🚀");
		System.out.println("\tHarmful Astroid     : 💥");
		System.out.println("\tHarmless Astroid    : 💨");
		System.out.println("\tPresent (30 points) : 🎁");
		System.out.print("\nContinue? ");
		input.nextLine();
	}

	public static int chooseDifficuilty() {
		System.out.println("\n\t\t\tCHOOSE DIFFICUILTY\n");
		System.out.println("\t\t\t\t1: Easy");
		System.out.println("\t\t\t\t2: Medium");
		System.out.println("\t\t\t\t3: Hard");
		System.out.print("\n\nDifficuilty (1|2|3): ");
		return 12 - Math.min(3, Math.max(1, input.nextInt())) * 3;
	}
	
	public static void wait(int ms) {
		try {
			TimeUnit.MILLISECONDS.sleep(ms);
		}
		catch(InterruptedException e) {
		}
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
