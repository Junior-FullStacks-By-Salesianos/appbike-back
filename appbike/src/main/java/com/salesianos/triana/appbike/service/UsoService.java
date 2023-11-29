package com.salesianos.triana.appbike.service;

import com.salesianos.triana.appbike.dto.Uso.UsoResponse;
import com.salesianos.triana.appbike.MyPage;
import com.salesianos.triana.appbike.dto.Uso.EditUso;
import com.salesianos.triana.appbike.dto.Usuario.UserResponse;
import com.salesianos.triana.appbike.exception.NotEnoughBalanceException;
import com.salesianos.triana.appbike.exception.NotFoundException;
import com.salesianos.triana.appbike.exception.InUseException;
import com.salesianos.triana.appbike.model.*;
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

    public Uso addUso(UUID idBicicleta, UsuarioBici user) {
        Uso u = new Uso();
        Optional<Bicicleta> bike = bicicletaRepository.findById(idBicicleta);

        if(usoRepository.findCurrentUsoByUser(user.getId().toString()).isPresent()){
            throw new InUseException("The user already has a bike in use");
        }

        if(user.getSaldo()<=0){
            throw new NotEnoughBalanceException("You can´t rent a bike because you haven´t got enough balance");
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
        Coste coste = costeRepository.getCurrentCost();
        Optional<Estacion> e = estacionRepository.findById(idEstacion);
        UsuarioBici usuarioBici = usuarioBiciRepository.findById(idUser).orElseThrow(()-> new NotFoundException("User"));
        long difference;
        double totalCost;

        Uso toFinish = usuarioBiciRepository.findActiveUsoByUsuario(idUser.toString())
                .orElseThrow(() -> new NotFoundException("Uso"));

        if (e.isPresent()) {
            toFinish.setFechaFin(LocalDateTime.now());
            toFinish.setEstacion(e.get());
            e.get().addBicicleta(toFinish.getBicicleta());
        } else {
            throw new NotFoundException("Estacion");
        }

        difference = ChronoUnit.MINUTES.between(toFinish.getFechaInicio(), toFinish.getFechaFin());
        totalCost = difference * coste.getPrecioMinuto();
        toFinish.setCoste(totalCost); //Entity Cost

        usuarioBici.setSaldo(usuarioBici.getSaldo()-totalCost);
        usuarioBiciRepository.save(usuarioBici);

        return usoRepository.save(toFinish);
    }

    public Page<UsoResponse> findUsoByUser(UUID userId, Pageable pageable){
        return usoRepository.findByAuthor(userId.toString(), pageable).map(UsoResponse::of);
    }

    public MyPage<UsoResponse> findAllUses(Pageable pageable){
        Page<Uso> usos =  usoRepository.findAllPageable(pageable);
        if(usos.isEmpty()){
            throw new NotFoundException("Uso");
        }
        return MyPage.of(usos
                .map(uso -> UsoResponse.of(uso, usuarioBiciRepository.findById(UUID.fromString(uso.getAuthor())).orElseThrow(()-> new NotFoundException("User")))));
    }

    public Uso editUse (UUID id, EditUso editUso){
        Uso u = usoRepository.findById(id).orElseThrow(() -> new NotFoundException("Uso"));
        if(u.getFechaFin() == null){
            throw new InUseException("the Trip you have choosed is taking place");
        }
        u.setCoste(editUso.coste());
        return usoRepository.save(u);
    }
}
