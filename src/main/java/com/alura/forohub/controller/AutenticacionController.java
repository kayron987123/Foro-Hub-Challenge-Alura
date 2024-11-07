package com.alura.forohub.controller;

import com.alura.forohub.domain.usuario.AutenticacionRequest;
import com.alura.forohub.domain.usuario.Usuario;
import com.alura.forohub.infra.security.JwtTokenResponse;
import com.alura.forohub.infra.security.TokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class AutenticacionController {
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody @Valid AutenticacionRequest autenticacionRequest) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(autenticacionRequest.username(),
                autenticacionRequest.password());
        var usuarioAutenticado = authenticationManager.authenticate(authToken);
        var JWTtoken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new JwtTokenResponse(JWTtoken));
    }
}
