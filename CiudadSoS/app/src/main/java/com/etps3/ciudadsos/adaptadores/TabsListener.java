package com.etps3.ciudadsos.adaptadores;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;

import com.etps3.ciudadsos.ciudadsos.R;

/**
 * Created by martin on 09-25-15.
 */
public class TabsListener  implements android.app.ActionBar.TabListener {
    private Fragment fragment;
    private final String tag;

    public TabsListener( String tag, Fragment fragment) {
        this.tag = tag;
        this.fragment= fragment;
    }

    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
    //    if(tab.getPosition() == 1){
            ft.replace(R.id.FrgListado, fragment, tag);
            //ft.replace(android.R.id.content, fragment, tag);
        //}else{
//
  //      }

    }

    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
        ft.remove(fragment);
    }

    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {}




}
