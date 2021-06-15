package com.example.mobilhotelqr.Services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.mobilhotelqr.Core.ApiUtils;
import com.example.mobilhotelqr.Core.RetrofitProcess;
import com.example.mobilhotelqr.MainActivity;
import com.example.mobilhotelqr.NavigationActivity;
import com.example.mobilhotelqr.PojoModels.LoginUserAfter.LoginUserAfter;
import com.example.mobilhotelqr.PojoModels.LogoutControl;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        h.postDelayed(runnable,10000);
        logoutControl();

    }

    @Override
    public void onDestroy() {

    }

    private final Runnable  runnable  =  new Runnable() {
        @Override
        public void run() {
            LogoutService.this.h.postDelayed(runnable,10000);
            if(!logoutControl()){
                LogoutService.this.h.removeMessages(0);
            }
             }
    };

    @Override
    public boolean stopService(Intent name) {
        return super.stopService(name);
    }

    private boolean logoutControl(){
        Gson gson = new Gson();
        mPrefs = getSharedPreferences("MobilHotelInfo", Context.MODE_PRIVATE);
        String json = mPrefs.getString("User", "");
        if(json.equals("[]" )|| json.equals("")){
            return false;
        }
        LoginUserAfter user = gson.fromJson(json,LoginUserAfter.class);

        retrofitProcess = ApiUtils.loginAfterPrivateKey();
        retrofitProcess.logoutControlServiceForMobile(user.getData().getUser().getId()).enqueue(new Callback<LogoutControl>() {
            @Override
            public void onResponse(Call<LogoutControl> call, Response<LogoutControl> response) {
                if(response.body().getMessage().equals("fail")){
                    mPrefs = getSharedPreferences("MobilHotelInfo", Context.MODE_PRIVATE);
                    mPrefs.edit().remove("User").commit();
                    Intent intent =  new Intent(getBaseContext(), MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    Intent  intentService = new Intent(getBaseContext(),LogoutService.class);
                    stopService(intentService);
                    LogoutService.this.h.removeMessages(0);
                    startActivity(intent);

                }
            }

            @Override
            public void onFailure(Call<LogoutControl> call, Throwable t) {

               //Todo şimdilik hataları yakalmmıyoruz

            }
        });

        return true;
    }
}
