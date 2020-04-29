package edu.idat.semana4.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import edu.idat.semana4.R;
import edu.idat.semana4.entity.SeccionMenu;


public class SeccionMenuAdapter extends ArrayAdapter<SeccionMenu> {
    public SeccionMenuAdapter(@NonNull Context context, int resource, @NonNull List<SeccionMenu> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_seccion_menu, parent, false);
        }

        SeccionMenu seccionMenu = getItem(position);

        ImageView imgSeccionMenu = convertView.findViewById(R.id.imgSeccionMenu);
        TextView txtNombre = convertView.findViewById(R.id.txtNombre);

        imgSeccionMenu.setImageResource(seccionMenu.getImagenId());
        txtNombre.setText(seccionMenu.getNombre());

        return convertView;
    }
}
