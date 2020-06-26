package edu.idat.semana10.api;

import androidx.lifecycle.LiveData;

import retrofit2.http.GET;

public interface EventoVirtualApi {
    String path = "/api/evento-virtual/";

    @GET(path + "proximos")
    LiveData<GenericResponse> listProximos();

    @GET(path + "pasados")
    LiveData<GenericResponse> listPasados();
}
