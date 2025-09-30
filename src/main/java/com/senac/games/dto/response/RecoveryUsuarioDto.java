package com.senac.games.dto.response;



import com.senac.games.entity.Role;

import java.util.List;

public record RecoveryUsuarioDto(
        Long id,
        String email,
        List<Role> roles
) {
}
