package edu.idat.eventosvirtuales.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import edu.idat.eventosvirtuales.api.GenericResponse;
import edu.idat.eventosvirtuales.dto.EventoVirtualDTO;
import edu.idat.eventosvirtuales.entity.EventoVirtual;
import edu.idat.eventosvirtuales.entity.InscripcionEventoVirtual;
import edu.idat.eventosvirtuales.dto.InscripcionEventoVirtualDTO;
import edu.idat.eventosvirtuales.repository.EventoVirtualRepository;
import edu.idat.eventosvirtuales.repository.InscripcionEventoVirtualRepository;

public class EventoInfoViewModel extends AndroidViewModel {
    private EventoVirtualRepository eviRepo;
    private InscripcionEventoVirtualRepository inscEviRepo;

    public EventoInfoViewModel(@NonNull Application application) {
        super(application);
        eviRepo = EventoVirtualRepository.getInstance();
        inscEviRepo = InscripcionEventoVirtualRepository.getInstance();
    }

    public LiveData<GenericResponse<EventoVirtualDTO>> find(long id) {
        return eviRepo.find(id);
    }

    public LiveData<GenericResponse<InscripcionEventoVirtual>> inscribir(InscripcionEventoVirtualDTO dto) {
        return inscEviRepo.inscribir(dto);
    }

    public LiveData<GenericResponse<InscripcionEventoVirtualDTO>> findByEventoPersona(long eventoVirtualId, long personaId) {
        return inscEviRepo.findByEventoPersona(eventoVirtualId, personaId);
    }
}
