import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.*;

public class ExpenseTrackerGUI {

    private static ArrayList<expense> expenses = new ArrayList<>();
    private static double budget = 5000;

    public static void main(String[] args) {

        JFrame frame = new JFrame("Smart Expense Tracker");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        JLabel amountLabel = new JLabel("Amount:");
        JTextField amountField = new JTextField(10);

        JLabel categoryLabel = new JLabel("Category:");
        JTextField categoryField = new JTextField(10);

        JButton addButton = new JButton("Add Expense");
        JButton viewButton = new JButton("View Expenses");
        JButton totalButton = new JButton("Total Spending");
        JButton summaryButton = new JButton("Category Summary");

        JTextArea outputArea = new JTextArea(10, 40);
        outputArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(outputArea);

        // ADD EXPENSE
        addButton.addActionListener(e -> {
            try {
                double amount = Double.parseDouble(amountField.getText());
                String category = categoryField.getText();

                expense exp = new expense(amount, category, LocalDate.now());
                expenses.add(exp);

                outputArea.setText("✅ Expense Added!\n");

                checkBudget(outputArea);

            } catch (Exception ex) {
                outputArea.setText("❌ Invalid Input!");
            }
        });

        // VIEW EXPENSES
        viewButton.addActionListener(e -> {
            StringBuilder sb = new StringBuilder();

            for (expense exp : expenses) {
                sb.append(exp).append("\n");
            }

            outputArea.setText(sb.toString());
        });

        // TOTAL
        totalButton.addActionListener(e -> {
            double total = expenses.stream()
                    .mapToDouble(expense::getAmount)
                    .sum();

            outputArea.setText("💰 Total Spending: ₹" + total);
        });

        // CATEGORY SUMMARY
        summaryButton.addActionListener(e -> {
            StringBuilder sb = new StringBuilder();
            sb.append("📊 Category Summary:\n");

            java.util.HashMap<String, Double> map = new java.util.HashMap<>();

            for (expense e1 : expenses) {
                map.put(e1.getCategory(),
                        map.getOrDefault(e1.getCategory(), 0.0) + e1.getAmount());
            }

            for (String key : map.keySet()) {
                sb.append(key).append(" → ₹").append(map.get(key)).append("\n");
            }

            outputArea.setText(sb.toString());
        });

        // ADD COMPONENTS
        frame.add(amountLabel);
        frame.add(amountField);
        frame.add(categoryLabel);
        frame.add(categoryField);
        frame.add(addButton);
        frame.add(viewButton);
        frame.add(totalButton);
        frame.add(summaryButton);
        frame.add(scrollPane);

        frame.setVisible(true);
    }

    private static void checkBudget(JTextArea outputArea) {
        double total = expenses.stream()
                .mapToDouble(expense::getAmount)
                .sum();

        if (total > budget) {
            outputArea.append("⚠ Budget Exceeded!\n");
        }
    }
}