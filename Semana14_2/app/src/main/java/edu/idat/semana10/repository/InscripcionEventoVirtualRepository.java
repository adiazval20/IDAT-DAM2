package edu.idat.semana10.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import edu.idat.semana10.api.ConfigApi;
import edu.idat.semana10.api.GenericResponse;
import edu.idat.semana10.api.InscripcionEventoVirtualApi;
import edu.idat.semana10.dto.InscripcionEventoVirtualDTO;
import edu.idat.semana10.entity.InscripcionEventoVirtual;
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
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<GenericResponse<InscripcionEventoVirtual>> call, Throwable t) {
                data.setValue(null);
            }
        });


        return data;
    }
}
