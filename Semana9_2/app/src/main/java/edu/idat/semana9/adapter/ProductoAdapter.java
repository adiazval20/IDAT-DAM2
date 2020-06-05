package edu.idat.semana9.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.widget.PopupMenu;

import com.squareup.picasso.Picasso;

import java.util.List;

import edu.idat.semana9.Communication;
import edu.idat.semana9.DataActivity;
import edu.idat.semana9.R;
import edu.idat.semana9.entity.Producto;

public class ProductoAdapter extends ArrayAdapter<Producto> {
    private Communication communication;

    public ProductoAdapter(@NonNull Context context, int resource, @NonNull List<Producto> objects, Communication communication) {
        super(context, resource, objects);
        this.communication = communication;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_producto, parent, false);
        }

        final Producto producto = getItem(position);

        ImageView imgProducto = convertView.findViewById(R.id.imgProducto);
        TextView txtNombre = convertView.findViewById(R.id.txtNombre);
        TextView txtPrecio = convertView.findViewById(R.id.txtPrecio);

        Picasso.get().load(producto.getImagenUrl()).into(imgProducto);
        txtNombre.setText(producto.getNombre());
        txtPrecio.setText(String.format("S/%.2f", producto.getPrecio()));

        final TextView btnMenu = convertView.findViewById(R.id.btnMenu);
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(getContext(), btnMenu);
                popup.getMenuInflater().inflate(R.menu.menu_producto, popup.getMenu());
                popup.show();

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.optEditar:
                                Intent intent = new Intent(getContext(), DataActivity.class);
                                intent.putExtra("productoId", producto.getId());
                                communication.loadActivity(intent);
                                break;
                            case R.id.optEliminar:
                                break;
                        }
                        return false;
                    }
                });
            }
        });

        return convertView;
    }

    public void loadData(List<Producto> productos) {
        clear();
        addAll(productos);
        notifyDataSetChanged();
    }
}
