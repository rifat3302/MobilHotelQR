package com.example.mobilhotelqr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {


    private BottomNavigationView bottom_navigation;
    private Fragment tempFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        String mesaj = getIntent().getStringExtra("mesaj");


        bottom_navigation=findViewById(R.id.bottom_navigation);
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_tutucu,new fragment_first()).commit();

        bottom_navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.dashboard){
                    Toast.makeText(getApplicationContext(), "dashboard",Toast.LENGTH_SHORT).show();
                    tempFragment=new fragment_first();

                }
                if(item.getItemId()==R.id.order){
                    Toast.makeText(getApplicationContext(), "order",Toast.LENGTH_SHORT).show();
                    tempFragment=new fragment_second();

                }
                if(item.getItemId()==R.id.tripadvisor){
                    Toast.makeText(getApplicationContext(), "tripAdvisor",Toast.LENGTH_SHORT).show();
                    tempFragment=new fragment_third();
                }


                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_tutucu,tempFragment).commit();

                return true;
            }
        });
    }

}