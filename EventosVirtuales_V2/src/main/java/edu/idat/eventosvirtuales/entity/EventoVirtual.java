package edu.idat.eventosvirtuales.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class EventoVirtual {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Dege ingresar un nombre")
    @Column(nullable = false)
    private String nombre;

    @NotNull(message = "Debe ingresar una fecha de inicio")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date fechaHoraInicio;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraFin;

    @NotBlank(message = "Debe indicar una URL")
    @Column(nullable = false)
    private String url;

    @NotBlank
    @Column(length = 1, nullable = false)
    private String estado;

    private boolean eliminado;

    @ManyToOne(optional = false)
    private Persona ponente;

    @OneToOne
    private DocumentoAlmacenado banner;

    @OneToMany(mappedBy = "eventoVirtual")
    private List<InscripcionEventoVirtual> inscripcionEventoVirtuals;

    public EventoVirtual() {
        this.id = 0L;
        this.nombre = "";
        this.fechaHoraInicio = new Date();
        this.fechaHoraFin = new Date();
        this.url = "";
        this.estado = "A";
        this.eliminado = false;
        this.ponente = new Persona();
        this.inscripcionEventoVirtuals = new ArrayList<>();
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

    public void setPonente(Persona ponente) {
        this.ponente = ponente;
    }

    public Date getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(Date fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public Date getFechaHoraFin() {
        return fechaHoraFin;
    }

    public void setFechaHoraFin(Date fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Persona getPonente() {
        return ponente;
    }

    public DocumentoAlmacenado getBanner() {
        return banner;
    }

    public void setBanner(DocumentoAlmacenado banner) {
        this.banner = banner;
    }

    public List<InscripcionEventoVirtual> getInscripcionEventoVirtuals() {
        return inscripcionEventoVirtuals;
    }

    public void setInscripcionEventoVirtuals(List<InscripcionEventoVirtual> inscripcionEventoVirtuals) {
        this.inscripcionEventoVirtuals = inscripcionEventoVirtuals;
    }


}
