package edu.idat.ec1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import edu.idat.ec1.entity.Producto;
import edu.idat.ec1.repository.ProductoRepository;

public class DetalleProductoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_producto);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Información del Producto");

        Bundle data = getIntent().getExtras();
        int id = (data != null ? data.getInt("id") : 0);

        Producto producto = ProductoRepository.findById(id);

        ImageView imgProducto = findViewById(R.id.imgProducto);
        TextView txtNombre = findViewById(R.id.txtNombre);

        imgProducto.setImageResource(producto.getImagenId());
        txtNombre.setText(producto.getNombre());
    }
}
