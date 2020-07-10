package edu.idat.semana10.api;

import edu.idat.semana10.dto.InscripcionEventoVirtualDTO;
import edu.idat.semana10.entity.InscripcionEventoVirtual;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface InscripcionEventoVirtualApi {
    String path = "/api/inscripcion-evento-virtual/";

    @POST(path)
    Call<GenericResponse<InscripcionEventoVirtual>> inscribir(@Body InscripcionEventoVirtualDTO dto);
}
