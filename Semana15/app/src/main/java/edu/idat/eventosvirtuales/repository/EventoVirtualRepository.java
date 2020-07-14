package edu.idat.eventosvirtuales.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

import edu.idat.eventosvirtuales.api.ConfigApi;
import edu.idat.eventosvirtuales.api.GenericResponse;
import edu.idat.eventosvirtuales.api.EventoVirtualApi;
import edu.idat.eventosvirtuales.dto.EventoVirtualDTO;
import edu.idat.eventosvirtuales.entity.EventoVirtual;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventoVirtualRepository {
    private static EventoVirtualRepository repository;
    private EventoVirtualApi api;

    public static EventoVirtualRepository getInstance() {
        if (repository == null) {
            repository = new EventoVirtualRepository();
        }
        return repository;
    }

    private EventoVirtualRepository() {
        api = ConfigApi.getEventoVirtualApi();
    }

    public LiveData<GenericResponse<ArrayList<EventoVirtualDTO>>> listProximos() {
        MutableLiveData<GenericResponse<ArrayList<EventoVirtualDTO>>> data = new MutableLiveData<>();

        api.listProximos().enqueue(new Callback<GenericResponse<ArrayList<EventoVirtualDTO>>>() {
            @Override
            public void onResponse(Call<GenericResponse<ArrayList<EventoVirtualDTO>>> call, Response<GenericResponse<ArrayList<EventoVirtualDTO>>> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<GenericResponse<ArrayList<EventoVirtualDTO>>> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }

    public LiveData<GenericResponse<EventoVirtualDTO>> find(long id) {
        MutableLiveData<GenericResponse<EventoVirtualDTO>> data = new MutableLiveData<>();

        api.find(id).enqueue(new Callback<GenericResponse<EventoVirtualDTO>>() {
            @Override
            public void onResponse(Call<GenericResponse<EventoVirtualDTO>> call, Response<GenericResponse<EventoVirtualDTO>> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<GenericResponse<EventoVirtualDTO>> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }
}
