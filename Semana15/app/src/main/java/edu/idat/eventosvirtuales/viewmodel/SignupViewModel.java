package edu.idat.eventosvirtuales.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import edu.idat.eventosvirtuales.api.GenericResponse;
import edu.idat.eventosvirtuales.dto.UsuarioPersonaDTO;
import edu.idat.eventosvirtuales.repository.UsuarioRepository;

public class SignupViewModel extends AndroidViewModel {
    private UsuarioRepository usuarioRepository;

    public SignupViewModel(@NonNull Application application) {
        super(application);
        usuarioRepository = UsuarioRepository.getInstance();
    }

    public LiveData<GenericResponse> signup(UsuarioPersonaDTO dto) {
        return usuarioRepository.signup(dto);
    }
}
