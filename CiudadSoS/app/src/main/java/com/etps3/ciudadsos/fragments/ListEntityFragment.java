package com.etps3.ciudadsos.fragments;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
 import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.etps3.ciudadsos.adaptadores.EntidadesListAdapter;
import com.etps3.ciudadsos.ciudadsos.R;
import com.etps3.ciudadsos.models.DataSource;
import com.etps3.ciudadsos.models.Entidad;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListEntityFragment extends Fragment {
    public Fragment parentFr;

    public ListEntityFragment() {
        // Required empty public constructor
    }

    View rootView = null;
    List<Entidad> datos= null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView= inflater.inflate(R.layout.layout_f_listado_entidades, container, false);
        datos= getListEntidades();
        int idInicial=1;

        if(!getResources().getBoolean(R.bool.isLarge)) {
            Log.i(this.getClass().getName(), "Engresamos en forma corta");
            ListView lst = (ListView) rootView.findViewById(R.id.lvItems);
            lst.setAdapter(new EntidadesListAdapter(rootView.getContext(), datos));
            lst.setOnItemClickListener(new OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    createViewSucursal(position);
                }
            });

        }else{
            Log.i(this.getClass().getName(), "Engresamos en forma Larga");
            GridView lst = (GridView) rootView.findViewById(R.id.lvItems);
            lst.setAdapter(new EntidadesListAdapter(rootView.getContext(), datos));
            lst.setOnItemClickListener(new OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    createViewSucursal(position);
                }
            });

        }
        return rootView;
    }



    public void createViewSucursal(int position){
        Log.i(this.getClass().getName(), "Llega al create View Sucursal"+ position);
        ListSucursalesFragment ls = new ListSucursalesFragment();
        Bundle args = new Bundle();
        args.putInt("POSITION", datos.get(position).getID());
        ls.setArguments(args);
        int cargador = (getResources().getBoolean(R.bool.isLand))? R.id.fragmentSucursalLand:
                                R.id.fragmentListPort ;
        getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(cargador, ls)
                .commitAllowingStateLoss();

    }

    public void replaceEntityForSucursal(int ID){
        ListSucursalesFragment ls = new ListSucursalesFragment();
        Log.i("Revision", "Entra a la invocacion del frame principal sustitucion");
        getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentListPort, ls)
                .commitAllowingStateLoss();
    }

    public void createViewLarge(int ID){
        ListSucursalesFragment ls = new ListSucursalesFragment();
        Log.i("Revision", "Entra a la invocacion del frame principal sustitucion");
        getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentListPort, ls)
                .commitAllowingStateLoss();;
    }

    /**
     * Obtiene la lista de entidades
     * Este metodo se actualizara con un objeto que permita sincronizar por medio de WS el listado
     * de entidades a una db de la cual se extraera la informacion
     * @return
     */
    public List<Entidad> getListEntidades(){
        //ArrayList<Entidad> lst= new ArrayList<>();
        /*String[] entidades= rootView.getContext().getResources().getStringArray(R.array.entidadesSoS);
        lst.add(new Entidad(entidades[0], R.drawable.hospital2));
        lst.add(new Entidad(entidades[1], R.drawable.police));
        lst.add(new Entidad(entidades[2], R.drawable.cross2));
        lst.add(new Entidad(entidades[3], R.drawable.cross2));
        lst.add(new Entidad(entidades[4], R.drawable.proteccioncivil));
        lst.add(new Entidad(entidades[5], R.drawable.fireman));
        lst.add(new Entidad(entidades[6], R.drawable.proteccioncivil));*/
        DataSource dt = new DataSource(this.getContext());
        return dt.getEntidades(0);
    }

}
