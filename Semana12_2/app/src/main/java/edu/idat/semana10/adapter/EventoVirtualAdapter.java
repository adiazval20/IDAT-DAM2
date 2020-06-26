package edu.idat.semana10.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import edu.idat.semana10.entity.EventoVirtual;

public class EventoVirtualAdapter extends ArrayAdapter<EventoVirtual> {
    public EventoVirtualAdapter(@NonNull Context context, int resource, @NonNull List<EventoVirtual> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }

    public void loadData(ArrayList<EventoVirtual> eventoVirtuals) {
        clear();
        addAll(eventoVirtuals);
        notifyDataSetChanged();
    }
}
