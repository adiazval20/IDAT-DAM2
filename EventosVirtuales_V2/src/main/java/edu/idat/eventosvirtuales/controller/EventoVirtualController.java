package edu.idat.eventosvirtuales.controller;

import edu.idat.eventosvirtuales.dto.EventoVirtualDTO;
import edu.idat.eventosvirtuales.entity.EventoVirtual;
import edu.idat.eventosvirtuales.service.EventoVirtualService;
import edu.idat.eventosvirtuales.utils.GenericResponse;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/evento-virtual")
public class EventoVirtualController implements BaseController<EventoVirtual, Long> {
    private EventoVirtualService service;

    public EventoVirtualController(EventoVirtualService service) {
        this.service = service;
    }

    @Override
    @GetMapping
    public GenericResponse list() {
        return service.list();
    }

    @GetMapping("/proximos")
    public GenericResponse listProximos() {
        return service.listProximos();
    }

    @GetMapping("/pasados")
    public GenericResponse listPasados() {
        return service.listPasados();
    }

    @GetMapping("/inscripcion-persona/proximos/{personaId}")
    public GenericResponse listProximosByPersonaInscrita(@PathVariable long personaId) {
        return service.listProximosByPersonaInscrita(personaId);
    }

    @Override
    @GetMapping("/{id}")
    public GenericResponse find(@PathVariable Long id) {
        return service.find(id);
    }

    @Override
    public GenericResponse save(@Valid @RequestBody EventoVirtual obj) {
        return null;
    }

    @PostMapping
    public GenericResponse save(@ModelAttribute EventoVirtualDTO dto) {
        return service.save(dto);
    }

    @Override
    @PutMapping("/{id}")
    public GenericResponse update(@PathVariable Long id, @RequestBody EventoVirtual obj) {
        obj.setId(id);
        return service.save(obj);
    }

    @Override
    @DeleteMapping("/{id}")
    public GenericResponse delete(@PathVariable Long id) {
        return null;
    }
}
