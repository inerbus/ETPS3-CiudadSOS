package com.etps3.ciudadsos.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.etps3.ciudadsos.adaptadores.SucursalesListAdapter;
import com.etps3.ciudadsos.ciudadsos.R;
import com.etps3.ciudadsos.models.DataSource;
import com.etps3.ciudadsos.models.Sucursal;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListSucursalesFragment extends Fragment {

    List<Sucursal> datos;
    int id;
    public ListSucursalesFragment() {
        // Required empty public constructor
    }
    View rootView = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(this.getClass().getName(), "Entrando en el create del list sucursal");
        rootView= inflater.inflate(R.layout.layout_f_listado_sucursales, container, false);

        ListView lst = (ListView) rootView.findViewById(R.id.lvItemsSucursal);
        int idp = (this.getArguments() != null)?this.getArguments().getInt("POSITION"):1;
        datos = (new DataSource(this.getContext())).getSucursales(idp);

        lst.setAdapter(new SucursalesListAdapter(rootView.getContext(),
                datos));

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Obtener el id de la sucursal
                createViewSucursal(position);
            }
        });
        return rootView;
    }


    public void createViewSucursal(int position){
        Log.i("Revision", "Llega al create View Sucursal" + position);
        EmergencyFragment ls = new EmergencyFragment();
        Bundle args = new Bundle();
        args.putInt("POSITION", datos.get(position).getID());
        ls.setArguments(args);
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        Log.i(this.getClass().getName().toString(), "El valor de island es : "+getResources().getBoolean(R.bool.isLand));


        if(getResources().getBoolean(R.bool.isLand) &&
                getResources().getBoolean(R.bool.isLarge)){
            Log.d(this.getClass().getName(), "Esta cargando en fragmentDetalleLand");
            transaction.replace(R.id.fragmentDetalleLand, ls);
        }


        if(!getResources().getBoolean(R.bool.isLand) &&
                getResources().getBoolean(R.bool.isLarge)){
            Log.d(this.getClass().getName(), "Esta cargando en fragmentListPort por no ser land");
            transaction.replace(R.id.fragmentListPort, ls);
        }

        if(getResources().getBoolean(R.bool.isLand) &&
                !getResources().getBoolean(R.bool.isLarge)){
            Log.d(this.getClass().getName(), "Esta cargando en fragmentSucursalLand por no ser Large");
            transaction.replace(R.id.fragmentSucursalLand, ls);
        }

        if(!getResources().getBoolean(R.bool.isLand)
                && !getResources().getBoolean(R.bool.isLarge)) {
            Log.d(this.getClass().getName(), "Esta cargando en fragmentListPort por no ser Large ni land");
            transaction.replace(R.id.fragmentListPort, ls);
        }
        transaction.addToBackStack(null);
        transaction.commitAllowingStateLoss();

    }
}
