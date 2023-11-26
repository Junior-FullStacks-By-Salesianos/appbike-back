package com.salesianos.triana.appbike.controller;

import com.salesianos.triana.appbike.dto.station.AddStationDto;
import com.salesianos.triana.appbike.dto.station.GetStationDto;
import com.salesianos.triana.appbike.dto.station.StationResponse;
import com.salesianos.triana.appbike.model.Estacion;
import com.salesianos.triana.appbike.service.EstacionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RestController
@RequestMapping("stations")
@RequiredArgsConstructor
@Tag(name="Station",description = "CRUD for managing stations in the API..")
public class StationController {

    private final EstacionService estacionService;
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201",description = "Creation of new Stations",content =
                            {@Content(mediaType = "application/json",examples = {@ExampleObject(
                                    value = """
                                            {
                                                 "id": "f1b6d478-d973-435b-bab6-42b0771e2278",
                                                 "name": "Plaza de Cuba",
                                                 "coordinates": "12.345,-0.12345",
                                                 "capacity": 29,
                                                 "bikes": null
                                             }
                                            """
                            )})}),
                    @ApiResponse(responseCode = "400",
                            description = "The creation of the station has not been done",
                            content = @Content)

            }

    )
    @PostMapping("/add")
    @Operation(summary = "addEstacion",description = "Create a new Station")
    public ResponseEntity<StationResponse>addStation(@Valid @RequestBody AddStationDto estacion){
        Estacion e = estacionService.newStation(estacion);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(StationResponse.of(e));
    }

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201",description = "Get all stations",content =
                            {@Content(mediaType = "application/json",examples = {@ExampleObject(
                                    value = """
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
                                            """
                            )})}),
                    @ApiResponse(responseCode = "400",
                            description = "An error appears with the list of the stations",
                            content = @Content)

            }

    )
    @Operation(summary = "findAll",description = "Find All Stations in the database")
    @GetMapping("/get")
    public ResponseEntity<List<GetStationDto>>findAllStations() {

        List<GetStationDto> all = estacionService.findAll();

        if (all.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(all);
    }

    @ApiResponses(
            @ApiResponse(
                    responseCode = "204 ",description = "Bike from station delete correctly"
            )
    )
    @Operation(summary = "deleteBike",description = "Delete a bike from a station checking that exits previously")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBikeFromStation(@PathVariable Long id) {
        /*bicicletaServicio.delete(id);*/
        return ResponseEntity.noContent().build();
    }

    @ApiResponses(
            @ApiResponse(
                    responseCode = "204 ",description = "Station delete"
            )
    )
    @Operation(summary = "deleteBike",description = "Delete a Station checking that the station is in the database saved")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        estacionService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
