package com.alura.forohub.domain.topico;

import java.time.LocalDateTime;

public record TopicoResponse(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        String status,
        String nombreAutor,
        String nombreCurso
) {
}
