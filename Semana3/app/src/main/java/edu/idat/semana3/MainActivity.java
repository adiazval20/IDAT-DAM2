package edu.idat.semana3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

import java.util.ArrayList;

import edu.idat.semana3.adapter.ProductoAdapter;
import edu.idat.semana3.entity.Producto;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Lista de Productos");
        setSupportActionBar(toolbar);

        ArrayList<Producto> productos = new ArrayList<>();
        productos.add(new Producto(1, "Producto 1", "Descripción de producto 1", 1500.00, R.drawable.cel1));
        productos.add(new Producto(2, "Producto 2", "Descripción de producto 2", 2500.00, R.drawable.cel2));
        productos.add(new Producto(3, "Producto 3", "Descripción de producto 3", 3500.00, R.drawable.cel3));

        ProductoAdapter adapter = new ProductoAdapter(productos);

        RecyclerView rcvProductos = findViewById(R.id.rcvProductos);
        rcvProductos.setLayoutManager(new GridLayoutManager(this, 1));
        rcvProductos.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }
}
