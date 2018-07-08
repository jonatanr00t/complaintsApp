package com.jonatan.criminalintent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

/**
 * Created by jona on 13/11/17.
 */

public abstract class SingleFragmentActivity extends FragmentActivity {

    protected abstract Fragment createFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        //Administrador de fragmentos de la libreria de soporte
        FragmentManager fm = getSupportFragmentManager();

        Fragment fragment = fm.findFragmentById(R.id.fragment_container);
        /*First, you ask the FragmentManager for the fragment with a container view ID of
        R.id.fragment_container. If this fragment is already in the list,
        the FragmentManager will return it.*/
        if(fragment == null){
            //si no existe cargo la variable con un nuevo fragmento
            fragment = createFragment();

            /*Create a new fragment transaction, include
            one add operation in it, and then commit it.*/
            fm.beginTransaction().add(R.id.fragment_container, fragment).commit();

            /*onCreateView(…) methods are called when
            you add the fragment to the FragmentManager.*/
        }
    }


}
