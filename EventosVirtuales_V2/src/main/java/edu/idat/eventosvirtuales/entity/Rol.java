package edu.idat.eventosvirtuales.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Column(length = 50)
    private String nombre;

    @NotBlank
    @Column(length = 1)
    private String estado;

    @NotNull
    private boolean eliminado;

    public Rol() {
    }

    public Rol(@NotBlank String nombre, @NotBlank String estado, @NotNull boolean eliminado) {
        this.nombre = nombre;
        this.estado = estado;
        this.eliminado = eliminado;
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
}
