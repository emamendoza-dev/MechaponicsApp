package com.upiita.mechaponicsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> implements View.OnClickListener{
    // Variables para la construcción del Recycler View
    ArrayList<ListElement> mData;
    LayoutInflater mInflater;
    private View.OnClickListener listener;
    final ListAdapter.OnItemClickListener listener2;

    // Función para evento de selección de un CardView dentro del Recylcer View
    public interface OnItemClickListener{
        void onItemClick(ListElement item);
    }

    // Adaptador del Recycler View
    public ListAdapter(Context context, ArrayList<ListElement> itemList, ListAdapter.OnItemClickListener listener2){
        this.mInflater = LayoutInflater.from(context);
        this.mData = itemList;
        this.listener2 = listener2;
    }

    // Función para obtener el tamaño de items
    @Override
    public int getItemCount(){ return mData.size();}

    // Función para a creación del Recylcer View
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = mInflater.inflate(R.layout.list_element, parent, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    // Función para construir el evento de selección de un CardView dentro del Recylcer View
    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    // Función para obtener y poner datos en el CardView
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){
        // Obtención de los valores del CardView
        String nodo = mData.get(position).getNodo();
        String var1 = mData.get(position).getVariable1();
        String var2 = mData.get(position).getVariable2();
        String var3 = mData.get(position).getVariable3();
        String var4 = mData.get(position).getVariable4();
        String var5 = mData.get(position).getVariable5();
        String var6 = mData.get(position).getVariable6();
        String var7 = mData.get(position).getVariable7();
        String var8 = mData.get(position).getVariable8();
        String estado = mData.get(position).getEstado();

        // Colocación de los datos del CardView
        holder.nodo.setText(nodo);
        holder.variable1.setText(var1);
        holder.variable2.setText(var1);
        holder.variable3.setText(var1);
        holder.variable4.setText(var1);
        holder.variable5.setText(var1);
        holder.variable6.setText(var1);
        holder.variable7.setText(var1);
        holder.variable8.setText(var1);
        holder.estado.setText(estado);
        holder.cv.setAnimation(AnimationUtils.loadAnimation(mInflater.getContext(), R.anim.fade_transition));
        holder.bindData(mData.get(position));
    }

    public void setItems(ArrayList<ListElement> items){mData = items;}

    // Función de evento de click del CardView
    public void onClick(View view){
        if(listener!=null){
            listener.onClick(view);
        }
    }

    // Función del Recycler View
    public class ViewHolder extends RecyclerView.ViewHolder {

        // Variables de la CardView
        TextView nodo, variable1, variable2, variable3,
                variable4, variable5, variable6, variable7, variable8, estado;

        CardView cv;

        // Vinculación de las variables del CardView
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            nodo = itemView.findViewById(R.id.tvNodo);
            variable1 = itemView.findViewById(R.id.tvVariable1);
            variable2 = itemView.findViewById(R.id.tvVariable2);
            variable3 = itemView.findViewById(R.id.tvVariable3);
            variable4 = itemView.findViewById(R.id.tvVariable4);
            variable5 = itemView.findViewById(R.id.tvVariable5);
            variable6 = itemView.findViewById(R.id.tvVariable6);
            variable7 = itemView.findViewById(R.id.tvVariable7);
            variable8 = itemView.findViewById(R.id.tvVariable8);
            estado = itemView.findViewById(R.id.tvEstadoSis);
            cv = itemView.findViewById(R.id.cv);
        }

        // Función para actualizar los valores del CardView
        void bindData(final ListElement item){
            nodo.setText(item.getNodo());
            variable1.setText(item.getVariable1());
            variable2.setText(item.getVariable2());
            variable3.setText(item.getVariable3());
            variable4.setText(item.getVariable4());
            variable5.setText(item.getVariable5());
            variable6.setText(item.getVariable6());
            variable7.setText(item.getVariable7());
            variable8.setText(item.getVariable8());
            estado.setText(item.getEstado());
            itemView.setOnClickListener((new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener2.onItemClick(item);
                }
            }));
        }
    }
}
