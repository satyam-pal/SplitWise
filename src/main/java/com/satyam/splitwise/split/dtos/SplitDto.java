package com.satyam.splitwise.split.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SplitDto {

    Long id;

    Long userId;

    Double amount;

}
