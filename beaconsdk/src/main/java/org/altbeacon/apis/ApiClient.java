package org.altbeacon.apis;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by KaurSarabjot on 1/25/2019.
 */

public class ApiClient {

    private CallRetrofitApi apiServices;
    private static ApiClient appRetrofit;

    public ApiClient() {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.readTimeout(3, TimeUnit.MINUTES)
                .connectTimeout(3, TimeUnit.MINUTES);
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        client.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                // Request customization: add request headers
                Request.Builder requestBuilder = original.newBuilder();
                requestBuilder.addHeader(ApiConstant.ACCEPT, "application/json");
                requestBuilder.addHeader(ApiConstant.CONTENT_TYPE, /*"application/json"*/"application/x-www-form-urlencoded");
                requestBuilder.addHeader(ApiConstant.ACCEPT_LANGUAGE,"en;q=1");   //TODO
                //requestBuilder.addHeader(ApiConstant.USER_AGENT, EmberPreference.readString(AppController.getInstance(),ApiConstant.USER_AGENT,""));
                //requestBuilder.addHeader(ApiConstant.AUTHORIZATION,/*"Basic aW9zX015Q2hhaXJfb2JqYy42RUJBRDVDRTU0NTU0NkRCOEI0NUU3RjFFRDVBOEZBNzpmZWI1M2EwMjgyOTQ0YmNiYTc4ZDkwMDhhNDgyNjljZjc2ZGIyNGE5ZDBiMDRjYWM5N2Q2ZTY="*/
                //        EmberPreference.readString(AppController.getInstance(),AppConstant.AUTHORIZATION_KEY,""));
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        client.interceptors().add(loggingInterceptor);
        Retrofit retrofit =  new Retrofit.Builder()
                .baseUrl(ApiConstant.BASE_URL)
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        apiServices = retrofit.create(CallRetrofitApi.class);
    }

    public CallRetrofitApi getApiServices() {
        return apiServices;
    }

    // static method to get singleton object of AppRetrofit
    public static synchronized ApiClient getAppRetrofitInstance() {
        if (appRetrofit == null) {
            appRetrofit = new ApiClient();
            return appRetrofit;
        } else {
            return appRetrofit;
        }
    }
}
