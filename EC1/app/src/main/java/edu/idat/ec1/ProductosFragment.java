package edu.idat.ec1;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import edu.idat.ec1.adapter.ProductoAdapter;
import edu.idat.ec1.entity.Producto;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProductosFragment extends Fragment {
    private Comunicacion comunicacion;
    private List<Producto> productos;

    public ProductosFragment() {
        productos = new ArrayList<>();
    }

    public ProductosFragment(Comunicacion comunicacion, List<Producto> productos) {
        this.comunicacion = comunicacion;
        this.productos = productos;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_productos, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        RecyclerView rcvProductos = view.findViewById(R.id.rcvProductos);
        rcvProductos.setLayoutManager(new GridLayoutManager(view.getContext(), 1));
        ProductoAdapter productoAdapter = new ProductoAdapter(comunicacion, productos);
        rcvProductos.setAdapter(productoAdapter);
    }
}
