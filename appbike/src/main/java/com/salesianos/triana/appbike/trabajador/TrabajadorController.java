package com.salesianos.triana.appbike.trabajador;

import com.salesianos.triana.appbike.trabajador.dto.TrabajadorDTO;
import com.salesianos.triana.appbike.usuario.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TrabajadorController {

    private final TrabajadorService trabajadorService;

    //Si da tiempo se hace
//    @PostMapping("/admin/worker")
//    public ResponseEntity<Trabajador> applyWorkerStatus(Usuario u){
//
//    }

    @GetMapping("/admin/issues") //se cambiará a /admin/workes si se acaba haciendo esa página
    public ResponseEntity<List<TrabajadorDTO>> getAll(){
        if(trabajadorService.findAll().isEmpty())
            return ResponseEntity.noContent().build();

        List<TrabajadorDTO> allTrabajadores = trabajadorService.findAll().stream()
                .map((TrabajadorDTO::of))
                .toList();

        return ResponseEntity.ok(allTrabajadores);
    }
}
