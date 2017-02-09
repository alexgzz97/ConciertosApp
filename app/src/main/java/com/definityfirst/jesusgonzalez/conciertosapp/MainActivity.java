package com.definityfirst.jesusgonzalez.conciertosapp;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import com.lapism.searchview.SearchView;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    DBHandler db = new DBHandler(this);
    String bArtista;
    FragmentManager fragmentManager;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .memoryCache(new LruMemoryCache(2 * 1024 * 1024))
                .diskCache(new UnlimitedDiskCache(getCacheDir()))
                .diskCacheFileCount(100)
                .build();
        ImageLoader.getInstance().init(config);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (savedInstanceState == null) {
            setFragment(new FragmentFavoritos());
        }
        db.getWritableDatabase();

    }
    public void agregarArtista(Artist artist){
        db.addArtist(artist);
    }
    public void eliminarArtista(Artist artist){
        db.deleteArtist(artist);
    }

    public List<Artist> listaArtistas(List<Artist> list){
        list=db.getAllArtist();
        return list;
    }

    public boolean isAlreadyFavorite(String name) {
        SQLiteDatabase data = new DBHandler(this).getReadableDatabase();
        Cursor cur = data.rawQuery("SELECT * FROM " + "artists" + " WHERE name = '" + name + "'", null);
        boolean exist = (cur.getCount() > 0);
        cur.close();
        db.close();
        return exist;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_buscar: {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                alertDialog.setTitle("Buscar Artista");
                final EditText input = new EditText(this);
                input.setHint("Nombre de la banda o Artista");
                alertDialog.setView(input);
                alertDialog.setCancelable(true);
                alertDialog.setPositiveButton( "Buscar",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        try {
                            bArtista= input.getText().toString();
                            Fragment fragment = new FragmentArtista();
                            bundle = new Bundle();
                            bundle.putString("name",bArtista);
                            fragment.setArguments(bundle);
                            FragmentManager fragmentManager = getSupportFragmentManager();
                                             fragmentManager.beginTransaction()
                                                  .replace(R.id.fragment_container, fragment)
                                                    .addToBackStack(null)
                                                   .commit();


                        } catch (Exception e) {

                        }
                    }
                })
                        .setNegativeButton("Cancelar",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });
                alertDialog.show();

            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void setFragment(Fragment fragment) {
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }

    public void setMap(Double lat, Double lng,String title){
        bundle = new Bundle();
        bundle.putDouble("lat",lat);
        bundle.putDouble("lng",lng);
        bundle.putString("title",title);
        Fragment fragment = new FragmentMaps();
        fragment.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }

    public void FragmentoArtista(String name){
    try {
        bArtista= name;
        Fragment fragment = new FragmentArtista();
        bundle = new Bundle();
        bundle.putString("name",bArtista);
        fragment.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();


    } catch (Exception e) {
    }
    }

    public void setActionBarTitle(String title){
        setActionBarTitle(title);
    }

}
