package edu.idat.eventosvirtuales.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import edu.idat.eventosvirtuales.R;
import edu.idat.eventosvirtuales.api.GenericResponse;
import edu.idat.eventosvirtuales.dto.UsuarioPersonaDTO;
import edu.idat.eventosvirtuales.viewmodel.SignupViewModel;

public class SignupActivity extends AppCompatActivity {
    private SignupViewModel viewModel;
    private EditText edtNroDocIdentidad, edtApellidoPaterno, edtApellidoMaterno, edtNombres, dtpFechaNacimiento, edtUsername, edtPassword;
    private Button btnCancel, btnSave;
    private ProgressBar pgbSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        viewModel = new ViewModelProvider(this).get(SignupViewModel.class);

        initViews();
        initListeners();
    }

    private void initViews() {
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
                dto.setUsername(edtUsername.getText().toString());
                dto.setPassword(edtPassword.getText().toString());

                signup(dto);
            }
        });
    }

    private void signup(UsuarioPersonaDTO dto) {
        pgbSignup.setVisibility(View.VISIBLE);

        viewModel.signup(dto).observe(this, new Observer<GenericResponse>() {
            @Override
            public void onChanged(GenericResponse genericResponse) {
                if (genericResponse.getRpta() == 1) {
                    startActivity(new Intent(getBaseContext(), HomeActivity.class));
                } else {
                    showAlert(genericResponse.getMessage());
                }

                pgbSignup.setVisibility(View.GONE);
            }
        });
    }

    private void showAlert(String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Mensaje de respuesta");
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