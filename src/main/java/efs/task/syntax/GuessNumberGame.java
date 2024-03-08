package efs.task.syntax;

import java.util.Scanner;

public class GuessNumberGame {
    private final int numberToGuess;
    private final int maxAttempts;
    private final int upperBound;

    public GuessNumberGame(String argument) throws IllegalArgumentException {
        int M;
        try {
            M = Integer.parseInt(argument);
        } catch (NumberFormatException e) {
            System.out.println(UsefulConstants.WRONG_ARGUMENT);
            throw new IllegalArgumentException(UsefulConstants.WRONG_ARGUMENT);
        }

        if (M < 1 || M > UsefulConstants.MAX_UPPER_BOUND) {
            System.out.println(UsefulConstants.WRONG_ARGUMENT);
            throw new IllegalArgumentException(UsefulConstants.WRONG_ARGUMENT);
        }

        this.upperBound = M;
        this.numberToGuess = (int) (Math.random() * M) + 1;
        this.maxAttempts = (int) (Math.log(M) / Math.log(2)) + 1;
    }

    public void play() {
        System.out.println("Zagrajmy. Zgadnij liczbę z zakresu <1," + this.upperBound + ">");
        Scanner scanner = new Scanner(System.in);
        int attempts = 0;

        while (attempts < maxAttempts) {

            printProgressBar(attempts + 1, maxAttempts - attempts - 1);
            System.out.println(UsefulConstants.GIVE_ME);
            String input = scanner.nextLine();
            int guess;

            try {
                guess = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("'" + input + "' to " + UsefulConstants.NOT_A_NUMBER);
                attempts++;
                continue;
            }

            attempts++;

            if (guess == this.numberToGuess) {
                System.out.println(UsefulConstants.YES);
                System.out.println(UsefulConstants.CONGRATULATIONS + ", " + attempts + " - tyle prób zajęło Ci odgadnięcie liczby " + this.numberToGuess);
                return;
            } else if (guess < this.numberToGuess) {
                System.out.println(UsefulConstants.TO_LESS);
            } else {
                System.out.println(UsefulConstants.TO_MUCH);
            }
        }

        System.out.println(UsefulConstants.UNFORTUNATELY + ", wyczerpałeś limit prób (" + maxAttempts + ") do odgadnięcia liczby " + this.numberToGuess);
    }




    private void printProgressBar(int attemptsMade, int attemptsLeft) {
        System.out.print("Twoje próby: [");
        for (int i = 0; i < attemptsMade; i++) {
            System.out.print("*");
        }
        for (int i = 0; i < attemptsLeft; i++) {
            System.out.print(".");
        }
        System.out.println("]");
    }


    public static void main(String[] args) {
        try {
            GuessNumberGame game = new GuessNumberGame(args.length > 0 ? args[0] : "");
            game.play();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
