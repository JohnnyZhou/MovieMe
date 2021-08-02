package com.johnnyzhou.movieme.di.module;

import com.johnnyzhou.movieme.data.MovieRepositoryImpl;
import com.johnnyzhou.movieme.network.ApiInterceptor;
import com.johnnyzhou.movieme.network.MovieService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

@Module
public class ApiModule {
    private String baseUrl;

    public ApiModule(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Provides
    @Singleton
    MovieRepositoryImpl provideRestClient(MovieService movieService) {
        return new MovieRepositoryImpl(movieService);
    }

    @Provides
    @Singleton
    MovieService provideMovieService(Retrofit retrofit) {
        return retrofit.create(MovieService.class);
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(
//            Converter.Factory converterFactory,
            OkHttpClient okHttpClient) {

        return new Retrofit.Builder()
                .baseUrl(baseUrl)
//                .addCallAdapterFactory(rxJavaCallAdapterFactory)
//                .addConverterFactory(converterFactory)
                .client(okHttpClient)
                .build();
    }

//    @Provides
//    @Singleton
//    RxJavaCallAdapterFactory provideRxJavaCallAdapterFactory() {
//        return RxJavaCallAdapterFactory.create();
//    }

//    @Provides
//    @Singleton
//    Converter.Factory provideConverterFactory(Gson gson) {
//        return GsonConverterFactory.create(gson);
//    }
//
//    @Provides
//    @Singleton
//    Gson provideGson() {
//        return new GsonBuilder().create();
//    }

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