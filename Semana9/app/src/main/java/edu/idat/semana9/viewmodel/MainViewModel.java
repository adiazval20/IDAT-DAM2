package edu.idat.semana9.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import edu.idat.semana9.entity.Producto;
import edu.idat.semana9.repository.ProductoRepository;

public class MainViewModel extends AndroidViewModel {
    private ProductoRepository productoRepository;

    public MainViewModel(@NonNull Application application) {
        super(application);
        productoRepository = new ProductoRepository(application);
    }

    public LiveData<List<Producto>> listProductos() {
        return productoRepository.list();
    }
}
