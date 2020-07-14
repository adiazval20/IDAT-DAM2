package edu.idat.eventosvirtuales.dto;

import java.util.Date;

public class EventoVirtualDTO {
    private long eventoVirtualId;
    private String nombre;
    private Date fechaHoraInicio;
    private Date fechaHoraFin;
    private String url;
    private long ponenteId;
    private String nroDocIdentidad;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String nombres;
    private Date fechaNacimiento;
    private long fotoPerfilId;
    private String fileNameBanner;
    private long bannerId;

    public EventoVirtualDTO() {
    }

    public long getEventoVirtualId() {
        return eventoVirtualId;
    }

    public void setEventoVirtualId(long eventoVirtualId) {
        this.eventoVirtualId = eventoVirtualId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public long getPonenteId() {
        return ponenteId;
    }

    public void setPonenteId(long ponenteId) {
        this.ponenteId = ponenteId;
    }

    public String getNroDocIdentidad() {
        return nroDocIdentidad;
    }

    public void setNroDocIdentidad(String nroDocIdentidad) {
        this.nroDocIdentidad = nroDocIdentidad;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getNombreCompletoPonente() {
        return String.format("%s %s %s", apellidoPaterno, apellidoMaterno, nombres);
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public long getFotoPerfilId() {
        return fotoPerfilId;
    }

    public void setFotoPerfilId(long fotoPerfilId) {
        this.fotoPerfilId = fotoPerfilId;
    }

    public String getFileNameBanner() {
        return fileNameBanner;
    }

    public void setFileNameBanner(String fileNameBanner) {
        this.fileNameBanner = fileNameBanner;
    }

    public long getBannerId() {
        return bannerId;
    }

    public void setBannerId(long bannerId) {
        this.bannerId = bannerId;
    }
}
