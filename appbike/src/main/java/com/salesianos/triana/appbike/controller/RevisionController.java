package com.salesianos.triana.appbike.controller;

import com.salesianos.triana.appbike.dto.Revision.NewRevisionDTO;
import com.salesianos.triana.appbike.model.Revision;
import com.salesianos.triana.appbike.repository.EstacionRepository;
import com.salesianos.triana.appbike.service.RevisionService;
import com.salesianos.triana.appbike.dto.Revision.RevisionDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("admin/issues")
public class RevisionController {

    private final RevisionService revisionService;
    private final EstacionRepository estacionRepository;

    @Operation(summary = "Obstains a new issues page")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Returned a pageable list of issues",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = RevisionDTO.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                 "content": [
                                                     {
                                                         "id": 996,
                                                         "fechaProgramada": "2023-01-01",
                                                         "fechaRealizacion": "2022-12-31",
                                                         "anotaciones": "Anotaciones 1",
                                                         "nombreEstacion": "Parque Principes",
                                                         "nombreTrabajador": "admin",
                                                         "estado": "FINISHED"
                                                     },
                                                     {
                                                         "id": 997,
                                                         "fechaProgramada": "2023-02-01",
                                                         "fechaRealizacion": "2023-02-02",
                                                         "anotaciones": "Anotaciones 2",
                                                         "nombreEstacion": "Parque Principes",
                                                         "nombreTrabajador": "admin",
                                                         "estado": "FINISHED"
                                                     }
                                                 ],
                                                 "pageable": {
                                                     "pageNumber": 0,
                                                     "pageSize": 20,
                                                     "sort": {
                                                         "empty": true,
                                                         "unsorted": true,
                                                         "sorted": false
                                                     },
                                                     "offset": 0,
                                                     "unpaged": false,
                                                     "paged": true
                                                 },
                                                 "totalElements": 4,
                                                 "totalPages": 1,
                                                 "last": true,
                                                 "size": 20,
                                                 "number": 0,
                                                 "sort": {
                                                     "empty": true,
                                                     "unsorted": true,
                                                     "sorted": false
                                                 },
                                                 "numberOfElements": 4,
                                                 "first": true,
                                                 "empty": false
                                             }
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "Unable to find the specified issue",
                    content = @Content),
    })
    @GetMapping("/")
    public Page<RevisionDTO> getAll(@PageableDefault(page = 0, size = 20) Pageable pageable) {
        return revisionService.searchPage(pageable);
    }

    @Operation(summary = "Creates a new issue")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Created a new issue",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = RevisionDTO.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [{
                                                 "content": [
                                                     {
                                                         "id": 996,
                                                         "fechaProgramada": "2023-01-01",
                                                         "fechaRealizacion": "2022-12-31",
                                                         "anotaciones": "Anotaciones 1",
                                                         "nombreEstacion": "Parque Principes",
                                                         "nombreTrabajador": "admin",
                                                         "estado": "FINISHED"
                                                     },
                                                     {
                                                         "id": 997,
                                                         "fechaProgramada": "2023-02-01",
                                                         "fechaRealizacion": "2023-02-02",
                                                         "anotaciones": "Anotaciones 2",
                                                         "nombreEstacion": "Parque Principes",
                                                         "nombreTrabajador": "admin",
                                                         "estado": "FINISHED"
                                                     },
                                                     
                                                 ],
                                                 "pageable": {
                                                     "pageNumber": 0,
                                                     "pageSize": 20,
                                                     "sort": {
                                                         "empty": true,
                                                         "unsorted": true,
                                                         "sorted": false
                                                     },
                                                     "offset": 0,
                                                     "unpaged": false,
                                                     "paged": true
                                                 },
                                                 "totalElements": 4,
                                                 "totalPages": 1,
                                                 "last": true,
                                                 "size": 20,
                                                 "number": 0,
                                                 "sort": {
                                                     "empty": true,
                                                     "unsorted": true,
                                                     "sorted": false
                                                 },
                                                 "numberOfElements": 4,
                                                 "first": true,
                                                 "empty": false
                                             }
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "Unable to find the specified issue",
                    content = @Content),
    })
    @PostMapping("/")
    public ResponseEntity<Revision> createIssue(@RequestBody @Valid NewRevisionDTO newRevisionDTO) {
        Revision newRevision = revisionService.save(newRevisionDTO);

        URI createdURI = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newRevision.getId())
                .toUri();

        return ResponseEntity.created(createdURI).body(newRevision);

    }

    @Operation(summary = "Edits an issue")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Successfully edited an issue",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = RevisionDTO.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                            {
                                                "id": 1,
                                                "fechaProgramada": "2023-11-29",
                                                "fechaRealizacion": null,
                                                "anotaciones": "Esta revisión es una de prueba",
                                                "estacion": {
                                                    "id": "c7af6951-0967-407d-a6cd-20d79904ee57",
                                                    "number": 1,
                                                    "name": "Plaza de Armas",
                                                    "coordinates": "",
                                                    "capacity": 10,
                                                    "bikes": 6
                                                },
                                                "trabajador": {
                                                    "id": "c0a83801-8c0b-1d26-818c-0b6d330c0000",
                                                    "email": "admin@bikeapp.com",
                                                    "nombre": "admin"
                                                },
                                                "estado": "IN_PROGRESS"
                                            }
                                            ]
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "Unable to find the specified issue",
                    content = @Content),
    })
    @PutMapping("/{id}")
    public RevisionDTO editRevision(@PathVariable Long id, @RequestBody RevisionDTO r) {
        return revisionService.edit(id, r);
    }

    @Operation(summary = "Deletes an issue")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "An issue has been deleted",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Revision.class))
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "Unable to find the specified issue",
                    content = @Content),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRevision(@PathVariable Long id) {
        revisionService.delete(id);

        return ResponseEntity.noContent().build();
    }


}
