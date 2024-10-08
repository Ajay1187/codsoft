import java.util.*;
public class NumberGuessingGame {
    static ArrayList<Integer> scoreBoard = new ArrayList<>();

    public static void main(String[] args) {
        NumberGuessingGame game = new NumberGuessingGame();
        game.menu();
    }

    public void menu() {
        Scanner input = new Scanner(System.in);

        System.out.println("--------------------");
        System.out.println("Welcome to the Number Guessing Game");
        System.out.println("1) Play the Game");
        System.out.println("2) Score Board");
        System.out.println("3) Exit the game");
        System.out.println("--------------------");

        try {
            System.out.print("What action would you like to do from the above actions? ");
            int menuOption = input.nextInt();

            switch (menuOption) {
                case 1:
                    int numberRange = 100;  // Specify the range for the random number
                    int randomNumber = randomNumber(numberRange);
                    guessNumber(randomNumber);
                    break;
                case 2:
                    displayScoreBoard();
                    break;
                case 3:
                    System.out.println("\nThanks for playing the game!");
                    System.exit(0);
                    break;
                default:
                    throw new InputMismatchException("Invalid number entry. Please try again.");
            }
        } catch (InputMismatchException e) {
            System.err.println("\n" + e.getMessage() + "\n");
            input.nextLine();  // Clear the buffer
            menu();  // Restart the menu
        }
    }

    public int randomNumber(int numberRange) {
        Random random = new Random();
        return random.nextInt(numberRange) + 1;
    }

    public void guessNumber(int randomNumber) {
        Scanner input = new Scanner(System.in);
        int userGuess;
        int attempts = 0;
        int maxAttempts = 7;

        do {
            System.out.print("Enter your guess number (1-100): ");
            userGuess = input.nextInt();
            attempts++;

            if (userGuess > randomNumber) {
                System.out.println("Entered number is too high.");
            } else if (userGuess < randomNumber) {
                System.out.println("Entered number is too low.");
            }

            if (attempts >= maxAttempts && userGuess != randomNumber) {
                System.out.println("You've reached the maximum number of attempts. The correct number was: " + randomNumber);
                break;
            }
        } while (userGuess != randomNumber);

        if (userGuess == randomNumber) {
            System.out.println();
            if (attempts == 1) {
                System.out.println("You guessed the number correctly in " + attempts + " try!");
            } else {
                System.out.println("You guessed the number correctly in " + attempts + " tries!");
            }
            scoreBoard.add(attempts);
        }

        System.out.println();
        menu();  // Return to the menu after guessing
    }

    public void displayScoreBoard() {
        System.out.println("--------------------");
        System.out.println("Score Board");
        System.out.println("--------------------");
        System.out.println("Your fastest games today out of all tries are: \n");

        Collections.sort(scoreBoard);
        for (Integer score : scoreBoard) {
            System.out.println("Finished the number game in " + score + " tries");
        }

        System.out.println();
        menu();  // Return to the menu after displaying the scoreboard
    }
}

