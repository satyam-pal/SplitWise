package com.satyam.splitwise.expenses;

import com.satyam.splitwise.expense_group.ExpenseGroupModel;
import com.satyam.splitwise.expense_group.ExpenseGroupService;
import com.satyam.splitwise.expenses.dtos.CreateExpenseDto;
import com.satyam.splitwise.expenses.dtos.ExpenseDto;
import com.satyam.splitwise.split.ExpenseSplitter;
import com.satyam.splitwise.split.SplitModel;
import com.satyam.splitwise.split.SplitService;
import com.satyam.splitwise.split.dtos.AddSplitDto;
import com.satyam.splitwise.user.UserModel;
import com.satyam.splitwise.user.UserService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ExpenseService {

    ExpenseRepository expenseRepository;

    ModelMapper modelMapper;

    ExpenseGroupService expenseGroupService;

    UserService userService;

    SplitService splitService;

    public ExpenseService(ExpenseRepository expenseRepository, ModelMapper modelMapper, ExpenseGroupService expenseGroupService, UserService userService, SplitService splitService) {
        this.expenseRepository = expenseRepository;
        this.modelMapper = modelMapper;
        this.expenseGroupService = expenseGroupService;
        this.userService = userService;
        this.splitService = splitService;
    }

    @Transactional
    public ExpenseDto createExpense(CreateExpenseDto expense) {
        ExpenseGroupModel expenseGroup = expenseGroupService.getGroupById(expense.getExpenseGroup());
        UserModel createdBy = userService.findUserById(expense.getCreatedBy());
        UserModel paidBy = userService.findUserById(expense.getPaidBy());
        List<SplitModel> splitLists = getSplits(expense);
        SplitType expenseSplitType = SplitType.valueOf(expense.getSplitType());
        ExpenseModel expenseModel = new ExpenseModel();
        expenseModel.setAmount(expense.getAmount());
        expenseModel.setDescription(expense.getDescription());
        expenseModel.setCreatedBy(createdBy);
        expenseModel.setPaidBy(paidBy);
        expenseModel.setExpenseGroup(expenseGroup);
        expenseModel.setSplitType(expenseSplitType);
        expenseModel.setSplits(splitLists);
        splitLists.stream().forEach((split) ->{
            split.setExpense(expenseModel);
        });
        ExpenseModel savedExpense = expenseRepository.save(expenseModel);
        splitService.saveAllSplits(splitLists);
        return modelMapper.map(savedExpense, ExpenseDto.class);
    }

    public List<ExpenseDto> getExpensesByGroup(Long groupId) {
        ExpenseGroupModel expenseGroupModel = expenseGroupService.findById(groupId);

        List<ExpenseDto> expensesList = expenseRepository.findByExpenseGroup(expenseGroupModel)
                                        .stream().map((ex) -> modelMapper.map(ex,ExpenseDto.class))
                .collect(Collectors.toList());
        return expensesList;
    }


    private List<SplitModel> getSplits(CreateExpenseDto expenseDto){
        SplitType expenseSplitType = SplitType.valueOf(expenseDto.getSplitType());
        ExpenseSplitter expenseSplitter = new ExpenseSplitter(expenseSplitType);
        List<AddSplitDto> lists =  expenseSplitter.splitExpense(expenseDto.getAmount(), expenseDto.getSplits());
        double splitsSum = lists.stream().map(s -> s.getAmountValue()).collect(Collectors.summingDouble(Double::doubleValue));
        AddSplitDto payerDto = new AddSplitDto();
        payerDto.setAmountValue(expenseDto.getAmount()-splitsSum);
        payerDto.setUser(expenseDto.getPaidBy());
        lists.add(payerDto);
        return lists.stream().map(s -> {
            SplitModel splitModel = new SplitModel();
            splitModel.setAmount(s.getAmountValue());
            splitModel.setUser(userService.findUserById(s.getUser()));
            return splitModel;
        }).collect(Collectors.toList());
    }
}
