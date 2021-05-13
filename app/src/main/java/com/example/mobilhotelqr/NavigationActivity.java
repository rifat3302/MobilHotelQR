package com.example.mobilhotelqr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import com.example.mobilhotelqr.Core.ApiUtils;
import com.example.mobilhotelqr.Core.RetrofitProcess;
import com.example.mobilhotelqr.PojoModels.Menu.MenuData;
import com.example.mobilhotelqr.PojoModels.Occupancy.OccupancyData;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;

public class NavigationActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,BottomNavigationView.OnNavigationItemSelectedListener {

    SharedPreferences mPrefs ;

    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private Fragment fragment;
    private BottomNavigationView bottomNavigationView;
    private Context mContext;

    private RetrofitProcess retrofitProcess;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        mPrefs = getSharedPreferences("MobilHotelInfo",MODE_PRIVATE);
        try {
            getAllMenu();
            getAllOccupancy();
        } catch (IOException e) {
            e.printStackTrace();
        }

        navigationView = findViewById(R.id.navigation_view);
        drawerLayout = findViewById(R.id.drawer);
        toolbar = findViewById(R.id.toolbar);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        toolbar.setTitle("Giriş");
        setSupportActionBar(toolbar);


        fragment = new DashboardFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_tutucu,fragment).commit();

        ActionBarDrawerToggle toogle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,
                0,0);

        drawerLayout.addDrawerListener(toogle);
        toogle.syncState();

        View baslik = navigationView.inflateHeaderView(R.layout.navigation_baslik);

        navigationView.setNavigationItemSelectedListener(this);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        //Nav Menu
        if(item.getItemId() == R.id.nav_item_birinci){
            fragment = new FragmentBirinci();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_tutucu,fragment).commit();
            drawerLayout.closeDrawer(GravityCompat.START);
        }

        if(item.getItemId() == R.id.nav_item_ikinci){
            fragment = new FragmentIkinci();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_tutucu,fragment).commit();
            drawerLayout.closeDrawer(GravityCompat.START);
        }

        if(item.getItemId() == R.id.nav_item_ucuncu){
            fragment = new FragmentUcuncu();
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
            fragment = new fragment_third();
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

                Gson gsonn = new Gson();
                String jsonn = mPrefs.getString("Menu", "");
                MenuData obj = gson.fromJson(json, MenuData.class);
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


}