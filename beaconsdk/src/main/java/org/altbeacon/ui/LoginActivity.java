package org.altbeacon.ui;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import org.altbeacon.beaconreference.R;
import org.altbeacon.beaconreference.databinding.ActivityLoginBinding;
import org.altbeacon.model.LoginModel;
import org.altbeacon.pojo.request.LoginRequest;
import org.altbeacon.pojo.response.LoginResponse;
import org.altbeacon.presenter.LoginPresenter;

import retrofit2.Call;
import retrofit2.Response;

public class LoginActivity extends Activity implements View.OnClickListener,LoginPresenter{

    private ActivityLoginBinding mBinding;
    private LoginModel mModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        mModel =  new LoginModel(this, this);
        mBinding.btLogin.setOnClickListener(this);
        Intent i=new Intent(this,ShowAllBeaconActivity.class);
        startActivity(i);
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.btLogin) {
            mModel.loginApi(loginRequest(""));
        }
    }

    private LoginRequest loginRequest(String scope){
        LoginRequest mRequest = new LoginRequest();
        mRequest.setUsername(mBinding.etEmail.getText().toString());
        mRequest.setPassword(mBinding.etPassword.getText().toString());
        mRequest.setGrant_type(getResources().getString(R.string.string_password_small));
        mRequest.setScope(scope);
        return mRequest;
    }

    @Override
    public void onLoginSuccess(Response<LoginResponse> response) {
        Intent startNewActivity = new Intent(this, ShowAllBeaconActivity.class);
        startActivity(startNewActivity);
    }

    @Override
    public void onLoginFailed(Call<LoginResponse> call, String string) {
        Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
    }
}
