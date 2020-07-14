package edu.idat.eventosvirtuales.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import edu.idat.eventosvirtuales.api.ConfigApi;
import edu.idat.eventosvirtuales.api.GenericResponse;
import edu.idat.eventosvirtuales.api.InscripcionEventoVirtualApi;
import edu.idat.eventosvirtuales.entity.InscripcionEventoVirtual;
import edu.idat.eventosvirtuales.dto.InscripcionEventoVirtualDTO;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InscripcionEventoVirtualRepository {
    private static InscripcionEventoVirtualRepository repository;
    private InscripcionEventoVirtualApi api;

    public static InscripcionEventoVirtualRepository getInstance() {
        if (repository == null) {
            repository = new InscripcionEventoVirtualRepository();
        }
        return repository;
    }

    private InscripcionEventoVirtualRepository() {
        api = ConfigApi.getInscripcionEventoVirtualApi();
    }

    public LiveData<GenericResponse<InscripcionEventoVirtual>> inscribir(InscripcionEventoVirtualDTO dto) {
        MutableLiveData<GenericResponse<InscripcionEventoVirtual>> data = new MutableLiveData<>();

        api.inscribir(dto).enqueue(new Callback<GenericResponse<InscripcionEventoVirtual>>() {
            @Override
            public void onResponse(Call<GenericResponse<InscripcionEventoVirtual>> call, Response<GenericResponse<InscripcionEventoVirtual>> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<GenericResponse<InscripcionEventoVirtual>> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }

    public LiveData<GenericResponse<InscripcionEventoVirtualDTO>> findByEventoPersona(long eventoVirtualId, long personaId) {
        MutableLiveData<GenericResponse<InscripcionEventoVirtualDTO>> data = new MutableLiveData<>();

        api.findByEventoPersona(eventoVirtualId, personaId).enqueue(new Callback<GenericResponse<InscripcionEventoVirtualDTO>>() {
            @Override
            public void onResponse(Call<GenericResponse<InscripcionEventoVirtualDTO>> call, Response<GenericResponse<InscripcionEventoVirtualDTO>> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<GenericResponse<InscripcionEventoVirtualDTO>> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }
}
