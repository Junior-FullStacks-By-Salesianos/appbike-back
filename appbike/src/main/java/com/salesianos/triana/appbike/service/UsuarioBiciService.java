package com.salesianos.triana.appbike.service;

import com.salesianos.triana.appbike.dto.UsuarioBici.AddUsuarioBici;
import com.salesianos.triana.appbike.dto.UsuarioBici.UsuarioBiciDTO;
import com.salesianos.triana.appbike.exception.NotFoundException;
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
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsuarioBiciService {

    private final PasswordEncoder passwordEncoder;
    private final UsuarioBiciRepository usuarioBiciRepository;
    private final UsuarioRepository usuarioRepository;

    public UsuarioBici createUser(AddUsuarioBici addUsuarioBici) {

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

    public UsuarioBici findById(UUID id){
        return usuarioBiciRepository.findById(id).orElseThrow(() -> new NotFoundException("Cannot find a user with the specified id."));
    }

    public UsuarioBici setCard(UUID id, UsuarioBiciDTO u){
        return usuarioBiciRepository.findById(id).map(old -> {
            old.setNumTarjeta(u.numTarjeta());
            old.setPin(u.pin());
            return usuarioBiciRepository.save(old);
        }).orElseThrow(() -> new EntityNotFoundException("Could not set card for user as there are no users with a matching id"));
    }

}
