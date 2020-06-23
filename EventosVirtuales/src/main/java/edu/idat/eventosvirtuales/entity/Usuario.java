package edu.idat.eventosvirtuales.entity;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Column(length = 50, unique = true)
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    @Column(length = 1)
    private String estado;

    @NotNull
    private boolean eliminado;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Persona persona;

    public Usuario() {
        this.id = 0L;
        this.username = "";
        this.password = "";
        this.estado = "A";
        this.eliminado = false;
        this.persona = new Persona();
    }

    public Usuario(@NotBlank String username, @NotBlank String password) {
        this.username = username;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
}
