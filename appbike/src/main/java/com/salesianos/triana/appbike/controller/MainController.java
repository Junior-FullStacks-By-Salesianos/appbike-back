package com.salesianos.triana.appbike.controller;

import com.salesianos.triana.appbike.dto.AddUsuarioBici;
import com.salesianos.triana.appbike.dto.UsuarioBiciResponse;
import com.salesianos.triana.appbike.model.UsuarioBici;
import com.salesianos.triana.appbike.security.jwt.JwtProvider;
import com.salesianos.triana.appbike.service.UsuarioBiciService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MainController {

    private final UsuarioBiciService userService;
    private final AuthenticationManager authManager;
    private final JwtProvider jwtProvider;


    @PostMapping("/auth/register")
    public ResponseEntity<UsuarioBiciResponse> createUserWithUserRole(@RequestBody AddUsuarioBici addUsuarioBici) {
        UsuarioBici usuario = userService.createUser(addUsuarioBici);

        return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioBiciResponse.of(usuario));
    }

}
