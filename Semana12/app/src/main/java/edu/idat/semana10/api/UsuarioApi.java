package edu.idat.semana10.api;

import edu.idat.semana10.dto.UsuarioPersonaDTO;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UsuarioApi {
    @FormUrlEncoded
    @POST("/api/usuario/auth")
    Call<GenericResponse> auth(@Field("username") String username, @Field("password") String password);

    @POST("/api/usuario/persona")
    Call<GenericResponse> register(@Body UsuarioPersonaDTO dto);
}
