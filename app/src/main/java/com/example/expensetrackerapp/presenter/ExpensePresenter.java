package com.example.expensetrackerapp.presenter;

import com.example.expensetrackerapp.model.Expense;
import com.example.expensetrackerapp.model.ExpenseRepository;
import com.example.expensetrackerapp.view.ExpenseView;

import java.util.List;

public class ExpensePresenter {

    private final ExpenseView view;
    private final ExpenseRepository repository;

    public ExpensePresenter(ExpenseView view) {
        this.view = view;
        this.repository = new ExpenseRepository();
        loadExpenses();
    }

    private void loadExpenses() {
        List<Expense> expenses = repository.getExpenses();
        view.showExpenses(expenses);
    }

    public void addExpense(String title, double amount) {
        repository.addExpense(new Expense(title, amount));
        loadExpenses();
        view.showAddSuccessMessage();
    }

    public void deleteExpense(int position) {
        repository.removeExpense(position);
        loadExpenses();
    }
}

