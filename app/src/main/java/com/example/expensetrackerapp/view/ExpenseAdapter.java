package com.example.expensetrackerapp.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.expensetrackerapp.R;
import com.example.expensetrackerapp.model.Expense;

import java.util.List;

public class ExpenseAdapter extends BaseAdapter {

    private final Context context;
    private final List<Expense> expenses;

    public ExpenseAdapter(Context context, List<Expense> expenses) {
        this.context = context;
        this.expenses = expenses;
    }

    @Override
    public int getCount() {
        return expenses.size();
    }

    @Override
    public Object getItem(int position) {
        return expenses.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.item_expense, parent, false);
        }

        TextView titleTextView = convertView.findViewById(R.id.titleTextView);
        TextView amountTextView = convertView.findViewById(R.id.amountTextView);

        Expense expense = expenses.get(position);
        titleTextView.setText(expense.getTitle());
        amountTextView.setText(String.valueOf(expense.getAmount()));

        return convertView;
    }
}
