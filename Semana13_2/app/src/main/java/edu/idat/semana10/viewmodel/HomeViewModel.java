package edu.idat.semana10.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.ArrayList;

import edu.idat.semana10.api.GenericResponse;
import edu.idat.semana10.entity.EventoVirtual;
import edu.idat.semana10.repository.EventoVirtualRepository;

public class HomeViewModel extends AndroidViewModel {
    private EventoVirtualRepository repository;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        repository = EventoVirtualRepository.getInstance();
    }

    public LiveData<GenericResponse<ArrayList<EventoVirtual>>> listProximos() {
        return repository.listProximos();
    }
}
