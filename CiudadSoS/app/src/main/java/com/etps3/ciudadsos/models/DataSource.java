package com.etps3.ciudadsos.models;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.etps3.ciudadsos.TaskAsync.UpdateDataBase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by martin on 10-20-15.
 */
public class DataSource {

    private static final String TABLE_ENTIDAD = "ENTIDAD";
    private static final String TABLE_SUCURSAL = "SUCURSAL";
    private ReaderDbHelper openHelper=null;
    private SQLiteDatabase database=null;

    public static final String CREATE_ENTITY_SCRIPT ="CREATE TABLE IF NOT EXISTS ENTIDAD   (ID INTEGER PRIMARY KEY,NOMBRE varchar , DESCRIPCION varchar, URLIMAGEN varchar)";
    public static final String CREATE_SUCURSALES_SCRIPT ="CREATE TABLE IF NOT EXISTS SUCURSAL (ID INTEGER PRIMARY KEY,ID_ENTIDAD INT, NOMBRE varchar , DIRECCION varchar, COORDENADAS varchar,TELEFONO varchar)";
    public static final String INSERT_ENTITY_SCRIPT="insert into ENTIDAD values(?,?,?,?)";
    public static final String INSERT_SUCURSALES_SCRIPT="insert into SUCURSAL values(?,?,?,?,?)";
    private static final String QUERY_ENTITY_SCRIPT = "";
    private static final String QUERY_SUCURSALES_SCRIPT = "";

    public DataSource(Context context) {
        openHelper = new ReaderDbHelper(context);
        database = openHelper.getWritableDatabase();

    }

    public void init(Context context){
        database.execSQL("DELETE FROM ENTIDAD WHERE 1=1 ");
        database.execSQL("DELETE FROM SUCURSAL WHERE 1=1 ");
        List<Entidad> entidades = (new UpdateDataBase()).getListadoEntidades(context);
        for(Entidad et: entidades){
            Log.d(this.getClass().getName(), "Creanto entidad " + et.getNombre());
            database.execSQL("insert into  ENTIDAD values(" + et.getID() + ", " +
                    "'" + et.getNombre() + "', '" + et.getDescripcion() + "', '" + et.getUrlImagen() + "' )");
            for(Sucursal suc : et.getSucrusales()){
                Log.d(this.getClass().getName(), "Creanto sucursal " + suc.getID());
                database.execSQL("insert into  SUCURSAL values("+suc.getID()+","+suc.getID_ENTIDAD()+" ," +
                        "'"+suc.getNombre()+"', '"+suc.getDireccion()+"', '"+suc.getCoordenadas()+"', '"+
                        suc.getTelefono()+"' )");
            }
        }
    }

    public void sincronizeData(List<Entidad> entidades){
        if(database == null){
            return;
        }

        SQLiteStatement stmt = database.compileStatement(INSERT_SUCURSALES_SCRIPT);
        for(Entidad enti : entidades){
            stmt.bindLong(1,enti.getID() );
            stmt.bindString(2, enti.getNombre());
            stmt.bindString(3, enti.getDescripcion());
            stmt.bindString(4, enti.getUrlImagen());
            insertListSucursales(enti.getSucrusales());
            stmt.execute();
        }

    }

    private void insertListSucursales(List<Sucursal> sucursales){
        SQLiteStatement stmt = database.compileStatement(INSERT_SUCURSALES_SCRIPT);
        for(Sucursal enti : sucursales){
            stmt.bindLong(1, enti.getID());
            stmt.bindLong(2, enti.getID_ENTIDAD());
            stmt.bindString(3, enti.getNombre());
            stmt.bindString(4, enti.getCoordenadas());
            stmt.bindString(5, enti.getDireccion());
            stmt.bindString(6, enti.getTelefono());
            stmt.execute();
        }
    }

    public List<Entidad> getEntidades(int ID){
        List<Entidad> entidades= new ArrayList<Entidad>();
        Log.d(this.getClass().getName(), "Consultando las entidades");
        String[] columnsToReturn = { "ID", "NOMBRE" , "DESCRIPCION", "URLIMAGEN"};
        String[] selectionArgs = { ""+ID };
        String selection = "0 = ?";
        Cursor cursor = database.rawQuery("SELECT * FROM ENTIDAD", null);
        //Cursor cursor= database.query(TABLE_ENTIDAD,columnsToReturn,
          //                          selection, selectionArgs, null, null, null );
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    entidades.add(new Entidad(cursor.getInt(cursor.getColumnIndex("ID")), cursor.getString(cursor.getColumnIndex("NOMBRE")),
                            cursor.getString(cursor.getColumnIndex("DESCRIPCION")), cursor.getString(cursor.getColumnIndex("URLIMAGEN"))));
                } while (cursor.moveToNext());
            }else{
                Log.d(this.getClass().getName(), "cursor vacio de entidades");
            }
        }else{
            Log.d(this.getClass().getName(), "cursor null de entidades");
        }
        return entidades;
    }

    public List<Sucursal> getSucursales(int name){
        Log.d(this.getClass().getName(), "Extrayendo registros para entidad"+ name);
        List<Sucursal> entidades= new ArrayList<Sucursal>();
        Cursor cursor= database.rawQuery("SELECT ID, ID_ENTIDAD, NOMBRE, DIRECCION,COORDENADAS, TELEFONO  FROM SUCURSAL WHERE ID_ENTIDAD = "+name+" ", null );
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                Log.d(this.getClass().getName(), "Existen registros para entidad"+ name);
                do {
                    entidades.add(new Sucursal(cursor.getInt(0),cursor.getInt(1), cursor.getString(2), cursor.getString(3), cursor.getString(4),  cursor.getString(5)));
                } while (cursor.moveToNext());
            }else{Log.d(this.getClass().getName(), "No Existen registros para entidad"+ name);}
        }else{
            Log.d(this.getClass().getName(), "Cursor vacio para entidad"+ name);
        }
        return entidades;
    }

    public Entidad getDetalleByIdSucursal(int id){
        Sucursal sucursal = null;
        Cursor cursor= database.rawQuery("SELECT ID, ID_ENTIDAD, NOMBRE, DIRECCION,COORDENADAS, TELEFONO  FROM SUCURSAL WHERE ID = "+id, null );
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                sucursal = new Sucursal(cursor.getInt(1),cursor.getInt(1), cursor.getString(2), cursor.getString(3), cursor.getString(4),  cursor.getString(5));
            }
        }
        if(cursor == null){
            return null;
        }
        Entidad ls =null;

        Cursor cursorEntidad = database.rawQuery("SELECT ID, NOMBRE, DESCRIPCION,URLIMAGEN  FROM ENTIDAD WHERE ID = "+sucursal.getID_ENTIDAD(), null );
        if (cursorEntidad != null) {
            if (cursorEntidad.moveToFirst()) {
              ls=  new Entidad(cursorEntidad.getInt(cursorEntidad.getColumnIndex("ID")),
                        cursorEntidad.getString(cursorEntidad.getColumnIndex("NOMBRE")),
                        cursorEntidad.getString(cursorEntidad.getColumnIndex("DESCRIPCION")),
                        cursorEntidad.getString(cursorEntidad.getColumnIndex("URLIMAGEN")));
            }
        }

        if(ls == null){
            return null;
        }
        ls.getSucrusales().add(sucursal);

        return ls;
    }



}
