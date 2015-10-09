package com.etps3.ciudadsos.beans;

import android.graphics.Color;
import android.provider.CalendarContract;

/**
 * Created by martin on 09-25-15.
 */
public class Entidad {
    private String nombre;
    private int drawableImageID;


    public Entidad(String nombre, int drawableImageID) {
        this.nombre = nombre;
        this.drawableImageID = drawableImageID;
    }



    public String getNombre() {

        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDrawableImageID() {
        return drawableImageID;
    }

    public void setDrawableImageID(int drawableImageID) {
        this.drawableImageID = drawableImageID;
    }
}
