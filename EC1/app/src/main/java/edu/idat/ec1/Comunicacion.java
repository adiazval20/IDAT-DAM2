package edu.idat.ec1;

import android.os.Bundle;

public interface Comunicacion {
    void cargarFragment(Class<?> cls);

    void lanzarActivity(Class<?> cls, Bundle bundle);
}
