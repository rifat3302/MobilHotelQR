package com.example.mobilhotelqr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.budiyev.android.codescanner.AutoFocusMode;
import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.budiyev.android.codescanner.ErrorCallback;
import com.budiyev.android.codescanner.ScanMode;
import com.example.mobilhotelqr.Core.ApiUtils;
import com.example.mobilhotelqr.Core.RetrofitProcess;
import com.example.mobilhotelqr.PojoModels.Menu.MenuData;
import com.example.mobilhotelqr.PojoModels.Response.ErrorMessage;
import com.example.mobilhotelqr.PojoModels.Response.ResponseData;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.zxing.Result;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    private static final int CAMERA_REQUEST_CODE = 101 ;
    private CodeScanner codeScanner;
    private Button button;
    private CodeScannerView codeScannerView;
    private RetrofitProcess retrofitProcess;

    private int camereScannerCount =  0;
    //Timer için  gerekli
    timer t = new timer(5000,1000);;
    int i=0;
    boolean onFinishControl = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
       /* button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,NavigationActivity.class);
                startActivity(intent);
            }
        });*/
        codeScannerView = findViewById(R.id.scanner_view);
        setupPermission();
        codeScanner();


    }

    public void codeScanner() {
        //Kullanılan kütüphane = com.budiyev.android:code-scanner:2.1.0'
        codeScanner = new CodeScanner(this, codeScannerView);
        codeScanner.setCamera(CodeScanner.CAMERA_BACK);
        codeScanner.setFormats(CodeScanner.ALL_FORMATS);
        codeScanner.setAutoFocusMode(AutoFocusMode.SAFE);
        codeScanner.setScanMode(ScanMode.CONTINUOUS);
        codeScanner.setAutoFocusEnabled(true);
        codeScanner.setFlashEnabled(false);
        codeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull Result result) {

                JSONObject item = new JSONObject();
                try {
                    item.put("qr_key",result.getText());
                } catch (JSONException e) {
                    e.printStackTrace();
                }


               if(i==0){
                   try {
                       controlQrKey(item);
                       i++;
                   } catch (JSONException e) {
                       e.printStackTrace();
                   }
               }else {
                   if(onFinishControl){
                       t.start();
                   }

               }


            }
        });
        codeScanner.setErrorCallback(new ErrorCallback() {
            @Override
            public void onError(@NonNull Exception error) {
                Log.e("Main","Camera error: "+error.getMessage());
            }
        });

        codeScannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                codeScanner.startPreview();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        codeScanner.startPreview();
    }

    @Override
    protected void onPause() {
        codeScanner.releaseResources();
        super.onPause();
    }

    private void setupPermission(){
        int permission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);

        if(permission!= PackageManager.PERMISSION_GRANTED){
            makeRequest();
        }

    }

    private void makeRequest(){

        String[] arr ={Manifest.permission.CAMERA};
        ActivityCompat.requestPermissions(this,arr,CAMERA_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode){

            case CAMERA_REQUEST_CODE:
                if(grantResults.length==0 || grantResults[0] != PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this,"You Need to camera permission to be able to use this app!", Toast.LENGTH_SHORT).show();
                }
                   break;
            default:
                break;

        }

    }

    public void controlQrKey(JSONObject qrKey) throws JSONException {

        retrofitProcess = ApiUtils.qrControl();
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),(qrKey).toString());
        retrofitProcess.qrControl(body).enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                int a =0;
                if(response.body()==null){

                    Gson gson = new Gson();
                    ErrorMessage message = gson.fromJson(response.errorBody().charStream(),ErrorMessage.class);

                    Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),message.getMessage(),Snackbar.LENGTH_SHORT);
                    snackbar.getView().setBackgroundColor(Color.GRAY);
                    snackbar.show();
                }
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                int b =0;
            }
        });


    }

    class timer extends CountDownTimer{
        public timer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            i++;
            onFinishControl=false;
        }

        @Override
        public void onFinish() {
            onFinishControl=true;
            i=0;
        }
    }
}

