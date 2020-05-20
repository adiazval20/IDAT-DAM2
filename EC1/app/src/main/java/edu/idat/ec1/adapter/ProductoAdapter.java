package edu.idat.ec1.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import edu.idat.ec1.Comunicacion;
import edu.idat.ec1.DetalleProductoActivity;
import edu.idat.ec1.R;
import edu.idat.ec1.entity.Producto;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoViewHolder> {
    private Comunicacion comunicacion;
    private List<Producto> productos;

    public ProductoAdapter(Comunicacion comunicacion) {
        this.comunicacion = comunicacion;
        productos = new ArrayList<>();
    }

    public ProductoAdapter(Comunicacion comunicacion, List<Producto> productos) {
        this.comunicacion = comunicacion;
        this.productos = productos;
    }

    @NonNull
    @Override
    public ProductoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_producto, parent, false);
        return new ProductoViewHolder(view, comunicacion);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoViewHolder holder, int position) {
        holder.cargarDatos(productos.get(position));
    }

    @Override
    public int getItemCount() {
        return productos.size();
    }
}
