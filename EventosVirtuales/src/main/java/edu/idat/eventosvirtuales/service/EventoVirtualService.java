package edu.idat.eventosvirtuales.service;

import edu.idat.eventosvirtuales.dto.EventoVirtualDTO;
import edu.idat.eventosvirtuales.entity.EventoVirtual;
import edu.idat.eventosvirtuales.entity.Persona;
import edu.idat.eventosvirtuales.repository.EventoVirtualRepository;
import edu.idat.eventosvirtuales.utils.GenericResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import static edu.idat.eventosvirtuales.utils.Global.*;

@Service
public class EventoVirtualService implements BaseService<EventoVirtual, Long> {
    private EventoVirtualRepository repo;
    private PersonaService perServ;

    public EventoVirtualService(EventoVirtualRepository repo, PersonaService perServ) {
        this.repo = repo;
        this.perServ = perServ;
    }

    @Override
    public GenericResponse list() {
        return new GenericResponse(TIPO_RESULT, RPTA_OK, OPERACION_CORRECTA, loadListDto(repo.list()));
    }

    public GenericResponse listProximos() {
        return new GenericResponse(TIPO_DATA, RPTA_OK, OPERACION_CORRECTA, loadListDto(repo.listProximos(new Date())));
    }

    public GenericResponse listPasados() {
        return new GenericResponse(TIPO_DATA, RPTA_OK, OPERACION_CORRECTA, loadListDto(repo.listPasados(new Date())));
    }

    @Override
    public GenericResponse find(Long id) {
        return new GenericResponse(TIPO_RESULT, RPTA_OK, OPERACION_CORRECTA, loadDto(repo.findById(id).orElse(new EventoVirtual())));
    }

    @Override
    public GenericResponse save(EventoVirtual obj) {
        GenericResponse response = new GenericResponse();
        HashMap<String, Object> errors, ponenteErrors, allErrors;

        response.setType(TIPO_DATA);

        Persona ponente = perServ.find(obj.getPonente().getId()).orElse(obj.getPonente());

        errors = validate(obj);
        ponenteErrors = perServ.validate(obj.getPonente());

        if (errors.size() == 0 && ponenteErrors.size() == 0) {
            ponente = perServ.save(ponente);
            obj.setPonente(ponente);
            obj = repo.save(obj);

            response.setRpta(RPTA_OK);
            response.setMessage(OPERACION_CORRECTA);
            response.setBody(obj);
        } else {
            response.setRpta(RPTA_WARNING);
            response.setMessage(OPERACION_INCORRECTA);

            allErrors = new HashMap<>();
            allErrors.put("EventoVirtual", errors);
            allErrors.put("Ponente", ponenteErrors);
            response.setBody(allErrors);
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

    private ArrayList<EventoVirtualDTO> loadListDto(Iterable<EventoVirtual> eventoVirtuals) {
        ArrayList<EventoVirtualDTO> data = new ArrayList<>();

        for (EventoVirtual eve : eventoVirtuals) {
            EventoVirtualDTO dto = loadDto(eve);
            data.add(dto);
        }

        return data;
    }

    private EventoVirtualDTO loadDto(EventoVirtual eve) {
        Persona ponente = eve.getPonente();

        EventoVirtualDTO dto = new EventoVirtualDTO();
        dto.setEventoVirtualId(eve.getId());
        dto.setNombre(eve.getNombre());
        dto.setFechaHoraInicio(eve.getFechaHoraInicio());
        dto.setFechaHoraFin(eve.getFechaHoraFin());
        dto.setUrl(eve.getUrl());
        dto.setPonenteId(ponente.getId());
        dto.setNroDocIdentidad(ponente.getNroDocIdentidad());
        dto.setApellidoPaterno(ponente.getApellidoPaterno());
        dto.setApellidoMaterno(ponente.getApellidoMaterno());
        dto.setNombres(ponente.getNombres());

        return dto;
    }
}
