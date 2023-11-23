package com.salesianos.triana.appbike.service;

import com.salesianos.triana.appbike.dto.AddUso;
import com.salesianos.triana.appbike.model.Uso;
import com.salesianos.triana.appbike.model.Usuario;
import com.salesianos.triana.appbike.repository.BicicletaRepository;
import com.salesianos.triana.appbike.repository.UsoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UsoService {


    private final UsoRepository usoRepository;
    private final BicicletaRepository bicicletaRepository;

    public Uso addUso(AddUso add, Usuario user){
        Uso u = new Uso();
        System.out.println(add.id_bicicleta());
        if(bicicletaRepository.findById(add.id_bicicleta()).isPresent());{
            u.setBicicleta(bicicletaRepository.findById(add.id_bicicleta()).get());
        }
        u.setFechaInicio(LocalDateTime.now());
        u.setAuthor(user.getId().toString());
        return u;
    }
}
