package edu.idat.semana10.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.HashMap;

import edu.idat.semana10.api.GenericResponse;
import edu.idat.semana10.repository.UsuarioRepository;

public class LoginViewModel extends AndroidViewModel {
    private UsuarioRepository usuarioRepository;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        usuarioRepository = UsuarioRepository.getInstance();
    }

    public LiveData<GenericResponse<HashMap<String, Object>>> auth(String username, String password) {
        return usuarioRepository.auth(username, password);
    }
}
