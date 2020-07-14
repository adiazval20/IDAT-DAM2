package edu.idat.eventosvirtuales.dto;

public class InscripcionEventoVirtualDTO {
    private long eventoVirtualId;
    private long personaId;
    private boolean requiereCertificado;

    public InscripcionEventoVirtualDTO() {
    }

    public long getEventoVirtualId() {
        return eventoVirtualId;
    }

    public void setEventoVirtualId(long eventoVirtualId) {
        this.eventoVirtualId = eventoVirtualId;
    }

    public long getPersonaId() {
        return personaId;
    }

    public void setPersonaId(long personaId) {
        this.personaId = personaId;
    }

    public boolean isRequiereCertificado() {
        return requiereCertificado;
    }

    public void setRequiereCertificado(boolean requiereCertificado) {
        this.requiereCertificado = requiereCertificado;
    }
}
