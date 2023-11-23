package com.salesianos.triana.appbike.controller;

import com.salesianos.triana.appbike.dto.AddUso;
import com.salesianos.triana.appbike.dto.Bike.GetBicicletaDTO;
import com.salesianos.triana.appbike.dto.UsoBeginResponse;
import com.salesianos.triana.appbike.model.Bicicleta;
import com.salesianos.triana.appbike.model.Uso;
import com.salesianos.triana.appbike.model.Usuario;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bikes")
@Tag(name = "Bikes", description = "Controller who manages all requests regarding bikes")
public class BicicletaController {

        private final BicicletaService bicicletaService;
        private final UsoService usoService;

        @Operation(summary = "Obtains a list of bikes")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "All bikes have been found.", content = {
                                        @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Bicicleta.class)), examples = {
                                                        @ExampleObject(value = """
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
                                                                        """) }) }),
                        @ApiResponse(responseCode = "404", description = "Not found any bike", content = @Content),
        })

        @GetMapping("/")
        public List<GetBicicletaDTO> findAll() {
                return bicicletaService.findAll();
        }

        @GetMapping("/station/{idEstacion}/bikes")
        public List<GetBicicletaDTO> findAllByStation(@PathVariable UUID idEstacion) {
                List<Bicicleta> data = bicicletaService.findAllByStation(idEstacion);

                return data.stream()
                                .map(GetBicicletaDTO::of)
                                .toList();
        }


        @GetMapping("/{uuid}")
        public GetBicicletaDTO findBikeById(@PathVariable UUID uuid){
                return GetBicicletaDTO.of(bicicletaService.findById(uuid).get());
        }

        @GetMapping("/byname/{name}")
        public GetBicicletaDTO findBikeByName(@PathVariable String name){
                return GetBicicletaDTO.of(bicicletaService.findByName(name));
        }

        @PostMapping("/rent/{idBicicleta}")
        public ResponseEntity<UsoBeginResponse> rentABike(@PathVariable UUID idBicicleta, @AuthenticationPrincipal Usuario user) {

                Uso newUso = usoService.addUso(idBicicleta, user);

                if (newUso == null) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
                }

                URI createdURI = ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(newUso.getId()).toUri();

                return ResponseEntity
                        .created(createdURI)
                        .body(UsoBeginResponse.of(newUso));
        }
}
