package edu.idat.semana10.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import edu.idat.semana10.R;
import edu.idat.semana10.fragment.EventosFragment;
import edu.idat.semana10.fragment.InicioFragment;
import edu.idat.semana10.fragment.PerfilFragment;

public class HomeActivity extends AppCompatActivity {
    private BottomNavigationView bnvMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initControls();
        initListeners();
        loadFragment(new InicioFragment());
    }

    private void initControls() {
        bnvMenu = findViewById(R.id.bnvMenu);
    }

    private void initListeners() {
        bnvMenu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.optInicio:
                        loadFragment(new InicioFragment());
                        break;
                    case R.id.optEventos:
                        loadFragment(new EventosFragment());
                        break;
                    case R.id.optPerfil:
                        loadFragment(new PerfilFragment());
                        break;
                }

                return true;
            }
        });
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frmContainer, fragment);
        transaction.addToBackStack(null);
        transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        transaction.commit();
    }
}