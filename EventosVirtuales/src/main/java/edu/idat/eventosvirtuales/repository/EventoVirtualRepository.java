package edu.idat.eventosvirtuales.repository;

import edu.idat.eventosvirtuales.entity.EventoVirtual;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;

public interface EventoVirtualRepository extends CrudRepository<EventoVirtual, Long> {
    @Query("SELECT ev FROM EventoVirtual ev WHERE ev.estado = 'A' AND ev.eliminado = false")
    Iterable<EventoVirtual> list();

    @Query("SELECT ev FROM EventoVirtual ev " +
            "WHERE ev.fechaHoraInicio >= :fechaHora AND ev.estado = 'A' AND ev.eliminado = false " +
            "ORDER BY ev.fechaHoraInicio")
    Iterable<EventoVirtual> listProximos(Date fechaHora);

    @Query("SELECT ev FROM EventoVirtual ev " +
            "WHERE ev.fechaHoraInicio < :fechaHora AND ev.estado = 'A' AND ev.eliminado = false " +
            "ORDER BY ev.fechaHoraInicio DESC")
    Iterable<EventoVirtual> listPasados(Date fechaHora);
}
