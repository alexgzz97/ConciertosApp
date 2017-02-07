package com.definityfirst.jesusgonzalez.conciertosapp;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by jesus.gonzalez on 07/02/2017.
 */

public class FragmentFavoritos extends Fragment {
    // The onCreateView method is called when Fragment should create its View object hierarchy,
    // either dynamically or via XML layout inflation.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favoritos, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {


    }

}
