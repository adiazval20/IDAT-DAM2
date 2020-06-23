package edu.idat.eventosvirtuales.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class InscripcionEventoVirtual {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(nullable = false)
    private boolean requiereCertificado;

    @NotBlank
    @Column(length = 1, nullable = false)
    private String estado;

    private boolean eliminado;

    @ManyToOne(optional = false)
    private Persona persona;

    @ManyToOne(optional = false)
    private EventoVirtual eventoVirtual;

    public InscripcionEventoVirtual() {
        this.id = 0;
        this.requiereCertificado = false;
        this.estado = "A";
        this.eliminado = false;
        this.persona = new Persona();
        this.eventoVirtual = new EventoVirtual();
    }

    public InscripcionEventoVirtual(@NotBlank boolean requiereCertificado) {
        this.id = 0;
        this.requiereCertificado = requiereCertificado;
        this.estado = "A";
        this.eliminado = false;
        this.persona = new Persona();
        this.eventoVirtual = new EventoVirtual();
    }

    public InscripcionEventoVirtual(@NotBlank boolean requiereCertificado, Persona persona, EventoVirtual eventoVirtual) {
        this.id = 0;
        this.requiereCertificado = requiereCertificado;
        this.estado = "A";
        this.eliminado = false;
        this.persona = persona;
        this.eventoVirtual = eventoVirtual;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isRequiereCertificado() {
        return requiereCertificado;
    }

    public void setRequiereCertificado(boolean requiereCertificado) {
        this.requiereCertificado = requiereCertificado;
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

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public EventoVirtual getEventoVirtual() {
        return eventoVirtual;
    }

    public void setEventoVirtual(EventoVirtual eventoVirtual) {
        this.eventoVirtual = eventoVirtual;
    }
}