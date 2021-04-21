package com.example.mobilhotelqr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.budiyev.android.codescanner.AutoFocusMode;
import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.budiyev.android.codescanner.ErrorCallback;
import com.budiyev.android.codescanner.ScanMode;
import com.google.zxing.Result;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private static final int CAMERA_REQUEST_CODE = 101 ;
    private CodeScanner codeScanner;

    private TextView textView;

    private CodeScannerView codeScannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        codeScannerView = findViewById(R.id.scanner_view);
       // textView = findViewById(R.id.tv_textview);
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
            public void onDecoded(@NonNull final Result result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                       // Toast.makeText(MainActivity.this,result.getText(),Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this,HomeActivity.class);
                        intent.putExtra("mesaj","Hoşgeldin " +result.getText());
                        startActivity(intent);

                       // textView.setText(result.getText());
                    }

                });

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
}