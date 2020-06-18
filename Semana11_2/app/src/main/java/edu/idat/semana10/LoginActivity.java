package edu.idat.semana10;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Toast;

import edu.idat.semana10.api.CustomResponse;
import edu.idat.semana10.viewmodel.MainViewModel;

public class LoginActivity extends AppCompatActivity {
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        viewModel.auth("andy", "andy2").observe(this, new Observer<CustomResponse>() {
            @Override
            public void onChanged(CustomResponse customResponse) {
                boolean result = (boolean) customResponse.getBody();
                if (result) {
                    Toast.makeText(LoginActivity.this, "DATOS CORRECTOS", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this, "DATOS INCORRECTOS", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
