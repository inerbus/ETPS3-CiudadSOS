package com.etps3.ciudadsos.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by martin on 10-20-15.
 */
public class Entidad {
    private int ID;
    private String nombre, descripcion , UrlImagen;
    private List<Sucursal> sucrusales= new ArrayList<Sucursal>();

    public Entidad(int ID, String nombre, String descripcion, String urlImagen) {
        this.ID = ID;
        this.nombre = nombre;
        this.descripcion = descripcion;
        UrlImagen = urlImagen;
    }

    public Entidad() {
    }

    public List<Sucursal> getSucrusales() {
        return sucrusales;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrlImagen() {
        return UrlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        UrlImagen = urlImagen;
    }
}
