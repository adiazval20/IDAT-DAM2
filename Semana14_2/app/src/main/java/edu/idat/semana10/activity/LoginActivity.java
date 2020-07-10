package edu.idat.semana10.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.PreferenceManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.HashMap;

import edu.idat.semana10.fragment.LoginFragment;
import edu.idat.semana10.R;
import edu.idat.semana10.api.GenericResponse;
import edu.idat.semana10.viewmodel.LoginViewModel;

public class LoginActivity extends AppCompatActivity implements LoginCommunication {
    private LoginViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        loadFragment(new LoginFragment(this));
    }

    @Override
    public void loadActivity(Intent intent) {
        startActivity(intent);
    }

    @Override
    public void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
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
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (response.getRpta() == 1) {
                    HashMap<String, Object> body = response.getBody();
                    long personaId = ((Double) body.get("personaId")).longValue();
                    long usuarioId = ((Double) body.get("usuarioId")).longValue();

                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putLong("personaId", personaId);
                    editor.putLong("usuarioId", usuarioId);
                    editor.apply();

                    loadActivity(new Intent(getBaseContext(), HomeActivity.class));
                } else {
                    Toast.makeText(LoginActivity.this, "NO SE PUDO ACCEDER", Toast.LENGTH_SHORT).show();
                }
                progressBar.setVisibility(View.GONE);
            }
        });
    }
}
