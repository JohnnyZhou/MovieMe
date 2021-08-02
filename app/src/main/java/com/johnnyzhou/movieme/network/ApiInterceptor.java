package com.johnnyzhou.movieme.network;

import com.johnnyzhou.movieme.util.NetworkUtil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ApiInterceptor implements Interceptor {
    private static final int RETRY_TIMES = 3;

    public ApiInterceptor() {
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        return chain.proceed(chain.request());
    }


//    @Override
//    public Response intercept(Chain chain) throws IOException {
//        Request original = chain.request();
//        Request newRequest = original;
//
//        String hostName = original.url().host();
//
//        Request.Builder builder = original.newBuilder();
//
//        if (hostName.equalsIgnoreCase(NetworkUtil.API_HOST_NAME)) {
//            String newUrl = original.url() + "&api_key=" + NetworkUtil.API_KEY;
//            newRequest = builder.url(newUrl).build();
//        }
//
//        Response response = chain.proceed(newRequest);
//
//        // Handle request retries
//        int retryCount = 0;
//        while (!response.isSuccessful() && retryCount < RETRY_TIMES) {
//            retryCount++;
//            response = chain.proceed(newRequest);
//        }
//
//        return response;
//    }
}