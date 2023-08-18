package com.satyam.splitwise.split.strategies;

import com.satyam.splitwise.split.dtos.AddSplitDto;
import com.satyam.splitwise.split.exception.InvalidPercentException;

import java.util.List;
import java.util.stream.Collectors;

public class PercentageSplitStrategy implements SplitStrategy{
    @Override
    public List<AddSplitDto> split(Double amount, List<AddSplitDto> participants) {
        double totalPercent = participants.stream().map(s -> s.getAmountValue()).collect(Collectors.summingDouble(Double::doubleValue));
        if(totalPercent > 100){
            throw new InvalidPercentException("Total percent exceeds 100");
        }
        return participants.stream().map((s) -> {
            if(s.getAmountValue()!=null) return new AddSplitDto(s.getUser(),(s.getAmountValue()*amount)/100);
            else throw new InvalidPercentException("No percentage in split for user: "+s.getUser());
        }).collect(Collectors.toList());
    }
}
