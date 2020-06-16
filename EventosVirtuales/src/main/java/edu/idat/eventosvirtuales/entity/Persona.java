package edu.idat.eventosvirtuales.entity;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(length = 20, nullable = false)
    private String nroDocIdentidad;

    @NotBlank
    @Column(length = 100, nullable = false)
    private String apellidoPaterno;

    @NotBlank
    @Column(length = 100, nullable = false)
    private String apellidoMaterno;

    @NotBlank
    @Column(length = 150, nullable = false)
    private String nombres;

    private String fechaNacimiento;

    @NotBlank
    @Column(length = 1, nullable = false)
    private String estado;

    private boolean eliminado;

    @JsonBackReference
    @OneToMany(mappedBy = "persona")
    private List<Usuario> usuarios;

    public Persona() {
        this.id = 0L;
        this.nroDocIdentidad = "";
        this.apellidoPaterno = "";
        this.apellidoMaterno = "";
        this.nombres = "";
        this.fechaNacimiento = "01/01/1900";
        this.estado = "A";
        this.eliminado = false;
        this.usuarios = new ArrayList<>();
    }

    public Persona(@NotBlank String nroDocIdentidad, @NotBlank String apellidoPaterno, @NotBlank String apellidoMaterno, @NotBlank String nombres, String fechaNacimiento) {
        this.nroDocIdentidad = nroDocIdentidad;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.nombres = nombres;
        this.fechaNacimiento = fechaNacimiento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
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

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
