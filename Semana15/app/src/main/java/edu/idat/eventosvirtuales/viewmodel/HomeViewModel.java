package edu.idat.eventosvirtuales.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.ArrayList;

import edu.idat.eventosvirtuales.api.GenericResponse;
import edu.idat.eventosvirtuales.dto.EventoVirtualDTO;
import edu.idat.eventosvirtuales.entity.EventoVirtual;
import edu.idat.eventosvirtuales.repository.EventoVirtualRepository;

public class HomeViewModel extends AndroidViewModel {
    private EventoVirtualRepository eviRepo;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        eviRepo = EventoVirtualRepository.getInstance();
    }

    public LiveData<GenericResponse<ArrayList<EventoVirtualDTO>>> listProximos() {
        return eviRepo.listProximos();
    }

    public LiveData<GenericResponse<ArrayList<EventoVirtualDTO>>> listProximosByPersonaInscrita(long personaId) {
        return eviRepo.listProximosByPersonaInscrita(personaId);
    }
}
