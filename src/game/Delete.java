package game;

import db.DBConnector;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class Delete {
    public static void deleteQuestionAnswer(int rowID, String fileName) {
        if (rowID < 0) {
            System.err.println("Invalid row ID. Skipping deletion.");
            return;
        }

        ArrayList<String> lines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }

        if (rowID < lines.size()) {
            lines.remove(rowID);
            System.out.println("Question and answer removed successfully!");
        } else {
            System.err.println("Row ID does not exist. Skipping deletion.");
        }

        try (FileWriter writer = new FileWriter(fileName)) {
            for (String line : lines) {
                writer.write(line + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public static void deleteQuestionAnswerByRowIDFromMySQL(int rowID, String tableName) {
        String query = "DELETE FROM " + tableName + " WHERE rowID = ?";

        try (Connection connection = DBConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, rowID);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }
}
