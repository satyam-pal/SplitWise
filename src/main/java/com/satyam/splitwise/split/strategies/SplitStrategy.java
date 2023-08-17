package com.satyam.splitwise.split.strategies;

import com.satyam.splitwise.split.SplitModel;
import com.satyam.splitwise.split.dtos.AddSplitDto;
import com.satyam.splitwise.user.UserModel;

import java.util.List;

public interface SplitStrategy {

    public List<AddSplitDto> split(Double amount, List<AddSplitDto> participants);
}
