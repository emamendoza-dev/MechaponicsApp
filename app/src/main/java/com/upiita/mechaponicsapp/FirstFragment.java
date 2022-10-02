package com.upiita.mechaponicsapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirstFragment extends Fragment {

    ListAdapter adapterH;
    RecyclerView recyclerViewH;
    ArrayList<ListElement> elements;

    String pathProyecto = "MechaponicsSystem/";
    String valor1, valor2, valor3, valor4, valor5, valor6, valor7, valor8;
    int nodos = 1;

    FirebaseDatabase database = FirebaseDatabase.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        recyclerViewH = view.findViewById(R.id.recyclerView);
        elements = new ArrayList<>();

        for (int i=0; i<nodos; i++){
            int valnodo = i+1;
            String numnodo = Integer.toString(valnodo);
            String pathNodo = "Nodo" + numnodo;
            DatabaseReference referenceNodo = database.getReference(pathProyecto+pathNodo);

            referenceNodo.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    valor1 = snapshot.child("Variable1").getValue().toString();
                    valor2 = snapshot.child("Variable2").getValue().toString();
                    valor3 = snapshot.child("Variable3").getValue().toString();
                    valor4 = snapshot.child("Variable4").getValue().toString();
                    valor5 = snapshot.child("Variable5").getValue().toString();
                    valor6 = snapshot.child("Variable6").getValue().toString();
                    valor7 = snapshot.child("Variable7").getValue().toString();
                    valor8 = snapshot.child("Variable8").getValue().toString();

                    if(elements.size() == 2){
                        elements.remove(0);
                        elements.add(0, new ListElement( pathNodo, "Variable 1: "+valor1,
                                "Variable 2: "+valor2,"Variable 3: "+valor3,
                                "Variable 4: "+valor4,"Variable 5: "+valor5,
                                "Variable 6: "+valor6,"Variable 7: "+valor7,
                                "Variable 8: "+valor8,"Estado OK"));
                    }else{
                        elements.add(0, new ListElement( pathNodo, "Variable 1: "+valor1,
                                "Variable 2: "+valor2,"Variable 3: "+valor3,
                                "Variable 4: "+valor4,"Variable 5: "+valor5,
                                "Variable 6: "+valor6,"Variable 7: "+valor7,
                                "Variable 8: "+valor8,"Estado OK"));
                    }
                    init();
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        //return inflater.inflate(R.layout.fragment_first, container, false);
        return view;
    }

    public void init(){
        recyclerViewH.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterH = new ListAdapter(getContext(),elements, new ListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ListElement item) {
                moveToDescription(item);
            }
        });
        recyclerViewH.setAdapter(adapterH);

        adapterH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cambio = new Intent( getActivity() ,DatosActivity.class);
                startActivity(cambio);
                String nombre = elements.get(recyclerViewH.getChildAdapterPosition(view)).getNodo();
                Toast.makeText(getContext(),"Selecciono: "+nombre, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void moveToDescription(ListElement item){
        Intent intent = new Intent(getContext(), DatosActivity.class);
        intent.putExtra("ListElement", item);
        startActivity(intent);
    }
}