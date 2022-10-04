package com.upiita.mechaponicsapp;

import android.animation.LayoutTransition;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.slider.RangeSlider;
import com.google.android.material.slider.Slider;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ThirdFragment extends Fragment {

    LinearLayout linearConfigMod, linearCongifCard, linearConfigDemos;
    TextView tvMSN;
    LinearLayout linearConfigSN, layouttvMSN;
    TextView tvMCC;
    LinearLayout linearConfigCC, layouttvMCC;
    LinearLayout layoutSheet;

    SwitchMaterial modExperto, modDemos;

    Button EditModo;

    Slider sliderSNVar1, sliderSNVar2, sliderCCVar1, sliderCCVar2, sliderCCVar3, sliderCCVar4;
    float valSSNVar1, valSSNVar2, valSCCVar1, valSCCVar2, valSCCVar3, valSCCVar4;
    TextView tvSliderSNVar1, tvSliderSNVar2, tvSliderCCVar1, tvSliderCCVar2, tvSliderCCVar3, tvSliderCCVar4;

    // Ruta principal del proyecto de la base de datos al apartado de Usuario
    String pathProyecto = "MechaponicsSystem/Perfil";

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
        EditModo = view.findViewById(R.id.btnEditModo);
        modExperto = view.findViewById(R.id.swChangeMod);
        modDemos = view.findViewById(R.id.swChangeDemos);

        sliderSNVar1 = view.findViewById(R.id.sliderSNVar1);
        sliderSNVar2 = view.findViewById(R.id.sliderSNVar2);
        sliderCCVar1 = view.findViewById(R.id.sliderCCVar1);
        sliderCCVar2 = view.findViewById(R.id.sliderCCVar2);
        sliderCCVar3 = view.findViewById(R.id.sliderCCVar3);
        sliderCCVar4 = view.findViewById(R.id.sliderCCVar4);

        DatabaseReference mDatabase = database.getReference(pathProyecto);

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

        linearConfigMod.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
        linearConfigSN.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
        linearConfigCC.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);

        modExperto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int v = (linearConfigMod.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;

                TransitionManager.beginDelayedTransition(linearConfigMod, new AutoTransition());
                linearConfigMod.setVisibility(v);

                if (modExperto.isChecked()){
                    DatabaseReference mDatabase = database.getReference(pathProyecto);
                    mDatabase.child("pH").setValue(5.9f);
                    mDatabase.child("CE").setValue(5.8f);
                    mDatabase.child("Temp").setValue(5.7f);
                    mDatabase.child("Hum").setValue(5.6f);
                    mDatabase.child("Lum").setValue(5.5f);
                    mDatabase.child("Rie").setValue(5.4f);
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
                        Toast.makeText(getActivity(), "Cambios guardados", Toast.LENGTH_SHORT).show();
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