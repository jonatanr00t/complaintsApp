package com.jonatan.criminalintent;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.jonatan.criminalintent.entities.Crime;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by jona on 13/11/17.
 */

public class CrimeListFragment extends Fragment {

    private RecyclerView mCrimeRecyclerView;
    private CrimeAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_crime_list, container, false);

        mCrimeRecyclerView = view.findViewById(R.id.crime_recycler_view);

        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        List<Crime> crimes = crimeLab.getCrimes();

//        call notifyDataSetChanged() if the CrimeAdapter is already set up
        if(mAdapter == null) {
            mAdapter = new CrimeAdapter(crimes);
            //Supongo que aquí al seteaer el adaptador con la lista de crimenes, el RecyclerView hace su magia
            //y crea las vistas
            mCrimeRecyclerView.setAdapter(mAdapter);
        }else{
            mAdapter.notifyDataSetChanged();
        }
    }

    //    defining the ViewHolder as an inner class in CrimeListFragment
    private class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //        public TextView mTitleTextView;
        private TextView mTitleTextView;
        private TextView mDateTextView;
        private CheckBox mSolvedCheckBox;

        /*ViewHolder can relieve a lot of this pain. By stashing the results of these findViewById(int) calls,
        you only have to spend that time in createViewHolder(…). When onBindViewHolder(…) is called, the
        work is already done. Which is nice, because onBindViewHolder(…) is called much more often than
        onCreateViewHolder(…).
        However, that binding process is a little more complicated now. Add a bindCrime(Crime) method to
        CrimeHolder to clean things up a bit.*/
        private Crime mCrime;


        public CrimeHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
//            mTitleTextView = (TextView) itemView;
            mTitleTextView = itemView.findViewById(R.id.list_item_crime_title_text_view);
            mDateTextView = itemView.findViewById(R.id.list_item_crime_date_text_view);
            mSolvedCheckBox = itemView.findViewById(R.id.list_item_crime_solved_check_box);
        }

        public void bindCrime(Crime crime) {
            mCrime = crime;
            mTitleTextView.setText(mCrime.getTitle());

            SimpleDateFormat formateador = new SimpleDateFormat(
                    "E, dd/MM/yyyy HH:mm", new Locale("es_ES"));
            Date fechaDate = mCrime.getFecha();
            String fecha = formateador.format(fechaDate);

            mDateTextView.setText(fecha.toString());
            mSolvedCheckBox.setChecked(mCrime.isSolved());

            //Agregamos listener oara mabiar el estado del crimen desde la lista
            mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    //setear el booleano
                    mCrime.setSolved(isChecked);
                }
            });
        }

        @Override
        public void onClick(View v) {
//            Toast.makeText(getActivity(),
//                    mCrime.getTitle() + " clicked!", Toast.LENGTH_SHORT)
//                    .show();
//            Intent intent = new Intent(getActivity(), CrimeActivity.class);
            Intent intent = CrimeActivity.newIntent(getActivity(), mCrime.getId());
            startActivity(intent);
        }
    }

    //    DEFINIMOS EL ADAPTADOR
    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder> {
        private List<Crime> mCrimes;

        public CrimeAdapter(List<Crime> crimes) {
            mCrimes = crimes;
        }


        /*onCreateViewHolder is called by the RecyclerView when it needs a new View to display an item. In
        this method, you create the View and wrap it in a ViewHolder. The RecyclerView does not expect that
        you will hook it up to any data yet.
        For the View, you inflate a layout from the Android standard library called simple_list_item_1. This
        layout contains a single TextView, styled to look nice in a list. Later in the chapter, you will make a
        more advanced View for the list items.*/
        @Override
        public CrimeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater
                    .inflate(R.layout.list_item_crime, parent, false);
            return new CrimeHolder(view);
        }

        /*onBindViewHolder: This method will bind a ViewHolder’s View to your model object. It receives
        the ViewHolder and a position in your data set. To bind your View, you use that position to find the
        right model data. Then you update the View to reflect that model data.
        In your implementation, that position is the index of the Crime in your array. Once you pull it out, you
        bind that Crime to your View by sending its title to your ViewHolder’s TextView.*/
        @Override
        public void onBindViewHolder(CrimeHolder holder, int position) {
            Crime crime = mCrimes.get(position);
//            holder.mTitleTextView.setText(crime.getTitle());
            holder.bindCrime(crime);
        }

        @Override
        public int getItemCount() {
            return mCrimes.size();
        }
    }


}
