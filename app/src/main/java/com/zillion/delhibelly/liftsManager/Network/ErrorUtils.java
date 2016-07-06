package com.zillion.delhibelly.liftsManager.Network;

import com.squareup.okhttp.ResponseBody;
import com.zillion.delhibelly.liftsManager.Network.Models.ApiError;

import java.io.IOException;
import java.lang.annotation.Annotation;

import retrofit.Converter;
import retrofit.Response;
import retrofit.Retrofit;

public class ErrorUtils {

    /**
     * Parses the error message from a failed response
     * @param response the response object after failing
     * @param retrofit the corresponding retrofit object
     * @return returns an ApiError object
     */
    public static ApiError parseError(Response response, Retrofit retrofit) {
        Converter<ResponseBody, ApiError> converter =
                retrofit.responseConverter(ApiError.class, new Annotation[0]);

        ApiError error;

        try {
            error = converter.convert(response.errorBody());
        } catch (IOException e) {
            return new ApiError();
        }

        return error;
    }
}
