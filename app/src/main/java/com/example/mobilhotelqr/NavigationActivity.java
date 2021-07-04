package com.example.mobilhotelqr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.mobilhotelqr.Core.ApiUtils;
import com.example.mobilhotelqr.Core.RetrofitProcess;
import com.example.mobilhotelqr.Fragment.FragmentAlarm;
import com.example.mobilhotelqr.Fragment.FragmentBank;
import com.example.mobilhotelqr.Fragment.FragmentHospital;
import com.example.mobilhotelqr.Fragment.FragmentLastOrder;
import com.example.mobilhotelqr.Fragment.FragmentOrderHistory;
import com.example.mobilhotelqr.Fragment.FragmentPharmacy;
import com.example.mobilhotelqr.Fragment.FragmentTaxi;
import com.example.mobilhotelqr.Fragment.FragmentTripAdvisor;
import com.example.mobilhotelqr.PojoModels.GooglePlaces.GooglePlaces;
import com.example.mobilhotelqr.PojoModels.LoginUserAfter.LoginUserAfter;
import com.example.mobilhotelqr.PojoModels.Menu.MenuData;
import com.example.mobilhotelqr.PojoModels.Occupancy.OccupancyData;
import com.example.mobilhotelqr.Services.LogoutService;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NavigationActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,BottomNavigationView.OnNavigationItemSelectedListener {



    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private Fragment fragment;
    private BottomNavigationView bottomNavigationView;
    private Context mContext;
    private ImageView imageViewSepet;
    private TextView kisiAdinNavigation;

    SharedPreferences mPrefs ;
    private RetrofitProcess retrofitProcess;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        mPrefs = getSharedPreferences("MobilHotelInfo",MODE_PRIVATE);

        /**
         * Logout bu navigation activity  çalıştığı  sürece kontrol ediliyor.
         */
        //Todo burayı açmayı unutma
        /*
        stopService(new Intent(NavigationActivity.this, LogoutService.class));
        startService(new Intent(NavigationActivity.this, LogoutService.class));*/

       // mPrefs.edit().remove("GooglePlaces").commit();


        try {
            getAllMenu();
            getAllOccupancy();
            setGooglePlaces();
        } catch (IOException e) {
            e.printStackTrace();
        }

        navigationView = findViewById(R.id.navigation_view);
        drawerLayout = findViewById(R.id.drawer);
        toolbar = findViewById(R.id.toolbar);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        imageViewSepet = findViewById(R.id.imageViewSepet);
        imageViewSepet.setImageResource(R.drawable.food_serving);
        imageViewSepet.setOnClickListener(new View.OnClickListener() {


            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {

                mPrefs = getBaseContext().getSharedPreferences("MobilHotelInfo",Context.MODE_PRIVATE);
                Gson gson = new Gson();
                String json = mPrefs.getString("Order", "");

                if(json=="" || json.contains("[]")){
                    Snackbar snackbar = Snackbar.make(view,"Your cart is empty!",Snackbar.LENGTH_SHORT);
                    snackbar.getView().setBackgroundColor(Color.GRAY);

                    View sView = snackbar.getView();
                    TextView textView = sView.findViewById(com.google.android.material.R.id.snackbar_text);

                    textView.setTextColor(R.color.colorPrimary);
                    snackbar.show();

                }else{
                    fragment = new FragmentLastOrder();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_tutucu,fragment).commit();
                    drawerLayout.closeDrawer(GravityCompat.START);


                }

            }
        });

        toolbar.setTitle("MobilHotel");
        setSupportActionBar(toolbar);


        fragment = new DashboardFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_tutucu,fragment).commit();

        ActionBarDrawerToggle toogle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,
                0,0);

        drawerLayout.addDrawerListener(toogle);
        toogle.syncState();


        Gson gson = new Gson();
        mPrefs = getSharedPreferences("MobilHotelInfo",Context.MODE_PRIVATE);
        String json = mPrefs.getString("User", "");
        LoginUserAfter user = gson.fromJson(json,LoginUserAfter.class);

        View baslik = navigationView.inflateHeaderView(R.layout.navigation_baslik);
        kisiAdinNavigation = baslik.findViewById(R.id.textView11);
        //Todo burayı açmayı unutma
        //kisiAdinNavigation.setText(user.getData().getUser().getName()+" "+user.getData().getUser().getSurname());
        kisiAdinNavigation.setText("Username_UserSurname");


        navigationView.setNavigationItemSelectedListener(this);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        //Nav Menu
        if(item.getItemId() == R.id.nav_item_user_info){
            fragment = new FragmentBirinci();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_tutucu,fragment).commit();
            drawerLayout.closeDrawer(GravityCompat.START);
        }

        if(item.getItemId() == R.id.nav_item_room_info){
            fragment = new FragmentIkinci();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_tutucu,fragment).commit();
            drawerLayout.closeDrawer(GravityCompat.START);
        }

        if(item.getItemId() == R.id.nav_item_call_taxi){
            fragment = new FragmentTaxi();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_tutucu,fragment).commit();
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        if(item.getItemId() == R.id.nav_item_order_history){
            fragment = new FragmentOrderHistory();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_tutucu,fragment).commit();
            drawerLayout.closeDrawer(GravityCompat.START);
        }

        //bottom menu
        if(item.getItemId() == R.id.home){
            fragment = new DashboardFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_tutucu,fragment).commit();
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        if(item.getItemId() == R.id.order){
            fragment = new OrderFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_tutucu,fragment).commit();
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        if(item.getItemId() == R.id.tripadvisor){
            fragment = new FragmentTripAdvisor();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_tutucu,fragment).commit();
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        if(item.getItemId() == R.id.nav_item_bank){
            fragment = new FragmentBank();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_tutucu,fragment).commit();
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        if(item.getItemId() == R.id.nav_item_hospital){
            fragment = new FragmentHospital();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_tutucu,fragment).commit();
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        if(item.getItemId() == R.id.nav_item_ph){
            fragment = new FragmentPharmacy();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_tutucu,fragment).commit();
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        if(item.getItemId() == R.id.nav_item_places){
           Intent intent = new Intent(NavigationActivity.this,PlacesQrActivity.class);
           startActivity(intent);
        }
        if(item.getItemId() == R.id.nav_item_alarm){
            fragment = new FragmentAlarm(NavigationActivity.this);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_tutucu,fragment).commit();
            drawerLayout.closeDrawer(GravityCompat.START);
        }

        return false;
    }

    @Override
    public void onBackPressed() {

        if(drawerLayout.isDrawerOpen(GravityCompat.START)){

            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }

    public void getAllMenu() throws IOException {
        retrofitProcess = ApiUtils.getAllMenu();
        retrofitProcess.getAllMenu().enqueue(new Callback<MenuData>() {
            @Override
            public void onResponse(Call<MenuData> call, retrofit2.Response<MenuData> response) {


                MenuData menuData = response.body();
                SharedPreferences.Editor prefsEditor = mPrefs.edit();
                Gson gson = new Gson();
                String json = gson.toJson(menuData);
                prefsEditor.putString("Menu", json);
                prefsEditor.commit();

            }

            @Override
            public void onFailure(Call<MenuData> call, Throwable t) {

                Toast.makeText(mContext,"The menus could not be pulled",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getAllOccupancy() throws IOException {
        retrofitProcess = ApiUtils.getOccupancy();
        retrofitProcess.allDashboarOccupancy().enqueue(new Callback<OccupancyData>() {
            @Override
            public void onResponse(Call<OccupancyData> call, retrofit2.Response<OccupancyData> response) {


                OccupancyData mData = response.body();
                SharedPreferences.Editor prefsEditor = mPrefs.edit();
                Gson gson = new Gson();
                String json = gson.toJson(mData);
                prefsEditor.putString("Occupancy", json);
                prefsEditor.commit();


            }

            @Override
            public void onFailure(Call<OccupancyData> call, Throwable t) {

                Toast.makeText(mContext,"The Occupancy could not be pulled",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setGooglePlaces(){

        mPrefs = getSharedPreferences("MobilHotelInfo", Context.MODE_PRIVATE);
        String json = mPrefs.getString("GooglePlaces", "");
        if( json.equals("") || json.equals("[]") || json == null || json.equals("{}")){
            retrofitProcess = ApiUtils.getGooglePlaces();
            retrofitProcess.getGooglePlaces().enqueue(new Callback<GooglePlaces>() {
                @Override
                public void onResponse(Call<GooglePlaces> call, Response<GooglePlaces> response) {

                    GooglePlaces placesResult = response.body();
                    SharedPreferences.Editor prefsEditor = mPrefs.edit();
                    Gson gson = new Gson();
                    String json = gson.toJson(placesResult);
                    prefsEditor.putString("GooglePlaces", json);
                    prefsEditor.commit();
                }

                @Override
                public void onFailure(Call<GooglePlaces> call, Throwable t) {

                }
            });
        }

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(new Intent(NavigationActivity.this, LogoutService.class));
    }
}