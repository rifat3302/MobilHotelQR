package com.example.mobilhotelqr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mukesh.OnOtpCompletionListener;
import com.mukesh.OtpView;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class PrivateKeyActivity extends AppCompatActivity implements  OnOtpCompletionListener {
    private OtpView otpView;
    private String qr_key;

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

        StringRequest istek = new StringRequest(Request.Method.POST, Constant.API_URL + "MHlogin", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Intent intent = new Intent(PrivateKeyActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                NetworkResponse errorRes = error.networkResponse;
                String stringData = "";
                if(errorRes != null && errorRes.data != null){
                    try {
                        stringData = new String(errorRes.data,"UTF-8");

                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
                Toast.makeText(PrivateKeyActivity.this,"Geçersiz kullanıcı adı veya şifre",Toast.LENGTH_SHORT).show();
            }

        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params = new HashMap<>();
                params.put("qr_key",qr_key);
                params.put("private_key",otp);
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> headers = new HashMap<>();
                headers.put("Content-Type", "application/x-www-form-urlencoded");
                headers.put("Accept", "application/json");
                 headers.put("Connection", "keep-alive");
                return headers;
            }
        };
        Volley.newRequestQueue(this).add(istek);


    }
}