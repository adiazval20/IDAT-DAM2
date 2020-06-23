package edu.idat.eventosvirtuales.service;

import edu.idat.eventosvirtuales.entity.EventoVirtual;
import edu.idat.eventosvirtuales.repository.EventoVirtualRepository;
import edu.idat.eventosvirtuales.utils.GenericResponse;
import org.aspectj.weaver.ResolvedPointcutDefinition;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;

import static edu.idat.eventosvirtuales.utils.Global.*;

@Service
@Transactional
public class EventoVirtualService implements BaseService<EventoVirtual, Long> {
    private EventoVirtualRepository repo;

    public EventoVirtualService(EventoVirtualRepository repo) {
        this.repo = repo;
    }

    @Override
    public GenericResponse list() {
        return new GenericResponse(TIPO_RESULT, RPTA_OK, OPERACION_CORRECTA, repo.list());
    }

    public GenericResponse listProximos() {
        return new GenericResponse(TIPO_DATA, RPTA_OK, OPERACION_CORRECTA, repo.listProximos(new Date()));
    }

    public GenericResponse listPasados() {
        return new GenericResponse(TIPO_DATA, RPTA_OK, OPERACION_CORRECTA, repo.listPasados(new Date()));
    }

    @Override
    public GenericResponse find(Long id) {
        return new GenericResponse(TIPO_RESULT, RPTA_OK, OPERACION_CORRECTA, repo.findById(id));
    }

    @Override
    public GenericResponse save(EventoVirtual obj) {
        GenericResponse response = new GenericResponse();
        response.setType(TIPO_DATA);

        HashMap<String, Object> errors = validate(obj);
        if (errors.size() == 0) {
            obj = repo.save(obj);
            response.setRpta(RPTA_OK);
            response.setMessage(OPERACION_CORRECTA);
            response.setBody(obj);
        } else {
            response.setRpta(RPTA_WARNING);
            response.setMessage(OPERACION_INCORRECTA);
        }

        return response;
    }

    @Override
    public GenericResponse delete(Long id) {
        return null;
    }

    @Override
    public HashMap<String, Object> validate(EventoVirtual obj) {
        HashMap<String, Object> errors = new HashMap<>();
        return errors;
    }
}
