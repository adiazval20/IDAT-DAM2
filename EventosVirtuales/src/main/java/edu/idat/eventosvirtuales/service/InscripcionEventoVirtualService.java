package edu.idat.eventosvirtuales.service;

import edu.idat.eventosvirtuales.dto.InscripcionEventoVirtualDTO;
import edu.idat.eventosvirtuales.entity.EventoVirtual;
import edu.idat.eventosvirtuales.entity.InscripcionEventoVirtual;
import edu.idat.eventosvirtuales.entity.Persona;
import edu.idat.eventosvirtuales.repository.EventoVirtualRepository;
import edu.idat.eventosvirtuales.repository.InscripcionEventoVirtualRepository;
import edu.idat.eventosvirtuales.repository.PersonaRepository;
import edu.idat.eventosvirtuales.utils.GenericResponse;
import org.springframework.stereotype.Service;

import java.util.HashMap;

import static edu.idat.eventosvirtuales.utils.Global.*;

@Service
public class InscripcionEventoVirtualService implements BaseService<InscripcionEventoVirtual, Long> {
    private InscripcionEventoVirtualRepository repo;
    private EventoVirtualRepository eveVirtRepo;
    private PersonaRepository perRepo;

    public InscripcionEventoVirtualService(InscripcionEventoVirtualRepository repo, EventoVirtualRepository eveVirtRepo, PersonaRepository perRepo) {
        this.repo = repo;
        this.eveVirtRepo = eveVirtRepo;
        this.perRepo = perRepo;
    }

    @Override
    public GenericResponse list() {
        return new GenericResponse(TIPO_DATA, RPTA_OK, OPERACION_CORRECTA, repo.list());
    }

    @Override
    public GenericResponse find(Long id) {
        return new GenericResponse(TIPO_DATA, RPTA_OK, OPERACION_CORRECTA, repo.findById(id));
    }

    @Override
    public GenericResponse save(InscripcionEventoVirtual obj) {
        EventoVirtual eventoVirtual = eveVirtRepo.findById(obj.getEventoVirtual().getId()).orElse(new EventoVirtual());
        Persona persona = perRepo.findById(obj.getPersona().getId()).orElse(new Persona());
        obj.setEventoVirtual(eventoVirtual);
        obj.setPersona(persona);
        
        return new GenericResponse(TIPO_DATA, RPTA_OK, OPERACION_CORRECTA, repo.save(obj));
    }

    public GenericResponse save(InscripcionEventoVirtualDTO dto) {
        InscripcionEventoVirtual iev = repo.findByEventoPersona(dto.getEventoVirtualId(), dto.getPersonaId()).orElse(new InscripcionEventoVirtual());
        if (iev.getId() == 0) {
            EventoVirtual eventoVirtual = eveVirtRepo.findById(dto.getEventoVirtualId()).orElse(new EventoVirtual());
            Persona persona = perRepo.findById(dto.getPersonaId()).orElse(new Persona());
            iev.setEventoVirtual(eventoVirtual);
            iev.setPersona(persona);
        }
        iev.setRequiereCertificado(dto.isRequiereCertificado());
        return new GenericResponse(TIPO_DATA, RPTA_OK, OPERACION_CORRECTA, repo.save(iev));
    }

    @Override
    public GenericResponse delete(Long id) {
        return null;
    }

    @Override
    public HashMap<String, Object> validate(InscripcionEventoVirtual obj) {
        return null;
    }
}
