package edu.idat.semana10;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import edu.idat.semana10.api.GenericResponse;
import edu.idat.semana10.dto.UsuarioPersonaDTO;
import edu.idat.semana10.viewmodel.SignupViewModel;

public class SignupActivity extends AppCompatActivity {
    private SignupViewModel viewModel;
    private Button btnCancel, btnSave;
    private EditText edtNroDocIdentidad, edtApellidoPaterno, edtApellidoMaterno, edtNombres, dtpFechaNacimiento, edtUsername, edtPassword;
    private ProgressBar pgbSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        viewModel = new ViewModelProvider(this).get(SignupViewModel.class);

        initControls();
        initListeners();
    }

    private void initControls() {
        edtNroDocIdentidad = findViewById(R.id.edtNroDocIdentidad);
        edtApellidoPaterno = findViewById(R.id.edtApellidoPaterno);
        edtApellidoMaterno = findViewById(R.id.edtApellidoMaterno);
        edtNombres = findViewById(R.id.edtNombres);
        dtpFechaNacimiento = findViewById(R.id.dtpFechaNacimiento);
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);

        btnCancel = findViewById(R.id.btnCancel);
        btnSave = findViewById(R.id.btnSave);

        pgbSignup = findViewById(R.id.pgbSignup);
    }

    private void initListeners() {
        final Context context = this;

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UsuarioPersonaDTO dto = new UsuarioPersonaDTO();
                dto.setNroDocIdentidad(edtNroDocIdentidad.getText().toString());
                dto.setApellidoPaterno(edtApellidoPaterno.getText().toString());
                dto.setApellidoMaterno(edtApellidoMaterno.getText().toString());
                dto.setNombres(edtNombres.getText().toString());
                dto.setFechaNacimiento(dtpFechaNacimiento.getText().toString());
                dto.setUsername(edtUsername.getText().toString());
                dto.setPassword(edtPassword.getText().toString());

                viewModel.register(dto).observe((LifecycleOwner) context, new Observer<GenericResponse>() {
                    @Override
                    public void onChanged(GenericResponse genericResponse) {
                        if (genericResponse.getRpta() == 1) {
                            startActivity(new Intent(context, HomeActivity.class));
                        } else {
                            Toast.makeText(context, "No se pudo realizar la operación", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}