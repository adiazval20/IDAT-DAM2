package edu.idat.eventosvirtuales.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import edu.idat.eventosvirtuales.R;
import edu.idat.eventosvirtuales.fragment.EventosFragment;
import edu.idat.eventosvirtuales.fragment.HomeFragment;
import edu.idat.eventosvirtuales.viewmodel.HomeViewModel;

public class HomeActivity extends AppCompatActivity implements HomeCommunication {
    private HomeViewModel viewModel;
    private BottomNavigationView bnvMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Bienvenido!");
        setSupportActionBar(toolbar);

        initViews();
        initListeners();

        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        
    }

    private void initViews() {
        bnvMenu = findViewById(R.id.bnvMenu);
    }

    private void initListeners() {
        HomeCommunication communication = this;
        bnvMenu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.optInicio:
                        loadFragment(new HomeFragment());
                        break;
                    case R.id.optEventos:
                        loadFragment(new EventosFragment(communication));
                        break;
                    case R.id.optPerfil:
//                        loadFragment();
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public void loadActivity(Intent intent) {
        startActivity(intent);
    }

    @Override
    public void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        transaction.replace(R.id.frmContainer, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void backFragment() {
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void showAlert(String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Alerta");
        builder.setMessage(msg);
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
