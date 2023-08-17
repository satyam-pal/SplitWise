package com.satyam.splitwise.user.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class UserLoginRequestDto {

    @NonNull
    private final String username;

    @NonNull
    private final String password;

}
