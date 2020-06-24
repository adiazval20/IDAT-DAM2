package edu.idat.semana10.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import edu.idat.semana10.api.GenericResponse;
import edu.idat.semana10.dto.UsuarioPersonaDTO;

public class SignupViewModel extends AndroidViewModel {
    public SignupViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<GenericResponse> register(UsuarioPersonaDTO dto) {
        return null;
    }
}
