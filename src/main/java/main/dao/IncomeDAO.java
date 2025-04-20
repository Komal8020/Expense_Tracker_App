package main.dao;

import main.database.DatabaseConnection;
import main.model.Income;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IncomeDAO {
    // Add a new income record
    public void addIncome(Income income) {
        String sql = "INSERT INTO income (user_id, amount, source, date, notes) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, income.getUserId());
            pstmt.setDouble(2, income.getAmount());
            pstmt.setString(3, income.getSource());
            pstmt.setString(4, income.getDate());
            pstmt.setString(5, income.getNotes());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get all income records for a specific user
    public List<Income> getAllIncome(int userId) {
        List<Income> incomeList = new ArrayList<>();
        String sql = "SELECT * FROM income WHERE user_id = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                incomeList.add(new Income(
                        rs.getInt("income_id"),
                        userId,
                        rs.getDouble("amount"),
                        rs.getString("source"),
                        rs.getString("date"),
                        rs.getString("notes")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return incomeList;
    }

    // ✅ Get all income records for all users
    public List<Income> getAllIncome() {
        List<Income> incomeList = new ArrayList<>();
        String sql = "SELECT * FROM income";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                incomeList.add(new Income(
                        rs.getInt("income_id"),
                        rs.getInt("user_id"),
                        rs.getDouble("amount"),
                        rs.getString("source"),
                        rs.getString("date"),
                        rs.getString("notes")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return incomeList;
    }
}
