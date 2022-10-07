package com.upiita.mechaponicsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.LayoutTransition;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class NodoWifiActivity extends AppCompatActivity {

    Button btnOkNodo, btnListoNodo;

    WebView wvConfignodo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nodo_wifi);

        btnOkNodo =  findViewById(R.id.btnOkNodo);
        btnListoNodo = findViewById(R.id.btnListoNodo);

        wvConfignodo = (WebView) findViewById(R.id.wvConfignodo);
        wvConfignodo.setWebViewClient(new WebViewClient());
        //final WebSettings ajustesVisorWeb = wvConfignodo.getSettings();
        //ajustesVisorWeb.setJavaScriptEnabled(true);

        btnOkNodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wvConfignodo.loadUrl("192.168.4.1");
                //wvConfignodo.loadUrl("https://www.google.com.mx/");
            }
        });

        btnListoNodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(NodoWifiActivity.this, InitActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}