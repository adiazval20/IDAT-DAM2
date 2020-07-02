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



        return convertView;
    }

    public void loadData(ArrayList<EventoVirtualDTO> eventoVirtuals) {
        clear();
        addAll(eventoVirtuals);
        notifyDataSetChanged();
    }
}
