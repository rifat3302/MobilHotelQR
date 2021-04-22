package com.example.mobilhotelqr;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //sukru deneme

        String mesaj = getIntent().getStringExtra("mesaj");
        Toast.makeText(this,mesaj,Toast.LENGTH_SHORT).show();
    }
}