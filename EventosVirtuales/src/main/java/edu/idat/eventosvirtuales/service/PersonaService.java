package edu.idat.eventosvirtuales.service;

import edu.idat.eventosvirtuales.entity.Persona;
import edu.idat.eventosvirtuales.repository.PersonaRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Service
public class PersonaService {
    private PersonaRepository repo;

    public PersonaService(PersonaRepository repo) {
        this.repo = repo;
    }

    public Optional<Persona> find(long id) {
        return repo.findById(id);
    }

    public Persona save(Persona persona) {
        return repo.save(persona);
    }

    public HashMap<String, String> validate(Persona persona) {
        HashMap<String, String> errors = new HashMap<>();

        if (persona.getId() == 0 && repo.findByNroDocIdentidad(persona.getNroDocIdentidad()).isPresent()) {
            errors.put("nroDocIdentidad", String.format("Ya existe una persona con DNI '%s'", persona.getNroDocIdentidad()));
        }

        return errors;
    }
}
