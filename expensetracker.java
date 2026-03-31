import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class expensetracker {

    private static List<expense> expenses = new ArrayList<>();
    private static final String FILE_NAME = "expenses.txt";
    private static double budget = 5000; // default budget

    public static void main(String[] args) {
        loadExpenses();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== Smart Expense Tracker =====");
            System.out.println("1. Add Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. Total Spending");
            System.out.println("4. Filter by Category");
            System.out.println("5. Delete Expense");
            System.out.println("6. Monthly Summary");
            System.out.println("7. Set Budget");
            System.out.println("8. Exit");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> addExpense(sc);
                case 2 -> viewExpenses();
                case 3 -> totalSpending();
                case 4 -> filterByCategory(sc);
                case 5 -> deleteExpense(sc);
                case 6 -> monthlySummary();
                case 7 -> setBudget(sc);
                case 8 -> {
                    saveExpenses();
                    System.out.println("Data saved. Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    private static void addExpense(Scanner sc) {
        System.out.print("Enter amount: ");
        double amount = sc.nextDouble();
        sc.nextLine();

        System.out.print("Enter category: ");
        String category = sc.nextLine();

        expense exp = new expense(amount, category, LocalDate.now());
        expenses.add(exp);

        System.out.println("✅ Expense added!");

        checkBudget();
    }

    private static void viewExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses found.");
            return;
        }

        System.out.println("\n--- All Expenses ---");
        for (int i = 0; i < expenses.size(); i++) {
            System.out.println(i + ". " + expenses.get(i));
        }
    }

    private static void totalSpending() {
        double total = expenses.stream()
                .mapToDouble(expense::getAmount)
                .sum();

        System.out.println("💰 Total Spending: ₹" + total);
    }

    private static void filterByCategory(Scanner sc) {
        System.out.print("Enter category: ");
        String category = sc.nextLine();

        System.out.println("\nFiltered Results:");
        expenses.stream()
                .filter(e -> e.getCategory().equalsIgnoreCase(category))
                .forEach(System.out::println);
    }

    private static void deleteExpense(Scanner sc) {
        viewExpenses();

        System.out.print("Enter index to delete: ");
        int index = sc.nextInt();

        if (index >= 0 && index < expenses.size()) {
            expenses.remove(index);
            System.out.println("🗑 Expense deleted!");
        } else {
            System.out.println("Invalid index!");
        }
    }

    private static void monthlySummary() {
        Map<String, Double> summary = new HashMap<>();

        for (expense e : expenses) {
            summary.put(e.getCategory(),
                    summary.getOrDefault(e.getCategory(), 0.0) + e.getAmount());
        }

        System.out.println("\n📊 Monthly Summary:");
        for (String cat : summary.keySet()) {
            System.out.println(cat + " → ₹" + summary.get(cat));
        }
    }

    private static void setBudget(Scanner sc) {
        System.out.print("Enter monthly budget: ");
        budget = sc.nextDouble();
        System.out.println("Budget updated!");
    }

    private static void checkBudget() {
        double total = expenses.stream()
                .mapToDouble(expense::getAmount)
                .sum();

        if (total > budget) {
            System.out.println("⚠ WARNING: Budget exceeded!");
        }
    }

    private static void saveExpenses() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (expense e : expenses) {
                writer.write(e.getAmount() + "," + e.getCategory() + "," + e.getDate());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving data.");
        }
    }

    private static void loadExpenses() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                expenses.add(new expense(
                        Double.parseDouble(data[0]),
                        data[1],
                        LocalDate.parse(data[2])
                ));
            }

        } catch (IOException e) {
            System.out.println("No previous data found.");
        }
    }
}