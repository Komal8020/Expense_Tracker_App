package main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.database.DatabaseConnection;

public class UserDAO {
    private final Connection connection;

    public UserDAO() {
        connection = DatabaseConnection.connect();
    }

    // Create a new user
    @SuppressWarnings("CallToPrintStackTrace")
    public void addUser(int userId, String name, String email) {
        String sql = "INSERT INTO users (user_id, name, email) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            statement.setString(2, name);
            statement.setString(3, email);
            statement.executeUpdate();
            System.out.println("User added successfully!");
        } catch (SQLException e) {
            if (e.getErrorCode() == 19) { // SQLite error code for UNIQUE constraint violation
                System.err.println("Error: User ID already exists!");
            } else {
                e.printStackTrace();
            }
        }
    }

    // Retrieve a user by ID
    @SuppressWarnings("CallToPrintStackTrace")
    public void getUserById(int userId) {
        String sql = "SELECT * FROM users WHERE user_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                System.out.println("User ID: " + resultSet.getInt("user_id"));
                System.out.println("Name: " + resultSet.getString("name"));
                System.out.println("Email: " + resultSet.getString("email"));
            } else {
                System.out.println("User not found!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }   
    }

    // Update a user's details
    @SuppressWarnings("CallToPrintStackTrace")
    public void updateUser(int userId, String name, String email) {
        String sql = "UPDATE users SET name = ?, email = ? WHERE user_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, email);
            statement.setInt(3, userId);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("User updated successfully!");
            } else {
                System.out.println("User not found!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete a user by ID
    @SuppressWarnings("CallToPrintStackTrace")
    public void deleteUser(int userId) {
        String sql = "DELETE FROM users WHERE user_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("User deleted successfully!");
            } else {
                System.out.println("User not found!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}