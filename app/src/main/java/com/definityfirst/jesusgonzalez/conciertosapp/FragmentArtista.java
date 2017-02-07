package com.definityfirst.jesusgonzalez.conciertosapp;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by jesus.gonzalez on 07/02/2017.
 */

public class FragmentArtista extends Fragment {
    public static String name= null;

    public FragmentArtista(){
    }

    // The onCreateView method is called when Fragment should create its View object hierarchy,
    // either dynamically or via XML layout inflation.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        name=getArguments().getString("name");
        return inflater.inflate(R.layout.fragment_artista, parent, false);


    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Log.d("nombre",name);

    }

    public void onBackPressed()
    {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        fm.popBackStack();
    }
}