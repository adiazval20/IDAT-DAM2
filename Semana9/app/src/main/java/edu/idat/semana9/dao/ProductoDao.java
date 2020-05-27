package edu.idat.semana9.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import edu.idat.semana9.entity.Producto;

@Dao
public interface ProductoDao {
    @Query("SELECT * FROM Producto")
    LiveData<List<Producto>> list();

    @Query("SELECT * FROM Producto WHERE id = :id")
    LiveData<Producto> find(int id);

    @Insert
    long insert(Producto producto);

    @Insert
    void insertAll(Producto... productos);

    @Update
    void update(Producto producto);

    @Delete
    void delete(Producto producto);
}
