package com.etps3.ciudadsos.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.etps3.ciudadsos.ciudadsos.R;
import com.etps3.ciudadsos.models.DataSource;
import com.etps3.ciudadsos.models.Entidad;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
/**
 * Created by martin on 09-25-15.
 */
public class EmergencyFragment extends Fragment {

MapView mapView;
    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.layout_detalle, container, false);
        int position = ( this.getArguments() == null )? 1 : this.getArguments().getInt("POSITION");
        Log.d(this.getClass().getName(), "El id de la sucursal selec es " + position);
        Entidad entity=(new DataSource(rootView.getContext())).getDetalleByIdSucursal(position);

        ((TextView)rootView.findViewById(R.id.txtEntidad))
                .setText(entity.getNombre()+ " - " + entity.getSucrusales().get(0).getNombre());

        ((TextView)rootView.findViewById(R.id.txtDireccion))
                .setText(entity.getSucrusales().get(0).getDireccion());

        ((TextView)rootView.findViewById(R.id.txtTelefono))
                .setText(entity.getSucrusales().get(0).getTelefono());
        Log.i("InicioFragment", "Llega a ejecutarse");

        this.getActivity().getSupportFragmentManager().beginTransaction()
                .add(R.id.webView, new MapsActivity()).commitAllowingStateLoss();
        Log.i("InicioFragment", "Llega a ejecutarse despues del map invoq");
        return rootView;
    }

}
