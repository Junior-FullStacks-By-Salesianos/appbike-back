package com.salesianos.triana.appbike.service;

import com.salesianos.triana.appbike.exception.NotFoundException;
import com.salesianos.triana.appbike.model.Coste;
import com.salesianos.triana.appbike.repository.CosteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CosteService {

    private final CosteRepository costeRepository;

    public Coste findCurrentCost(){

        Coste c = costeRepository.getCurrentCost();

        if(c!=null){
            return c;
        }

        throw new NotFoundException("Coste");

    }

}
