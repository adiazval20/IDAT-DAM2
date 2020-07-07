package edu.idat.semana10.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import edu.idat.semana10.R;

public class EventoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento);

        Bundle data = getIntent().getExtras();
        long id = (data != null) ? data.getLong("id") : 0;

        Toast.makeText(this, String.valueOf(id), Toast.LENGTH_SHORT).show();
    }
}