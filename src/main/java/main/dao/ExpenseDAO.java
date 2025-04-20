package main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import main.database.DatabaseConnection;
import main.model.Expense;

public class ExpenseDAO {
    private static final Logger logger = LoggerFactory.getLogger(ExpenseDAO.class);

    public void addExpense(Expense expense) {
        String sql = "INSERT INTO expenses (user_id, amount, category, date, notes) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, expense.getUserId());
            pstmt.setDouble(2, expense.getAmount());
            pstmt.setString(3, expense.getCategory());
            pstmt.setString(4, expense.getDate());
            pstmt.setString(5, expense.getNotes());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error adding expense: {}", e.getMessage(), e);
        }
    }

    public List<Expense> getAllExpenses(int userId) {
        List<Expense> expenseList = new ArrayList<>();
        String sql = "SELECT * FROM expenses WHERE user_id = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                expenseList.add(new Expense(
                        rs.getInt("expense_id"),
                        rs.getInt("user_id"),
                        rs.getDouble("amount"),
                        rs.getString("category"),
                        rs.getString("date"),
                        rs.getString("notes")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return expenseList;
    }
    
    public List<Expense> getAllExpenses() {
        List<Expense> expenseList = new ArrayList<>();
        String sql = "SELECT * FROM expenses";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                expenseList.add(new Expense(
                    rs.getInt("expense_id"),
                    rs.getInt("user_id"),
                    rs.getDouble("amount"),
                    rs.getString("category"),
                    rs.getString("date"),
                    rs.getString("notes")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return expenseList;
    }
    
    
    
}