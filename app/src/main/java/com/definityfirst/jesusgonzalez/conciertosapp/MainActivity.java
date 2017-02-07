package com.definityfirst.jesusgonzalez.conciertosapp;

import android.content.DialogInterface;
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

public class MainActivity extends AppCompatActivity {
    String bArtista;
    FragmentManager fragmentManager;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .build();
        ImageLoader.getInstance().init(config);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setFragment(new FragmentFavoritos());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
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
                                //do something if you need
                                dialog.cancel();
                            }
                        });
                alertDialog.show();  //<-- See This!

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

}