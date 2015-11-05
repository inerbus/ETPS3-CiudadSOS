package com.etps3.ciudadsos.models;
public class Sucursal {
    private int ID, ID_ENTIDAD;
    private String nombre, direccion, coordenadas, telefono;

    public Sucursal(int ID, int ID_ENTIDAD, String nombre, String direccion, String coordenadas, String telefono) {
        this.ID = ID;
        this.ID_ENTIDAD = ID_ENTIDAD;
        this.nombre = nombre;
        this.direccion = direccion;
        this.coordenadas = coordenadas;
        this.telefono = telefono;
    }

    public Sucursal() {
    }

    public int getID_ENTIDAD() {
        return ID_ENTIDAD;
    }

    public void setID_ENTIDAD(int ID_ENTIDAD) {
        this.ID_ENTIDAD = ID_ENTIDAD;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(String coordenadas) {
        this.coordenadas = coordenadas;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
