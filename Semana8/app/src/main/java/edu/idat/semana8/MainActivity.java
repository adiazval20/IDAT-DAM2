package edu.idat.semana8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements Comunicacion {
    private EditText edtUsername;
    private EditText edtPassword;
    private ProgressBar pgbEjecutando;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        pgbEjecutando = findViewById(R.id.pgbEjecutando);

        Button btnIngresar = findViewById(R.id.btnIngresar);
        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtUsername.getText().toString();
                String password = edtPassword.getText().toString();
                int time = 5000;
                new TareaAsincrona(MainActivity.this).execute(username, password, time);
            }
        });
    }

    @Override
    public void toggleProgressBar(boolean status) {
        if (status) {
            pgbEjecutando.setVisibility(View.VISIBLE);
        } else {
            pgbEjecutando.setVisibility(View.GONE);
        }
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void loadActivity(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }
}
