package com.example.expensetrackerapp.view;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.expensetrackerapp.R;
import com.example.expensetrackerapp.model.Expense;
import com.example.expensetrackerapp.presenter.ExpensePresenter;

import java.util.ArrayList;
import java.util.List;

public class ExpenseActivity extends AppCompatActivity implements ExpenseView {

    private EditText titleEditText;
    private EditText amountEditText;
    private ListView expenseListView;
    private ExpenseAdapter expenseAdapter;
    private TextView sumTextView;
    private Button addButton;
    private List<Double> expenseAmounts = new ArrayList<>();

    private ExpensePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);

        titleEditText = findViewById(R.id.titleEditText);
        amountEditText = findViewById(R.id.amountEditText);
        expenseListView = findViewById(R.id.expenseListView);
        sumTextView = findViewById(R.id.totalSumTextView);
        addButton = findViewById(R.id.addButton);

        presenter = new ExpensePresenter(this);

        addButton.setOnClickListener(v -> {
            String title = titleEditText.getText().toString();
            double amount = Double.parseDouble(amountEditText.getText().toString());
            // Add expense amount to the list
            expenseAmounts.add(amount);
            // Update ListView
            //adapter.notifyDataSetChanged();
            // Calculate total sum and update TextView
            double totalSum = calculateTotalSum();
            sumTextView.setText("Total Sum: $" + String.format("%.2f", totalSum));
            // Clear EditText
            amountEditText.getText().clear();
            presenter.addExpense(title, amount);
        });

        expenseListView.setOnItemClickListener((parent, view, position, id) -> {
            presenter.deleteExpense(position);
        });
    }

    private double calculateTotalSum() {
        double sum = 0;
        for (double amount : expenseAmounts) {
            sum += amount;
        }
        return sum;
    }
    private double calculateTotalSumFromList(List<Expense> expenses) {
        double sum = 0;
        for (Expense expense : expenses) {
            sum += expense.getAmount();
        }
        return sum;
    }

    @Override
    public void showExpenses(List<Expense> expenses) {
        expenseAdapter = new ExpenseAdapter(this, expenses);
        double totalSum = calculateTotalSumFromList(expenses);
        sumTextView.setText("Total Sum: $" + String.format("%.2f", totalSum));
        expenseListView.setAdapter(expenseAdapter);
    }
    @Override
    public void showAddSuccessMessage() {
        Toast.makeText(this, "Expense added successfully", Toast.LENGTH_SHORT).show();
        titleEditText.setText("");
        amountEditText.setText("");
    }
}
