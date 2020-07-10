package edu.idat.semana10.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.PreferenceManager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import edu.idat.semana10.R;
import edu.idat.semana10.api.GenericResponse;
import edu.idat.semana10.dto.EventoVirtualDTO;
import edu.idat.semana10.dto.InscripcionEventoVirtualDTO;
import edu.idat.semana10.entity.InscripcionEventoVirtual;
import edu.idat.semana10.viewmodel.EventoViewModel;

public class EventoActivity extends AppCompatActivity {
    private EventoViewModel viewModel;
    private TextView txtNombreEvento, txtNombrePonente, txtHorario;
    private Button btnCancel, btnInscribirse;
    private long eventoVirtualId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento);

        initViews();

        viewModel = new ViewModelProvider(this).get(EventoViewModel.class);

        Bundle data = getIntent().getExtras();
        eventoVirtualId = (data != null) ? data.getLong("id") : 0;

        viewModel.find(eventoVirtualId).observe(this, new Observer<GenericResponse<EventoVirtualDTO>>() {
            @Override
            public void onChanged(GenericResponse<EventoVirtualDTO> response) {
                if (response != null) {
                    loadData(response.getBody());
                }
            }
        });

        initListeners();
    }

    private void initViews() {
        txtNombreEvento = findViewById(R.id.txtNombreEvento);
        txtNombrePonente = findViewById(R.id.txtNombrePonente);
        txtHorario = findViewById(R.id.txtHorario);
        btnCancel = findViewById(R.id.btnCancel);
        btnInscribirse = findViewById(R.id.btnInscribirse);
    }

    private void loadData(EventoVirtualDTO dto) {
        txtNombreEvento.setText(dto.getNombre());
        txtNombrePonente.setText(dto.getNombrePonente());

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:MM");
        String horario = String.format("Horario: %s", df.format(dto.getFechaHoraInicio()));
        if (dto.getFechaHoraFin() != null) {
            horario += String.format(" - %s", df.format(dto.getFechaHoraFin()));
        }
        txtHorario.setText(horario);
    }

    private void initListeners() {
        LifecycleOwner lifecycleOwner = this;

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnInscribirse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences((Context) lifecycleOwner);
                long personaId = preferences.getLong("personaId", 0);
                if (personaId == 0) {
                    return;
                }

                InscripcionEventoVirtualDTO dto = new InscripcionEventoVirtualDTO();
                dto.setRequiereCertificado(true);
                dto.setPersonaId(personaId);
                dto.setEventoVirtualId(eventoVirtualId);

                viewModel.inscribir(dto).observe(lifecycleOwner, new Observer<GenericResponse<InscripcionEventoVirtual>>() {
                    @Override
                    public void onChanged(GenericResponse<InscripcionEventoVirtual> response) {
                        if (response != null) {
                            Toast.makeText(EventoActivity.this, String.valueOf((int) response.getBody().getId()), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}