package edu.idat.semana4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import java.util.ArrayList;

import edu.idat.semana4.adapter.SeccionMenuAdapter;
import edu.idat.semana4.entity.SeccionMenu;

public class MainActivity extends AppCompatActivity implements Comunicacion {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ArrayList<String> menus = new ArrayList<>();
//        menus.add("Inicio");
//        menus.add("Mapa");
//        menus.add("Config");
//        menus.add("Efectos");
//
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, menus);

        ArrayList<SeccionMenu> menus = new ArrayList<>();
        menus.add(new SeccionMenu(1, "Inicio", R.drawable.ic_home_black_24dp));
        menus.add(new SeccionMenu(2, "Mapa", R.drawable.ic_add_location_black_24dp));
        menus.add(new SeccionMenu(3, "Config", R.drawable.ic_settings_black_24dp));
        menus.add(new SeccionMenu(4, "Efectos", R.drawable.ic_fullscreen_exit_black_24dp));

        SeccionMenuAdapter adapter = new SeccionMenuAdapter(this, R.layout.item_seccion_menu, menus);

        GridView grvMenus = findViewById(R.id.grvMenus);
        grvMenus.setAdapter(adapter);

        grvMenus.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        cargarFragment(InicioFragment.class);
                        break;
                    case 1:
                        cargarFragment(MapaFragment.class);
                        break;
                    case 2:
                        cargarFragment(ConfiguracionFragment.class);
                        break;
                    case 3:
                        cargarFragment(EfectosFragment.class);
                        break;
                }
            }
        });

        cargarFragment(InicioFragment.class);
    }

    private void cargarFragment(Class<?> claseFragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_right_in, R.anim.slide_right_out);

        if (claseFragment == InicioFragment.class) {
            InicioFragment inicioFragment = new InicioFragment();
            transaction.replace(R.id.frmContenedor, inicioFragment);

        } else if (claseFragment == MapaFragment.class) {
            MapaFragment mapaFragment = new MapaFragment();
            transaction.replace(R.id.frmContenedor, mapaFragment);

        } else if (claseFragment == ConfiguracionFragment.class) {
            ConfiguracionFragment configuracionFragment = new ConfiguracionFragment();
            transaction.replace(R.id.frmContenedor, configuracionFragment);

        } else if (claseFragment == EfectosFragment.class) {
            EfectosFragment efectosFragment = new EfectosFragment(this);
            transaction.replace(R.id.frmContenedor, efectosFragment);
        }

        transaction.commit();
    }

    @Override
    public void lanzarActividad(Class<?> tipoActividad) {
        Intent intent = new Intent(this, tipoActividad);
        startActivity(intent);
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }
}
