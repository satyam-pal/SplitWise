package com.satyam.splitwise.expenses;

import com.satyam.splitwise.expenses.dtos.CreateExpenseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/expense")
public class ExpenseController {

    private ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping("")
    public ResponseEntity createExpense(@RequestBody CreateExpenseDto expense){
        return ResponseEntity.ok(expenseService.createExpense(expense));
    }

    @GetMapping("/{groupId}")
    public ResponseEntity getAllExpensesByGroup(@PathVariable("groupId") Long groupId){
        return ResponseEntity.ok(expenseService.getExpensesByGroup(groupId));
    }
}
