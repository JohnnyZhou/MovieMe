package com.johnnyzhou.movieme.di.module;

//import com.facebook.stetho.okhttp.StethoInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.johnnyzhou.movieme.BuildConfig;
import com.johnnyzhou.movieme.network.ApiInterceptor;
import com.johnnyzhou.movieme.network.MovieApi;
import com.johnnyzhou.movieme.network.MovieService;
import com.johnnyzhou.movieme.network.NetworkManager;
import com.johnnyzhou.movieme.network.NetworkManagerImpl;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.Converter;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

@Module
public class ApiModule {
    private String baseUrl;

    public ApiModule(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Provides
    @Singleton
    NetworkManager provideNetworkManager(MovieApi movieApi) {
        return new NetworkManagerImpl(movieApi);
    }

    @Provides
    @Singleton
    MovieApi provideRestClient(MovieService movieService) {
        return new MovieApi(movieService);
    }

    @Provides
    @Singleton
    MovieService provideMovieService(Retrofit retrofit) {
        return retrofit.create(MovieService.class);
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Converter.Factory converterFactory,
                             OkHttpClient okHttpClient,
                             RxJavaCallAdapterFactory rxJavaCallAdapterFactory) {

        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(rxJavaCallAdapterFactory)
                .addConverterFactory(converterFactory)
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    RxJavaCallAdapterFactory provideRxJavaCallAdapterFactory() {
        return RxJavaCallAdapterFactory.create();
    }

    @Provides
    @Singleton
    Converter.Factory provideConverterFactory(Gson gson) {
        return GsonConverterFactory.create(gson);
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder().create();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(Interceptor interceptor) {
        OkHttpClient okHttpClient = new OkHttpClient();

        okHttpClient.interceptors().add(interceptor);

//        if (BuildConfig.DEBUG)
//            okHttpClient.networkInterceptors().add(new StethoInterceptor());

        return okHttpClient;
    }

    @Provides
    @Singleton
    Interceptor provideInterceptor() {
        return new ApiInterceptor();
    }
}