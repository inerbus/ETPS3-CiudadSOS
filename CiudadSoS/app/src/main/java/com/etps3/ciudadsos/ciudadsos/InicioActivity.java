package com.etps3.ciudadsos.ciudadsos;




import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.AsyncTask;
import android.speech.RecognizerIntent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import android.os.Bundle;



import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.ImageView;


import com.etps3.ciudadsos.fragments.DialogoFinder;
import com.etps3.ciudadsos.fragments.EmergencyFragment;
import com.etps3.ciudadsos.fragments.ErrorFragment;
import com.etps3.ciudadsos.fragments.InicioFragment;
import com.etps3.ciudadsos.fragments.ListadoFragment;
import com.etps3.ciudadsos.models.DataSource;

import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;


public class InicioActivity extends ActionBarActivity {
    public int frameSelected= 1;
    public String selectNameClassFrame = "";
    public Fragment selected;
    TabsListener<InicioFragment> tblInicio = null;
    TabsListener<EmergencyFragment> tblFinManual = null;
    TabsListener<ListadoFragment> tblListado = null;
    ImageView phone;

    public void localizarEntidadMasCercana(String text){
        Log.i(this.getClass().getName().toString(), "" + text);
        if(text== null || text.isEmpty() || text.equals("")){
            lanzarFragmentoError(text);
            return;
        }
        lanzarFragmenteEntidadMasCercana(text);
    }
    
    public void lanzarFragmentoError(String text){
            ErrorFragment re = new ErrorFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(android.R.id.content, re)
                .commitAllowingStateLoss();
    }
    public void lanzarFragmenteEntidadMasCercana(String text){
        // Buscar entidad mas cercana
        EmergencyFragment re = new EmergencyFragment();
        Bundle args = new Bundle();
        args.putInt("POSITION", 1);
        re.setArguments(args);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(android.R.id.content, re).commitAllowingStateLoss();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //mCallbacks = (TaskCallbacks) this;
        DataSource dt = new DataSource(this);
        dt.init(this);

        ActionBar actionBar =(ActionBar) this.getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        boolean paginaRotate=false;
        this.tblListado = new TabsListener<ListadoFragment>(this, "Entidades", ListadoFragment.class, paginaRotate);
        boolean st1 = true , st2 = false, st3 = false;
        this.tblInicio= new TabsListener<InicioFragment>(this, "Inicio", InicioFragment.class, paginaRotate);

        actionBar.addTab(actionBar.newTab()
                        .setText(R.string.menu_inicio)
                        .setTabListener(tblInicio), 0, true
        );

        actionBar.addTab(actionBar.newTab().setText(R.string.menu_entidades)
                .setTabListener(tblListado)
                , 1, false);

        actionBar.addTab(actionBar.newTab().setText(R.string.menu_emergencia)
                .setTabListener(new TabsListener<EmergencyFragment>(this, "Emergencia", EmergencyFragment.class, paginaRotate))
                , 2, false);

    }

    /**
     * Ejecutara la accion del SpeachToText
     * @param view
     */
    public void irAListado(View view) {
        Log.d(this.getClass().getName(), "Se ejecuta el item ");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_inicio, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    public class TabsListener <T extends Fragment> implements ActionBar.TabListener {
        public Fragment fragment;
        public final String tag;
        boolean paginaRotate= false;
        public TabsListener(Activity activity, String tag, Class<T> cls,boolean paginaRotate) {
            this.tag = tag;
            fragment = Fragment.instantiate(activity, cls.getName());
            this.paginaRotate = paginaRotate;
        }


        public void onTabSelected(Tab tab, FragmentTransaction ft) {
            Log.i("TabListener","Es un tab select");
            if (fragment.getClass().toString().equalsIgnoreCase( EmergencyFragment.class.toString() )) {
                FragmentManager fm = getSupportFragmentManager();
                DialogoFinder finder = new DialogoFinder();
                finder.show(fm, "dialog1");
                return;
            }
            selected = fragment;
            ft.replace(android.R.id.content, fragment, tag);
        }

        public void onTabUnselected(Tab tab, FragmentTransaction ft) {
                ft.remove(fragment);
        }

        public void onTabReselected(Tab tab, FragmentTransaction ft) {
            if (fragment.getClass().toString().equalsIgnoreCase(EmergencyFragment.class.toString())) {
                FragmentManager fm = getSupportFragmentManager();
                DialogoFinder finder = new DialogoFinder();
                Log.i("TabListener","Antes del show");
                finder.show(fm, "dialog1");
                Log.i("TabListener", "Despues del show");
                return;
            }
        }
    }

    static interface TaskCallbacks {
        void onPreExecute();
        void onProgressUpdate(int progress);
        void onCancelled();
        void onPostExecute();
    }

    private TaskCallbacks mCallbacks;


}
