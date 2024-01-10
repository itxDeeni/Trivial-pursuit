package game;

import db.DBConnector;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class Read {
    public static LinkedList<String> readQuestionsFromFile(String fileName) {
        LinkedList<String> questions = new LinkedList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 1) {
                    questions.add(data[0].trim());
                } else {
                    System.err.println("Invalid data in questions file. Skipping line.");
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }

        return questions;
    }

    public static LinkedList<String> readAnswersFromFile(String fileName) {
        LinkedList<String> answers = new LinkedList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 2) {
                    answers.add(data[1].trim());
                } else {
                    System.err.println("Invalid data in answers file. Skipping line.");
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }

        return answers;
    }

    public static LinkedList<String> readAllQuestionsFromFiles(LinkedList<String> fileNames) {
        LinkedList<String> allQuestions = new LinkedList<>();

        for (String fileName : fileNames) {
            allQuestions.addAll(readQuestionsFromFile(fileName));
        }

        return allQuestions;
    }

    public static LinkedList<String> readAllAnswersFromFiles(LinkedList<String> fileNames) {
        LinkedList<String> allAnswers = new LinkedList<>();

        for (String fileName : fileNames) {
            allAnswers.addAll(readAnswersFromFile(fileName));
        }

        return allAnswers;
    }

    public static LinkedList<String> readQuestionsFromMySQL(String tableName) {
        LinkedList<String> questions = new LinkedList<>();
        String query = "SELECT question FROM " + tableName;

        try (Connection connection = DBConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                questions.add(resultSet.getString("question"));
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }

        return questions;
    }
}
