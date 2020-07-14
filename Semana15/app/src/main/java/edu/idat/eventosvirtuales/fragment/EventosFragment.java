package edu.idat.eventosvirtuales.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import edu.idat.eventosvirtuales.R;
import edu.idat.eventosvirtuales.activity.HomeCommunication;
import edu.idat.eventosvirtuales.adapter.EventoVirtualAdapter;
import edu.idat.eventosvirtuales.api.GenericResponse;
import edu.idat.eventosvirtuales.dto.EventoVirtualDTO;
import edu.idat.eventosvirtuales.entity.EventoVirtual;
import edu.idat.eventosvirtuales.viewmodel.HomeViewModel;

public class EventosFragment extends Fragment {
    private HomeCommunication communication;
    private HomeViewModel viewModel;
    private EventoVirtualAdapter adapter;
    private ListView lsvEventosProximos;

    public EventosFragment() {
    }

    public EventosFragment(HomeCommunication communication) {
        this.communication = communication;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        return inflater.inflate(R.layout.fragment_eventos, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        lsvEventosProximos = view.findViewById(R.id.lsvEventosProximos);

        adapter = new EventoVirtualAdapter(getContext(), R.layout.item_evento, new ArrayList<EventoVirtualDTO>(), communication);
        lsvEventosProximos.setAdapter(adapter);

        viewModel.listProximos().observe((AppCompatActivity) getContext(), new Observer<GenericResponse<ArrayList<EventoVirtualDTO>>>() {
            @Override
            public void onChanged(GenericResponse<ArrayList<EventoVirtualDTO>> response) {
                if (response != null) {
                    adapter.loadItems(response.getBody());
                }
            }
        });
    }
}