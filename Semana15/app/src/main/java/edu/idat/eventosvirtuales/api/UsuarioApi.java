package edu.idat.eventosvirtuales.api;

import java.util.HashMap;

import edu.idat.eventosvirtuales.dto.UsuarioPersonaDTO;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UsuarioApi {
    @FormUrlEncoded
    @POST("/api/usuario/auth")
    Call<GenericResponse<HashMap<String, Object>>> auth(@Field("username") String username, @Field("password") String password);

    @POST("api/usuario/persona")
    Call<GenericResponse> signup(@Body UsuarioPersonaDTO dto);
}
