package com.salesianos.triana.appbike.service;

import com.salesianos.triana.appbike.dto.LoginUser;
import com.salesianos.triana.appbike.exception.InvalidCredentialsException;
import com.salesianos.triana.appbike.exception.NotFoundException;
import com.salesianos.triana.appbike.model.Usuario;
import com.salesianos.triana.appbike.repository.UsuarioRepository;
import com.salesianos.triana.appbike.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public Optional<Usuario> findById(UUID id){
        return usuarioRepository.findById(id);
    }


}
