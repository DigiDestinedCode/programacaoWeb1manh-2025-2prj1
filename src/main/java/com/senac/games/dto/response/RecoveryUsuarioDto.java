package com.senac.games.dto.request;



import com.senac.games.entity.Role;

import java.util.List;

public record RecoveryUsuarioDto(
        Long id,
        String email,
        List<Role> roles
) {
}
