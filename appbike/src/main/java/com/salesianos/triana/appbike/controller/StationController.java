package com.salesianos.triana.appbike.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
@RequestMapping("estacion")
@RequiredArgsConstructor
@Tag(name="Station",description = "CRUD para el manejo de estaciones en la API.")
public class StationController {

    @PostMapping("/add/station")
    @Operation(summary = "addEstacion",description = "Crea una nueva estación")
    public ResponseEntity<AddStationDto>addStation(){

    }


}
