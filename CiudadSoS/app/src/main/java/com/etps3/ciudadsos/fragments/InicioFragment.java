package com.etps3.ciudadsos.fragments;

import android.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.etps3.ciudadsos.ciudadsos.R;

/**
 * Created by martin on 09-25-15.
 */
public class InicioFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.layout_f_inicio, container, false);
        ImageView vm= (ImageView) rootView.findViewById(R.id.imagePhone);
        vm.setImageResource(R.drawable.microphone);
        Log.i("InicioFragment","Llega a ejecutarse");
        //Log.i("Seleccionado item Inicio");
        return rootView;
    }


}
