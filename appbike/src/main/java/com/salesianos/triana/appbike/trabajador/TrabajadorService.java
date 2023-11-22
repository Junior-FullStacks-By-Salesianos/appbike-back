package com.salesianos.triana.appbike.trabajador;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrabajadorService {
    private final TrabajadorRepository trabajadorRepository;
    public List<Trabajador> findAll() {
        return trabajadorRepository.findAll();
    }
}
