package com.upiita.mechaponicsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class InitActivity extends AppCompatActivity {

    // Fragmentos del bottom navigation bar
    FirstFragment firstFragment = new FirstFragment();
    SecondFragment secondFragment = new SecondFragment();
    ThirdFragment thirdFragment = new ThirdFragment();
    FourthFragment fourthFragment = new FourthFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);

        // Objeto del bottom navigation bar realizado
        BottomNavigationView navigationView = findViewById(R.id.bottom_nav);

        // Inicializar el primer fragmento en activity incial
        getSupportFragmentManager().beginTransaction().replace(R.id.fContainer,firstFragment).commit();
        // Cambio de fragmento con eventos de seleccion en opciones del bottom navigation
        navigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                // Elección de fragmento
                switch (item.getItemId()){
                    case R.id.firstFragment:
                        // Fragmento del sistema
                        getSupportFragmentManager().beginTransaction().replace(R.id.fContainer, firstFragment).commit();
                        return true;
                    //case R.id.secondFragment:
                        // Fragmento de gestión del sistema
                        //getSupportFragmentManager().beginTransaction().replace(R.id.fContainer, secondFragment).commit();
                        //return true;
                    case R.id.thirdFragment:
                        // Fragmento de configuración del sistema
                        getSupportFragmentManager().beginTransaction().replace(R.id.fContainer, thirdFragment).commit();
                        return true;
                    case R.id.fourthFragment:
                        // Fragmento de ayuda del sistema
                        getSupportFragmentManager().beginTransaction().replace(R.id.fContainer, fourthFragment).commit();
                        return true;
                }
                return false;
            }
        });
    }
}