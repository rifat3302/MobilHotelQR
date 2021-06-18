package com.example.mobilhotelqr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.budiyev.android.codescanner.AutoFocusMode;
import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.budiyev.android.codescanner.ErrorCallback;
import com.budiyev.android.codescanner.ScanMode;
import com.example.mobilhotelqr.Core.ApiUtils;
import com.example.mobilhotelqr.Core.LoadingDialog;
import com.example.mobilhotelqr.Core.RetrofitProcess;
import com.example.mobilhotelqr.PojoModels.LoginUserAfter.LoginUserAfter;
import com.example.mobilhotelqr.PojoModels.LoginUserAfter.User;
import com.example.mobilhotelqr.PojoModels.QrPlacesControl;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.zxing.Result;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlacesQrActivity extends AppCompatActivity {

    private static final int CAMERA_REQUEST_CODE = 101 ;
    private CodeScannerView codeScannerView;
    private CodeScanner codeScanner;
    private RetrofitProcess retrofitProcess;
    SharedPreferences mPrefs ;
    private AlertDialog alertDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.places_qr_activity);

        codeScannerView = findViewById(R.id.codeScannerView2);
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
                qrKeyKontrol(result.getText());
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
        //de
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

    private void qrKeyKontrol(String qr_key){

        Gson gson = new Gson();
        mPrefs = getBaseContext().getSharedPreferences("MobilHotelInfo", Context.MODE_PRIVATE);
        String json = mPrefs.getString("User", "");
        LoginUserAfter user = gson.fromJson(json,LoginUserAfter.class);
        retrofitProcess = ApiUtils.qrPlacesControl();
        retrofitProcess.qrPlacesControl(user.getData().getUser().getId(),qr_key).enqueue(new Callback<QrPlacesControl>() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onResponse(Call<QrPlacesControl> call, Response<QrPlacesControl> response) {
                if (response.isSuccessful()) {
                    AlertDialog.Builder ab = new AlertDialog.Builder(PlacesQrActivity.this);
                    LayoutInflater inflater = getLayoutInflater();
                    ab.setView(inflater.inflate(R.layout.save_success,null));
                    ab.setCancelable(false);
                    alertDialog = ab.create();
                    alertDialog.show();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            Intent intent = new Intent(PlacesQrActivity.this,NavigationActivity.class);
                            startActivity(intent);
                            alertDialog.dismiss();
                        }
                    },3000);
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),jObjError.getString("message"),Snackbar.LENGTH_SHORT);
                        snackbar.getView().setBackgroundColor(Color.GRAY);
                        View sView = snackbar.getView();
                        TextView textView = sView.findViewById(com.google.android.material.R.id.snackbar_text);

                        textView.setTextColor(R.color.colorPrimary);
                        snackbar.show();
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<QrPlacesControl> call, Throwable t) {
                int b = 0;
            }
        });

    }
}