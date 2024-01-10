package game;

import java.util.LinkedList;
import java.util.Scanner;

public class GameSetup {
    private final String playerName;
    private final int maxLives;
    private final int maxQuestions;

    public GameSetup(String playerName, int maxLives, int maxQuestions) {
        this.playerName = playerName;
        this.maxLives = maxLives;
        this.maxQuestions = maxQuestions;
    }

    public void start() {
        LinkedList<String> allQuestions = Read.readAllQuestionsFromFiles(getQuestionFiles());
        LinkedList<String> allAnswers = Read.readAllAnswersFromFiles(getAnswerFiles());

        int lives = maxLives;
        int correctAnswers = 0;
        int totalQuestionsAsked = 0;

        while (correctAnswers < maxQuestions && lives > 0) {
            int category = GameFunctions.rollDice(1, 6);
            LinkedList<String> questionAnswerPair = GameFunctions.pickQuestionAndAnswer(allQuestions, allAnswers, category);

            String question = questionAnswerPair.get(0);
            String correctAnswer = questionAnswerPair.get(1);

            System.out.print("Your answer: ");
            Scanner scanner = new Scanner(System.in);
            String userAnswer = scanner.nextLine();

            if (GameFunctions.checkAnswer(userAnswer, correctAnswer)) {
                correctAnswers++;
            } else {
                lives--;
            }

            totalQuestionsAsked++;
        }

        System.out.println("\nGame over for " + playerName + "!");
        System.out.println("Total correct answers: " + correctAnswers);
        System.out.println("Total questions asked: " + totalQuestionsAsked);
        System.out.println("Lives remaining: " + lives);
    }

    private LinkedList<String> getQuestionFiles() {
        // Return a list of question file names
        // Customize this method based on your file naming conventions
        LinkedList<String> questionFiles = new LinkedList<>();
        questionFiles.add("questions_file.csv");
//        questionFiles.add("questions_file2.csv");
        // Add more files if needed
        return questionFiles;
    }

    private LinkedList<String> getAnswerFiles() {
        // Return a list of answer file names
        // Customize this method based on your file naming conventions
        LinkedList<String> answerFiles = new LinkedList<>();
        answerFiles.add("questions_file.csv");
        //  answerFiles.add("answers_file2.csv");
        // Add more files if needed
        return answerFiles;
    }
}
