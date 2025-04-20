package main.model;

public class Expense {
    private int expenseId;
    private int userId;
    private double amount;
    private String category;
    private String date;
    private String notes;

    // Constructor for retrieving from DB (with expenseId)
    public Expense(int expenseId, int userId, double amount, String category, String date, String notes) {
        this.expenseId = expenseId;
        this.userId = userId;
        this.amount = amount;
        this.category = category;
        this.date = date;
        this.notes = notes;
    }

    // Constructor for inserting (without expenseId)
    public Expense(int userId, double amount, String category, String date, String notes) {
        this.userId = userId;
        this.amount = amount;
        this.category = category;
        this.date = date;
        this.notes = notes;
    }

    // Getters
    public int getExpenseId() {
        return expenseId;
    }

    public int getUserId() {
        return userId;
    }

    public double getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public String getDate() {
        return date;
    }

    public String getNotes() {
        return notes;
    }

    // Optional: Setters (only if needed)
    public void setExpenseId(int expenseId) {
        this.expenseId = expenseId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
