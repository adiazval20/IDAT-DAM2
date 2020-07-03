package edu.idat.eventosvirtuales.controller;

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

    @Override
    @GetMapping("/{id}")
    public GenericResponse find(@PathVariable Long id) {
        return service.find(id);
    }

    @Override
    @PostMapping
    public GenericResponse save(@Valid @RequestBody EventoVirtual obj) {
        return service.save(obj);
    }

    @Override
    @PutMapping("/{id}")
    public GenericResponse update(@PathVariable Long id, @Valid EventoVirtual obj) {
        obj.setId(id);
        return service.save(obj);
    }

    @Override
    @DeleteMapping("/{id}")
    public GenericResponse delete(@PathVariable Long id) {
        return null;
    }
}
