package com.upiita.mechaponicsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DatosActivity extends AppCompatActivity {

    TextView textnodo;
    TextView textvar1;
    TextView textvar2;
    TextView textvar3;
    TextView textvar4;
    TextView textvar5;
    TextView textvar6;
    TextView textvar7;
    TextView textvar8;
    TextView textestado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos);

        ListElement element = (ListElement) getIntent().getSerializableExtra("ListElement");
        textnodo = findViewById(R.id.tvDNodo);
        textvar1 = findViewById(R.id.tvDVar1);
        textvar2 = findViewById(R.id.tvDVar2);
        textvar3 = findViewById(R.id.tvDVar3);
        textvar4 = findViewById(R.id.tvDVar4);
        textvar5 = findViewById(R.id.tvDVar5);
        textvar6 = findViewById(R.id.tvDVar6);
        textvar7 = findViewById(R.id.tvDVar7);
        textvar8 = findViewById(R.id.tvDVar8);
        textestado = findViewById(R.id.tvDEstado);

        textnodo.setText(element.getNodo());
        textvar1.setText(element.getVariable1());
        textvar2.setText(element.getVariable2());
        textvar3.setText(element.getVariable3());
        textvar4.setText(element.getVariable4());
        textvar5.setText(element.getVariable5());
        textvar6.setText(element.getVariable6());
        textvar7.setText(element.getVariable7());
        textvar8.setText(element.getVariable8());
        textestado.setText(element.getEstado());
    }
}