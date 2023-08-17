package com.satyam.splitwise.expenses.dtos;

import com.satyam.splitwise.expense_group.dtos.GroupDto;
import com.satyam.splitwise.split.SplitModel;
import com.satyam.splitwise.split.dtos.AddSplitDto;
import com.satyam.splitwise.split.dtos.SplitDto;
import com.satyam.splitwise.user.dtos.UserDetailsDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseDto {

    Long id;

    @NonNull
    Double amount;

    String description;

    GroupDto expenseGroup;

    @NonNull
    UserDetailsDto createdBy;

    @NonNull
    UserDetailsDto paidBy;

    List<SplitDto> splits;
}
