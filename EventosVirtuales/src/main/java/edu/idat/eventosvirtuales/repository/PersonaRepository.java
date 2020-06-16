package edu.idat.eventosvirtuales.repository;

import edu.idat.eventosvirtuales.entity.Persona;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PersonaRepository extends CrudRepository<Persona, Long> {
    @Query("SELECT p FROM Persona p WHERE p.nroDocIdentidad = :nroDocIdentidad AND p.eliminado = false")
    Optional<Persona> findByNroDocIdentidad(String nroDocIdentidad);
}
