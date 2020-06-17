package edu.idat.semana10.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import edu.idat.semana10.api.CustomResponse;
import edu.idat.semana10.repository.UsuarioRepository;

public class MainViewModel extends AndroidViewModel {
    private UsuarioRepository usuarioRepository;

    public MainViewModel(@NonNull Application application) {
        super(application);
        usuarioRepository = UsuarioRepository.getInstance();
    }

    public LiveData<CustomResponse> auth(String username, String password) {
        return usuarioRepository.auth(username, password);
    }
}
