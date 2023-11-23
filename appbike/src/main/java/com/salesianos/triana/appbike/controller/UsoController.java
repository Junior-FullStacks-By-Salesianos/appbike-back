package com.salesianos.triana.appbike.controller;

import com.salesianos.triana.appbike.dto.AddUso;
import com.salesianos.triana.appbike.dto.UsoBeginResponse;
import com.salesianos.triana.appbike.model.Uso;
import com.salesianos.triana.appbike.model.Usuario;
import com.salesianos.triana.appbike.model.UsuarioBici;
import com.salesianos.triana.appbike.service.UsoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@Tag(name = "Uso", description = "El controlador de usos tiene diferentes métodos para obtener información variada" +
        " sobre los usos, y opciones como alquilar una bicicleta o finalizar un viaje")
public class UsoController {

    private final UsoService usoService;

    /*@PostMapping("/rent")
    public ResponseEntity<UsoBeginResponse> rentABike(@Valid @RequestBody AddUso addUso, @AuthenticationPrincipal Usuario user) {

        Uso newUso = usoService.addUso(addUso, user);

        if (newUso == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        URI createdURI = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newUso.getId()).toUri();

        return ResponseEntity
                .created(createdURI)
                .body(UsoBeginResponse.of(newUso));
    }*/
}
