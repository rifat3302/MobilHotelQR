package com.example.mobilhotelqr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class NavigationActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,BottomNavigationView.OnNavigationItemSelectedListener {

    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private Fragment fragment;
    private BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

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
            fragment = new fragment_second();
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


}