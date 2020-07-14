package edu.idat.eventosvirtuales.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

public class EventoVirtualDTO {
    private long eventoVirtualId;
    private String nombre;

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date fechaHoraInicio;

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date fechaHoraFin;

    private String url;
    private long ponenteId;
    private String nroDocIdentidad;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String nombres;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date fechaNacimiento;
    private long fotoPerfilId;
    private String fileNameBanner;
    private long bannerId;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private MultipartFile banner;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private MultipartFile fotoPerfil;

    public EventoVirtualDTO() {
        this.eventoVirtualId = 0;
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

    public MultipartFile getBanner() {
        return banner;
    }

    public void setBanner(MultipartFile banner) {
        this.banner = banner;
    }

    public MultipartFile getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(MultipartFile fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }
}
