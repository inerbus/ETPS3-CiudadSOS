package com.etps3.ciudadsos.adaptadores;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.content.*;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.etps3.ciudadsos.beans.Entidad;
import com.etps3.ciudadsos.ciudadsos.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by martin on 09-25-15.
 */
public class EntidadesListAdapter extends ArrayAdapter {

    private Context context;
    private List<Entidad> datos = new ArrayList<Entidad>();

    public EntidadesListAdapter(Context context, ArrayList<Entidad> datos) {
        super(context, R.layout.layout_personalize_entidades, datos);
        this.context= context;
        this.datos= datos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // En primer lugar "inflamos" una nueva vista, que será la que se
        // mostrará en la celda del ListView. Para ello primero creamos el
        // inflater, y después inflamos la vista.
        LayoutInflater inflater = LayoutInflater.from(context);
        View item = inflater.inflate(R.layout.layout_personalize_entidades, null);

        // A partir de la vista, recogeremos los controles que contiene para
        // poder manipularlos.
        // Recogemos el ImageView y le asignamos una foto.
        ImageView imagen = (ImageView) item.findViewById(R.id.imageView);
        imagen.setImageResource(datos.get(position).getDrawableImageID());

        TextView tx =(TextView) item.findViewById(R.id.textView);
        tx.setText(datos.get(position).getNombre());
        LinearLayout ly=(LinearLayout) item.findViewById(R.id.layoutcolor);
        switch (position){
            case 0:
                ly.setBackgroundColor(Color.parseColor("#DE09D4A9"));
                break;
            case 1:
                ly.setBackgroundColor(Color.parseColor("#FFFFAD7E"));
                break;
            case 2:
                ly.setBackgroundColor(Color.parseColor("#bbd6ff"));
                break;
            case 3:
                ly.setBackgroundColor(Color.parseColor("#FFFF4BBB"));
                break;
            case 4:
                ly.setBackgroundColor(Color.parseColor("#DED49800"));
                break;
            case 5:
                ly.setBackgroundColor(Color.parseColor("#DED40092"));
                break;
            case 6:
                ly.setBackgroundColor(Color.parseColor("#DE87D853"));
                break;
        }
        // Devolvemos la vista para que se muestre en el ListView.
        return item;
    }
}
