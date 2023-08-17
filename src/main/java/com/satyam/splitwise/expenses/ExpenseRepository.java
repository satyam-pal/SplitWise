package com.satyam.splitwise.expenses;

import com.satyam.splitwise.expense_group.ExpenseGroupModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<ExpenseModel, Long> {
    public List<ExpenseModel> findByExpenseGroup(ExpenseGroupModel expenseGroupModel);
}
