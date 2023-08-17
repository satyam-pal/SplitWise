package com.satyam.splitwise.user.dtos;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsDto {

    @NonNull
    private Long userId;

    @NonNull
    private String username;

    @NonNull
    private String email;

    @NonNull
    private String name;

}
