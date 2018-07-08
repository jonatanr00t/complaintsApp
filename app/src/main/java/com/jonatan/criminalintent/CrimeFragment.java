package com.jonatan.criminalintent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.jonatan.criminalintent.entities.Crime;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

/**
 * Created by jona on 08/11/17.
 */

public class CrimeFragment extends Fragment {

    private static final String ARG_CRIME_ID = "crime_id";

    private Crime mCrime;
    private EditText mTitleField;
    private Button mFechaButton;
    private CheckBox mSolvedCheckbox;

    /*Attaching arguments to a fragment must be done after the fragment is created but before it is
    added to an activity.
    To hit this window, Android programmers follow a convention of adding a static method named
    newInstance() to the Fragment class. This method creates the fragment instance and bundles up and
    sets its arguments.*/
    public static CrimeFragment newInstance(UUID crimeId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_CRIME_ID, crimeId);
        CrimeFragment fragment = new CrimeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
//        mCrime = new Crime();
        /*fragment access the intent that belongs to the hosting activity costs you the encapsulation.
        CrimeFragment is no longer a reusable building block. A better solution is to stash
        the crime ID someplace that belongs to CrimeFragment rather than keeping
        it in CrimeActivity’s personal space. The “someplace”that belongs to a fragment
        is known as its arguments bundle.*/
//        UUID crimeId = (UUID) getActivity().getIntent()
//                .getSerializableExtra(CrimeActivity.EXTRA_CRIME_ID);

//        When a fragment needs to access its arguments, it calls the Fragment method getArguments() and
//        then one of the type-specific “get” methods of Bundle
//        Back in CrimeFragment.onCreate(…), replace your shortcut code with retrieving the UUID from the
//        fragment arguments.
//        you should feel all warm and fuzzy inside for maintaining CrimeFragment’s independence.
        UUID crimeId = (UUID) getArguments().getSerializable(ARG_CRIME_ID);
        //le pasamos el contexto(getActivity()) a CrimeLab.get que a su vez llama al constructor y obtenemos
        //luego el crimen a través de su Id
        mCrime = CrimeLab.get(getActivity()).getCrime(crimeId);
    }


    /*onCreateView(…) methods are called when
    you add the fragment to the FragmentManager.*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        /*The Activity.findViewById(int) method that you used before is a convenience
        method that calls View.findViewById(int) behind the scenes. The Fragment class does not have a
                corresponding convenience method, so you have to call the real thing*/
        View v = inflater.inflate(R.layout.fragment_crime, container, false);

        mTitleField = (EditText) v.findViewById(R.id.crime_title);

        mTitleField.setText(mCrime.getTitle());

        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // This space intentionally left blank
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mCrime.setTitle(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // This space intentionally left blank
            }
        });

        mFechaButton = v.findViewById(R.id.crime_date);

//        SimpleDateFormat formateador = new SimpleDateFormat(
//                "E, dd 'de' MMMM 'de' yyyy", new Locale("es_ES"));
        SimpleDateFormat formateador = new SimpleDateFormat(
                "E, dd/MM/yyyy HH:mm:ss", new Locale("es_ES"));
        Date fechaDate = mCrime.getFecha();
        String fecha = formateador.format(fechaDate);

//        mFechaButton.setText(android.text.format.DateFormat.format("E, dd/MM/yyyy hh:mm:ss", mCrime.getFecha()));
        mFechaButton.setText(fecha);
        mFechaButton.setEnabled(false);

        mSolvedCheckbox = v.findViewById(R.id.crime_solved);

        mSolvedCheckbox.setChecked(mCrime.isSolved());

        mSolvedCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                //setear el booleano
                mCrime.setSolved(isChecked);
            }
        });

        return v;

    }

}
