package com.salesianos.triana.appbike.service;

import com.salesianos.triana.appbike.model.UsuarioBici;
import com.salesianos.triana.appbike.repository.UsuarioBiciRepository;
import com.salesianos.triana.appbike.dto.UsuarioBici.AddUsuarioBici;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioBiciService {

    private final PasswordEncoder passwordEncoder;
    private final UsuarioBiciRepository usuarioBiciRepository;

    public UsuarioBici createUser(AddUsuarioBici addUsuarioBici) {
        UsuarioBici user = new UsuarioBici();
                user.setUsername(addUsuarioBici.username());
                user.setPassword(passwordEncoder.encode(addUsuarioBici.password()));
                user.setEmail(addUsuarioBici.email());
                user.setNombre(addUsuarioBici.nombre());

        return usuarioBiciRepository.save(user);
    }

}
