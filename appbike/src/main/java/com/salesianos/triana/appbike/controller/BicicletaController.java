package com.salesianos.triana.appbike.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Bicicleta", description = "El controlador de bicicletas tiene diferentes métodos para obtener información variada" +
        " sobre las bicicletas, y opciones como alquilar una")
public class BicicletaController {



}
