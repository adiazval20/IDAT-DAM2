package edu.idat.semana10.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

import edu.idat.semana10.api.ConfigApi;
import edu.idat.semana10.api.EventoVirtualApi;
import edu.idat.semana10.api.GenericResponse;
import edu.idat.semana10.entity.EventoVirtual;
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

    public LiveData<GenericResponse<ArrayList<EventoVirtual>>> listProximos() {
        MutableLiveData<GenericResponse<ArrayList<EventoVirtual>>> data = new MutableLiveData<>();

        api.listProximos().enqueue(new Callback<GenericResponse<ArrayList<EventoVirtual>>>() {
            @Override
            public void onResponse(Call<GenericResponse<ArrayList<EventoVirtual>>> call, Response<GenericResponse<ArrayList<EventoVirtual>>> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<GenericResponse<ArrayList<EventoVirtual>>> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }
}
