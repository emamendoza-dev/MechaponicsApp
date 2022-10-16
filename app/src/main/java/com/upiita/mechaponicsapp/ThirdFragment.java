package com.upiita.mechaponicsapp;

import android.animation.LayoutTransition;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.chip.Chip;
import com.google.android.material.slider.Slider;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import cn.pedant.SweetAlert.SweetAlertDialog;
import android.graphics.Color;

public class ThirdFragment extends Fragment {

    LinearLayout linearConfigMod, linearCongifCard, linearConfigDemos;
    TextView tvMSN;
    LinearLayout linearConfigSN, layouttvMSN;
    TextView tvMCC;
    LinearLayout linearConfigCC, layouttvMCC;
    LinearLayout layoutSheet, layoutSheetDemos, layoutSheetNodos;

    SwitchMaterial modExperto, modDemos;

    Button EditModo, btnRunDemos, btnAgregarNodo;

    Slider sliderSNVar1, sliderSNVar2, sliderCCVar1, sliderCCVar2, sliderCCVar3, sliderCCVar4;
    float valSSNVar1, valSSNVar2, valSCCVar1, valSCCVar2, valSCCVar3, valSCCVar4;
    TextView tvSliderSNVar1, tvSliderSNVar2, tvSliderCCVar1, tvSliderCCVar2, tvSliderCCVar3, tvSliderCCVar4;

    TextView tvModDOp1, tvModDOp2, tvModDOp3, tvModDOp4, tvModDOp5, tvModDOp6;
    boolean boolModOp1, boolModOp2, boolModOp3, boolModOp4, boolModOp5, boolModOp6;
    Chip chipModOp1, chipModOp2, chipModOp3, chipModOp4, chipModOp5, chipModOp6;

    RadioGroup rbNodos;
    RadioButton rbNodo1, rbNodo2, rbNodo3, rbNodo4;
    String selnodo;
    TextView tvCNodo1, tvCNodo2, tvCNodo3, tvCNodo4;

    // Ruta principal del proyecto de la base de datos al apartado de Usuario
    String pathProyecto = "MechaponicsSystem/Perfil";
    // Ruta principal del proyecto de la base de datos al apartado del estado del sistema
    String pathEstadoDemos = "MechaponicsSystem/Estado";
    // Ruta principal del proyecto de la base de datos al apartado de nodos
    String pathEstadoNodos = "MechaponicsSystem/Nodos";

    String estadoN1, estadoN2, estadoN3, estadoN4;

    // Objeto de Firebase para obtener la referencia de obtención de datos de la base de datos
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_third, container, false);

        linearConfigMod = view.findViewById(R.id.linearConfigMod);
        linearConfigDemos = view.findViewById(R.id.linearConfigDemos);
        linearCongifCard = view.findViewById(R.id.layoutConfigMod);
        tvMSN = view.findViewById(R.id.tvMSN);
        linearConfigSN = view.findViewById(R.id.linearConfigSN);
        layouttvMSN = view.findViewById(R.id.layouttvMSN);
        tvMCC = view.findViewById(R.id.tvMCC);
        linearConfigCC = view.findViewById(R.id.linearConfigCC);
        layouttvMCC = view.findViewById(R.id.layouttvMCC);
        layoutSheet = view.findViewById(R.id.bottomSheetContainerMod);
        layoutSheetDemos = view.findViewById(R.id.bottomSheetContainerMod);
        layoutSheetNodos = view.findViewById(R.id.bottomSheetContainerNodos);
        btnAgregarNodo = view.findViewById(R.id.btnAgregarNodo);
        EditModo = view.findViewById(R.id.btnEditModo);
        btnRunDemos = view.findViewById(R.id.btnRunDemos);
        modExperto = view.findViewById(R.id.swChangeMod);
        modDemos = view.findViewById(R.id.swChangeDemos);

        sliderSNVar1 = view.findViewById(R.id.sliderSNVar1);
        sliderSNVar2 = view.findViewById(R.id.sliderSNVar2);
        sliderCCVar1 = view.findViewById(R.id.sliderCCVar1);
        sliderCCVar2 = view.findViewById(R.id.sliderCCVar2);
        sliderCCVar3 = view.findViewById(R.id.sliderCCVar3);
        sliderCCVar4 = view.findViewById(R.id.sliderCCVar4);

        chipModOp1 = view.findViewById(R.id.chip1);
        chipModOp2 = view.findViewById(R.id.chip2);
        chipModOp3 = view.findViewById(R.id.chip3);
        chipModOp4 = view.findViewById(R.id.chip4);
        chipModOp5 = view.findViewById(R.id.chip5);
        chipModOp6 = view.findViewById(R.id.chip6);

        tvCNodo1 = view.findViewById(R.id.tvCNodo1);
        tvCNodo2 = view.findViewById(R.id.tvCNodo2);
        tvCNodo3 = view.findViewById(R.id.tvCNodo3);
        tvCNodo4 = view.findViewById(R.id.tvCNodo4);

        DatabaseReference mDatabase = database.getReference(pathProyecto);

        DatabaseReference mDatabaseN = database.getReference(pathEstadoNodos);

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String valor1 = snapshot.child("pH").getValue().toString();
                sliderSNVar1.setValue(Float.parseFloat(valor1));
                String valor2 = snapshot.child("CE").getValue().toString();
                sliderSNVar2.setValue(Float.parseFloat(valor2));
                String valor3 = snapshot.child("Temp").getValue().toString();
                sliderCCVar1.setValue(Float.parseFloat(valor3));
                String valor4 = snapshot.child("Hum").getValue().toString();
                sliderCCVar2.setValue(Float.parseFloat(valor4));
                String valor5 = snapshot.child("Lum").getValue().toString();
                sliderCCVar3.setValue(Float.parseFloat(valor5));
                String valor6 = snapshot.child("Rie").getValue().toString();
                sliderCCVar4.setValue(Float.parseFloat(valor6));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        mDatabaseN.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                estadoN1 = snapshot.child("N1").getValue().toString();
                if (Integer.parseInt(estadoN1) == 0){
                    tvCNodo1.setText("Sin nodo de solución");
                    tvCNodo1.setTextColor(Color.parseColor("#ff6659"));
                }else{
                    tvCNodo1.setText("Nodo de solución");
                    tvCNodo1.setTextColor(Color.parseColor("#00838f"));
                }
                estadoN2 = snapshot.child("N2").getValue().toString();
                if (Integer.parseInt(estadoN2) == 0){
                    tvCNodo2.setText("Sin nodo de cultivo 1");
                    tvCNodo2.setTextColor(Color.parseColor("#ff6659"));
                }else{
                    tvCNodo2.setText("Nodo de cultivo 1");
                    tvCNodo2.setTextColor(Color.parseColor("#00838f"));
                }
                estadoN3 = snapshot.child("N3").getValue().toString();
                if (Integer.parseInt(estadoN3) == 0){
                    tvCNodo3.setText("Sin nodo de cultivo 2");
                    tvCNodo3.setTextColor(Color.parseColor("#ff6659"));
                }else{
                    tvCNodo3.setText("Nodo de cultivo 2");
                    tvCNodo3.setTextColor(Color.parseColor("#00838f"));
                }
                estadoN4 = snapshot.child("N4").getValue().toString();
                if (Integer.parseInt(estadoN4) == 0){
                    tvCNodo4.setText("Sin nodo de cultivo 3");
                    tvCNodo4.setTextColor(Color.parseColor("#ff6659"));
                }else{
                    tvCNodo4.setText("Nodo de cultivo 3");
                    tvCNodo4.setTextColor(Color.parseColor("#00838f"));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        linearConfigMod.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
        linearConfigSN.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
        linearConfigCC.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);

        btnAgregarNodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(
                        getActivity(), R.style.BottomSheetDialogTheme);
                //bottomSheetDialog.setContentView(R.layout.layout_bottom_sheet);
                View bottomSheetView = LayoutInflater.from(getActivity().getApplicationContext())
                        .inflate(
                                R.layout.layout_bs_confignodos, layoutSheetNodos);
                rbNodos = bottomSheetView.findViewById(R.id.rbNodos);
                rbNodo1 = bottomSheetView.findViewById(R.id.rbNodo1);
                rbNodo2 = bottomSheetView.findViewById(R.id.rbNodo2);
                rbNodo3 = bottomSheetView.findViewById(R.id.rbNodo3);
                rbNodo4 = bottomSheetView.findViewById(R.id.rbNodo4);

                rbNodo1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        selnodo = "1";
                        Toast.makeText(getActivity(), "Nodo 1 seleccionado", Toast.LENGTH_SHORT).show();
                    }
                });

                rbNodo2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        selnodo = "2";
                        Toast.makeText(getActivity(), "Nodo 2 seleccionado", Toast.LENGTH_SHORT).show();
                    }
                });

                rbNodo3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        selnodo = "3";
                        Toast.makeText(getActivity(), "Nodo 3 seleccionado", Toast.LENGTH_SHORT).show();
                    }
                });

                rbNodo4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        selnodo = "4";
                        Toast.makeText(getActivity(), "Nodo 4 seleccionado", Toast.LENGTH_SHORT).show();
                    }
                });


                bottomSheetView.findViewById(R.id.btnAddNodo).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (selnodo == "1"){
                            tvCNodo1.setTextColor(Color.parseColor("#00838f"));
                            mDatabaseN.child("N1").setValue(1);
                        }else if (selnodo == "2"){
                            tvCNodo2.setTextColor(Color.parseColor("#00838f"));
                            mDatabaseN.child("N1").setValue(1);
                            mDatabaseN.child("N2").setValue(1);
                        }else if (selnodo == "3"){
                            tvCNodo3.setTextColor(Color.parseColor("#00838f"));
                            mDatabaseN.child("N1").setValue(1);
                            mDatabaseN.child("N2").setValue(1);
                            mDatabaseN.child("N3").setValue(1);
                        }else if (selnodo == "4"){
                            tvCNodo4.setTextColor(Color.parseColor("#00838f"));
                            mDatabaseN.child("N1").setValue(1);
                            mDatabaseN.child("N2").setValue(1);
                            mDatabaseN.child("N3").setValue(1);
                            mDatabaseN.child("N4").setValue(1);
                        }
                        //Toast.makeText(getActivity(), "Nodo "+selnodo+" agregado", Toast.LENGTH_SHORT).show();
                        new SweetAlertDialog(getActivity(), SweetAlertDialog.SUCCESS_TYPE)
                                .setTitleText("Listo!")
                                .setContentText("Nodo "+selnodo+" agregado")
                                .show();
                        bottomSheetDialog.dismiss();
                        //getActivity().onBackPressed();
                    }
                });
                bottomSheetView.findViewById(R.id.btnConfigNodo).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Toast.makeText(getActivity(), "Abriendo configuración del nodo "+selnodo, Toast.LENGTH_SHORT).show();
                        new SweetAlertDialog(getActivity())
                                .setTitleText("Abriendo configuración del nodo "+selnodo)
                                .show();
                        bottomSheetDialog.dismiss();
                        Intent i = new Intent(getActivity(), NodoWifiActivity.class);
                        startActivity(i);
                        //getActivity().onBackPressed();
                    }
                });
                bottomSheetView.findViewById(R.id.btnDeleNodo).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (selnodo == "1"){
                            if (tvCNodo4.getText() == "Nodo de cultivo 3" || tvCNodo3.getText() == "Nodo de cultivo 2" || tvCNodo2.getText() == "Nodo de cultivo 1"){
                                //Toast.makeText(getActivity(), "Es necesario eliminar el nodo final", Toast.LENGTH_SHORT).show();
                                new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                                        .setTitleText("Error!")
                                        .setContentText("Es necesario eliminar el nodo final")
                                        .show();
                            }else{
                                tvCNodo1.setTextColor(Color.parseColor("#ff6659"));
                                mDatabaseN.child("N1").setValue(0);
                                //Toast.makeText(getActivity(), "Nodo "+selnodo+" eliminado", Toast.LENGTH_SHORT).show();
                                new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                                        .setTitleText("Eliminado!")
                                        .setContentText("Nodo "+selnodo+" eliminado")
                                        .show();
                            }
                        }else if (selnodo == "2"){
                            if (tvCNodo4.getText() == "Nodo de cultivo 3" || tvCNodo3.getText() == "Nodo de cultivo 2"){
                                //Toast.makeText(getActivity(), "Es necesario eliminar el nodo final", Toast.LENGTH_SHORT).show();
                                new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                                        .setTitleText("Error!")
                                        .setContentText("Es necesario eliminar el nodo final")
                                        .show();
                            }else{
                                tvCNodo2.setTextColor(Color.parseColor("#ff6659"));
                                mDatabaseN.child("N2").setValue(0);
                                //Toast.makeText(getActivity(), "Nodo "+selnodo+" eliminado", Toast.LENGTH_SHORT).show();
                                new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                                        .setTitleText("Eliminado!")
                                        .setContentText("Nodo "+selnodo+" eliminado")
                                        .show();
                            }
                        }else if (selnodo == "3"){
                            if (tvCNodo4.getText() == "Nodo de cultivo 3"){
                                //Toast.makeText(getActivity(), "Es necesario eliminar el nodo final", Toast.LENGTH_SHORT).show();
                                new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                                        .setTitleText("Error!")
                                        .setContentText("Es necesario eliminar el nodo final")
                                        .show();
                            }else{
                                tvCNodo3.setTextColor(Color.parseColor("#ff6659"));
                                mDatabaseN.child("N3").setValue(0);
                                //Toast.makeText(getActivity(), "Nodo "+selnodo+" eliminado", Toast.LENGTH_SHORT).show();
                                new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                                        .setTitleText("Eliminado!")
                                        .setContentText("Nodo "+selnodo+" eliminado")
                                        .show();
                            }
                        }else if (selnodo == "4"){
                            tvCNodo4.setTextColor(Color.parseColor("#ff6659"));
                            mDatabaseN.child("N4").setValue(0);
                        }

                        bottomSheetDialog.dismiss();
                    }
                });
                bottomSheetDialog.setContentView(bottomSheetView);
                bottomSheetDialog.show();

            }
        });

        modExperto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int v = (linearConfigMod.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;

                TransitionManager.beginDelayedTransition(linearConfigMod, new AutoTransition());
                linearConfigMod.setVisibility(v);

                if (modExperto.isChecked()){
                    DatabaseReference mDatabase = database.getReference(pathProyecto);
                    mDatabase.child("pH").setValue(6.0f);
                    mDatabase.child("CE").setValue(1.0f);
                    mDatabase.child("Temp").setValue(17.0f);
                    mDatabase.child("Hum").setValue(70);
                    mDatabase.child("Lum").setValue(50);
                    mDatabase.child("Rie").setValue(5);
                }
            }
        });

        modDemos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int v = (linearConfigDemos.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;

                TransitionManager.beginDelayedTransition(linearConfigDemos, new AutoTransition());
                linearConfigDemos.setVisibility(v);
            }
        });

        tvMSN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int v = (linearConfigSN.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;

                TransitionManager.beginDelayedTransition(layouttvMSN, new AutoTransition());
                linearConfigSN.setVisibility(v);
            }
        });

        tvMCC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int v = (linearConfigCC.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;

                TransitionManager.beginDelayedTransition(layouttvMCC, new AutoTransition());
                linearConfigCC.setVisibility(v);
            }
        });

        EditModo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                valSSNVar1 = sliderSNVar1.getValue();
                valSSNVar2 = sliderSNVar2.getValue();
                valSCCVar1 = sliderCCVar1.getValue();
                valSCCVar2 = sliderCCVar2.getValue();
                valSCCVar3 = sliderCCVar3.getValue();
                valSCCVar4 = sliderCCVar4.getValue();
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(
                        getActivity(), R.style.BottomSheetDialogTheme);
                //bottomSheetDialog.setContentView(R.layout.layout_bottom_sheet);
                View bottomSheetView = LayoutInflater.from(getActivity().getApplicationContext())
                        .inflate(
                                R.layout.layout_bs_changemod, layoutSheet);
                tvSliderSNVar1 = bottomSheetView.findViewById(R.id.tvSliderSNVar1);
                tvSliderSNVar1.setText("pH del sistema a: "+valSSNVar1);
                tvSliderSNVar2 = bottomSheetView.findViewById(R.id.tvSliderSNVar2);
                tvSliderSNVar2.setText("CE del sistema a: "+valSSNVar2);
                tvSliderCCVar1 = bottomSheetView.findViewById(R.id.tvSliderCCVar1);
                tvSliderCCVar1.setText("Temperatura del sistema a: "+valSCCVar1);
                tvSliderCCVar2 = bottomSheetView.findViewById(R.id.tvSliderCCVar2);
                tvSliderCCVar2.setText("Humedad del sistema a: "+valSCCVar2);
                tvSliderCCVar3 = bottomSheetView.findViewById(R.id.tvSliderCCVar3);
                tvSliderCCVar3.setText("Luminosidad del sistema a: "+valSCCVar3);
                tvSliderCCVar4 = bottomSheetView.findViewById(R.id.tvSliderCCVar4);
                tvSliderCCVar4.setText("Riegos del sistema a: "+valSCCVar4);

                bottomSheetView.findViewById(R.id.btnCambioVarMod).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DatabaseReference mDatabase = database.getReference(pathProyecto);
                        mDatabase.child("pH").setValue(valSSNVar1);
                        mDatabase.child("CE").setValue(valSSNVar2);
                        mDatabase.child("Temp").setValue(valSCCVar1);
                        mDatabase.child("Hum").setValue(valSCCVar2);
                        mDatabase.child("Lum").setValue(valSCCVar3);
                        mDatabase.child("Rie").setValue(valSCCVar4);
                        //Toast.makeText(getActivity(), "Cambios guardados", Toast.LENGTH_SHORT).show();
                        new SweetAlertDialog(getActivity(), SweetAlertDialog.SUCCESS_TYPE)
                                .setTitleText("Listo!")
                                .setContentText("Cambios guardados")
                                .show();
                        bottomSheetDialog.dismiss();
                    }
                });
                bottomSheetDialog.setContentView(bottomSheetView);
                bottomSheetDialog.show();
            }
        });

        btnRunDemos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolModOp1 = chipModOp1.isChecked();
                boolModOp2 = chipModOp2.isChecked();
                boolModOp3 = chipModOp3.isChecked();
                boolModOp4 = chipModOp4.isChecked();
                boolModOp5 = chipModOp5.isChecked();
                boolModOp6 = chipModOp6.isChecked();

                DatabaseReference mDatabaseDemos = database.getReference(pathEstadoDemos);
                mDatabaseDemos.child("Modo").setValue(1);

                mDatabaseDemos.child("Dosificadores").setValue(boolModOp1);
                mDatabaseDemos.child("Solucion").setValue(boolModOp2);
                mDatabaseDemos.child("Irrigacion").setValue(boolModOp3);
                mDatabaseDemos.child("Temperatura").setValue(boolModOp4);
                mDatabaseDemos.child("Humedad").setValue(boolModOp5);
                mDatabaseDemos.child("Mediciones").setValue(boolModOp6);

                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(
                        getActivity(), R.style.BottomSheetDialogTheme);
                //bottomSheetDialog.setContentView(R.layout.layout_bottom_sheet);
                View bottomSheetView = LayoutInflater.from(getActivity().getApplicationContext())
                        .inflate(
                                R.layout.layout_bs_mododemostrativo, layoutSheetDemos);
                tvModDOp1 = bottomSheetView.findViewById(R.id.tvModDOp1);
                tvModDOp2 = bottomSheetView.findViewById(R.id.tvModDOp2);
                tvModDOp3 = bottomSheetView.findViewById(R.id.tvModDOp3);
                tvModDOp4 = bottomSheetView.findViewById(R.id.tvModDOp4);
                tvModDOp5 = bottomSheetView.findViewById(R.id.tvModDOp5);
                tvModDOp6 = bottomSheetView.findViewById(R.id.tvModDOp6);

                if (boolModOp1) {
                    tvModDOp1.setTypeface(null, Typeface.BOLD);
                }else{
                    tvModDOp1.setTypeface(null, Typeface.NORMAL);
                }
                if (boolModOp2){
                    tvModDOp2.setTypeface(null, Typeface.BOLD);
                }else{
                    tvModDOp2.setTypeface(null, Typeface.NORMAL);
                }
                if (boolModOp3){
                    tvModDOp3.setTypeface(null, Typeface.BOLD);
                }else{
                    tvModDOp3.setTypeface(null, Typeface.NORMAL);
                }
                if (boolModOp4){
                    tvModDOp4.setTypeface(null, Typeface.BOLD);
                }else{
                    tvModDOp4.setTypeface(null, Typeface.NORMAL);
                }
                if (boolModOp5){
                    tvModDOp5.setTypeface(null, Typeface.BOLD);
                }else{
                    tvModDOp5.setTypeface(null, Typeface.NORMAL);
                }
                if (boolModOp6){
                    tvModDOp6.setTypeface(null, Typeface.BOLD);
                }else{
                    tvModDOp6.setTypeface(null, Typeface.NORMAL);
                }

                bottomSheetView.findViewById(R.id.btnModDemosDesac).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DatabaseReference mDatabaseDemos = database.getReference(pathEstadoDemos);
                        mDatabaseDemos.child("Modo").setValue(0);

                        mDatabaseDemos.child("Dosificadores").setValue(false);
                        mDatabaseDemos.child("Solucion").setValue(false);
                        mDatabaseDemos.child("Irrigacion").setValue(false);
                        mDatabaseDemos.child("Temperatura").setValue(false);
                        mDatabaseDemos.child("Humedad").setValue(false);
                        mDatabaseDemos.child("Mediciones").setValue(false);
                        //Toast.makeText(getActivity(), "Prueba finalizada, regresando a modo automático", Toast.LENGTH_SHORT).show();
                        new SweetAlertDialog(getActivity())
                                .setTitleText("Prueba finalizada, regresando a modo automático")
                                .show();
                        bottomSheetDialog.dismiss();
                    }
                });
                bottomSheetDialog.setContentView(bottomSheetView);
                bottomSheetDialog.show();
            }
        });

        return view;
    }

}