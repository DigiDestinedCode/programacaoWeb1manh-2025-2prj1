package com.senac.games.dto.request;


import com.senac.games.entity.RoleName;

public record CreateUserDto(
        String email,
        String password,
        RoleName role
) {

}
