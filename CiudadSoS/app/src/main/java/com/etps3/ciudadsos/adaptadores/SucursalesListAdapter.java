package com.etps3.ciudadsos.adaptadores;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.etps3.ciudadsos.ciudadsos.R;
import com.etps3.ciudadsos.models.Sucursal;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by martin on 10-21-15.
 */
public class SucursalesListAdapter extends ArrayAdapter {
    List<Sucursal> datos;
    Context context;
    public SucursalesListAdapter(Context context,  List<Sucursal> datos) {
        super(context, R.layout.layout_personalize_sucursales, datos);
        this.datos=datos;
        Log.i("Revision", "Cantidad de sucursales" + datos.size());
        this.context= context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.i("Revision", "Entrando a la posicion "+ position);

        LayoutInflater inflater = LayoutInflater.from(context);
        View item = inflater.inflate(R.layout.layout_personalize_sucursales, null);
        ((TextView)item.findViewById(R.id.txtNameSucursal))

        .setText(datos.get(position).getNombre());
        ((TextView)item.findViewById(R.id.txtDirSucursal))
                .setText(datos.get(position).getDireccion());
        return item;
    }
}
