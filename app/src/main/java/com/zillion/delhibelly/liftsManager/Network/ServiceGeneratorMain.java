package com.zillion.delhibelly.liftsManager.Network;


import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.zillion.delhibelly.liftsManager.Login;
import com.zillion.delhibelly.liftsManager.Network.Models.Form;
import com.zillion.delhibelly.liftsManager.Network.Models.Listing;
import com.zillion.delhibelly.liftsManager.Network.Models.User;

import java.io.IOException;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Query;

public class ServiceGeneratorMain {

    public static String API_BASE_URL = "http://192.169.4.46:8888/";

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

                    Request.Builder requestBuilder = original
                            .newBuilder()
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
        Call<User> getToken(@Header("access_token") String login_token,
                            @Field("email") String username,
                            @Field("password") String password
        );

        @GET("/inspector/fetchAssignedForms")
        Call<List<Listing>> getListing(@Header("access_token")
                                       String token
        );

        @FormUrlEncoded
        @PUT("inspector/submitReport")
        Call<Form> submitForm(@Header("access_token") String token,
                              @Field("assignId") int assign_id,
                              @Field("completedOn") String date_of_insp,
                              @Field("applicant_name") String name,
                              @Field("applicant_address") String address,
                              @Field("site_address") String site_addr,
                              @Field("is_new_lift") Boolean isNew,
                              @Field("firm_name") String firm_name,
                              @Field("lift_type") String lift_type,
                              @Field("max_person") int max,
                              @Field("num_of_stop") int stops,
                              @Field("depth_of_pit") float depth,
                              @Field("overhead_clearance") float clearance,
                              @Field("height_mc_room") float height,
                              @Field("shaft_width") float shaft_wd,
                              @Field("shaft_height") float shaft_ht,
                              @Field("door_width") float door_wd,
                              @Field("door_height") float door_ht,
                              @Field("other") String other,
                              @Field("remarks") String remarks
        );

        @FormUrlEncoded
        @POST("inspector/schedule")
        Call<Listing> schedule(@Header("access_token") String token,
                               @Field("assignId") int assign_id,
                               @Field("scheduledDate") String scheduledDate
                               );

        @FormUrlEncoded
        @POST("inspector/getForm")
        Call<List<Listing>> getDetails(@Header("access_token") String token,
                               @Field("formId") int form_id
        );
    }
}