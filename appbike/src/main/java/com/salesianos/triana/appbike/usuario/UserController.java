package com.salesianos.triana.appbike.usuario;

import com.salesianos.triana.appbike.usuariobici.AddUsuarioBici;
import com.salesianos.triana.appbike.security.jwt.JwtUserResponse;
import com.salesianos.triana.appbike.security.LoginUser;
import com.salesianos.triana.appbike.security.UserResponse;
import com.salesianos.triana.appbike.usuario.Usuario;
import com.salesianos.triana.appbike.usuariobici.UsuarioBici;
import com.salesianos.triana.appbike.security.jwt.JwtProvider;
import com.salesianos.triana.appbike.usuariobici.UsuarioBiciService;
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
public class UserController {

    private final UsuarioBiciService userService;
    private final AuthenticationManager authManager;
    private final JwtProvider jwtProvider;


    @PostMapping("/auth/register")
    public ResponseEntity<UserResponse> createUserWithUserRole(@RequestBody AddUsuarioBici addUsuarioBici) {
        UsuarioBici usuario = userService.createUser(addUsuarioBici);

        return ResponseEntity.status(HttpStatus.CREATED).body(UserResponse.of(usuario));
    }

    @PostMapping("/auth/login")
    public ResponseEntity<JwtUserResponse> login(@RequestBody LoginUser loginUser) {

        // Realizamos la autenticación

        Authentication authentication =
                authManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                loginUser.username(),
                                loginUser.password()
                        )
                );

        // Una vez realizada, la guardamos en el contexto de seguridad
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Devolvemos una respuesta adecuada
        String token = jwtProvider.generateToken(authentication);

        Usuario user = (Usuario) authentication.getPrincipal();


        return ResponseEntity.status(HttpStatus.CREATED)
                .body(JwtUserResponse.of(user, token));


    }

}
