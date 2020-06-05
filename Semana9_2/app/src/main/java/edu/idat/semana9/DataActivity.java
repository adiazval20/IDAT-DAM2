package edu.idat.semana9;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Optional;

import edu.idat.semana9.entity.Producto;
import edu.idat.semana9.viewmodel.DataViewModel;

public class DataActivity extends AppCompatActivity {
    private DataViewModel viewModel;
    private Producto producto;
    private EditText edtNombre, edtDescripcion, edtPrecio, edtImagenUrl;
    private Button btnCancelar, btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        initControls();

        Bundle data = getIntent().getExtras();
        long productoId = (data != null) ? data.getLong("productoId") : 0;

        viewModel = new ViewModelProvider(this).get(DataViewModel.class);
        viewModel.findProducto(productoId).observe(this, new Observer<Optional<Producto>>() {
            @Override
            public void onChanged(Optional<Producto> p) {
                producto = p.orElse(new Producto());
                loadData();
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
                finish();
            }
        });
    }

    private void initControls() {
        edtNombre = findViewById(R.id.edtNombre);
        edtDescripcion = findViewById(R.id.edtDescripcion);
        edtPrecio = findViewById(R.id.edtPrecio);
        edtImagenUrl = findViewById(R.id.edtImagenUrl);
        btnCancelar = findViewById(R.id.btnCancelar);
        btnGuardar = findViewById(R.id.btnGuardar);
    }

    private void loadData() {
        edtNombre.setText(producto.getNombre());
        edtDescripcion.setText(producto.getDescripcion());
        edtPrecio.setText(String.valueOf(producto.getPrecio()));
        edtImagenUrl.setText(producto.getImagenUrl());
    }

    private void saveData() {
        producto.setNombre(edtNombre.getText().toString());
        producto.setDescripcion(edtDescripcion.getText().toString());
        producto.setPrecio(Double.parseDouble(edtPrecio.getText().toString()));
        producto.setImagenUrl(edtImagenUrl.getText().toString());

        viewModel.save(producto);
    }

}
