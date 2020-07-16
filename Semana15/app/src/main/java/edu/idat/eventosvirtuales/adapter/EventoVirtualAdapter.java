package edu.idat.eventosvirtuales.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import edu.idat.eventosvirtuales.activity.EventoInfoActivity;
import edu.idat.eventosvirtuales.R;
import edu.idat.eventosvirtuales.activity.HomeCommunication;
import edu.idat.eventosvirtuales.dto.EventoVirtualDTO;

public class EventoVirtualAdapter extends ArrayAdapter<EventoVirtualDTO> {
    private HomeCommunication communication;

    public EventoVirtualAdapter(@NonNull Context context, int resource, @NonNull List<EventoVirtualDTO> objects, HomeCommunication communication) {
        super(context, resource, objects);
        this.communication = communication;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_evento, parent, false);
        }

        EventoVirtualDTO dto = getItem(position);

        TextView txtNombre = convertView.findViewById(R.id.txtNombre);
        TextView txtPonente = convertView.findViewById(R.id.txtPonente);
        TextView txtHorario = convertView.findViewById(R.id.txtHorario);
        Button btnInformacion = convertView.findViewById(R.id.btnInformacion);

        txtNombre.setText(dto.getNombre());
        txtPonente.setText(dto.getNombres());

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:MM");
        String horario = String.format("Horario: %s", df.format(dto.getFechaHoraInicio()));
        if (dto.getFechaHoraFin() != null) {
            horario += String.format(" - %s", df.format(dto.getFechaHoraFin()));
        }
        txtHorario.setText(horario);

        btnInformacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), EventoInfoActivity.class);
                intent.putExtra("id", dto.getEventoVirtualId());
                communication.loadActivity(intent);
            }
        });

        return convertView;
    }

    public void loadItems(ArrayList<EventoVirtualDTO> eventoVirtuals) {
        clear();
        addAll(eventoVirtuals);
        notifyDataSetChanged();
    }
}
