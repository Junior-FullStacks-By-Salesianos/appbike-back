package com.salesianos.triana.appbike.service;

import com.salesianos.triana.appbike.dto.UsuarioBici.AddUsuarioBici;
import com.salesianos.triana.appbike.dto.UsuarioBici.EditSaldo;
import com.salesianos.triana.appbike.exception.InvalidPinExcepcion;
import com.salesianos.triana.appbike.exception.NotFoundException;
import com.salesianos.triana.appbike.model.Usuario;
import com.salesianos.triana.appbike.model.UsuarioBici;
import com.salesianos.triana.appbike.repository.UsuarioBiciRepository;
import com.salesianos.triana.appbike.repository.UsuarioRepository;
import com.salesianos.triana.appbike.model.UsuarioBici;
import com.salesianos.triana.appbike.repository.UsuarioBiciRepository;
import com.salesianos.triana.appbike.dto.UsuarioBici.AddUsuarioBici;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioBiciService {

    private final PasswordEncoder passwordEncoder;
    private final UsuarioBiciRepository usuarioBiciRepository;
    private final UsuarioRepository usuarioRepository;

    public UsuarioBici createUser(AddUsuarioBici addUsuarioBici) {

        if (usuarioRepository.existsByUsernameIgnoreCase(addUsuarioBici.username()))
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre de usuario ya existe");

        UsuarioBici user = new UsuarioBici();
        user.setUsername(addUsuarioBici.username());
        user.setPassword(passwordEncoder.encode(addUsuarioBici.password()));
        user.setEmail(addUsuarioBici.email());
        user.setNombre(addUsuarioBici.nombre());

        return usuarioBiciRepository.save(user);
    }

    public UsuarioBici rechargeBalanceByUser(EditSaldo editSaldo, UsuarioBici user){
        if(editSaldo.pin().equalsIgnoreCase(user.getPin())){
            user.setSaldo(user.getSaldo()+ editSaldo.recharge());
            return usuarioBiciRepository.save(user);
        }
        throw new InvalidPinExcepcion("Your pin does not match");
    }

    public UsuarioBici getDetails(UsuarioBici user){
        return usuarioBiciRepository.findById(user.getId()).orElseThrow(() -> new NotFoundException("User"));
    }


}
