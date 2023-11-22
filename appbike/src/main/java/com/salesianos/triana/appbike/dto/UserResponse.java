package com.salesianos.triana.appbike.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.salesianos.triana.appbike.model.Usuario;
import com.salesianos.triana.appbike.model.UsuarioBici;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class UserResponse {

    protected String id;
    protected String username, email, nombre;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    protected LocalDateTime createdAt;


    public static UserResponse of(Usuario user) {

        return UserResponse.builder()
                .id(user.getId().toString())
                .username(user.getUsername())
                .email(user.getEmail())
                .nombre(user.getNombre())
                .createdAt(user.getCreatedAt())
                .build();
    }

}
