package edu.idat.eventosvirtuales.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.HashMap;

import edu.idat.eventosvirtuales.api.GenericResponse;
import edu.idat.eventosvirtuales.repository.UsuarioRepository;

public class LoginViewModel extends AndroidViewModel {
    private UsuarioRepository usuRepo;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        usuRepo = UsuarioRepository.getInstance();
    }

    public LiveData<GenericResponse<HashMap<String, Object>>> auth(String username, String password) {
        return usuRepo.auth(username, password);
    }
}
