package com.example.expensetrackerapp.model;

import java.util.ArrayList;
import java.util.List;

public class ExpenseRepository {
    private List<Expense> expenses;

    public ExpenseRepository() {
        expenses = new ArrayList<>();
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void addExpense(Expense expense) {
        expenses.add(expense);
    }

    public void removeExpense(int position) {
        if (position >= 0 && position < expenses.size()) {
            expenses.remove(position);
        }
    }
}

