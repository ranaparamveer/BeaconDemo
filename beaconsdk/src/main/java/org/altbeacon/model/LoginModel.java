package org.altbeacon.model;

import android.content.Context;

import org.altbeacon.apis.ApiClient;
import org.altbeacon.pojo.request.LoginRequest;
import org.altbeacon.pojo.response.LoginResponse;
import org.altbeacon.presenter.LoginPresenter;
import org.altbeacon.utils.AppUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by KaurSarabjot on 1/25/2019.
 */

public class LoginModel {

    private LoginPresenter mPresenter;
    private Context mContext;

    public LoginModel(Context mContext, LoginPresenter mPresenter) {
        this.mPresenter = mPresenter;
        this.mContext = mContext;
    }

    public void loginApi(LoginRequest mRequest) {
        //((BaseActivity) mContext).showProgressDialog();
       // EmberPreference.writeString(mContext, AppConstant.AUTHORIZATION_KEY, "");
        AppUtils.generateAuthorizationKey(mContext);
        AppUtils.getInfoAboutDevice(mContext);
        Call<LoginResponse> call = ApiClient.getAppRetrofitInstance().getApiServices().
                loginApi(mRequest.getUsername(), mRequest.getPassword(), mRequest.getGrant_type(), mRequest.getScope());
        AppUtils.enqueueCall(call, new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                //((BaseActivity) mContext).hidProgressDialog();
                if (response.body() != null) {
                    LoginResponse responseBody = response.body();
                    /*if (responseBody != null && !TextUtils.isEmpty(responseBody.getAccess_token())) {
                        EmberPreference.writeString(mContext, AppConstant.AUTHORIZATION_KEY, "Bearer " + responseBody.getAccess_token());
                        EmberPreference.saveLoginResponse(mContext, responseBody);
                    }*/
                    mPresenter.onLoginSuccess(response);
                } /*else if (response.errorBody() != null) {
                    switch (response.code()) {
                        case ApiConstant.ERROR_400:
                            mPresenter.onLoginFailed(call, mContext.getResources().getString(R.string.string_sorry_that_login_is_incorrect));
                            break;
                        case ApiConstant.ERROR_402:
                          *//*  showLoginFailureDialog(mContext.getResources().getString(R.string.string_upgrade_or_disable),
                                    mContext.getResources().getString(R.string.string_another_device_is_already_connected)
                                    ,mContext.getResources().getString(R.string.string_upgrade_account)
                                    , mContext.getResources().getString(R.string.string_dont_change_anything)
                                    ,ApiConstant.ERROR_402);*//*
                            mPresenter.onLoginFailureAction(ApiConstant.ERROR_402);
                            break;

                        case ApiConstant.ERROR_403:
                            showNoLongerActiveDialog("", mContext.getResources().getString(R.string.string_the_account_is_no_longer_active)
                                    , mContext.getResources().getString(R.string.btn_ok)
                                    , "", ApiConstant.ERROR_403);
                            break;

                        case ApiConstant.ERROR_409:
                            mPresenter.onLoginFailed(call, mContext.getResources().getString(R.string.string_sorry_your_account_is_for_different_ember_app));
                            break;
                        case ApiConstant.ERROR_418:
                            showLoginFailureDialog(mContext.getResources().getString(R.string.string_begin_subscription_trail),
                                    mContext.getResources().getString(R.string.string_another_device_is_already_connected_basic_subscription)
                                    , mContext.getResources().getString(R.string.string_start_trail_ok)
                                    , mContext.getResources().getString(R.string.string_start_trial_no_thanks)
                                    , ApiConstant.ERROR_418);
                            break;

                        case ApiConstant.ERROR_999:
                            showTrialAccountDialog(mContext.getResources().getString(R.string.string_your_account_has_expired),
                                    mContext.getResources().getString(R.string.string_your_account_has_expired_description)
                                    , mContext.getResources().getString(R.string.string_go_online_and_select_subscription)
                                    , mContext.getResources().getString(R.string.string_no_thanks)
                                    , ApiConstant.ERROR_999);
                            break;

                        default:
                            mPresenter.onLoginFailed(call, AppController.getInstance().getString(R.string.string_server_error_occurred));
                            break;
                    }
                } else {
                    mPresenter.onLoginFailed(call, AppController.getInstance().getString(R.string.string_server_error_occurred));
                }*/

              /*  else if(response.errorBody() !=null){
                    Gson gson = new GsonBuilder().create();
                    ErrorResponse.LoginErrorResponse pojo = new ErrorResponse.LoginErrorResponse();
                    try {
                        pojo = gson.fromJson(response.errorBody().string(), ErrorResponse.LoginErrorResponse.class);
                        if(pojo !=null)
                            mPresenter.onLoginFailed(call, !TextUtils.isEmpty(pojo.getError_description()) ? pojo.getError_description()
                                    : AppController.getInstance().getString(R.string.string_server_error_occurred));
                    } catch (IOException e) {
                        e.printStackTrace();
                        mPresenter.onLoginFailed(call, AppController.getInstance().getString(R.string.string_server_error_occurred));
                    }
                }else{
                    mPresenter.onLoginFailed(call, AppController.getInstance().getString(R.string.string_server_error_occurred));
                }*/
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                //((BaseActivity) mContext).hidProgressDialog();
                mPresenter.onLoginFailed(call, t.getMessage());
            }
        });
    }
}
