package com.upiita.mechaponicsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class InitActivity extends AppCompatActivity {

    FirstFragment firstFragment = new FirstFragment();
    SecondFragment secondFragment = new SecondFragment();
    ThirdFragment thirdFragment = new ThirdFragment();
    FourthFragment fourthFragment = new FourthFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);

        BottomNavigationView navigationView = findViewById(R.id.bottom_nav);

        getSupportFragmentManager().beginTransaction().replace(R.id.fContainer,firstFragment).commit();

        navigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                switch (item.getItemId()){
                    case R.id.firstFragment:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fContainer, firstFragment).commit();
                        return true;
                    case R.id.secondFragment:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fContainer, secondFragment).commit();
                        return true;
                    case R.id.thirdFragment:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fContainer, thirdFragment).commit();
                        return true;
                    case R.id.fourthFragment:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fContainer, fourthFragment).commit();
                        return true;
                }
                return false;
            }
        });
    }
}