package edu.idat.eventosvirtuales.api;


import edu.idat.eventosvirtuales.entity.InscripcionEventoVirtual;
import edu.idat.eventosvirtuales.dto.InscripcionEventoVirtualDTO;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface InscripcionEventoVirtualApi {
    String prefix = "/api/inscripcion-evento-virtual";

    @POST(prefix)
    Call<GenericResponse<InscripcionEventoVirtual>> inscribir(@Body InscripcionEventoVirtualDTO dto);

    @GET(prefix+"/evento-persona/{eventoVirtualId}/{personaId}")
    Call<GenericResponse<InscripcionEventoVirtualDTO>> findByEventoPersona(@Path("eventoVirtualId") long eventoVirtualId, @Path("personaId") long personaId);
}
