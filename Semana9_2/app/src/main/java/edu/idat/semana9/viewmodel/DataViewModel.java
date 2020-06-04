package edu.idat.semana9.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.Optional;

import edu.idat.semana9.entity.Producto;
import edu.idat.semana9.repository.ProductoRepository;

public class DataViewModel extends AndroidViewModel {
    private ProductoRepository productoRepository;

    public DataViewModel(@NonNull Application application) {
        super(application);
        productoRepository = new ProductoRepository(application);
    }

    public LiveData<Optional<Producto>> findProducto(int id) {
        return null;
//        return productoRepository.find(id);
    }
}
