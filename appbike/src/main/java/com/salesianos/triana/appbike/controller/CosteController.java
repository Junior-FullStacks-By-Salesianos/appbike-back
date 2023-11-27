package com.salesianos.triana.appbike.controller;

import com.salesianos.triana.appbike.dto.Coste.CosteResponse;
import com.salesianos.triana.appbike.dto.Uso.UsoResponse;
import com.salesianos.triana.appbike.service.CosteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cost")
@Tag(name = "Costs", description = "Controller who manages all requests regarding costs")
public class CosteController {

    private final CosteService costeService;

    @Operation(summary = "Gets the current cost per minute")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The cost has been found", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = CosteResponse.class)), examples = {
                            @ExampleObject(value = """
                                    {
                                        "id": 1,
                                        "precioMinuto": 0.2,
                                        "fechaInicio": "2023-11-27T08:32:30.296463",
                                        "fechaFin": null
                                    }
   
                                                                        """) }) }),
            @ApiResponse(responseCode = "404", description = "Not found any cost", content = @Content),
    })
    @GetMapping("/current")
    public CosteResponse getCurrentCost(){
        return CosteResponse.of(costeService.findCurrentCost());
    }

}
