package edu.idat.semana10;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import edu.idat.semana10.api.CustomResponse;
import edu.idat.semana10.viewmodel.MainViewModel;

public class LoginActivity extends AppCompatActivity implements LoginCommunication {
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

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
        viewModel.auth(username, password).observe(this, new Observer<CustomResponse>() {
            @Override
            public void onChanged(CustomResponse customResponse) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (customResponse.getRpta() == 1) {
                    loadActivity(new Intent(getBaseContext(), HomeActivity.class));
                } else {
                    Toast.makeText(LoginActivity.this, "NO SE PUDO ACCEDER", Toast.LENGTH_SHORT).show();
                }
                progressBar.setVisibility(View.GONE);
            }
        });
    }
}
