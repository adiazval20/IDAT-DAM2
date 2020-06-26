package edu.idat.semana10.fragment;

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

import java.util.ArrayList;

import edu.idat.semana10.R;
import edu.idat.semana10.api.GenericResponse;
import edu.idat.semana10.entity.EventoVirtual;
import edu.idat.semana10.viewmodel.HomeViewModel;

public class EventosFragment extends Fragment {
    private HomeViewModel viewModel;

    public EventosFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_eventos, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        AppCompatActivity context = (AppCompatActivity) getContext();

        viewModel = new ViewModelProvider(context).get(HomeViewModel.class);

        viewModel.listProximos().observe(context, new Observer<GenericResponse<ArrayList<EventoVirtual>>>() {
            @Override
            public void onChanged(GenericResponse<ArrayList<EventoVirtual>> response) {
                int asd = 0;
            }
        });
    }
}