package com.salesianos.triana.appbike.dto.UsuarioBici;

import com.salesianos.triana.appbike.validation.annotation.FieldsValueMatch;
import com.salesianos.triana.appbike.validation.annotation.UniqueUsername;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@FieldsValueMatch(
        field = "password", fieldMatch = "verifyPassword",
        message = "{addUsuarioBici.password.nomatch}"
)
public record AddUsuarioBici(
        @NotBlank(message = "{addUsuarioBici.username.notblank}")
        @UniqueUsername
        String username,

        @NotBlank(message = "{addUsuarioBici.password.notblank}")
        @Size(min = 6, message = "{addUsuarioBici.password.size}")
        String password,

        @NotBlank(message = "{addUsuarioBici.verifyPassword.notblank}")
        String verifyPassword,

        @NotBlank(message = "{addUsuarioBici.email.notblank}")
        @Email(message = "{addUsuarioBici.email.email}")
        String email,

        @NotBlank(message = "{addUsuarioBici.nombre.notblank}")
        String nombre
) {
}