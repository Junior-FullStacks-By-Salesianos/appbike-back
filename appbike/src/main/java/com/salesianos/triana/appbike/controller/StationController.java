package com.salesianos.triana.appbike.controller;

import com.salesianos.triana.appbike.dto.station.AddStationDto;
import com.salesianos.triana.appbike.dto.station.StationResponse;
import com.salesianos.triana.appbike.model.Estacion;
import com.salesianos.triana.appbike.service.EstacionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
                                 
                                        """
                            )})}),
                    @ApiResponse(responseCode = "400",
                            description = "The creation of the station has not been done",
                            content = @Content)

            }

    )
    @PostMapping("/add")
    @Operation(summary = "addEstacion",description = "Create a new Station")
    public ResponseEntity<StationResponse>addStation(
            @RequestBody AddStationDto estacion
    ){
        Estacion e = estacionService.newStation(estacion);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(StationResponse.of(e));
    }


}
