package com.upiita.mechaponicsapp;

import android.animation.LayoutTransition;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class FourthFragment extends Fragment {

    Button btnLogout;
    Button btnEditUs;
    TextView detalleCVInfo, detalleCVPonics, detalleCVSistema;
    LinearLayout layoutInfo, layoutPonics, layoutSistema, layoutSheet;
    CardView cvInfo, cvPonics, cvSistema;
    TextView nickname, correo;
    EditText nicknamec, correoc;

    // Ruta principal del proyecto de la base de datos al apartado de Usuario
    String pathProyecto = "MechaponicsSystem/Usuario";
    String nicknameb, correob;

    // Objeto de Firebase para obtener la referencia de obtención de datos de la base de datos
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fourth, container, false);

        // Referencia completa de ese nodo de la base de datos
        DatabaseReference referenceUsuario = database.getReference(pathProyecto);

        referenceUsuario.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // Obtención de los valores de la base de datos
                nicknameb = snapshot.child("Nickname").getValue().toString();
                nickname.setText(nicknameb);
                correob = snapshot.child("Correo").getValue().toString();
                correo.setText(correob);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btnLogout = view.findViewById(R.id.btnCerrarS);
        btnEditUs = view.findViewById(R.id.btnEditUs);
        nickname = view.findViewById(R.id.tvInfoName);
        correo = view.findViewById(R.id.tvInfoEmail);
        detalleCVInfo = view.findViewById(R.id.tvDesInfoMecha);
        detalleCVPonics = view.findViewById(R.id.tvDesInfoPonics);
        detalleCVSistema = view.findViewById(R.id.tvDesInfoSistema);
        layoutInfo = view.findViewById(R.id.llInfoMecha);
        layoutPonics = view.findViewById(R.id.llInfoPonics);
        layoutSistema = view.findViewById(R.id.llInfoSistema);
        layoutSheet = view.findViewById(R.id.bottomSheetContainer);
        layoutInfo.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
        layoutPonics.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
        layoutSistema.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
        cvInfo = view.findViewById(R.id.cvf4InfoMecha);
        cvPonics = view.findViewById(R.id.cvf4InfoPonics);
        cvSistema = view.findViewById(R.id.cvf4InfoSistema);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });

        btnEditUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(
                        getActivity(), R.style.BottomSheetDialogTheme);
                //bottomSheetDialog.setContentView(R.layout.layout_bottom_sheet);
                View bottomSheetView = LayoutInflater.from(getActivity().getApplicationContext())
                        .inflate(
                                R.layout.layout_bottom_sheet, layoutSheet);
                nicknamec = bottomSheetView.findViewById(R.id.etENickname);
                correoc = bottomSheetView.findViewById(R.id.etECorreo);
                bottomSheetView.findViewById(R.id.btnCambioUs).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DatabaseReference mDatabase = database.getReference(pathProyecto);
                        mDatabase.child("Nickname").setValue(nicknamec.getText().toString());
                        mDatabase.child("Correo").setValue(correoc.getText().toString());
                        //nickname.setText(nicknamec.getText());
                        //correo.setText(correoc.getText());
                        Toast.makeText(getActivity(), "Cambios guardados", Toast.LENGTH_SHORT).show();
                        bottomSheetDialog.dismiss();
                    }
                });
                bottomSheetDialog.setContentView(bottomSheetView);
                bottomSheetDialog.show();
            }
        });

        cvInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int v = (detalleCVInfo.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;

                TransitionManager.beginDelayedTransition(layoutInfo, new AutoTransition());
                detalleCVInfo.setVisibility(v);
            }
        });

        cvPonics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int v = (detalleCVPonics.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;

                TransitionManager.beginDelayedTransition(layoutPonics, new AutoTransition());
                detalleCVPonics.setVisibility(v);
            }
        });

        cvSistema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int v = (detalleCVSistema.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;

                TransitionManager.beginDelayedTransition(layoutSistema, new AutoTransition());
                detalleCVSistema.setVisibility(v);
            }
        });

        return view;
    }
}