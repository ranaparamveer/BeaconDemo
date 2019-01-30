package org.altbeacon.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;

import org.altbeacon.apis.ApiClient;
import org.altbeacon.apis.ApiConstant;
import org.altbeacon.beaconreference.R;
import org.altbeacon.pojo.request.LoginRequest;
import org.altbeacon.pojo.response.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by KaurSarabjot on 1/25/2019.
 */

public class AppUtils {

    private static Context mContext;

    public AppUtils(Context mContext) {
        this.mContext = mContext;
    }

    //T extends Comparable<? super T
    public static <T> void enqueueCall(final Call<T> call, final Callback<T> callback) {
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                Log.e("Response param", response.raw().request().url().toString());
                if (response.body() == null && response.code() != ApiConstant.STATUS_200)
                    try {
                        if (response.code() == ApiConstant.ERROR_401) {
                            hitRefreshTokenApi(mContext, refreshTokenRequest());
                        }

                        if (response.errorBody() != null)
                            callback.onResponse(call, response);
                        //else
                            //callback.onFailure(call, new Throwable(AppController.getInstance().getString(R.string.string_server_error_occurred)));
                    } catch (NullPointerException e) {
                        //callback.onFailure(call, new Throwable(AppController.getInstance().getString(R.string.string_server_error_occurred)));
                        e.printStackTrace();
                    }
                else {
                    callback.onResponse(call, response);
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                t.printStackTrace();
                Log.e("Response-onLoginFailed", t.toString());
                callback.onFailure(call, t);
            }
        });
    }

    public static void hitRefreshTokenApi(Context mContext, LoginRequest mRequest) {
       /* if (EmberPreference.readBoolean(mContext, AppConstant.IS_API_RUNNING, false)) {
            EmberPreference.writeBoolean(mContext, AppConstant.IS_API_RUNNING, true);
            return;
        }*/
        //EmberPreference.writeString(mContext, AppConstant.AUTHORIZATION_KEY, "");
        AppUtils.generateAuthorizationKey(mContext);
        AppUtils.getInfoAboutDevice(mContext);
        Call<LoginResponse> call = ApiClient.getAppRetrofitInstance().getApiServices().
                refreshTokenApi(mRequest.getGrant_type(), mRequest.getRefresh_token());
        AppUtils.enqueueCall(call, new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.body() != null) {
                    LoginResponse responseBody = response.body();
                    if (responseBody != null && !TextUtils.isEmpty(responseBody.getAccess_token())) {
                        //EmberPreference.writeString(mContext, AppConstant.AUTHORIZATION_KEY, "");

                       // EmberPreference.writeString(AppUtils.mContext, AppConstant.AUTHORIZATION_KEY, "Bearer " + responseBody.getAccess_token());
                        //EmberPreference.saveLoginResponse(mContext, responseBody);
                    }
                } else {

                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
            }
        });
    }

    public static LoginRequest refreshTokenRequest() {
        LoginRequest mRequest = new LoginRequest();
        mRequest.setGrant_type("refresh_token");
        //mRequest.setRefresh_token(Utility.isEmpty(EmberPreference.readLoginResponse(mContext).getRefresh_token()));
        return mRequest;
    }

    public static String generateDeviceId(Context mContext) {
        return Settings.Secure.getString(mContext.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public static void generateAuthorizationKey(Context mContext) {
        //if (!EmberPreference.readString(mContext, AppConstant.AUTHORIZATION_KEY, "").isEmpty())
         //   return;
        String deviceId = generateDeviceId(mContext);
        StringBuilder mStringBuilder = new StringBuilder(AppConstant.CLIENT_ID + ".");
        mStringBuilder.append(deviceId + ":");
        mStringBuilder.append(AppConstant.SECRECT_ID);
        String authKey = Base64.encodeToString(mStringBuilder.toString().getBytes(), Base64.NO_WRAP);
        mStringBuilder.delete(0, mStringBuilder.length());
        mStringBuilder.append("Basic ");
        mStringBuilder.append(authKey);
        //EmberPreference.writeString(mContext, AppConstant.AUTHORIZATION_KEY, mStringBuilder.toString());
    }

    public static void getInfoAboutDevice(Context mContext) {
       // if (!EmberPreference.readString(mContext, ApiConstant.USER_AGENT, "").isEmpty())
        //    return;
        /*
         * String Format
         * "MyChair/2.9.4 (iPhone; iOS 10.3.1; Scale/2.00)";
         *
         * */
        StringBuilder mStringBuilder = new StringBuilder(mContext.getResources().getString(R.string.app_name) + "/");
        try {
            PackageInfo pInfo = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), PackageManager.GET_META_DATA);
            // userAgent += "\n APP Package Name: " + mContext.getPackageName();
            mStringBuilder.append(pInfo.versionName + " " + " (Android; " + "OS Version " + Build.VERSION.RELEASE + "; " + "Scale/");
            mStringBuilder.append(getDeviceDensity(mContext));
            //EmberPreference.writeString(mContext, ApiConstant.USER_AGENT, mStringBuilder.toString());
        } catch (PackageManager.NameNotFoundException e) {
        }
    }

    private static String getDeviceDensity(Context context) {
        String deviceDensity;
        switch (context.getResources().getDisplayMetrics().densityDpi) {
            case DisplayMetrics.DENSITY_LOW:
                deviceDensity = 0.75 + " ldpi";
                break;
            case DisplayMetrics.DENSITY_MEDIUM:
                deviceDensity = 1.0 + " mdpi";
                break;
            case DisplayMetrics.DENSITY_HIGH:
                deviceDensity = 1.5 + " hdpi";
                break;
            case DisplayMetrics.DENSITY_XHIGH:
                deviceDensity = 2.0 + " xhdpi";
                break;
            case DisplayMetrics.DENSITY_XXHIGH:
                deviceDensity = 3.0 + " xxhdpi";
                break;
            case DisplayMetrics.DENSITY_XXXHIGH:
                deviceDensity = 4.0 + " xxxhdpi";
                break;
            default:
                deviceDensity = "Not found";
        }
        return deviceDensity;
    }
}
