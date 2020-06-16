package edu.idat.eventosvirtuales.service;

import edu.idat.eventosvirtuales.dto.UsuarioPersonaDTO;
import edu.idat.eventosvirtuales.entity.Persona;
import edu.idat.eventosvirtuales.entity.Usuario;
import edu.idat.eventosvirtuales.repository.PersonaRepository;
import edu.idat.eventosvirtuales.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Optional;

@Service
public class UsuarioService {
    private UsuarioRepository repo;
    private PersonaRepository perRepo;
    private PersonaService perServ;

    public UsuarioService(UsuarioRepository repo, PersonaRepository perRepo, PersonaService perServ) {
        this.repo = repo;
        this.perRepo = perRepo;
        this.perServ = perServ;
    }

    public Iterable<Usuario> list() {
        return repo.findAll();
    }

    public Optional<Usuario> find(long id) {
        return repo.findById(id);
    }

    @Transactional
    public HashMap<String, Object> saveWidhPersona(UsuarioPersonaDTO dto) {
        HashMap<String, Object> result = new HashMap<>();
        HashMap<String, String> errors, errorsPersona;

        Usuario usuario = repo.findById(dto.getUsuarioId()).orElse(new Usuario());
        Persona persona = perRepo.findById(dto.getPersonaId()).orElse(new Persona());

        if (persona.getId() == 0) {
            persona.setNroDocIdentidad(dto.getNroDocIdentidad());
            persona.setApellidoPaterno(dto.getApellidoPaterno());
            persona.setApellidoMaterno(dto.getApellidoMaterno());
            persona.setNombres(dto.getNombres());
            persona.setFechaNacimiento(dto.getFechaNacimiento());
        }

        usuario.setUsername(dto.getUsername());
        usuario.setPassword(dto.getPassword());

        errors = validate(usuario);
        errorsPersona = perServ.validate(persona);

        if (errors.size() == 0 && errorsPersona.size() == 0) {
            persona = perRepo.save(persona);
            usuario.setPersona(persona);
            usuario = repo.save(usuario);
            result.put("rpta", 1);
            result.put("data", usuario);
        } else {
            HashMap<String, Object> allErrors = new HashMap<>();
            allErrors.put("usuario", errors);
            allErrors.put("persona", errorsPersona);
            result.put("rpta", 0);
            result.put("data", allErrors);
        }

        return result;
    }

    public Optional<Usuario> findByCredenciales(String username, String password) {
        return repo.findByCredenciales(username, password);
    }

    public HashMap<String, String> validate(Usuario usuario) {
        HashMap errors = new HashMap();

        if (repo.findByUsername(usuario.getUsername()).isPresent()) {
            errors.put("username", String.format("El nombre de usuario '%s' ya existe", usuario.getUsername()));
        }
        return errors;
    }
}