package edu.idat.eventosvirtuales.controller;

import edu.idat.eventosvirtuales.dto.InscripcionEventoVirtualDTO;
import edu.idat.eventosvirtuales.entity.InscripcionEventoVirtual;
import edu.idat.eventosvirtuales.service.InscripcionEventoVirtualService;
import edu.idat.eventosvirtuales.utils.GenericResponse;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/inscripcion-evento-virtual")
public class InscripcionEventoVirtualController implements BaseController<InscripcionEventoVirtual, Long> {
    private InscripcionEventoVirtualService service;

    public InscripcionEventoVirtualController(InscripcionEventoVirtualService service) {
        this.service = service;
    }

    @Override
    @GetMapping
    public GenericResponse list() {
        return service.list();
    }

    @Override
    @GetMapping("/{id}")
    public GenericResponse find(@PathVariable Long id) {
        return service.find(id);
    }

    @Override
    public GenericResponse save(@Valid @RequestBody InscripcionEventoVirtual obj) {
        return service.save(obj);
    }

    @PostMapping
    public GenericResponse save(@RequestBody InscripcionEventoVirtualDTO dto) {
        return service.save(dto);
    }

    @Override
    @PutMapping("/{id}")
    public GenericResponse update(@PathVariable Long id, @Valid @RequestBody InscripcionEventoVirtual obj) {
        obj.setId(id);
        return service.save(obj);
    }

    @Override
    @DeleteMapping("/{id}")
    public GenericResponse delete(@PathVariable Long id) {
        return service.delete(id);
    }
}