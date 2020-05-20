package edu.idat.ec1;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;

import edu.idat.ec1.adapter.SeccionMenuAdapter;
import edu.idat.ec1.entity.Producto;
import edu.idat.ec1.entity.SeccionMenu;
import edu.idat.ec1.repository.ProductoRepository;

public class MainActivity extends AppCompatActivity implements Comunicacion {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Bienvenido!");
        setSupportActionBar(toolbar);

        GridView grvSeccionMenus = findViewById(R.id.grvSeccionMenus);
        List<SeccionMenu> seccionMenus = new ArrayList<>();
        seccionMenus.add(new SeccionMenu(1, "Inicio", R.drawable.ic_home_black_24dp));
        seccionMenus.add(new SeccionMenu(2, "Tienda", R.drawable.ic_assignment_turned_in_black_24dp));
        seccionMenus.add(new SeccionMenu(3, "Anim", R.drawable.ic_fullscreen_exit_black_24dp));

        SeccionMenuAdapter seccionMenuAdapter = new SeccionMenuAdapter(this, R.layout.item_seccion_menu, seccionMenus);
        grvSeccionMenus.setAdapter(seccionMenuAdapter);

        grvSeccionMenus.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        cargarFragment(InicioFragment.class);
                        break;
                    case 1:
                        cargarFragment(ProductosFragment.class);
                        break;
                    case 2:
                        cargarFragment(AnimacionesFragment.class);
                        break;
                }
            }
        });

        cargarFragment(InicioFragment.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        ((MenuBuilder) menu).setOptionalIconsVisible(true);
        getMenuInflater().inflate(R.menu.menu_opciones, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.optCarrito:
                cargarFragment(ProductosFragment.class);
                break;
            case R.id.optAnim:
                cargarFragment(AnimacionesFragment.class);
                break;
            case R.id.optSalir:
                finish();
                break;
        }

        return true;
    }

    @Override
    public void cargarFragment(Class<?> cls) {
        // 1. Verifico el fragment y determino el orden
        FragmentManager manager = getSupportFragmentManager();
        int contenedorId = R.id.frmContenedor;
        manager.findFragmentById(contenedorId);

        Class<?> actualFragmentCls;
        int prevPosition = 0, nextPosition = 0;

        Fragment actualFragment = manager.findFragmentById(contenedorId);
        if (actualFragment != null) {
            actualFragmentCls = actualFragment.getClass();
            if (actualFragmentCls == ProductosFragment.class) {
                prevPosition = 1;
            } else if (actualFragmentCls == AnimacionesFragment.class) {
                prevPosition = 2;
            }
        }

        if (cls == ProductosFragment.class) {
            nextPosition = 1;
        } else if (cls == AnimacionesFragment.class) {
            nextPosition = 2;
        }

        //2. Determino el efecto a aplicar
        FragmentTransaction transaction = manager.beginTransaction();
        if (nextPosition > prevPosition) {
            transaction.setCustomAnimations(R.anim.slide_up_in, R.anim.slide_up_out);
        } else if (nextPosition < prevPosition) {
            transaction.setCustomAnimations(R.anim.slide_down_in, R.anim.slide_down_out);
        }

        //2. Realizo la transacción
        Fragment fragment = null;
        if (cls == InicioFragment.class) {
            fragment = new InicioFragment();
        } else if (cls == ProductosFragment.class) {
            fragment = new ProductosFragment(this, ProductoRepository.getAll());
        } else if (cls == AnimacionesFragment.class) {
            fragment = new AnimacionesFragment(this);
        }
        if (fragment != null) {
            transaction.replace(contenedorId, fragment);
            transaction.commit();
        }
    }

    @Override
    public void lanzarActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
    }
}
