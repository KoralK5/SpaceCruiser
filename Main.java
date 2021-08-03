import java.util.*;
import java.io.*;
import javax.swing.*;
import java.lang.Math;
import java.util.concurrent.TimeUnit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class Main {
    static Scanner input = new Scanner(System.in);
    static Game game;
    
    public static void main(String[] args) {
        // Using javax.swing, an interactable server graphics screen is created
		JFrame frame = new JFrame();
		frame.setVisible(true);
		frame.setFocusable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// A KeyListener method is called on the graphics screen frame.
		frame.addKeyListener(new KeyListener() {
		    // @Overrides overrides any other class methods named keyPressed, keyReleased, and keyTyped.

		    // When a key is pressed, the key's integer representation is passed into the setDirection method
		    // using the getKeyCode method along with true, indicating that it's pressed.
			@Override
			public void keyPressed(KeyEvent event) {
				game.setDirection(event.getKeyCode(), true);
			}

		    // When a key is released, the key's integer representation is passed into the setDirection method
		    // using the getKeyCode method along with false, indicating that it's released.
			@Override
			public void keyReleased(KeyEvent event) {
				game.setDirection(event.getKeyCode(), false);
			}

            // This method is unnecessary, but is required for the addKeyListener function.
			@Override
			public void keyTyped(KeyEvent e) {
			}
		});
        
        // An empty panel is created to host the frame on.
		JPanel panel = new JPanel();
		frame.add(panel);

        int record = 0;
		while (true) {
		    game = new Game();
		    
		    // Helper methods are used to introduce the game.
            clear();
            art();
            space(4);
            System.out.println("\t\tWELCOME TO SPACE CRUISER!");
            space(4);
            System.out.print("\t\t  Press enter to play: ");
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
            game.setDifficulty(chooseDifficulty());
            countdown();

            while (game.isOngoing()) {
                // The game difficulty and distance are used to adjust the game speed.
                wait(game.difficulty*15 - game.distance/100);
                game.distance++;
                
                // Object locations are changed and displayed repetitively.
                clear();
                game.moveObjects();
				game.moveSpaceship();
                game.printScreen();
                
                // The rocket fires if it gets a present (+30 points) or 30 points on its own.
				if (game.score % 30 == 0) {
					game.shoot();
				}
    			
    			// The randomness of astroid spawns are determined by the difficulty.
                if (game.distance % game.difficulty == 0 && Math.random() > 0.4) {
                    game.score++;
                    game.addAstroid();
    				
                    if (Math.random() > 0.9) {
                        game.addPresent();
                    }
                }
            }
            
            // An empty scanner is used to cancel out ghost inputs caused by the KeyListener.
			input.nextLine();
            clear();
            art();
            space(2);
            
            // The user's personal best is stored and used for congratulating.
            if (game.score > record) {
                System.out.println("\t\t\tNEW RECORD!!!");
                record = game.score;
            }

            System.out.println("\n\t\t\tScore: " + game.score);
            System.out.println("\t\t\tDistance: " + game.distance);

            System.out.print("\n\nGo again? ([Y]es or [N]o): ");
            String choice = input.nextLine().toLowerCase();
            
            // The game ends if the user inputs "n" or "no".
            if (choice.equals("n") || choice.equals("no")) {
                clear();
                art();
                space(2);
                System.out.println("\t\t\tThanks For Playing!");
                space(1);
                System.out.println("\t\t\tBest Score: " + record);
                break;
            }
		}
	}
    
    // Prints the special string to erase console text.
    public static void clear() {
		System.out.println("\033[H\033[2J");
    }

    // Prints variable lines amount of spaces by repetition using the nCopies method.
    public static void space(int lines) {
		System.out.println(String.join("", Collections.nCopies(lines, "\n")));
    }

    // Reads and prints the Spaceship.txt file line by line until there is no next line
    // using a scaner. Returns an error message upon facing the FileNotFoundException.
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

    // Prints out a story.
    public static void story() {
		System.out.println("Ever since the Earth was destroyed, humanity has been left stranded in a space cruiser with little to no resources left.\n");
        System.out.println("With the objective of searching for a sustainable planet, you set foot in your spaceship and set sail into the abyss of space...\n");
        System.out.println("Good luck!\n");
        System.out.print("Continue? ");
        input.nextLine();
	}

    // Prints out the rules.
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

    // Prints out the difficulties and uses the Math class to shrink down numbers from a 1-3 range.
    // Then, adjusts the difficulty accordingly for the game.
	public static int chooseDifficulty() {
		System.out.println("\n\t\t\tCHOOSE DIFFICULTY\n");
		System.out.println("\t\t\t\t1: Easy");
		System.out.println("\t\t\t\t2: Medium");
		System.out.println("\t\t\t\t3: Hard");
		System.out.print("\n\nDifficulty (1|2|3): ");
		return 12 - Math.min(3, Math.max(1, input.nextInt())) * 3;
	}
	
	// Uses the java.util library's TimeUnit class to sleep variable ms miliseconds.
	// Upon facing an error, it doesn't sleep.
	public static void wait(int ms) {
		try {
			TimeUnit.MILLISECONDS.sleep(ms);
		}
		catch(InterruptedException e) {
		}
	}

	// Uses the wait method above and some ascii art to simulate a countdown.
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
