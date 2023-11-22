package com.salesianos.triana.appbike.revision;

import com.salesianos.triana.appbike.revision.dto.RevisionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RevisionController {

    private final RevisionService revisionService;

    @GetMapping("/admin/issues")
    public ResponseEntity<List<RevisionDTO>> getAll(){
        if(revisionService.findAll().isEmpty())
            return ResponseEntity.noContent().build();

        List<RevisionDTO> allRevisionDTO = revisionService.findAll().stream()
                .map(RevisionDTO::of)
                .toList();

        return ResponseEntity.ok(allRevisionDTO);
    }
}
