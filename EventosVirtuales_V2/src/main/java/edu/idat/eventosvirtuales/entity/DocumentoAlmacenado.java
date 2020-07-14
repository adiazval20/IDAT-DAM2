package edu.idat.eventosvirtuales.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
public class DocumentoAlmacenado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nombre;

    @NotBlank
    private String fileName;

    private String extension;

    private String estado;

    private boolean eliminado;

    @Transient
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private MultipartFile file;

    @Transient
    private String urlFile;

    @OneToOne(mappedBy = "fotoPerfil")
    private Persona persona;

    public DocumentoAlmacenado() {
        this.id = 0;
        this.nombre = "";
        this.fileName = "";
        this.extension = "";
        this.estado = "A";
        this.eliminado = false;
        this.urlFile = "";
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public boolean isEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getUrlFile() {
        return urlFile;
    }

    public void setUrlFile(String urlFile) {
        this.urlFile = urlFile;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public String getCompleteFileName() {
        return fileName + extension;
    }
}