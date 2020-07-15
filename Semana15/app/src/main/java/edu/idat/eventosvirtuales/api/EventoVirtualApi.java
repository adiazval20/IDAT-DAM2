package edu.idat.eventosvirtuales.api;

import java.util.ArrayList;
import java.util.List;

import edu.idat.eventosvirtuales.dto.EventoVirtualDTO;
import edu.idat.eventosvirtuales.entity.EventoVirtual;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface EventoVirtualApi {
    String prefix = "/api/evento-virtual";

    @GET(prefix + "/proximos")
    Call<GenericResponse<ArrayList<EventoVirtualDTO>>> listProximos();

    @GET(prefix + "/{id}")
    Call<GenericResponse<EventoVirtualDTO>> find(@Path("id") long id);

    @GET(prefix + "/inscripcion-persona/proximos/{personaId}")
    Call<GenericResponse<ArrayList<EventoVirtualDTO>>> listProximosByPersonaInscrita(@Path("personaId") long personaId);
}
