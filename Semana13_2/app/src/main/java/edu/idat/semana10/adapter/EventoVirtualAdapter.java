package edu.idat.semana10.adapter;

import android.content.Context;
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

import edu.idat.semana10.R;
import edu.idat.semana10.dto.EventoVirtualDTO;
import edu.idat.semana10.entity.EventoVirtual;

public class EventoVirtualAdapter extends ArrayAdapter<EventoVirtualDTO> {
    private TextView txtNombre, txtPonente, txtHorario;
    private Button btnInformacion;

    public EventoVirtualAdapter(@NonNull Context context, int resource, @NonNull List<EventoVirtualDTO> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_evento_virtual, parent, false);
        }

        EventoVirtualDTO dto = getItem(position);

        txtNombre = convertView.findViewById(R.id.txtNombre);
        txtPonente = convertView.findViewById(R.id.txtPonente);
        txtHorario = convertView.findViewById(R.id.txtHorario);
        btnInformacion = convertView.findViewById(R.id.btnInformacion);

        txtNombre.setText(dto.getNombre());
        txtPonente.setText(dto.getNombrePonente());

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:MM");
        String horario = String.format("Horario: %s", df.format(dto.getFechaHoraInicio()));
        if (dto.getFechaHoraFin() != null) {
            horario += String.format(" - %s", df.format(dto.getFechaHoraFin()));
        }
        txtHorario.setText(horario);

        btnInformacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return convertView;
    }

    public void loadData(ArrayList<EventoVirtualDTO> eventoVirtuals) {
        clear();
        addAll(eventoVirtuals);
        notifyDataSetChanged();
    }
}
