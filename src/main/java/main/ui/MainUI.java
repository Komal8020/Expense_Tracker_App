package main.ui;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;

public class MainUI {
    private final VBox root;
    private ListView<String> expenseList;
    private List<String> expenses;

    public MainUI() {
        expenses = new ArrayList<>();
        root = new VBox(10);
        root.setPadding(new Insets(10));

        // Title
        Label title = new Label("Expense Tracker");
        title.setFont(new Font("Arial", 24));

        // Form
        HBox form = new HBox(10);
        TextField nameField = new TextField();
        nameField.setPromptText("Expense Name");
        TextField amountField = new TextField();
        amountField.setPromptText("Amount");
        Button addButton = new Button("Add Expense");

        form.getChildren().addAll(nameField, amountField, addButton);

        // Expense List
        expenseList = new ListView<>();

        // Add Button Action
        addButton.setOnAction(e -> {
            String name = nameField.getText();
            String amount = amountField.getText();

            if (!name.isEmpty() && !amount.isEmpty()) {
                expenses.add(name + " - $" + amount);
                expenseList.getItems().setAll(expenses);
                nameField.clear();
                amountField.clear();
            } else {
                showAlert("Error", "Please fill in both fields.");
            }
        });

        root.getChildren().addAll(title, form, expenseList);
    }

    public VBox getRoot() {
        return root;
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}