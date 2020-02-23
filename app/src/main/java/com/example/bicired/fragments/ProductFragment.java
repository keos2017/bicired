package com.example.bicired.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bicired.DescripcionProducto;
import com.example.bicired.R;
import com.example.bicired.RecyclerViewAdapatador;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductFragment extends Fragment {

    private RecyclerView recyclerViewProducto;
    private RecyclerViewAdapatador adaptadorProducto;


    public ProductFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View vista =inflater.inflate(R.layout.fragment_product, container, false);

        recyclerViewProducto=(RecyclerView)vista.findViewById(R.id.recyclerProducto);
        recyclerViewProducto.setLayoutManager(new LinearLayoutManager(getContext()));

        adaptadorProducto=new RecyclerViewAdapatador(obtenerProducto());
        recyclerViewProducto.setAdapter(adaptadorProducto);




        return vista;
    }



        public List<DescripcionProducto> obtenerProducto(){
            List<DescripcionProducto> productos=new ArrayList<>();
            productos.add(new DescripcionProducto("Benotto Marlin","R26 18V.Rojo",R.drawable.bici1));
            productos.add(new DescripcionProducto("Helion GT","R27.5, 29.Negro Mate",R.drawable.bici2));
            productos.add(new DescripcionProducto("MTB GW JACKAL","R29 18V.Negro Mate | Verde Olivo",R.drawable.bici3));
            productos.add(new DescripcionProducto("Conted 3 NEG/BCO(M)","Llantas S-R3 AC, 700*28.Negro|Blanco",R.drawable.bici4));

            return productos;
        }

}
