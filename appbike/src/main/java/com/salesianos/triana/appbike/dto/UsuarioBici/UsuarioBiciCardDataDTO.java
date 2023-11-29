package com.salesianos.triana.appbike.dto.UsuarioBici;

import com.salesianos.triana.appbike.validation.annotation.PinMatch;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@PinMatch(pinField = "pin", pinCormirmationField = "pinConfirmation", message = "{UsuarioBiciCardDataDTO.pin.nomatch}")
public record UsuarioBiciCardDataDTO (@NotBlank(message = "{UsuarioBiciCardDataDTO.numTarjeta.notblank}")
                                      String numTarjeta,
                                      @NotBlank(message = "{UsuarioBiciCardDataDTO.pin.notblank}")
                                      String pin,
                                      @NotBlank(message = "{UsuarioBiciCardDataDTO.pinConfirmation.notblank}")
                                      String pinConfirmation){
}
