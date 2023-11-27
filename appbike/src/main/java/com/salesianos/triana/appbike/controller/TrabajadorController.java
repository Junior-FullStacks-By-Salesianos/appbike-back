package com.salesianos.triana.appbike.controller;

import com.salesianos.triana.appbike.service.TrabajadorService;
import com.salesianos.triana.appbike.dto.Trabajador.TrabajadorDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/worker")
public class TrabajadorController {

    private final TrabajadorService trabajadorService;

    //Si da tiempo se hace
//    @PostMapping("/admin/worker")
//    public ResponseEntity<Trabajador> applyWorkerStatus(Usuario u){
//
//    }

    @GetMapping("/")
    public ResponseEntity<List<TrabajadorDTO>> getAll(){
        if(trabajadorService.findAll().isEmpty())
            return ResponseEntity.noContent().build();

        List<TrabajadorDTO> allTrabajadores = trabajadorService.findAll().stream()
                .map((TrabajadorDTO::of))
                .toList();

        return ResponseEntity.ok(allTrabajadores);
    }
}
