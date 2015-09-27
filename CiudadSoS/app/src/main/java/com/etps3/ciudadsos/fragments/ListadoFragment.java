package com.etps3.ciudadsos.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.etps3.ciudadsos.adaptadores.EntidadesListAdapter;
import com.etps3.ciudadsos.beans.Entidad;
import com.etps3.ciudadsos.ciudadsos.R;

import java.util.ArrayList;

/**
 * Created by martin on 09-25-15.
 */
public class ListadoFragment extends Fragment {
    View rootView;
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        rootView= inflater.inflate(R.layout.layout_f_listado_entidades, container, false);
        Log.i("ListadoFragment", "Llega a ejecutarse");
        ListView lst = (ListView) rootView.findViewById(R.id.lvItems);

        ArrayList<Entidad> datos= getListEntidades();
        lst.setAdapter(new EntidadesListAdapter(rootView.getContext(), datos));
        return rootView;
    }

    /**
     * Obtiene la lista de entidades
     * Este metodo se actualizara con un objeto que permita sincronizar por medio de WS el listado
     * de entidades a una db de la cual se extraera la informacion
     * @return
     */
    public ArrayList<Entidad> getListEntidades(){

        ArrayList<Entidad> lst= new ArrayList<Entidad>();

        String[] entidades= rootView.getContext().getResources().getStringArray(R.array.entidadesSoS);
        lst.add(new Entidad(entidades[0], R.drawable.hospital2));
        lst.add(new Entidad(entidades[1], R.drawable.pnc));
        lst.add(new Entidad(entidades[2], R.drawable.cruzroja));
        lst.add(new Entidad(entidades[3], R.drawable.cross2));
        lst.add(new Entidad(entidades[4], R.drawable.proteccioncivil));
        lst.add(new Entidad(entidades[5], R.drawable.fireman));
        lst.add(new Entidad(entidades[6], R.drawable.comandossalvamentos));

        return lst;

    }

}
