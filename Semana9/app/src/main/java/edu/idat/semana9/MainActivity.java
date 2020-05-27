package edu.idat.semana9;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

import edu.idat.semana9.entity.Producto;
import edu.idat.semana9.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity {
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        viewModel.listProductos().observe(this, new Observer<List<Producto>>() {
            @Override
            public void onChanged(List<Producto> productos) {
                for (Producto p : productos) {
                    Toast.makeText(MainActivity.this, p.getNombre(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
