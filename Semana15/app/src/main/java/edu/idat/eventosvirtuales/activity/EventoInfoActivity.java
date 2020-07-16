package edu.idat.eventosvirtuales.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.PreferenceManager;

import android.content.SharedPreferences;
import android.media.ImageReader;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import edu.idat.eventosvirtuales.R;
import edu.idat.eventosvirtuales.api.ConfigApi;
import edu.idat.eventosvirtuales.api.DocumentoAlmacenadoApi;
import edu.idat.eventosvirtuales.api.EventoVirtualApi;
import edu.idat.eventosvirtuales.api.GenericResponse;
import edu.idat.eventosvirtuales.dto.EventoVirtualDTO;
import edu.idat.eventosvirtuales.entity.EventoVirtual;
import edu.idat.eventosvirtuales.entity.InscripcionEventoVirtual;
import edu.idat.eventosvirtuales.dto.InscripcionEventoVirtualDTO;
import edu.idat.eventosvirtuales.viewmodel.EventoInfoViewModel;

public class EventoInfoActivity extends AppCompatActivity {
    private long eventoVirtualId, personaId;
    private EventoInfoViewModel viewModel;
    private TextView txtNombreEvento, txtNombrePonente, txtHorario;
    private ImageView imgBanner;
    private Button btnCancel, btnInscribirse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento_info);

        viewModel = new ViewModelProvider(this).get(EventoInfoViewModel.class);

        initViews();

        Bundle data = getIntent().getExtras();
        eventoVirtualId = data != null ? data.getLong("id") : 0;

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        personaId = preferences.getLong("personaId", 0);

        viewModel.find(eventoVirtualId).observe(this, new Observer<GenericResponse<EventoVirtualDTO>>() {
            @Override
            public void onChanged(GenericResponse<EventoVirtualDTO> response) {
                if (response != null) {
                    loadData(response.getBody());
                }
            }
        });

        viewModel.findByEventoPersona(eventoVirtualId, personaId).observe(this, new Observer<GenericResponse<InscripcionEventoVirtualDTO>>() {
            @Override
            public void onChanged(GenericResponse<InscripcionEventoVirtualDTO> response) {
                if (response != null) {
                    if (response.getBody().getEventoVirtualId() != 0) {
                        btnInscribirse.setVisibility(View.GONE);
                    } else {
                        btnInscribirse.setVisibility(View.VISIBLE);
                    }
                }
            }
        });

        initListeners();
    }

    private void initViews() {
        txtNombreEvento = findViewById(R.id.txtNombreEvento);
        txtNombrePonente = findViewById(R.id.txtNombrePonente);
        txtHorario = findViewById(R.id.txtHorario);
        imgBanner = findViewById(R.id.imgBanner);
        btnCancel = findViewById(R.id.btnCancel);
        btnInscribirse = findViewById(R.id.btnInscribirse);
    }

    private void initListeners() {
        AppCompatActivity context = this;

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnInscribirse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (personaId == 0) {
                    return;
                }

                InscripcionEventoVirtualDTO dto = new InscripcionEventoVirtualDTO();
                dto.setEventoVirtualId(eventoVirtualId);
                dto.setPersonaId(personaId);
                dto.setRequiereCertificado(false);

                viewModel.inscribir(dto).observe(context, new Observer<GenericResponse<InscripcionEventoVirtual>>() {
                    @Override
                    public void onChanged(GenericResponse<InscripcionEventoVirtual> response) {
                        if (response != null) {
                            Toast.makeText(context, "OK", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    private void loadData(EventoVirtualDTO dto) {
        txtNombreEvento.setText(dto.getNombre());
        txtNombrePonente.setText(dto.getNombreCompletoPonente());

        String url = ConfigApi.baseUrl + DocumentoAlmacenadoApi.prefix + "/download/" + dto.getFileNameBanner();
        loadImage(imgBanner, url);
    }

    private void loadImage(ImageView img, String url) {
        Picasso picasso = new Picasso.Builder(img.getContext())
                .downloader(new OkHttp3Downloader(ConfigApi.getClient()))
                .build();

        picasso.load(url).into(img);
    }
}