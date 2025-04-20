package main.ui;

import java.util.Scanner;

import main.dao.UserDAO;
import main.service.ExpenseService;
import main.service.IncomeService;

public class MainMenu {
    private final IncomeService incomeService = new IncomeService();
    private final ExpenseService expenseService = new ExpenseService();
    private final Scanner scanner = new Scanner(System.in);

    public void displayMenu() {
        while (true) {
            try {
                System.out.println("\n===== Expense Tracker Menu =====");
                System.out.println("1. Add Income");
                System.out.println("2. Add Expense");
                System.out.println("3. View Income");
                System.out.println("4. View Expenses");
                System.out.println("5. Calculate Balance");
                System.out.println("6. Manage Users");
                System.out.println("7. Exit");
                System.out.print("Enter your choice: ");
    
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume newline
    
                switch (choice) {
                    case 1 -> addIncome();
                    case 2 -> addExpense();
                    case 3 -> viewIncome();
                    case 4 -> viewExpenses();
                    case 5 -> calculateBalance();
                    case 6 -> manageUsers();
                    case 7 -> {
                        System.out.println("Exiting... Thank you!");
                        return;
                    }
                    default -> System.out.println("Invalid choice! Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Clear invalid input
            }
        }
    }

    private void addIncome() {
        System.out.print("Enter user ID: ");
        int userId = scanner.nextInt();
        System.out.print("Enter amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); 
        System.out.print("Enter source: ");
        String source = scanner.nextLine();
        System.out.print("Enter date (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        System.out.print("Enter notes: ");
        String notes = scanner.nextLine();

        incomeService.addIncome(userId, amount, source, date, notes);
        System.out.println("Income added successfully!");
    }

    private void addExpense() {
        System.out.print("Enter user ID: ");
        int userId = scanner.nextInt();
        System.out.print("Enter amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Enter category: ");
        String category = scanner.nextLine();
        System.out.print("Enter date (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        System.out.print("Enter notes: ");
        String notes = scanner.nextLine();

        expenseService.addExpense(userId, amount, category, date, notes);
        System.out.println("Expense added successfully!");
    }

    private void viewIncome() {
        System.out.print("Enter user ID: ");
        int userId = scanner.nextInt();
        incomeService.viewIncome(userId);
    }

    private void viewExpenses() {
        System.out.print("Enter user ID: ");
        int userId = scanner.nextInt();
        expenseService.viewExpenses(userId);
    }

    private void calculateBalance() {
        System.out.print("Enter user ID: ");
        int userId = scanner.nextInt();
        double balance = incomeService.calculateBalance(userId);
        System.out.println("Current Balance: " + balance);
    }

    private void manageUsers() {
        UserDAO userDAO = new UserDAO();

        while (true) {
            System.out.println("\n===== User Management =====");
            System.out.println("1. Add User");
            System.out.println("2. View User");
            System.out.println("3. Update User");
            System.out.println("4. Delete User");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter User ID: ");
                    int userId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Email: ");
                    String email = scanner.nextLine();
                    userDAO.addUser(userId, name, email);
                }
                case 2 -> {
                    System.out.print("Enter User ID: ");
                    int userId = scanner.nextInt();
                    userDAO.getUserById(userId);
                }
                case 3 -> {
                    System.out.print("Enter User ID: ");
                    int userId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter New Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter New Email: ");
                    String email = scanner.nextLine();
                    userDAO.updateUser(userId, name, email);
                }
                case 4 -> {
                    System.out.print("Enter User ID: ");
                    int userId = scanner.nextInt();
                    userDAO.deleteUser(userId);
                }
                case 5 -> {
                    return; // Exit to main menu
                }
                default -> System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}