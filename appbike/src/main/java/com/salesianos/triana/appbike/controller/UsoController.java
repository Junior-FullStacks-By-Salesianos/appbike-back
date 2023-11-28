package com.salesianos.triana.appbike.controller;

import com.salesianos.triana.appbike.dto.Bike.GetBicicletaDTO;
import com.salesianos.triana.appbike.dto.Uso.UsoBeginResponse;
import com.salesianos.triana.appbike.dto.Uso.UsoResponse;
import com.salesianos.triana.appbike.model.Uso;
import com.salesianos.triana.appbike.model.Usuario;
import com.salesianos.triana.appbike.service.UsoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/use")
@Tag(name = "Uses", description = "Controller who manages all requests regarding uses")
public class UsoController {

    private final UsoService usoService;

    @Operation(summary = "Gets finished use")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The use has been found", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = UsoResponse.class)), examples = {
                            @ExampleObject(value = """
                                    {
                                        "id": 1,
                                        "fechaInicio": "2023-11-26T10:31:40.804104",
                                        "fechaFin": null,
                                        "coste": 0.0,
                                        "bicicleta": "Pacote",
                                        "estacionFin": "Plaza de España",
                                        "usuario": "5b08c955-1463-43d5-8326-8b511663e848"
                                    }
   
                                                                        """) }) }),
            @ApiResponse(responseCode = "404", description = "Not found any use", content = @Content),
            @ApiResponse(responseCode = "400", description = "The user has already a bike in use", content = @Content),
    })
    @GetMapping("/last-use")
    public UsoResponse findLastUse (@AuthenticationPrincipal Usuario user){
        return UsoResponse.of(usoService.findByLastUse(user));
    }

    @Operation(summary = "Gets an active use by an user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The use has been found", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = UsoResponse.class)), examples = {
                            @ExampleObject(value = """
                                    {
                                        "id": 1,
                                        "fechaInicio": "2023-11-26T10:31:40.804104",
                                        "fechaFin": null,
                                        "coste": 0.0,
                                        "bicicleta": "Pacote",
                                        "estacionFin": "El viaje aun no tiene estación de fin",
                                        "usuario": "5b08c955-1463-43d5-8326-8b511663e848"
                                    }
                                                                        """) }) }),
            @ApiResponse(responseCode = "404", description = "Not found any active use", content = @Content),
    })
    @GetMapping("/active")
    public UsoResponse findActiveUse (@AuthenticationPrincipal Usuario usuario){
        return UsoResponse.of(usoService.findActiveUseByUser(usuario.getId()));
    }


    @Operation(summary = "Method to finish the use")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "The use has been finished", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = UsoResponse.class)), examples = {
                            @ExampleObject(value = """
                                    {
                                        "id": 1,
                                        "fechaInicio": "2023-11-27T12:12:25.658844",
                                        "fechaFin": "2023-11-27T12:12:47.1572202",
                                        "coste": 0.0,
                                        "bicicleta": "Michael",
                                        "estacionFin": "Plaza de Armas",
                                        "usuario": "4fa002e5-95d3-43b9-a624-7afad5f1b5ab"
                                    }
                                                                        """) }) }),
            @ApiResponse(responseCode = "404", description = "Not found any station", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found any use", content = @Content)
    })
    @PostMapping("/finish/{idEstacion}")
    public ResponseEntity<UsoResponse> finishUse(@AuthenticationPrincipal Usuario usuario, @PathVariable UUID idEstacion){
        Uso newUso = usoService.finishActiveUseByUser(usuario.getId(), idEstacion);

        URI createdURI = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newUso.getUuid()).toUri();

        return ResponseEntity
                .created(createdURI)
                .body(UsoResponse.of(newUso));
    }

    @GetMapping("/{userId}")
    public Page<UsoResponse> getUsesByUser(@PathVariable UUID userId, @PageableDefault(page = 0, size = 10) Pageable pageable){
        return usoService.findUsoByUser(userId, pageable);
    }
}
