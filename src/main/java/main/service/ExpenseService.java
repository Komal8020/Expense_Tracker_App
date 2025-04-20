package main.service;

import java.util.List;

import main.dao.ExpenseDAO;
import main.model.Expense;

public class ExpenseService {
    private final ExpenseDAO expenseDAO = new ExpenseDAO();

    public void addExpense(int userId, double amount, String category, String date, String notes) {
        Expense expense = new Expense(0, userId, amount, category, date, notes);
        expenseDAO.addExpense(expense);
    }

    public void viewExpenses(int userId) {
        List<Expense> expenseList = expenseDAO.getAllExpenses();
        System.out.println("\nExpense Records:");
        for (Expense exp : expenseList) {
            System.out.println("ID: " + exp.getExpenseId() + ", Amount: " + exp.getAmount() + 
                               ", Category: " + exp.getCategory() + ", Date: " + exp.getDate());
        }
    }

    public double calculateTotalExpenses(int userId) {
        return expenseDAO.getAllExpenses(userId).stream()
                         .mapToDouble(Expense::getAmount)
                         .sum();
    }
    

    public List<Expense> getAllExpenses() {
        return expenseDAO.getAllExpenses();  // No parameter: returns all expenses
    }
    
}
