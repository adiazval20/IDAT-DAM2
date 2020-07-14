package edu.idat.eventosvirtuales.controller;

import edu.idat.eventosvirtuales.entity.DocumentoAlmacenado;
import edu.idat.eventosvirtuales.service.DocumentoAlmacenadoService;
import edu.idat.eventosvirtuales.utils.GenericResponse;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/documento-almacenado")
public class DocumentoAlmacenadoController implements BaseController<DocumentoAlmacenado, Long> {
    private final DocumentoAlmacenadoService service;

    public DocumentoAlmacenadoController(DocumentoAlmacenadoService service) {
        this.service = service;
    }

    @Override
    @GetMapping
    public GenericResponse list() {
        return service.list();
    }

    @Override
    public GenericResponse find(Long aLong) {
        return null;
    }

    @Override
    @PostMapping
    public GenericResponse save(@ModelAttribute DocumentoAlmacenado obj) {
        return service.save(obj);
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<Resource> download(@PathVariable String fileName, HttpServletRequest request) {
        return service.downloadByFileName(fileName, request);
    }

    @Override
    public GenericResponse update(Long aLong, DocumentoAlmacenado obj) {
        return null;
    }

    @Override
    public GenericResponse delete(Long aLong) {
        return null;
    }
}
