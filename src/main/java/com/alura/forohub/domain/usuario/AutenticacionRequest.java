package com.alura.forohub.domain.usuario;

import jakarta.validation.constraints.NotBlank;

public record AutenticacionRequest(
        @NotBlank
        String username,
        @NotBlank
        String password
) {
}
