package com.mismascotasfavoritas.basededatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mismascotasfavoritas.pojo.Mascota;

import java.util.ArrayList;

public class FuncionesBase extends SQLiteOpenHelper {

       private Context context;

        public FuncionesBase(Context context) {
            super(context, Conexiones.NombreBaseDatos, null, Conexiones.VersionBaseDatos);
            this.context = context;

        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String queryCrearTablaMascota = " CREATE TABLE " + Conexiones.TABLA_MASCOTA + "(" +
                    Conexiones.TABLA_MASCOTA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    Conexiones.TABLA_MASCOTA_NOMBRE + " TEXT, " +
                    Conexiones.TABLA_MASCOTA_FOTO + " INTEGER " +
                    ")";

            String queryCrearTablaCalificacionesContacto = "CREATE TABLE " + Conexiones.TABLA_CALIFICACION_MASCOTA + "(" +
                    Conexiones.TABLA_CALIFICACION_MASCOTA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    Conexiones.TABLA_CALIFICACION_MASCOTA_ID_MASCOTA + " INTEGER, " +
                    Conexiones.TABLA_CALIFICACION_MASCOTA_NUMERO_CALIFICACION + " INTEGER, " +
                    "FOREIGN KEY (" + Conexiones.TABLA_CALIFICACION_MASCOTA_ID_MASCOTA + ") " +
                    "REFERENCES " + Conexiones.TABLA_MASCOTA + "(" + Conexiones.TABLA_MASCOTA_ID + ")" +
                    ")";

            db.execSQL(queryCrearTablaMascota);
            db.execSQL(queryCrearTablaCalificacionesContacto);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int i, int i1) {
            db.execSQL("DROP TABLE IF EXISTS " + Conexiones.TABLA_MASCOTA);
            db.execSQL("DROP TABLE IF EXISTS " + Conexiones.TABLA_CALIFICACION_MASCOTA);
            onCreate(db);
        }

        public ArrayList<Mascota> obtenerTodasMascotas() {
            ArrayList<Mascota> mascotas = new ArrayList<>();

            String query = "SELECT * FROM " + Conexiones.TABLA_MASCOTA;
            SQLiteDatabase db = this.getWritableDatabase();

            Cursor registros = db.rawQuery(query, null);

            while (registros.moveToNext()) {
                Mascota mascota = new Mascota();
                mascota.setId_mascota(registros.getInt(0));
                mascota.setNombre(registros.getString(1));
                mascota.setFoto(registros.getInt(2));

                String querycalificaciones = "SELECT SUM(" + Conexiones.TABLA_CALIFICACION_MASCOTA_NUMERO_CALIFICACION + ") as likes " +
                        " FROM " + Conexiones.TABLA_CALIFICACION_MASCOTA +
                        " WHERE " + Conexiones.TABLA_CALIFICACION_MASCOTA_ID_MASCOTA + "=" + mascota.getId_mascota();

                Cursor calificaciones = db.rawQuery(querycalificaciones, null);

                if (calificaciones.moveToNext()) {
                    mascota.setCalificacion(calificaciones.getInt(0));
                } else {
                    mascota.setCalificacion(0);
                }


                mascotas.add(mascota);
            }

            db.close();
            return mascotas;
        }


        public ArrayList<Mascota> obtenerMascotasFavoritas() {
            ArrayList<Mascota> mascotas = new ArrayList<>();

            String query = "SELECT * FROM " + Conexiones.TABLA_MASCOTA + " TM, " + Conexiones.TABLA_CALIFICACION_MASCOTA + " TC" +
                    " WHERE " + "TM." + Conexiones.TABLA_MASCOTA_ID + "=" + "TC." + Conexiones.TABLA_CALIFICACION_MASCOTA_ID_MASCOTA +
                    " ORDER BY " + "TC." + Conexiones.TABLA_CALIFICACION_MASCOTA_NUMERO_CALIFICACION + " DESC" + " LIMIT 5";
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor registros = db.rawQuery(query, null);

            while (registros.moveToNext()) {
                Mascota mascota = new Mascota();
                mascota.setId_mascota(registros.getInt(0));
                mascota.setNombre(registros.getString(1));
                mascota.setFoto(registros.getInt(2));

                String queryCalificaciones = "SELECT SUM(" + Conexiones.TABLA_CALIFICACION_MASCOTA_NUMERO_CALIFICACION + ") as likes " +
                        " FROM " + Conexiones.TABLA_CALIFICACION_MASCOTA +
                        " WHERE " + Conexiones.TABLA_CALIFICACION_MASCOTA_ID_MASCOTA + "=" + mascota.getId_mascota();

                Cursor calificaciones = db.rawQuery(queryCalificaciones, null);

                if (calificaciones.moveToNext()) {
                    mascota.setCalificacion(calificaciones.getInt(0));
                } else {
                    mascota.setCalificacion(0);
                }

                mascotas.add(mascota);
            }
            db.close();
            return mascotas;
        }

        public Mascota obtenerMascotaFavorita() {
            Mascota mascotafavorita = new Mascota();

            String query = "SELECT * FROM " + Conexiones.TABLA_MASCOTA + " TM, " + Conexiones.TABLA_CALIFICACION_MASCOTA + " TC" +
                    " WHERE " + "TM." + Conexiones.TABLA_MASCOTA_ID + "=" + "TC." + Conexiones.TABLA_CALIFICACION_MASCOTA_ID_MASCOTA +
                    " ORDER BY " + "TC." + Conexiones.TABLA_CALIFICACION_MASCOTA_NUMERO_CALIFICACION + " DESC" + " LIMIT 1";
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor registros = db.rawQuery(query, null);

            while (registros.moveToNext()) {

                mascotafavorita.setId_mascota(registros.getInt(0));
                mascotafavorita.setNombre(registros.getString(1));
                mascotafavorita.setFoto(registros.getInt(2));

                String queryCalificaciones = "SELECT SUM(" + Conexiones.TABLA_CALIFICACION_MASCOTA_NUMERO_CALIFICACION + ") as likes " +
                        " FROM " + Conexiones.TABLA_CALIFICACION_MASCOTA +
                        " WHERE " + Conexiones.TABLA_CALIFICACION_MASCOTA_ID_MASCOTA + "=" + mascotafavorita.getId_mascota();

                Cursor calificaciones = db.rawQuery(queryCalificaciones, null);

                if (calificaciones.moveToNext()) {
                    mascotafavorita.setCalificacion(calificaciones.getInt(0));
                } else {
                    mascotafavorita.setCalificacion(0);
                }


            }
            db.close();
            return mascotafavorita;
        }







        public void insertarMascota(ContentValues contentValues) {
            SQLiteDatabase db = this.getWritableDatabase();
            db.insert(Conexiones.TABLA_MASCOTA, null, contentValues);
            db.close();
        }

        public void insertarCalificacionesMascota(ContentValues contentValues) {
            SQLiteDatabase db = this.getWritableDatabase();
            db.insert(Conexiones.TABLA_CALIFICACION_MASCOTA, null, contentValues);
            db.close();
        }

        public int obtenerCalificacionMascota(Mascota mascota) {
            int calificacion = 0;

            String query = "SELECT COUNT(" + Conexiones.TABLA_CALIFICACION_MASCOTA_NUMERO_CALIFICACION + ")" +
                    " FROM " + Conexiones.TABLA_CALIFICACION_MASCOTA +
                    " WHERE " + Conexiones.TABLA_CALIFICACION_MASCOTA_ID_MASCOTA + "=" + mascota.getId_mascota();

            SQLiteDatabase db = this.getWritableDatabase();
            Cursor registros = db.rawQuery(query, null);

            if (registros.moveToNext()) {
                calificacion = registros.getInt(0);
            }

            db.close();
            return calificacion;
        }


        public void actualizarCalificacion(ContentValues contentValues){
              String id=contentValues.getAsString(Conexiones.TABLA_CALIFICACION_MASCOTA_ID_MASCOTA);
              String valor=contentValues.getAsString(Conexiones.TABLA_CALIFICACION_MASCOTA_NUMERO_CALIFICACION);


                SQLiteDatabase db = this.getReadableDatabase();
                db.execSQL("UPDATE "+Conexiones.TABLA_CALIFICACION_MASCOTA+" SET "+ Conexiones.TABLA_CALIFICACION_MASCOTA_NUMERO_CALIFICACION +
                        "= "+valor+ " WHERE "+ Conexiones.TABLA_CALIFICACION_MASCOTA_ID_MASCOTA + "=" + id);
                db.close();
            }


        }









