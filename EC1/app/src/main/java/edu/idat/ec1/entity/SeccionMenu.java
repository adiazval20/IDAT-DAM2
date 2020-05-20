package edu.idat.ec1.entity;

public class SeccionMenu {
    private int id;
    private String titulo;
    private int imagenId;

    public SeccionMenu() {
    }

    public SeccionMenu(int id, String titulo, int imagenId) {
        this.id = id;
        this.titulo = titulo;
        this.imagenId = imagenId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getImagenId() {
        return imagenId;
    }

    public void setImagenId(int imagenId) {
        this.imagenId = imagenId;
    }
}
