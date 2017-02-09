package com.definityfirst.jesusgonzalez.conciertosapp;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jesus.gonzalez on 07/02/2017.
 */

public class FragmentFavoritos extends Fragment {
    FavAdapter favap;
    List<Artist> favArtist = new ArrayList<Artist>();
    // The onCreateView method is called when Fragment should create its View object hierarchy,
    // either dynamically or via XML layout inflation.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favoritos, parent, false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        setHasOptionsMenu(false);
        favArtist=((MainActivity)getActivity()).listaArtistas(favArtist);
        final GridView favshow = (GridView) getView().findViewById(R.id.fav_gridview);
        favap = new FavAdapter(getActivity());
        favshow.setAdapter(favap);
        favshow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                    Artist artistfav=favArtist.get(position);
                    String intentname=artistfav.getName();
                ((MainActivity)getActivity()).FragmentoArtista(intentname);


            }
        });
        favshow.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           final int position, long arg3) {
                new AlertDialog.Builder(getActivity())
                        .setMessage("Desea eliminar este artista?")
                        .setPositiveButton(
                                "Si",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(
                                            DialogInterface dialog,
                                            int which) {
                                        ((MainActivity) getActivity()).eliminarArtista(favArtist.get(position));
                                        favap.notifyDataSetChanged();
                                        favArtist.clear();
                                        favArtist=((MainActivity)getActivity()).listaArtistas(favArtist);

                                    }
                                })
                        .setNegativeButton(
                                "No",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(
                                            DialogInterface dialog,
                                            int which) {
                                        dialog.cancel();
                                    }
                                }).show();
                return true;
            }
        });
    }

    private final class FavAdapter extends BaseAdapter {

        private final LayoutInflater mInflater;

        public FavAdapter(Context context) {
            mInflater = LayoutInflater.from(context);

        }

        @Override
        public int getCount() {
            return favArtist.size();
        }

        @Override
        public Artist getItem(int i) {
            return favArtist.get(i);
        }
        @Override
        public long getItemId(int i) {
            return i;
        }


        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View v = view;
            ImageView picture;
            TextView name;

            if (v == null) {
                v = mInflater.inflate(R.layout.fav_grid_item, viewGroup, false);
                v.setTag(R.id.picture, v.findViewById(R.id.picture));
                v.setTag(R.id.text, v.findViewById(R.id.text));
            }

            picture = (ImageView) v.getTag(R.id.picture);
            name = (TextView) v.getTag(R.id.text);

            Artist artist = getItem(i);
            ImageLoader imageLoader = ImageLoader.getInstance();
            imageLoader.displayImage(artist.getThumbUrl(), picture);
            name.setText(artist.getName());

            return v;
        }

    }


}
