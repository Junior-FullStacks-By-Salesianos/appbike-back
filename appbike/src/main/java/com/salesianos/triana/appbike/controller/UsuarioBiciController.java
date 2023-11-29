package com.salesianos.triana.appbike.controller;

import com.salesianos.triana.appbike.dto.Uso.UsoResponse;
import com.salesianos.triana.appbike.dto.UsuarioBici.EditSaldo;

import com.salesianos.triana.appbike.dto.UsuarioBici.UsuarioBiciDTO;
import com.salesianos.triana.appbike.dto.UsuarioBici.UsuarioBiciResponse;
import com.salesianos.triana.appbike.model.UsuarioBici;
import com.salesianos.triana.appbike.service.UsuarioBiciService;
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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Tag(name = "Bike User", description = "Controller who manages all requests regarding bike users")
public class UsuarioBiciController {

    private final UsuarioBiciService usuarioBiciService;

    @Operation(summary = "Method to get the User details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The user list charged successfully", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = UsuarioBiciResponse.class)), examples = {
                            @ExampleObject(value = """
                                    {
                                        "id": "04d0595e-45d5-4f63-8b53-1d79e9d84a5d",
                                        "username": "user1",
                                        "nombre": "User 1",
                                        "saldo": 20.0
                                    }
                                                                        """) }) }),
            @ApiResponse(responseCode = "404", description = "Not found any user", content = @Content)
    })
    @GetMapping("/details")
    public UsuarioBiciResponse getUserDetailsAlex(@AuthenticationPrincipal UsuarioBici user){
        return UsuarioBiciResponse.of(usuarioBiciService.getDetails(user));
    }

    @Operation(summary = "Method to get the User details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The user list charged successfully", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = UsuarioBiciResponse.class)), examples = {
                            @ExampleObject(value = """
                                    {
                                        "id": "04d0595e-45d5-4f63-8b53-1d79e9d84a5d",
                                        "username": "user1",
                                        "nombre": "User 1",
                                        "saldo": 20.0
                                    }
                                                                        """)})}),
            @ApiResponse(responseCode = "404", description = "Not found any user", content = @Content)
    })
    @GetMapping("/{id}")
    public UsuarioBiciDTO getUserDetails(@PathVariable UUID id) {
        return UsuarioBiciDTO.of(usuarioBiciService.findById(id));
    }

    @Operation(summary = "Method to recharge the User balance")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The user list charged successfully", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = UsuarioBiciResponse.class)), examples = {
                            @ExampleObject(value = """
                                    {
                                        "id": "04d0595e-45d5-4f63-8b53-1d79e9d84a5d",
                                        "username": "user1",
                                        "nombre": "User 1",
                                        "saldo": 20.0
                                    }
                                                                        """)})}),
            @ApiResponse(responseCode = "400", description = "The user pin does not match", content = @Content)
    })
    //@PreAuthorize("hasRole('USER')")
    @PutMapping("/recharge")
    public UsuarioBiciResponse rechargeBalance(@Valid @RequestBody EditSaldo recharge, @AuthenticationPrincipal UsuarioBici user) {
        return UsuarioBiciResponse.of(usuarioBiciService.rechargeBalanceByUser(recharge, user));
    }

    @Operation(summary = "Method to set a user's card")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The user list charged successfully", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = UsuarioBiciResponse.class)), examples = {
                            @ExampleObject(value = """
                                    {
                                         "id": "04d0595e-45d5-4f63-8b53-1d79e9d84a5d",
                                         "createdAt": "2023-11-29",
                                         "email": "user1@user.com",
                                         "nombre": "User 1",
                                         "username": "user1",
                                         "numTarjeta": "1111-1111-1111-1111",
                                         "pin": "1111",
                                         "saldo": 0.0
                                     }
                                                                        """)})}),
            @ApiResponse(responseCode = "400", description = "Error setting card. Check credit card number and pin.", content = @Content)
    })
    @PutMapping("/setCard/{id}")
    public UsuarioBiciDTO setCard(@PathVariable UUID id, @RequestBody @Valid UsuarioBiciDTO u){
        return UsuarioBiciDTO.of(usuarioBiciService.setCard(id,u));
    }
}


