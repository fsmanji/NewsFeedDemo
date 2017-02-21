package com.example.fsmanji.data.net;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.RxJavaCallAdapterFactory;

import static com.example.fsmanji.data.net.NewsService.BASE_URL;

/**
 * Created by fsmanji on 2/20/17.
 */

public class ServiceFactory {
    private static Retrofit sRetrofit;

    static {
        OkHttpClient httpClient;
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.readTimeout(30, TimeUnit.SECONDS);
        builder.writeTimeout(30, TimeUnit.SECONDS);
        builder.connectTimeout(5, TimeUnit.SECONDS);

        //if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(interceptor);
        //}

        httpClient = builder.build();
        sRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(httpClient).build();
    }

    public static <S> S getService(Class<S> serviceClass) {
        return sRetrofit.create(serviceClass);
    }

    public static NewsService getNewsService() {
        return sRetrofit.create(NewsService.class);
    }
}
