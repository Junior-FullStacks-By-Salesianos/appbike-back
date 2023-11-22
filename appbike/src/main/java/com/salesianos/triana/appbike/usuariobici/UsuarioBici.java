package com.salesianos.triana.appbike.usuariobici;

import com.salesianos.triana.appbike.uso.Uso;
import com.salesianos.triana.appbike.usuario.Usuario;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class UsuarioBici extends Usuario {

    private String numTarjeta;

    private String pin;

    private double saldo;

    @OneToMany(mappedBy = "usuarioBici")
    private List<Uso> usos;

    public UsuarioBici(UUID id, String username, String password, String email, String nombre, boolean accountNonExpired, boolean accountNonLocked, boolean credentialsNonExpired, boolean enabled, LocalDateTime createdAt, LocalDateTime lastPasswordChangeAt, String numTarjeta, String pin, double saldo, List<Uso> usos) {
        super(id, username, password, email, nombre, accountNonExpired, accountNonLocked, credentialsNonExpired, enabled, createdAt, lastPasswordChangeAt);
        this.numTarjeta = numTarjeta;
        this.pin = pin;
        this.saldo = saldo;
        this.usos = usos;
    }
}
