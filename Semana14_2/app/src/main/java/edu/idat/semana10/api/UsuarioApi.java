package edu.idat.semana10.api;

import java.util.HashMap;

import edu.idat.semana10.dto.UsuarioPersonaDTO;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UsuarioApi {
    String path = "/api/usuario/";

    @FormUrlEncoded
    @POST(path + "auth")
    Call<GenericResponse<HashMap<String, Object>>> auth(@Field("username") String username, @Field("password") String password);

    @POST(path + "persona")
    Call<GenericResponse> register(@Body UsuarioPersonaDTO dto);
}
