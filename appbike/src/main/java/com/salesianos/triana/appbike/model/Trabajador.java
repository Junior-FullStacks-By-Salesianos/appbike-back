package com.salesianos.triana.appbike.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Trabajador extends Usuario{

    private String turno;

    private boolean esJefe;

    public Trabajador(UUID id, String username, String password, String email,
                      String nombre, boolean accountNonExpired,
                      boolean accountNonLocked, boolean credentialsNonExpired,
                      boolean enabled, LocalDateTime createdAt, LocalDateTime lastPasswordChangeAt,
                      String turno, boolean esJefe) {
        super(id, username, password, email, nombre, accountNonExpired, accountNonLocked,
                credentialsNonExpired, enabled, createdAt, lastPasswordChangeAt);
        this.turno = turno;
        this.esJefe = esJefe;
    }
}
