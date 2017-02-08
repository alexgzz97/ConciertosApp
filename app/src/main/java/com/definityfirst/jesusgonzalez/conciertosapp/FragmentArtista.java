package com.definityfirst.jesusgonzalez.conciertosapp;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by jesus.gonzalez on 07/02/2017.
 */

public class FragmentArtista extends Fragment {
    public Artist artista;
    Event[] events;
    ImageView artistaImage;
    TextView artistaName;
    String urlname;
    String urlmbid;
    ListView listaeventos;



    public FragmentArtista(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_artista, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        FloatingActionButton myFab = (FloatingActionButton) getView().findViewById(R.id.myFAB);
        myFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Toast.makeText(getActivity(), artista.getName()+" ha sido agregado a tus artistas favoritos!", Toast.LENGTH_LONG).show();

            }
        });

        artistaImage = (ImageView) getView().findViewById(R.id.imageViewArtista);
        artistaName = (TextView) getView().findViewById(R.id.nameView);
        listaeventos = (ListView) getView().findViewById(R.id.listEvents);
        listaeventos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Event selectedevent = (Event) parent.getItemAtPosition(position);
                 String VenueName = selectedevent.getVenue().getPlace();
                 Double Lat = selectedevent.getVenue().getLatitude();
                 Double Lng = selectedevent.getVenue().getLongitude();
                 String Country = selectedevent.getVenue().getCountry();
                 String City = selectedevent.getVenue().getCity();
                ((MainActivity)getActivity()).setMap(Lat,Lng,VenueName);




            }
        });
        String name=getArguments().getString("name");
        urlname="http://api.bandsintown.com/artists/"+name+".json?api_version=2.0&app_id=ConciertosDemoApp";
        new ArtistAsyncTask().execute(name);
        urlmbid="http://api.bandsintown.com/artists/"+name+"/events?format=json&api_version=2.0&app_id=ConciertosDemoApp";
        new EventAsyncTask().execute(name);
    }


    public void onBackPressed()
    {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        fm.popBackStack();
    }

    class ArtistAsyncTask extends AsyncTask<String, Void, Artist> {
        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(getActivity());
            dialog.setMessage("Loading, please wait");
            dialog.setTitle("Connecting server");
            dialog.show();
            dialog.setCancelable(false);
        }

        @Override
        protected Artist doInBackground(String... nombre) {

                try {
                    URL url = new URL(urlname);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                    InputStream stream = new BufferedInputStream(urlConnection.getInputStream());
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
                    StringBuilder builder = new StringBuilder();

                    String inputString;
                    while ((inputString = bufferedReader.readLine()) != null) {
                        builder.append(inputString);
                    }
                    Gson gson = new Gson();
                    artista = gson.fromJson(builder.toString(), Artist.class);
                    urlConnection.disconnect();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return artista;
        }

        @Override
        protected void onPostExecute(Artist artista) {
            if (artista!=null){
            ImageLoader imageLoader = ImageLoader.getInstance();
            imageLoader.displayImage(artista.getImageUrl(), artistaImage);
            artistaName.setText(artista.getName());}
            else
                Toast.makeText(getContext(), "Artista no encontrado", Toast.LENGTH_SHORT).show();
            if(dialog != null && dialog.isShowing()){
                dialog.dismiss();
            }
            if(isCancelled()) return;
        }
}

    class EventAsyncTask extends AsyncTask<String, Void, Event[]> {
        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Event[] doInBackground(String... nombre) {

            try {
                URL url = new URL(urlmbid);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                InputStream stream = new BufferedInputStream(urlConnection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
                StringBuilder builder = new StringBuilder();

                String inputString;
                while ((inputString = bufferedReader.readLine()) != null) {
                    builder.append(inputString);
                }
                Gson gson = new Gson();

               events = gson.fromJson(builder.toString(), Event[].class);
                urlConnection.disconnect();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return events;
        }

        @Override
        protected void onPostExecute(Event[] events) {
            if (events.length>0) {
                List<Event> list=(Arrays.asList(events));
                ListAdapter eventAdapter = new ListAdapter(getContext(), R.layout.item_eventos, list);
                listaeventos.setAdapter(eventAdapter);
                Toast.makeText(getContext(), "Hay eventos proximos de este artista", Toast.LENGTH_SHORT).show();
            }
            else
                Toast.makeText(getContext(), "No hay eventos proximos de este artista", Toast.LENGTH_SHORT).show();
            if(dialog != null && dialog.isShowing()){
                dialog.dismiss();
            }
            if(isCancelled()) return;
        }
    }


    public class ListAdapter extends ArrayAdapter<Event> {

        public ListAdapter(Context context, int textViewResourceId) {
            super(context, textViewResourceId);
        }

        public ListAdapter(Context context, int resource, List<Event> items) {
            super(context, resource, items);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View v = convertView;

            if (v == null) {
                LayoutInflater vi;
                vi = LayoutInflater.from(getContext());
                v = vi.inflate(R.layout.item_eventos, null);
            }

            Event p = getItem(position);

            if (p != null) {
                TextView tt1 = (TextView) v.findViewById(R.id.EventTitle);
                TextView tt2 = (TextView) v.findViewById(R.id.EventDate);


                if (tt1 != null) {
                    tt1.setText(p.getTitle());
                    tt2.setText(p.getFormattedDatetime());
                }

            }

            return v;
        }

    }


}