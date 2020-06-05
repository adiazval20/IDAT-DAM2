package edu.idat.semana9;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import edu.idat.semana9.adapter.ProductoAdapter;
import edu.idat.semana9.entity.Producto;
import edu.idat.semana9.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity implements Communication {
    private MainViewModel viewModel;
    private ProductoAdapter adapter;
    private FloatingActionButton fabAgregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView lstProductos = findViewById(R.id.lstProductos);
        adapter = new ProductoAdapter(this, R.layout.item_producto, new ArrayList<Producto>(), this);
        lstProductos.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        viewModel.listProductos().observe(this, new Observer<List<Producto>>() {
            @Override
            public void onChanged(List<Producto> productos) {
                adapter.loadData(productos);
            }
        });

        fabAgregar = findViewById(R.id.fabAgregar);
        fabAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadActivity(new Intent(MainActivity.this, DataActivity.class));
            }
        });
    }

    @Override
    public void loadActivity(Intent intent) {
        startActivity(intent);
    }

    @Override
    public void deleteItem(Producto producto) {
        viewModel.deleteProducto(producto);
    }
}
