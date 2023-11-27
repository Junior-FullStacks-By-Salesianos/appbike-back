package com.salesianos.triana.appbike.controller;

import com.salesianos.triana.appbike.dto.UsuarioBici.EditSaldo;

import com.salesianos.triana.appbike.dto.UsuarioBici.UsuarioBiciResponse;
import com.salesianos.triana.appbike.model.UsuarioBici;
import com.salesianos.triana.appbike.service.UsuarioBiciService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Tag(name = "Bike User", description = "Controller who manages all requests regarding bike users")
public class UsuarioBiciController {

    private final UsuarioBiciService usuarioBiciService;

    @GetMapping("/details")
    public UsuarioBiciResponse getUserDetails(@AuthenticationPrincipal UsuarioBici user){
        return UsuarioBiciResponse.of(usuarioBiciService.getDetails(user));
    }

    //@PreAuthorize("hasRole('USER')")
    @PutMapping("/recharge")
    public UsuarioBiciResponse rechargeBalance(@Valid @RequestBody EditSaldo recharge, @AuthenticationPrincipal UsuarioBici user){
        return UsuarioBiciResponse.of(usuarioBiciService.rechargeBalanceByUser(recharge, user));
    }

}
