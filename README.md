# Smart Expense Tracker

A straightforward, Java-based application designed to help users log their daily expenses, categorize spending, and monitor a custom monthly budget. This project offers two distinct ways to interact with the tracker: a feature-rich Command-Line Interface (CLI) and a user-friendly Graphical User Interface (GUI).

## 🚀 Features

### **Command-Line Interface (CLI)**
The CLI version offers a robust set of features, including data persistence so you don't lose your records between sessions:
* **Add Expenses:** Log an amount and category (date is automatically recorded).
* **View & Delete:** Review all logged expenses and delete incorrect entries by index.
* **Financial Summaries:** Calculate total spending or view a breakdown by category.
* **Filtering:** Search for expenses by a specific category.
* **Budget Management:** Set a custom monthly budget. The app warns you if you exceed it.
* **Data Persistence:** Automatically saves and loads your expense data to a local `expenses.txt` file.

### **Graphical User Interface (GUI)**
A lightweight, windowed desktop app built with Java Swing for quick logging:
* **Add & View:** Simple text fields to add an expense and a text area to view the log.
* **Quick Summaries:** Buttons to instantly calculate total spending or generate a category breakdown.
* **Budget Alerts:** Displays an on-screen warning if your spending exceeds the default budget (₹5000).
* *(Note: The GUI version currently stores data in memory for the active session and does not write to a file).*

---

## 📁 Project Structure

* `expense.java`: The core data model. Defines an expense object containing an `amount`, `category`, and `date`.
* `expensetracker.java`: The main class for the CLI application. Handles the console menus, application logic, and file I/O operations.
* `ExpenseTrackerGUI.java`: The main class for the desktop app. Uses Java Swing to build the interactive user interface.

---

## ⚙️ Prerequisites

To compile and run this application, you will need:
* **Java Development Kit (JDK)** installed on your machine (Java 8 or higher is recommended).

---

## 💻 How to Run

First, open your terminal or command prompt, navigate to the folder containing these files, and compile the Java code:

bash
javac expense.java expensetracker.java ExpenseTrackerGUI.java


### Running the CLI Application
To launch the terminal-based tracker, run:

bash
java expensetracker


### Running the GUI Application
To launch the desktop interface, run:

bash
java ExpenseTrackerGUI


---

## 🗄️ Data Storage Details

If you use the **CLI version** (`expensetracker.java`), the program will automatically generate a file named `expenses.txt` in the same directory. The data is stored in a simple comma-separated format:
`Amount,Category,YYYY-MM-DD`

*Feel free to fork, modify, and enhance this project!*