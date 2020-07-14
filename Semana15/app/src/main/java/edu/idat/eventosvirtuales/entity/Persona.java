package edu.idat.eventosvirtuales.entity;

import java.util.Date;

public class Persona {
    private long id;
    private String nroDocIdentidad;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String nombres;
    private Date fechaNacimiento;

    public Persona() {
        id = 0;
        nroDocIdentidad = "";
        apellidoPaterno = "";
        apellidoMaterno = "";
        nombres = "";
        fechaNacimiento = new Date();
    }

    public Persona(long id, String nroDocIdentidad, String apellidoPaterno, String apellidoMaterno, String nombres, Date fechaNacimiento) {
        this.id = id;
        this.nroDocIdentidad = nroDocIdentidad;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.nombres = nombres;
        this.fechaNacimiento = fechaNacimiento;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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