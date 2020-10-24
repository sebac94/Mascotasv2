package com.mismascotasfavoritas.presentador;

import android.content.Context;

import com.mismascotasfavoritas.fragments.InterfaceRecyclerView;
import com.mismascotasfavoritas.pojo.Mascota;
import com.mismascotasfavoritas.pojo.MascotaManager;

import java.util.ArrayList;


public class RecyclerViewPresentador implements InterfaceRecyclerViewPresentador {

    private InterfaceRecyclerView interfaceRecyclerView;
    private Context context;
    private MascotaManager constructorMascotas;
    private ArrayList<Mascota> mascotas;

    public RecyclerViewPresentador(InterfaceRecyclerView interfaceRecyclerView, Context context) {
        this.interfaceRecyclerView = interfaceRecyclerView;
        this.context = context;
        obtenerMascotasBaseDatos();
    }

    @Override
    public void obtenerMascotasBaseDatos() {
        constructorMascotas = new MascotaManager(context);
        mascotas = constructorMascotas.traeMascotas();
        this.mostrarMascotaRecyclerView();
    }

    @Override
    public void mostrarMascotaRecyclerView() {
        interfaceRecyclerView.inicializarAdaptador(interfaceRecyclerView.crearAdaptador(mascotas));
        interfaceRecyclerView.generarLinearLayoutVertical();
    }
}
