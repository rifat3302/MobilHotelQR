package com.example.mobilhotelqr.Services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.mobilhotelqr.Core.RetrofitProcess;
import com.example.mobilhotelqr.PojoModels.LoginUserAfter.LoginUserAfter;
import com.google.gson.Gson;

public class LogoutService extends Service {

    private Handler h ;
    SharedPreferences mPrefs ;
    private RetrofitProcess retrofitProcess;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {

        h  = new Handler();

        //servis 2 dk bir uygulama açıkken çalışıyor
        h.postDelayed(runnable,3000);
        Toast.makeText(this,"Servis çalışamaya başladı",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onDestroy() {
        Toast.makeText(this,"Servis çalışamaya başladı",Toast.LENGTH_SHORT).show();
    }

    private final Runnable  runnable  =  new Runnable() {
        @Override
        public void run() {
            LogoutService.this.h.postDelayed(runnable,3000);
            Toast.makeText(LogoutService.this,"Servis çalışamaya devam ediyor ",Toast.LENGTH_SHORT).show();
        }
    };

    private boolean logoutControl(){
        Gson gson = new Gson();
        mPrefs = getSharedPreferences("MobilHotelInfo", Context.MODE_PRIVATE);
        String json = mPrefs.getString("User", "");
        LoginUserAfter user = gson.fromJson(json,LoginUserAfter.class);

        return false;
    }
}
