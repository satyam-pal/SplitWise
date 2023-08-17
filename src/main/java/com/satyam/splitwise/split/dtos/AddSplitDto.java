package com.satyam.splitwise.split.dtos;

import com.satyam.splitwise.expenses.ExpenseModel;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddSplitDto {

    @NonNull
    Long user;

    Double amountValue;

}
