package com.aluracursos.forohub.controller;

import com.aluracursos.forohub.domain.user.DataAuthenticationUser;
import com.aluracursos.forohub.domain.user.User;
import com.aluracursos.forohub.infra.security.DataJWTToken;
import com.aluracursos.forohub.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid DataAuthenticationUser data) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var authenticatedUser = authenticationManager.authenticate(authToken);

        var jwTtoken = tokenService.generateToken((User) authenticatedUser.getPrincipal());

        return ResponseEntity.ok(new DataJWTToken(jwTtoken));
    }
}
