package com.etps3.ciudadsos.TaskAsync;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.etps3.ciudadsos.ciudadsos.R;
import com.etps3.ciudadsos.models.Entidad;
import com.etps3.ciudadsos.models.Sucursal;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by martin on 10-20-15.
 */
public class UpdateDataBase <Void, Integer, Long>  {

    public  List<Entidad> getListadoEntidades(Context context){
        List<Entidad> ls = new ArrayList<Entidad>();
        String[] entidades= context.getResources().getStringArray(R.array.entidadesSoS);
        int i = 1;
        for(String enti : entidades){
            Log.d(UpdateDataBase.class.getName(), "Leyendo entidad" + enti);
            try {
                JSONObject js =new JSONObject(enti);
                Entidad et=new Entidad(js.getInt("id"),
                                        js.getString("nombre"),
                                        js.getString("descripcion"),
                                        js.getString("url_imagen"));

                String[] sucursales= context.getResources().getStringArray(R.array.sucursales);
                for(String suc : sucursales){
                    JSONObject jsSuc =new JSONObject(suc);
                    et.getSucrusales().add(new Sucursal(i, js.getInt("id"),
                            js.getString("nombre")+" "+jsSuc.getString("nombre"),
                            jsSuc.getString("direccion"),
                            jsSuc.getString("coordenadas"),
                            jsSuc.getString("telefono")));
                    i++;
                }

                ls.add(et);
            } catch (JSONException e) {
                Log.e(UpdateDataBase.class.toString(), e.getMessage(), e);
            }
        }
        return ls;
    }
}
