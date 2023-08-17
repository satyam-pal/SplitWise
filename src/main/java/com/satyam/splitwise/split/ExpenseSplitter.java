package com.satyam.splitwise.split;

import com.satyam.splitwise.expenses.SplitType;
import com.satyam.splitwise.split.dtos.AddSplitDto;
import com.satyam.splitwise.split.strategies.CustomSplitStrategy;
import com.satyam.splitwise.split.strategies.EqualSplitStrategy;
import com.satyam.splitwise.split.strategies.PercentageSplitStrategy;
import com.satyam.splitwise.split.strategies.SplitStrategy;

import java.util.List;

public class ExpenseSplitter {

    private SplitStrategy splitStrategy;
    public ExpenseSplitter(SplitType splitType){
        switch (splitType){
            case EQUAL -> this.splitStrategy = new EqualSplitStrategy();
            case PERCENTAGE -> this.splitStrategy = new PercentageSplitStrategy();
            case CUSTOM -> this.splitStrategy = new CustomSplitStrategy();
        }
    }

    public List<AddSplitDto> splitExpense(Double amount, List<AddSplitDto> participants){
        return this.splitStrategy.split(amount,participants);
    }
}
