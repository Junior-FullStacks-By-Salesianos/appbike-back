package com.salesianos.triana.appbike.service;

import com.salesianos.triana.appbike.dto.AddUso;
import com.salesianos.triana.appbike.model.Bicicleta;
import com.salesianos.triana.appbike.model.Uso;
import com.salesianos.triana.appbike.model.Usuario;
import com.salesianos.triana.appbike.repository.BicicletaRepository;
import com.salesianos.triana.appbike.repository.UsoRepository;
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

    public Uso addUso(UUID idBicicleta, Usuario user){
        Uso u = new Uso();
        Optional<Bicicleta> bike = bicicletaRepository.findById(idBicicleta);
        bike.ifPresent(u::setBicicleta);
        //Gestionar error
        u.setFechaInicio(LocalDateTime.now());
        u.setAuthor(user.getId().toString());
        return u;
    }
}
