package com.salesianos.triana.appbike.controller;

import com.salesianos.triana.appbike.dto.Bike.GetBicicletaDTO;
import com.salesianos.triana.appbike.dto.Bike.PostBicicletaDTO;
import com.salesianos.triana.appbike.dto.Uso.UsoBeginResponse;
import com.salesianos.triana.appbike.dto.Uso.UsoResponse;
import com.salesianos.triana.appbike.model.Bicicleta;
import com.salesianos.triana.appbike.model.Uso;
import com.salesianos.triana.appbike.model.Usuario;
import com.salesianos.triana.appbike.model.UsuarioBici;
import com.salesianos.triana.appbike.service.BicicletaService;
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
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Tag(name = "Bikes", description = "Controller who manages all requests regarding bikes")
public class BicicletaController {

        private final BicicletaService bicicletaService;

    @Operation(summary = "Obtains a list of bikes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "All bikes have been found.",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Bicicleta.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                {"nombre": "Rogelio", "marca":
                                                "Asis", "modelo": "michaeltooper",
                                                "estado":"new", "usos": 3,
                                                "estacion": "Plaza de armas"},
                        
                                                
                                                {"nombre": "Hermenegildo", "marca":
                                                "Asis", "modelo": "michaeltooper",
                                                "estado":"worn_out", "usos": 7,
                                                "estacion": "Setas de Sevilla"},
                                                
                                                {"nombre": "Hugo", "marca":
                                                "Pole", "modelo": "chimneychains",
                                                "estado":"need_to_be_replaced", "usos": 18,
                                                "estacion": "Alameda de Hercules"}
                                            ]                                          
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "Not found any bike",
                    content = @Content),
    })
    @GetMapping("/paged")
    public Page<GetBicicletaDTO> findAllPageable(@PageableDefault(page = 0, size = 20) Pageable page) {
        Page<Bicicleta> pagedResult = bicicletaService.searchPage(page);
        return pagedResult.map(GetBicicletaDTO::of);
    }

    @Operation(summary = "Obtains a list of bikes without pageable")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "All bikes have been found.",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Bicicleta.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                {"nombre": "Rogelio", "marca":
                                                "Asis", "modelo": "michaeltooper",
                                                "estado":"new", "usos": 3,
                                                "estacion": "Plaza de armas"},
                        
                                                
                                                {"nombre": "Hermenegildo", "marca":
                                                "Asis", "modelo": "michaeltooper",
                                                "estado":"worn_out", "usos": 7,
                                                "estacion": "Setas de Sevilla"},
                                                
                                                {"nombre": "Hugo", "marca":
                                                "Pole", "modelo": "chimneychains",
                                                "estado":"need_to_be_replaced", "usos": 18,
                                                "estacion": "Alameda de Hercules"}
                                            ]                                          
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "Not found any bike",
                    content = @Content),
    })
    @GetMapping("/")
    public List<GetBicicletaDTO> findAll() {
        return bicicletaService.findAll();
    }


    @Operation(summary = "Obtains a list of bikes of a station")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "All bikes have been found in that station.",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetBicicletaDTO.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                {"nombre": "Rogelio", "marca":
                                                "Asis", "modelo": "michaeltooper",
                                                "estado":"new", "usos": 3,
                                                "estacion": "Plaza de armas"},
                        
                                                
                                                {"nombre": "Hermenegildo", "marca":
                                                "Asis", "modelo": "michaeltooper",
                                                "estado":"worn_out", "usos": 7,
                                                "estacion": "Plaza de armas"},
                                                
                                                {"nombre": "Hugo", "marca":
                                                "Pole", "modelo": "chimneychains",
                                                "estado":"need_to_be_replaced", "usos": 18,
                                                "estacion": "Plaza de armas"}
                                            ]                                          
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "Not found the station",
                    content = @Content),
    })
    @GetMapping("/bikes/station/{idEstacion}/bikes")
    public List<GetBicicletaDTO> findAllByStation(@PathVariable UUID idEstacion) {
        return bicicletaService.findAllByStation(idEstacion).stream().map(GetBicicletaDTO::of).toList();
    }
       

        @Operation(summary = "Gets a bicycle from its id")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "The bike has been found", content = {
                        @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = GetBicicletaDTO.class)), examples = {
                                @ExampleObject(value = """
                                        {
                                            "uuid": "1c90f4dc-d57f-4398-a33d-f66c42a95f53",
                                            "nombre": "Pacote",
                                            "marca": "FieldCletas",
                                            "modelo": "Gen15",
                                            "estado": "NEEDS_TO_BE_REPLACED",
                                            "usos": 0,
                                            "estacion": "Plaza de Armas"
                                        }
                                                                        """) }) }),
                @ApiResponse(responseCode = "404", description = "Not found any bike", content = @Content),
        })
        @GetMapping("/bikes/{uuid}")
        public GetBicicletaDTO findBikeById(@PathVariable UUID uuid){
                return GetBicicletaDTO.of(bicicletaService.findById(uuid));
        }


        @Operation(summary = "Gets a bicycle from its name")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "The bike has been found", content = {
                        @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = GetBicicletaDTO.class)), examples = {
                                @ExampleObject(value = """
                                        {
                                            "uuid": "1c90f4dc-d57f-4398-a33d-f66c42a95f53",
                                            "nombre": "Pacote",
                                            "marca": "FieldCletas",
                                            "modelo": "Gen15",
                                            "estado": "NEEDS_TO_BE_REPLACED",
                                            "usos": 0,
                                            "estacion": "Plaza de Armas"
                                        }
                                                                        """)
                        })
                }),
                @ApiResponse(responseCode = "404", description = "Not found any bike", content = @Content),
        })
        @GetMapping("/bikes/byname/{name}")
        public GetBicicletaDTO findBikeByName(@PathVariable String name) {
            return GetBicicletaDTO.of(bicicletaService.findByName(name));
        }

        @Operation(summary = "Create a new bike")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "201", description = "The bike has been created",
                    content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = UsoBeginResponse.class)), examples = {
                            @ExampleObject(value = """
                                        {
                                            "uuid": "1c90f4dc-d57f-4398-a33d-f66c42a95f21",
                                            "nombre": "Luis",
                                            "marca": "FieldCletas",
                                            "modelo": "Gen15",
                                            "estado": "NEEDS_TO_BE_REPLACED",
                                            "usos": 0,
                                            "estacion": "Plaza de Armas"
                                        }
                                                                        """)
                    })
                }),
            @ApiResponse(responseCode = "400", description = "Bad request from the user", content = @Content),
        })
        @PostMapping("/add")
        public ResponseEntity<GetBicicletaDTO> addABike(@Valid @RequestBody PostBicicletaDTO bike){
            Bicicleta b = bicicletaService.saveDTO(bike);

            URI createdURI = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .buildAndExpand(b.getUuid()).toUri();

            return ResponseEntity.created(createdURI).body(GetBicicletaDTO.of(b));
        }
}
