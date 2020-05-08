package edu.idat.semana5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import edu.idat.semana5.adapter.ProductoAdapter;
import edu.idat.semana5.entity.Producto;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Producto> productos = new ArrayList<>();
        productos.add(new Producto(1, "Celular 1", "Descripción de celular 1", 1500, R.drawable.cel1));
        productos.add(new Producto(2, "Celular 2", "Descripción de celular 2", 2500, R.drawable.cel2));
        productos.add(new Producto(3, "Celular 3", "Descripción de celular 3", 3500, R.drawable.cel3));

        ProductoAdapter adapter = new ProductoAdapter(this, R.layout.item_producto, productos);

        ListView lstProductos = findViewById(R.id.lstProductos);
        lstProductos.setAdapter(adapter);
    }
}
