package com.satyam.splitwise.split.strategies;

import com.satyam.splitwise.split.SplitModel;
import com.satyam.splitwise.split.dtos.AddSplitDto;
import com.satyam.splitwise.split.exception.AmountExceededTotalAmountException;
import com.satyam.splitwise.user.UserModel;

import java.util.List;
import java.util.stream.Collectors;

public class CustomSplitStrategy implements SplitStrategy{

    @Override
    public List<AddSplitDto> split(Double amount, List<AddSplitDto> participants) {
        double totalValue = participants.stream().map(s -> s.getAmountValue()).collect(Collectors.summingDouble(Double::doubleValue));
        if(totalValue > amount){
            throw new AmountExceededTotalAmountException();
        }
        return participants;
    }
}
