package edu.idat.semana10.dto;

public class InscripcionEventoVirtualDTO {
    private boolean requiereCertificado;
    private long personaId;
    private long eventoVirtualId;

    public InscripcionEventoVirtualDTO() {
    }

    public InscripcionEventoVirtualDTO(boolean requiereCertificado, long personaId, long eventoVirtualId) {
        this.requiereCertificado = requiereCertificado;
        this.personaId = personaId;
        this.eventoVirtualId = eventoVirtualId;
    }

    public boolean isRequiereCertificado() {
        return requiereCertificado;
    }

    public void setRequiereCertificado(boolean requiereCertificado) {
        this.requiereCertificado = requiereCertificado;
    }

    public long getPersonaId() {
        return personaId;
    }

    public void setPersonaId(long personaId) {
        this.personaId = personaId;
    }

    public long getEventoVirtualId() {
        return eventoVirtualId;
    }

    public void setEventoVirtualId(long eventoVirtualId) {
        this.eventoVirtualId = eventoVirtualId;
    }
}
