package edu.idat.eventosvirtuales.service;

import edu.idat.eventosvirtuales.dto.EventoVirtualDTO;
import edu.idat.eventosvirtuales.entity.DocumentoAlmacenado;
import edu.idat.eventosvirtuales.entity.EventoVirtual;
import edu.idat.eventosvirtuales.entity.Persona;
import edu.idat.eventosvirtuales.repository.DocumentoAlmacenadoRepository;
import edu.idat.eventosvirtuales.repository.EventoVirtualRepository;
import edu.idat.eventosvirtuales.utils.GenericResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static edu.idat.eventosvirtuales.utils.Global.*;

@Service
@Transactional
public class EventoVirtualService implements BaseService<EventoVirtual, Long> {
    private EventoVirtualRepository repo;
    private DocumentoAlmacenadoRepository docAlmRepo;
    private PersonaService perServ;
    private DocumentoAlmacenadoService docAlmServ;

    public EventoVirtualService(EventoVirtualRepository repo, DocumentoAlmacenadoRepository docAlmRepo, PersonaService perServ, DocumentoAlmacenadoService docAlmServ) {
        this.repo = repo;
        this.docAlmRepo = docAlmRepo;
        this.perServ = perServ;
        this.docAlmServ = docAlmServ;
    }

    @Override
    public GenericResponse list() {
        return new GenericResponse(TIPO_RESULT, RPTA_OK, OPERACION_CORRECTA, repo.list());
    }

    public GenericResponse listProximos() {
        List<EventoVirtual> dtos = (List<EventoVirtual>) repo.listProximos(new Date());
        return new GenericResponse(TIPO_DATA, RPTA_OK, OPERACION_CORRECTA, loadDtos(dtos));
    }

    public GenericResponse listPasados() {
        return new GenericResponse(TIPO_DATA, RPTA_OK, OPERACION_CORRECTA, repo.listPasados(new Date()));
    }

    @Override
    public GenericResponse find(Long id) {
        return new GenericResponse(TIPO_RESULT, RPTA_OK, OPERACION_CORRECTA, loadDto(repo.findById(id).orElse(new EventoVirtual())));
    }

    @Override
    public GenericResponse save(EventoVirtual obj) {
        return null;
    }

    public GenericResponse save(EventoVirtualDTO dto) {
        GenericResponse response = new GenericResponse();
        HashMap<String, Object> errors, errorsPonente;

        response.setType(TIPO_DATA);

        EventoVirtual obj = repo.findById(dto.getEventoVirtualId()).orElse(new EventoVirtual());
        obj.setNombre(dto.getNombre());
        obj.setFechaHoraInicio(dto.getFechaHoraInicio());
        obj.setFechaHoraFin(dto.getFechaHoraFin());
        obj.setUrl(dto.getUrl());

        Persona ponente = perServ.find(dto.getPonenteId()).orElse(new Persona());
        ponente.setNroDocIdentidad(dto.getNroDocIdentidad());
        ponente.setApellidoPaterno(dto.getApellidoPaterno());
        ponente.setApellidoMaterno(dto.getApellidoMaterno());
        ponente.setNombres(dto.getNombres());
        ponente.setFechaNacimiento(dto.getFechaNacimiento());

        // Guardo la imagen del banner
        DocumentoAlmacenado banner = obj.getBanner() != null ? obj.getBanner() : new DocumentoAlmacenado();
        if (banner.getId() == 0) {
            banner = docAlmRepo.findById(dto.getBannerId()).orElse(new DocumentoAlmacenado());
        }
        banner.setFile(dto.getBanner());

        // Guardo la imagen del ponente
        DocumentoAlmacenado fotoPerfil = ponente.getFotoPerfil() != null ? ponente.getFotoPerfil() : new DocumentoAlmacenado();
        if (fotoPerfil.getId() == 0) {
            fotoPerfil = docAlmRepo.findById(dto.getFotoPerfilId()).orElse(new DocumentoAlmacenado());
        }
        fotoPerfil.setFile(dto.getFotoPerfil());

        errors = validate(obj);
        errorsPonente = perServ.validate(ponente);

        if (errors.size() == 0 && errorsPonente.size() == 0) {
            fotoPerfil = (DocumentoAlmacenado) docAlmServ.save(fotoPerfil).getBody();
            ponente.setFotoPerfil(fotoPerfil);
            ponente = perServ.save(ponente);
            obj.setPonente(ponente);

            banner = (DocumentoAlmacenado) docAlmServ.save(banner).getBody();
            obj.setBanner(banner);
            obj = repo.save(obj);

            response.setRpta(RPTA_OK);
            response.setMessage(OPERACION_CORRECTA);
            response.setBody(obj);
        } else {
            response.setRpta(RPTA_WARNING);
            response.setMessage(OPERACION_INCORRECTA);

            HashMap<String, Object> allErrors = new HashMap<>();
            allErrors.put("EventoVirtual", errors);
            allErrors.put("Ponente", errorsPonente);
            response.setBody(allErrors);
        }

        return response;
    }

    @Override
    public GenericResponse delete(Long id) {
        return null;
    }

    public GenericResponse listProximosByPersonaInscrita(long personaId) {
        List<EventoVirtual> eventoVirtuals = (List<EventoVirtual>) repo.listProximosByPersonaInscrita(new Date(), personaId);
        List<EventoVirtualDTO> dtos = loadDtos(eventoVirtuals);
        return new GenericResponse(TIPO_RESULT, RPTA_OK, OPERACION_CORRECTA, dtos);
    }

    @Override
    public HashMap<String, Object> validate(EventoVirtual obj) {
        HashMap<String, Object> errors = new HashMap<>();
        return errors;
    }

    private List<EventoVirtualDTO> loadDtos(List<EventoVirtual> eventoVirtuals) {
        List<EventoVirtualDTO> dtos = new ArrayList<>();
        for (EventoVirtual eventoVirtual : eventoVirtuals) {
            dtos.add(loadDto(eventoVirtual));
        }
        return dtos;
    }

    private EventoVirtualDTO loadDto(EventoVirtual eventoVirtual) {
        EventoVirtualDTO dto = new EventoVirtualDTO();
        dto.setEventoVirtualId(eventoVirtual.getId());
        dto.setNombre(eventoVirtual.getNombre());
        dto.setFechaHoraInicio(eventoVirtual.getFechaHoraInicio());
        dto.setFechaHoraFin(eventoVirtual.getFechaHoraFin());
        dto.setUrl(eventoVirtual.getUrl());

        DocumentoAlmacenado banner = eventoVirtual.getBanner();
        if (banner != null) {
            dto.setFileNameBanner(banner.getFileName());
        }

        Persona ponente = eventoVirtual.getPonente();
        if (ponente != null) {
            dto.setPonenteId(ponente.getId());
            dto.setApellidoPaterno(ponente.getApellidoPaterno());
            dto.setApellidoMaterno(ponente.getApellidoMaterno());
            dto.setNombres(ponente.getNombres());
            dto.setNroDocIdentidad(ponente.getNroDocIdentidad());
        }

        return dto;
    }
}
