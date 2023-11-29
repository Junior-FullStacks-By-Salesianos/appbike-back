package com.salesianos.triana.appbike.controller;

import com.salesianos.triana.appbike.dto.Bike.GetBicicletaDTO;
import com.salesianos.triana.appbike.dto.Station.EditStationDto;
import com.salesianos.triana.appbike.dto.Station.GetStationDto;
import com.salesianos.triana.appbike.exception.BikesInThatStationException;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.salesianos.triana.appbike.dto.Station.StationResponse;
import com.salesianos.triana.appbike.dto.Station.AddStationDto;

import java.util.List;
import java.util.UUID;

@Controller
@RestController
@RequiredArgsConstructor
@Tag(name = "Station", description = "CRUD for managing stations in the API.")
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
    @PostMapping("/admin/add")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "addEstacion", description = "Create a new Station")
    public ResponseEntity<StationResponse> addStation(@Valid @RequestBody AddStationDto estacion) {
        Estacion e = estacionService.newStation(estacion);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(StationResponse.of(e));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Gets all stations", content = {
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
            @ApiResponse(responseCode = "400", description = "Unable to find any stations", content = @Content)

    }

    )
    @Operation(summary = "findAll", description = "Find All Stations in the database")
    @GetMapping("/station/get")
    public ResponseEntity<List<GetStationDto>> findAllStations() {

        List<GetStationDto> all = estacionService.findAll();

        if (all.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(all);
    }

    @Operation(summary = "Obtains a list of stations paged for admin")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All stations have been found.", content = {
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
            @ApiResponse(responseCode = "404", description = "Not found any stations", content = @Content),
    })
    @GetMapping("/admin/station")
    public Page<StationResponse> findAllPageable(@PageableDefault(page = 0, size = 20) Pageable page) {
        Page<Estacion> pagedResult = estacionService.searchPage(page);
        return pagedResult.map(StationResponse::of);
    }

    @Operation(summary = "Gets specific station of the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The station has been found", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = GetBicicletaDTO.class)), examples = {
                            @ExampleObject(value = """
                                    {
                                         "id": "3a35f7a1-95f7-4fc8-b2f4-6d5a147a7c8e",
                                         "number": 5,
                                         "name": "Parque Principes",
                                         "coordinates": "37.374405948794475, -6.005344980963008",
                                         "capacity": 4,
                                         "bikes": 4
                                     }
                                                         """) }) }),
            @ApiResponse(responseCode = "404", description = "Unable to find station with that id. Try it again", content = @Content),
    })
    @GetMapping("/station/get/{id}")
    public StationResponse findStationById(@PathVariable UUID id) {
        return StationResponse.of(estacionService.findById(id));
    }

    @Operation(summary = "Edit a Station")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The station has been edited", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = GetBicicletaDTO.class)), examples = {
                            @ExampleObject(value = """
                                    {
                                          "numero": 6,
                                          "name": "Huevete",
                                          "coordinates": "40.7128,-74.0060",
                                          "capacity": 1,
                                          "bikes": [
                                              {
                                                  "uuid": null,
                                                  "nombre": "",
                                                  "marca": "",
                                                  "modelo": "",
                                                  "estado": "",
                                                  "usos": 0,
                                                  "estacion": ""
                                              }
                                          ]
                                      }
                                                         """) }) }),
            @ApiResponse(responseCode = "404", description = "Any Station was found", content = @Content),
    })
    @PutMapping("/admin/edit/{id}")
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
    @DeleteMapping("/admin/delete/{id}")
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
