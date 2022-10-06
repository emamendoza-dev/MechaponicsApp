package com.upiita.mechaponicsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ekn.gruzer.gaugelibrary.ArcGauge;

import java.util.Objects;

public class DatosActivity extends AppCompatActivity {

    TextView textnodo, textvar1, textvar2, textvar3, textvar4, textvar5, textvar6, textvar7,
            textvar8, textestado;

    ImageView dn_ima_var1, dn_ima_var2, dn_ima_var3, dn_ima_var4, dn_ima_var5, dn_ima_var6,
            dn_ima_var7, dn_ima_var8;

    ArcGauge medicion1, medicion2, medicion3, medicion4, medicion5, medicion6, medicion7, medicion8;

    com.ekn.gruzer.gaugelibrary.Range Rango1, Rango2, Rango3;

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

        dn_ima_var1 = findViewById(R.id.dn_ima_var1);
        dn_ima_var2 = findViewById(R.id.dn_ima_var2);
        dn_ima_var3 = findViewById(R.id.dn_ima_var3);
        dn_ima_var4 = findViewById(R.id.dn_ima_var4);
        dn_ima_var5 = findViewById(R.id.dn_ima_var5);
        dn_ima_var6 = findViewById(R.id.dn_ima_var6);
        dn_ima_var7 = findViewById(R.id.dn_ima_var7);
        dn_ima_var8 = findViewById(R.id.dn_ima_var8);

        medicion1 = findViewById(R.id.medidor1);
        medicion2 = findViewById(R.id.medidor2);
        medicion3 = findViewById(R.id.medidor3);
        medicion4 = findViewById(R.id.medidor4);
        medicion5 = findViewById(R.id.medidor5);
        medicion6 = findViewById(R.id.medidor6);
        medicion7 = findViewById(R.id.medidor7);
        medicion8 = findViewById(R.id.medidor8);

        if (Objects.equals(element.getNodo(), "Solución nutritiva")){
            dn_ima_var1.setImageResource(R.drawable.ima_ph);
            dn_ima_var2.setImageResource(R.drawable.ima_ce);
            dn_ima_var3.setImageResource(R.drawable.ima_tempsn);
            dn_ima_var4.setImageResource(R.drawable.ima_nivtanque);
            gauguearcprint(medicion1, new float[]{4.0f, 5.0f}, new float[]{5.0f, 6.5f},
                    new float[]{6.5f, 7.0f}, element.getVariable1());
            gauguearcprint(medicion2, new float[]{0.7f, 0.8f}, new float[]{0.8f, 1.1f},
                    new float[]{1.1f, 1.2f}, element.getVariable2());
            gauguearcprint(medicion3, new float[]{14.0f, 15.0f}, new float[]{15.0f, 18.0f},
                    new float[]{18.0f, 25.0f}, element.getVariable3());
            gauguearcprint(medicion4, new float[]{0.0f, 30.0f}, new float[]{30.0f, 100.0f},
                    new float[]{100.0f, 125.0f}, element.getVariable4());
            gauguearcprint(medicion5, new float[]{0.0f, 30.0f}, new float[]{30.0f, 100.0f},
                    new float[]{100.0f, 125.0f}, element.getVariable5());
            gauguearcprint(medicion6, new float[]{0.0f, 30.0f}, new float[]{30.0f, 100.0f},
                    new float[]{100.0f, 125.0f}, element.getVariable6());
            gauguearcprint(medicion7, new float[]{0.0f, 30.0f}, new float[]{30.0f, 100.0f},
                    new float[]{100.0f, 125.0f}, element.getVariable7());
            gauguearcprint(medicion8, new float[]{0.0f, 30.0f}, new float[]{30.0f, 100.0f},
                    new float[]{100.0f, 125.0f}, element.getVariable8());
        }else{
            dn_ima_var1.setImageResource(R.drawable.ima_tempsn);
            dn_ima_var2.setImageResource(R.drawable.ima_hum);
            dn_ima_var3.setImageResource(R.drawable.ima_lum);
            dn_ima_var4.setImageResource(R.drawable.ima_lum);
            dn_ima_var5.setVisibility(View.INVISIBLE);
            dn_ima_var6.setVisibility(View.INVISIBLE);
            dn_ima_var7.setVisibility(View.INVISIBLE);
            dn_ima_var8.setVisibility(View.INVISIBLE);
            medicion5.setVisibility(View.INVISIBLE);
            medicion6.setVisibility(View.INVISIBLE);
            medicion7.setVisibility(View.INVISIBLE);
            medicion8.setVisibility(View.INVISIBLE);
            gauguearcprint(medicion1, new float[]{14.0f, 15.0f}, new float[]{15.0f, 18.0f},
                    new float[]{18.0f, 25.0f}, element.getVariable1());
            gauguearcprint(medicion2, new float[]{0.0f, 60.0f}, new float[]{60.0f, 80.0f},
                    new float[]{80.0f, 120.0f}, element.getVariable2());
            gauguearcprint(medicion3, new float[]{0.0f, 30.0f}, new float[]{30.0f, 80.0f},
                    new float[]{80.0f, 125.0f}, element.getVariable3());
            gauguearcprint(medicion4, new float[]{0.0f, 2.0f}, new float[]{2.0f, 5.0f},
                    new float[]{6.0f, 7.0f}, element.getVariable4());
        }
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

    public void gauguearcprint(ArcGauge medidor, float []rango1, float []rango2, float []rango3, String element){
        Rango1 = new com.ekn.gruzer.gaugelibrary.Range();
        Rango2 = new com.ekn.gruzer.gaugelibrary.Range();
        Rango3 = new com.ekn.gruzer.gaugelibrary.Range();

        Rango1.setFrom(rango1[0]); Rango1.setTo(rango1[1]);
        Rango2.setFrom(rango2[0]); Rango2.setTo(rango2[1]);
        Rango3.setFrom(rango3[0]); Rango3.setTo(rango3[1]);

        Rango1.setColor(Color.RED);
        Rango2.setColor(Color.GREEN);
        Rango3.setColor(Color.RED);

        String textodiv1 = element;

        String[] parts1 = textodiv1.split(": ");
        String part12 = parts1[1];
        String[] num = part12.split(" ");
        String val = num[0];

        medidor.setMinValue(rango1[0]);
        medidor.setMaxValue(rango3[1]);
        medidor.setValue(Double.parseDouble(val));
        medidor.addRange(Rango1);
        medidor.addRange(Rango2);
        medidor.addRange(Rango3);
    }
}