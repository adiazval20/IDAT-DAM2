package edu.idat.semana10.api;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import edu.idat.semana10.entity.InscripcionEventoVirtual;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConfigApi {
    private static Retrofit retrofit;
    private static UsuarioApi usuarioApi;
    private static EventoVirtualApi eventoVirtualApi;
    private static InscripcionEventoVirtualApi inscripcionEventoVirtualApi;

    static {
        String baseUrl = "http://10.0.2.2:9090"; //IP DESDE EL EMULADOR
//        String baseUrl = "http://192.168.1.109:9090"; //IP DESDE DISPOSITIVO MÓVIL - DEPENDE DE CADA PC
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getClient())
                .build();
    }

    private static OkHttpClient getClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        // Agregando interceptor logging
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.level(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(logging);

        //Agregando interceptor stetho
        StethoInterceptor stetho = new StethoInterceptor();
        builder.addNetworkInterceptor(stetho);

        return builder.build();
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
