package edu.idat.ec1.repository;

import java.util.ArrayList;
import java.util.List;

import edu.idat.ec1.R;
import edu.idat.ec1.entity.Producto;

public class ProductoRepository {
    private static List<Producto> productos;

    static {
        productos = new ArrayList<>();
        productos.add(new Producto(1, "Producto 1", "Descripción de producto 1", 1500, R.drawable.cel1));
        productos.add(new Producto(2, "Producto 2", "Descripción de producto 2", 2500, R.drawable.cel2));
        productos.add(new Producto(3, "Producto 3", "Descripción de producto 3", 3500, R.drawable.cel3));
    }

    public static List<Producto> getAll() {
        return productos;
    }

    public static Producto findById(int id) {
        Producto producto = new Producto();
        for (Producto _producto : productos) {
            if (_producto.getId() == id) {
                producto = _producto;
                break;
            }
        }
        return producto;
    }
}
