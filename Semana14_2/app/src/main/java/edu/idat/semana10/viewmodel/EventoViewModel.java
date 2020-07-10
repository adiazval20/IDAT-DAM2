package edu.idat.semana10.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import edu.idat.semana10.api.GenericResponse;
import edu.idat.semana10.dto.EventoVirtualDTO;
import edu.idat.semana10.dto.InscripcionEventoVirtualDTO;
import edu.idat.semana10.entity.EventoVirtual;
import edu.idat.semana10.entity.InscripcionEventoVirtual;
import edu.idat.semana10.repository.EventoVirtualRepository;
import edu.idat.semana10.repository.InscripcionEventoVirtualRepository;

public class EventoViewModel extends AndroidViewModel {
    private EventoVirtualRepository eveRepo;
    private InscripcionEventoVirtualRepository ievRepo;

    public EventoViewModel(@NonNull Application application) {
        super(application);
        eveRepo = EventoVirtualRepository.getInstance();
        ievRepo = InscripcionEventoVirtualRepository.getInstance();
    }

    public LiveData<GenericResponse<EventoVirtualDTO>> find(long id) {
        return eveRepo.find(id);
    }

    public LiveData<GenericResponse<InscripcionEventoVirtual>> inscribir(InscripcionEventoVirtualDTO dto) {
        return ievRepo.inscribir(dto);
    }
}
