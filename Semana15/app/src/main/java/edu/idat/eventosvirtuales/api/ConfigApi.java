package edu.idat.eventosvirtuales.api;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.bind.util.ISO8601Utils;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Date;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConfigApi {
    public static String baseUrl = "http://10.0.2.2:9090"; //"http://192.168.1.109:9090";
    private static Retrofit retrofit;
    private static String token = "";

    private static UsuarioApi usuarioApi;
    private static EventoVirtualApi eventoVirtualApi;
    private static InscripcionEventoVirtualApi inscripcionEventoVirtualApi;

    static {
        initClient();
    }

    private static void initClient() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(getClient())
                .build();
    }

    public static OkHttpClient getClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.level(HttpLoggingInterceptor.Level.BODY);

        StethoInterceptor stetho = new StethoInterceptor();

        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        Interceptor authInterceptor = new Interceptor() {
            @NotNull
            @Override
            public Response intercept(@NotNull Chain chain) throws IOException {
                Request request = chain.request().newBuilder().addHeader("Authorization", "Bearer " + token).build();
                return chain.proceed(request);
            }
        };

        builder.addInterceptor(logging)
                .addInterceptor(authInterceptor)
                .addNetworkInterceptor(stetho);

        return builder.build();
    }

    public static void setToken(String value) {
        token = value;
        initClient();
    }

    public static UsuarioApi getUsuarioApi() {
        if (usuarioApi == null) {
            usuarioApi = retrofit.create(UsuarioApi.class);
        }
        return usuarioApi;
    }

    public static EventoVirtualApi getEventoVirtualApi() {
        if (eventoVirtualApi == null) {
            eventoVirtualApi = retrofit.create(EventoVirtualApi.class);
        }
        return eventoVirtualApi;
    }

    public static InscripcionEventoVirtualApi getInscripcionEventoVirtualApi() {
        if (inscripcionEventoVirtualApi == null) {
            inscripcionEventoVirtualApi = retrofit.create(InscripcionEventoVirtualApi.class);
        }
        return inscripcionEventoVirtualApi;
    }
}
