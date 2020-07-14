package edu.idat.eventosvirtuales.entity;

import java.util.Date;

public class EventoVirtual {
    private long id;
    private String nombre;
    private Persona ponente;
    private Date fechaHoraInicio;
    private Date fechaHoraFin;
    private String url;

    public EventoVirtual() {
    }

    public EventoVirtual(long id, String nombre, Persona ponente, Date fechaHoraInicio, Date fechaHoraFin, String url) {
        this.id = id;
        this.nombre = nombre;
        this.ponente = ponente;
        this.fechaHoraInicio = fechaHoraInicio;
        this.fechaHoraFin = fechaHoraFin;
        this.url = url;
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

    public Persona getPonente() {
        return ponente;
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
}
