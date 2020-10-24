package com.mismascotasfavoritas.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.mismascotasfavoritas.basededatos.FuncionesBase;
import com.mismascotasfavoritas.pojo.Mascota;
import com.mismascotasfavoritas.R;
import com.mismascotasfavoritas.adapter.MascotasAdapter;
import com.mismascotasfavoritas.presentador.RecyclerViewPresentador;

import java.util.ArrayList;

public class RecyclerViewFragment extends Fragment implements InterfaceRecyclerView {


    FuncionesBase conexion;

  //  public static ArrayList<Mascota> mascotasFavoritas = new ArrayList<>();

    MascotasAdapter adapter;
    public RecyclerView recyclerViewListaMascotas;
    public RecyclerViewPresentador presentador;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragmentrecyclerview_listado,container,false);
        recyclerViewListaMascotas = v.findViewById(R.id.recycler_view_layout);

        presentador = new RecyclerViewPresentador(this, getContext());

        return v;
    }


 /*   public static void calcularDatosFavorito(FuncionesBase conexion) {

        mascotasFavoritas= conexion.obtenerMascotasFavoritas();
    }
*/
    public static void cargarImagenes(Mascota mascota, int cantidad) {

        for (int x = 0; x <= cantidad; x++) {

            mascota.setImagen(mascota.getFoto());

        }
    }


    @Override
    public void generarLinearLayoutVertical() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewListaMascotas.setLayoutManager(layoutManager);
    }

    @Override
    public MascotasAdapter crearAdaptador(ArrayList<Mascota> mascotas) {

       MascotasAdapter adaptador = new MascotasAdapter(mascotas);
       return adaptador;
    }

    @Override
    public void inicializarAdaptador(MascotasAdapter adaptador) {
        recyclerViewListaMascotas.setAdapter(adaptador);
    }
}

