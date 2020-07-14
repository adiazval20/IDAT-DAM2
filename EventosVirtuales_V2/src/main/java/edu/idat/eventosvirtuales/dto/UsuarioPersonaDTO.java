package edu.idat.eventosvirtuales.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class UsuarioPersonaDTO {
    private long usuarioId;
    private String username;
    private String password;
    private long personaId;
    private String nroDocIdentidad;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String nombres;
    private Date fechaNacimiento;

    public UsuarioPersonaDTO() {
        usuarioId = 0;
        username = "";
        password = "";
        personaId = 0;
        nroDocIdentidad = "";
        apellidoPaterno = "";
        apellidoMaterno = "";
        nombres = "";
        fechaNacimiento = new Date();
    }

    public UsuarioPersonaDTO(Long usuarioId, String username, String password, Long personaId, String nroDocIdentidad, String apellidoPaterno, String apellidoMaterno, String nombres, Date fechaNacimiento) {
        this.usuarioId = usuarioId;
        this.username = username;
        this.password = password;
        this.personaId = personaId;
        this.nroDocIdentidad = nroDocIdentidad;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.nombres = nombres;
        this.fechaNacimiento = fechaNacimiento;
    }

    public long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getPersonaId() {
        return personaId;
    }

    public void setPersonaId(long personaId) {
        this.personaId = personaId;
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
}
