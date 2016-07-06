package com.zillion.delhibelly.liftsManager.Network;


import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.zillion.delhibelly.liftsManager.Login;
import com.zillion.delhibelly.liftsManager.Network.Models.Listing;
import com.zillion.delhibelly.liftsManager.Network.Models.User;

import java.io.IOException;
import java.util.List;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.Query;

public class ServiceGeneratorMain {

    public static String API_BASE_URL = "http://192.169.3.117:8888/";

    private static OkHttpClient httpClient = new OkHttpClient();
    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());


    public static void changeApiBaseUrl(String newApiBaseUrl) {
        builder.baseUrl(newApiBaseUrl);
    }


    public static <S> S createService(Class<S> serviceClass) {
        String username = Login.email;
        String password = Login.password;
        if (username != null && password != null) {
            httpClient.interceptors().clear();
            httpClient.interceptors().add(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();

                    Request.Builder requestBuilder = original.newBuilder()
                            .method(original.method(), original.body());

                    Request request = requestBuilder.build();
                    return chain.proceed(request);

                }
            });
        }
        Retrofit retrofit = builder.client(httpClient).build();
        return retrofit.create(serviceClass);
    }

    public interface UserClient {
        @FormUrlEncoded
        @POST("/inspector/login")
        Call<User> getToken(
                @Field("email") String username,
                @Field("password") String password
        );

        @GET("/assign")
        Call<List<Listing>> getListing(@Header("x-access-token")
                                       String contentRange,
                                       @Query("assocInspector")
                                       int assocInspector);

    }
}