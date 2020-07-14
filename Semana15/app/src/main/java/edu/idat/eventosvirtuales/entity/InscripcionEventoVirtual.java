package edu.idat.eventosvirtuales.entity;

public class InscripcionEventoVirtual {
    private long id;
    private boolean requiereCertificado;
    private Persona persona;
    private EventoVirtual eventoVirtual;

    public InscripcionEventoVirtual() {
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
