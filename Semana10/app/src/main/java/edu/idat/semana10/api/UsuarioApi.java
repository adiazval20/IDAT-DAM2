package edu.idat.semana10.api;

import java.util.List;

import edu.idat.semana10.entity.Usuario;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UsuarioApi {
    @FormUrlEncoded
    @POST("/api/usuario")
    Call<CustomResponse> auth(@Field("username") String username, @Field("password") String password);
}
