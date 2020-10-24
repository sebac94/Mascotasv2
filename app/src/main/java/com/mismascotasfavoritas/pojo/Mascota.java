package com.mismascotasfavoritas.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Mascota implements Parcelable {
    private String nombre;
    private int foto;
    private int calificacion;
    private int id_mascota;
    private ArrayList<Integer> imagenesMascotas=new ArrayList<Integer>();

    public Mascota(){}


    public Mascota(int foto, String nombre) {
        this.nombre = nombre;
        this.foto = foto;
        this.calificacion = 0;

    }

    private Mascota(Parcel in) {
        nombre = in.readString();
        calificacion = in.readInt();
        foto = in.readInt();
    }

    public int getId_mascota() {
        return id_mascota;
    }

    public void setId_mascota(int id) {
        this.id_mascota = id;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void rateUp() {
        this.calificacion++;
    }

    public void rateDown() {
        if (this.calificacion > 0) this.calificacion--;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Integer> getImagen(){
        return imagenesMascotas;
    }


    public void setImagen(int imagen){
        imagenesMascotas.add(imagen);
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(nombre);
        parcel.writeInt(calificacion);
        parcel.writeInt(foto);
    }

    public static final Parcelable.Creator<Mascota> CREATOR = new Parcelable.Creator<Mascota>() {
        @Override
        public Mascota createFromParcel(Parcel parcel) {
            return new Mascota(parcel);
        }

        @Override
        public Mascota[] newArray(int i) {
            return new Mascota[0];
        }
    };
}
