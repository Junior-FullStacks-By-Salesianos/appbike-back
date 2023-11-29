package com.salesianos.triana.appbike.controller;

import com.salesianos.triana.appbike.dto.Revision.RevisionDTO;
import com.salesianos.triana.appbike.service.TrabajadorService;
import com.salesianos.triana.appbike.dto.Trabajador.TrabajadorDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/worker")
public class TrabajadorController {

    private final TrabajadorService trabajadorService;

    //Si da tiempo se hace
//    @PostMapping("/admin/worker")
//    public ResponseEntity<Trabajador> applyWorkerStatus(Usuario u){
//
//    }

    @Operation(summary = "Obstains all workers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Returns a list of all workers",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = RevisionDTO.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                 "content": [
                                                  {
                                                      "id": "c62db400-22e3-4e92-94db-1447f5688f2c",
                                                      "email": "admin@admin.com",
                                                      "nombre": "admin"
                                                  }
                                              ]
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "Unable to find any workers",
                    content = @Content),
    })
    @GetMapping("/")
    public ResponseEntity<List<TrabajadorDTO>> getAll(){
        if(trabajadorService.findAll().isEmpty())
            return ResponseEntity.noContent().build();

        List<TrabajadorDTO> allTrabajadores = trabajadorService.findAll().stream()
                .map((TrabajadorDTO::of))
                .toList();

        return ResponseEntity.ok(allTrabajadores);
    }
}
