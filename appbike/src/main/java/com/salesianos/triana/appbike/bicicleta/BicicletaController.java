package com.salesianos.triana.appbike.bicicleta;


import com.salesianos.triana.appbike.bicicleta.dto.GetBicicletaDTO;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bikes")
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

    @GetMapping("/")
    public List<GetBicicletaDTO> findAll() {
        return bicicletaService.findAll();
    }

    @GetMapping("/station/{idEstacion}/bikes")
    public List<GetBicicletaDTO> findAllByStation(@PathVariable UUID idEstacion) {
        List<Bicicleta> data=bicicletaService.findAllByStation(idEstacion);

        return data.stream()
                .map(GetBicicletaDTO::of)
                .toList();
    }
}
