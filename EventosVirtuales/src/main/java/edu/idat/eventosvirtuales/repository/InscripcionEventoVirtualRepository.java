package edu.idat.eventosvirtuales.repository;

import edu.idat.eventosvirtuales.entity.InscripcionEventoVirtual;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface InscripcionEventoVirtualRepository extends CrudRepository<InscripcionEventoVirtual, Long> {
    @Query("SELECT iev FROM InscripcionEventoVirtual iev WHERE iev.estado = 'A' AND iev.eliminado = false")
    Iterable<InscripcionEventoVirtual> list();

    @Query("SELECT iev FROM InscripcionEventoVirtual iev WHERE iev.eventoVirtual.id = :eventoVirtualId " +
            "AND iev.persona.id = :personaId AND iev.estado = 'A' AND iev.eliminado = false")
    Optional<InscripcionEventoVirtual> findByEventoPersona(long eventoVirtualId, long personaId);
}
