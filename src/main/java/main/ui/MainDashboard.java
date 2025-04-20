package main.ui;

import java.util.List;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.model.Expense;
import main.model.Income;
import main.service.ExpenseService;
import main.service.IncomeService;

public class MainDashboard extends Application {

    private BorderPane root;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Expense Tracker Dashboard");

        // Root layout
        root = new BorderPane();

        // Left navigation panel
        VBox navigationPanel = createNavigationPanel();

        // Default content
        Label defaultContent = new Label("Welcome to the Expense Tracker Dashboard!");
        root.setCenter(defaultContent);

        // Add navigation panel to the left
        root.setLeft(navigationPanel);

        // Create and set the scene
        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox createNavigationPanel() {
        VBox navigationPanel = new VBox(10);
        navigationPanel.setPadding(new Insets(10));
        navigationPanel.setStyle("-fx-background-color: #f0f0f0;");

        // Buttons for navigation
        Button addIncomeButton = new Button("Add Income");
        Button addExpenseButton = new Button("Add Expense");
        Button viewIncomeButton = new Button("View Income");
        Button viewExpensesButton = new Button("View Expenses");
        Button calculateBalanceButton = new Button("Calculate Balance");

        // Set button actions
        addIncomeButton.setOnAction(e -> showAddIncomeForm());
        addExpenseButton.setOnAction(e -> showAddExpenseForm());
        viewIncomeButton.setOnAction(e -> showViewIncome());
        viewExpensesButton.setOnAction(e -> showViewExpenses());
        calculateBalanceButton.setOnAction(e -> showCalculateBalance());

        // Add buttons to the navigation panel
        navigationPanel.getChildren().addAll(
                addIncomeButton,
                addExpenseButton,
                viewIncomeButton,
                viewExpensesButton,
                calculateBalanceButton
        );

        return navigationPanel;
    }

    private void showAddIncomeForm() {
        VBox form = new VBox(10);
        form.setPadding(new Insets(20));

        Label titleLabel = new Label("Add Income");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        TextField userIdField = new TextField();
        userIdField.setPromptText("User ID");

        TextField amountField = new TextField();
        amountField.setPromptText("Amount");

        TextField sourceField = new TextField();
        sourceField.setPromptText("Source");

        DatePicker datePicker = new DatePicker();

        TextField notesField = new TextField();
        notesField.setPromptText("Notes");

        Button submitButton = new Button("Submit");
        submitButton.setStyle("-fx-background-color: #0078d7; -fx-text-fill: white;");

        submitButton.setOnAction(e -> {
            try {
                int userId = Integer.parseInt(userIdField.getText());
                double amount = Double.parseDouble(amountField.getText());
                String source = sourceField.getText();
                String date = datePicker.getValue().toString();
                String notes = notesField.getText();

                IncomeService incomeService = new IncomeService();
                incomeService.addIncome(userId, amount, source, date, notes);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Income added successfully!");
                alert.showAndWait();

                userIdField.clear();
                amountField.clear();
                sourceField.clear();
                datePicker.setValue(null);
                notesField.clear();
            } catch (NumberFormatException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Invalid input. Please enter valid numbers for User ID and Amount.");
                alert.showAndWait();
            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("An error occurred: " + ex.getMessage());
                alert.showAndWait();
            }
        });

        form.getChildren().addAll(titleLabel, userIdField, amountField, sourceField, datePicker, notesField, submitButton);
        root.setCenter(form);
    }

    private void showAddExpenseForm() {
        VBox form = new VBox(10);
        form.setPadding(new Insets(20));

        Label titleLabel = new Label("Add Expense");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        TextField userIdField = new TextField();
        userIdField.setPromptText("User ID");

        TextField amountField = new TextField();
        amountField.setPromptText("Amount");

        TextField categoryField = new TextField();
        categoryField.setPromptText("Category");

        DatePicker datePicker = new DatePicker();

        TextField notesField = new TextField();
        notesField.setPromptText("Notes");

        Button submitButton = new Button("Submit");
        submitButton.setStyle("-fx-background-color: #0078d7; -fx-text-fill: white;");

        submitButton.setOnAction(e -> {
            try {
                int userId = Integer.parseInt(userIdField.getText());
                double amount = Double.parseDouble(amountField.getText());
                String category = categoryField.getText();
                String date = datePicker.getValue().toString();
                String notes = notesField.getText();

                ExpenseService expenseService = new ExpenseService();
                expenseService.addExpense(userId, amount, category, date, notes);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Expense added successfully!");
                alert.showAndWait();

                userIdField.clear();
                amountField.clear();
                categoryField.clear();
                datePicker.setValue(null);
                notesField.clear();
            } catch (NumberFormatException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Invalid input. Please enter valid numbers for User ID and Amount.");
                alert.showAndWait();
            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("An error occurred: " + ex.getMessage());
                alert.showAndWait();
            }
        });

        form.getChildren().addAll(titleLabel, userIdField, amountField, categoryField, datePicker, notesField, submitButton);
        root.setCenter(form);
    }

    @SuppressWarnings("unchecked")
    private void showViewIncome() {
        TableView<Income> table = new TableView<>();

        TableColumn<Income, Integer> userIdColumn = new TableColumn<>("User ID");
        userIdColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));

        TableColumn<Income, Double> amountColumn = new TableColumn<>("Amount");
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));

        TableColumn<Income, String> sourceColumn = new TableColumn<>("Source");
        sourceColumn.setCellValueFactory(new PropertyValueFactory<>("source"));

        TableColumn<Income, String> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        TableColumn<Income, String> notesColumn = new TableColumn<>("Notes");
        notesColumn.setCellValueFactory(new PropertyValueFactory<>("notes"));

        table.getColumns().addAll(userIdColumn, amountColumn, sourceColumn, dateColumn, notesColumn);

        IncomeService incomeService = new IncomeService();
        List<Income> incomes = incomeService.getAllIncome(); // Fetch all income records
        table.getItems().addAll(incomes);

        root.setCenter(table);
    }

    @SuppressWarnings("unchecked")
    private void showViewExpenses() {
        TableView<Expense> table = new TableView<>();

        TableColumn<Expense, Integer> userIdColumn = new TableColumn<>("User ID");
        userIdColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));

        TableColumn<Expense, Double> amountColumn = new TableColumn<>("Amount");
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));

        TableColumn<Expense, String> categoryColumn = new TableColumn<>("Category");
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));

        TableColumn<Expense, String> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        TableColumn<Expense, String> notesColumn = new TableColumn<>("Notes");
        notesColumn.setCellValueFactory(new PropertyValueFactory<>("notes"));

        table.getColumns().addAll(userIdColumn, amountColumn, categoryColumn, dateColumn, notesColumn);

        ExpenseService expenseService = new ExpenseService();
        List<Expense> expenses = expenseService.getAllExpenses(); // Fetch all expense records
        table.getItems().addAll(expenses);

        root.setCenter(table);
    }

    private void showCalculateBalance() {
        VBox balancePane = new VBox(10);
        balancePane.setPadding(new Insets(20));
    
        Label titleLabel = new Label("Calculate Balance");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
    
        TextField userIdField = new TextField();
        userIdField.setPromptText("User ID");
    
        Button calculateButton = new Button("Calculate");
        calculateButton.setStyle("-fx-background-color: #0078d7; -fx-text-fill: white;");
    
        Label resultLabel = new Label();
    
        calculateButton.setOnAction(e -> {
            try {
                int userId = Integer.parseInt(userIdField.getText());
    
                // Create instances of services
                IncomeService incomeService = new IncomeService();
                ExpenseService expenseService = new ExpenseService();
    
                // Get total income (this should be just the sum of all income for the user)
                double totalIncome = incomeService.calculateBalance(userId);  // Only returns total income for the user
                System.out.println("Total Income from Service: " + totalIncome);  // Debugging log
    
                // Get total expenses (this should be just the sum of all expenses for the user)
                double totalExpenses = expenseService.calculateTotalExpenses(userId);  // Only returns total expenses for the user
                System.out.println("Total Expenses from Service: " + totalExpenses);  // Debugging log
    
                // Calculate balance by subtracting total expenses from total income
                double balance = totalIncome - totalExpenses;
                System.out.println("Calculated Balance: " + balance);  // Debugging log
    
                // Display balance (rounded to 2 decimal places)
                resultLabel.setText("Balance: " + String.format("%.2f", balance));
    
            } catch (NumberFormatException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Invalid input. Please enter a valid User ID.");
                alert.showAndWait();
            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("An error occurred: " + ex.getMessage());
                alert.showAndWait();
            }
        });
    
        balancePane.getChildren().addAll(titleLabel, userIdField, calculateButton, resultLabel);
        root.setCenter(balancePane);
    }
    public static void main(String[] args) {
        launch(args);
    }
}