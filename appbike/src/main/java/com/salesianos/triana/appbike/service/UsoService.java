package com.salesianos.triana.appbike.service;

import com.salesianos.triana.appbike.exception.NotFoundException;
import com.salesianos.triana.appbike.exception.InUseException;
import com.salesianos.triana.appbike.model.Bicicleta;
import com.salesianos.triana.appbike.model.Uso;
import com.salesianos.triana.appbike.model.Usuario;
import com.salesianos.triana.appbike.repository.BicicletaRepository;
import com.salesianos.triana.appbike.repository.UsoRepository;
import com.salesianos.triana.appbike.repository.UsuarioBiciRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsoService {


    private final UsoRepository usoRepository;
    private final BicicletaRepository bicicletaRepository;
    private final UsuarioBiciRepository usuarioBiciRepository;

    public Uso addUso(UUID idBicicleta, Usuario user) {
        Uso u = new Uso();
        Optional<Bicicleta> bike = bicicletaRepository.findById(idBicicleta);

        if(usoRepository.findCurrentUsoByUser(user.getId().toString()).isPresent()){
            throw new InUseException("The user already has a bike in use");
        }

        if (bike.isPresent()) {
            if(usoRepository.findCurrentUsoByBicicleta(bike.get().getUuid()).isPresent()){
                throw new InUseException("The bike is already in use");
            }
            bike.get().setEstacion(null);
            u.setBicicleta(bike.get());
            u.setFechaInicio(LocalDateTime.now());
            u.setAuthor(user.getId().toString());
            return usoRepository.save(u);
        } else {
            throw new NotFoundException("Bicicleta");
        }
    }

    public Uso findById(Long id){
        if(usoRepository.findById(id).isPresent()){
            return usoRepository.findById(id).get();
        }
        throw new NotFoundException("Uso");
    }

    public Uso findActiveUseByUser(UUID uuid){
        //Authentication principal
        if(usuarioBiciRepository.findActiveUsoByUsuario(uuid.toString()).isPresent()){
         return usuarioBiciRepository.findActiveUsoByUsuario(uuid.toString()).get();
        }

        throw new NotFoundException("Uso");
    }
}
