package com.satyam.splitwise.expense_group.exceptions;

public class ExpenseGroupNotFoundException extends IllegalArgumentException{

    public ExpenseGroupNotFoundException(String message) {
        super(message);
    }
}
