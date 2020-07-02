package edu.idat.semana10.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import edu.idat.semana10.api.GenericResponse;
import edu.idat.semana10.dto.UsuarioPersonaDTO;
import edu.idat.semana10.repository.UsuarioRepository;

public class SignupViewModel extends AndroidViewModel {
    private UsuarioRepository usuRepo;

    public SignupViewModel(@NonNull Application application) {
        super(application);
        usuRepo = UsuarioRepository.getInstance();
    }

    public LiveData<GenericResponse> register(UsuarioPersonaDTO dto) {
        return usuRepo.register(dto);
    }
}
