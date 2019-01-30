package org.altbeacon.presenter;

import org.altbeacon.pojo.response.LoginResponse;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by KaurSarabjot on 1/25/2019.
 */

public interface LoginPresenter {

    void onLoginSuccess(Response<LoginResponse> response);

    void onLoginFailed(Call<LoginResponse> call, String string);
}
