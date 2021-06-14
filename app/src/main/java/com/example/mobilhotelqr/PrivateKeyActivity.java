package com.example.mobilhotelqr;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import com.example.mobilhotelqr.Core.ApiUtils;
import com.example.mobilhotelqr.Core.LoadingDialog;
import com.example.mobilhotelqr.Core.RetrofitProcess;
import com.example.mobilhotelqr.PojoModels.LoginUserAfter.LoginUserAfter;
import com.example.mobilhotelqr.PojoModels.Occupancy.OccupancyData;
import com.google.gson.Gson;
import com.mukesh.OnOtpCompletionListener;
import com.mukesh.OtpView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PrivateKeyActivity extends AppCompatActivity implements  OnOtpCompletionListener {
    private OtpView otpView;
    private String qr_key;
    private RetrofitProcess retrofitProcess;
    SharedPreferences mPrefs ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_private_key);
        qr_key = getIntent().getStringExtra("qr_key");
        initializeUi();
        setListeners();
    }


    private void initializeUi() {
        otpView = findViewById(R.id.otp_view);

    }

    private void setListeners() {
        otpView.setOtpCompletionListener(this);
    }

    @Override
    public void onOtpCompleted(String otp) {
        login(otp);

    }

    public void login (final String otp){

        retrofitProcess = ApiUtils.loginAfterPrivateKey();
        retrofitProcess.loginAfterPrivateKey(qr_key,otp).enqueue(new Callback<LoginUserAfter>() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onResponse(Call<LoginUserAfter> call, Response<LoginUserAfter> response) {
                if(response.body().getData()==null){
                    LoadingDialog loadingDialog = new LoadingDialog(PrivateKeyActivity.this);
                    loadingDialog.privateNoSuccess(response.body().getMessage());
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                                loadingDialog.dismissDialog();
                        }
                    },3000);
                }
                if(response.body().getData()!=null){
                    
                    mPrefs = getSharedPreferences("MobilHotelInfo", Context.MODE_PRIVATE);
                    LoginUserAfter mData = response.body();
                    SharedPreferences.Editor prefsEditor = mPrefs.edit();
                    Gson gson = new Gson();
                    String json = gson.toJson(mData);
                    prefsEditor.putString("User", json);
                    prefsEditor.commit();
                    Intent  intent = new Intent(PrivateKeyActivity.this,NavigationActivity.class);
                    startActivity(intent);
                }else{
                    LoadingDialog loadingDialog = new LoadingDialog(PrivateKeyActivity.this);
                    loadingDialog.privateNoSuccess("LoginUser fetching error data");
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            loadingDialog.dismissDialog();
                        }
                    },3000);
                }

            }

            @Override
            public void onFailure(Call<LoginUserAfter> call, Throwable t) {
                LoadingDialog loadingDialog = new LoadingDialog(PrivateKeyActivity.this);
                loadingDialog.privateNoSuccess("LoginUser fetching error data (problem is network)");
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadingDialog.dismissDialog();
                    }
                },3000);
            }
        });

    }
}