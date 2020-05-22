package edu.idat.semana8;

import android.os.AsyncTask;
import android.widget.Toast;

public class TareaAsincrona extends AsyncTask<Object, Void, Boolean> {
    private Comunicacion comunicacion;

    public TareaAsincrona(Comunicacion comunicacion) {
        this.comunicacion = comunicacion;
    }

    @Override
    protected void onPreExecute() {
        comunicacion.toggleProgressBar(true);
    }

    @Override
    protected Boolean doInBackground(Object... objects) {
        try {
            int time = (int) objects[2];
            Thread.sleep(time);

            String username = (String) objects[0];
            String password = (String) objects[1];

            if (username.equals("admin") && password.equals("123456")) {
                return true;
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    protected void onPostExecute(Boolean s) {
        comunicacion.toggleProgressBar(false);

        if (s) {
            comunicacion.loadActivity(HomeActivity.class);
        } else {
            comunicacion.showMessage("Datos incorrectos");
        }
    }
}