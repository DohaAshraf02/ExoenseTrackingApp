package com.example.expensetrackerapp.view;

import com.example.expensetrackerapp.model.Expense;

import java.util.List;

public interface ExpenseView {
    void showExpenses(List<Expense> expenses);
    void showAddSuccessMessage();
}

