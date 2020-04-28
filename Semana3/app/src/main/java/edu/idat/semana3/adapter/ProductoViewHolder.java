package edu.idat.semana3.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Locale;

import edu.idat.semana3.R;
import edu.idat.semana3.entity.Producto;

class ProductoViewHolder extends RecyclerView.ViewHolder {
    private ImageView imgProducto;
    private TextView txtNombre;
    private TextView txtPrecio;
    private TextView txtDescripcion;

    public ProductoViewHolder(@NonNull View itemView) {
        super(itemView);

        imgProducto = itemView.findViewById(R.id.imgProducto);
        txtNombre = itemView.findViewById(R.id.txtNombre);
        txtPrecio = itemView.findViewById(R.id.txtPrecio);
        txtDescripcion = itemView.findViewById(R.id.txtDescripcion);
    }

    public void cargarDatos(Producto producto) {
        imgProducto.setImageResource(producto.getImagenId());
        txtNombre.setText(producto.getNombre());
        txtDescripcion.setText(producto.getDescripcion());
        txtPrecio.setText(String.format(Locale.ENGLISH, "S/%.2f", producto.getPrecio()));
    }
}
