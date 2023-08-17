package com.satyam.splitwise.split.strategies;

import com.satyam.splitwise.split.SplitModel;
import com.satyam.splitwise.split.dtos.AddSplitDto;
import com.satyam.splitwise.user.UserModel;

import java.util.List;
import java.util.stream.Collectors;

public class EqualSplitStrategy implements SplitStrategy{
    @Override
    public List<AddSplitDto> split(Double amount, List<AddSplitDto> participants) {
         Double amountPerHead = amount / (participants.size()+1);
         return participants.stream().map(s -> new AddSplitDto(s.getUser(),amountPerHead)).collect(Collectors.toList());
    }
}
