package com.upiita.mechaponicsapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirstFragment extends Fragment {

    private Context globalContext = null;

    // Variables del adaptador deñ Recycler View
    ListAdapter adapterH;
    RecyclerView recyclerViewH;
    ArrayList<ListElement> elements;

    // Ruta principal del proyecto de la base de datos
    String pathProyecto = "MechaponicsSystem/";
    // Valores a extraer de la base de datos
    String valor1, valor2, valor3, valor4, valor5, valor6, valor7, valor8;

    // Objeto de Firebase para obtener la referencia de obtención de datos de la base de datos
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    // Ruta principal del proyecto de la base de datos al apartado de nodos
    String pathEstadoNodos = "MechaponicsSystem/Nodos";
    String estadoN1, estadoN2, estadoN3, estadoN4;
    // Nodos vinculados en la app
    int nodos;
    // Texto del fragmento 1
    TextView tvFirstFragment;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        globalContext = this.getActivity();
    }

    // Función para crear los CardViews de los nodos vinculados en el Recycler View
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Fragmento del Recycler View
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        tvFirstFragment = view.findViewById(R.id.tvFirstFragment);

        DatabaseReference mDatabaseN = database.getReference(pathEstadoNodos);

        mDatabaseN.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                estadoN1 = snapshot.child("N1").getValue().toString();
                estadoN2 = snapshot.child("N2").getValue().toString();
                estadoN3 = snapshot.child("N3").getValue().toString();
                estadoN4 = snapshot.child("N4").getValue().toString();

                if(Integer.parseInt(estadoN4) != 0){
                    nodos=4;
                }else if(Integer.parseInt(estadoN3) != 0){
                    nodos=3;
                }else if(Integer.parseInt(estadoN2) != 0){
                    nodos=2;
                }else if(Integer.parseInt(estadoN1) != 0){
                    nodos=1;
                }else{
                    nodos=0;
                }
                // Recycler View de la interfaz
                recyclerViewH = view.findViewById(R.id.recyclerView);
                elements = new ArrayList<>();
                if (nodos == 0){
                    tvFirstFragment.setText("No hay ningún nodo configurado");
                    elements.clear();
                    recyclerViewH.setAdapter(new ListAdapter(globalContext, elements, new ListAdapter.OnItemClickListener() {
                        // Función para mover los datos del CardView a una ventana nueva
                        @Override
                        public void onItemClick(ListElement item) {

                        }
                    }));
                }else{
                    tvFirstFragment.setText("Selecciona un sistema para más detalles");
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
                                if(elements.size() == nodos){
                                    // Creación del CardView del nodo
                                    if (valnodo == 1){
                                        // Mismo CardView del nodo
                                        // Eliminación del CardView desactualizado
                                        elements.remove(valnodo-1);
                                        elements.add(0, new ListElement(R.drawable.ima_sn , "Solución nutritiva", "Valor de pH: "+valor1,
                                                "Valor de conductividad: "+valor2+" mS/cm","Temperatura de la solución: "+valor3+" °C",
                                                "Nivel de la solución: "+valor4+" %","Nivel del ácido: "+valor5+" %",
                                                "Nivel de la base: "+valor6+" %","Nivel del agua: "+valor7+" %",
                                                "Nivel de los nutrientes: "+valor8+" %","Activo"));
                                    }else{
                                        // Mismo CardView del nodo
                                        // Eliminación del CardView desactualizado
                                        elements.remove(valnodo-1);
                                        elements.add(valnodo-1, new ListElement(R.drawable.ima_lettuce ,"Celda de cultivo "+(valnodo-1),
                                                "Temperatura de la celda: "+valor1+" °C", "Humedad relativa de la celda: "+valor2+" %","Luminosidad: "+valor3+" %",
                                                "Riegos: "+valor4+" al día","", "","", "","Activo"));
                                    }
                                }else{
                                    if (valnodo == 1){
                                        elements.add(valnodo-1, new ListElement(R.drawable.ima_sn , "Solución nutritiva", "Valor de pH: "+valor1,
                                                "Valor de conductividad: "+valor2+" mS/cm","Temperatura de la solución: "+valor3+" °C",
                                                "Nivel de la solución: "+valor4+" %","Nivel del ácido: "+valor5+" %",
                                                "Nivel de la base: "+valor6+" %","Nivel del agua: "+valor7+" %",
                                                "Nivel de los nutrientes: "+valor8+" %","Activo"));
                                    }else{
                                        elements.add(valnodo-1, new ListElement(R.drawable.ima_lettuce ,"Celda de cultivo "+(valnodo-1),
                                                "Temperatura: "+valor1+" °C", "Humedad relativa: "+valor2+" %","Luminosidad: "+valor3+" %",
                                                "Riegos: "+valor4+" al día","", "","", "","Activo"));
                                    }
                                }
                                init();
                            }
                            // Función para cancelar
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return view;
    }

    // Función para inicializar el CardView seleccionado en el Recycler View
    public void init(){
        recyclerViewH.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterH = new ListAdapter(globalContext,elements, new ListAdapter.OnItemClickListener() {
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
                // Notificación al usuario de la selección del CardView
                String nombre = elements.get(recyclerViewH.getChildAdapterPosition(view)).getNodo();
                Toast.makeText(globalContext,"Selecciono: "+nombre, Toast.LENGTH_SHORT).show();
                // Cambio de ventana
                Intent cambio = new Intent( getActivity() ,DatosActivity.class);
                startActivity(cambio);
                getActivity().onBackPressed();
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