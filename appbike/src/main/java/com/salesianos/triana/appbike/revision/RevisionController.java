package com.salesianos.triana.appbike.revision;

import com.salesianos.triana.appbike.revision.dto.EditRevisionDTO;
import com.salesianos.triana.appbike.revision.dto.RevisionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/issue")
public class RevisionController {

    private final RevisionService revisionService;

    @GetMapping("/")
    public ResponseEntity<List<RevisionDTO>> getAll(){
        List<RevisionDTO> allRevisionDTO = revisionService.findAll().stream()
                .map(RevisionDTO::of)
                .toList();
        return ResponseEntity.ok(allRevisionDTO);
    }

    @PutMapping("/{id}")
    public EditRevisionDTO editRevision(@PathVariable Long id, @RequestBody EditRevisionDTO r){
        return revisionService.edit(id,r);
    }
}
