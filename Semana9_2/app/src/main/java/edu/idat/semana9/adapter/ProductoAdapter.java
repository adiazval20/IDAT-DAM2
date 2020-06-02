package edu.idat.semana9.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.List;

import edu.idat.semana9.R;
import edu.idat.semana9.entity.Producto;

public class ProductoAdapter extends ArrayAdapter<Producto> {
    public ProductoAdapter(@NonNull Context context, int resource, @NonNull List<Producto> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_producto, parent, false);
        }

        Producto producto = getItem(position);

        ImageView imgProducto = convertView.findViewById(R.id.imgProducto);
        TextView txtNombre = convertView.findViewById(R.id.txtNombre);
        TextView txtPrecio = convertView.findViewById(R.id.txtPrecio);

        Picasso.get().load(producto.getImagenUrl()).into(imgProducto);
        txtNombre.setText(producto.getNombre());
        txtPrecio.setText(String.format("S/%.2f", producto.getPrecio()));

        return convertView;
    }

    public void loadData(List<Producto> productos) {
        clear();
        addAll(productos);
        notifyDataSetChanged();
    }
}
