package com.example.bicired;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapatador extends RecyclerView.Adapter<RecyclerViewAdapatador.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView descripcion, producto;
        ImageView fotoProducto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            descripcion=(TextView)itemView.findViewById(R.id.tvDescripcion);
            producto=(TextView)itemView.findViewById(R.id.tvProducto);
            fotoProducto=(ImageView)itemView.findViewById(R.id.imgBici1);
        }
    }

    public List<DescripcionProducto> productoLista;

    public RecyclerViewAdapatador(List<DescripcionProducto> productoLista) {
        this.productoLista = productoLista;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_producto,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.descripcion.setText(productoLista.get(position).getDescripcion());
        holder.producto.setText(productoLista.get(position).getProducto());
        holder.fotoProducto.setImageResource(productoLista.get(position).getImgProducto());
    }

    @Override
    public int getItemCount() {
        return productoLista.size();
    }
}
