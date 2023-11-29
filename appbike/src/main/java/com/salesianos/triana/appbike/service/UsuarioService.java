package com.salesianos.triana.appbike.service;

import com.salesianos.triana.appbike.model.Usuario;
import com.salesianos.triana.appbike.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public Optional<Usuario> findById(UUID id){
        return usuarioRepository.findById(id);
    }

    public boolean userExists(String username) {
        return usuarioRepository.existsByUsernameIgnoreCase(username);
    }


}
