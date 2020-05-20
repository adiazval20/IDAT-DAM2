package edu.idat.ec1.adapter;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Locale;

import edu.idat.ec1.Comunicacion;
import edu.idat.ec1.DetalleProductoActivity;
import edu.idat.ec1.R;
import edu.idat.ec1.entity.Producto;

class ProductoViewHolder extends RecyclerView.ViewHolder {
    private Comunicacion comunicacion;
    private CardView crvProducto;
    private ImageView imgProducto;
    private TextView txtNombre;
    private TextView txtPrecio;

    public ProductoViewHolder(@NonNull View itemView, Comunicacion comunicacion) {
        super(itemView);

        this.comunicacion = comunicacion;
        crvProducto = itemView.findViewById(R.id.crvProducto);
        imgProducto = itemView.findViewById(R.id.imgProducto);
        txtNombre = itemView.findViewById(R.id.txtNombre);
        txtPrecio = itemView.findViewById(R.id.txtPrecio);

        crvProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("id", Integer.parseInt(String.valueOf(crvProducto.getTag())));
                ProductoViewHolder.this.comunicacion.lanzarActivity(DetalleProductoActivity.class, bundle);
            }
        });
    }

    public void cargarDatos(Producto producto) {
        crvProducto.setTag(producto.getId());
        imgProducto.setImageResource(producto.getImagenId());
        txtNombre.setText(producto.getNombre());
        txtPrecio.setText(String.format(Locale.ENGLISH, "S/%.2f", producto.getPrecio()));
    }
}
