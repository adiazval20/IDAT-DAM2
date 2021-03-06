package edu.idat.semana10.api;

import java.util.ArrayList;

import edu.idat.semana10.dto.EventoVirtualDTO;
import edu.idat.semana10.entity.EventoVirtual;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface EventoVirtualApi {
    String path = "/api/evento-virtual/";

    @GET(path + "proximos")
    Call<GenericResponse<ArrayList<EventoVirtualDTO>>> listProximos();

    @GET(path + "pasados")
    Call<GenericResponse<ArrayList<EventoVirtualDTO>>> listPasados();

    @GET(path + "{id}")
    Call<GenericResponse<EventoVirtualDTO>> find(@Path("id") int id);
}
