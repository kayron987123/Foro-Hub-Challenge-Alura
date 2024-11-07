package com.alura.forohub.controller;

import com.alura.forohub.domain.topico.TopicoActualizarRequest;
import com.alura.forohub.domain.topico.TopicoRequest;
import com.alura.forohub.domain.topico.TopicoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
@RequiredArgsConstructor
public class TopicoController {

    private final TopicoService topicoService;

    @GetMapping("/listar")
    public ResponseEntity<?> listarTopicos(){
        return ResponseEntity.ok(topicoService.listarTopico());
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarTopicoPorId(@PathVariable Long id){
        return ResponseEntity.ok(topicoService.listarTopicoPorId(id));
    }

    @PostMapping("/guardar")
    public ResponseEntity<?> guardarTopico(@RequestBody @Valid TopicoRequest topicoRequest, UriComponentsBuilder uriComponentsBuilder){
        var response = topicoService.guardarTopico(topicoRequest);
        URI url =uriComponentsBuilder.path("/topicos/listar/{id}").buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(url).body(response);
    }

    @PutMapping("/actualizar")
    public ResponseEntity<?> actualizarTopico(@RequestBody @Valid TopicoActualizarRequest topicoActualizarRequest){
        var response = topicoService.actualizarTopico(topicoActualizarRequest);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarTopico(@PathVariable Long id){
        topicoService.eliminarTopico(id);
        return ResponseEntity.noContent().build();
    }

}
