package com.salesianos.triana.appbike.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchConnectionDetails;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class UsuarioBici extends Usuario{

    private String numTarjeta;

    private String pin;

    private double saldo;

    public UsuarioBici(UUID id, String username, String password, String email, String nombre, boolean accountNonExpired, boolean accountNonLocked, boolean credentialsNonExpired, boolean enabled, LocalDateTime createdAt, LocalDateTime lastPasswordChangeAt, String numTarjeta, String pin, double saldo) {
        super(id, username, password, email, nombre, accountNonExpired, accountNonLocked, credentialsNonExpired, enabled, createdAt, lastPasswordChangeAt);
        this.numTarjeta = numTarjeta;
        this.pin = pin;
        this.saldo = saldo;
    }
}
