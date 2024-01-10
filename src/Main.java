import game.GameSetup;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter player's name: ");
        String playerName = scanner.nextLine();

        System.out.print("Enter number of lives: ");
        int lives = scanner.nextInt();

        System.out.print("Enter max correct questions: ");
        int maxQuestions = scanner.nextInt();

        GameSetup gameSetup = new GameSetup(playerName, lives, maxQuestions);
        gameSetup.start();
    }
}
