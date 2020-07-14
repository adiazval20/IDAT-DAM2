package edu.idat.eventosvirtuales.service;

import edu.idat.eventosvirtuales.entity.DocumentoAlmacenado;
import edu.idat.eventosvirtuales.entity.EventoVirtual;
import edu.idat.eventosvirtuales.repository.DocumentoAlmacenadoRepository;
import edu.idat.eventosvirtuales.utils.GenericResponse;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;

import static edu.idat.eventosvirtuales.utils.Global.*;

@Service
@Transactional
public class DocumentoAlmacenadoService implements BaseService<DocumentoAlmacenado, Long> {
    private DocumentoAlmacenadoRepository repo;
    private FileStorageService storageService;

    public DocumentoAlmacenadoService(DocumentoAlmacenadoRepository repo, FileStorageService storageService) {
        this.repo = repo;
        this.storageService = storageService;
    }

    @Override
    public GenericResponse list() {
        return new GenericResponse(TIPO_DATA, RPTA_OK, OPERACION_CORRECTA, repo.list());
    }

    @Override
    public GenericResponse find(Long id) {
        return new GenericResponse(TIPO_DATA, RPTA_OK, OPERACION_CORRECTA, repo.findById(id));
    }

    @Override
    public GenericResponse save(DocumentoAlmacenado obj) {
        String originalFileName = obj.getFile().getOriginalFilename();
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String nombreArchivo = (repo.findById(obj.getId()).orElse(new DocumentoAlmacenado())).getFileName();

        nombreArchivo = storageService.storeFile(obj.getFile(), nombreArchivo);

        obj.setFileName(nombreArchivo);
        obj.setExtension(extension);

        return new GenericResponse(TIPO_DATA, RPTA_OK, OPERACION_CORRECTA, repo.save(obj));
    }

    private ResponseEntity<Resource> download(String completeFileName, HttpServletRequest request) {
        Resource resource = storageService.loadFileAsResource(completeFileName);
        String contentType = null;

        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    public ResponseEntity<Resource> downloadById(long id, HttpServletRequest request) {
        DocumentoAlmacenado doc = repo.findById(id).orElse(new DocumentoAlmacenado());
        return download(doc.getCompleteFileName(), request);
    }

    public ResponseEntity<Resource> downloadByFileName(String fileName, HttpServletRequest request) {
        DocumentoAlmacenado doc = repo.findByFileName(fileName).orElse(new DocumentoAlmacenado());
        return download(doc.getCompleteFileName(), request);
    }

    @Override
    public GenericResponse delete(Long aLong) {
        return null;
    }

    @Override
    public HashMap<String, Object> validate(DocumentoAlmacenado obj) {
        return null;
    }
}