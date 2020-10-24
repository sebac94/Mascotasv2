package com.mismascotasfavoritas.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mismascotasfavoritas.pojo.Mascota;
import com.mismascotasfavoritas.R;

import com.mismascotasfavoritas.pojo.MascotaManager;

import java.util.ArrayList;



public class MascotasAdapter extends RecyclerView.Adapter<MascotasAdapter.MyViewHolder> {

    private ArrayList<Mascota> mDataSet;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageViewMainListItem;
        private final TextView textViewName;
        private final TextView textViewRateInt;
        private final ImageView imageViewRateUp;
        private final ImageView imageViewRateDown;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageViewMainListItem = itemView.findViewById(R.id.image_main_list_item);
            textViewName = itemView.findViewById(R.id.txt_nombreFavorito);
            textViewRateInt = itemView.findViewById(R.id.text_rate_int);
            imageViewRateUp = itemView.findViewById(R.id.image_bone_rate_up);
            imageViewRateDown = itemView.findViewById(R.id.image_bone_rate_down);
        }
    }

    public MascotasAdapter(ArrayList<Mascota> dataSet) {
        mDataSet = dataSet;
    }

    @Override
    public MascotasAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View element = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.mascotas_list_item, parent, false);
        return new MyViewHolder(element);

    }

    @Override
    public void onBindViewHolder(@NonNull MascotasAdapter.MyViewHolder holder, final int position) {

        final Mascota mascota = mDataSet.get(position);
        final TextView textRateInt = holder.textViewRateInt;
        holder.imageViewMainListItem.setImageResource(mascota.getFoto());
        holder.textViewName.setText(mascota.getNombre());
        textRateInt.setText(String.valueOf(mascota.getCalificacion()));
        holder.imageViewRateUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mascota.rateUp();
                textRateInt.setText(String.valueOf(mascota.getCalificacion()));
                MascotaManager.actualizarCalificacion(mascota.getId_mascota(),mascota.getCalificacion());

            }
        });
        holder.imageViewRateDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mascota.rateDown();
                textRateInt.setText(String.valueOf(mascota.getCalificacion()));
                MascotaManager.actualizarCalificacion(mascota.getId_mascota(),mascota.getCalificacion());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }
}
