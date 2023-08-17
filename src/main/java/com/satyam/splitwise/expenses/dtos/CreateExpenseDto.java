package com.satyam.splitwise.expenses.dtos;

import com.satyam.splitwise.split.dtos.AddSplitDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateExpenseDto {

    @NonNull
    Double amount;

    String description;

    Long expenseGroup;

    @NonNull
    Long createdBy;

    @NonNull
    Long paidBy;

    String splitType;

    List<AddSplitDto> splits;
}
