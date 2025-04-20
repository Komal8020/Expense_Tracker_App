package main.service;

import java.util.List;
import java.util.stream.Collectors;

import main.dao.IncomeDAO;
import main.model.Income;

public class IncomeService {
    private final IncomeDAO incomeDAO = new IncomeDAO();

    // Add a new income record
    public void addIncome(int userId, double amount, String source, String date, String notes) {
        Income income = new Income(0, userId, amount, source, date, notes);
        incomeDAO.addIncome(income);
    }

    // View income records for a specific user (console output)
    public void viewIncome(int userId) {
        List<Income> incomeList = incomeDAO.getAllIncome(userId);
        System.out.println("\nIncome Records:");
        for (Income inc : incomeList) {
            System.out.println("ID: " + inc.getId() + ", Amount: " + inc.getAmount() + 
                               ", Source: " + inc.getSource() + ", Date: " + inc.getDate());
        }
    }

    // Calculate the balance for a specific user
    public double calculateBalance(int userId) {
        double totalIncome = incomeDAO.getAllIncome(userId).stream()
                                      .mapToDouble(Income::getAmount)
                                      .sum();  // Total income for the user
        return totalIncome;
    }
    
    

   public List<Income> getAllIncome(int userId) {
    return getAllIncome().stream()
                         .filter(income -> income.getUserId() == userId)
                         .collect(Collectors.toList());
}


    // Get all income records (for all users, if needed)
    public List<Income> getAllIncome() {
        return incomeDAO.getAllIncome();
    }
}