package com.jonatan.criminalintent;


import android.support.v4.app.Fragment;

/**
 * Created by jona on 13/11/17.
 */

public class CrimeListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
