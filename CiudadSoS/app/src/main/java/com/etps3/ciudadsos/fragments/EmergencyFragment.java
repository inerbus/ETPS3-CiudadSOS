package com.etps3.ciudadsos.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.etps3.ciudadsos.ciudadsos.R;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by martin on 09-25-15.
 */
public class EmergencyFragment extends Fragment {

MapView mapView;
    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.layout_detalle, container, false);
        //mapView = (MapView) rootView.findViewById(R.id.mapview);
        //mapView.onCreate(savedInstanceState);
       // map = mapView.getMap();
        //map.getUiSettings().setMyLocationButtonEnabled(false);
        //map.setMyLocationEnabled(true);

        //MapsInitializer.initialize(this.getActivity());


        /*CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(43.1, -87.9), 10);
        map.animateCamera(cameraUpdate);*/

        Log.i("InicioFragment", "Llega a ejecutarse");
        //Log.i("Seleccionado item Inicio");
        return rootView;
    }

}
