package edu.idat.semana5.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;
import java.util.Locale;

import edu.idat.semana5.DetalleActivity;
import edu.idat.semana5.R;
import edu.idat.semana5.entity.Producto;

public class ProductoAdapter extends ArrayAdapter<Producto> {
    public ProductoAdapter(@NonNull Context context, int resource, @NonNull List<Producto> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Producto producto = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_producto, parent, false);
        }

        ImageView imgProducto = convertView.findViewById(R.id.imgProducto);
        TextView txtNombre = convertView.findViewById(R.id.txtNombre);
        TextView txtPrecio = convertView.findViewById(R.id.txtPrecio);

        imgProducto.setImageResource(producto.getImagenId());
        txtNombre.setText(producto.getNombre());
        txtPrecio.setText(String.format(Locale.ENGLISH, "S/%.2f", producto.getPrecio()));

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getContext().startActivity(new Intent(getContext(), DetalleActivity.class));
            }
        });

        return convertView;
    }
}
