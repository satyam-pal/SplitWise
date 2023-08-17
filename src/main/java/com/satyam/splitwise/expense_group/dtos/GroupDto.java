package com.satyam.splitwise.expense_group.dtos;

import com.satyam.splitwise.user.dtos.UserDetailsDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GroupDto {

    @NonNull
    private Long id;

    private String name;

    private List<UserDetailsDto> users;

}
