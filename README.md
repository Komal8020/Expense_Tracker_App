# 💰 Java Expense Tracker Application

This is a Java-based **Expense Tracker** desktop application built using **JavaFX** for the UI and **JDBC** for database connectivity with **SQLite**. It helps multiple users to manage their expenses and incomes, keeping track of their remaining balance.

---

## 📌 Features

- 🔐 **User-Based Tracking**: Each user's expenses and income are tracked separately.
- 💸 **Add & View Income**
- 💰 **Add & View Expenses**
- 📊 **Dashboard View**: View total income, total expense, and remaining balance.
- 🧾 **Persistent Data Storage** using SQLite.
- 🖥️ **User-Friendly JavaFX Interface**

---

## Project Structure
The project is organized as follows:

expense-tracker/
├── src/
│   └── main/
│       └── java/
│           └── main/
│               ├── dao/
│               │   ├── ExpenseDAO.java
│               │   ├── IncomeDAO.java
│               │   └── UserDAO.java
│               ├── database/
│               │   └── DatabaseConnection.java
│               ├── model/
│               │   ├── Expense.java
│               │   └── Income.java
│               ├── service/
│               │   ├── ExpenseService.java
│               │   └── IncomeService.java
│               ├── ui/
│               │   ├── MainApp.java
│               │   ├── MainDashboard.java
│               │   ├── MainMenu.java
│               │   └── MainUI.java
│               └── util/
├── expense_tracker.db
├── pom.xml
├── test/
├── target/
└── README.md


---

## 🛠️ Technologies Used

- Java
- JavaFX
- JDBC
- SQLite

---

## 🧪 How to Run

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername expense-tracker-java.git
   cd expense-tracker-java

3. **Database Setup**
   Ensure that the SQLite JDBC driver (`sqlite-jdbc.jar`) is included in the `lib` directory. The application will create the database file `expense_tracker.db` in the `database` directory upon first run.

## Usage
1. Run the application using the following command:
   mvn javafx:run

2. Follow the on-screen instructions to navigate through the main menu, where you can add, view, and manage your income and expenses.

## Dependencies
- Java Development Kit (JDK) 8 or higher
- Maven (for building the project)
- SQLite JDBC Driver

## Future Enhancements
User registration & authentication
Export data to PDF/CSV
Category-based filtering and charts
Cloud backup and sync

## License
This project is licensed under the MIT License. See the LICENSE file for more details.