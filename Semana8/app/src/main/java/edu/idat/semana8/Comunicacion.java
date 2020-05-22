package edu.idat.semana8;

public interface Comunicacion {
    void toggleProgressBar(boolean status);

    void showMessage(String msg);

    void loadActivity(Class<?> cls);
}
