package com.jonatan.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.UUID;

public class CrimeActivity extends SingleFragmentActivity {

//    CrimeActivity should call CrimeFragment.newInstance(UUID) when it needs to create
//    a CrimeFragment. It will pass in the UUID it retrieved from its extra. Return to CrimeActivity
//    and, in createFragment(), retrieve the extra from CrimeActivity’s intent and pass it into
//    CrimeFragment.newInstance(UUID).
//    You can now also make EXTRA_CRIME_ID private since no other class will access that extra.
    //    public static final String EXTRA_CRIME_ID =
    //    "com.bignerdranch.android.criminalintent.crime_id";

    private static final String EXTRA_CRIME_ID =
            "com.bignerdranch.android.criminalintent.crime_id";

    public static Intent newIntent(Context packageContext, UUID crimeId) {
        Intent intent = new Intent(packageContext, CrimeActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeId);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
//        return new CrimeFragment();
        UUID crimeId = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);
        return CrimeFragment.newInstance(crimeId);
    }

}

/*public class CrimeActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        //Administrador de fragmentos de la libreria de soporte
        FragmentManager fm = getSupportFragmentManager();

        Fragment fragment = fm.findFragmentById(R.id.fragment_container);
        *//*First, you ask the FragmentManager for the fragment with a container view ID of
        R.id.fragment_container. If this fragment is already in the list,
        the FragmentManager will return it.*//*
        if(fragment == null){
            //si no existe cargo la variable con un nuevo CrimeFragment
            fragment = new CrimeFragment();

            *//*Create a new fragment transaction, include
            one add operation in it, and then commit it.*//*
            fm.beginTransaction().add(R.id.fragment_container, fragment).commit();

            *//*onCreateView(…) methods are called when
            you add the fragment to the FragmentManager.*//*
        }
    }
}*/
