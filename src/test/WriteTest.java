package test;

import game.Read;
import game.Write;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class WriteTest {

    @Test
    public void testAddQuestionAnswerToFile() {
        Write.addQuestionAnswerToFile("Test Question", "Test Answer", "test_write.csv");
        // Assuming "test_write.csv" contains the added data
        LinkedList<String> questions = Read.readQuestionsFromFile("test_write.csv");
        LinkedList<String> answers = Read.readAnswersFromFile("test_write.csv");
        assertTrue(questions.contains("Test Question"));
        assertTrue(answers.contains("Test Answer"));
    }

    @Test
    public void testAddMultipleQuestionsAnswersToFile() {
        LinkedList<String> questions = new LinkedList<>();
        LinkedList<String> answers = new LinkedList<>();
        questions.add("Q1");
        answers.add("A1");
        questions.add("Q2");
        answers.add("A2");

        Write.addMultipleQuestionsAnswersToFile(questions, answers, "test_write_multiple.csv");

        // Assuming "test_write_multiple.csv" contains the added data
//        LinkedList<String> allQuestions = game.Read.readAllQuestionsFromFiles(Collections.singletonList("test_write_multiple.csv"));
//        LinkedList<String> allAnswers = game.Read.readAllAnswersFromFiles(Collections.singletonList("test_write_multiple.csv"));

//        assertTrue(allQuestions.contains("Q1"));
//        assertTrue(allQuestions.contains("Q2"));
//        assertTrue(allAnswers.contains("A1"));
//        assertTrue(allAnswers.contains("A2"));
        // Add more specific tests as needed
    }
}
