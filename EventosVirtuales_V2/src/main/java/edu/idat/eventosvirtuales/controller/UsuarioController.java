package edu.idat.eventosvirtuales.controller;

import edu.idat.eventosvirtuales.dto.UsuarioPersonaDTO;
import edu.idat.eventosvirtuales.entity.Usuario;
import edu.idat.eventosvirtuales.service.PersonaService;
import edu.idat.eventosvirtuales.service.UsuarioService;
import edu.idat.eventosvirtuales.utils.GenericResponse;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static edu.idat.eventosvirtuales.utils.Global.*;

@RestController
@RequestMapping("api/usuario")
public class UsuarioController implements BaseController<Usuario, Long> {
    private UsuarioService service;
    private PersonaService personaService;

    public UsuarioController(UsuarioService service, PersonaService personaService) {
        this.service = service;
        this.personaService = personaService;
    }

    @GetMapping
    public GenericResponse list() {
        return service.list();
    }

    @Override
    public GenericResponse find(Long id) {
        return service.find(id);
    }

    @Override
    public GenericResponse save(Usuario obj) {
        return null;
    }

    @Override
    public GenericResponse update(Long id, Usuario obj) {
        return null;
    }

    @Override
    public GenericResponse delete(Long id) {
        return null;
    }

    @PostMapping("/auth")
    public GenericResponse auth(@RequestParam String username, @RequestParam String password) {
        return service.findByCredenciales(username, password);
    }

    @PostMapping("/persona")
    public GenericResponse createWithPersona(@RequestBody @Valid UsuarioPersonaDTO dto) {
        return service.saveWidhPersona(dto);
    }
}