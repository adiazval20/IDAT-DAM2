package edu.idat.semana10.entity;

public class Usuario {
    private long id;
    private String username;
    private String password;
    private Persona persona;

    public Usuario() {
    }

    public Usuario(long id, String username, String password, Persona persona) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.persona = persona;
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

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
}
