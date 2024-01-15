package com.salesianos.triana.appbike.repository;

import com.salesianos.triana.appbike.model.Bicicleta;
import com.salesianos.triana.appbike.model.Uso;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UsoRepositoryTest {

    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    UsoRepository usoRepository;

    @Autowired
    EstacionRepository estacionRepository;

    @Autowired
    BicicletaRepository bicicletaRepository;

    @Test
    void findCurrentUsoByBicicleta() {


        UUID idBici = UUID.fromString("128329b5-4080-4e0a-8b09-8cf2d3a88c20");
        Bicicleta b = new Bicicleta();
        b.setUuid(idBici);
        b.setNombre("Michael");
        Uso u = new Uso();
        u.setUuid(UUID.fromString("57c4051c-cfa8-4175-a8a5-67b7a3dbe14c"));
        u.setBicicleta(b);

        testEntityManager.merge(b);
        testEntityManager.merge(u);

        Optional<Uso> usoActivo = usoRepository.findCurrentUsoByBicicleta(idBici);

        assertTrue(usoActivo.isPresent());
        assertEquals(u.getUuid(), usoActivo.get().getUuid());

    }
}
