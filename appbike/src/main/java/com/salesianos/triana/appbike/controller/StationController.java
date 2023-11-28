package com.salesianos.triana.appbike.controller;

import com.salesianos.triana.appbike.dto.Bike.GetBicicletaDTO;
import com.salesianos.triana.appbike.dto.station.AddStationDto;
import com.salesianos.triana.appbike.dto.station.EditStationDto;
import com.salesianos.triana.appbike.dto.station.GetStationDto;
import com.salesianos.triana.appbike.dto.station.StationResponse;
import com.salesianos.triana.appbike.error.BikesInThatStationException;
import com.salesianos.triana.appbike.model.Estacion;
import com.salesianos.triana.appbike.service.EstacionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RestController
@RequestMapping("stations")
@RequiredArgsConstructor
@Tag(name = "Station", description = "CRUD for managing stations in the API..")
public class StationController {

    private final EstacionService estacionService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Creation of new Stations", content = {
                    @Content(mediaType = "application/json", examples = { @ExampleObject(value = """
                            {
                                 "id": "f1b6d478-d973-435b-bab6-42b0771e2278",
                                 "name": "Plaza de Cuba",
                                 "coordinates": "12.345,-0.12345",
                                 "capacity": 29,
                                 "bikes": null
                             }
                            """) }) }),
            @ApiResponse(responseCode = "400", description = "The creation of the station has not been done", content = @Content)

    }

    )
    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "addEstacion", description = "Create a new Station")
    public ResponseEntity<StationResponse> addStation(@Valid @RequestBody AddStationDto estacion) {
        Estacion e = estacionService.newStation(estacion);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(StationResponse.of(e));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Get all stations", content = {
                    @Content(mediaType = "application/json", examples = { @ExampleObject(value = """
                                {
                                    "number": "1",
                                    "name": "Plaza de Armas",
                                    "coordinates": "12.345,-0.12345",
                                    "capacity": 0,
                                    "bikes": 0
                                },
                                {
                                    "number": "12",
                                    "name": "Plaza de Cuba",
                                    "coordinates": "12.345,-0.12345",
                                    "capacity": 0,
                                    "bikes": 0
                                }
                            """) }) }),
            @ApiResponse(responseCode = "400", description = "An error appears with the list of the stations", content = @Content)

    }

    )
    @Operation(summary = "findAll", description = "Find All Stations in the database")
    @GetMapping("/get")
    public ResponseEntity<List<GetStationDto>> findAllStations() {

        List<GetStationDto> all = estacionService.findAll();

        if (all.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(all);
    }

    @Operation(summary = "Gets a station from its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The station has been found", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = GetBicicletaDTO.class)), examples = {
                            @ExampleObject(value = """
                                    {
                                        "numero": 2,
                                        "name": "Plaza de España",
                                        "coordinates": "37.37739933159319, -5.987447323379356",
                                        "capacity": 10,
                                        "bikes": [
                                            {
                                                "uuid": "e6c62724-b555-41e2-b8c2-5d27d2b51b06",
                                                "nombre": "Patricio",
                                                "marca": "FieldCletas",
                                                "modelo": "Gen15",
                                                "estado": "WORN_OUT",
                                                "usos": 0,
                                                "estacion": "Plaza de España"
                                            },
                                            {
                                                "uuid": "be2a602d-2bcc-4bd4-93cf-29d3abfaf70a",
                                                "nombre": "Hofrague",
                                                "marca": "ChimneyChains",
                                                "modelo": "SmokeyCruise",
                                                "estado": "NEW",
                                                "usos": 0,
                                                "estacion": "Plaza de España"
                                            }
                                        ]
                                    }
                                                                        """) }) }),
            @ApiResponse(responseCode = "404", description = "Any Station was found", content = @Content),
    })
    @GetMapping("/get/{id}")
    public StationResponse findStationById(@PathVariable Long id) {
        return StationResponse.of(estacionService.findById(id));
    }

    @Operation(summary = "Edit a station ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The station has been edited", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StationResponse.class))
            }),
            @ApiResponse(responseCode = "404", description = "Something went wrong", content = @Content)
    })
    @PutMapping("/edit/{id}")
    public ResponseEntity<StationResponse> editStation(@PathVariable Long id, @RequestBody @Valid EditStationDto e) {
        Estacion estacion = estacionService.editStation(id, e);
        if (estacion != null) {
            StationResponse stationResponse = StationResponse.of(estacion);
            return ResponseEntity.status(HttpStatus.OK).body(stationResponse);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Station delete")
    })
    @Operation(summary = "deleteBike", description = "Delete a Station checking that the station is in the database saved")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteStationById(@PathVariable Long id) {
        Estacion estacion = estacionService.findStationById(id)
                .orElseThrow(() -> new EntityNotFoundException("Unable to find a station with id: " + id));

        if (estacion.getBicicletas() != null && !estacion.getBicicletas().isEmpty()) {
            throw new BikesInThatStationException();
        }

        estacionService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
