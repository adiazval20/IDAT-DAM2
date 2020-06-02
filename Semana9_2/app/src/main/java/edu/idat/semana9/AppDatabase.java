package edu.idat.semana9;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import edu.idat.semana9.dao.ProductoDao;
import edu.idat.semana9.entity.Producto;

@Database(entities = {Producto.class}, version = 1, exportSchema = true)
public abstract class AppDatabase extends RoomDatabase {
    private static volatile AppDatabase DB;
    private static final int HILOS = 4;
    public static final ExecutorService dbExecutor = Executors.newFixedThreadPool(HILOS);

    public abstract ProductoDao productoDao();

    public static AppDatabase getDatabase(Context context) {
        if (DB == null) {
            synchronized (AppDatabase.class) {
                if (DB == null) {
                    DB = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "DB")
                            .addCallback(initCallback)
                            .build();
                }
            }
        }
        return DB;
    }

    private static Callback initCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            dbExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    Producto celular = new Producto("Celular", "Celular", 1500, "https://comandato.vteximg.com.br/arquivos/ids/195480-600-600/celular-lg-k11-color-azul-1.jpg?v=636933769267330000");
                    Producto camara = new Producto("Cámara", "Cámara", 1000, "https://www.sony.com.pe/image/7724b023e5b7563538a19dc7ec96a37b?fmt=pjpeg&wid=330&bgcolor=FFFFFF&bgc=FFFFFF");

                    ProductoDao dao = DB.productoDao();
                    dao.insertAll(celular, camara);
                }
            });
        }

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
        }
    };
}
