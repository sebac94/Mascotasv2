package com.mismascotasfavoritas.fragments;

import com.mismascotasfavoritas.adapter.MascotasAdapter;
import com.mismascotasfavoritas.pojo.Mascota;



import java.util.ArrayList;

public interface InterfaceRecyclerView {

    public void generarLinearLayoutVertical();

    public MascotasAdapter crearAdaptador(ArrayList<Mascota> mascotas);

    public void inicializarAdaptador(MascotasAdapter adaptador);

}
