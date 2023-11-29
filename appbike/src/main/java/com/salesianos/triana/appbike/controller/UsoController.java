package com.salesianos.triana.appbike.controller;

import com.salesianos.triana.appbike.MyPage;
import com.salesianos.triana.appbike.dto.Bike.GetBicicletaDTO;
import com.salesianos.triana.appbike.dto.Uso.EditUso;
import com.salesianos.triana.appbike.dto.Uso.UsoBeginResponse;
import com.salesianos.triana.appbike.dto.Uso.UsoResponse;
import com.salesianos.triana.appbike.model.Uso;
import com.salesianos.triana.appbike.model.Usuario;
import com.salesianos.triana.appbike.model.UsuarioBici;
import com.salesianos.triana.appbike.service.UsoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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
@Tag(name = "Uses", description = "Controller who manages all requests regarding uses")
public class UsoController {

    private final UsoService usoService;

    @Operation(summary = "Method to rent a bike")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "The use has been created", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = UsoBeginResponse.class)), examples = {
                            @ExampleObject(value = """
                                        {
                                            "id": 1,
                                            "fechaInicio": "2023-11-25T19:52:45.5159449",
                                            "bicicleta": "Michael",
                                            "usuario": "933913fc-187e-412e-a77d-54b5e6b0888f"
                                        }
                                                                        """) }) }),
            @ApiResponse(responseCode = "404", description = "Not found any bike", content = @Content),
            @ApiResponse(responseCode = "400", description = "The bike is already in use", content = @Content),
            @ApiResponse(responseCode = "400", description = "The user has already a bike in use", content = @Content)
    })
    @PostMapping("/bikes/rent/{idBicicleta}")
    public ResponseEntity<UsoResponse> rentABike(@PathVariable UUID idBicicleta, @AuthenticationPrincipal UsuarioBici user) {
        Uso newUso = usoService.addUso(idBicicleta, user);

        URI createdURI = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newUso.getUuid()).toUri();

        return ResponseEntity
                .created(createdURI)
                .body(UsoResponse.of(newUso));
    }

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
    @GetMapping("/use/last-use")
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
    @GetMapping("/use/active")
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
    @PostMapping("/use/finish/{idEstacion}")
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

    @Operation(summary = "Gets a pageable list of the uses (trips) of a single user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The list has been found", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = UsoResponse.class)), examples = {
                            @ExampleObject(value = """
                                    {
                                        "content": [
                                            {
                                                "id": "57c4051c-cfa8-4175-a8a5-67b7a3dbe14c",
                                                "fechaInicio": "2023-11-27T17:00:00",
                                                "fechaFin": "2023-11-27T17:30:00",
                                                "coste": 5.0,
                                                "bicicleta": "Braulio",
                                                "estacionFin": "Puerta Jerez",
                                                "usuario": "04d0595e-45d5-4f63-8b53-1d79e9d84a5d"
                                            }
                                        ],
                                        "pageable": {
                                            "pageNumber": 0,
                                            "pageSize": 10,
                                            "sort": {
                                                "empty": true,
                                                "unsorted": true,
                                                "sorted": false
                                            },
                                            "offset": 0,
                                            "unpaged": false,
                                            "paged": true
                                        },
                                        "totalElements": 1,
                                        "totalPages": 1,
                                        "last": true,
                                        "size": 10,
                                        "number": 0,
                                        "sort": {
                                            "empty": true,
                                            "unsorted": true,
                                            "sorted": false
                                        },
                                        "numberOfElements": 1,
                                        "first": true,
                                        "empty": false
                                    }
                                                                        """) }) }),
            @ApiResponse(responseCode = "404", description = "Not found any uses for this user", content = @Content),
    })
    @GetMapping("use/{userId}")
    public Page<UsoResponse> getUsesByUser(@PathVariable UUID userId, @PageableDefault(page = 0, size = 10) Pageable pageable){
        return usoService.findUsoByUser(userId, pageable);
    }
    @Operation(summary = "Gets a pageable list of all uses")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The list has been found", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = UsoResponse.class)), examples = {
                            @ExampleObject(value = """
                                    {
                                        "content": [
                                            {
                                                "id": "57c4051c-cfa8-4175-a8a5-67b7a3dbe14c",
                                                "fechaInicio": "2023-11-27T17:00:00",
                                                "fechaFin": "2023-11-27T17:30:00",
                                                "coste": 5.0,
                                                "bicicleta": "Braulio",
                                                "estacionFin": "EstaciÃ³n 2",
                                                "usuario": "user1"
                                            },
                                            {
                                                "id": "cf30627d-8696-4106-8c83-793f9da6e3f8",
                                                "fechaInicio": "2023-11-27T17:00:00",
                                                "fechaFin": "2023-11-27T17:30:00",
                                                "coste": 5.0,
                                                "bicicleta": "JesÃºs",
                                                "estacionFin": "EstaciÃ³n 6",
                                                "usuario": "user2"
                                            },
                                            {
                                                "id": "9365d1a9-7997-4a52-a3c9-4e9e3d36eca2",
                                                "fechaInicio": "2023-11-27T17:00:00",
                                                "fechaFin": "2023-11-27T17:30:00",
                                                "coste": 5.0,
                                                "bicicleta": "Bloste",
                                                "estacionFin": "EstaciÃ³n 6",
                                                "usuario": "user3"
                                            },
                                            {
                                                "id": "0024870f-9459-4ff6-8fd9-fdaa9e1c174b",
                                                "fechaInicio": "2023-11-28T21:45:56.322218",
                                                "fechaFin": null,
                                                "coste": 0.0,
                                                "bicicleta": "Pacote",
                                                "estacionFin": "Unfinished Use",
                                                "usuario": "user1"
                                            }
                                        ],
                                        "size": 20,
                                        "totalElements": 4,
                                        "pageNumber": 0,
                                        "first": true,
                                        "last": true
                                    }
                                                                        """) }) }),
            @ApiResponse(responseCode = "404", description = "Not found any use", content = @Content),
    })
    @GetMapping("/admin/travels")
    public MyPage<UsoResponse> findAll(@PageableDefault(size = 20, page = 0) Pageable pageable){
        return usoService.findAllUses(pageable);
    }

    @Operation(summary = "Edits the cost from an use")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The use has been edited successfully", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = UsoResponse.class)), examples = {
                            @ExampleObject(value = """
                                    {
                                          "id": "9365d1a9-7997-4a52-a3c9-4e9e3d36eca2",
                                          "fechaInicio": "2023-11-27T17:00:00",
                                          "fechaFin": "2023-11-27T17:30:00",
                                          "coste": 9.23,
                                          "bicicleta": "Bloste",
                                          "estacionFin": "EstaciÃ³n 6",
                                          "usuario": "5cf8b808-3b6e-4d9d-90d5-65c83b0e75b2"
                                      }
                                                                        """) }) }),
            @ApiResponse(responseCode = "404", description = "Not found any use", content = @Content),
            @ApiResponse(responseCode = "400", description = "The trip you have choosed is taking place", content = @Content)
    })
    @PutMapping("/admin/edit-use/{id}")
    public UsoResponse editUse(@PathVariable UUID id, @Valid @RequestBody EditUso editUso){
        return UsoResponse.of(usoService.editUse(id, editUso));
    }

}
