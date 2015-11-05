package com.etps3.ciudadsos.fragments;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.etps3.ciudadsos.ciudadsos.InicioActivity;
import com.etps3.ciudadsos.ciudadsos.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DialogoFinder extends DialogFragment {
    private View mLayout;
    private ViewGroup mContainer;

    public DialogoFinder() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContainer = container;
        if (getShowsDialog()) {
            return super.onCreateView(inflater, container, savedInstanceState);
        }
        return getLayout(inflater, container);
    }
    private View getLayout(LayoutInflater inflater, ViewGroup container) {
        mLayout = inflater.inflate(R.layout.layout_dialog_finder_custom, container, false);
        return mLayout;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getContext())
                .setPositiveButton("Buscar",  new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Obtener el texto ingresado, llamar al método
                        //en la actividad principal y cerrar el diálogo
                        TextView txtv = (TextView) mLayout.findViewById(R.id.editText);
                        ((InicioActivity) getActivity()).localizarEntidadMasCercana(txtv.getText().toString());
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setNeutralButton(" ", null)
                .setView(getLayout(LayoutInflater.from(getContext()), mContainer))
                .create()
                ;
        /*
        // Seleccionar y mostrar el layout a mostrar
        Log.i(this.getClass().getName().toString(), "Inicia On create Dialog");
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this.getContext());
        Log.i(this.getClass().getName().toString(), "Crea alert Dialog");
        final View view = getActivity().getLayoutInflater().inflate(R.layout.layout_dialog_finder_custom, null);
        Log.i(this.getClass().getName().toString(), "Infla Vista");
        alertDialogBuilder.setView(view);
        Log.i(this.getClass().getName().toString(), "Setea VIsta");
        //Seteamos el título

        alertDialogBuilder.setTitle("Busqueda por texto");
        //El botón cancelar
        alertDialogBuilder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        //El botón aceptar
        alertDialogBuilder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Obtener el texto ingresado, llamar al método
                //en la actividad principal y cerrar el diálogo
                TextView txtv = (TextView) view.findViewById(R.id.editText);
                ((InicioActivity) getActivity()).localizarEntidadMasCercana(txtv.getText().toString());
                dialog.dismiss();
            }
        });
        Log.i(this.getClass().getName().toString(), "Crea otras cosas");
        return alertDialogBuilder.create();*/
    }

}
