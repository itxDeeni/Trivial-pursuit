package test;

import game.GameSetup;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameSetupTest {

    @Test
    public void testGameSetup() {
        // Redirect System.out for testing user input/output
        ByteArrayInputStream in = new ByteArrayInputStream("John\n3\n5\n".getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        // Assuming "questions_test.csv" and "answers_test.csv" contain valid test data
        GameSetup gameSetup = new GameSetup("John", 3, 5);
        gameSetup.start();

        // Add assertions based on expected console output
        String consoleOutput = out.toString();
        assertTrue(consoleOutput.contains("John"));
        assertTrue(consoleOutput.contains("Game over"));

        // Reset System.in and System.out
        System.setIn(System.in);
        System.setOut(System.out);
    }
}
