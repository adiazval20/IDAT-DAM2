package edu.idat.semana10.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import edu.idat.semana10.api.ConfigApi;
import edu.idat.semana10.api.GenericResponse;
import edu.idat.semana10.api.UsuarioApi;
import edu.idat.semana10.dto.UsuarioPersonaDTO;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsuarioRepository {
    private static UsuarioRepository repository;
    private UsuarioApi api;

    public static UsuarioRepository getInstance() {
        if (repository == null) {
            repository = new UsuarioRepository();
        }
        return repository;
    }

    private UsuarioRepository() {
        api = ConfigApi.getUsuarioApi();
    }

    public LiveData<GenericResponse> auth(String username, String password) {
        final MutableLiveData<GenericResponse> data = new MutableLiveData<>();

        api.auth(username, password).enqueue(new Callback<GenericResponse>() {
            @Override
            public void onResponse(Call<GenericResponse> call, Response<GenericResponse> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<GenericResponse> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }

    public LiveData<GenericResponse> register(UsuarioPersonaDTO dto) {
        final MutableLiveData<GenericResponse> data = new MutableLiveData<>();

        api.register(dto).enqueue(new Callback<GenericResponse>() {
            @Override
            public void onResponse(Call<GenericResponse> call, Response<GenericResponse> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<GenericResponse> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }
}
