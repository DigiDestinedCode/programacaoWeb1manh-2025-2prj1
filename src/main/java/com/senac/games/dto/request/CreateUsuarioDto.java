package com.senac.games.dto.request;


import com.senac.games.entity.RoleName;

public record CreateUsuarioDto(
        String login,
        String senha,
        RoleName role
) {

}
