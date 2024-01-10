package test;

import game.Read;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;


public class ReadTest {

    @Test
    public void testReadQuestionsFromFile() {
        LinkedList<String> questions = Read.readQuestionsFromFile("test_questions.csv");
//        assertNotNull(questions);
//        assertEquals(5, questions.size()); // Assuming there are 5 questions in the test file
        // Add more specific tests as needed
    }

    @Test
    public void testReadAnswersFromFile() {
        LinkedList<String> answers = Read.readAnswersFromFile("test_answers.csv");
//        assertNotNull(answers);
//        assertEquals(5, answers.size()); // Assuming there are 5 answers in the test file
    }

    @org.junit.jupiter.api.AfterEach
    public void tearDown() throws Exception {
    }

    @org.junit.jupiter.api.Test
    public void testReadQuestionsFromFile1() {
    }

    @org.junit.jupiter.api.Test
    public void testReadAnswersFromFile1() {
    }

    // Similar tests for readAllQuestionsFromFiles and readAllAnswersFromFiles
}
