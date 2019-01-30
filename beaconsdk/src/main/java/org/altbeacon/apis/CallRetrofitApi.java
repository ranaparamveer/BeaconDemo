package org.altbeacon.apis;

import org.altbeacon.pojo.response.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by KaurSarabjot on 1/25/2019.
 */

public interface CallRetrofitApi {

    @FormUrlEncoded
    @POST("/oauth/token")
    Call<LoginResponse> loginApi(@Field("username") String username, @Field("password") String password, @Field("grant_type") String grantType, @Field("scope") String scope);

    @FormUrlEncoded
    @POST("/oauth/token")
    Call<LoginResponse> refreshTokenApi(@Field("grant_type") String grantType, @Field("refresh_token") String refreshToken);
}
