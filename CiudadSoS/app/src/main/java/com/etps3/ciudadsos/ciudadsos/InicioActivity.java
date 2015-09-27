package com.etps3.ciudadsos.ciudadsos;



import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import android.os.Bundle;

import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.etps3.ciudadsos.adaptadores.TabsListener;
import com.etps3.ciudadsos.fragments.EmergencyFragment;
import com.etps3.ciudadsos.fragments.InicioFragment;
import com.etps3.ciudadsos.fragments.ListadoFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBarActivity;



public class InicioActivity extends ActionBarActivity {

    ImageView phone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_inicio);

        //ImageView phone=(ImageView)findViewById(R.id.imagePhone);
        //phone.setImageResource(R.drawable.microphone);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        actionBar.addTab(actionBar.newTab().setText(R.string.menu_inicio)
                        .setTabListener(new TabsListener<InicioFragment>(this, "Inicio" ,  InicioFragment.class))
        );

        actionBar.addTab(actionBar.newTab().setText(R.string.menu_entidades)
                .setTabListener(new TabsListener<ListadoFragment>(this, "Entidades" ,  ListadoFragment.class)));

        actionBar.addTab(actionBar.newTab().setText(R.string.menu_emergencia)
                .setTabListener(new TabsListener<EmergencyFragment>(this, "Emergencia" ,  EmergencyFragment.class)));

    }

    public void irAListado(View view) {
        //Intent i = new Intent(this, ListadoCategorias.class );
        //startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_inicio, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        /*switch (item.getItemId()){
            case  R.id.inicio:
                return true;

            case  R.id.emergencia:
                return true;

            case  R.id.entidades:
                return true;

        }*/

        //noinspection Simplifia
        return super.onOptionsItemSelected(item);
    }


    protected class MyTabsListener implements ActionBar.TabListener{
        private Fragment fragment;

        public MyTabsListener(Fragment ft){
            fragment=ft;
        }

        @Override
        public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
            //llega aqu
            Log.i("MyTabsListener","llega a onTabSelected");

            ft.add(R.id.FrgListado, fragment, null);
        }

        @Override
        public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

        }

        @Override
        public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
            ft.remove(fragment);
        }
    }

    public class TabsListener <T extends Fragment> implements ActionBar.TabListener {

        private Fragment fragment;
        private final String tag;

        public TabsListener(Activity activity, String tag, Class<T> cls) {
            this.tag = tag;
            fragment = Fragment.instantiate(activity, cls.getName());
        }

        public void onTabSelected(Tab tab, FragmentTransaction ft) {
            ft.replace(android.R.id.content, fragment, tag);
        }

        public void onTabUnselected(Tab tab, FragmentTransaction ft) {
            ft.remove(fragment);
        }

        public void onTabReselected(Tab tab, FragmentTransaction ft) {}

    }

}
