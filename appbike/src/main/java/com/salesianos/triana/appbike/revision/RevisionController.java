package com.salesianos.triana.appbike.revision;

import com.salesianos.triana.appbike.revision.dto.EditRevisionDTO;
import com.salesianos.triana.appbike.revision.dto.RevisionDTO;
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
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/issue")
public class RevisionController {

    private final RevisionService revisionService;

    @Operation(summary = "Obstains a new issues page")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Returned a pageable list of issues",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = RevisionDTO.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                {"id": 1, "nombre": "Iglesias"},
                                                {"id": 2, "nombre": "Románico"}
                                            ]
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
                                            [
                                                {"id": 1, "nombre": "Iglesias"},
                                                {"id": 2, "nombre": "Románico"}
                                            ]
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "Unable to find the specified issue",
                    content = @Content),
    })
    @PostMapping("/")
    public ResponseEntity<Revision> createIssue(@RequestBody EditRevisionDTO editRevisionDTO) {
        Revision newRevision = revisionService.save(editRevisionDTO);

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
                                                {"id": 1, "nombre": "Iglesias"},
                                                {"id": 2, "nombre": "Románico"}
                                            ]                                          
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "Unable to find the specified issue",
                    content = @Content),
    })
    @PutMapping("/{id}")
    public EditRevisionDTO editRevision(@PathVariable Long id, @RequestBody EditRevisionDTO r) {
        return revisionService.edit(id, r);
    }

    @Operation(summary = "Deletes an issue")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "An issue has been deleted",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = RevisionDTO.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                {"id": 1, "nombre": "Iglesias"},
                                                {"id": 2, "nombre": "Románico"}
                                            ]
                                            """
                            )}
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
