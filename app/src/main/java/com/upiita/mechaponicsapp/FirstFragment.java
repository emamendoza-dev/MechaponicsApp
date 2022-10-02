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

    // Variables del adaptador deñ Recycler View
    ListAdapter adapterH;
    RecyclerView recyclerViewH;
    ArrayList<ListElement> elements;

    // Ruta principal del proyecto de la base de datos
    String pathProyecto = "MechaponicsSystem/";
    // Valores a extraer de la base de datos
    String valor1, valor2, valor3, valor4, valor5, valor6, valor7, valor8;
    // Nodos vinculados en la app
    int nodos = 1;
    // Objeto de Firebase para obtener la referencia de obtención de datos de la base de datos
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    // Función para crear los CardViews de los nodos vinculados en el Recycler View
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Fragmento del Recycler View
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        // Recycler View de la interfaz
        recyclerViewH = view.findViewById(R.id.recyclerView);
        elements = new ArrayList<>();
        // Creación de los CardViews de los nodos
        for (int i=0; i<nodos; i++){
            // Número del nodo
            int valnodo = i+1;
            // Creación de la ruta del nodo
            String numnodo = Integer.toString(valnodo);
            String pathNodo = "Nodo" + numnodo;
            // Referencia completa de ese nodo de la base de datos
            DatabaseReference referenceNodo = database.getReference(pathProyecto+pathNodo);
            // Creación del CardView del nodo
            referenceNodo.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    // Obtención de los valores de la base de datos
                    valor1 = snapshot.child("Variable1").getValue().toString();
                    valor2 = snapshot.child("Variable2").getValue().toString();
                    valor3 = snapshot.child("Variable3").getValue().toString();
                    valor4 = snapshot.child("Variable4").getValue().toString();
                    valor5 = snapshot.child("Variable5").getValue().toString();
                    valor6 = snapshot.child("Variable6").getValue().toString();
                    valor7 = snapshot.child("Variable7").getValue().toString();
                    valor8 = snapshot.child("Variable8").getValue().toString();
                    // Elminación de CardViews del mismo nodo o creación del CardView si no existe
                    if(elements.size() == 2){
                        // Mismo CardView del nodo
                        // Eliminación del CardView desactualizado
                        elements.remove(0);
                        // Creación del CardView del nodo
                        elements.add(0, new ListElement( pathNodo, "Variable 1: "+valor1,
                                "Variable 2: "+valor2,"Variable 3: "+valor3,
                                "Variable 4: "+valor4,"Variable 5: "+valor5,
                                "Variable 6: "+valor6,"Variable 7: "+valor7,
                                "Variable 8: "+valor8,"Estado OK"));
                    }else{
                        // Creación del CardView del nodo si no existe
                        elements.add(0, new ListElement( pathNodo, "Variable 1: "+valor1,
                                "Variable 2: "+valor2,"Variable 3: "+valor3,
                                "Variable 4: "+valor4,"Variable 5: "+valor5,
                                "Variable 6: "+valor6,"Variable 7: "+valor7,
                                "Variable 8: "+valor8,"Estado OK"));
                    }
                    init();
                }
                // Función para cancelar
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        return view;
    }

    // Función para inicializar el CardView seleccionado en el Recycler View
    public void init(){
        recyclerViewH.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterH = new ListAdapter(getContext(),elements, new ListAdapter.OnItemClickListener() {
            // Función para mover los datos del CardView a una ventana nueva
            @Override
            public void onItemClick(ListElement item) {
                moveToDescription(item);
            }
        });
        recyclerViewH.setAdapter(adapterH);

        // Función de selección de CardView y cambio de ventana
        adapterH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Cambio de ventana
                Intent cambio = new Intent( getActivity() ,DatosActivity.class);
                startActivity(cambio);
                // Notificación al usuario de la selección del CardView
                String nombre = elements.get(recyclerViewH.getChildAdapterPosition(view)).getNodo();
                Toast.makeText(getContext(),"Selecciono: "+nombre, Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Función para mover los datos a la nueva ventana
    public void moveToDescription(ListElement item){
        Intent intent = new Intent(getContext(), DatosActivity.class);
        intent.putExtra("ListElement", item);
        startActivity(intent);
    }
}