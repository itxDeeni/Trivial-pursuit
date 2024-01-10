package game;

import java.util.LinkedList;
import java.util.Random;

public class GameFunctions {
    public static LinkedList<String> pickQuestionAndAnswer(LinkedList<String> questions, LinkedList<String> answers, int randomCategory) {
        if (questions.isEmpty() || answers.isEmpty()) {
            System.err.println("No questions or answers available. Game cannot continue.");
            return new LinkedList<>();
        }

        Random random = new Random();
        int randomIndex = random.nextInt(questions.size());

        String pickedQuestion = questions.remove(randomIndex);
        String pickedAnswer = answers.remove(randomIndex);

        LinkedList<String> result = new LinkedList<>();
        result.add(pickedQuestion);
        result.add(pickedAnswer);

        System.out.println("Category " + randomCategory + ": " + pickedQuestion);

        return result;
    }

    public static int rollDice(int min, int max) {
        System.out.println("Rolling the dice...");
        return new Random().nextInt(max - min + 1) + min;
    }

    public static boolean checkAnswer(String userInput, String realAnswer) {
        if (userInput == null || realAnswer == null) {
            System.err.println("Invalid input for answer comparison. Returning false.");
            return false;
        }

        boolean isCorrect = userInput.trim().equalsIgnoreCase(realAnswer.trim());

        if (isCorrect) {
            System.out.println("Correct answer!");
        } else {
            System.out.println("Incorrect answer.");
        }

        return isCorrect;
    }
}
