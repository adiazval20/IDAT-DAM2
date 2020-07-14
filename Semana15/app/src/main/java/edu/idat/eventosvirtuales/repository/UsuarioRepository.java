package edu.idat.eventosvirtuales.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.HashMap;

import edu.idat.eventosvirtuales.api.ConfigApi;
import edu.idat.eventosvirtuales.api.GenericResponse;
import edu.idat.eventosvirtuales.api.UsuarioApi;
import edu.idat.eventosvirtuales.dto.UsuarioPersonaDTO;
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

    public UsuarioRepository() {
        api = ConfigApi.getUsuarioApi();
    }

    public LiveData<GenericResponse<HashMap<String, Object>>> auth(String username, String password) {
        final MutableLiveData<GenericResponse<HashMap<String, Object>>> data = new MutableLiveData<>();

        api.auth(username, password).enqueue(new Callback<GenericResponse<HashMap<String, Object>>>() {
            @Override
            public void onResponse(Call<GenericResponse<HashMap<String, Object>>> call, Response<GenericResponse<HashMap<String, Object>>> response) {
                data.setValue(response.body());

                if (response.body().getRpta() == 1) {
                    HashMap<String, Object> body = (HashMap<String, Object>) response.body().getBody();
                    ConfigApi.setToken((String) body.get("token"));
                }
            }

            @Override
            public void onFailure(Call<GenericResponse<HashMap<String, Object>>> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }

    public LiveData<GenericResponse> signup(UsuarioPersonaDTO dto) {
        MutableLiveData<GenericResponse> data = new MutableLiveData<>();

        api.signup(dto).enqueue(new Callback<GenericResponse>() {
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
