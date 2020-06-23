package edu.idat.semana10.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import edu.idat.semana10.api.ConfigApi;
import edu.idat.semana10.api.CustomResponse;
import edu.idat.semana10.api.UsuarioApi;
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

    public LiveData<CustomResponse> auth(String username, String password) {
        final MutableLiveData<CustomResponse> data = new MutableLiveData<>();

        api.auth(username, password).enqueue(new Callback<CustomResponse>() {
            @Override
            public void onResponse(Call<CustomResponse> call, Response<CustomResponse> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<CustomResponse> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }
}
