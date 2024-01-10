package game;

import db.DBConnector;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;

public class Write {
    public static void addQuestionAnswerToFile(String question, String answer, String fileName) {
        try (FileWriter writer = new FileWriter(fileName, true)) {
            int previousRowID = getPreviousRowID(fileName);
            if (previousRowID >= 0) {
                writer.append((previousRowID + 1) + "," + question + "," + answer + "\n");
                System.out.println("Question and answer added successfully!");
            } else {
                System.err.println("Error determining previous row ID. Skipping addition.");
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public static void addMultipleQuestionsAnswersToFile(LinkedList<String> questions, LinkedList<String> answers, String fileName) {
        try (FileWriter writer = new FileWriter(fileName, true)) {
            int previousRowID = getPreviousRowID(fileName);
            if (previousRowID >= 0 && questions.size() == answers.size()) {
                for (int i = 0; i < questions.size(); i++) {
                    String question = questions.get(i);
                    String answer = answers.get(i);

                    writer.append((previousRowID + i + 1) + "," + question + "," + answer + "\n");
                }
                System.out.println("Multiple questions and answers added successfully!");
            } else {
                System.err.println("Error determining previous row ID or number of questions/answers mismatch. Skipping addition.");
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    private static int getPreviousRowID(String fileName) {
        try (FileWriter writer = new FileWriter(fileName, true)) {
            // game.Read the last line to get the previous rowID
            int lastRowID = 0;
            try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] data = line.split(",");
                    if (data.length >= 1) {
                        lastRowID = Integer.parseInt(data[0].trim());
                    }
                }
            } catch (IOException e) {
                System.err.println("Error reading from file: " + e.getMessage());
            }
            return lastRowID;
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
            return -1; // Return -1 to indicate an error
        }
    }

    public static void writeQuestionAnswerToMySQL(String question, String answer, String tableName) {
        String query = "INSERT INTO " + tableName + " (question, answer) VALUES (?, ?)";

        try (Connection connection = DBConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, question);
            preparedStatement.setString(2, answer);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }
}
