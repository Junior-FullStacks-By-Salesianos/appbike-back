package com.salesianos.triana.appbike.controller;

import com.salesianos.triana.appbike.model.Usuario;
import com.salesianos.triana.appbike.dto.Usuario.LoginUser;
import com.salesianos.triana.appbike.security.jwt.JwtProvider;
import com.salesianos.triana.appbike.security.jwt.JwtUserResponse;
import com.salesianos.triana.appbike.dto.UsuarioBici.AddUsuarioBici;
import com.salesianos.triana.appbike.model.UsuarioBici;
import com.salesianos.triana.appbike.service.UsuarioBiciService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "User", description = "El controlador de user tiene diferentes métodos para obtener información variada" +
        " sobre los usuarios, tanto como métodos para el registro y login")
public class UserController {

        private final UsuarioBiciService userService;
        private final AuthenticationManager authManager;
        private final JwtProvider jwtProvider;

        @Operation(summary = "Register user")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "201 Created", description = "Register was succesful", content = {
                        @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = JwtUserResponse.class)), examples = {
                                @ExampleObject(value = """
                                                                        {
                                                                            "id": "ba00362c-f808-4dfd-8d0c-386d6c1757a9",
                                                                            "username": "alexluque",
                                                                            "email": "user@gmail.com",
                                                                            "nombre": "Alexander Luque",
                                                                            "createdAt": "22/11/2023 10:27:44",
                                                                            "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJiYTAwMzYyYy1mODA4LTRkZmQtOGQwYy0zODZkNmMxNzU3YTkiLCJpYXQiOjE3MDA2NDUyNjQsImV4cCI6MTcwMDczMTY2NH0.2a62n6XejYfeInr-00ywKVfm5me6armBPHA7ehLMwyelHvnLUWRLGmLv6CUN6nZd8QvKMlueIRQEezAqmftcPw"
                                                                        }
                                                                        """) }) }),
                @ApiResponse(responseCode = "400 Bad Request", description = "Register was not succesful", content = @Content),
        })
        @PostMapping("/auth/register")//Autor del método Alex
        public ResponseEntity<JwtUserResponse> createUserWithUserBike(@Valid @RequestBody AddUsuarioBici addUsuarioBici) {
                UsuarioBici usuario = userService.createUser(addUsuarioBici);
                Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(addUsuarioBici.username(),addUsuarioBici.password()));
                SecurityContextHolder.getContext().setAuthentication(authentication);
                String token = jwtProvider.generateToken(authentication);
                return ResponseEntity.status(HttpStatus.CREATED).body(JwtUserResponse.of(usuario, token));
        }


        @Operation(summary = "Login user")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "201 Created", description = "Login was succesful", content = {
                        @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = JwtUserResponse.class)), examples = {
                                @ExampleObject(value = """
                                                                        {
                                                                            "id": "ba00362c-f808-4dfd-8d0c-386d6c1757a9",
                                                                            "username": "alexluque",
                                                                            "email": "user@gmail.com",
                                                                            "nombre": "Alexander Luque",
                                                                            "createdAt": "22/11/2023 10:27:44",
                                                                            "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJiYTAwMzYyYy1mODA4LTRkZmQtOGQwYy0zODZkNmMxNzU3YTkiLCJpYXQiOjE3MDA2NDUyNjQsImV4cCI6MTcwMDczMTY2NH0.2a62n6XejYfeInr-00ywKVfm5me6armBPHA7ehLMwyelHvnLUWRLGmLv6CUN6nZd8QvKMlueIRQEezAqmftcPw"
                                                                        }
                                                                        """) }) }),
                @ApiResponse(responseCode = "400 Bad Request", description = "Login was not succesful", content = @Content),
        })
        @PostMapping("/auth/login") //Autor del método Alex
        public ResponseEntity<JwtUserResponse> login(@RequestBody LoginUser loginUser) {

                Authentication authentication = authManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                loginUser.username(),
                                loginUser.password()));
                SecurityContextHolder.getContext().setAuthentication(authentication);
                String token = jwtProvider.generateToken(authentication);

                Usuario user = (Usuario) authentication.getPrincipal();

                return ResponseEntity.status(HttpStatus.CREATED)
                        .body(JwtUserResponse.of(user, token));

        }

}
