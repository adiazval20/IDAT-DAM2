package edu.idat.semana9.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import edu.idat.semana9.AppDatabase;
import edu.idat.semana9.dao.ProductoDao;
import edu.idat.semana9.entity.Producto;

public class ProductoRepository {
    private ProductoDao dao;

    public ProductoRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        this.dao = db.productoDao();
    }

    public LiveData<List<Producto>> list() {
        return dao.list();
    }
}
