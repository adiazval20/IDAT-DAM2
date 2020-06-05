package edu.idat.semana9;

import android.content.Intent;

import edu.idat.semana9.entity.Producto;

public interface Communication {
    void loadActivity(Intent intent);

    void deleteItem(Producto producto);
}
