package com.upiita.mechaponicsapp;

import android.animation.LayoutTransition;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.switchmaterial.SwitchMaterial;

public class ThirdFragment extends Fragment {

    LinearLayout linearConfigMod, linearCongifCard;
    LinearLayout layoutSheet;

    SwitchMaterial modExperto;

    Button EditModo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_third, container, false);

        linearConfigMod = view.findViewById(R.id.linearConfigMod);
        linearCongifCard = view.findViewById(R.id.layoutConfigMod);
        layoutSheet = view.findViewById(R.id.bottomSheetContainerMod);
        EditModo = view.findViewById(R.id.btnEditModo);
        modExperto = view.findViewById(R.id.swChangeMod);
        linearConfigMod.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);

        modExperto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int v = (linearConfigMod.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;

                TransitionManager.beginDelayedTransition(linearCongifCard, new AutoTransition());
                linearConfigMod.setVisibility(v);
            }
        });

        EditModo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(
                        getActivity(), R.style.BottomSheetDialogTheme);
                //bottomSheetDialog.setContentView(R.layout.layout_bottom_sheet);
                View bottomSheetView = LayoutInflater.from(getActivity().getApplicationContext())
                        .inflate(
                                R.layout.layout_bs_changemod, layoutSheet);
                bottomSheetView.findViewById(R.id.btnCambioVarMod).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
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