package edu.idat.eventosvirtuales.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.PreferenceManager;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import java.util.HashMap;

import edu.idat.eventosvirtuales.api.ConfigApi;
import edu.idat.eventosvirtuales.api.GenericResponse;
import edu.idat.eventosvirtuales.entity.Usuario;
import edu.idat.eventosvirtuales.fragment.LoginFragment;
import edu.idat.eventosvirtuales.R;
import edu.idat.eventosvirtuales.viewmodel.LoginViewModel;

public class LoginActivity extends AppCompatActivity implements LoginCommunication {
    private LoginViewModel viewModel;
    private FrameLayout frmContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        initViews();

        loadFragment(new LoginFragment(this));
    }

    private void initViews() {
        frmContainer = findViewById(R.id.frmContainer);
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
    public void login(String username, String password, ProgressBar progressBar) {
        Context context = this;

        viewModel.auth(username, password).observe(this, new Observer<GenericResponse<HashMap<String, Object>>>() {
            @Override
            public void onChanged(GenericResponse<HashMap<String, Object>> response) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (response == null) {
                    return;
                }

                if (response.getRpta() == 1) {
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
                    SharedPreferences.Editor editor = preferences.edit();

                    HashMap<String, Object> body = response.getBody();
                    long usuarioId = ((Double) body.get("usuarioId")).longValue();
                    long personaId = ((Double) body.get("personaId")).longValue();

                    editor.putLong("usuarioId", usuarioId);
                    editor.putLong("personaId", personaId);
                    editor.apply();

                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                } else {
                    showAlert(response.getMessage());
                }
                progressBar.setVisibility(View.INVISIBLE);
            }
        });
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