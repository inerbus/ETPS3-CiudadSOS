package com.etps3.ciudadsos.fragments;

import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
    LayoutInflater inflater= null;

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        this.inflater= inflater;

        rootView = inflater.inflate(R.layout.layout_f_main_list, container, false);
        boolean isLarge = getResources().getBoolean(R.bool.isLarge);
        boolean isLand = getResources().getBoolean(R.bool.isLand);
        Log.i(this.getClass().getName(), "largo: " + isLarge + " Land:"+ isLand);
        if(isLand){
            createViewLand(savedInstanceState);
        }else{
            createViewPort(savedInstanceState);
        }
        return rootView;
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

    public void createViewPort(Bundle savedInstanceState ){
        // Crear entidades, que es comun a las 2
        getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragmentListPort,new ListEntityFragment() )
                .commitAllowingStateLoss();
    }


    public void createViewLand(Bundle savedInstanceState){
        getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragmentListPort,new ListEntityFragment() )
                .commitAllowingStateLoss();

        getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentSucursalLand, new ListSucursalesFragment())
                .commitAllowingStateLoss();

        if(getResources().getBoolean(R.bool.isLarge)){

            getActivity()
                    .getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentDetalleLand, new EmergencyFragment())
                    .commitAllowingStateLoss();

        }

    }

}
