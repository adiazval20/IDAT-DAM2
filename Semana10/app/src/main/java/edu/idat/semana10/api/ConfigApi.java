package edu.idat.semana10.api;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConfigApi {
    private static Retrofit retrofit;

    static {
        String baseUrl = "http://10.0.2.2:9090";
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
}
