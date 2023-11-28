package com.salesianos.triana.appbike.service;

import com.salesianos.triana.appbike.dto.Uso.UsoResponse;
import com.salesianos.triana.appbike.exception.NotFoundException;
import com.salesianos.triana.appbike.exception.InUseException;
import com.salesianos.triana.appbike.model.Bicicleta;
import com.salesianos.triana.appbike.model.Coste;
import com.salesianos.triana.appbike.model.Uso;
import com.salesianos.triana.appbike.model.Usuario;
import com.salesianos.triana.appbike.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsoService {


    private final UsoRepository usoRepository;
    private final BicicletaRepository bicicletaRepository;
    private final UsuarioBiciRepository usuarioBiciRepository;
    private final EstacionRepository estacionRepository;
    private final CosteRepository costeRepository;

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

    public Uso findByLastUse(Usuario user){
        Optional<Uso> activeUse = usoRepository.findCurrentUsoByUser(user.getId().toString());
        Optional<Uso> finishedUse = usoRepository.findLastUsoFinished(user.getId().toString());
        if(activeUse.isPresent()){
            throw new InUseException("The user already has a bike in use");
        }
        if(finishedUse.isPresent()){
            return finishedUse.get();
        }
        throw new NotFoundException("Uso");
    }

    public Uso findActiveUseByUser(UUID uuid){
        //Authentication principal
        return usuarioBiciRepository.findActiveUsoByUsuario(uuid.toString())
                .orElseThrow(() -> new NotFoundException("Uso"));
    }

    public Uso finishActiveUseByUser(UUID idUser, UUID idEstacion) {
        Duration duration;
        Coste coste = costeRepository.getCurrentCost();

        Uso toFinish = usuarioBiciRepository.findActiveUsoByUsuario(idUser.toString())
                .orElseThrow(() -> new NotFoundException("Uso"));

        if (estacionRepository.findById(idEstacion).isPresent()) {
            toFinish.setFechaFin(LocalDateTime.now());
            toFinish.setEstacion(estacionRepository.findById(idEstacion).get());
        } else {
            throw new NotFoundException("Estacion");
        }

        long difference = ChronoUnit.MINUTES.between(toFinish.getFechaInicio(), toFinish.getFechaFin());

        toFinish.setCoste(difference * coste.getPrecioMinuto()); //Entity Cost

        return usoRepository.save(toFinish);
    }

    public Page<UsoResponse> findUsoByUser(UUID userId, Pageable pageable){
        return usoRepository.findByAuthor(userId.toString(), pageable).map(UsoResponse::of);
    }
}
