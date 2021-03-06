package edu.idat.semana10.api;

import java.util.ArrayList;

import edu.idat.semana10.entity.EventoVirtual;
import retrofit2.Call;
import retrofit2.http.GET;

public interface EventoVirtualApi {
    String path = "/api/evento-virtual/";

    @GET(path + "proximos")
    Call<GenericResponse<ArrayList<EventoVirtual>>> listProximos();

    @GET(path + "pasados")
    Call<GenericResponse<ArrayList<EventoVirtual>>> listPasados();
}
