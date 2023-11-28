package com.salesianos.triana.appbike.controller;

import com.salesianos.triana.appbike.dto.UsuarioBici.UsuarioBiciDTO;
import com.salesianos.triana.appbike.model.UsuarioBici;
import com.salesianos.triana.appbike.service.UsuarioBiciService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UsuarioBiciController {

    private final UsuarioBiciService usuarioBiciService;

    @GetMapping("/{id}")
    public UsuarioBiciDTO getUserDetails(@PathVariable UUID id){
        return UsuarioBiciDTO.of(usuarioBiciService.findById(id));
    }
}
